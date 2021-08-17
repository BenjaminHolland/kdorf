package land.generic.kdorf.components

import dagger.BindsInstance
import dagger.Subcomponent
import land.generic.kdorf.CommandContext
import land.generic.kdorf.modules.commands.CommandParser

@CommandScope
@Subcomponent(
    modules = []
)
interface DorfCommandComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: CommandContext): Builder
        fun build(): DorfCommandComponent
    }

    fun buildParser(): CommandParser

}