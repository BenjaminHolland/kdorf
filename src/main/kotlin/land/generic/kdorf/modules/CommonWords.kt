package land.generic.kdorf.modules

import land.generic.kdorf.utility.Resources
import land.generic.kdorf.utility.Trie
import java.io.StringReader
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CommonWords @Inject constructor() {
    val lookup by lazy {
        Trie().also { trie ->
            Resources.open("common.txt")?.bufferedReader()?.lines()?.forEach {
                trie.insert(it)
            }
        }
    }
}