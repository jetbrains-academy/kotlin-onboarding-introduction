import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
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

        private val countPositionalMatchingsMethod = TestMethod(
            "countPositionalMatchings", "Int",
            listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        )

        private val countLettersMatchingsMethod = TestMethod(
            "countLettersMatchings", "Int", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        )
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountLettersMatchingsImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = countLettersMatchingsMethod.getMethodFromClass()
        Assertions.assertEquals(expectedLettersMatchings, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the number of letters matchings is $expectedLettersMatchings")
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountPositionalMatchingsImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = countPositionalMatchingsMethod.getMethodFromClass()
        Assertions.assertEquals(expectedPosMatchings, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the number of positional matchings is $expectedPosMatchings")
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
    fun testCountLettersMatchingsFunction() {
        countLettersMatchingsMethod.getMethodFromClass()
    }

    @Test
    fun testCountPositionalMatchingsFunction() {
        countPositionalMatchingsMethod.getMethodFromClass()
    }

    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Welcome to the game! ${Util.newLineSeparator}" +
                    Util.newLineSeparator +
                    "Two people play this game, one guesses a word (a sequence of letters), the other guesses it. In this case, the computer guesses the word. A sequence of 4 letters is guessed (for example, ACEB). Several attempts are given to guess it (max number is 3). For each attempt, the number of complete matches (letter and position) and partial (letter only) is reported. ${Util.newLineSeparator}" +
                    Util.newLineSeparator +
                    "For example, for a BCDF guess (with ACEB guessed) there will be 1 full match (C), 1 partial match (B).${Util.newLineSeparator}" +
                    "Please, input your guess. It should be 4 size.${Util.newLineSeparator}",
            runMainFunction(::main, "ABCD")
        )
    }
}