fun fillPatternRow(patternRow: String, patternWidth: Int) = if (patternRow.length < patternWidth) {
    val filledSpace = "$separator".repeat(patternWidth - patternRow.length)
    "$patternRow$filledSpace"
} else {
    patternRow
}

fun getPatternHeight(pattern: String) = pattern.lines().size

fun main() {
    // Write your code here
}