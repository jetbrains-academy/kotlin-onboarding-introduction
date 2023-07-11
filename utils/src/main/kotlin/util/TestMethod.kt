package util

import org.jetbrains.academy.test.system.findMethod
import org.jetbrains.academy.test.system.models.method.TestMethod
import java.lang.reflect.Method

fun TestMethod.getMethodFromClass(
    packageName: String,
    packagePrefix: String = "jetbrains.kotlin.course",
    className: String = "MainKt",
): Method {
    val clazz = findClassSafe(packageName, packagePrefix, className)
    val methods = clazz.methods
    return methods.findMethod(this)
}

fun findClassSafe(
    packageName: String,
    packagePrefix: String = "jetbrains.kotlin.course",
    className: String = "MainKt"
) = Class.forName("${packagePrefix.removeSuffix(".")}.${packageName.removeSuffix(".")}.$className")
    ?: throwInternalCourseError()
