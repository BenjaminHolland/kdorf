package land.generic.kdorf

import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import land.generic.kdorf.components.DorfCommandComponent
import land.generic.kdorf.modules.handlers.MessageHandler
import land.generic.kdorf.modules.kord.KordProvider
import land.generic.kdorf.parsing.ArgumentTokenizer
import java.time.OffsetDateTime
import javax.inject.Inject
import javax.inject.Provider
import kotlin.random.Random


class DorfCommandContext(
    var messageCreateEvent: MessageCreateEvent,
    var stop: Boolean
)

@JvmSuppressWildcards
class DorfApp @Inject constructor(
    val clientProvider: KordProvider,
    val chaos: Random,
    val handlers: Map<String, MessageHandler>
) {
    lateinit var client: Kord
    private fun MessageCreateEvent.shouldHandleCommand(): Boolean {
        return member?.let { !it.isBot } ?: false && message.content.startsWith("!")
    }

    suspend fun execute() {
        client = clientProvider.provideKord()
        client.on<MessageCreateEvent> {
            if (handlers.getValue("command").canHandle(this)) {
                handlers.getValue("command").handle(this)
            } else if (
                handlers.getValue("tiktok").canHandle(this)
            ) {
                handlers.getValue("tiktok").handle(this)
            } else {
                if (handlers.getValue("autoresponse").canHandle(this)) {
                    handlers.getValue("autoresponse").handle(this)
                }

            }

        }
        client.login()

    }

}