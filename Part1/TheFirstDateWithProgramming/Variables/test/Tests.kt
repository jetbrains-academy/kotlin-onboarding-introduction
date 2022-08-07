import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.runMainFunction
import java.io.File

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.\n" +
                    "Please answer all of them and be honest with me!\n",
            runMainFunction(::main)
        )
    }

    @Test
    fun testVariablesInSolution() {
        val taskDirectoryPath = System.getProperty("user.dir")
        val sourceCodeFile = File("$taskDirectoryPath/src/main/kotlin/Main.kt")
        if (sourceCodeFile.exists()) {
            val variables = listOf("firstUserAnswer", "secondUserAnswer", "thirdUserAnswer")
            val content = sourceCodeFile.readText()
            for (variable in variables) {
                val baseDef = variableDefTemplateBase(variable)
                val defWithType = variableDefTemplateWithType(variable)
                if (!(baseDef in content || defWithType in content)) {
                    error("The code should contains a definition of the $variable variable! " +
                            "Please, add <$baseDef> or <$defWithType> code in your solution.")
                }
            }
        } else {
            // TODO: log some errors?
            error("Internal course error!")
        }
    }

    private fun variableDefTemplateBase(variableName: String) = "val $variableName = \"\""

    private fun variableDefTemplateWithType(variableName: String) = "val $variableName: String = \"\""
}