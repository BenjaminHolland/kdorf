package land.generic.kdorf.components

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import land.generic.kdorf.DorfCommandContext
import land.generic.kdorf.modules.CommonWords
import land.generic.kdorf.modules.commands.DorfCommandHandler
import land.generic.kdorf.modules.handlers.AutoResponseHandler
import land.generic.kdorf.modules.handlers.MessageHandler
import land.generic.kdorf.modules.handlers.TiktokHandler
import javax.inject.Provider
import kotlin.random.Random

@Module
class HandlerModule {
    @Provides
    @IntoMap
    @StringKey("command")
    fun provideCommandHandler(commandContextProvider: Provider<DorfCommandComponent.Builder>): MessageHandler =
        DorfCommandHandler(commandContextProvider)

    @Provides
    @IntoMap
    @StringKey("autoresponse")
    fun provideAutoResponseHandler(commonWords: CommonWords, chaos: Random): MessageHandler =
        AutoResponseHandler(commonWords, chaos)


    @Provides
    @IntoMap
    @StringKey("tiktok")
    fun provideTikTokHandler(): MessageHandler =
        TiktokHandler()

}