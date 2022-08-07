import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.TestMethod
import util.Variable
import util.getMethodFromClass
import util.invokeWithArgs

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
    fun testApplyFilterFunction() {
        applyFilterMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementation(
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
        val expectedPicture = picture.initialImage.trimIndent()
        val userMethod = trimPictureMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage),
            "For picture:\n${picture.initialImage}\n the function ${trimPictureMethod.name} should return\n$expectedPicture\n"
        )
    }
}