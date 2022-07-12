package util

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

object Util {
    const val DEFAULT_USER_INPUT = "<some user's answer>"
}

fun runMainFunction(mainFunction: () -> Unit, input: String? = null): String {
    input?.let { System.setIn(it.byteInputStream()) }
    val baos = ByteArrayOutputStream()
    val ps = PrintStream(baos, true, StandardCharsets.UTF_8.name())
    System.setOut(ps)
    mainFunction()
    assert(isSystemInEmpty()) { "You are asking the user to enter data fewer times than required in the task!" }
    return baos.toString()
}

private fun isSystemInEmpty() = String(System.`in`.readAllBytes()).isEmpty()