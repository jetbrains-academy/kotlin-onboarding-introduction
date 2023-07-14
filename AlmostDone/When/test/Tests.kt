import jetbrains.kotlin.course.almost.done.allImages
import org.jetbrains.academy.test.system.invokeWithArgs
import org.jetbrains.academy.test.system.models.TestKotlinType
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        private val trimPictureMethod = TestMethod(
            "trimPicture", TestKotlinType("String"), listOf(
                TestVariable("picture", "String"),
            )
        )

        private const val WITH_INDENT =
            """
            The indent
            must be
            deleted
        """

        @JvmStatic
        fun pictures() = allImages.map { Arguments.of(it.value) } + WITH_INDENT

        private val applyBordersFilterMethod = TestMethod(
            "applyBordersFilter", TestKotlinType("String"), listOf(
                TestVariable("picture", "String"),
            )
        )

        private val applySquaredFilterMethod = TestMethod(
            "applySquaredFilter", TestKotlinType("String"), listOf(
                TestVariable("picture", "String"),
            )
        )

        const val PACKAGE_NAME = "almost.done"
    }

    @Test
    fun testApplyFilterFunction() {
        TestMethod(
            "applyFilter", TestKotlinType("String"), listOf(
                TestVariable("picture", "String"),
                TestVariable("filter", "String"),
            )
        ).getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testApplyBordersFilterFunction() {
        applyBordersFilterMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testApplySquaredFilterFunction() {
        applySquaredFilterMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testTrimPictureFunction() {
        trimPictureMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testTrimPictureImplementation(
        picture: String,
    ){
        val expectedPicture = picture.trimIndent()
        val userMethod = trimPictureMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(expectedPicture, userMethod.invokeWithArgs(picture, clazz = findClassSafe(PACKAGE_NAME)),
            "For picture:${Util.newLineSeparator}$picture${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}")
    }
}