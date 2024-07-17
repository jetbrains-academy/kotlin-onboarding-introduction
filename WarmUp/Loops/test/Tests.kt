import jetbrains.kotlin.course.warmup.main
import jetbrains.kotlin.course.warmup.newLineSymbol
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import util.*
import java.lang.reflect.InvocationTargetException

@HandleNotImplementedError(["countPartialMatches", "countExactMatches"])
@ExtendWith(HandleNotImplementedErrorExtension::class)
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
    fun smokeTest() {
        runMainFunction(::main, "")
    }

    @Test
    fun testPlayGameFunction() {
        mainClass.checkMethod(mainClazz, playGameMethod)
    }

    @Test
    fun testPlayGameFunctionImplementation() {
        val userMethod = mainClass.findMethod(mainClazz, playGameMethod)
        setSystemIn("arg$newLineSymbol")
        try {
            userMethod.invoke(mainClazz, "ACEB", 3, 3)
        } catch(e: InvocationTargetException) {
            Assertions.assertTrue(false) { "The method ${playGameMethod.name} should ask the user about input and then call isComplete function!" }
        }

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
            Assertions.assertFalse(false) { "The method ${generateSecretMethod.name} should always return \"ABCD\" now" }
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

    private fun trimOutput(output: String) = output.lines().joinToString(newLineSymbol) { it.trim() }
}