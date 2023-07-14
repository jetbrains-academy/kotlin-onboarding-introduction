import jetbrains.kotlin.course.warmup.main
import jetbrains.kotlin.course.warmup.newLineSymbol
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import util.*
import java.lang.reflect.InvocationTargetException

class Test {
    companion object {
        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @Test
    fun testPlayGameFunction() {
        mainClass.checkMethod(mainClazz, playGameMethod)
    }

    @Test
    fun testIsCompleteFunction() {
        val userMethod = mainClass.findMethod(mainClazz, isCompleteMethod)
        val methodRes = userMethod.invokeWithArgs("A", "B", clazz = mainClazz)
        val expectedResult = true
        Assertions.assertEquals(
            expectedResult,
            methodRes
        ) { "The method ${isCompleteMethod.name} should always return $expectedResult" }
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
            Assertions.assertEquals(
                expectedResult,
                methodRes
            ) { "The method ${generateSecretMethod.name} should always return $expectedResult" }
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