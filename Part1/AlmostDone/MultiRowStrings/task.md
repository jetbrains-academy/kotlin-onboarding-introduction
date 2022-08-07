### Theory

To use long strings, it is most convenient to use the so-called [multi row strings](https://kotlinlang.org/docs/coding-conventions.html#strings).
Unlike regular strings, they are enclosed in triple quotes:
```kotlin
// A regular string:
val regularString = "My text"

// A multi row string:
val multiRowString = """
    First line of the string\n
    Second line of the string
"""
```

For convenient work with such strings, there are two functions: [`trimIndent`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/trim-indent.html) and [`trimMargin`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/trim-margin.html).
`trimIndent` detects a common minimal indent of all the input lines, 
removes it from every line and also removes the first and the last lines 
if they are blank.
`trimMargin` trims leading whitespace characters 
followed by `marginPrefix` from every line of a source string and removes 
the first and the last lines if they are blank.

For example, the output for the following code:
```kotlin
fun main() {
    val multiRowString = """
    *First line of the string\n
    *Second line of the string
    """
    println("______REGULAR STRING_____")
    println(multiRowString)
    println("______TRIM INDENT_____")
    println(multiRowString.trimIndent())
    println("______TRIM MARGIN WITH *_____")
    println(multiRowString.trimMargin("*"))
}
```

will be:

```text
______REGULAR STRING_____

    *First line of the string\n
    *Second line of the string
    
______TRIM INDENT_____
*First line of the string\n
*Second line of the string
______TRIM MARGIN WITH *_____
First line of the string\n
Second line of the string
```

In the base case the string will have extra indents and margins. 
Also, each row in the string has the `*` symbol.
In the second case the string looks better, but although it has an extra symbol in each row.
The last case looks as the best option.

Therefore, to use multiline strings, it is great to use such functions.

___

### Task

**Description**: implement `trimPicture` function, that accepts a picture and trims all indents from it. 