package util

import org.jetbrains.academy.test.system.findMethod
import org.jetbrains.academy.test.system.models.method.TestMethod
import java.lang.reflect.Method

fun TestMethod.getMethodFromClass(className: String = "MainKt"): Method {
    val clazz = findClassSafe(className)
    val methods = clazz.methods
    return methods.findMethod(this)
}

fun findClassSafe(className: String  = "MainKt") = Class.forName(className) ?: throwInternalCourseError()
