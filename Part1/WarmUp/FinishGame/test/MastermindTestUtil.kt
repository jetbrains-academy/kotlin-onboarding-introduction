import util.Util

internal const val SECRET = "ABCD"
internal const val WORD_LENGTH = 4
internal const val ALPHABET = "ABCDEFG"

data class GameStep(
    val attempt: String,
    val positions: Int,
    val letters: Int
) {
    companion object {
        private const val welcomeMessage = "Please, input your guess. It should be $WORD_LENGTH size."
    }

    fun imitateGameProcess() = "$welcomeMessage${Util.newLineSeparator}Your guess has $positions full match, and $letters partial match."
}

enum class UserInputCorrectness(val message: String, val isCorrect: Boolean) {
    CORRECT("", true),
    INCORRECT_LENGTH("The length of your guess should be $WORD_LENGTH! Try again!", false),
    INCORRECT_ALPHABET("All symbols in your guess should be from the alphabet: $ALPHABET! Try again!", false)
}