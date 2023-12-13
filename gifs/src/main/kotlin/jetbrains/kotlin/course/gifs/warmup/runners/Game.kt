package jetbrains.kotlin.course.gifs.warmup.runners

import jetbrains.kotlin.course.mastermind.advanced.getGameRules
import jetbrains.kotlin.course.mastermind.advanced.playGame

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val alphabet = "ABCDEFGH"
    println(getGameRules(wordLength, maxAttemptsCount, secretExample).replace(". ", ".\n"))
    playGame("BBDH", wordLength, maxAttemptsCount, alphabet)
}
