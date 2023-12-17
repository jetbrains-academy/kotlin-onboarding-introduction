import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val trimPictureMethod = TestMethod(
    "trimPicture",
    TestKotlinType("String"),
    listOf(TestVariable("picture", "String"))
)

internal val applyBordersFilterMethod = TestMethod(
    "applyBordersFilter",
    TestKotlinType("String"),
    listOf(TestVariable("picture", "String"))
)

internal val applySquaredFilterMethod = TestMethod(
    "applySquaredFilter",
    TestKotlinType("String"),
    listOf(TestVariable("picture", "String"))
)

internal val applyFilterMethod = TestMethod(
    "applyFilter",
    TestKotlinType("String"),
    listOf(
        TestVariable("picture", "String"),
        TestVariable("filter", "String"),
    ),
)

internal val safeReadLineMethod = TestMethod("safeReadLine", TestKotlinType("String"))
