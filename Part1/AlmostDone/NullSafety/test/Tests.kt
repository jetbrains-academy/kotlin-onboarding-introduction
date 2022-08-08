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
        val userMethod = chooseFilterMethod.getMethodFromClass()
        setSystemIn(input)
        val result = userMethod.invokeWithoutArgs()
        val errorPostfix = if (!isSystemInEmpty) "not" else ""
        Assertions.assertEquals(isSystemInEmpty, isSystemInEmpty(),
            "For the user's input: $input the function ${chooseFilterMethod.name} should read $errorPostfix " +
                    "all inputs before returning the result."
        )
        Assertions.assertEquals(output, result, "For the user's input: $input the " +
                "function ${chooseFilterMethod.name} should return $output")
    }

    @Test
    fun testApplyBordersFilterFunction() {
        applyBordersFilterMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyBordersFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = "${picture.borderedImage.trimIndent()}$newLineSymbol"
        val userMethod = applyBordersFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent()),
            "For picture:\n${picture.initialImage}\n the function ${applyBordersFilterMethod.name} should return\n$expectedPicture\n"
        )
    }

    @Test
    fun testApplySquaredFilterFunction() {
        applySquaredFilterMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplySquaredFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = "${picture.squaredImage.trimIndent()}$newLineSymbol"
        val userMethod = applySquaredFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent()),
            "For picture:\n${picture.initialImage}\n the function ${applySquaredFilterMethod.name} should return\n$expectedPicture\n"
        )
    }

    @Test
    fun testApplyFilterFunction() {
        applyFilterMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationSquare(
        picture: Image,
    ) {
        val expectedPicture = "${picture.squaredImage.trimIndent()}$newLineSymbol"
        val userMethod = applyFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "squared"),
            "For picture:\n${picture.initialImage}\n and filter <borders> the function ${applyBordersFilterMethod.name} should return\n$expectedPicture\n"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationBorders(
        picture: Image,
    ) {
        val expectedPicture = "${picture.borderedImage.trimIndent()}$newLineSymbol"
        val userMethod = applyFilterMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "borders"),
            "For picture:\n${picture.initialImage}\n and filter <borders> the function ${applyBordersFilterMethod.name} should return\n$expectedPicture\n"
        )
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
        val expectedPicture = picture.initialImage.trimIndent()
        val userMethod = trimPictureMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage),
            "For picture:\n${picture.initialImage}\n the function ${trimPictureMethod.name} should return\n$expectedPicture\n"
        )
    }
}