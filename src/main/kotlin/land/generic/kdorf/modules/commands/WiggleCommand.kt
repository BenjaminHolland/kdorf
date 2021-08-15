package land.generic.kdorf.modules.commands

import land.generic.kdorf.DorfCommandContext
import land.generic.kdorf.parsing.Subcommand
import javax.inject.Inject

class WiggleCommand @Inject constructor(val context: DorfCommandContext) : Subcommand("wiggle", "Do some wiggles") {
    override suspend fun execute() {
        context.messageCreateEvent.message.channel.createMessage("https://ve.media.tumblr.com/tumblr_q7octxuXdx1u32bhn.mp4")
    }
}