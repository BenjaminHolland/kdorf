package land.generic.kdorf.modules.settings

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.Feature
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingsModule {
    @Provides
    @Singleton
    @DiscordConfig
    fun provideSecureSettings() =
        Config { addSpec(DiscordConfigSpec) }.enable(Feature.FAIL_ON_UNKNOWN_PATH).from.json.resource("secure/discord.json")

}