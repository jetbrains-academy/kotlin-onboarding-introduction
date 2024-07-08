import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val canvasWithGapsGeneratorMethod = TestMethod(
    "canvasWithGapsGenerator",
    TestKotlinType("String"),
    listOf(
        TestVariable("pattern", "String"),
        TestVariable("width", "Int"),
        TestVariable("height", "Int"),
    ),
)

internal fun canvasWithGaps() = Pattern.values()
    .flatMap { f -> f.canvasWithGapsFilters.map { f.pattern to it } }
