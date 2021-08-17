package land.generic.kdorf.modules.commands

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.font.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.localVfs
import com.soywiz.korio.file.std.resourcesVfs
import dev.kord.core.behavior.channel.createMessage
import land.generic.kdorf.CommandContext
import land.generic.kdorf.modules.zalgo.ZalgoTransformer
import land.generic.kdorf.parsing.ArgType
import land.generic.kdorf.parsing.Subcommand
import land.generic.kdorf.parsing.default
import java.io.File
import javax.inject.Inject

class ZalgoCommand @Inject constructor(val context: CommandContext, val zalgo: ZalgoTransformer) :
    Subcommand("zalgo", "Generate spooky text.") {
    private val input by argument(ArgType.String, "input", "The text to spookify.")
    private val minUp by option(ArgType.Int, "minUp", shortName = "u").default(5)
    private val maxUp by option(ArgType.Int, "maxUp", shortName = "U").default(10)
    private val minMid by option(ArgType.Int, "minMid", shortName = "m").default(5)
    private val maxMid by option(ArgType.Int, "maxMid", shortName = "M").default(10)
    private val minDown by option(ArgType.Int, "minDown", shortName = "d").default(5)
    private val maxDown by option(ArgType.Int, "maxDown", shortName = "D").default(10)
    override suspend fun execute() {
        val text = zalgo.transform(input, minUp..maxUp, minMid..maxMid, minDown..maxDown)
        println()
        val font = resourcesVfs["arialuni.ttf"].readTtfFont()
        var bmp = Bitmap32(1024, 1024, false)
        bmp = bmp.context2d {
            drawText(text,0.0,512.0,font = DefaultTtfFont)
        }
        bmp.showImageAndWait()
        val file = File.createTempFile("zalgo", ".png")
        val dumbFile = localVfs(file)
        bmp.writeTo(dumbFile, formats = PNG)

        val inputStream = file.inputStream()
        context.event.message.channel.createMessage { this.addFile("zalgo.png", inputStream) }

    }
}