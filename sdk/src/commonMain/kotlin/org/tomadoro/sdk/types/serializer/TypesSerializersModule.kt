package org.tomadoro.sdk.types.serializer

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.tomadoro.sdk.types.TimerSessionCommand
import org.tomadoro.sdk.types.TimerUpdate

internal val TypesSerializersModule = SerializersModule {
    polymorphic(TimerSessionCommand::class) {
        subclass(TimerSessionCommand.StartTimer::class)
        subclass(TimerSessionCommand.StopTimer::class)
        subclass(TimerSessionCommand.LeaveSession::class)
        subclass(TimerSessionCommand.ConfirmAttendance::class)
    }
    polymorphic(TimerUpdate::class) {
        subclass(TimerUpdate.TimerStarted::class)
        subclass(TimerUpdate.TimerStopped::class)
        subclass(TimerUpdate.SessionFailed::class)
        subclass(TimerUpdate.SessionFinished::class)
        subclass(TimerUpdate.Settings::class)
        subclass(TimerUpdate.Confirmation::class)
    }
}