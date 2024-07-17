import jetbrains.kotlin.course.first.date.main
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.checkListOfVariables
import util.runMainFunction
import java.io.File
import org.junit.jupiter.api.extension.ExtendWith
import util.HandleNotImplementedError
import util.HandleNotImplementedErrorExtension

@HandleNotImplementedError
@ExtendWith(HandleNotImplementedErrorExtension::class)
class Test {
    @Test
    fun testVariablesInSolution() {
        val taskDirectoryPath = System.getProperty("user.dir")
        val sourceCodeFile = File("$taskDirectoryPath/src/main/kotlin/jetbrains/kotlin/course/first/date/Main.kt")
        checkListOfVariables(
            sourceCodeFile,
            listOf(
                TestVariable("firstUserAnswer", "String", "\"\""),
                TestVariable("secondUserAnswer", "String", "\"\""),
                TestVariable("thirdUserAnswer", "String", "\"\"")
            )
        )
    }
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.${Util.newLineSeparator}" +
                    "Please answer all of them and be honest with me!${Util.newLineSeparator}",
            runMainFunction(::main)
        )
    }
}