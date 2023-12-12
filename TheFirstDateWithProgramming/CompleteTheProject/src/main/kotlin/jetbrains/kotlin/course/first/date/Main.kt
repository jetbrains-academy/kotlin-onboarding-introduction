package jetbrains.kotlin.course.first.date

fun main() {
    println("Hello! I will ask you several questions.")
    println("Please answer all of them and be honest with me!")
    println("What is TROTEN?")
    val firstUserAnswer = readlnOrNull()
    println("How did you spend your graduation?")
    val secondUserAnswer = readlnOrNull()
    println("Why does a spider need eight legs?")
    val thirdUserAnswer = readlnOrNull()
    println("Now let's have fun!")
    println(firstQuestion)
    println(firstUserAnswer)
    println(secondQuestion)
    println(secondUserAnswer)
    println(thirdQuestion)
    println(thirdUserAnswer)
}
