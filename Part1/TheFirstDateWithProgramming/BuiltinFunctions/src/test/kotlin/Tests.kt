import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.\n" +
                    "Please answer all of them and be honest with me!\n",
            runMainFunction(::main)
        )
    }
}