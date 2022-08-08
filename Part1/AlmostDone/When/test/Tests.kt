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
        fun pictures() = allImages.map { Arguments.of(it) }

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
    }

    @Test
    fun testApplyFilterFunction() {
        TestMethod(
            "applyFilter", "String", listOf(
                Variable("picture", "String"),
                Variable("filter", "String"),
            )
        ).getMethodFromClass()
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
        picture: String,
    ) {
        val expectedPicture = picture.trimIndent()
        val userMethod = trimPictureMethod.getMethodFromClass()
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture),
            "For picture:\n$picture\n the function ${trimPictureMethod.name} should return\n$expectedPicture\n"
        )
    }
}