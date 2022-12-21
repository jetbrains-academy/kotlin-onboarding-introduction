import org.junit.jupiter.params.provider.Arguments
import util.TestMethod
import util.Variable

internal val fillPatternRowMethod = TestMethod(
    "fillPatternRow", "String", listOf(
        Variable("patternRow", "String"),
        Variable("patternWidth", "Int"),
    )
)

internal val getPatternHeightMethod = TestMethod(
    "getPatternHeight", "Int", listOf(
        Variable("pattern", "String"),
    )
)

internal fun patternRowsData() = listOf(
    // pattern row, pattern width, resulting row
    Arguments.of("", 5, "$separator".repeat(5)),
    Arguments.of(ball, 5, "$ball$separator$separator$separator$separator"),
    Arguments.of(ball.repeat(2), 5, "$ball$ball$separator$separator$separator"),
    Arguments.of(ball.repeat(3), 5, "$ball$ball$ball$separator$separator"),
    Arguments.of(ball.repeat(4), 5, "$ball$ball$ball$ball$separator"),
    Arguments.of(ball.repeat(5), 5, ball.repeat(5)),
    Arguments.of(ball.repeat(6), 5, ball.repeat(6))
)
