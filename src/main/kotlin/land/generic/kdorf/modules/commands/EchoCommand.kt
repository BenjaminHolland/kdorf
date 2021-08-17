package land.generic.kdorf.modules.commands

import land.generic.kdorf.CommandContext
import land.generic.kdorf.parsing.ArgType
import land.generic.kdorf.parsing.Subcommand
import javax.inject.Inject

class EchoCommand @Inject constructor(val context: CommandContext) :
    Subcommand("echo", "Repeat a word") {
    private val input by argument(ArgType.String, "input", "The text to echo")
    override suspend fun execute() {
        context.event.message.channel.createMessage(input)
    }

}