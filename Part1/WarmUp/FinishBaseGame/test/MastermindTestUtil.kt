import util.Util

internal const val SECRET = "ABCD"
internal const val WORD_LENGTH = 4

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

internal fun List<GameStep>.imitateGameProcess() = joinToString(Util.newLineSeparator) { it.imitateGameProcess() }

internal fun List<GameStep>.imitateUserInput() = joinToString(Util.newLineSeparator) { it.attempt }

enum class GameResult(val finalMessage: String) {
    WIN("Congratulations! You guessed!"),
    LOSE("Sorry, you lost! :( My word is $SECRET")
}