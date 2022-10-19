import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        private const val SQUARED = "squared"
        private const val BORDERS = "borders"

        private const val SQUARED_BAD = "${SQUARED}1"
        private const val BORDERS_BAD = "${BORDERS}1"

        private const val SIMBA = "simba"
        private const val SIMBA_BAD = "${SIMBA}1"

        private const val YES = "yes"
        private const val NO = "no"
        private const val BAD_ANSWER = "yess"

        private const val CUSTOM_IMAGE = "^_^"

        private val trimPictureMethod = TestMethod(
            "trimPicture", "String", listOf(
                Variable("picture", "String"),
            )
        )

        @JvmStatic
        fun pictures() = Image.values().map { Arguments.of(it) }

        private val applyBordersFilterMethod = TestMethod(
            "applyBordersFilter", "String", listOf(
                Variable("picture", "String"),
            )
        )

        private val applySquaredFilterMethod = TestMethod(
            "applySquaredFilter", "String", listOf(
                Variable("picture", "String"),
            )
        )

        private val applyFilterMethod = TestMethod(
            "applyFilter", "String", listOf(
                Variable("picture", "String"),
                Variable("filter", "String"),
            )
        )

        private val chooseFilterMethod = TestMethod("chooseFilter", "String")

        @JvmStatic
        fun filters() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$BORDERS$newLineSymbol", true, BORDERS),
            Arguments.of("$BORDERS${newLineSymbol}$BORDERS$newLineSymbol", false, BORDERS),
            Arguments.of("$SQUARED$newLineSymbol", true, SQUARED),
            Arguments.of("$SQUARED${newLineSymbol}$SQUARED$newLineSymbol", false, SQUARED),
            Arguments.of("$SQUARED_BAD${newLineSymbol}$SQUARED$newLineSymbol", true, SQUARED),
            Arguments.of("$BORDERS_BAD${newLineSymbol}$BORDERS$newLineSymbol", true, BORDERS),
        )

        private val choosePictureMethod = TestMethod("choosePicture", "String")

        @JvmStatic
        fun preDefinedPictures() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
            Arguments.of("$SIMBA$newLineSymbol$SIMBA$newLineSymbol", false, allImages[SIMBA]!!),
            Arguments.of("$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
        )

        @JvmStatic
        fun picturesInputs() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$YES$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
            Arguments.of("$YES$newLineSymbol$SIMBA$newLineSymbol$SIMBA$newLineSymbol", false, allImages[SIMBA]!!),
            Arguments.of("$YES$newLineSymbol$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),

            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA$newLineSymbol$SIMBA$newLineSymbol", false, allImages[SIMBA]!!),
            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),

            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", false, CUSTOM_IMAGE),

            Arguments.of("$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of("$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", false, CUSTOM_IMAGE),
        )

        private val getPictureMethod = TestMethod("getPicture", "String")
    }

    @Test
    fun testChoosePictureFunction() {
        choosePictureMethod.getMethodFromClass()
    }

    @Test
    fun testGetPictureFunction() {
        getPictureMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("preDefinedPictures")
    fun testChoosePictureImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(choosePictureMethod, input, isSystemInEmpty, output)
    }

    @ParameterizedTest
    @MethodSource("picturesInputs")
    fun testGetPictureImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(getPictureMethod, input, isSystemInEmpty, output)
    }

    private fun checkReadLineFunctions(
        testMethod: TestMethod,
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        val userMethod = testMethod.getMethodFromClass()
        setSystemIn(input)
        val result = userMethod.invokeWithoutArgs()
        val errorPostfix = if (!isSystemInEmpty) "not" else ""
        Assertions.assertEquals(isSystemInEmpty, isSystemInEmpty(),
            "For the user's input: $input the function ${testMethod.name} should read $errorPostfix " +
                    "all inputs before returning the result."
        )
        Assertions.assertEquals(output, result, "For the user's input: $input the " +
                "function ${testMethod.name} should return $output")
    }

    @Test
    fun testSafeReadLineFunction() {
        TestMethod("safeReadLine", "String").getMethodFromClass()
    }

    @Test
    fun testChooseFilterFunction() {
        chooseFilterMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("filters")
    fun testChooseFilterImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(chooseFilterMethod, input, isSystemInEmpty, output)
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationBorders(
        picture: Image,
    ) {
        val expectedPicture = "${picture.borderedImage.trimIndent().replaceLineSeparator()}$newLineSymbol"
        val userMethod = applyFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "borders"),
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <borders> the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationSquare(
        picture: Image,
    ) {
        val expectedPicture = "${picture.squaredImage.trimIndent().replaceLineSeparator()}$newLineSymbol"
        val userMethod = applyFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "squared"),
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <borders> the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyBordersFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = "${picture.borderedImage.trimIndent().replaceLineSeparator()}$newLineSymbol"
        val userMethod = applyBordersFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent()),
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplySquaredFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = "${picture.squaredImage.trimIndent().replaceLineSeparator()}$newLineSymbol"
        val userMethod = applySquaredFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent()),
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${applySquaredFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @Test
    fun testApplyFilterFunction() {
        applyFilterMethod.getMethodFromClass()
    }

    @Test
    fun testApplyBordersFilterFunction() {
        applyBordersFilterMethod.getMethodFromClass()
    }

    @Test
    fun testApplySquaredFilterFunction() {
        applySquaredFilterMethod.getMethodFromClass()
    }

    @Test
    fun testTrimPictureFunction() {
        trimPictureMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testTrimPictureImplementation(
        picture: Image,
    ) {
        val expectedPicture = picture.initialImage.trimIndent().replaceLineSeparator()
        val userMethod = trimPictureMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage),
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }
}