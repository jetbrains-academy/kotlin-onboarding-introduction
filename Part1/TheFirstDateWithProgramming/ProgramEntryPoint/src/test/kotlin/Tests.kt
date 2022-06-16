import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals("Hello!\n", runMainFunction(::main))
    }
}