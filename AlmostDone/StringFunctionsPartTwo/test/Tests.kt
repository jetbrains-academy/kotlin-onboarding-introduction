import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        @JvmStatic
        fun pictures() = Image.values().map { Arguments.of(it) }

        private val trimPictureMethod = TestMethod(
            "trimPicture",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String"))
        )

        private val applyBordersFilterMethod = TestMethod(
            "applyBordersFilter",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String"))
        )

        private val applySquaredFilterMethod = TestMethod(
            "applySquaredFilter",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String"))
        )

        private val applyFilterMethod = TestMethod(
            "applyFilter",
            TestKotlinType("String"),
            listOf(
                TestVariable("picture", "String"),
                TestVariable("filter", "String"),
            ),
        )

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.almost.done",
            customMethods = listOf(
                trimPictureMethod,
                applyBordersFilterMethod,
                applySquaredFilterMethod,
                applyFilterMethod,
            ),
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationBorders(picture: Image) {
        val expectedPicture = picture.borderedImage.trimIndent().replaceLineSeparator()
        val userMethod = mainClass.findMethod(mainClazz, applyFilterMethod)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "borders", clazz = mainClazz) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <borders> the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationSquare(picture: Image) {
        val expectedPicture = picture.squaredImage.trimIndent().replaceLineSeparator()
        val userMethod = mainClass.findMethod(mainClazz, applyFilterMethod)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "squared", clazz = mainClazz) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <squared> the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyBordersFilterImplementation(picture: Image) {
        val expectedPicture = picture.borderedImage.trimIndent().replaceLineSeparator()
        val userMethod = mainClass.findMethod(mainClazz, applyBordersFilterMethod)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), clazz = mainClazz) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplySquaredFilterImplementation(picture: Image) {
        val expectedPicture = picture.squaredImage.trimIndent().replaceLineSeparator()
        val userMethod = mainClass.findMethod(mainClazz, applySquaredFilterMethod)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), clazz = mainClazz) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${applySquaredFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }

    @Test
    fun testApplyFilterFunction() {
        mainClass.checkMethod(mainClazz, applyFilterMethod)
    }

    @Test
    fun testApplyBordersFilterFunction() {
        mainClass.checkMethod(mainClazz, applyBordersFilterMethod)
    }

    @Test
    fun testApplySquaredFilterFunction() {
        mainClass.checkMethod(mainClazz, applySquaredFilterMethod)
    }

    @Test
    fun testTrimPictureFunction() {
        mainClass.checkMethod(mainClazz, trimPictureMethod)
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testTrimPictureImplementation(picture: Image) {
        val expectedPicture = picture.initialImage.trimIndent().replaceLineSeparator()
        val userMethod = mainClass.findMethod(mainClazz, trimPictureMethod)
        val actualResult = (userMethod.invokeWithArgs(picture.initialImage, clazz = mainClazz) as String).replaceLineSeparator()
        Assertions.assertEquals(
            expectedPicture, actualResult,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }
}