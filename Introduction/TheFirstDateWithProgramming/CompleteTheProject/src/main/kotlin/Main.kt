fun main() {
    println("Hello! I will ask you several questions.\n" +
            "Please answer all of them and be honest with me!")
    println("What is TROTEN?")
    val firstUserAnswer = readlnOrNull()
    println("How did you spend your graduation?")
    val secondUserAnswer = readlnOrNull()
    println("Why does a spider need eight legs?")
    val thirdUserAnswer = readlnOrNull()
    println("Now let's have fun!")
    println("$firstQuestion\n$firstUserAnswer")
    println("$secondQuestion\n$secondUserAnswer")
    println("$thirdQuestion\n$thirdUserAnswer")
}
