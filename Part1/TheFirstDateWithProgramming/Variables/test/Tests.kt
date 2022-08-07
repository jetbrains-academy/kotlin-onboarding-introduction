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
                Variable("firstUserAnswer", "String", "\"\""),
                Variable("secondUserAnswer", "String", "\"\""),
                Variable("thirdUserAnswer", "String", "\"\"")
            )
        )
    }
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.\n" +
                    "Please answer all of them and be honest with me!\n",
            runMainFunction(::main)
        )
    }
}