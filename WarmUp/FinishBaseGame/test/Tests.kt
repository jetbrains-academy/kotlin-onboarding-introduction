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
        fun gameProcessImitation() = listOf(
            // secret, attempts, game result
            Arguments.of(
                SECRET, listOf(
                    GameStep("BCDF", 0, 3),
                    GameStep("ABCD", 4, 0)
                ), GameResult.WIN
            ),
            Arguments.of(
                SECRET, listOf(
                    GameStep("BCDF", 0, 3),
                    GameStep("ABDC", 2, 2),
                    GameStep("ABCD", 4, 0)
                ), GameResult.WIN
            ),
            Arguments.of(
                SECRET, listOf(
                    GameStep("BCDF", 0, 3),
                    GameStep("BCDF", 0, 3),
                    GameStep("BCDF", 0, 3),
                    GameStep("BCDF", 0, 3),
                ), GameResult.LOSE
            ),
        )

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

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
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
    ){
        val userMethod = mainClass.findMethod(mainClazz, isWinMethod)
        Assertions.assertEquals(isWin, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount, clazz = mainClazz),
            "The function ${isWinMethod.name} must return $isWin for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount")
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
    ){
        val userMethod = mainClass.findMethod(mainClazz, isLostMethod)
        Assertions.assertEquals(isLost, userMethod.invokeWithArgs(complete, attempts, maxAttemptsCount, clazz = mainClazz),
            "The function ${isLostMethod.name} must return $isLost for the following arguments: complete: $complete, attempts: $attempts, maxAttemptsCount: $maxAttemptsCount")
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
            "For secret: $secret and guess: $guess the number of partial matches is $expectedPosMatchings")
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
        Assertions.assertEquals(expectedPosMatchings, userMethod.invokeWithArgs(secret, guess, clazz = mainClazz),
            "For secret: $secret and guess: $guess the number of exact matches is $expectedPosMatchings")
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

    @ParameterizedTest
    @MethodSource("gameProcessImitation")
    fun testSolution(
        secret: String,
        attempts: List<GameStep>,
        gameResult: GameResult
    ) {
        val wordLength = 4
        val secretExample = "ACEB"
        val maxAttemptsCount = 3
        val initialText = "Welcome to the game!$newLineSymbol" +
                newLineSymbol +
                "Two people play this game: one chooses a word (a sequence of letters), " +
                "the other guesses it. In this version, the computer chooses the word: " +
                "a sequence of $wordLength letters (for example, $secretExample). " +
                "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
                "For each attempt, the number of complete matches (letter and position) " +
                "and partial matches (letter only) is reported.$newLineSymbol" +
                newLineSymbol +
                "For example, with $secretExample as the hidden word, the BCDF guess will " +
                "give 1 full match (C) and 1 partial match (B).$newLineSymbol"
        val expectedOutput = "$initialText${attempts.imitateGameProcess()}${Util.newLineSeparator}${gameResult.finalMessage}${Util.newLineSeparator}"

        Assertions.assertEquals(expectedOutput, trimOutput(runMainFunction(::main, "${attempts.imitateUserInput()}${Util.newLineSeparator}")))
    }

    private fun trimOutput(output: String) = output.lines().joinToString(newLineSymbol) { it.trim() }
}