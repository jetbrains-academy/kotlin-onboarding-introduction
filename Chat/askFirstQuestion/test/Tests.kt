import jetbrains.kotlin.course.chat.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.Util.DEFAULT_USER_INPUT
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I'm glad to meet you, let me get to know you better! What is your name?${Util.newLineSeparator}",
            runMainFunction(::main, "$DEFAULT_USER_INPUT${Util.newLineSeparator}")
        )
    }
}