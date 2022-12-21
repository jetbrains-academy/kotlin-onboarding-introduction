import util.TestMethod
import util.Variable

internal val canvasWithGapsGeneratorMethod = TestMethod(
    "canvasWithGapsGenerator", "String", listOf(
        Variable("pattern", "String"),
        Variable("width", "Int"),
        Variable("height", "Int"),
    )
)

internal fun canvasWithGaps() = Pattern.values()
    .flatMap { f -> f.canvasWithGapsFilters.map { f.pattern to it } }
