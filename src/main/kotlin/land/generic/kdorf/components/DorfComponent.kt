package land.generic.kdorf.components

import dagger.*
import land.generic.kdorf.DorfApp
import land.generic.kdorf.modules.firestore.FirestoreModule
import land.generic.kdorf.modules.handlers.MessageHandler
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DorfAppModule::class,
        FirestoreModule::class
    ]
)
interface DorfComponent {
    @Component.Builder
    interface Builder {
        fun build(): DorfComponent
    }

    fun buildDorfApp(): DorfApp

    @CommandScope
    fun commandScope(): DorfCommandComponent.Builder

    fun handlers():Map<String, MessageHandler>
}

