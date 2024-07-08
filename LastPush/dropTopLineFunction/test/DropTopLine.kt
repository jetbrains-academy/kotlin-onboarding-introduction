import jetbrains.kotlin.course.last.push.ball
import jetbrains.kotlin.course.last.push.cube
import jetbrains.kotlin.course.last.push.robot
import org.junit.jupiter.params.provider.Arguments


internal val dropTopLineData = listOf(
    // pattern, expected
    Arguments.of(
        ball, ball
    ),
    Arguments.of(
        robot,
        """
| o   o |
|   ^   |
|  ---  |
+---+---+
        """.trimIndent()
    ),
    Arguments.of(
        cube,
        """
 .' |    .'|
+---+--+'  |
|   |  |   |
|  ,+--+---+
|.'    | .' 
+------+'
        """.trimIndent()
    ),
)