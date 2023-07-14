import org.jetbrains.academy.test.system.core.models.classes.TestClass

internal val mainClass = TestClass(
    classPackage = "jetbrains.kotlin.course.last.push",
    customMethods = listOf(
        fillPatternRowMethod,
        getPatternHeightMethod,
    )
)
