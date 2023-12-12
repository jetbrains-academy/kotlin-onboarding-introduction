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
            "Hello! I'm glad to meet you, let me get to know you better! What is your name?${Util.newLineSeparator}" +
                    "Nice to meet you, $DEFAULT_USER_INPUT! My name is Kotlin Bot! I am a young programming " +
                    "language created in 2010. How old are you?${Util.newLineSeparator}",
            runMainFunction(::main, "$DEFAULT_USER_INPUT${Util.newLineSeparator}$DEFAULT_USER_INPUT${Util.newLineSeparator}")
        )
    }
}