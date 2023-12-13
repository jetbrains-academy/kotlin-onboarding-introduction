import jetbrains.kotlin.course.warmup.main
import jetbrains.kotlin.course.warmup.newLineSymbol
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*
import java.lang.reflect.InvocationTargetException

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
            // guess, secret, positions, letters, all
            Arguments.of("ACEB", "BCDF", 1, 1, 2),
            Arguments.of("ABCD", "ABCD", 4, 0, 4),
            Arguments.of("ABCD", "DCBA", 0, 4, 4),
            Arguments.of("ABCD", "DBCA", 2, 2, 4),
            Arguments.of("ABCD", "EBCF", 2, 0, 2),

            Arguments.of("AAAA", "AAAA", 4, 0, 4),
            Arguments.of("AAAA", "BBBB", 0, 0, 0),
            Arguments.of("AABB", "BBAA", 0, 4, 4),
            Arguments.of("ABCD", "ABBA", 2, 0, 2),
            Arguments.of("ABBA", "ABCD", 2, 0, 2),
            Arguments.of("AAAA", "ABBB", 1, 0, 1),
            Arguments.of("BBBB", "BBDH", 2, 0, 2),
            Arguments.of("AAAA", "ABCD", 1, 0, 1),
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
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
        Assertions.assertEquals(expectedLettersMatchings, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the number of letters matchings is $expectedLettersMatchings")
    }

    @Test
    fun testCountAllMatchesFunction() {
        mainClass.checkMethod(mainClazz, countAllMatches)
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountAllMatchesImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int,
        expectedAllMatchings: Int
    ) {
        val userMethod = mainClass.findMethod(mainClazz, countAllMatches)
        Assertions.assertEquals(expectedAllMatchings, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the number of all matchings is $expectedAllMatchings")
    }

    @ParameterizedTest
    @MethodSource("sequences")
    fun testCountExactMatchesImplementation(
        guess: String,
        secret: String,
        expectedPosMatchings: Int,
        expectedLettersMatchings: Int,
        expectedAllMatchings: Int
    ) {
        val userMethod = mainClass.findMethod(mainClazz, countExactMatchesMethod)
        Assertions.assertEquals(expectedPosMatchings, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the number of positional matchings is $expectedPosMatchings")
    }

    @ParameterizedTest
    @MethodSource("inputsToCheck")
    fun testIsCompleteImplementation(
        guess: String,
        secret: String,
        expectedResult: Boolean
    ){
        val userMethod = mainClass.findMethod(mainClazz, isCompleteMethod)
        Assertions.assertEquals(expectedResult, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the function ${isCompleteMethod.name} should return $expectedResult")
    }

    @Test
    fun testIsCompleteFunction() {
        mainClass.checkMethod(mainClazz, isCompleteMethod)
    }

    @Test
    fun testGetGameRulesFunction() {
        mainClass.checkMethod(mainClazz, getGameRulesMethod)
    }

    @Test
    fun testCountGenerateSecretFunction() {
        val userMethod = mainClass.findMethod(mainClazz, generateSecretMethod)
        try {
            val methodRes = userMethod.invokeWithoutArgs(mainClazz)
            val expectedResult = "ABCD"
            Assertions.assertEquals(expectedResult, methodRes) { "The method ${generateSecretMethod.name} should always return $expectedResult" }
        } catch (e: InvocationTargetException) {
            assert(false) { "The method ${generateSecretMethod.name} should always return \"ABCD\" now" }
        }
    }

    @Test
    fun testCountPartialMatchesFunction() {
        mainClass.checkMethod(mainClazz, countPartialMatchesMethod)
    }

    @Test
    fun testCountExactMatchesFunction() {
        mainClass.checkMethod(mainClazz, countExactMatchesMethod)
    }

    @Test
    fun testSolution() {
        val wordLength = 4
        val secretExample = "ACEB"
        val maxAttemptsCount = 3
        val expectedOutput = "Welcome to the game!$newLineSymbol" +
                newLineSymbol +
                "Two people play this game: one chooses a word (a sequence of letters), " +
                "the other guesses it. In this version, the computer chooses the word: " +
                "a sequence of $wordLength letters (for example, $secretExample). " +
                "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
                "For each attempt, the number of complete matches (letter and position) " +
                "and partial matches (letter only) is reported.$newLineSymbol" +
                newLineSymbol +
                "For example, with $secretExample as the hidden word, the BCDF guess will " +
                "give 1 full match (C) and 1 partial match (B).$newLineSymbol" +
                "Please input your guess. It should be of length 4.$newLineSymbol"
        Assertions.assertEquals(expectedOutput, trimOutput(runMainFunction(::main, "ABCD")))
    }

    private fun trimOutput(output: String) = output.lines().joinToString(newLineSymbol) { it.trim() }
}