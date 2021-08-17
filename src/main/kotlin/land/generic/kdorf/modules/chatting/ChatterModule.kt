package land.generic.kdorf.modules.chatting

import com.uchuhimo.konf.Config
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChatterModule {
    @Provides
    @Singleton
    @ChatterConfig
    fun provideChatterConfig() =
        Config { addSpec(ChatterConfigSpec) }.from.json.watchFile("C:/Users/Ben/dorf-chat.json")
}