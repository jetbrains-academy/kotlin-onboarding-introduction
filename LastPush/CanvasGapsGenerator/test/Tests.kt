import jetbrains.kotlin.course.last.push.ball
import jetbrains.kotlin.course.last.push.newLineSymbol
import org.jetbrains.academy.test.system.invokeWithArgs
import org.junit.jupiter.api.Assertions
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

        @JvmStatic
        fun canvasWithGapsArguments() = canvasWithGaps().toArguments()

        const val PACKAGE_NAME = "last.push"
    }

    @Test
    fun canvasWithGapsGeneratorFunction() {
        canvasWithGapsGeneratorMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("canvasWithGapsArguments")
    fun canvasWithGapsGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = canvasWithGapsGeneratorMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height, clazz = findClassSafe(PACKAGE_NAME)).toString().trimIndent(),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${canvasWithGapsGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @Test
    fun canvasGeneratorFunction() {
        canvasGeneratorMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("canvasArguments")
    fun canvasGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = canvasGeneratorMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height, clazz = findClassSafe(PACKAGE_NAME)).toString().trimIndent(),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${canvasGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @Test
    fun fillPatternRowFunction() {
        fillPatternRowMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("patternRows")
    fun fillPatternRowImplementation(
        patternRow: String,
        patternWidth: Int,
        expectedRow: String
    ) {
        val userMethod = fillPatternRowMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            expectedRow, userMethod.invokeWithArgs(patternRow, patternWidth, clazz = findClassSafe(PACKAGE_NAME)),
            "For pattern row: $patternRow and patternWidth: $patternWidth the function ${fillPatternRowMethod.name} should return $expectedRow"
        )
    }

    @Test
    fun fillPatternRowErrorCase() {
        val patternRow = ball.repeat(6)
        val patternWidth = 5

        try {
            val userMethod = fillPatternRowMethod.getMethodFromClass(PACKAGE_NAME)
            userMethod.invokeWithArgs(patternRow, patternWidth, clazz = findClassSafe(PACKAGE_NAME))
        } catch (e: InvocationTargetException) {
            assert("IllegalStateException" in e.stackTraceToString()) {"The method ${fillPatternRowMethod.name} should throw an IllegalStateException error if patternRow.length > patternWidth, you can check your solution with this data: patternRow = $patternRow and patternWidth = $patternWidth"}
        }
    }

    @Test
    fun getPatternHeightFunction() {
        getPatternHeightMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("patternHeights")
    fun getPatternHeightImplementation(
        pattern: String,
        patternHeight: Int,
    ) {
        val userMethod = getPatternHeightMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            patternHeight, userMethod.invokeWithArgs(pattern, clazz = findClassSafe(PACKAGE_NAME)),
            "For pattern:$newLineSymbol$pattern$newLineSymbol the function ${getPatternHeightMethod.name} should return $patternHeight"
        )
    }
}