import jetbrains.kotlin.course.first.date.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.${Util.newLineSeparator}" +
                    "Please answer all of them and be honest with me!${Util.newLineSeparator}",
            runMainFunction(::main)
        )
    }
}