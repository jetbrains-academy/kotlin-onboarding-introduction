import jetbrains.kotlin.course.first.date.main
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import util.HandleNotImplementedError
import util.HandleNotImplementedErrorExtension
import util.Util
import util.runMainFunction

@HandleNotImplementedError
@ExtendWith(HandleNotImplementedErrorExtension::class)
class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals("Hello!${Util.newLineSeparator}", runMainFunction(::main))
    }
}