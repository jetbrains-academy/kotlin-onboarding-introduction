fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    println("Welcome to the game! \n" +
            "\n" +
            "Two people play this game, one guesses a word (a sequence of letters), " +
            "the other guesses it. In this case, the computer guesses the word. " +
            "A sequence of $wordLength letters is guessed (for example, $secretExample). " +
            "Several attempts are given to guess it (max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial (letter only) is reported. \n" +
            "\n" +
            "For example, for a BCDF guess (with $secretExample guessed) there will " +
            "be 1 full match (C), 1 partial match (B).")
}