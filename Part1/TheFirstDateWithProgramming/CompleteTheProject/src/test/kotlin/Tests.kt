import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.runMainFunction

class Test {
    @Test
    fun testSolution() {
        val firstInput = "1"
        val secondInput = "2"
        val thirdInput = "3"
        Assertions.assertEquals(
            "Hello! I will ask you several questions.\n" +
                    "Please answer all of them and be honest with me!\n" +
                    "What is TROTEN?\n" +
                    "How did you spend your graduation?\n" +
                    "Why does a spider need eight legs?\n" +
                    "Now let's have fun!\n" +
                    "$FIRST_QUESTION\n$firstInput\n" +
                    "$SECOND_QUESTION\n$secondInput\n" +
                    "$THIRD_QUESTION\n$thirdInput\n",
            runMainFunction(::main, "$firstInput\n$secondInput\n$thirdInput\n")
        )
    }
}