import jetbrains.kotlin.course.warmup.main
import jetbrains.kotlin.course.warmup.newLineSymbol
import org.jetbrains.academy.test.system.invokeWithArgs
import org.jetbrains.academy.test.system.models.TestKotlinType
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.jetbrains.academy.test.system.invokeWithoutArgs
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

        private val isCompleteMethod = TestMethod(
            "isComplete", TestKotlinType("Boolean"), listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
            )
        )

        const val PACKAGE_NAME = "warmup"
    }

    @ParameterizedTest
    @MethodSource("inputsToCheck")
    fun testIsCompleteImplementation(
        guess: String,
        secret: String,
        expectedResult: Boolean
    ){
        val userMethod = isCompleteMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(expectedResult, userMethod.invokeWithArgs(secret, guess, clazz = findClassSafe(PACKAGE_NAME)),
            "For secret: $secret and guess: $guess the function ${isCompleteMethod.name} should return $expectedResult")
    }

    @Test
    fun testPlayGameFunction() {
        TestMethod(
            "playGame", TestKotlinType("Unit"), listOf(
                TestVariable("secret", "String"),
                TestVariable("wordLength", "Int"),
                TestVariable("maxAttemptsCount", "Int"),
            ),
            "Void"
        )
    }

    @Test
    fun testIsCompleteFunction() {
        isCompleteMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testGetGameRulesFunction() {
        TestMethod(
            "getGameRules", TestKotlinType("String"), listOf(
                TestVariable("wordLength", "Int"),
                TestVariable("maxAttemptsCount", "Int"),
                TestVariable("secretExample", "String"),
            )
        ).getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testCountGenerateSecretFunction() {
        val m = TestMethod("generateSecret", TestKotlinType("String"), emptyList())
        val userMethod = m.getMethodFromClass(PACKAGE_NAME)
        try {
            val methodRes = userMethod.invokeWithoutArgs(findClassSafe(PACKAGE_NAME))
            val expectedResult = "ABCD"
            Assertions.assertEquals(expectedResult, methodRes) { "The method ${m.name} should always return $expectedResult" }
        } catch (e: InvocationTargetException) {
            assert(false) { "The method ${m.name} should always return \"ABCD\" now" }
        }
    }

    @Test
    fun testCountPartialMatchesFunction() {
        TestMethod(
            "countPartialMatches", TestKotlinType("Int"), listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
            )
        ).getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testCountExactMatchesFunction() {
        TestMethod(
            "countExactMatches", TestKotlinType("Int"), listOf(
                TestVariable("secret", "String"),
                TestVariable("guess", "String"),
            )
        ).getMethodFromClass(PACKAGE_NAME)
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
