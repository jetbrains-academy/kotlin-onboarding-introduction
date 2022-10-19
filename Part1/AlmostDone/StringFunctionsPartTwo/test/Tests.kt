import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
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