import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        @JvmStatic
        fun inputsToCheck() = listOf(
            // guess, secret, expected result
            Arguments.of("ACEB", "BCDF", false),
            Arguments.of("ACEB", "ACEB", true),
        )

        @JvmStatic
        fun sequences() = listOf(
            // guess, secret, positions, letters
            Arguments.of("ACEB", "BCDF", 1, 1),
            Arguments.of("ABCD", "ABCD", 4, 0),
            Arguments.of("ABCD", "DCBA", 0, 4),
            Arguments.of("ABCD", "DBCA", 2, 2),
            Arguments.of("ABCD", "EBCF", 2, 0),

            Arguments.of("AAAA", "AAAA", 4, 0),
            Arguments.of("AAAA", "BBBB", 0, 0),
            Arguments.of("AABB", "BBAA", 0, 4),
            Arguments.of("ABCD", "ABBA", 2, 0),
            Arguments.of("ABBA", "ABCD", 2, 0),
            Arguments.of("AAAA", "ABBB", 1, 0),
            Arguments.of("BBBB", "BBDH", 2, 0),
            Arguments.of("AAAA", "ABCD", 1, 0),
        )

        @JvmStatic
        fun gameResults() = listOf(
            // complete, attempts, maxAttemptsCount, isWin, isLost
            Arguments.of(true, 3, 4, true, false),
            Arguments.of(true, 4, 4, true, false),
            Arguments.of(false, 4, 4, false, false),
            Arguments.of(false, 5, 4, false, true),
            Arguments.of(false, 3, 4, false, false),
        )

        @JvmStatic
        fun secretProperties() = listOf(
            // wordLength, alphabet
            Arguments.of(3, "ABCDE"),
            Arguments.of(5, "ABCDE"),
            Arguments.of(3, "ABCDEFG"),
            Arguments.of(5, "ABCDEFG"),
        )

        @JvmStatic
        fun userInputs() = listOf(
            // input, correctness
            Arguments.of("ABCD", UserInputCorrectness.CORRECT),
            Arguments.of("ABC", UserInputCorrectness.INCORRECT_LENGTH),
            Arguments.of("ABCCC", UserInputCorrectness.INCORRECT_LENGTH),
            Arguments.of("ABCI", UserInputCorrectness.INCORRECT_ALPHABET),
            Arguments.of("ACI", UserInputCorrectness.INCORRECT_LENGTH),
        )

        @JvmStatic
        fun safeUserInputs() = listOf(
            // systemIn, output, isSystemInEmpty
            Arguments.of("ABC\nABCCC\nABCI\nACI\nABCD\n", "ABCD", true),
            Arguments.of("ABCD\n", "ABCD", true),
            Arguments.of("ABCD\nABC\nABCCC\nABCI\nACI\n", "ABCD", false),
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @Test
    fun testIsCorrectInputFunction() {
        mainClass.checkMethod(mainClazz, isCorrectInputMethod)
    }

    @ParameterizedTest
    @MethodSource("userInputs")
    fun testIsCorrectInputImplementation(
        input: String,
        correctness: UserInputCorrectness,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isCorrectInputMethod)
        val isCorrect = userMethod.invokeWithArgs(input, WORD_LENGTH, ALPHABET, clazz = mainClazz)
        Assertions.assertEquals(
            correctness.isCorrect,
            isCorrect,
            "The function ${isCorrectInputMethod.name} with arguments: input=$input, wordLength=$WORD_LENGTH, alphabet=$ALPHABET should return ${correctness.message}"
        )
    }

    @Test
    fun testGenerateSecretMethodFunction() {
        mainClass.checkMethod(mainClazz, generateSecretMethod)
    }

    @ParameterizedTest
    @MethodSource("secretProperties")
    fun testGenerateSecretImplementation(
        wordLength: Int,
        alphabet: String,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, generateSecretMethod)
        val secret = userMethod.invokeWithArgs(wordLength, alphabet, clazz = mainClazz)
        assert(secret is String) { "The method ${generateSecretMethod.name} should return String" }
        require(secret is String)
        assert(secret.split(", ").size == 1) { "The current generated secret is: $secret, you forgot to change the separator" }
        val nonAlphabetSymbols = secret.filter { it !in alphabet }
        assert(nonAlphabetSymbols.isEmpty()) { "The method ${generateSecretMethod.name} for alphabet: $alphabet returns incorrect symbols $nonAlphabetSymbols" }
        assert(secret.length == wordLength) { "The method ${generateSecretMethod.name} for wordLength: $wordLength should return String with length $wordLength" }
    }

    @Test
    fun testPrintRoundResultsFunction() {
        mainClass.checkMethod(mainClazz, printRoundResultsMethod)
    }

    @Test
    fun testIsWinFunction() {
        mainClass.checkMethod(mainClazz, isWinMethod)
    }

    @ParameterizedTest
    @MethodSource("gameResults")
    fun testIsWinImplementation(
        complete: Boolean,
        attempts: Int,
        maxAttemptsCount: Int,
        isWin: Boolean,
        isLost: Boolean
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isWinMethod)
        Assertions.assertEquals(
            isWin, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount, clazz = mainClazz),
            "The function ${isWinMethod.name} must return $isWin for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount"
        )
    }

    @Test
    fun testIsLostFunction() {
        mainClass.checkMethod(mainClazz, isLostMethod)
    }

    @ParameterizedTest
    @MethodSource("gameResults")
    fun testIsLostImplementation(
        complete: Boolean,
        attempts: Int,
        maxAttemptsCount: Int,
        isWin: Boolean,
        isLost: Boolean
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isLostMethod)
        Assertions.assertEquals(
            isLost, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount, clazz = mainClazz),
            "The function ${isLostMethod.name} must return $isLost for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount"
        )
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountPartialMatchesImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = mainClass.findMethod(mainClazz, countPartialMatchesMethod)
        Assertions.assertEquals(
            expectedLettersMatchings, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the number of partial matches is $expectedLettersMatchings"
        )
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountExactMatchesImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = mainClass.findMethod(mainClazz, countExactMatchesMethod)
        Assertions.assertEquals(
            expectedPosMatchings, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the number of exact matches is $expectedPosMatchings"
        )
    }

    @ParameterizedTest
    @MethodSource("inputsToCheck")
    fun testIsCompleteImplementation(
        guess: String,
        secret: String,
        expectedResult: Boolean
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isCompleteMethod)
        Assertions.assertEquals(
            expectedResult, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the function ${isCompleteMethod.name} should return $expectedResult"
        )
    }

    @Test
    fun testIsCompleteFunction() {
        mainClass.checkMethod(mainClazz, isCompleteMethod)
    }

    @Test
    fun testGetGameRulesFunction() {
        val userMethod = mainClass.findMethod(mainClazz, getGameRulesMethod)
        val alphabet = "ABCDEHG"
        val methodRes = userMethod.invokeWithArgs(3, 4, "ABCD", alphabet, clazz = mainClazz)
        val basePhrase = "The possible symbols in the word:"
        assert("$basePhrase $alphabet" in methodRes.toString()) { "The phrase: $basePhrase <alphabet> should be returned by the ${getGameRulesMethod.name} function" }
    }

    @Test
    fun testGenerateSecretFunction() {
        mainClass.checkMethod(mainClazz, generateSecretMethod)
    }

    @Test
    fun testCountPartialMatchesFunction() {
        mainClass.checkMethod(mainClazz, countPartialMatchesMethod)
    }

    @Test
    fun testCountExactMatchesFunction() {
        mainClass.checkMethod(mainClazz, countExactMatchesMethod)
    }
}