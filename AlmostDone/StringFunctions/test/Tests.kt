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

        @JvmStatic
        fun pictures() = Image.values().map { Arguments.of(it) }

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

        private val applyFilterMethod = TestMethod(
            "applyFilter", TestKotlinType("String"), listOf(
                TestVariable("picture", "String"),
                TestVariable("filter", "String"),
            )
        )

        const val PACKAGE_NAME = "almost.done"
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyBordersFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = picture.borderedImage.trimIndent().replaceLineSeparator()
        val userMethod = applyBordersFilterMethod.getMethodFromClass(PACKAGE_NAME)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), clazz = findClassSafe(PACKAGE_NAME)) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = picture.borderedImage.trimIndent().replaceLineSeparator()
        val userMethod = applyFilterMethod.getMethodFromClass(PACKAGE_NAME)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "borders", clazz = findClassSafe(PACKAGE_NAME)) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <borders> the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @Test
    fun testApplyFilterFunction() {
        applyFilterMethod.getMethodFromClass(PACKAGE_NAME)
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
        picture: Image,
    ) {
        val expectedPicture = picture.initialImage.trimIndent()
        val userMethod = trimPictureMethod.getMethodFromClass(PACKAGE_NAME)
        Assertions.assertEquals(
            expectedPicture, userMethod.invokeWithArgs(picture.initialImage, clazz = findClassSafe(PACKAGE_NAME)),
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }
}