import jetbrains.kotlin.course.warmup.main
import jetbrains.kotlin.course.warmup.newLineSymbol
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.checkListOfVariables
import util.runMainFunction
import java.io.File

class Test {
    @Test
    fun testVariablesInSolution() {
        val taskDirectoryPath = System.getProperty("user.dir")
        val sourceCodeFile = File("$taskDirectoryPath/src/main/kotlin/jetbrains/kotlin/course/warmup/Main.kt")
        checkListOfVariables(
            sourceCodeFile,
            listOf(
                TestVariable("wordLength", "Int", "4"),
                TestVariable("maxAttemptsCount", "Int", "3"),
                TestVariable("secretExample", "String", "\"ACEB\"")
            )
        )
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