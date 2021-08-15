package land.generic.kdorf.modules.settings

import com.uchuhimo.konf.Config
import com.uchuhimo.konf.ConfigSpec
import com.uchuhimo.konf.Feature
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

object SecureConfigSpec : ConfigSpec("secure") {
    object Discord : ConfigSpec("discord") {
        val token by required<String>("token", "Discord bot token.")
    }
}

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class SecureConfig

@Module
class SettingsModule {
    @Provides
    @Singleton
    @SecureConfig
    fun provideSecureSettings() =
        Config { addSpec(SecureConfigSpec) }.enable(Feature.FAIL_ON_UNKNOWN_PATH).from.json.resource("secure.json")

}