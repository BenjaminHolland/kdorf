package land.generic.kdorf.modules.commands

import land.generic.kdorf.CommandContext
import land.generic.kdorf.parsing.Subcommand
import javax.inject.Inject

class WiggleCommand @Inject constructor(val context: CommandContext) : Subcommand("wiggle", "Do some wiggles") {
    override suspend fun execute() {
        context.event.message.channel.createMessage("https://ve.media.tumblr.com/tumblr_q7octxuXdx1u32bhn.mp4")
    }
}