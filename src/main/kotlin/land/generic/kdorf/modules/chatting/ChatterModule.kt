package land.generic.kdorf.modules.chatting

import com.uchuhimo.konf.Config
import dagger.Module
import dagger.Provides
import land.generic.kdorf.utility.Resources
import java.io.File
import javax.inject.Singleton

@Module
class ChatterModule {
    @Provides
    @Singleton
    @ChatterConfig
    fun provideChatterConfig():Config {
        val file = File("C:\\Users\\Benjamin\\dorf-chat.json")
        if (!file.exists()) {
            file.createNewFile()
            file.writeText(Resources.open("empty-vocab.json").bufferedReader().readText())
        }
        return Config { addSpec(ChatterConfigSpec) }.from.json.watchFile("C:/Users/Benjamin/dorf-chat.json")
    }

}