package land.generic.kdorf.modules.handlers

import com.soywiz.korio.async.launchAsap
import dev.kord.core.event.message.MessageCreateEvent
import land.generic.kdorf.modules.CommonWords
import java.time.OffsetDateTime
import kotlin.random.Random

class AutoResponseHandler(val commonWords: CommonWords, val chaos: Random) : MessageHandler {
    var nextTime = OffsetDateTime.now()
    override fun canHandle(message: MessageCreateEvent): Boolean {
        return !(message.member?.isBot ?: true) && (nextTime.isBefore(OffsetDateTime.now()) || chaos.nextInt(200) == 0)
    }

    override suspend fun handle(event: MessageCreateEvent) {
        nextTime = OffsetDateTime.now().plusMinutes(chaos.nextLong(60, 60 * 5))
        val words = event.message.content.split(" ")
        // Find fun words by filtering out all the common ones.
        val goodWords = words.filter { !commonWords.lookup.search(it) }
        val actualWords = goodWords.ifEmpty { words }

        var word = actualWords.random(chaos)
        if (word.contains(",") || word.contains(";")) {
            word += actualWords.random(chaos)
        }
        val layers = chaos.nextInt(4)
        for (layer in 0..layers) {
            word = "*$word*"
        }
        if (chaos.nextInt(10) == 0) {
            word += "!"
        }

        if (chaos.nextInt(10) == 0) {
            word += "?"
        }
        event.message.channel.createMessage(word)


    }
}