import jetbrains.kotlin.course.hangman.*
import org.jetbrains.academy.test.system.core.invokeWithArgs
import org.jetbrains.academy.test.system.core.invokeWithoutArgs
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.classes.findClassSafe
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Test
import util.*

@HandleNotImplementedError
@ExtendWith(HandleNotImplementedErrorExtension::class)
class Test {
    companion object {
        @JvmStatic
        fun functions() = listOf(
            Arguments.of(generateNewUserWordMethod),
            Arguments.of(isCompleteMethod),
            Arguments.of(generateSecretMethod),
            Arguments.of(getHiddenSecret),
            Arguments.of(isCorrectInputMethod),
            Arguments.of(safeUserInputMethod),
            Arguments.of(getRoundResultsMethod),
            Arguments.of(playGameMethod),
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
                safeUserInputMethod,
                getRoundResultsMethod,
                playGameMethod,
            )
        )

        @JvmStatic
        fun safeUserInputs() = listOf(
            // inputs, final input
            Arguments.of(listOf("AB", "abc", "1", "b"), 'B'),
            Arguments.of(listOf("b"), 'B'),
            Arguments.of(listOf("AB", "abc", "1", "b", "c"), 'B'),
            Arguments.of(listOf("AB", "abc", "1", "b", "c1d"), 'B'),
            Arguments.of(listOf("b", "c"), 'B'),
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

        fun getRoundResults(secret: String, guess: Char, currentUserWord: String): String {
            if (guess !in secret) {
                println("Sorry, the secret does not contain the symbol: $guess. The current word is $currentUserWord")
                return currentUserWord
            }
            val newUserWord = generateNewUserWord(secret, guess, currentUserWord)
            println("Great! This letter is in the word! The current word is $newUserWord")
            return newUserWord
        }

        @JvmStatic
        fun roundResults() = listOf(
            // secret, guess, currentUserWord, expected guess (null if the same)
            Arguments.of("BOOK", 'A', "$underscoreWithSeparator$underscoreWithSeparator${underscoreWithSeparator}K", null),
            Arguments.of("BOOK", 'B', "$underscoreWithSeparator$underscoreWithSeparator${underscoreWithSeparator}K", "B$separator$underscoreWithSeparator${underscoreWithSeparator}K"),
        )
    }

    @Test
    fun smokeTest() {
        runMainFunction(::main, List(maxAttemptsCount + 1) { "A$newLineSymbol" }.joinToString(""))
    }

    @ParameterizedTest
    @MethodSource("functions")
    fun testFunctions(function: TestMethod) {
        mainClass.checkMethod(mainClazz, function)
    }

    @Test
    fun testSolution() {
        val secrets = mutableListOf<String>()
        repeat(1000) {
            val secret = imitateGameRound()
            secret?.let { secrets.add(it) }
        }
        Assertions.assertTrue(secrets.toSet().size > 1) { "Please, generate a random word from the <words> list. Don't forget uncomment code in the main function to run the game!" }
    }

    @Test
    fun testGameImitation() {
        imitateGameRound(true)
    }

    @ParameterizedTest
    @MethodSource("roundResults")
    fun testGetRoundResultsImplementation(
        secret: String,
        guess: Char,
        currentUserWord: String,
        expectedGuess: String?
    ) {
        val userMethod = mainClass.findMethod(mainClazz, getRoundResultsMethod)
        val baos = setSystemOut()
        val actualNewGuess = userMethod.invokeWithArgs(secret, guess, currentUserWord, clazz = mainClazz) as String
        val consoleText = baos.toString("UTF-8").replaceLineSeparator()
        val errorPrefix = "The function ${getRoundResultsMethod.name} with arguments secret=$secret, guess=$guess, currentUserWord=$currentUserWord"
        if (expectedGuess == null) {
            val sorryText = "Sorry, the secret does not contain the symbol: $guess. The current word is $currentUserWord"
            Assertions.assertTrue(
                sorryText in consoleText
            ) { "$errorPrefix should print $sorryText" }
        } else {
            val greatText = "Great! This letter is in the word! The current word is $expectedGuess"
            Assertions.assertTrue(
                greatText in consoleText
            ) { "$errorPrefix should print $greatText" }
        }

        val expectedGuessReal = expectedGuess ?: currentUserWord
        Assertions.assertEquals(
            expectedGuessReal,
            actualNewGuess,
            "$errorPrefix should return $expectedGuessReal, but it returns $actualNewGuess"
        )
    }

    @ParameterizedTest
    @MethodSource("safeUserInputs")
    fun testSafeUserInputImplementation(
        safeUserInputs: List<String>,
        finalInput: Char
    ) {
        val userMethod = mainClass.findMethod(mainClazz, safeUserInputMethod)
        setSystemIn(safeUserInputs)
        val actualInput = userMethod.invokeWithoutArgs(mainClazz)
        Assertions.assertEquals(
            finalInput,
            actualInput,
            "The function ${safeUserInputMethod.name} for the sequence of the user inputs: ${safeUserInputs.joinToString(", ")} should return $actualInput"
        )
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