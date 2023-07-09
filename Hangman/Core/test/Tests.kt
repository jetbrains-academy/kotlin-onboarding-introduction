import jetbrains.kotlin.course.hangman.separator
import org.jetbrains.academy.test.system.invokeWithArgs
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        @JvmStatic
        fun functions() = listOf(
            Arguments.of(generateNewUserWordMethod),
            Arguments.of(isCompleteMethod),
        )

        @JvmStatic
        fun userGuesses() = userGuessesData()

        const val PACKAGE_NAME = "hangman"
    }

    @ParameterizedTest
    @MethodSource("functions")
    fun testFunctions(function: TestMethod) {
        function.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("userGuesses")
    fun testGenerateNewUserWordImplementation(
        secret: String,
        guess: Char,
        currentUserWord: String,
        expectedGuess: String?
    ) {
        val userMethod = generateNewUserWordMethod.getMethodFromClass(PACKAGE_NAME)
        val actualGuess = (userMethod.invokeWithArgs(secret, guess, currentUserWord, clazz = findClassSafe(PACKAGE_NAME)) as String).dropLastWhile { it.toString() == separator }
        val expected = expectedGuess ?: currentUserWord
        Assertions.assertEquals(
            expected,
            actualGuess,
            "The function ${generateNewUserWordMethod.name} with arguments: secret=$secret, guess=$guess, and currentUserWord=$currentUserWord should return $expected"
        )
    }
}