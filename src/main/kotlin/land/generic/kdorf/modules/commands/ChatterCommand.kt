package land.generic.kdorf.modules.commands

import land.generic.kdorf.CommandContext
import land.generic.kdorf.modules.chatting.ChatterGenerator
import land.generic.kdorf.parsing.ArgType
import land.generic.kdorf.parsing.Subcommand
import javax.inject.Inject

class ChatterCommand @Inject constructor(
    private val context: CommandContext,
    private val generator: ChatterGenerator,
    private val rememberCommand: RememberCommand
) : Subcommand("chatter", "Chatter") {
    class RememberCommand @Inject constructor(
        private val context: CommandContext,
        private val generator: ChatterGenerator):Subcommand("remember","Remember a word"){
            val word by argument(ArgType.String,"word","The word to remember")
        override suspend fun execute() {
            generator.remember(word)
            context.event.message.channel.createMessage("I 'member!")
        }
    }
    init {
        subcommands(rememberCommand)
    }
    override suspend fun execute() {
        context.event.message.channel.createMessage(generator.generate(5).joinToString(" "))
    }
}
