import org.jetbrains.academy.test.system.models.TestKotlinType
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.variable.TestVariable
import org.junit.jupiter.params.provider.Arguments

internal val generateNewUserWordMethod = TestMethod(
    "generateNewUserWord", TestKotlinType("String"), listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "Char"),
        TestVariable("currentUserWord", "String"),
    )
)

internal val isCompleteMethod = TestMethod(
    "isComplete", TestKotlinType("Boolean"), listOf(
        TestVariable("secret", "String"),
        TestVariable("currentGuess", "String"),
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
