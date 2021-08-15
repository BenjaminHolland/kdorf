package land.generic.kdorf.utility

object Resources {
    fun load(path: String) = Thread.currentThread().contextClassLoader.getResource(path)
    fun open(path: String) = Thread.currentThread().contextClassLoader.getResourceAsStream(path)
}