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
    fun testCountGenerateSecretFunction() {
        val m = TestMethod("generateSecret", "String", emptyList())
        val userMethod = m.getMethodFromClass()
        val methodRes = userMethod.invokeWithoutArgs()
        val expectedResult = "ABCD"
        Assertions.assertEquals(expectedResult, methodRes) { "The method ${m.name} should always return $expectedResult" }
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
    fun testSolution() {
        val wordLength = 4
        val secretExample = "ACEB"
        val maxAttemptsCount = 3
        val expectedOutput = "Welcome to the game! $newLineSymbol" +
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
        Assertions.assertEquals(expectedOutput, runMainFunction(::main))
    }
}