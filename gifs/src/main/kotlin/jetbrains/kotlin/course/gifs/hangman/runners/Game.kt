package jetbrains.kotlin.course.gifs.hangman.runners

import jetbrains.kotlin.course.hangman.getGameRules
import jetbrains.kotlin.course.hangman.maxAttemptsCount
import jetbrains.kotlin.course.hangman.playGame
import jetbrains.kotlin.course.hangman.wordLength

fun main() {
    println(getGameRules(wordLength, maxAttemptsCount))
    playGame("TREE", maxAttemptsCount)
}
