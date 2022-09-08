fun getGameRules(wordLength: Int, maxAttemptsCount: Int) = "Welcome to the game!\n\n" +
        "In this game, you need to guess the word made by the computer.\n" +
        "The hidden word will appear as a sequence of dashes, one dash means one letter.\n" +
        "You have $maxAttemptsCount attempts to guess the word.\n" +
        "All words are English words, consisting of $wordLength letters.\n" +
        "Each attempt you should enter any one letter,\n" +
        "if it is in the hidden word, all matches will be guessed.\n\n" +
        "" +
        "For example, if the word \"CAT\" was guessed, \"_ _ _\" will be displayed first, " +
        "since the word has 3 letters.\n" +
        "If you enter the letter A, you will see \"_ A _\" and so on.\n\n" +
        "" +
        "Good luck in the game!"

fun isWin(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun deleteSeparator(guess: String) = guess.replace(separator, "")

fun isComplete(secret: String, currentGuess: String) = secret == deleteSeparator(currentGuess)

fun getRoundResults(secret: String, guess: Char, currentUserWord: String): String {
    if (guess !in secret) {
        println("Sorry, the secret does not contain the symbol: $guess. The current word: $currentUserWord")
        return currentUserWord
    }
    val newUserWord = generateNewUserWord(secret, guess, currentUserWord)
    println("Great! This letter is in the word! The current word: $newUserWord")
    return newUserWord
}

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    var newUserWord = ""
    for (i in secret.indices) {
        newUserWord += if (secret[i] == guess) {
            "${secret[i]}$separator"
        } else {
            "${currentUserWord[i * 2]}$separator"
        }
    }
    return newUserWord.removeSuffix(separator)
}

fun getHiddenSecret(wordLength: Int) = List(wordLength) { dash }.joinToString(separator)

fun safeUserInput(): Char {
    var guess: String
    var isCorrect: Boolean
    do {
        println("Please, input your guess.")
        guess = safeReadLine()
        isCorrect = isCorrectInput(guess)
    } while (!isCorrect)
    return guess.uppercase()[0]
}

fun isCorrectInput(userInput: String): Boolean {
    if (userInput.length != 1) {
        println("The length of your guess should be 1! Try again!")
        return false
    }
    if (!userInput[0].isLetter()) {
        println("You should input only English letters! Try again!")
        return false
    }
    return true
}

fun generateSecret() = words.random()

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var complete: Boolean
    var attempts = 0
    var currentUserWord = getHiddenSecret(wordLength)
    println("I guessed a word: $currentUserWord")
    do {
        val guess = safeUserInput()
        currentUserWord = getRoundResults(secret, guess, currentUserWord)
        complete = isComplete(secret, currentUserWord)
        attempts++
        if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! My word is $secret")
            break
        } else if (isWin(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed!")
        }
    } while (!complete)
}

fun main() {
    println(getGameRules(wordLength, maxAttemptsCount))
    playGame(generateSecret(), wordLength, maxAttemptsCount)
}