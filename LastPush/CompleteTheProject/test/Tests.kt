import jetbrains.kotlin.course.last.push.*
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*
import java.lang.reflect.InvocationTargetException

@HandleNotImplementedError
@ExtendWith(HandleNotImplementedErrorExtension::class)
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
    fun smokeTest() {
        runMainFunction(
            ::main,
            "$YES$newLineSymbol$CUBE$newLineSymbol$CANVAS${newLineSymbol}5${newLineSymbol}5$newLineSymbol"
        )
    }

    @Test
    fun fillPatternRowFunction() {
        mainClass.checkMethod(mainClazz, fillPatternRowMethod)
    }

    @Test
    fun getPatternHeightFunction() {
        mainClass.checkMethod(mainClazz, getPatternHeightMethod)
    }

    @Test
    fun canvasGeneratorFunction() {
        mainClass.checkMethod(mainClazz, canvasGeneratorMethod)
    }

    @Test
    fun canvasWithGapsGeneratorFunction() {
        mainClass.checkMethod(mainClazz, canvasWithGapsGeneratorMethod)
    }

    @Test
    fun applyGeneratorFunction() {
        mainClass.checkMethod(mainClazz, applyGeneratorMethod)
    }

    @Test
    fun getPatternFunction() {
        mainClass.checkMethod(mainClazz, getPatternMethod)
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

    @ParameterizedTest
    @MethodSource("canvasArguments")
    fun canvasGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, canvasGeneratorMethod)
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height, clazz = mainClazz).toString().trimIndent(),
            "For pattern:$newLineSymbol$pattern$newLineSymbol, width=${canvasFilter.width}, and height=${canvasFilter.height} the function ${canvasGeneratorMethod.name} should return $newLineSymbol${canvasFilter.result}$newLineSymbol"
        )
    }

    @ParameterizedTest
    @MethodSource("canvasWithGapsArguments")
    fun canvasWithGapsGeneratorImplementation(
        pattern: String,
        canvasFilter: Filter,
    ) {
        val userMethod = mainClass.findMethod(mainClazz, canvasWithGapsGeneratorMethod)
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator().trimIndent(),
            userMethod.invokeWithArgs(pattern, canvasFilter.width, canvasFilter.height, clazz = mainClazz).toString().trimIndent(),
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
        val userMethod = mainClass.findMethod(mainClazz, applyGeneratorMethod)
        Assertions.assertEquals(
            canvasFilter.result.toAddNewLineSymbol().replaceLineSeparator(),
            userMethod.invokeWithArgs(pattern.replaceLineSeparator(), generatorName, canvasFilter.width, canvasFilter.height, clazz = mainClazz).toString().replaceLineSeparator(),
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
        checkReadLineFunctions(
            testMethod = getPatternMethod,
            clazz = mainClazz,
            input = input,
            isSystemInEmpty = isSystemInEmpty,
            output = output,
        )
    }

    @ParameterizedTest
    @MethodSource("patternsGeneratorInputs")
    fun testPatternsGeneratorImplementation(
        input: String,
        output: String
    ) {
        assert(output.trimIndent().replaceLineSeparator() in runMainFunction(::main, input)) { "The patterns generator project should print $output as the output image for the pattern: $input" }
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
}