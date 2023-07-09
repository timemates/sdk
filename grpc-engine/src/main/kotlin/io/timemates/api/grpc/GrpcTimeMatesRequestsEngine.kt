@file:Suppress("UNCHECKED_CAST")

package io.timemates.api.grpc

import com.google.protobuf.Empty
import com.google.protobuf.kotlin.toByteString
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusException
import io.timemates.api.authorizations.AuthorizationServiceGrpcKt
import io.timemates.api.files.FilesServiceGrpcKt
import io.timemates.api.files.requests.UploadFileRequestKt.fileMetadata
import io.timemates.api.grpc.factory.GrpcEngineBuilder
import io.timemates.api.grpc.internal.mapException
import io.timemates.api.grpc.mappers.AuthorizationsMapper
import io.timemates.api.grpc.mappers.FilesMapper
import io.timemates.api.grpc.mappers.TimersMapper
import io.timemates.api.grpc.mappers.UsersMapper
import io.timemates.api.timers.TimerSessionsServiceGrpcKt
import io.timemates.api.timers.TimersServiceGrpcKt
import io.timemates.api.users.UsersServiceGrpcKt
import io.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.authorization.types.value.AccessHash
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.exceptions.AlreadyExistsException
import io.timemates.sdk.common.exceptions.InternalServerError
import io.timemates.sdk.common.exceptions.InvalidArgumentException
import io.timemates.sdk.common.exceptions.NotFoundException
import io.timemates.sdk.common.exceptions.PermissionDeniedException
import io.timemates.sdk.common.exceptions.UnauthorizedException
import io.timemates.sdk.common.exceptions.UnavailableException
import io.timemates.sdk.common.exceptions.UnsupportedException
import io.timemates.sdk.common.pagination.Page
import io.timemates.sdk.common.pagination.PageToken
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.files.requests.GetFileBytesRequest
import io.timemates.sdk.files.requests.UploadFileRequest
import io.timemates.sdk.files.types.value.FileId
import io.timemates.sdk.timers.members.invites.requests.CreateInviteRequest
import io.timemates.sdk.timers.members.invites.requests.GetInvitesRequest
import io.timemates.sdk.timers.members.invites.requests.RemoveInviteRequest
import io.timemates.sdk.timers.members.invites.types.value.InviteCode
import io.timemates.sdk.timers.members.requests.GetMembersRequest
import io.timemates.sdk.timers.members.requests.KickMemberRequest
import io.timemates.sdk.timers.requests.*
import io.timemates.sdk.timers.sessions.requests.*
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.users.profile.requests.EditProfileRequest
import io.timemates.sdk.users.profile.requests.GetUsersRequest
import io.timemates.sdk.users.profile.requests.SetGravatarAvatarRequest
import io.timemates.sdk.users.settings.requests.EditEmailRequest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import kotlin.reflect.KClass
import io.timemates.api.authorizations.requests.ConfirmAuthorizationRequestOuterClass.ConfirmAuthorizationRequest as GrpcConfirmAuthorizationRequest
import io.timemates.api.authorizations.requests.GetAuthorizationsRequestOuterClass.GetAuthorizationsRequest as GrpcGetAuthorizationsRequest
import io.timemates.api.authorizations.requests.StartAuthorizationRequestOuterClass.StartAuthorizationRequest as GrpcStartAuthorizationRequest
import io.timemates.api.files.requests.GetFileBytesRequestOuterClass.GetFileBytesRequest as GrpcGetFileBytesRequest
import io.timemates.api.files.requests.uploadFileRequest as buildUploadFileGrpcRequest
import io.timemates.api.timers.members.invites.requests.getInvitesRequest as buildGetInvitesGrpcRequest
import io.timemates.api.timers.members.invites.requests.inviteMemberRequest as buildCreateInviteGrpcRequest
import io.timemates.api.timers.members.invites.requests.removeInviteRequest as buildRemoveInviteGrpcRequest
import io.timemates.api.timers.members.requests.getMembersRequest as buildGetMembersGrpcRequest
import io.timemates.api.timers.members.requests.kickMemberRequest as buildKickMemberGrpcRequest
import io.timemates.api.timers.requests.CreateTimerRequestOuterClass.CreateTimerRequest as GrpcCreateTimerRequest
import io.timemates.api.timers.requests.EditTimerInfoRequest.EditTimerRequest as GrpcEditTimerRequest
import io.timemates.api.timers.requests.getTimerRequest as buildGetTimerGrpcRequest
import io.timemates.api.timers.requests.getTimersRequest as buildGetTimersGrpcRequest
import io.timemates.api.timers.requests.removeTimerRequest as buildRemoveTimerGrpcRequest
import io.timemates.api.timers.sessions.requests.joinTimerSessionRequest as buildJoinTimerSessionGrpcRequest
import io.timemates.api.timers.sessions.requests.startTimerRequest as buildStartTimerGrpcRequest
import io.timemates.api.timers.sessions.requests.stopTimerRequest as buildStopTimerGrpcRequest
import io.timemates.api.users.requests.CreateProfileRequestOuterClass.CreateProfileRequest as GrpcCreateProfileRequest
import io.timemates.api.users.requests.EditUserRequestOuterClass.EditUserRequest as GrpcEditUserRequest
import io.timemates.api.users.requests.GetUsersRequestOuterClass.GetUsersRequest as GrpcGetUsersRequest
import io.timemates.api.users.requests.SetGravatarRequestOuterClass.SetGravatarRequest as GrpcSetGravatarRequest
import io.timemates.sdk.common.types.Empty as SdkEmpty

