package io.timemates.api.grpc

import com.google.protobuf.Empty
import io.grpc.ManagedChannelBuilder
import io.timemates.api.authorizations.AuthorizationServiceGrpcKt
import io.timemates.api.files.FilesServiceGrpcKt
import io.timemates.api.grpc.mappers.AuthorizationsMapper
import io.timemates.api.grpc.mappers.TimersMapper
import io.timemates.api.grpc.mappers.UsersMapper
import io.timemates.api.timers.TimersServiceGrpcKt
import io.timemates.api.users.UsersServiceGrpcKt
import io.timemates.sdk.authorization.email.requests.ConfigureNewAccountRequest
import io.timemates.sdk.authorization.email.requests.ConfirmAuthorizationRequest
import io.timemates.sdk.authorization.email.requests.StartAuthorizationRequest
import io.timemates.sdk.authorization.email.types.value.VerificationHash
import io.timemates.sdk.authorization.sessions.requests.GetAuthorizationSessionsRequest
import io.timemates.sdk.authorization.sessions.requests.TerminateCurrentAuthorizationSessionRequest
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.engine.TimeMatesRequestsEngine
import io.timemates.sdk.common.exceptions.UnsupportedException
import io.timemates.sdk.common.providers.AccessHashProvider
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.TimeMatesRequest
import io.timemates.sdk.common.types.value.Count
import io.timemates.sdk.common.types.value.PageToken
import io.timemates.sdk.files.requests.GetFileBytesRequest
import io.timemates.sdk.files.requests.UploadFileRequest
import io.timemates.sdk.timers.requests.CreateTimerRequest
import io.timemates.sdk.timers.types.value.TimerId
import io.timemates.sdk.users.profile.requests.EditProfileRequest
import io.timemates.sdk.users.profile.requests.GetUsersRequest
import io.timemates.sdk.users.settings.requests.EditEmailRequest
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import kotlin.reflect.KClass
import io.timemates.api.authorizations.requests.ConfirmAuthorizationRequestOuterClass.ConfirmAuthorizationRequest as GrpcConfirmAuthorizationRequest
import io.timemates.api.authorizations.requests.GetAuthorizationsRequestOuterClass.GetAuthorizationsRequest as GrpcGetAuthorizationsRequest
import io.timemates.api.authorizations.requests.StartAuthorizationRequestOuterClass.StartAuthorizationRequest as GrpcStartAuthorizationRequest
import io.timemates.api.files.requests.GetFileBytesRequestOuterClass.GetFileBytesRequest as GrpcGetFileBytesRequest
import io.timemates.api.timers.requests.CreateTimerRequestOuterClass.CreateTimerRequest as GrpcCreateTimerRequest
import io.timemates.api.users.requests.CreateProfileRequestOuterClass.CreateProfileRequest as GrpcCreateProfileRequest
import io.timemates.api.users.requests.EditUserRequestOuterClass.EditUserRequest as GrpcEditUserRequest
import io.timemates.api.users.requests.GetUsersRequestOuterClass.GetUsersRequest as GrpcGetUsersRequest
import io.timemates.sdk.common.types.Empty as SdkEmpty

public class GrpcTimeMatesRequestsEngine(
    endpoint: String = "https://api.timemates.io",
    tokenProvider: AccessHashProvider,
) : TimeMatesRequestsEngine {
    private val channel = ManagedChannelBuilder.forTarget(endpoint)
        .usePlaintext()
        .build()

    private val authorizationService = AuthorizationServiceGrpcKt.AuthorizationServiceCoroutineStub(channel)
    private val authMapper = AuthorizationsMapper()

    private val filesService = FilesServiceGrpcKt.FilesServiceCoroutineStub(channel)

    private val usersService = UsersServiceGrpcKt.UsersServiceCoroutineStub(channel)
    private val usersMapper = UsersMapper()

    private val timersService = TimersServiceGrpcKt.TimersServiceCoroutineStub(channel)
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
                    .build()
            ).let { response ->
                GetAuthorizationSessionsRequest.Result(
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

            is UploadFileRequest -> unsupported<UploadFileRequest>()

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
                    .build()
            ).let { GetUsersRequest.Result(it.usersList.map { usersMapper.grpcUserToSdkUser(it) }) }

            is EditEmailRequest -> unsupported<EditEmailRequest>()

            is CreateTimerRequest -> timersService.createTimer(
                GrpcCreateTimerRequest.newBuilder()
                    .setName(request.name.string)
                    .setDescription(request.description.string)
                    .setSettings(timersMapper.sdkSettingsToGrpcSettings(request.settings))
                    .build()
            ).let { CreateTimerRequest.Result(TimerId.createOrThrow(it.timerId)) }

            else -> throw UnsupportedException("Unsupported request of type ${request::class.simpleName}")
        } as T
    }

    private fun unsupported(kClass: KClass<*>): Nothing =
        throw UnsupportedException("Request of type ${kClass.simpleName} is not supported")

    private inline fun <reified T> unsupported(): Nothing = unsupported(T::class)
}