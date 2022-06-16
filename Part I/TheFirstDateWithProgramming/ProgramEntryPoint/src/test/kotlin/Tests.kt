import org.junit.jupiter.api.Assertions
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun testSolution() {
//        runMainFunction()
        val baos = ByteArrayOutputStream()
        val utf8 = StandardCharsets.UTF_8.name();
        val ps = PrintStream(baos, true, utf8)
        System.setOut(ps)
        main()
        Assertions.assertEquals("Hello!\n", baos.toString())
    }
}