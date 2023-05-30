import org.jetbrains.academy.test.system.invokeWithArgs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        @JvmStatic
        fun patternRows() = patternRowsData()

        @JvmStatic
        fun patternHeights() = Pattern.values().map { Arguments.of(it.pattern, it.height) }

        @JvmStatic
        fun canvasArguments() = canvas().toArguments()
    }

    @Test
    fun canvasGeneratorFunction() {
        canvasGeneratorMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("canvasArguments")
    fun canvasGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = canvasGeneratorMethod.getMethodFromClass()
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height, clazz = findClassSafe()).toString().trimIndent(),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${canvasGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @Test
    fun fillPatternRowFunction() {
        fillPatternRowMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("patternRows")
    fun fillPatternRowImplementation(
        patternRow: String,
        patternWidth: Int,
        expectedRow: String
    ) {
        val userMethod = fillPatternRowMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedRow, userMethod.invokeWithArgs(patternRow, patternWidth, clazz = findClassSafe()),
            "For pattern row: $patternRow and patternWidth: $patternWidth the function ${fillPatternRowMethod.name} should return $expectedRow"
        )
    }

    @Test
    fun getPatternHeightFunction() {
        getPatternHeightMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("patternHeights")
    fun getPatternHeightImplementation(
        pattern: String,
        patternHeight: Int,
    ) {
        val userMethod = getPatternHeightMethod.getMethodFromClass()
        Assertions.assertEquals(
            patternHeight, userMethod.invokeWithArgs(pattern, clazz = findClassSafe()),
            "For pattern:$newLineSymbol$pattern$newLineSymbol the function ${getPatternHeightMethod.name} should return $patternHeight"
        )
    }
}