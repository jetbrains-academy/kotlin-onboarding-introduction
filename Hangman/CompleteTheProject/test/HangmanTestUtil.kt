import jetbrains.kotlin.course.hangman.main
import jetbrains.kotlin.course.hangman.separator
import jetbrains.kotlin.course.hangman.wordLength
import org.junit.jupiter.api.Assertions
import util.Util.newLineSeparator
import util.runMainFunction

private val ALPHABET_RANGE = 'A'..'Z'
private val ALPHABET = ALPHABET_RANGE.toList()

private const val LOSS_MESSAGE = "Sorry, you lost! My word is "
private const val VICTORY_MESSAGE = "Congratulations! You guessed it!"

private fun generateGameInput() = "${ALPHABET.shuffled().joinToString(newLineSeparator)}$newLineSeparator"

internal fun imitateGameRound(toCheckResult: Boolean = false): String? {
    val gameInput = generateGameInput()
    val output = runMainFunction(::main, gameInput, false)
    if (toCheckResult) {
        Assertions.assertTrue(VICTORY_MESSAGE in output || LOSS_MESSAGE in output) { "Please, inform the user about the results of the game. If the user lost: \"$LOSS_MESSAGE<secret>\". If the user guessed: \"$VICTORY_MESSAGE\"" }
    }
    return if (LOSS_MESSAGE in output) {
        val index = Regex(LOSS_MESSAGE).find(output)?.range?.endInclusive
        index?.let {
            Assertions.assertTrue(output.length > index + wordLength) { "You should print the secret to the console in the loss message!" }
            output.substring(index..index + wordLength).replace(separator, "")
        }
            ?: error("The user lost, but you showed an incorrect loss message. The correct message is: \"$LOSS_MESSAGE<secret>\"")
    } else if (VICTORY_MESSAGE in output) {
        val index = Regex(VICTORY_MESSAGE).find(output)?.range?.start
            ?: error("The user guessed, but you showed an incorrect victory message. The correct message is: \"$VICTORY_MESSAGE\"")
        val startSecretIndex = index - (wordLength * 2 + newLineSeparator.length)
        output.substring(startSecretIndex until startSecretIndex + wordLength * 2).replace(separator, "")
    } else {
        null
    }
}