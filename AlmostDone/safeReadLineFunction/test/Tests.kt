import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*
import util.Util.newLineSeparator

@HandleNotImplementedError
@ExtendWith(HandleNotImplementedErrorExtension::class)
class Test {
    companion object {
        @JvmStatic
        fun pictures() = Image.values().map { Arguments.of(it) }

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @Test
    fun testSafeReadLineFunction() {
        mainClass.checkMethod(mainClazz, safeReadLineMethod)
    }

    @Test
    fun testSafeReadLineFunctionImplementation() {
        val userMethod = mainClass.findMethod(mainClazz, safeReadLineMethod)
        val input = "my input"
        setSystemIn("$input$newLineSeparator")
        val userOutput = (userMethod.invokeWithoutArgs(clazz = mainClazz) as String).trim()
        Assertions.assertEquals(
            input,
            userOutput,
            "The function ${safeReadLineMethod.name} for the user input \"$input\" should return the same output, but the current implementation returns \"$userOutput\""
        )
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationBorders(picture: Image) {
        val expectedPicture = picture.borderedImage.trimIndent().replaceLineSeparator()
        val userMethod = mainClass.findMethod(mainClazz, applyFilterMethod)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "borders", clazz = mainClazz) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:$newLineSeparator${picture.initialImage}$newLineSeparator and filter <borders> the function ${applyFilterMethod.name} should return$newLineSeparator$expectedPicture$newLineSeparator"
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
            "For picture:$newLineSeparator${picture.initialImage}$newLineSeparator and filter <squared> the function ${applyFilterMethod.name} should return$newLineSeparator$expectedPicture$newLineSeparator"
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
            "For picture:$newLineSeparator${picture.initialImage}$newLineSeparator the function ${applyBordersFilterMethod.name} should return$newLineSeparator$expectedPicture$newLineSeparator"
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
            "For picture:$newLineSeparator${picture.initialImage}$newLineSeparator the function ${applySquaredFilterMethod.name} should return$newLineSeparator$expectedPicture$newLineSeparator"
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
            "For picture:$newLineSeparator${picture.initialImage}$newLineSeparator the function ${trimPictureMethod.name} should return$newLineSeparator$expectedPicture$newLineSeparator"
        )
    }
}