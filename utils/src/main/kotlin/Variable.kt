package util

import java.io.File

data class Variable(
    val name: String,
    val type: String,
    val value: String? = null
)

fun Variable.variableDefTemplateBase() = "val ${this.name} = ${this.value}"

fun Variable.variableDefTemplateWithType() = "val ${this.name}: ${this.type} = ${this.value}"

fun Variable.isVariableExist(fileContent: String): Boolean {
    val baseDef = variableDefTemplateBase()
    val defWithType = variableDefTemplateWithType()
    if (!(baseDef in fileContent || defWithType in fileContent)) {
        error(
            "The code should contains a definition of the ${this.name} variable! " +
                    "Please, add <$baseDef> or <$defWithType> code in your solution."
        )
    }
    return true
}

fun checkListOfVariables(sourceCodeFile: File, variables: List<Variable>) {
    if (sourceCodeFile.exists()) {
        val content = sourceCodeFile.readText()
        for (variable in variables) {
            assert(variable.isVariableExist(content))
        }
    } else {
        // TODO: log some errors?
        throwInternalCourseError()
    }
}