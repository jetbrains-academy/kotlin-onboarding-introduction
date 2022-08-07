import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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