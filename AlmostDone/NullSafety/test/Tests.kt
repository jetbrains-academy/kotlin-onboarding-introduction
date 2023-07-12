import jetbrains.kotlin.course.almost.done.newLineSymbol
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
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
        private const val SQUARED = "squared"
        private const val BORDERS = "borders"

        private const val SQUARED_BAD = "${SQUARED}1"
        private const val BORDERS_BAD = "${BORDERS}1"

        @JvmStatic
        fun pictures() = Image.values().map { Arguments.of(it) }

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

        private val trimPictureMethod = TestMethod(
            "trimPicture",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String")),
        )

        private val applyBordersFilterMethod = TestMethod(
            "applyBordersFilter",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String"))
        )

        private val applySquaredFilterMethod = TestMethod(
            "applySquaredFilter",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String")),
        )

        private val applyFilterMethod = TestMethod(
            "applyFilter",
            TestKotlinType("String"),
            listOf(
                TestVariable("picture", "String"),
                TestVariable("filter", "String"),
            ),
        )

        private val chooseFilterMethod = TestMethod("chooseFilter", TestKotlinType("String"))

        private val safeReadLineMethod = TestMethod("safeReadLine", TestKotlinType("String"))

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.almost.done",
            customMethods = listOf(
                trimPictureMethod,
                applyBordersFilterMethod,
                applySquaredFilterMethod,
                applyFilterMethod,
                chooseFilterMethod,
                safeReadLineMethod,
            )
        )

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
    fun testChooseFilterFunction() {
        mainClass.checkMethod(mainClazz, chooseFilterMethod)
    }

    @ParameterizedTest
    @MethodSource("filters")
    fun testChooseFilterImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        val userMethod = mainClass.findMethod(mainClazz, chooseFilterMethod)
        setSystemIn(input)
        val result = userMethod.invokeWithoutArgs(mainClazz)
        val errorPostfix = if (!isSystemInEmpty) "not" else ""
        Assertions.assertEquals(isSystemInEmpty, isSystemInEmpty(),
            "For the user's input: $input the function ${chooseFilterMethod.name} should read $errorPostfix " +
                    "all inputs before returning the result."
        )
        Assertions.assertEquals(output, result, "For the user's input: $input the " +
                "function ${chooseFilterMethod.name} should return $output")
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