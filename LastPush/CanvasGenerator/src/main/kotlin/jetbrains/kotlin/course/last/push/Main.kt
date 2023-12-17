package jetbrains.kotlin.course.last.push

// You will use this function later
fun getPattern(): String {
    println(
        "Do you want to use a pre-defined pattern or a custom one? " +
                "Please input 'yes' for a pre-defined pattern or 'no' for a custom one"
    )
    do {
        when (safeReadLine()) {
            "yes" -> {
                return choosePattern()
            }
            "no" -> {
                println("Please, input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

// You will use this function later
fun choosePattern(): String {
    do {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val name = safeReadLine()
        val pattern = getPatternByName(name)
        pattern?.let {
            return@choosePattern pattern
        }
    } while (true)
}

// You will use this function later
fun chooseGenerator(): String {
    var toContinue = true
    var generator = ""
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    do {
        when (val input = safeReadLine()) {
            "canvas", "canvasGaps" -> {
                toContinue = false
                generator = input
            }
            else -> println("Please, input 'canvas' or 'canvasGaps'")
        }
    } while (toContinue)
    return generator
}

// You will use this function later
fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun fillPatternRow(patternRow: String, patternWidth: Int) = if (patternRow.length <= patternWidth) {
    val filledSpace = "$separator".repeat(patternWidth - patternRow.length)
    "$patternRow$filledSpace"
} else {
    error("patternRow length > patternWidth, please check the input!")
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

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val patternWidth = getPatternWidth(pattern)
    val patternHeight = getPatternHeight(pattern)
    val repeatedPattern = repeatHorizontally(pattern, width, patternWidth)
    val dropped = dropTopFromLine(repeatedPattern, width, patternHeight, patternWidth)
    return "$repeatedPattern${baseGenerator(dropped, dropped, height - 1)}"
}

fun main() {
    // Uncomment this code on the last step of the game

    // val pattern = getPattern()
    // val generatorName = chooseGenerator()
    // println("Please input the width of the resulting picture:")
    // val width = safeReadLine().toInt()
    // println("Please input the height of the resulting picture:")
    // val height = safeReadLine().toInt()

    // println("The pattern:$newLineSymbol${pattern.trimIndent()}")

    // println("The generated image:")
    // println(applyGenerator(pattern, generatorName, width, height))
}
