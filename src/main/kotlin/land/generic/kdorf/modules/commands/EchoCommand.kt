package land.generic.kdorf.modules.commands

import dev.kord.common.entity.DiscordEmbed
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.cache.data.EmbedData
import dev.kord.core.entity.Embed
import dev.kord.rest.builder.message.EmbedBuilder
import land.generic.kdorf.DorfCommandContext
import land.generic.kdorf.parsing.ArgType
import land.generic.kdorf.parsing.Subcommand
import java.util.*
import javax.inject.Inject
import dev.kord.common.entity.optional.*
import dev.kord.common.entity.optional.Optional
import dev.kord.core.cache.data.EmbedVideoData

class EchoCommand @Inject constructor(val context: DorfCommandContext) :
    Subcommand("echo", "Repeat a word") {
    private val input by argument(ArgType.String, "input", "The text to echo")
    override suspend fun execute() {
        context.messageCreateEvent.message.channel.createMessage(input)
    }

}