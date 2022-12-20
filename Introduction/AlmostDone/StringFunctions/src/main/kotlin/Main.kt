fun trimPicture(picture: String) = picture.trimIndent()

fun applyBordersFilter(picture: String): String {
    val pictureRows = picture.lines()
    val pictureWidth = getPictureWidth(picture)
    val horizontalBorder = "$borderSymbol".repeat(pictureWidth + 4)

    val sb = StringBuilder()
    sb.append("$horizontalBorder$newLineSymbol")
    for (row in pictureRows) {
        sb.append("$borderSymbol$separator$row")
        if (row.length < pictureWidth) {
            sb.append("$separator".repeat(pictureWidth - row.length))
        }
        sb.append("$separator$borderSymbol$newLineSymbol")
    }
    sb.append("$horizontalBorder$newLineSymbol")
    return sb.toString()
}

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
    // Write your solution in this file
}
