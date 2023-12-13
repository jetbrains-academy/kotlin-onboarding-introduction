import org.jetbrains.academy.test.system.core.models.classes.TestClass

internal val mainClass = TestClass(
    classPackage = "jetbrains.kotlin.course.mastermind.advanced",
    customMethods = listOf(
        isCompleteMethod,
        countExactMatchesMethod,
        countPartialMatchesMethod,
        isWinMethod,
        isLostMethod,
        generateSecretMethod,
        getGameRulesMethod,
        printRoundResultsMethod,
        isCorrectInputMethod
    ),
)
