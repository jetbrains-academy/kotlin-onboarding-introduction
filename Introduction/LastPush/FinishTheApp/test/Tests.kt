import org.jetbrains.academy.test.system.invokeWithArgs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*
import org.jetbrains.academy.test.system.models.TestKotlinType
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.variable.TestVariable
import java.lang.reflect.InvocationTargetException

class Test {

    companion object {
        private const val YES = "yes"
        private const val NO = "no"
        private const val BAD_ANSWER = "yess"

        private const val CUBE = "cube"
        private const val CUBE_BAD = "${CUBE}1"

        private const val CUSTOM_IMAGE = "^_^"
        private const val CUSTOM_IMAGE_CANVAS_5X7 = """
            ^_^^_^^_^^_^^_^
            ^_^^_^^_^^_^^_^
            ^_^^_^^_^^_^^_^
            ^_^^_^^_^^_^^_^
            ^_^^_^^_^^_^^_^
            ^_^^_^^_^^_^^_^
            ^_^^_^^_^^_^^_^
        """
        private const val CUSTOM_IMAGE_CANVAS_GAPS_5X7 = """
            ^_^   ^_^   ^_^
               ^_^   ^_^   
            ^_^   ^_^   ^_^
               ^_^   ^_^   
            ^_^   ^_^   ^_^
               ^_^   ^_^   
            ^_^   ^_^   ^_^
        """

        private const val CANVAS = "canvas"
        private const val CANVAS_GAPS = "canvasGaps"

        private val size_5x7 = "5${newLineSymbol}7$newLineSymbol"

        private val applyGeneratorMethod = TestMethod(
            "applyGenerator", TestKotlinType("String"), listOf(
                TestVariable("pattern", "String"),
                TestVariable("generatorName", "String"),
                TestVariable("width", "Int"),
                TestVariable("height", "Int"),
            )
        )

        private val getPatternMethod = TestMethod("getPattern", TestKotlinType("String"))

        @JvmStatic
        fun patternRows() = patternRowsData()

        @JvmStatic
        fun patternHeights() = Pattern.values().map { Arguments.of(it.pattern, it.height) }

        @JvmStatic
        fun canvasArguments() = canvas().toArguments()

        @JvmStatic
        fun canvasWithGapsArguments() = canvasWithGaps().toArguments()

        @JvmStatic
        fun generators() = canvas().map {
            Arguments.of(
                CANVAS,
                it.first,
                it.second
            )
        } + canvasWithGaps().map { Arguments.of(CANVAS_GAPS, it.first, it.second) }

        @JvmStatic
        fun generatorsInputs() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$YES$newLineSymbol$CUBE$newLineSymbol", true, allPatternsMap[CUBE]!!),
            Arguments.of("$YES$newLineSymbol$CUBE$newLineSymbol$CUBE$newLineSymbol", false, allPatternsMap[CUBE]!!),
            Arguments.of("$YES$newLineSymbol$CUBE_BAD$newLineSymbol$CUBE$newLineSymbol", true, allPatternsMap[CUBE]!!),

            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$CUBE$newLineSymbol", true, allPatternsMap[CUBE]!!),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$CUBE$newLineSymbol$CUBE$newLineSymbol",
                false,
                allPatternsMap[CUBE]!!
            ),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$CUBE_BAD$newLineSymbol$CUBE$newLineSymbol",
                true,
                allPatternsMap[CUBE]!!
            ),

            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of(
                "$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol",
                false,
                CUSTOM_IMAGE
            ),

            Arguments.of("$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol",
                false,
                CUSTOM_IMAGE
            ),
        )

        @JvmStatic
        fun patternsGeneratorInputs() = listOf(
            // Input, output
            Arguments.of("$YES$newLineSymbol$CUBE$newLineSymbol$CANVAS$newLineSymbol$size_5x7", Pattern.CUBE.canvasFilters.find5x7()),
            Arguments.of("$YES$newLineSymbol$CUBE$newLineSymbol$CANVAS_GAPS$newLineSymbol$size_5x7", Pattern.CUBE.canvasWithGapsFilters.find5x7()),
            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CANVAS$newLineSymbol$size_5x7", CUSTOM_IMAGE_CANVAS_5X7),
            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CANVAS_GAPS$newLineSymbol$size_5x7", CUSTOM_IMAGE_CANVAS_GAPS_5X7),
        )

        private fun List<Filter>.find5x7() = this.find { it.width == 5 && it.height == 7 }?.result ?: throwInternalCourseError()
    }

    @Test
    fun fillPatternRowFunction() {
        fillPatternRowMethod.getMethodFromClass()
    }

    @Test
    fun getPatternHeightFunction() {
        getPatternHeightMethod.getMethodFromClass()
    }

    @Test
    fun canvasGeneratorFunction() {
        canvasGeneratorMethod.getMethodFromClass()
    }

    @Test
    fun canvasWithGapsGeneratorFunction() {
        canvasWithGapsGeneratorMethod.getMethodFromClass()
    }

    @Test
    fun applyGeneratorFunction() {
        applyGeneratorMethod.getMethodFromClass()
    }

    @Test
    fun getPatternFunction() {
        getPatternMethod.getMethodFromClass()
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
    fun fillPatternRowErrorCase() {
        val patternRow = ball.repeat(6)
        val patternWidth = 5

        try {
            val userMethod = fillPatternRowMethod.getMethodFromClass()
            userMethod.invokeWithArgs(patternRow, patternWidth, clazz = findClassSafe())
        } catch (e: InvocationTargetException) {
            assert("IllegalStateException" in e.stackTraceToString()) {"The method ${fillPatternRowMethod.name} should throw an IllegalStateException error if patternRow.length > patternWidth, you can check your solution with this data: patternRow = $patternRow and patternWidth = $patternWidth"}
        }
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

    @ParameterizedTest
    @MethodSource("canvasWithGapsArguments")
    fun canvasWithGapsGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = canvasWithGapsGeneratorMethod.getMethodFromClass()
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height, clazz = findClassSafe()).toString().trimIndent(),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${canvasWithGapsGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @ParameterizedTest
    @MethodSource("generators")
    fun applyGeneratorImplementation(
        generatorName: String,
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = applyGeneratorMethod.getMethodFromClass()
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator(),
            userMethod.invokeWithArgs(pattern, generatorName, canvasFilter.width, canvasFilter.height, clazz = findClassSafe()),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, generatorName=$generatorName, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${applyGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @ParameterizedTest
    @MethodSource("generatorsInputs")
    fun getPatternImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(getPatternMethod, input, isSystemInEmpty, output)
    }

    @ParameterizedTest
    @MethodSource("patternsGeneratorInputs")
    fun testPatternsGeneratorImplementation(
        input: String,
        output: String
    ) {
        assert(output.trimIndent().replaceLineSeparator() in runMainFunction(::main, input)) { "The patterns generator project should print $output as the output image for the pattern: $input" }
    }
}