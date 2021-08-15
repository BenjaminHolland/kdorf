package land.generic.kdorf.modules.handlers

import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import dev.kord.core.event.message.MessageCreateEvent

interface MessageHandler {
    fun canHandle(message: MessageCreateEvent): Boolean
    suspend fun handle(message: MessageCreateEvent)
}
