import jetbrains.kotlin.course.almost.done.allImages
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
        fun pictures() = allImages.map { Arguments.of(it.key) } + WITH_INDENT

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.almost.done",
            customMethods = listOf(trimPictureMethod)
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @Test
    fun testTrimPictureFunction() {
        mainClass.checkMethod(mainClazz, trimPictureMethod)
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testTrimPictureImplementation(picture: String) {
        val expectedPicture = picture.trimIndent()
        val userMethod = mainClass.findMethod(mainClazz, trimPictureMethod)
        Assertions.assertEquals(expectedPicture, userMethod.invokeWithArgs(picture, clazz = mainClazz),
            "For picture:${Util.newLineSeparator}$picture${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}")
    }
}