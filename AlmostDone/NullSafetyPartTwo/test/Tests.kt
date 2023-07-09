import jetbrains.kotlin.course.almost.done.allImages
import jetbrains.kotlin.course.almost.done.newLineSymbol
import org.jetbrains.academy.test.system.invokeWithArgs
import org.jetbrains.academy.test.system.invokeWithoutArgs
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

        private val chooseFilterMethod = TestMethod("chooseFilter", TestKotlinType("String"))

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

        private val choosePictureMethod = TestMethod("choosePicture", TestKotlinType("String"))

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
            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA$newLineSymbol$SIMBA$newLineSymbol", false, allImages[SIMBA]!!),
            Arguments.of("$BAD_ANSWER$newLineSymbol$YES$newLineSymbol$SIMBA_BAD$newLineSymbol$SIMBA$newLineSymbol", true, allImages[SIMBA]!!),

            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of("$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", false, CUSTOM_IMAGE),

            Arguments.of("$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", true, CUSTOM_IMAGE),
            Arguments.of("$BAD_ANSWER$newLineSymbol$NO$newLineSymbol$CUSTOM_IMAGE$newLineSymbol$CUSTOM_IMAGE$newLineSymbol", false, CUSTOM_IMAGE),
        )

        private val getPictureMethod = TestMethod("getPicture", TestKotlinType("String"))

        const val PACKAGE_NAME = "almost.done"
    }

    @Test
    fun testChoosePictureFunction() {
        choosePictureMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testGetPictureFunction() {
        getPictureMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("preDefinedPictures")
    fun testChoosePictureImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(choosePictureMethod, input, isSystemInEmpty, output)
    }

    @ParameterizedTest
    @MethodSource("picturesInputs")
    fun testGetPictureImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(getPictureMethod, input, isSystemInEmpty, output)
    }

    private fun checkReadLineFunctions(
        testMethod: TestMethod,
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        val userMethod = testMethod.getMethodFromClass(PACKAGE_NAME)
        setSystemIn(input)
        val result = userMethod.invokeWithoutArgs(clazz = findClassSafe(PACKAGE_NAME))
        val errorPostfix = if (!isSystemInEmpty) "not" else ""
        Assertions.assertEquals(isSystemInEmpty, isSystemInEmpty(),
            "For the user's input: $input the function ${testMethod.name} should read $errorPostfix " +
                    "all inputs before returning the result."
        )
        Assertions.assertEquals(output, result, "For the user's input: $input the " +
                "function ${testMethod.name} should return $output")
    }

    @Test
    fun testSafeReadLineFunction() {
        TestMethod("safeReadLine", TestKotlinType("String")).getMethodFromClass(PACKAGE_NAME)
    }

    @Test
    fun testChooseFilterFunction() {
        chooseFilterMethod.getMethodFromClass(PACKAGE_NAME)
    }

    @ParameterizedTest
    @MethodSource("filters")
    fun testChooseFilterImplementation(
        input: String,
        isSystemInEmpty: Boolean,
        output: String
    ) {
        checkReadLineFunctions(chooseFilterMethod, input, isSystemInEmpty, output)
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationBorders(
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

    @ParameterizedTest
    @MethodSource("pictures")
    fun testApplyFilterImplementationSquare(
        picture: Image,
    ) {
        val expectedPicture = picture.squaredImage.trimIndent().replaceLineSeparator()
        val userMethod = applyFilterMethod.getMethodFromClass(PACKAGE_NAME)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), "squared", clazz = findClassSafe(PACKAGE_NAME)) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} and filter <borders> the function ${applyBordersFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
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
    fun testApplySquaredFilterImplementation(
        picture: Image,
    ) {
        val expectedPicture = picture.squaredImage.trimIndent().replaceLineSeparator()
        val userMethod = applySquaredFilterMethod.getMethodFromClass(PACKAGE_NAME)
        val userPicture = (userMethod.invokeWithArgs(picture.initialImage.trimIndent(), clazz = findClassSafe(PACKAGE_NAME)) as String).trim()
        Assertions.assertEquals(
            expectedPicture, userPicture,
            "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${applySquaredFilterMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
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
        val expectedPicture = picture.initialImage.trimIndent().replaceLineSeparator()
        val userMethod = trimPictureMethod.getMethodFromClass(PACKAGE_NAME)
        val actualResult = (userMethod.invokeWithArgs(picture.initialImage, clazz = findClassSafe(PACKAGE_NAME)) as String).replaceLineSeparator()
        Assertions.assertEquals(
                expectedPicture, actualResult,
                "For picture:${Util.newLineSeparator}${picture.initialImage}${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}"
        )
    }
}