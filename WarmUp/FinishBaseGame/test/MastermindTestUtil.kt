import util.Util

internal const val SECRET = "ABCD"
internal const val WORD_LENGTH = 4

data class GameStep(
    val attempt: String,
    val positions: Int,
    val letters: Int
) {
    companion object {
        private const val welcomeMessage = "Please input your guess. It should be of length $WORD_LENGTH."
    }

    fun imitateGameProcess() = "$welcomeMessage${Util.newLineSeparator}Your guess has $positions full matches and $letters partial matches."
}

internal fun List<GameStep>.imitateGameProcess() = joinToString(Util.newLineSeparator) { it.imitateGameProcess() }

internal fun List<GameStep>.imitateUserInput() = joinToString(Util.newLineSeparator) { it.attempt }

enum class GameResult(val finalMessage: String) {
    WIN("Congratulations! You guessed it!"),
    LOSE("Sorry, you lost! :( My word is $SECRET")
}