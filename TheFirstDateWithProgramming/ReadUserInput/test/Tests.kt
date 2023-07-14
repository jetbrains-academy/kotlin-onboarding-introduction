import jetbrains.kotlin.course.first.date.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.Util.DEFAULT_USER_INPUT
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.${Util.newLineSeparator}" +
                    "Please answer all of them and be honest with me!${Util.newLineSeparator}" +
                    "What is TROTEN?${Util.newLineSeparator}" +
                    "How did you spend your graduation?${Util.newLineSeparator}" +
                    "Why does a spider need eight legs?${Util.newLineSeparator}",
            runMainFunction(::main, "$DEFAULT_USER_INPUT${Util.newLineSeparator}$DEFAULT_USER_INPUT${Util.newLineSeparator}$DEFAULT_USER_INPUT${Util.newLineSeparator}")
        )
    }
}