import jetbrains.kotlin.course.hangman.separator
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
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
        fun isCompleteData() = listOf(
            // secret, guess, isComplete
            Arguments.of("ABC", "A${separator}B${separator}C", true),
            Arguments.of("ABC", "A${separator}B${separator}B", false),
            Arguments.of("ABC", "A${separator}A${separator}A", false),
        )

        @JvmStatic
        fun userGuesses() = userGuessesData()

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.hangman",
            customMethods = listOf(
                generateNewUserWordMethod,
                isCompleteMethod,
            )
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @ParameterizedTest
    @MethodSource("functions")
    fun testFunctions(function: TestMethod) {
        mainClass.checkMethod(mainClazz, function)
    }

    @ParameterizedTest
    @MethodSource("userGuesses")
    fun testGenerateNewUserWordImplementation(
        secret: String,
        guess: Char,
        currentUserWord: String,
        expectedGuess: String?
    ) {
        val userMethod = mainClass.findMethod(mainClazz, generateNewUserWordMethod)
        val actualGuess = (userMethod.invokeWithArgs(secret, guess, currentUserWord, clazz = mainClazz) as String).dropLastWhile { it.toString() == separator }
        val expected = expectedGuess ?: currentUserWord
        Assertions.assertEquals(
            expected,
            actualGuess,
            "The function ${generateNewUserWordMethod.name} with arguments: secret=$secret, guess=$guess, and currentUserWord=$currentUserWord should return $expected"
        )
    }

    @ParameterizedTest
    @MethodSource("isCompleteData")
    fun testIsCompleteMethodImplementation(
        secret: String,
        guess: String,
        isComplete: Boolean
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isCompleteMethod)
        val actualResult = (userMethod.invokeWithArgs(secret, guess, clazz = mainClazz) as Boolean)
        Assertions.assertEquals(isComplete, actualResult) { "The function ${isCompleteMethod.name} with arguments secret=$secret, guess=$guess should return $isComplete, but the current result is $actualResult" }
    }
}