package jetbrains.kotlin.course.gifs.warmup.runners

import jetbrains.kotlin.course.warmup.getGameRules
import jetbrains.kotlin.course.warmup.playGame

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val alphabet = "ABCDEFGH"
    println(getGameRules(wordLength, maxAttemptsCount, secretExample, alphabet).replace(". ", ".\n"))
    playGame("BBDH", wordLength, maxAttemptsCount, alphabet)
}
