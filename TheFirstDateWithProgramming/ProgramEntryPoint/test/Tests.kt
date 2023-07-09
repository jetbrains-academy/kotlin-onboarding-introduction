import jetbrains.kotlin.course.first.date.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals("Hello!${Util.newLineSeparator}", runMainFunction(::main))
    }
}