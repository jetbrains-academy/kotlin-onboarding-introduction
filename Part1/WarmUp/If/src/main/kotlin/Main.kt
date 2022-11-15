fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game, one chooses a word (a sequence of letters), " +
            "the other guesses it. In this case, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "Several attempts are given to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, for the BCDF guess (with $secretExample as the hidden word), " +
            "there will be 1 full match (C), 1 partial match (B)."

fun countLettersMatchings(secret: String, guess: String): Int = TODO("Not implemented yet")

fun countPositionalMatchings(secret: String, guess: String): Int = TODO("Not implemented yet")

fun generateSecret() = "ABCD"

fun isComplete(secret: String, guess: String) = secret == guess

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var complete: Boolean
    do {
        println("Please, input your guess. It should be of length $wordLength.")
        val guess = safeReadLine()
        complete = isComplete(secret, guess)
    } while (!complete)
}

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    println(getGameRules(wordLength, maxAttemptsCount, secretExample))
    playGame(generateSecret(), wordLength, maxAttemptsCount)
}