package land.generic.kdorf.modules.kord

import com.uchuhimo.konf.Config
import dev.kord.core.Kord
import kotlinx.coroutines.GlobalScope
import land.generic.kdorf.modules.settings.DiscordConfigSpec
import land.generic.kdorf.modules.settings.DiscordConfig
import land.generic.kdorf.utility.SuspendLazy
import land.generic.kdorf.utility.SuspendLazySuspendingImpl
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class KordProvider @Inject constructor(
    @DiscordConfig private val secure: Config
) {
    private val inst: SuspendLazy<Kord> = SuspendLazySuspendingImpl(GlobalScope, EmptyCoroutineContext) {
        Kord(secure[DiscordConfigSpec.token])
    }

    suspend fun provideKord(): Kord = inst()
}