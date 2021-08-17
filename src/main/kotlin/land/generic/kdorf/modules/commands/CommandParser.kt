package land.generic.kdorf.modules.commands

import land.generic.kdorf.parsing.ArgParser
import javax.inject.Inject

class CommandParser @Inject constructor(
    private val echo: EchoCommand,
    private val zalgo: ZalgoCommand,
    private val wiggle: WiggleCommand,
    private val chatter: ChatterCommand
) {
    suspend fun parse(args: List<String>) {
        val parser = ArgParser("kdorf-root")
        parser.subcommands(echo, zalgo, wiggle,chatter)
        parser.parse(args.toTypedArray())
    }
}

