import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.Variable
import util.checkListOfVariables
import util.runMainFunction
import java.io.File

class Test {
    @Test
    fun testVariablesInSolution() {
        val taskDirectoryPath = System.getProperty("user.dir")
        val sourceCodeFile = File("$taskDirectoryPath/src/main/kotlin/Main.kt")
        checkListOfVariables(
            sourceCodeFile,
            listOf(
                Variable("wordLength", "Int", "4"),
                Variable("maxAttemptsCount", "Int", "3"),
                Variable("secretExample", "String", "\"ACEB\"")
            )
        )
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