fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String, alphabet: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game, one chooses a word (a sequence of letters), " +
            "the other guesses it. In this case, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "Several attempts are given to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. The possible symbols in the word: $alphabet$newLineSymbol$newLineSymbol" +
            newLineSymbol +
            "For example, for the BCDF guess (with $secretExample as the hidden word), " +
            "there will be 1 full match (C), 1 partial match (B)."

fun countPartialMatches(secret: String, guess: String): Int {
    val matches = minOf(
        secret.filter { it in guess }.length,
        guess.filter { it in secret }.length,
    )
    return matches - countExactMatches(guess, secret)
}

fun countExactMatches(secret: String, guess: String): Int =
    guess.filterIndexed { index, letter -> letter == secret[index] }.length

fun generateSecret(wordLength: Int, alphabet: String) =
    List(wordLength) { alphabet.random() }.joinToString("")

fun isComplete(secret: String, guess: String) = secret == guess

fun printRoundResults(secret: String, guess: String) {
    val positionalMatches = countExactMatches(secret, guess)
    val lettersMatches = countPartialMatches(secret, guess)
    println("Your guess has $positionalMatches full matches, and $lettersMatches partial matches.")
}

fun isWin(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var complete: Boolean
    var attempts = 0
    do {
        println("Please, input your guess. It should be of length $wordLength.")
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