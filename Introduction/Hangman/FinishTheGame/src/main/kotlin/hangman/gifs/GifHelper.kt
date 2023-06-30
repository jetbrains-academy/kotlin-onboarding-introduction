package hangman.gifs

import getGameRules
import maxAttemptsCount
import playGame
import wordLength

fun runGame() {
    println(getGameRules(wordLength, maxAttemptsCount))
    playGame("ABCD", wordLength, maxAttemptsCount)
}
