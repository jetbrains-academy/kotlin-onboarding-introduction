import org.jetbrains.academy.test.system.models.TestKotlinType
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.jetbrains.academy.test.system.invokeWithArgs
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
            "isComplete", TestKotlinType("Boolean"), listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
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
            Arguments.of("ABBA", "ABCD", 2, 0),
            Arguments.of("AAAA", "ABBB", 1, 0),
            Arguments.of("BBBB", "BBDH", 2, 0),
            Arguments.of("AAAA", "ABCD", 1, 0),
        )

        private val countExactMatchesMethod = TestMethod(
            "countExactMatches", TestKotlinType("Int"),
            listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
            )
        )

        private val countPartialMatchesMethod = TestMethod(
            "countPartialMatches", TestKotlinType("Int"), listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
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
            "isWin", TestKotlinType("Boolean"), listOf(
                TestVariable("complete", "Boolean"),
                TestVariable("attempts", "Int"),
                TestVariable("maxAttemptsCount", "Int"),
            )
        )

        private val isLostMethod = TestMethod(
            "isLoss", TestKotlinType("Boolean"), listOf(
                TestVariable("complete", "Boolean"),
                TestVariable("attempts", "Int"),
                TestVariable("maxAttemptsCount", "Int"),
            )
        )

        private val generateSecretMethod = TestMethod(
            "generateSecret", TestKotlinType("String"), listOf(
                TestVariable("wordLength", "Int"),
                TestVariable("alphabet", "String"),
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

        const val PACKAGE_NAME = "warmup"
    }

    @ParameterizedTest
    @MethodSource("secretProperties")
    fun testGenerateSecretImplementation(
        wordLength: Int,
        alphabet: String,
    ) {
        val userMethod = generateSecretMethod.getMethodFromClass(PACKAGE_NAME)
        val secret = userMethod.invokeWithArgs(wordLength, alphabet, clazz = findClassSafe(PACKAGE_NAME))
        assert(secret is String) { "The method ${generateSecretMethod.name} should return String" }
        require(secret is String)
        assert(secret.split(", ").size == 1) { "The current generated secret is: $secret, you forgot to change the separator" }
        val nonAlphabetSymbols = secret.filter { it !in alphabet }
        assert(nonAlphabetSymbols.isEmpty()) { "The method ${generateSecretMethod.name} for alphabet: $alphabet returns incorrect symbols $nonAlphabetSymbols"  }
        assert(secret.length == wordLength) { "The method ${generateSecretMethod.name} for wordLength: $wordLength should return String with length $wordLength" }
    }

    @Test
    fun testPrintRoundResultsFunction() {
        TestMethod(
            "printRoundResults", TestKotlinType("Unit"), listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
            ),
            "Void"
        ).getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testIsWinFunction() {
        isWinMethod.getMethodFromClass(PACKAGE_NAME)
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
        val userMethod = isWinMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            isWin, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount, clazz = findClassSafe(PACKAGE_NAME)),
            "The function ${isWinMethod.name} must return $isWin for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount"
        )
    }

    @Test
    fun testIsLostFunction() {
        isLostMethod.getMethodFromClass(PACKAGE_NAME)
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
        val userMethod = isLostMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            isLost, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount, clazz = findClassSafe(PACKAGE_NAME)),
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
        val userMethod = countPartialMatchesMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            expectedLettersMatchings, userMethod.invokeWithArgs(secret, guess, clazz = findClassSafe(PACKAGE_NAME)),
            "For secret: $secret and guess: $guess the number of partial matches is $expectedPosMatchings"
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
        val userMethod = countExactMatchesMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            expectedPosMatchings, userMethod.invokeWithArgs(secret, guess, clazz = findClassSafe(PACKAGE_NAME)),
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
        val userMethod = isCompleteMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            expectedResult, userMethod.invokeWithArgs(secret, guess, clazz = findClassSafe(PACKAGE_NAME)),
            "For secret: $secret and guess: $guess the function ${isCompleteMethod.name} should return $expectedResult"
        )
    }

    @Test
    fun testIsCompleteFunction() {
        isCompleteMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testGetGameRulesFunction() {
        val m = TestMethod(
            "getGameRules", TestKotlinType("String"), listOf(
                TestVariable("wordLength", "Int"),
                TestVariable("maxAttemptsCount", "Int"),
                TestVariable("secretExample", "String"),
                TestVariable("alphabet", "String"),
            )
        )
        val userMethod = m.getMethodFromClass(PACKAGE_NAME)
        val alphabet = "ABCDEHG"
        val methodRes = userMethod.invokeWithArgs(3, 4, "ABCD", alphabet, clazz = findClassSafe(PACKAGE_NAME))
        val basePhrase = "The possible symbols in the word:"
        assert("$basePhrase $alphabet" in methodRes.toString()) { "The phrase: $basePhrase <alphabet> should be returned by the ${m.name} function" }
    }

    @Test
    fun testGenerateSecretFunction() {
        generateSecretMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testCountPartialMatchesFunction() {
        countPartialMatchesMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testCountExactMatchesFunction() {
        countExactMatchesMethod.getMethodFromClass(PACKAGE_NAME)
    }
}