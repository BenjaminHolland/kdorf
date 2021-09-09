package land.generic.kdorf.modules.settings

import com.uchuhimo.konf.ConfigSpec

object GoogleConfigSpec : ConfigSpec("secure") {
    object Google : ConfigSpec("discord") {
        val account by required<String>("account", "Service Account")
        val token by required<String>("token","Account Token")
    }
}