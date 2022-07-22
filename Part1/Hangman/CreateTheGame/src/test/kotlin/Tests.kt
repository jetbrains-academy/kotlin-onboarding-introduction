import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        private val isCorrectInputMethod = TestMethod(
            "isCorrectInput", "Boolean", listOf(
                Variable("userInput", "String"),
            )
        )

        private val safeUserInputMethod = TestMethod("safeUserInput", "Char")

        private val generateNewUserWordMethod = TestMethod(
            "generateNewUserWord", "String", listOf(
                Variable("secret", "String"),
                Variable("guess", "Char"),
                Variable("currentUserWord", "String"),
            )
        )

        private val isCompleteMethod = TestMethod(
            "isComplete", "Boolean", listOf(
                Variable("secret", "String"),
                Variable("currentGuess", "String"),
            )
        )

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

        private val dashWithSeparator = "$dash$separator"
        private val emptyWordSecret = "$dashWithSeparator$dashWithSeparator$dashWithSeparator$dashWithSeparator"

        @JvmStatic
        fun userGuesses() = listOf(
            // secret, guess, currentUserWord, expected guess
            Arguments.of("BOOK", 'A', emptyWordSecret, null),
            Arguments.of("BOOK", 'A', "${dashWithSeparator}O${separator}O$separator${dashWithSeparator}", null),
            Arguments.of("BOOK", 'A', "$dashWithSeparator$dashWithSeparator${dashWithSeparator}K$separator", null),
            Arguments.of(
                "BOOK",
                'B',
                emptyWordSecret,
                "B$separator$dashWithSeparator$dashWithSeparator$dashWithSeparator"
            ),
            Arguments.of(
                "BOOK",
                'B',
                "${dashWithSeparator}O${separator}O$separator${dashWithSeparator}",
                "B${separator}O${separator}O$separator${dashWithSeparator}"
            ),
            Arguments.of("BOOK", 'K', "$dashWithSeparator$dashWithSeparator${dashWithSeparator}K$separator", null),
        )

        @JvmStatic
        fun gameStates() = listOf(
            // secret, currentGuess, isComplete
            Arguments.of("BOOK", emptyWordSecret, false),
            Arguments.of("BOOK", "${dashWithSeparator}O${separator}O$separator${dashWithSeparator}", false),
            Arguments.of("BOOK", "B${separator}O${separator}O${separator}K", true),
        )
    }

    @ParameterizedTest
    @MethodSource("functions")
    fun testFunctions(function: TestMethod) {
        function.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("userInputs")
    fun testIsCorrectInputImplementation(input: String, isCorrect: Boolean) {
        val userMethod = isCorrectInputMethod.getMethodFromClass()
        Assertions.assertEquals(
            isCorrect,
            userMethod.invokeWithArgs(input),
            "The function ${isCorrectInputMethod.name} with argument: input=$input should return $isCorrect"
        )
    }

    @ParameterizedTest
    @MethodSource("safeUserInputs")
    fun testSafeUserInputImplementation(
        safeUserInputs: List<String>,
        finalInput: Char
    ) {
        val userMethod = safeUserInputMethod.getMethodFromClass()
        setSystemIn(safeUserInputs)
        val actualInput = userMethod.invokeWithoutArgs()
        Assertions.assertEquals(
            finalInput,
            actualInput,
            "The function ${safeUserInputMethod.name} for the sequence of the user inputs: ${safeUserInputs.joinToString { ", " }} should return $actualInput"
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
        val userMethod = generateNewUserWordMethod.getMethodFromClass()
        val actualGuess = userMethod.invokeWithArgs(secret, guess, currentUserWord)
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
        val userMethod = isCompleteMethod.getMethodFromClass()
        Assertions.assertEquals(
            isComplete,
            userMethod.invokeWithArgs(secret, currentGuess),
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
        require(secrets.toSet().isNotEmpty()) { "Please, generate a random word from the <words> list." }
    }

    @Test
    fun testGameImitation() {
        imitateGameRound(true)
    }
}