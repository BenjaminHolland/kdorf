package land.generic.kdorf.components

import dagger.BindsInstance
import dagger.Subcomponent
import land.generic.kdorf.DorfCommandContext
import land.generic.kdorf.modules.commands.CommandParser

@CommandScope
@Subcomponent(
    modules = []
)
interface DorfCommandComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: DorfCommandContext): Builder
        fun build(): DorfCommandComponent
    }

    fun buildParser(): CommandParser

}