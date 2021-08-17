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
    @SecureConfig
    fun provideSecureSettings() =
        Config { addSpec(SecureConfigSpec) }.enable(Feature.FAIL_ON_UNKNOWN_PATH).from.json.resource("secure.json")

}