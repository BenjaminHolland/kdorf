import land.generic.kdorf.components.DaggerDorfComponent
import land.generic.kdorf.parsing.*
import java.lang.Exception

suspend fun main(args: Array<String>) {
    val app = DaggerDorfComponent.builder().build().buildDorfApp()
    app.execute()
}