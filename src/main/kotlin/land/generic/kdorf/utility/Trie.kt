package land.generic.kdorf.utility

import kotlin.random.Random

class Trie {

    data class Node(var word: String? = null, val childNodes: MutableMap<Char, Node> = mutableMapOf())

    private val root = Node()

    fun insert(word: String) {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                currentNode.childNodes[char] = Node()
            }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.word = word
    }


    fun random(chaos: Random): String {
        var currentNode = root
        while (currentNode.childNodes.isNotEmpty()) {
            currentNode = currentNode.childNodes.values.random(chaos)
        }
        return currentNode.word!!
    }

    fun search(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode.word != null
    }

    fun startsWith(word: String): Boolean {
        var currentNode = root
        for (char in word) {
            if (currentNode.childNodes[char] == null) {
                return false
            }
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode.word == null
    }

}