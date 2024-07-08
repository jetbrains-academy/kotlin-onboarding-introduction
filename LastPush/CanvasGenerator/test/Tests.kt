import jetbrains.kotlin.course.last.push.ball
import jetbrains.kotlin.course.last.push.getPatternHeight
import jetbrains.kotlin.course.last.push.getPatternWidth
import jetbrains.kotlin.course.last.push.newLineSymbol
import org.jetbrains.academy.test.system.core.invokeWithArgs
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
        fun patternRows() = patternRowsData()

        @JvmStatic
        fun patternHeights() = Pattern.values().map { Arguments.of(it.pattern, it.height) }

        @JvmStatic
        fun canvasArguments() = canvas().toArguments()

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }

        @JvmStatic
        fun dropTopLineDataArguments() = dropTopLineData

        @JvmStatic
        fun repeatHorizontallyArguments() = canvas().filter{ (p, f) ->
            f.height == 1
        }.toArguments()
    }

    @Test
    fun canvasGeneratorFunction() {
        mainClass.checkMethod(mainClazz, canvasGeneratorMethod)
    }

    @ParameterizedTest
    @MethodSource("canvasArguments")
    fun canvasGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, canvasGeneratorMethod)
        Assertions.assertEquals(
            canvasFilter.result.replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern.replaceLineSeparator(), canvasFilter.width, canvasFilter.height, clazz = mainClazz).toString().trimIndent(),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${canvasGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @Test
    fun dropTopLineFunction() {
        mainClass.checkMethod(mainClazz, dropTopLineMethod)
    }

    @ParameterizedTest
    @MethodSource("dropTopLineDataArguments")
    fun dropTopLineFunctionImplementation(
        image: String,
        expected: String
    ) {
        val userMethod = mainClass.findMethod(mainClazz, dropTopLineMethod)
        val patternWidth = getPatternWidth(image)
        val patternHeight = getPatternHeight(image)
        val actualResult = userMethod.invokeWithArgs(image.replaceLineSeparator(), 1, patternHeight, patternWidth, clazz = mainClazz).toString()
        val error = "The method ${dropTopLineMethod.name} with arguments image=${newLineSymbol}$image${newLineSymbol}, width=1, patternHeight=$patternHeight, patternWidth=$patternWidth should return$newLineSymbol$expected${newLineSymbol}But it returns$newLineSymbol$actualResult"
        Assertions.assertEquals(
            expected.replaceLineSeparator(),
            actualResult.replaceLineSeparator()
        ) { error }
    }

    @Test
    fun repeatHorizontallyFunction() {
        mainClass.checkMethod(mainClazz, repeatHorizontallyMethod)
    }

    @ParameterizedTest
    @MethodSource("repeatHorizontallyArguments")
    fun repeatHorizontallyFunctionImplementation(
        pattern: String,
        canvasFilter: Filter
    ) {
        val userMethod = mainClass.findMethod(mainClazz, repeatHorizontallyMethod)
        val patternWidth = getPatternWidth(pattern)
        val actualResult = userMethod.invokeWithArgs(pattern, canvasFilter.width, patternWidth, clazz = mainClazz).toString()
        Assertions.assertEquals(
            canvasFilter.result.replaceLineSeparator().removeSuffix(System.lineSeparator()),
            actualResult.replaceLineSeparator().removeSuffix(System.lineSeparator())
        ) { "The method ${repeatHorizontallyMethod.name} with arguments pattern=$pattern, n=${canvasFilter.width}, patternWidth=$patternWidth should return:${System.lineSeparator()}${canvasFilter.result}${System.lineSeparator()}But it returns:${System.lineSeparator()}$actualResult" }
    }

    @Test
    fun fillPatternRowFunction() {
        mainClass.checkMethod(mainClazz, fillPatternRowMethod)
    }

    @ParameterizedTest
    @MethodSource("patternRows")
    fun fillPatternRowImplementation(
        patternRow: String,
        patternWidth: Int,
        expectedRow: String
    ) {
        val userMethod = mainClass.findMethod(mainClazz, fillPatternRowMethod)
        Assertions.assertEquals(
            expectedRow, userMethod.invokeWithArgs(patternRow, patternWidth, clazz = mainClazz),
            "For pattern row: $patternRow and patternWidth: $patternWidth the function ${fillPatternRowMethod.name} should return $expectedRow"
        )
    }

    @Test
    fun fillPatternRowErrorCase() {
        val patternRow = ball.repeat(6)
        val patternWidth = 5

        try {
            val userMethod = mainClass.findMethod(mainClazz, fillPatternRowMethod)
            userMethod.invokeWithArgs(patternRow, patternWidth, clazz = mainClazz)
        } catch (e: InvocationTargetException) {
            assert("IllegalStateException" in e.stackTraceToString()) {"The method ${fillPatternRowMethod.name} should throw an IllegalStateException error if patternRow.length > patternWidth, you can check your solution with this data: patternRow = $patternRow and patternWidth = $patternWidth"}
        }
    }

    @Test
    fun getPatternHeightFunction() {
        mainClass.checkMethod(mainClazz, getPatternHeightMethod)
    }

    @ParameterizedTest
    @MethodSource("patternHeights")
    fun getPatternHeightImplementation(
        pattern: String,
        patternHeight: Int,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, getPatternHeightMethod)
        Assertions.assertEquals(
            patternHeight, userMethod.invokeWithArgs(pattern, clazz = mainClazz),
            "For pattern:$newLineSymbol$pattern$newLineSymbol the function ${getPatternHeightMethod.name} should return $patternHeight"
        )
    }
}