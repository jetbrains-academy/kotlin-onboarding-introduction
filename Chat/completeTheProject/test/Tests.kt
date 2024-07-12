import jetbrains.kotlin.course.chat.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.Util.DEFAULT_USER_INPUT
import util.runMainFunction
import org.junit.jupiter.api.extension.ExtendWith
import util.HandleNotImplementedError
import util.HandleNotImplementedErrorExtension

@HandleNotImplementedError
@ExtendWith(HandleNotImplementedErrorExtension::class)
class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I'm glad to meet you, let me get to know you better! What is your name?${Util.newLineSeparator}" +
                    "Nice to meet you, $DEFAULT_USER_INPUT! My name is Kotlin Bot! I am a young programming " +
                    "language created in 2010. How old are you?${Util.newLineSeparator}" +
                    "$DEFAULT_USER_INPUT is great! I hope you successfully complete this course! " +
                    "Anyone can learn programming at any age!${Util.newLineSeparator}",
            runMainFunction(::main, "$DEFAULT_USER_INPUT${Util.newLineSeparator}$DEFAULT_USER_INPUT${Util.newLineSeparator}")
        )
    }
}