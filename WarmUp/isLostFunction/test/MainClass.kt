import org.jetbrains.academy.test.system.core.models.classes.TestClass

internal val mainClass = TestClass(
    classPackage = "jetbrains.kotlin.course.warmup",
    customMethods = listOf(
        isCompleteMethod,
        countExactMatchesMethod,
        countPartialMatchesMethod,
        generateSecretMethod,
        getGameRulesMethod,
        countAllMatches,
        printRoundResultsMethod,
        isWinMethod,
        isLostMethod
    ),
)
