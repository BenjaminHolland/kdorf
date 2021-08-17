package land.generic.kdorf.modules.chatting

import com.uchuhimo.konf.ConfigSpec

object ChatterConfigSpec : ConfigSpec("chatter") {
    val vocab by required<Set<String>>("vocab", "Known words.")
}



