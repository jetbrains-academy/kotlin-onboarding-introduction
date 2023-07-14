import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val isCompleteMethod = TestMethod(
    "isComplete",
    TestKotlinType("Boolean"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    ),
)

internal val countExactMatchesMethod = TestMethod(
    "countExactMatches",
    TestKotlinType("Int"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    ),
)

internal val countPartialMatchesMethod = TestMethod(
    "countPartialMatches",
    TestKotlinType("Int"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    ),
)

internal val isWinMethod = TestMethod(
    "isWin",
    TestKotlinType("Boolean"),
    listOf(
        TestVariable("complete", "Boolean"),
        TestVariable("attempts", "Int"),
        TestVariable("maxAttemptsCount", "Int"),
    ),
)

internal val isLostMethod = TestMethod(
    "isLoss",
    TestKotlinType("Boolean"),
    listOf(
        TestVariable("complete", "Boolean"),
        TestVariable("attempts", "Int"),
        TestVariable("maxAttemptsCount", "Int"),
    ),
)

internal val generateSecretMethod = TestMethod(
    "generateSecret",
    TestKotlinType("String"),
    listOf(
        TestVariable("wordLength", "Int"),
        TestVariable("alphabet", "String"),
    ),
)

internal val isCorrectInputMethod = TestMethod(
    "isCorrectInput",
    TestKotlinType("Boolean"),
    listOf(
        TestVariable("userInput", "String"),
        TestVariable("wordLength", "Int"),
        TestVariable("alphabet", "String"),
    ),
)

internal val safeUserInputMethod = TestMethod(
    "safeUserInput",
    TestKotlinType("String"),
    listOf(
        TestVariable("wordLength", "Int"),
        TestVariable("alphabet", "String"),
    ),
)

internal val getGameRulesMethod = TestMethod(
    "getGameRules",
    TestKotlinType("String"),
    listOf(
        TestVariable("wordLength", "Int"),
        TestVariable("maxAttemptsCount", "Int"),
        TestVariable("secretExample", "String"),
        TestVariable("alphabet", "String"),
    ),
)

internal val printRoundResultsMethod = TestMethod(
    "printRoundResults",
    TestKotlinType("Unit"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    ),
    "Void",
)
