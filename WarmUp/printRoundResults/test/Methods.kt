import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val isCompleteMethod = TestMethod(
    "isComplete", TestKotlinType("Boolean"), listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    )
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

internal val countExactMatchesMethod = TestMethod(
    "countExactMatches", TestKotlinType("Int"),
    listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    )
)

internal val countPartialMatchesMethod = TestMethod(
    "countPartialMatches", TestKotlinType("Int"), listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    )
)

internal val countAllMatches = TestMethod(
    "countAllMatches", TestKotlinType("Int"), listOf(
        TestVariable("secret", "String"),
        TestVariable("guess", "String"),
    )
)

internal val generateSecretMethod = TestMethod("generateSecret", TestKotlinType("String"), emptyList())

internal val getGameRulesMethod = TestMethod(
    "getGameRules",
    TestKotlinType("String"),
    listOf(
        TestVariable("wordLength", "Int"),
        TestVariable("maxAttemptsCount", "Int"),
        TestVariable("secretExample", "String"),
    ),
)
