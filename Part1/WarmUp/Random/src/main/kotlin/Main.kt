fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String, alphabet: String) = "Welcome to the game! $newLineSymbol" +
        newLineSymbol +
        "Two people play this game, one guesses a word (a sequence of letters), " +
        "the other guesses it. In this case, the computer guesses the word. " +
        "A sequence of $wordLength letters is guessed (for example, $secretExample). " +
        "Several attempts are given to guess it (max number is $maxAttemptsCount). " +
        "For each attempt, the number of complete matches (letter and position) " +
        "and partial (letter only) is reported. The possible symbols in the word: $alphabet$newLineSymbol" +
        newLineSymbol +
        "For example, for a BCDF guess (with $secretExample guessed) there will " +
        "be 1 full match (C), 1 partial match (B)."

fun countLettersMatchings(secret: String, guess: String): Int {
    val matchings = minOf(
        secret.filter { it in guess }.length,
        guess.filter { it in secret }.length,
    )
    return matchings - countPositionalMatchings(guess, secret)
}

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

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var complete: Boolean
    var attempts = 0
    do {
        println("Please, input your guess. It should be $wordLength size.")
        val guess = safeReadLine()
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
    playGame(generateSecret(wordLength, alphabet), wordLength, maxAttemptsCount)
}