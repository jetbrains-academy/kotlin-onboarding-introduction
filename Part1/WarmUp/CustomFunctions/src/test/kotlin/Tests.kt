import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.*

class Test {
    @Test
    fun testGetGameRulesFunction() {
        TestMethod("getGameRules", "String", listOf(
            Variable("wordLength", "Int"),
            Variable("maxAttemptsCount", "Int"),
            Variable("secretExample", "String"),
        )).getMethodFromClass()
    }

    @Test
    fun testCountLettersMatchingsFunction() {
        TestMethod("countLettersMatchings", "Int", listOf(
            Variable("secret", "String"),
            Variable("guess", "String"),
        )).getMethodFromClass()
    }

    @Test
    fun testCountPositionalMatchingsFunction() {
        TestMethod("countPositionalMatchings", "Int", listOf(
            Variable("secret", "String"),
            Variable("guess", "String"),
        )).getMethodFromClass()
    }

    @Test
    fun testCountGenerateSecretFunction() {
        val m = TestMethod("generateSecret", "String", emptyList())
        val userMethod = m.getMethodFromClass()
        val methodRes = userMethod.invokeWithoutArgs()
        val expectedResult = "ABCD"
        Assertions.assertEquals(expectedResult, methodRes) { "The method ${m.name} should always return $expectedResult" }
    }

    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Welcome to the game! \n" +
                    "\n" +
                    "Two people play this game, one guesses a word (a sequence of letters), the other guesses it. In this case, the computer guesses the word. A sequence of 4 letters is guessed (for example, ACEB). Several attempts are given to guess it (max number is 3). For each attempt, the number of complete matches (letter and position) and partial (letter only) is reported. \n" +
                    "\n" +
                    "For example, for a BCDF guess (with ACEB guessed) there will be 1 full match (C), 1 partial match (B).\n",
            runMainFunction(::main)
        )
    }
}