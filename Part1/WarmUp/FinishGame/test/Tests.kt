import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
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

        private val isCompleteMethod = TestMethod(
            "isComplete", "Boolean", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
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
            Arguments.of("ABBA", "ABCD", 2, 2),
            Arguments.of("AAAA", "ABBB", 1, 3)
        )

        private val countPositionalMatchingsMethod = TestMethod(
            "countPositionalMatchings", "Int",
            listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
        )

        private val countLettersMatchingsMethod = TestMethod(
            "countLettersMatchings", "Int", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            )
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

        private val isWinMethod = TestMethod(
            "isWin", "Boolean", listOf(
                Variable("complete", "Boolean"),
                Variable("attempts", "Int"),
                Variable("maxAttemptsCount", "Int"),
            )
        )

        private val isLostMethod = TestMethod(
            "isLost", "Boolean", listOf(
                Variable("complete", "Boolean"),
                Variable("attempts", "Int"),
                Variable("maxAttemptsCount", "Int"),
            )
        )

        private val generateSecretMethod = TestMethod(
            "generateSecret", "String", listOf(
                Variable("wordLength", "Int"),
                Variable("alphabet", "String"),
            )
        )

        @JvmStatic
        fun secretProperties() = listOf(
            // wordLength, alphabet
            Arguments.of(3, "ABCDE"),
            Arguments.of(5, "ABCDE"),
            Arguments.of(3, "ABCDEFG"),
            Arguments.of(5, "ABCDEFG"),
        )

        private val isCorrectInputMethod = TestMethod(
            "isCorrectInput", "Boolean", listOf(
                Variable("userInput", "String"),
                Variable("wordLength", "Int"),
                Variable("alphabet", "String"),
            )
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

        private val safeUserInputMethod = TestMethod(
            "safeUserInput", "String", listOf(
                Variable("wordLength", "Int"),
                Variable("alphabet", "String"),
            )
        )

        @JvmStatic
        fun safeUserInputs() = listOf(
            // systemIn, output, isSystemInEmpty
            Arguments.of("ABC\nABCCC\nABCI\nACI\nABCD\n", "ABCD", true),
            Arguments.of("ABCD\n", "ABCD", true),
            Arguments.of("ABCD\nABC\nABCCC\nABCI\nACI\n", "ABCD", false),
        )
    }

    @Test
    fun testIsCorrectInputFunction() {
        isCorrectInputMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("userInputs")
    fun testIsCorrectInputImplementation(
        input: String,
        correctness: UserInputCorrectness,
    ) {
        val userMethod = isCorrectInputMethod.getMethodFromClass()
        val isCorrect = userMethod.invokeWithArgs(input, WORD_LENGTH, ALPHABET)
        Assertions.assertEquals(correctness.isCorrect, isCorrect, "The function ${isCorrectInputMethod.name} with arguments: input=$input, wordLength=$WORD_LENGTH, alphabet=$ALPHABET should return ${correctness.isCorrect}")
    }

    @Test
    fun testSafeUserInputFunction() {
        safeUserInputMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("safeUserInputs")
    fun testSafeUserInputImplementation(
        systemIn: String,
        output: String,
        isSystemInEmpty: Boolean
    ) {
        setSystemIn(systemIn)
        val userMethod = safeUserInputMethod.getMethodFromClass()
        val userInput = userMethod.invokeWithArgs(WORD_LENGTH, ALPHABET)
        val errorMessage = "The function ${safeUserInputMethod.name} is work incorrect! Please, be sure, you check the user's input."
        Assertions.assertEquals(isSystemInEmpty, isSystemInEmpty(), errorMessage)
        Assertions.assertEquals(userInput, output, errorMessage)
    }

    @Test
    fun testIsWinFunction() {
        isWinMethod.getMethodFromClass()
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
        val userMethod = isWinMethod.getMethodFromClass()
        Assertions.assertEquals(
            isWin, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount),
            "The function ${isWinMethod.name} must return $isWin for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount"
        )
    }

    @Test
    fun testIsLostFunction() {
        isLostMethod.getMethodFromClass()
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
        val userMethod = isLostMethod.getMethodFromClass()
        Assertions.assertEquals(
            isLost, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount),
            "The function ${isLostMethod.name} must return $isLost for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount"
        )
    }

    @Test
    fun testPrintRoundResultsFunction() {
        TestMethod(
            "printRoundResults", "Unit", listOf(
                Variable("secret", "String"),
                Variable("guess", "String"),
            ),
            "Void"
        ).getMethodFromClass()
    }

    @Test
    fun testIsCompleteFunction() {
        isCompleteMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("inputsToCheck")
    fun testIsCompleteImplementation(
        guess: String,
        secret: String,
        expectedResult: Boolean
    ) {
        val userMethod = isCompleteMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedResult, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the function ${isCompleteMethod.name} should return $expectedResult"
        )
    }

    @Test
    fun testGetGameRulesFunction() {
        val m = TestMethod(
            "getGameRules", "String", listOf(
                Variable("wordLength", "Int"),
                Variable("maxAttemptsCount", "Int"),
                Variable("secretExample", "String"),
                Variable("alphabet", "String"),
            )
        )
        val userMethod = m.getMethodFromClass()
        val alphabet = "ABCDEHG"
        val methodRes = userMethod.invokeWithArgs(3, 4, "ABCD", alphabet)
        val basePhrase = "The possible symbols in the word:"
        require("$basePhrase $alphabet" in methodRes.toString()) { "The phrase: $basePhrase <alphabet> should be returned by the ${m.name} function" }
    }

    @Test
    fun testCountLettersMatchingsFunction() {
        countLettersMatchingsMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountLettersMatchingsImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = countLettersMatchingsMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedLettersMatchings, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the number of positional matchings is $expectedPosMatchings"
        )
    }

    @Test
    fun testCountPositionalMatchingsFunction() {
        countPositionalMatchingsMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountPositionalMatchingsImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int
    ) {
        val userMethod = countPositionalMatchingsMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPosMatchings, userMethod.invokeWithArgs(secret, guess),
            "For secret: $secret and guess: $guess the number of positional matchings is $expectedPosMatchings"
        )
    }

    @Test
    fun testGenerateSecretFunction() {
        generateSecretMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("secretProperties")
    fun testGenerateSecretImplementation(
        wordLength: Int,
        alphabet: String,
    ) {
        val userMethod = generateSecretMethod.getMethodFromClass()
        val secret = userMethod.invokeWithArgs(wordLength, alphabet)
        require(secret is String) { "The method ${generateSecretMethod.name} should return String" }
        val nonAlphabetSymbols = secret.filter { it !in alphabet }
        require(nonAlphabetSymbols.isEmpty()) { "The method ${generateSecretMethod.name} for alphabet: $alphabet returns incorrect symbols $nonAlphabetSymbols"  }
        require(secret.length == wordLength) { "The method ${generateSecretMethod.name} for wordLength: $wordLength should return String with length $wordLength" }
    }
}