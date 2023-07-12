import jetbrains.kotlin.course.almost.done.allImages
import jetbrains.kotlin.course.almost.done.main
import jetbrains.kotlin.course.almost.done.newLineSymbol
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
        private const val SQUARED = "squared"
        private const val BORDERS = "borders"

        private const val SQUARED_BAD = "${SQUARED}1"
        private const val BORDERS_BAD = "${BORDERS}1"

        private const val SIMBA = "simba"
        private const val SIMBA_BAD = "${SIMBA}1"

        private const val YES = "yes"
        private const val NO = "no"
        private const val BAD_ANSWER = "yess"

        private const val CUSTOM_IMAGE = "^_^"

        @JvmStatic
        fun pictures() = Image.values().map { Arguments.of(it) }

        @JvmStatic
        fun filters() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$BORDERS$newLineSymbol", true, BORDERS),
            Arguments.of("$BORDERS$newLineSymbol$BORDERS$newLineSymbol", false, BORDERS),
            Arguments.of("$SQUARED$newLineSymbol", true, SQUARED),
            Arguments.of("$SQUARED$newLineSymbol$SQUARED$newLineSymbol", false, SQUARED),
            Arguments.of("$SQUARED_BAD$newLineSymbol$SQUARED$newLineSymbol", true, SQUARED),
            Arguments.of("$BORDERS_BAD$newLineSymbol$BORDERS$newLineSymbol", true, BORDERS),
        )

        @JvmStatic
        fun preDefinedPictures() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
            Arguments.of("$SIMBA$newLineSymbol$SIMBA$newLineSymbol", false, allImages[SIMBA]!!),
            Arguments.of("$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
        )

        @JvmStatic
        fun picturesInputs() = listOf(
            // Input, is System.in empty, output
            Arguments.of("$YES$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
            Arguments.of("$YES$newLineSymbol$SIMBA$newLineSymbol$SIMBA$newLineSymbol", false, allImages[SIMBA]!!),
            Arguments.of("$YES$newLineSymbol$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),

            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA$newLineSymbol$SIMBA$newLineSymbol",
                false,
                allImages[SIMBA]!!
            ),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol",
                true,
                allImages[SIMBA]!!
            ),

            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of(
                "$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol",
                false,
                CUSTOM_IMAGE
            ),

            Arguments.of("$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of(
                "$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol",
                false,
                CUSTOM_IMAGE
            ),
        )

        @JvmStatic
        fun photoshopInputs() = listOf(
            // Input, output
            Arguments.of(
                "$YES$newLineSymbol$SIMBA$newLineSymbol$BORDERS$newLineSymbol",
                Image.SIMBA.borderedImage.trimIndent()
            ),
            Arguments.of(
                "$YES$newLineSymbol$SIMBA$newLineSymbol$SQUARED$newLineSymbol",
                Image.SIMBA.squaredImage.trimIndent()
            ),
            Arguments.of(
                "$NO$newLineSymbol${Image.CUSTOM.initialImage}$newLineSymbol$BORDERS$newLineSymbol",
                Image.CUSTOM.borderedImage.trimIndent()
            ),
            Arguments.of(
                "$NO$newLineSymbol${Image.CUSTOM.initialImage}$newLineSymbol$SQUARED$newLineSymbol",
                Image.CUSTOM.squaredImage.trimIndent()
            ),
        )

        private val photoshop = TestMethod("photoshop", TestKotlinType("Unit"), returnTypeJava = "Void")

        private val trimPictureMethod = TestMethod(
            "trimPicture",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String")),
        )

        private val applyBordersFilterMethod = TestMethod(
            "applyBordersFilter",
            TestKotlinType("String"),
            listOf(TestVariable("picture", "String")),
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

        private val choosePictureMethod = TestMethod("choosePicture", TestKotlinType("String"))

        private val getPictureMethod = TestMethod("getPicture", TestKotlinType("String"))

        private val safeReadLineMethod = TestMethod("safeReadLine", TestKotlinType("String"))

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.almost.done",
            customMethods = listOf(
                trimPictureMethod,
                applyBordersFilterMethod,
                applySquaredFilterMethod,
                applyFilterMethod,
                chooseFilterMethod,
                choosePictureMethod,
                getPictureMethod,
                photoshop,
                safeReadLineMethod,
            ),
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }
    }

    @Test
    fun testPhotoshopFunction() {
        mainClass.checkMethod(mainClazz, photoshop)
    }

    @ParameterizedTest
    @MethodSource("photoshopInputs")
    fun testPhotoshopImplementation(input: String, output: String) {
        assert(output.replaceLineSeparator() in runMainFunction(::main, input.replaceLineSeparator())) { "You applied the photoshop filters incorrect, for the input: $input the filtered image must be$newLineSymbol$output" }
    }

    @Test
    fun testChoosePictureFunction() {
        mainClass.checkMethod(mainClazz, choosePictureMethod)
    }

    @Test
    fun testGetPictureFunction() {
        mainClass.checkMethod(mainClazz, getPictureMethod)
    }

    @ParameterizedTest
    @MethodSource("preDefinedPictures")
    fun testChoosePictureImplementation(input: String, isSystemInEmpty: Boolean, output: String) {
        checkReadLineFunctions(
            testMethod = choosePictureMethod,
            clazz = mainClazz,
            input = input,
            isSystemInEmpty = isSystemInEmpty,
            output = output,
        )
    }

    @ParameterizedTest
    @MethodSource("picturesInputs")
    fun testGetPictureImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(
            testMethod = getPictureMethod,
            clazz = mainClazz,
            input =  input,
            isSystemInEmpty = isSystemInEmpty,
            output =  output,
        )
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
    fun testChooseFilterImplementation(input: String, isSystemInEmpty: Boolean, output: String) {
        checkReadLineFunctions(
            testMethod = chooseFilterMethod,
            clazz = mainClazz,
            input = input,
            isSystemInEmpty = isSystemInEmpty,
            output = output,
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
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <borders> the function ${applyFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
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
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <squared> the function ${applyFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
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
    fun testApplyBordersFilterFunction() {
        mainClass.checkMethod(mainClazz, applyBordersFilterMethod)
    }

    @Test
    fun testApplyFilterFunction() {
        mainClass.checkMethod(mainClazz, applyFilterMethod)
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