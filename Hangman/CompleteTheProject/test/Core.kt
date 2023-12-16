import jetbrains.kotlin.course.hangman.separator
import jetbrains.kotlin.course.hangman.underscore
import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.junit.jupiter.params.provider.Arguments

internal val generateNewUserWordMethod = TestMethod(
    "generateNewUserWord",
    TestKotlinType("String"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "Char"),
        TestVariable("currentUserWord", "String"),
    ),
)

internal val isCorrectInputMethod = TestMethod(
    "isCorrectInput",
    TestKotlinType("Boolean"),
    listOf(TestVariable("userInput", "String")),
)

internal val getRoundResultsMethod = TestMethod(
    "getRoundResults",
    TestKotlinType("String"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "Char"),
        TestVariable("currentUserWord", "String")
    ),
)

internal val playGameMethod = TestMethod(
    "playGame",
    TestKotlinType("unit"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("maxAttemptsCount", "Int"),
    ),
    returnTypeJava = "void",
)

internal val safeUserInputMethod = TestMethod("safeUserInput", TestKotlinType("Char"))

internal val isCompleteMethod = TestMethod(
    "isComplete",
    TestKotlinType("Boolean"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("currentGuess", "String"),
    ),
)

internal val generateSecretMethod = TestMethod(
    "generateSecret",
    TestKotlinType("String"),
)

internal val getHiddenSecret = TestMethod(
    "getHiddenSecret",
    TestKotlinType("String"),
    listOf(
        TestVariable("wordLength", "Int"),
    ),
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
