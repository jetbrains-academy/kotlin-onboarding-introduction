import org.junit.jupiter.params.provider.Arguments
import util.TestMethod
import util.Variable

internal val generateNewUserWordMethod = TestMethod(
    "generateNewUserWord", "String", listOf(
        Variable("secret", "String"),
        Variable("guess", "Char"),
        Variable("currentUserWord", "String"),
    )
)

internal val isCompleteMethod = TestMethod(
    "isComplete", "Boolean", listOf(
        Variable("secret", "String"),
        Variable("currentGuess", "String"),
    )
)

internal val underscoreWithSeparator = "$underscore$separator"

internal val emptyWordSecret = "$underscoreWithSeparator$underscoreWithSeparator$underscoreWithSeparator$underscore"

internal fun userGuessesData() = listOf(
    // secret, guess, currentUserWord, expected guess
    Arguments.of("BOOK", 'A', emptyWordSecret, null),
    Arguments.of("BOOK", 'A', "${underscoreWithSeparator}O${separator}O$separator$underscore", null),
    Arguments.of("BOOK", 'A', "$underscoreWithSeparator$underscoreWithSeparator${underscoreWithSeparator}K", null),
    Arguments.of(
        "BOOK",
        'B',
        emptyWordSecret,
        "B$separator$underscoreWithSeparator$underscoreWithSeparator$underscore"
    ),
    Arguments.of(
        "BOOK",
        'B',
        "${underscoreWithSeparator}O${separator}O$separator${underscoreWithSeparator}",
        "B${separator}O${separator}O$separator$underscore"
    ),
    Arguments.of("BOOK", 'K', "$underscoreWithSeparator$underscoreWithSeparator${underscoreWithSeparator}K", null),
)
