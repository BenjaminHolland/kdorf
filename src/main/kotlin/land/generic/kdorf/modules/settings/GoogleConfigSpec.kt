package land.generic.kdorf.modules.settings

import com.uchuhimo.konf.ConfigSpec

object DiscordConfigSpec : ConfigSpec("secure") {
    object Discord : ConfigSpec("discord") {
        val token by required<String>("token", "Discord bot token.")
    }
}