package jetbrains.kotlin.course.gifs.almost.done.runners

import jetbrains.kotlin.course.almost.done.simba
import jetbrains.kotlin.course.almost.done.trimPicture

fun main() {
    println("// The original image:")
    println(simba)
    readln()
    println("// The final image:")
    println(trimPicture(simba))
}
