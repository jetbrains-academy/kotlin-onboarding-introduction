import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.*

class Test {
    companion object {
        private val trimPictureMethod = TestMethod(
            "trimPicture", "String", listOf(
                Variable("picture", "String"),
            )
        )

        @JvmStatic
        fun pictures() = allImages.map { Arguments.of(it.key) }
    }

    @Test
    fun testTrimPictureFunction() {
        trimPictureMethod.getMethodFromClass()
    }

    @ParameterizedTest
    @MethodSource("pictures")
    fun testTrimPictureImplementation(
        picture: String,
    ){
        val expectedPicture = picture.trimIndent()
        val userMethod = trimPictureMethod.getMethodFromClass()
        Assertions.assertEquals(expectedPicture, userMethod.invokeWithArgs(picture),
            "For picture:${Util.newLineSeparator}$picture${Util.newLineSeparator} the function ${trimPictureMethod.name} should return${Util.newLineSeparator}$expectedPicture${Util.newLineSeparator}")
    }
}