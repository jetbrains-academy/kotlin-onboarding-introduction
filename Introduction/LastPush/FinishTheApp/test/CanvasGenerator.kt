import org.junit.jupiter.params.provider.Arguments
import util.TestMethod
import util.Variable

internal val canvasGeneratorMethod = TestMethod(
    "canvasGenerator", "String", listOf(
        Variable("pattern", "String"),
        Variable("width", "Int"),
        Variable("height", "Int"),
    )
)

internal fun canvas() = Pattern.values()
    .flatMap { f -> f.canvasFilters.map { f.pattern to it } }

internal fun List<Pair<String, Filter>>.toArguments() = this.map { Arguments.of(it.first, it.second) }

internal fun String.toAddNewLineSymbol() = "$this$newLineSymbol"
