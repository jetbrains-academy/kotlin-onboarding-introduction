import jetbrains.kotlin.course.last.push.newLineSymbol
import org.junit.jupiter.params.provider.Arguments
import org.jetbrains.academy.test.system.models.TestKotlinType
import org.jetbrains.academy.test.system.models.method.TestMethod
import org.jetbrains.academy.test.system.models.variable.TestVariable

internal val canvasGeneratorMethod = TestMethod(
    "canvasGenerator", TestKotlinType("String"), listOf(
        TestVariable("pattern", "String"),
        TestVariable("width", "Int"),
        TestVariable("height", "Int"),
    )
)

internal fun canvas() = Pattern.values()
    .flatMap { f -> f.canvasFilters.map { f.pattern to it } }

internal fun List<Pair<String, Filter>>.toArguments() = this.map { Arguments.of(it.first, it.second) }

internal fun String.toAddNewLineSymbol() = "$this$newLineSymbol"
