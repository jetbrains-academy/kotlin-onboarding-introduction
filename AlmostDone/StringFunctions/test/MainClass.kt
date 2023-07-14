import org.jetbrains.academy.test.system.core.models.classes.TestClass

internal val mainClass = TestClass(
    classPackage = "jetbrains.kotlin.course.almost.done",
    customMethods = listOf(
        applyBordersFilterMethod,
        applySquaredFilterMethod,
        applyFilterMethod,
        trimPictureMethod,
    )
)
