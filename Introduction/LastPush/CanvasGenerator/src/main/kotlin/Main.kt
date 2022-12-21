fun fillPatternRow(patternRow: String, patternWidth: Int) = if (patternRow.length < patternWidth) {
    val filledSpace = "$separator".repeat(patternWidth - patternRow.length)
    "$patternRow$filledSpace"
} else {
    patternRow
}

fun getPatternHeight(pattern: String) = pattern.lines().size

fun repeatHorizontally(pattern: String, n: Int, patternWidth: Int): String {
    val pictureRows = pattern.lines()
    val sb = StringBuilder()
    for (row in pictureRows) {
        val currentRow = fillPatternRow(row, patternWidth)
        sb.append(currentRow.repeat(n))
        sb.append(newLineSymbol)
    }
    return sb.toString()
}

fun dropTopFromLine(line: String, width: Int, patternHeight: Int, patternWidth: Int): String {
    val nToDrop = if (patternHeight > 1) {
        patternWidth * width + newLineSymbol.length
    } else {
        0
    }
    val newPattern = line.removeSuffix(newLineSymbol).drop(nToDrop)
    return "$newPattern$newLineSymbol"
}

fun baseGenerator(firstLine: String, secondLine: String, height: Int): String {
    val sb = StringBuilder()
    sb.append(firstLine)
    return when {
        height < 1 -> ""
        height == 1 -> sb.toString()
        else -> {
            sb.append(secondLine)
            sb.append(repeatVertically(firstLine, secondLine, height))
            sb.toString()
        }
    }
}

fun repeatVertically(firstLine: String, secondLine: String, height: Int): String {
    val currentHeight = makeEvenNumber(height) / 2 - 1
    val pattern = "$firstLine$secondLine".repeat(currentHeight)
    return if (height % 2 == 0) {
        pattern
    } else {
        "$pattern$firstLine"
    }
}

fun makeEvenNumber(number: Int) = if (number % 2 == 0) {
    number
} else {
    number - 1
}

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val patternWidth = getPatternWidth(pattern)
    val patternHeight = getPatternHeight(pattern)
    val repeatedPattern = repeatHorizontally(pattern, width, patternWidth)
    val dropped = dropTopFromLine(repeatedPattern, width, patternHeight, patternWidth)
    return "$repeatedPattern${baseGenerator(dropped, dropped, height - 1)}"
}

fun main() {
    // Write your code here
}