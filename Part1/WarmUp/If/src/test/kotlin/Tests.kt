import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*
import util.Util.DEFAULT_USER_INPUT

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
    fun testCountLettersMatchingsFunction() {
        TestMethod(
            "countLettersMatchings", "Int", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        ).getMethodFromClass()
    }

    @Test
    fun testCountPositionalMatchingsFunction() {
        TestMethod(
            "countPositionalMatchings", "Int", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
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
    fun testIsCompleteFunction() {
        isCompleteMethod.getMethodFromClass()
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
    fun testPlayGameFunction() {
        TestMethod(
            "playGame", "Unit", listOf(
                Variable("secret", "String"),
                Variable("wordLength", "Int"),
                Variable("maxAttemptsCount", "Int"),
            ),
            "Void"
        )
    }

    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Welcome to the game! \n" +
                    "\n" +
                    "Two people play this game, one guesses a word (a sequence of letters), the other guesses it. In this case, the computer guesses the word. A sequence of 4 letters is guessed (for example, ACEB). Several attempts are given to guess it (max number is 3). For each attempt, the number of complete matches (letter and position) and partial (letter only) is reported. \n" +
                    "\n" +
                    "For example, for a BCDF guess (with ACEB guessed) there will be 1 full match (C), 1 partial match (B).\n" +
                    "Please, input your guess. It should be 4 size.\n",
            runMainFunction(::main, "ABCD")
        )
    }
}