package util

import java.lang.IllegalArgumentException
import java.lang.reflect.Method

data class TestMethod(
    val name: String,
    val returnType: String,
    val arguments: List<Variable> = emptyList(),
    val returnTypeJava: String? = null,
) {
    fun prettyString(withToDo: Boolean = true): String {
        val args = arguments.joinToString(", ") { it.prettyString() }
        val body = if (withToDo) {
            "TODO(\"Not implemented yet\")"
        } else {
            "// Some code"
        }
        return "fun $name($args): $returnType = $body"
    }

    private fun Variable.prettyString() = "$name: $type"
}

private fun String.shortName() = this.split(".").last()

private fun List<Method>.filterByCondition(errorMessage: String, condition: (Method) -> Boolean): List<Method> {
    val filteredByCondition = this.filter { condition(it) }
    if (filteredByCondition.isEmpty()) {
        error(errorMessage)
    }
    return filteredByCondition
}

fun Array<Method>.findMethod(method: TestMethod): Method {
    val filteredByName =
        this.toList().filterByCondition("The method ${method.prettyString()} is missed") { it.name == method.name }
    val returnTypeJava = method.returnTypeJava ?: method.returnType
    val filteredByType =
        filteredByName.filterByCondition("The method ${method.name} should have the return type ${method.returnType}") {
            it.returnType.name.shortName().lowercase() == returnTypeJava.lowercase()
        }
    val filteredByArgumentsCount =
        filteredByType.filterByCondition("The method ${method.name} should have ${method.arguments.size} arguments") { it.parameterCount == method.arguments.size }
    assert(filteredByArgumentsCount.size == 1) { "The method ${method.prettyString()} is missed" }
    val m = filteredByArgumentsCount.first()
    val params = m.parameterTypes.map { it.name.shortName().lowercase() }
    val args = method.arguments.map { it.type.lowercase() }
    assert(params == args) { "The method ${method.name} should have ${method.arguments.size} arguments: $params. The full signature is: ${method.prettyString()}." }
    return m
}

fun TestMethod.getMethodFromClass(className: String = "MainKt"): Method {
    val clazz = findClassSafe(className)
    val methods = clazz.methods
    return methods.findMethod(this)
}

fun findClassSafe(className: String) = Class.forName(className) ?: throwInternalCourseError()

fun Method.invokeWithoutArgs(className: String = "MainKt"): Any = invokeWithArgs(className = className)

fun Method.invokeWithArgs(vararg args: Any, className: String = "MainKt"): Any {
    try {
        val clazz = findClassSafe(className)
        return invoke(clazz, *args)
    } catch (e: IllegalArgumentException) {
        assert(false) { "The function ${this.name} has wrong number or order of arguments" }
        throw e
    }
}