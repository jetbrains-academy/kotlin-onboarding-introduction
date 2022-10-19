package util

import org.junit.jupiter.api.Assertions
import util.Util.newLineSeparator
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

object Util {
    const val DEFAULT_USER_INPUT = "<some user's answer>"

    val newLineSeparator: String = System.lineSeparator()
}

fun setSystemIn(input: List<String>? = null) = setSystemIn(input?.joinToString(newLineSeparator))

fun setSystemIn(input: String? = null) = input?.let {
    System.setIn(it.replaceLineSeparator().byteInputStream())
}

fun String.replaceLineSeparator() = this.lines().joinToString(newLineSeparator)

fun setSystemOut(): ByteArrayOutputStream {
    val baos = ByteArrayOutputStream()
    val ps = PrintStream(baos, true, StandardCharsets.UTF_8.name())
    System.setOut(ps)
    return baos
}

fun runMainFunction(mainFunction: () -> Unit, input: String? = null, toAssertSystemIn: Boolean = true): String {
    setSystemIn(input)
    val baos = setSystemOut()
    mainFunction()
    if (toAssertSystemIn) {
        assert(isSystemInEmpty()) { "You are asking the user to enter data fewer times than required in the task!" }
    }
    return baos.toString("UTF-8").replaceLineSeparator()
}

fun isSystemInEmpty() = String(System.`in`.readBytes()).isEmpty()

fun throwInternalCourseError(): Nothing = error("Internal course error!")

fun checkReadLineFunctions(
    testMethod: TestMethod,
    input: String,
    isSystemInEmpty: Boolean,
    output: String
) {
    val userMethod = testMethod.getMethodFromClass()
    setSystemIn(input)
    val result = userMethod.invokeWithoutArgs()
    val errorPostfix = if (!isSystemInEmpty) "not" else ""
    Assertions.assertEquals(
        isSystemInEmpty, isSystemInEmpty(),
        "For the user's input: $input the function ${testMethod.name} should read $errorPostfix " +
                "all inputs before returning the result."
    )
    Assertions.assertEquals(
        output, result, "For the user's input: $input the " +
                "function ${testMethod.name} should return $output"
    )
}