import jetbrains.kotlin.course.last.push.ball
import jetbrains.kotlin.course.last.push.separator
import org.junit.jupiter.params.provider.Arguments
import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

internal val fillPatternRowMethod = TestMethod(
    "fillPatternRow",
    TestKotlinType("String"),
    listOf(
        TestVariable("patternRow", "String"),
        TestVariable("patternWidth", "Int"),
    ),
)

internal val getPatternHeightMethod = TestMethod(
    "getPatternHeight",
    TestKotlinType("Int"),
    listOf(TestVariable("pattern", "String")),
)

internal fun patternRowsData() = listOf(
    // pattern row, pattern width, resulting row
    Arguments.of("", 5, "$separator".repeat(5)),
    Arguments.of(ball, 5, "$ball$separator$separator$separator$separator"),
    Arguments.of(ball.repeat(2), 5, "$ball$ball$separator$separator$separator"),
    Arguments.of(ball.repeat(3), 5, "$ball$ball$ball$separator$separator"),
    Arguments.of(ball.repeat(4), 5, "$ball$ball$ball$ball$separator"),
    Arguments.of(ball.repeat(5), 5, ball.repeat(5)),
)
