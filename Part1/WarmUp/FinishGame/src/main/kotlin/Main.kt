fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String, alphabet: String) = "Welcome to the game! \n" +
        "\n" +
        "Two people play this game, one guesses a word (a sequence of letters), " +
        "the other guesses it. In this case, the computer guesses the word. " +
        "A sequence of $wordLength letters is guessed (for example, $secretExample). " +
        "Several attempts are given to guess it (max number is $maxAttemptsCount). " +
        "For each attempt, the number of complete matches (letter and position) " +
        "and partial (letter only) is reported. The possible symbols in the word: $alphabet\n" +
        "\n" +
        "For example, for a BCDF guess (with $secretExample guessed) there will " +
        "be 1 full match (C), 1 partial match (B)."

fun countLettersMatchings(secret: String, guess: String): Int =
    guess.filter { it in secret }.length - countPositionalMatchings(secret, guess)

fun countPositionalMatchings(secret: String, guess: String): Int =
    guess.filterIndexed { index, letter -> letter == secret[index] }.length

fun generateSecret(wordLength: Int, alphabet: String) =
    List(wordLength) { alphabet.random() }.joinToString("")

fun isComplete(secret: String, guess: String) = secret == guess

fun printRoundResults(secret: String, guess: String) {
    val positionalMatchings = countPositionalMatchings(secret, guess)
    val lettersMatchings = countLettersMatchings(secret, guess)
    println("Your guess has $positionalMatchings full match, and $lettersMatchings partial match.")
}

fun isWin(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun safeUserInput(wordLength: Int, alphabet: String): String {
    var guess: String
    var isCorrect: Boolean
    do {
        println("Please, input your guess. It should be $wordLength size and each symbol should be from the alphabet: $alphabet.")
        guess = safeReadLine()
        isCorrect = isCorrectInput(guess, wordLength, alphabet)
    } while(!isCorrect)
    return guess
}

fun isCorrectInput(userInput: String, wordLength: Int, alphabet: String): Boolean {
    if (userInput.length != wordLength) {
        println("The length of your guess should be $wordLength! Try again!")
        return false
    }
    val notAlphabetSymbols = userInput.filter { it !in alphabet }
    if (notAlphabetSymbols.isNotEmpty()) {
        println("All symbols in your guess should be from the alphabet: $alphabet! Try again!")
        return false
    }
    return true
}

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int, alphabet: String) {
    var complete: Boolean
    var attempts = 0
    do {
        val guess = safeUserInput(wordLength, alphabet)
        printRoundResults(secret, guess)
        complete = isComplete(secret, guess)
        attempts++
        if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! :( My word is $secret")
            break
        } else if (isWin(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed!")
        }
    } while (!complete)
}

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val alphabet = "ABCDEFGH"
    println(getGameRules(wordLength, maxAttemptsCount, secretExample, alphabet))
    playGame(generateSecret(wordLength, alphabet), wordLength, maxAttemptsCount, alphabet)
}