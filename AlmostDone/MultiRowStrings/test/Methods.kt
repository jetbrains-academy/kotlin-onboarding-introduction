import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val trimPictureMethod = TestMethod(
    "trimPicture", TestKotlinType("String"), listOf(
        TestVariable("picture", "String"),
    )
)
