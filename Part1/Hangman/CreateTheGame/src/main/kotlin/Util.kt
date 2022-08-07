fun safeReadLine() = readLine() ?: error("Your input is incorrect, sorry")

val separator = " "
val dash = "_"

val wordLength = 4
val maxAttemptsCount = wordLength * 2