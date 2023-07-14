import jetbrains.kotlin.course.hangman.separator
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        @JvmStatic
        fun functions() = listOf(
            Arguments.of(isCorrectInputMethod),
            Arguments.of(safeUserInputMethod),
            Arguments.of(generateNewUserWordMethod),
            Arguments.of(isCompleteMethod),
        )

        @JvmStatic
        fun userInputs() = listOf(
            // input, isCorrect
            Arguments.of("ABCD", false),
            Arguments.of("abc", false),
            Arguments.of("1", false),
            Arguments.of("1a", false),
            Arguments.of("", false),
        )

        @JvmStatic
        fun safeUserInputs() = listOf(
            // inputs, final input
            Arguments.of(listOf("AB", "abc", "1", "b"), 'B'),
            Arguments.of(listOf("b"), 'B'),
            Arguments.of(listOf("AB", "abc", "1", "b", "c"), 'B'),
            Arguments.of(listOf("AB", "abc", "1", "b", "c1d"), 'B'),
            Arguments.of(listOf("b", "c"), 'B'),
        )

        @JvmStatic
        fun userGuesses() = userGuessesData()

        @JvmStatic
        fun gameStates() = listOf(
            // secret, currentGuess, isComplete
            Arguments.of("BOOK", emptyWordSecret, false),
            Arguments.of("BOOK", "${underscoreWithSeparator}O${separator}O$separator${underscoreWithSeparator}", false),
            Arguments.of("BOOK", "B${separator}O${separator}O${separator}K", true),
        )

        private val isCorrectInputMethod = TestMethod(
            "isCorrectInput",
            TestKotlinType("Boolean"),
            listOf(TestVariable("userInput", "String")),
        )

        private val safeUserInputMethod = TestMethod("safeUserInput", TestKotlinType("Char"))

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.hangman",
            customMethods = listOf(
                isCorrectInputMethod,
                safeUserInputMethod,
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
    @MethodSource("userInputs")
    fun testIsCorrectInputImplementation(input: String, isCorrect: Boolean) {
        val userMethod = mainClass.findMethod(mainClazz, isCorrectInputMethod)
        Assertions.assertEquals(
            isCorrect,
            userMethod.invokeWithArgs(input, clazz = mainClazz),
            "The function ${isCorrectInputMethod.name} with argument: input=$input should return $isCorrect"
        )
    }

    @ParameterizedTest
    @MethodSource("safeUserInputs")
    fun testSafeUserInputImplementation(
        safeUserInputs: List<String>,
        finalInput: Char
    ) {
        val userMethod = mainClass.findMethod(mainClazz, safeUserInputMethod)
        setSystemIn(safeUserInputs)
        val actualInput = userMethod.invokeWithoutArgs(mainClazz)
        Assertions.assertEquals(
            finalInput,
            actualInput,
            "The function ${safeUserInputMethod.name} for the sequence of the user inputs: ${safeUserInputs.joinToString(", ")} should return $actualInput"
        )
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
    @MethodSource("gameStates")
    fun testIsCompleteImplementation(
        secret: String,
        currentGuess: String,
        isComplete: Boolean,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isCompleteMethod)
        Assertions.assertEquals(
            isComplete,
            userMethod.invokeWithArgs(secret, currentGuess, clazz = mainClazz),
            "The function ${isCompleteMethod.name} with arguments: secret=$secret, and currentGuess=$currentGuess should return $isComplete"
        )
    }

    @Test
    fun testSolution() {
        val secrets = mutableListOf<String>()
        repeat(1000) {
            val secret = imitateGameRound()
            secret?.let { secrets.add(it) }
        }
        assert(secrets.toSet().size > 1) { "Please, generate a random word from the <words> list." }
    }

    @Test
    fun testGameImitation() {
        imitateGameRound(true)
    }
}