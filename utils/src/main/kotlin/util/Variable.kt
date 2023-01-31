package util

import java.io.File

data class Variable(
    val name: String,
    val type: String,
    val value: String? = null
)

fun Variable.variableDefModifier() = "val ${this.name}"

fun Variable.variableDefTemplateBase() = "${variableDefModifier()} = ${this.value}"

fun Variable.variableDefTemplateWithType() = "${variableDefModifier()}: ${this.type} = ${this.value}"

fun Variable.isVariableExist(fileContent: String): Boolean {
    val differentStylesWithEqual = listOf("=", " =", "= ", " = ")
    val baseDefs = differentStylesWithEqual.map { "${variableDefModifier()}$it${this.value}" }
    val defWithTypes = listOf(
        "${variableDefModifier()}:${this.type}",
        "${variableDefModifier()}: ${this.type}",
    ).map { defWithType ->
        differentStylesWithEqual.map{
            "$defWithType$it"
        }
    }.flatten()
    if (!(baseDefs + defWithTypes).any { it in fileContent }) {
        error("The code should contains a definition of the ${this.name} variable! " +
                "Please, add <${variableDefTemplateBase()}> or <${variableDefTemplateWithType()}> code in your solution." +
                "Please, be careful with styles - check the spaces around =.")
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
        throwInternalCourseError()
    }
}
