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
    fun testCountPartialMatchesFunction() {
        TestMethod("countPartialMatches", "Int", listOf(
            Variable("secret", "String"),
            Variable("guess", "String"),
        )).getMethodFromClass()
    }

    @Test
    fun testCountExactMatchesFunction() {
        TestMethod("countExactMatches", "Int", listOf(
            Variable("secret", "String"),
            Variable("guess", "String"),
        )).getMethodFromClass()
    }

    @Test
    fun testSolution() {
        val wordLength = 4
        val secretExample = "ACEB"
        val maxAttemptsCount = 3
        val expectedOutput = "Welcome to the game!$newLineSymbol" +
                newLineSymbol +
                "Two people play this game: one chooses a word (a sequence of letters), " +
                "the other guesses it. In this version, the computer chooses the word: " +
                "a sequence of $wordLength letters (for example, $secretExample). " +
                "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
                "For each attempt, the number of complete matches (letter and position) " +
                "and partial matches (letter only) is reported.$newLineSymbol" +
                newLineSymbol +
                "For example, with $secretExample as the hidden word, the BCDF guess will " +
                "give 1 full match (C) and 1 partial match (B).$newLineSymbol"
        Assertions.assertEquals(expectedOutput, trimOutput(runMainFunction(::main)))
    }

    private fun trimOutput(output: String) = output.lines().joinToString(newLineSymbol) { it.trim() }
}