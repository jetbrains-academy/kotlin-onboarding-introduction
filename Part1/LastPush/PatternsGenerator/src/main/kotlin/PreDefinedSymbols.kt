val separator = ' '
val newLineSymbol = System.lineSeparator()

fun getPatternWidth(pattern: String) = pattern.split(newLineSymbol).maxOfOrNull { it.length } ?: 0

fun getPatternByName(name: String) = allPatternsMap[name]

fun allPatterns() = allPatternsMap.keys