package land.generic.kdorf.modules.chatting

import com.soywiz.korma.random.randomWithWeights
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec
import com.uchuhimo.konf.source.json.toJson
import dagger.Module
import dagger.Provides
import land.generic.kdorf.modules.CommonWords
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.random.Random

object ChatterConfigSpec : ConfigSpec("chatter") {
    val vocab by required<Set<String>>("vocab", "Known words.")
}


@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ChatterConfig


private const val SENTENCE_ENDINGS = ".!?"
private val SENTENCE_ENDINGS_WEIGHTS = listOf(0.5, 0.3, 0.2)

@Singleton
class ChatterGenerator @Inject constructor(
    @ChatterConfig private val config: Config,
    private val chaos: Random,
    private val common: CommonWords
) {
    val saveHandler = config.afterSet { _, _ -> config.toJson.toFile("C:/Users/Ben/dorf-chat.json") }
    fun remember(word: String) {
        config[ChatterConfigSpec.vocab] = config[ChatterConfigSpec.vocab] + setOf(word)
    }

    fun sentence(count: Int): String {
        val end = SENTENCE_ENDINGS.toList().randomWithWeights(SENTENCE_ENDINGS_WEIGHTS, chaos)
        val words = config[ChatterConfigSpec.vocab]
        return (List(count) {
            if (chaos.nextBoolean())
                common.lookup.random(chaos)
            else
                words.random(chaos)
        }.joinToString(" ") + end).let {
            it[0].uppercase() + it.substring(1)
        }
    }

    fun generate(count: Int): List<String> {
        return List(count) {
            sentence((5..10).random(chaos))
        }
    }
}

@Module
class ChatterModule {
    @Provides
    @Singleton
    @ChatterConfig
    fun provideChatterConfig() =
        Config { addSpec(ChatterConfigSpec) }.from.json.watchFile("C:/Users/Ben/dorf-chat.json")
}