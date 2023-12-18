When we repeat the pattern vertically, the bottom border will be repeated twice. 
For these cases we need to remove the top line.

### Task

Implement the `dropTopFromLine` function, which accepts a `line` (any string, can be multi-row), 
`width` of the new image that should be created (`line` should be repeated `width` times already),
`patternHeight`, and `patternWidth`. This function deletes the first line,
e.g. (for `width` = 1):
```text
   .+------+                 
 .' |    .'|                .' |    .'|
+---+--+'  |    ----->     +---+--+'  |
|   |  |   |               |   |  |   |
|  ,+--+---+               |  ,+--+---+
|.'    | .'                |.'    | .' 
+------+'                  +------+'
```


<div class="hint" title="Click me to see the new signature of the getPatternHeight function">

The signature of the function is:
```kotlin
fun dropTopFromLine(line: String, width: Int, patternHeight: Int, patternWidth: Int): String
```
</div>

**Note**, this function has to remove the first line only if `patternHeight > 1`. 

You can implement this function in any possible way, but we _recommend_ to look into the [`removeSuffix`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/remove-suffix.html) and [`drop`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/drop.html) built-in functions.

<div class="Hint" title="Click me to learn more about removeSuffix built-in function">

The [`removeSuffix`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/remove-suffix.html) built-in function helps
to delete a string from the end of the current string:
```kotlin
val str = "abcdefg"
println("abcdefg".removeSuffix("fg")) // abcde
println("abcdefg".removeSuffix("a")) // abcdefg, since the initial string does not end with "a"
```
</div>

<div class="Hint" title="Click me to learn more about drop built-in function">

The [`drop`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/drop.html) built-in function helps
to delete `n` symbols from the beginning of the current string:
```kotlin
val str = "abcdefg"
println("abcdefg".drop(1)) // bcdefg
```
</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to see several examples how dropTopFromLine function should work">

The first examples:
```text
○
```
This code:
```kotlin
println(ball)
println("___")
val patternWidth = getPatternWidth(ball)
val patternHeight = getPatternHeight(ball)
val repeatedPattern = repeatHorizontally(ball, 1, patternWidth)
val dropped = dropTopFromLine(repeatedPattern, 1, patternHeight, patternWidth)
println(dropped)
```
will print:
```text
○
```

The second example. For the pattern:
```text
 X
/ \
\ /
 X
```
This code:
```kotlin
println(rhombus)
println("___")
val patternWidth = getPatternWidth(rhombus)
val patternHeight = getPatternHeight(rhombus)
val repeatedPattern = repeatHorizontally(rhombus, 1, patternWidth)
val dropped = dropTopFromLine(repeatedPattern, 1, patternHeight, patternWidth)
println(dropped)
```
will print:
```text
/ \
\ /
 X 
```
You can notice that we use already implemented functions in this example. 
These functions will help us to implement the filters just to repeat vertically the pattern.

</div>

<div class="hint" title="Click me to learn how to calculate number of symbols to drop">

You need to remove `patternWidth * width + newLineSymbol.length` symbols from the beginning of the line, since
you have a line where the pattern is repeated `width` times and `newLineSymbol` in different OS can be more than `1`.
</div>
