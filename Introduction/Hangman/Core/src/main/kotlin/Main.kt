fun deleteSeparator(guess: String) = guess.replace(separator, "")

fun isComplete(secret: String, currentGuess: String) = secret == deleteSeparator(currentGuess)

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    var newUserWord = ""
    for (i in secret.indices) {
        newUserWord += if (secret[i] == guess) {
            "${secret[i]}$separator"
        } else {
            "${currentUserWord[i * 2]}$separator"
        }
    }
    // Just newUserWord will be ok for the tests
    return newUserWord.removeSuffix(separator)
}

fun main() {
    // Write your code here
}
