package land.generic.kdorf.modules.commands

import land.generic.kdorf.parsing.ArgParser
import javax.inject.Inject

class DorfCommandParser @Inject constructor(
    private val echo: EchoCommand,
    private val zalgo: ZalgoCommand,
    private val wiggle: WiggleCommand
) {
    suspend fun parse(args: List<String>) {
        val parser = ArgParser("kdorf-root")
        parser.subcommands(echo, zalgo, wiggle)
        parser.parse(args.toTypedArray())
    }
}

