package util

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

object Util {
    const val DEFAULT_USER_INPUT = "<some user's answer>"
}

fun setSystemIn(input: String? = null) = input?.let { System.setIn(it.byteInputStream()) }

fun setSystemOut(): ByteArrayOutputStream {
    val baos = ByteArrayOutputStream()
    val ps = PrintStream(baos, true, StandardCharsets.UTF_8.name())
    System.setOut(ps)
    return baos
}

fun runMainFunction(mainFunction: () -> Unit, input: String? = null): String {
    setSystemIn(input)
    val baos = setSystemOut()
    mainFunction()
    assert(isSystemInEmpty()) { "You are asking the user to enter data fewer times than required in the task!" }
    return baos.toString()
}

fun isSystemInEmpty() = String(System.`in`.readAllBytes()).isEmpty()