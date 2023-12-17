package jetbrains.kotlin.course.almost.done

fun trimPicture(picture: String) = picture.trimIndent()

fun applyBordersFilter(picture: String): String = TODO("Not implemented yet")

fun applySquaredFilter(picture: String): String = TODO("Not implemented yet")

fun applyFilter(picture: String, filter: String): String {
    val trimmedPicture = trimPicture(picture)
    return when(filter) {
        "borders" -> applyBordersFilter(trimmedPicture)
        "squared" -> applySquaredFilter(trimmedPicture)
        else -> error("Unexpected filter")
    }
}

fun main() {
    // Uncomment this code on the last step of the game

    // photoshop()
}