/**
 * gRPC-based implementation of the TimeMatesRequestsEngine interface.
 *
 * @param endpoint The endpoint URL for the gRPC API. Default value is "https://api.timemates.io".
 */
public class GrpcTimeMatesRequestsEngine(
    endpoint: String = "api.timemates.io",
    grpcEngineBuilder: GrpcEngineBuilder,
) : TimeMatesRequestsEngine {
    private companion object {
        val ACCESS_TOKEN: Metadata.Key<String> = Metadata.Key.of("access-token", Metadata.ASCII_STRING_MARSHALLER)
    }

    private val channel = grpcEngineBuilder.createGrpcEngine(endpoint)

    private val authorizationService = AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineStub(channel)
    private val authMapper = AuthorizationsMapper()

    private val filesService = FilesServiceGrpcKt.FilesServiceCoroutineStub(channel)
    private val filesMapper = FilesMapper()

    private val usersService = UsersServiceGrpcKt.UsersServiceCoroutineStub(channel)
    private val usersMapper = UsersMapper()

    private val timersService = TimersServiceGrpcKt.TimersServiceCoroutineStub(channel)
    private val timerSessionsService = TimerSessionsServiceGrpcKt.TimerSessionsServiceCoroutineStub(channel)
    private val timersMapper = TimersMapper()

    override suspend fun <T : TimeMatesEntity> execute(request: TimeMatesRequest<T>): Result<T> = runCatching {
        when (request) {
            is StartAuthorizationRequest -> authorizationService.startAuthorization(
                GrpcStartAuthorizationRequest
                    .newBuilder()
                    .setEmailAddress(request.emailAddress.string)
                    .build()
            ).let {
                StartAuthorizationRequest.Result(
                    verificationHash = VerificationHash.createOrThrow(it.verificationHash),
                    attempts = Count.createOrThrow(it.attempts),
                    expiresAt = Instant.fromEpochMilliseconds(it.expiresAt),
                )
            }

            is ConfirmAuthorizationRequest -> authorizationService.confirmAuthorization(
                GrpcConfirmAuthorizationRequest
                    .newBuilder()
                    .setConfirmationCode(request.confirmationCode.string)
                    .setVerificationHash(request.verificationHash.string)
                    .build()
            ).let { response ->
                ConfirmAuthorizationRequest.Response(
                    isNewAccount = response.isNewAccount,
                    authorization = response.authorization.takeIf { !response.isNewAccount }
                        ?.let(authMapper::grpcAuthorizationToSdkAuthorization),
                )
            }

            is ConfigureNewAccountRequest -> authorizationService.createProfile(
                GrpcCreateProfileRequest.newBuilder()
                    .setName(request.name.string)
                    .setDescription(request.description.string)
                    .setVerificationHash(request.verificationHash.string)
                    .build()
            ).let {
                ConfigureNewAccountRequest.Result(
                    authMapper.grpcAuthorizationToSdkAuthorization(it.authorization)
                )
            }

            is GetAuthorizationSessionsRequest -> authorizationService.getAuthorizations(
                GrpcGetAuthorizationsRequest.newBuilder()
                    .setPageToken(request.nextPageToken?.string)
                    .build(),
                headers = authorizedMetadata(request.accessHash),
            ).let { response ->
                Page(
                    response.authorizationsList.map(authMapper::grpcAuthorizationToSdkAuthorization),
                    nextPageToken = response.nextPageToken.takeIf { it.isNotEmpty() }
                        ?.let(PageToken::createOrThrow),
                )
            }

            is TerminateCurrentAuthorizationSessionRequest ->
                authorizationService.terminateAuthorization(Empty.getDefaultInstance())
                    .let { SdkEmpty }

            is GetFileBytesRequest ->
                filesService.getFileBytes(
                    GrpcGetFileBytesRequest
                        .newBuilder()
                        .setFileId(request.fileId.string)
                        .build()
                ).map { it.chunk.toByteArray() }.let { GetFileBytesRequest.Result(it) }

            is UploadFileRequest -> filesService.uploadFile(
                requests = flow {
                    emit(buildUploadFileGrpcRequest {
                        metadata = fileMetadata {
                            fileName = request.fileName.string
                            fileType = filesMapper.sdkFileTypeToGrpcFileType(request.fileType)
                        }
                    })

                    request.bytes.collect {
                        emit(buildUploadFileGrpcRequest {
                            chunk = it.toByteString()
                        })
                    }
                }
            ).let { UploadFileRequest.Result(FileId.createOrThrow(it.fileId)) }

            is EditProfileRequest -> usersService.setUser(
                GrpcEditUserRequest.newBuilder()
                    .apply {
                        request.name?.let { name = it.string }
                        request.description?.let { description = it.string }
                        request.avatarId?.let { avatarId = it.string }
                    }.build()
            ).let { SdkEmpty }

            is GetUsersRequest -> usersService.getUsers(
                GrpcGetUsersRequest.newBuilder()
                    .addAllUserId(request.users.map { it.long })
                    .build(),
            ).let { GetUsersRequest.Result(it.usersList.map { usersMapper.grpcUserToSdkUser(it) }) }

            is SetGravatarAvatarRequest -> usersService.setGravatar(
                GrpcSetGravatarRequest.newBuilder()
                    .apply {
                        request.email.let { email = it.string }
                    }.build(),
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is EditEmailRequest -> unsupported<EditEmailRequest>()

            is CreateTimerRequest -> timersService.createTimer(
                GrpcCreateTimerRequest.newBuilder()
                    .setName(request.name.string)
                    .setDescription(request.description.string)
                    .setSettings(timersMapper.sdkSettingsToGrpcSettings(request.settings))
                    .build(),
                headers = authorizedMetadata(request.accessHash)
            ).let { CreateTimerRequest.Result(TimerId.createOrThrow(it.timerId)) }

            is EditTimerRequest -> timersService.editTimer(
                GrpcEditTimerRequest.newBuilder()
                    .setTimerId(request.timerId.long)
                    .apply {
                        request.name?.let { name = it.string }
                        request.description?.let { description = it.string }
                        request.settings?.let {
                            settings = timersMapper.sdkSettingsPatchToGrpcSettingsPatch(it)
                        }
                    }.build(),
                headers = authorizedMetadata(request.accessHash)
            ).let { SdkEmpty }

            is GetTimerRequest -> timersService.getTimer(
                buildGetTimerGrpcRequest {
                    timerId = request.timerId.long
                },
                headers = authorizedMetadata(request.accessHash)
            ).let { timersMapper.grpcTimerToSdkTimer(it) }

            is GetUserTimersRequest -> timersService.getTimers(
                buildGetTimersGrpcRequest {
                    request.pageToken?.let { nextPageToken = it.string }
                },
                headers = authorizedMetadata(request.accessHash)
            ).let { response ->
                Page(
                    response.timersList.map { timersMapper.grpcTimerToSdkTimer(it) },
                    nextPageToken = response.nextPageToken.takeIf {
                        response.hasNextPageToken()
                    }?.let {
                        PageToken.createOrThrow(it)
                    },
                )
            }

            is RemoveTimerRequest -> timersService.removeTimer(
                buildRemoveTimerGrpcRequest {
                    timerId = request.timerId.long
                },
                headers = authorizedMetadata(request.accessHash)
            ).let { SdkEmpty }

            is GetMembersRequest -> timersService.getMembers(
                buildGetMembersGrpcRequest {
                    timerId = request.timerId.long
                    request.pageToken?.let { nextPageToken = it.string }
                },
                headers = authorizedMetadata(request.accessHash)
            ).let { response ->
                Page(
                    response.usersList.map { usersMapper.grpcUserToSdkUser(it) },
                    nextPageToken = response.nextPageToken?.takeIf { it.isNotEmpty() }
                        ?.let { PageToken.createOrThrow(it) }
                )
            }

            is KickMemberRequest -> timersService.kickMember(
                buildKickMemberGrpcRequest {
                    timerId = request.timerId.long
                    userId = request.userId.long
                },
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is CreateInviteRequest -> timersService.createInvite(
                buildCreateInviteGrpcRequest {
                    timerId = request.timerId.long
                    maxJoiners = request.maxJoinersCount.int
                }
            ).let { CreateInviteRequest.Result(InviteCode.createOrThrow(it.inviteCode)) }

            is GetInvitesRequest -> timersService.getInvites(
                buildGetInvitesGrpcRequest {
                    timerId = request.timerId.long
                    request.pageToken?.let { nextPageToken = it.string }
                },
                headers = authorizedMetadata(request.accessHash),
            ).let { response ->
                Page(
                    results = response.invitesList.map { timersMapper.grpcInviteToSdkInvite(it) },
                    nextPageToken = response.nextPageToken.takeIf { it.isNotEmpty() }
                        ?.let { PageToken.createOrThrow(it) }
                )
            }

            is RemoveInviteRequest -> timersService.removeInvite(
                buildRemoveInviteGrpcRequest {
                    timerId = request.timerId.long
                    inviteCode = request.inviteCode.string
                },
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is StartTimerRequest -> timerSessionsService.startTimer(
                buildStartTimerGrpcRequest {
                    timerId = request.timerId.long
                },
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is StopTimerRequest -> timerSessionsService.stopTimer(
                buildStopTimerGrpcRequest {
                    timerId = request.timerId.long
                },
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is JoinTimerSessionRequest -> timerSessionsService.joinSession(
                buildJoinTimerSessionGrpcRequest {
                    timerId = request.timerId.long
                },
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is LeaveTimerSessionRequest -> timerSessionsService.leaveSession(
                Empty.getDefaultInstance(),
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is ConfirmTimerRoundRequest -> timerSessionsService.pingSession(
                Empty.getDefaultInstance(),
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            is PingSessionRequest -> timerSessionsService.pingSession(
                Empty.getDefaultInstance(),
                headers = authorizedMetadata(request.accessHash),
            ).let { SdkEmpty }

            else -> unsupported(request::class)
        } as T
    }.mapException {
        val exception = (it as? StatusException) ?: return@mapException it
        val status = exception.status

        val message = exception.message ?: NO_MESSAGE

        when (status) {
            Status.INVALID_ARGUMENT, Status.FAILED_PRECONDITION ->
                InvalidArgumentException(message, exception)
            Status.UNAUTHENTICATED ->
                UnauthorizedException(message, exception)
            Status.INTERNAL ->
                InternalServerError(message, exception)
            Status.NOT_FOUND ->
                NotFoundException(message, exception)
            Status.ALREADY_EXISTS ->
                AlreadyExistsException(message, exception)
            Status.PERMISSION_DENIED -> PermissionDeniedException(message, exception)
            Status.UNAVAILABLE -> UnavailableException(message, exception)

            else -> InternalServerError(message, exception)
        }
    }

    private fun authorizedMetadata(accessHash: AccessHash) = Metadata().apply {
        put(ACCESS_TOKEN, accessHash.string)
    }

    private fun unsupported(kClass: KClass<*>): Nothing =
        throw UnsupportedException("Request of type ${kClass.simpleName} is not supported")

    private inline fun <reified T> unsupported(): Nothing = unsupported(T::class)
}

private const val NO_MESSAGE = "No message is provided."