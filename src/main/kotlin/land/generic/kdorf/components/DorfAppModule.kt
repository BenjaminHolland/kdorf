package land.generic.kdorf.components

import dagger.Module
import dagger.Provides
import land.generic.kdorf.modules.settings.SettingsModule
import javax.inject.Singleton
import kotlin.random.Random

@Module(
    subcomponents = [DorfCommandComponent::class],
    includes = [SettingsModule::class, HandlerModule::class]
)
class DorfAppModule {
    @Provides
    @Singleton
    fun provideChaos(): Random = Random
}