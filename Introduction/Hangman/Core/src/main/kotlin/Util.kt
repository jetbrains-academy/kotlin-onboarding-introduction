@file:Suppress("MayBeConst")

fun safeReadLine() = readlnOrNull() ?: error("Your input is incorrect, sorry")

val separator = " "
val underscore = "_"

val wordLength = 4
val maxAttemptsCount = wordLength * 2

val newLineSymbol: String = System.lineSeparator()
