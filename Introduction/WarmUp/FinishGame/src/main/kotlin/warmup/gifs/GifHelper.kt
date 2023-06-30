package warmup.gifs

import getGameRules
import playGame

fun runGame() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val alphabet = "ABCDEFGH"
    println(getGameRules(wordLength, maxAttemptsCount, secretExample, alphabet))
    playGame("ABCD", wordLength, maxAttemptsCount, alphabet)
}

fun main() {
    runGame()
}