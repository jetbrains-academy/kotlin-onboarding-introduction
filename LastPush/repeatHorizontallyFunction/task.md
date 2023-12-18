Let' practice to separate an algorithm to several function. We will implement several extra functions 
to combine them into another one which apply a filter to the pattern.

### Task

Implement the `repeatHorizontally` function, which accepts a `pattern`, the number of repeats `n`, and `patternWidth`
and repeat the `pattern` `n` times horizontally.

<div class="hint" title="Click me to see the new signature of the getPatternHeight function">

The signature of the function is:
```kotlin
fun repeatHorizontally(pattern: String, n: Int, patternWidth: Int): String
```
</div>

**Note**, since lines in the pattern can be with different width, you need to use `fillPatternRow` 
function to make all lines with the same width.

In addition, the project already stores the `newLineSymbol` variable, which can be used to add new lines between new generated picture lines, e.g.:
```kotlin
val line1 = "#######"
val line2 = "#######"

val line3 = "$line1$newLineSymbol$line2"
println(line3)
```

The result will be:
```text
#######
#######
```
</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to see several examples how repeatHorizontally function should work">

The first examples:
```kotlin
// Pattern: ○
// n = 1
// Result: ○
```

The second examples:
```kotlin
// Pattern: ○
// n = 2
// Result: ○○
```

The third examples:
```text
Pattern:
 X
/ \
\ /
 X
n = 1
Result:
 X
/ \
\ /
 X 
```

The fourth examples:
```text
Pattern:
 X
/ \
\ /
 X
n = 2
Result:
 X  X 
/ \/ \
\ /\ /
 X  X 
```

</div>

<div class="hint" title="Click me to learn how to run the repeatHorizontally function with pre-defined patterns">

To check how your function works, you can run it in <code>main</code> by passing one of the pre-defined patterns:

```kotlin
fun main() {
    println("Pattern:")
    val n = 2
    println(rhombus)
    println("n = $n")
    println("Result:")
    println(repeatHorizontally(rhombus, n, getPatternWidth(rhombus)))
}
```
</div>
