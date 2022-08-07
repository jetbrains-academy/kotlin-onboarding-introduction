import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Util.DEFAULT_USER_INPUT
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        Assertions.assertEquals(
            "Hello! I will ask you several questions.\n" +
                    "Please answer all of them and be honest with me!\n" +
                    "What is TROTEN?\n" +
                    "How did you spend your graduation?\n" +
                    "Why does a spider need eight legs?\n" +
                    "Now let's have fun!\n" +
                    "$firstQuestion\n$DEFAULT_USER_INPUT\n" +
                    "$secondQuestion\n$DEFAULT_USER_INPUT\n" +
                    "$thirdQuestion\n$DEFAULT_USER_INPUT\n",
            runMainFunction(::main, "$DEFAULT_USER_INPUT\n$DEFAULT_USER_INPUT\n$DEFAULT_USER_INPUT\n")
        )
    }
}