import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val applyGeneratorMethod = TestMethod(
    "applyGenerator", TestKotlinType("String"), listOf(
        TestVariable("pattern", "String"),
        TestVariable("generatorName", "String"),
        TestVariable("width", "Int"),
        TestVariable("height", "Int"),
    )
)

internal val getPatternMethod = TestMethod("getPattern", TestKotlinType("String"))
