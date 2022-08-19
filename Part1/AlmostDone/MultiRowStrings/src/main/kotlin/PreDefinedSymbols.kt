val borderSymbol = '#'
val separator = ' '
val newLineSymbol = System.lineSeparator()

fun getPictureWidth(picture: String) = picture.split(newLineSymbol).maxOfOrNull { it.length } ?: 0