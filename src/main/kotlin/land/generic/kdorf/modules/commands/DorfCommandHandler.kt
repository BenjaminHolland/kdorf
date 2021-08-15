package land.generic.kdorf.modules.commands

import dev.kord.core.event.message.MessageCreateEvent
import land.generic.kdorf.DorfCommandContext
import land.generic.kdorf.components.DorfCommandComponent
import land.generic.kdorf.modules.handlers.MessageHandler
import land.generic.kdorf.parsing.ArgumentTokenizer
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DorfCommandHandler @Inject constructor(
    private val commandContextProvider: Provider<DorfCommandComponent.Builder>
) : MessageHandler {
    override fun canHandle(event: MessageCreateEvent): Boolean {
        return event.member?.let { !it.isBot } ?: false && event.message.content.startsWith("!")
    }

    override suspend fun handle(event: MessageCreateEvent) {
        val command = event.message.content.substring(1)
        val args = ArgumentTokenizer.tokenize(command, false)
        commandContextProvider.get().bindContext(
            DorfCommandContext(event, false)
        ).build().buildParser().parse(args)
    }
}