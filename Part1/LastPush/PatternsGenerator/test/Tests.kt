import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

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

        private val fillPatternRowMethod = TestMethod(
            "fillPatternRow", "String", listOf(
                Variable("patternRow", "String"),
                Variable("patternWidth", "Int"),
            )
        )

        private val getPatternHeightMethod = TestMethod(
            "getPatternHeight", "Int", listOf(
                Variable("pattern", "String"),
            )
        )

        private val canvasGeneratorMethod = TestMethod(
            "canvasGenerator", "String", listOf(
                Variable("pattern", "String"),
                Variable("width", "Int"),
                Variable("height", "Int"),
            )
        )

        private val canvasWithGapsGeneratorMethod = TestMethod(
            "canvasWithGapsGenerator", "String", listOf(
                Variable("pattern", "String"),
                Variable("width", "Int"),
                Variable("height", "Int"),
            )
        )

        private val applyGeneratorMethod = TestMethod(
            "applyGenerator", "String", listOf(
                Variable("pattern", "String"),
                Variable("generatorName", "String"),
                Variable("width", "Int"),
                Variable("height", "Int"),
            )
        )

        private val getPatternMethod = TestMethod("getPattern", "String")

        @JvmStatic
        fun patternRows() = listOf(
            // pattern row, pattern width, resulting row
            Arguments.of("", 5, "$separator".repeat(5)),
            Arguments.of(ball, 5, "$ball$separator$separator$separator$separator"),
            Arguments.of(ball.repeat(2), 5, "$ball$ball$separator$separator$separator"),
            Arguments.of(ball.repeat(3), 5, "$ball$ball$ball$separator$separator"),
            Arguments.of(ball.repeat(4), 5, "$ball$ball$ball$ball$separator"),
            Arguments.of(ball.repeat(5), 5, ball.repeat(5)),
            Arguments.of(ball.repeat(6), 5, ball.repeat(6))
        )

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

        private fun canvas() = Pattern.values()
            .flatMap { f -> f.canvasFilters.map { f.pattern to it } }

        private fun canvasWithGaps() = Pattern.values()
            .flatMap { f -> f.canvasWithGapsFilters.map { f.pattern to it } }

        private fun List<Pair<String, Filter>>.toArguments() = this.map { Arguments.of(it.first, it.second) }

        private fun String.toAddNewLineSymbol() = "$this$newLineSymbol"

        @JvmStatic
        fun generatorsInputs() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$YES$newLineSymbol$CUBE$newLineSymbol", true, allPatterns[CUBE]!!),
            Arguments.of("$YES$newLineSymbol$CUBE$newLineSymbol$CUBE$newLineSymbol", false, allPatterns[CUBE]!!),
            Arguments.of("$YES$newLineSymbol$CUBE_BAD$newLineSymbol$CUBE$newLineSymbol", true, allPatterns[CUBE]!!),

            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$CUBE$newLineSymbol", true, allPatterns[CUBE]!!),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$CUBE$newLineSymbol$CUBE$newLineSymbol",
                false,
                allPatterns[CUBE]!!
            ),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$CUBE_BAD$newLineSymbol$CUBE$newLineSymbol",
                true,
                allPatterns[CUBE]!!
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
            expectedRow, userMethod.invokeWithArgs(patternRow, patternWidth),
            "For pattern row: $patternRow and patternWidth: $patternWidth the function ${fillPatternRowMethod.name} should return $expectedRow"
        )
    }

    @ParameterizedTest
    @MethodSource("patternHeights")
    fun getPatternHeightImplementation(
        pattern: String,
        patternHeight: Int,
    ) {
        val userMethod = getPatternHeightMethod.getMethodFromClass()
        Assertions.assertEquals(
            patternHeight, userMethod.invokeWithArgs(pattern),
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
            canvasFilter.result.toAddNewLineSymbol(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height),
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
            canvasFilter.result.toAddNewLineSymbol(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height),
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
            canvasFilter.result.toAddNewLineSymbol(),
            userMethod.invokeWithArgs(pattern, generatorName, canvasFilter.width, canvasFilter.height),
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
        assert(output.trimIndent() in runMainFunction(::main, input)) { "The patterns generator project should print $output as the output image for the pattern: $input" }
    }
}