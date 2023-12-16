import jetbrains.kotlin.course.hangman.separator
import jetbrains.kotlin.course.hangman.underscore
import jetbrains.kotlin.course.hangman.words
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Test
import util.*

class Test {
    companion object {
        @JvmStatic
        fun functions() = listOf(
            Arguments.of(generateNewUserWordMethod),
            Arguments.of(isCompleteMethod),
            Arguments.of(generateSecretMethod),
            Arguments.of(getHiddenSecret),
            Arguments.of(isCorrectInputMethod),
        )

        @JvmStatic
        fun isCompleteData() = listOf(
            // secret, guess, isComplete
            Arguments.of("ABC", "A${separator}B${separator}C", true),
            Arguments.of("ABC", "A${separator}B${separator}B", false),
            Arguments.of("ABC", "A${separator}A${separator}A", false),
        )

        @JvmStatic
        fun hiddenSecretsData() = listOf(
            // wordLength, secret
            Arguments.of(1, underscore),
            Arguments.of(2, "${underscore}${separator}${underscore}"),
            Arguments.of(3, "${underscore}${separator}${underscore}${separator}${underscore}"),
            Arguments.of(4, "${underscore}${separator}${underscore}${separator}${underscore}${separator}${underscore}"),
        )

        @JvmStatic
        fun userGuesses() = userGuessesData()

        private val mainClass = TestClass(
            classPackage = "jetbrains.kotlin.course.hangman",
            customMethods = listOf(
                generateNewUserWordMethod,
                isCompleteMethod,
                generateSecretMethod,
                getHiddenSecret,
                isCorrectInputMethod,
            )
        )

        private lateinit var mainClazz: Class<*>

        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mainClazz = mainClass.findClassSafe() ?: throwInternalCourseError()
        }

        @JvmStatic
        fun userInputs() = listOf(
            // input, isCorrect
            Arguments.of("ABCD", false),
            Arguments.of("abc", false),
            Arguments.of("1", false),
            Arguments.of("1a", false),
            Arguments.of("", false),
        )
    }

    @ParameterizedTest
    @MethodSource("functions")
    fun testFunctions(function: TestMethod) {
        mainClass.checkMethod(mainClazz, function)
    }

    @ParameterizedTest
    @MethodSource("userInputs")
    fun testIsCorrectInputImplementation(input: String, isCorrect: Boolean) {
        val userMethod = mainClass.findMethod(mainClazz, isCorrectInputMethod)
        Assertions.assertEquals(
            isCorrect,
            userMethod.invokeWithArgs(input, clazz = mainClazz),
            "The function ${isCorrectInputMethod.name} with argument: input=$input should return $isCorrect"
        )
    }

    @ParameterizedTest
    @MethodSource("hiddenSecretsData")
    fun testGetHiddenSecretImplementation(
        wordLength: Int,
        expectedSecret: String
    ) {
        val userMethod = mainClass.findMethod(mainClazz, getHiddenSecret)
        val actualSecret = userMethod.invokeWithArgs(wordLength, clazz = mainClazz) as String
        Assertions.assertEquals(
            expectedSecret,
            actualSecret,
            "The function ${getHiddenSecret.name} with argument: wordLength=$wordLength should return $expectedSecret, but the current result is $actualSecret"
        )
    }

    @Test
    fun testGenerateSecretMethodImplementation() {
        val userMethod = mainClass.findMethod(mainClazz, generateSecretMethod)
        val generatedWords = mutableSetOf<String>()
        repeat (100) {
            val word = userMethod.invokeWithoutArgs(clazz = mainClazz) as String
            Assertions.assertTrue(word in words) { "The method ${generateSecretMethod.name} should generate a random word from the 'words' list, but it returns the word $word, which is not in the 'words' list" }
            generatedWords.add(word)
        }
        Assertions.assertTrue(generatedWords.size > 1) { "The method ${generateSecretMethod.name} should generate random words, but after 100 calls it generated only: $words" }
    }

    @ParameterizedTest
    @MethodSource("userGuesses")
    fun testGenerateNewUserWordImplementation(
        secret: String,
        guess: Char,
        currentUserWord: String,
        expectedGuess: String?
    ) {
        val userMethod = mainClass.findMethod(mainClazz, generateNewUserWordMethod)
        val actualGuess = (userMethod.invokeWithArgs(secret, guess, currentUserWord, clazz = mainClazz) as String).dropLastWhile { it.toString() == separator }
        val expected = expectedGuess ?: currentUserWord
        Assertions.assertEquals(
            expected,
            actualGuess,
            "The function ${generateNewUserWordMethod.name} with arguments: secret=$secret, guess=$guess, and currentUserWord=$currentUserWord should return $expected"
        )
    }

    @ParameterizedTest
    @MethodSource("isCompleteData")
    fun testIsCompleteMethodImplementation(
        secret: String,
        guess: String,
        isComplete: Boolean
    ) {
        val userMethod = mainClass.findMethod(mainClazz, isCompleteMethod)
        val actualResult = (userMethod.invokeWithArgs(secret, guess, clazz = mainClazz) as Boolean)
        Assertions.assertEquals(isComplete, actualResult) { "The function ${isCompleteMethod.name} with arguments secret=$secret, guess=$guess should return $isComplete, but the current result is $actualResult" }
    }
}