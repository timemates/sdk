package org.tomadoro.sdk.results.serializer

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.tomadoro.sdk.results.*

/**
 * Serialization module for all results.
 * It contains polymorphic serializers.
 */
internal val ResultsSerializersModule: SerializersModule = SerializersModule {
    polymorphic(SignWithGoogleResult::class) {
        subclass(SignWithGoogleResult.Success::class)
    }
    polymorphic(GetUserIdResult::class) {
        subclass(GetUserIdResult.Success::class)
    }
    polymorphic(CreateInviteResult::class) {
        subclass(CreateInviteResult.Success::class)
        subclass(CreateInviteResult.NoAccess::class)
    }
    polymorphic(CreateTimerResult::class) {
        subclass(CreateTimerResult.Success::class)
    }
    polymorphic(GetInvitesResult::class) {
        subclass(GetInvitesResult.Success::class)
    }
    polymorphic(GetTimerResult::class) {
        subclass(GetTimerResult.Success::class)
        subclass(GetTimerResult.NotFound::class)
    }
    polymorphic(GetTimerResult::class) {
        subclass(GetTimerResult.Success::class)
        subclass(GetTimerResult.NotFound::class)
    }
    polymorphic(GetTimersResult::class) {
        subclass(GetTimersResult.Success::class)
    }
    polymorphic(JoinByCodeResult::class) {
        subclass(JoinByCodeResult.Success::class)
        subclass(JoinByCodeResult.NotFound::class)
    }
    polymorphic(RemoveInviteResult::class) {
        subclass(RemoveInviteResult.Success::class)
        subclass(RemoveInviteResult.NotFound::class)
        subclass(RemoveInviteResult.NoAccess::class)
    }
    polymorphic(RemoveTimerResult::class) {
        subclass(RemoveTimerResult.Success::class)
        subclass(RemoveTimerResult.NotFound::class)
    }
    polymorphic(RemoveTokenResult::class) {
        subclass(RemoveTokenResult.Success::class)
    }
    polymorphic(RenewTokenResult::class) {
        subclass(RenewTokenResult.Success::class)
    }
    polymorphic(SetTimerSettingsResult::class) {
        subclass(SetTimerSettingsResult.Success::class)
        subclass(SetTimerSettingsResult.NoAccess::class)
    }
    polymorphic(StartTimerResult::class) {
        subclass(StartTimerResult.Success::class)
        subclass(StartTimerResult.NoAccess::class)
    }
    polymorphic(StopTimerResult::class) {
        subclass(StopTimerResult.Success::class)
        subclass(StopTimerResult.NoAccess::class)
    }
}