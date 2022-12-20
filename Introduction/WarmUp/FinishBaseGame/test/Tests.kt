import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        @JvmStatic
        fun gameProcessImitation() = listOf(
            // secret, attempts, game result
            Arguments.of(
                SECRET, listOf(
                    GameStep("BCDF", 0, 3),
                    GameStep("ABCD", 4, 0)
                ), GameResult.WIN
            ),
            Arguments.of(
                SECRET, listOf(
                    GameStep("BCDF", 0, 3),
                    GameStep("ABDC", 2, 2),
                    GameStep("ABCD", 4, 0)
                ), GameResult.WIN
            ),
            Arguments.of(
                SECRET, listOf(
                    GameStep("BCDF", 0, 3),
                    GameStep("BCDF", 0, 3),
                    GameStep("BCDF", 0, 3),
                    GameStep("BCDF", 0, 3),
                ), GameResult.LOSE
            ),
        )

        @JvmStatic
        fun inputsToCheck() = listOf(
            // guess, secret, expected result
            Arguments.of("ACEB", "BCDF", false),
            Arguments.of("ACEB", "ACEB", true),
        )

        private val isCompleteMethod = TestMethod(
            "isComplete", "Boolean", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        )
        @JvmStatic
        fun sequences() = listOf(
            // guess, secret, positions, letters
            Arguments.of("ACEB", "BCDF", 1, 1),
            Arguments.of("ABCD", "ABCD", 4, 0),
            Arguments.of("ABCD", "DCBA", 0, 4),
            Arguments.of("ABCD", "DBCA", 2, 2),
            Arguments.of("ABCD", "EBCF", 2, 0),

            Arguments.of("AAAA", "AAAA", 4, 0),
            Arguments.of("AAAA", "BBBB", 0, 0),
            Arguments.of("AABB", "BBAA", 0, 4),
            Arguments.of("ABCD", "ABBA", 2, 0),
            Arguments.of("ABBA", "ABCD", 2, 0),
            Arguments.of("AAAA", "ABBB", 1, 0),
            Arguments.of("BBBB", "BBDH", 2, 0),
            Arguments.of("AAAA", "ABCD", 1, 0),
        )

        private val countExactMatchesMethod = TestMethod(
            "countExactMatches", "Int",
            listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        )

        private val countPartialMatchesMethod = TestMethod(
            "countPartialMatches", "Int", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        )


        @JvmStatic
        fun gameResults() = listOf(
            // complete, attempts, maxAttemptsCount, isWin, isLost
            Arguments.of(true, 3, 4, true, false),
            Arguments.of(true, 4, 4, true, false),
            Arguments.of(false, 4, 4, false, false),
            Arguments.of(false, 5, 4, false, true),
            Arguments.of(false, 3, 4, false, false),
        )

        private val isWinMethod = TestMethod(
            "isWin", "Boolean", listOf(
                Variable("complete", "Boolean"),
                Variable("attempts", "Int"),
                Variable("maxAttemptsCount", "Int"),
            )
        )

        private val isLostMethod = TestMethod(
            "isLost", "Boolean", listOf(
                Variable("complete", "Boolean"),
                Variable("attempts", "Int"),
                Variable("maxAttemptsCount", "Int"),
            )
        )
    }

    @Test
    fun testPrintRoundResultsFunction() {
        TestMethod(
            "printRoundResults", "Unit", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            ),
            "Void"
        ).getMethodFromClass()
    }

    @Test
    fun testIsWinFunction() {
        isWinMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("gameResults")
    fun testIsWinImplementation(
        complete: Boolean,
        attempts: Int,
        maxAttemptsCount: Int,
        isWin: Boolean,
        isLost: Boolean
    ){
        val userMethod = isWinMethod.getMethodFromClass()
        Assertions.assertEquals(isWin, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount),
            "The function ${isWinMethod.name} must return $isWin for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount")
    }

    @Test
    fun testIsLostFunction() {
        isLostMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("gameResults")
    fun testIsLostImplementation(
        complete: Boolean,
        attempts: Int,
        maxAttemptsCount: Int,
        isWin: Boolean,
        isLost: Boolean
    ){
        val userMethod = isLostMethod.getMethodFromClass()
        Assertions.assertEquals(isLost, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount),
            "The function ${isLostMethod.name} must return $isLost for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount")
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountPartialMatchesImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = countPartialMatchesMethod.getMethodFromClass()
        Assertions.assertEquals(expectedLettersMatchings, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the number of partial matches is $expectedPosMatchings")
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountExactMatchesImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = countExactMatchesMethod.getMethodFromClass()
        Assertions.assertEquals(expectedPosMatchings, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the number of exact matches is $expectedPosMatchings")
    }

    @ParameterizedTest
    @MethodSource("inputsToCheck")
    fun testIsCompleteImplementation(
        guess: String,
        secret: String,
        expectedResult: Boolean
    ){
        val userMethod = isCompleteMethod.getMethodFromClass()
        Assertions.assertEquals(expectedResult, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the function ${isCompleteMethod.name} should return $expectedResult")
    }

    @Test
    fun testIsCompleteFunction() {
        isCompleteMethod.getMethodFromClass()
    }

    @Test
    fun testGetGameRulesFunction() {
        TestMethod(
            "getGameRules", "String", listOf(
                Variable("wordLength", "Int"),
                Variable("maxAttemptsCount", "Int"),
                Variable("secretExample", "String"),
            )
        ).getMethodFromClass()
    }

    @Test
    fun testCountGenerateSecretFunction() {
        val m = TestMethod("generateSecret", "String", emptyList())
        val userMethod = m.getMethodFromClass()
        val methodRes = userMethod.invokeWithoutArgs()
        val expectedResult = "ABCD"
        Assertions.assertEquals(
            expectedResult,
            methodRes
        ) { "The method ${m.name} should always return $expectedResult" }
    }

    @Test
    fun testCountPartialMatchesFunction() {
        countPartialMatchesMethod.getMethodFromClass()
    }

    @Test
    fun testCountExactMatchesFunction() {
        countExactMatchesMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("gameProcessImitation")
    fun testSolution(
        secret: String,
        attempts: List<GameStep>,
        gameResult: GameResult
    ) {
        val wordLength = 4
        val secretExample = "ACEB"
        val maxAttemptsCount = 3
        val initialText = "Welcome to the game! $newLineSymbol" +
                newLineSymbol +
                "Two people play this game, one chooses a word (a sequence of letters), " +
                "the other guesses it. In this case, the computer chooses the word: " +
                "a sequence of $wordLength letters (for example, $secretExample). " +
                "Several attempts are given to guess it (the max number is $maxAttemptsCount). " +
                "For each attempt, the number of complete matches (letter and position) " +
                "and partial matches (letter only) is reported. $newLineSymbol" +
                newLineSymbol +
                "For example, for the BCDF guess (with $secretExample as the hidden word), " +
                "there will be 1 full match (C), 1 partial match (B).$newLineSymbol"
        val expectedOutput = "$initialText${attempts.imitateGameProcess()}${Util.newLineSeparator}${gameResult.finalMessage}${Util.newLineSeparator}"

        Assertions.assertEquals(expectedOutput, runMainFunction(::main, "${attempts.imitateUserInput()}${Util.newLineSeparator}"))
    }
}