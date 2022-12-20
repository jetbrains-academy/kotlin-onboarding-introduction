@file:Suppress("MayBeConst")

val separator = ' '
val newLineSymbol = System.lineSeparator()

fun getPatternWidth(pattern: String) = pattern.lines().maxOfOrNull { it.length } ?: 0

fun getPatternByName(name: String) = allPatternsMap[name]

fun allPatterns() = allPatternsMap.keys
