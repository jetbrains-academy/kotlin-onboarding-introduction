import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util.DEFAULT_USER_INPUT
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I'm glad to meet you, let me get to know you better! What is your name?\n" +
                    "Nice to meet you, $DEFAULT_USER_INPUT! My name is Kotlin Bot! I am a young programming " +
                    "language and was created in 2010. How old are you?\n" +
                    "$DEFAULT_USER_INPUT is great! I hope you successfully complete this course! " +
                    "Anyone can learn programming at any age!\n",
            runMainFunction(::main, "$DEFAULT_USER_INPUT\n$DEFAULT_USER_INPUT\n")
        )
    }
}