On this step you need to implement several functions that help you with the main app logic.

The **tests** of this step will be aimed at checking the following _two_ functions:
- `fillPatternRow`, which accepts a `patternRow` (one line from the pattern) and `patternWidth`
  and adds the row `separator` to extend the line to the `patternWidth` size. 
  Please, throw an `IllegalStateException` if `patternRow.length > patternWidth`.

- `getPatternHeight`, which accepts a `pattern` and calculates its height.

Also, in this project, you can use the **already implemented** functions and variables:
- the `separator` variable, which stores a space;

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="The `repeat` built-in function">

To generate a string that consists of some repeated symbols, you can use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/repeat.html"><code>repeat</code></a> function, e.g.:
  ```kotlin
  println("a".repeat(5)) // aaaaa
  ```
</div>

<div class="hint" title="The pattern height calculation">
  The pattern height can be calculated as the number of rows in the pattern.
</div>

<div class="hint" title="The main idea of `fillPatternRow` function">
  To implement the <code>fillPatternRow</code> function, you just need to check 
  if the length of the current pattern row is less than the pattern width. 
  If it is true, add the necessary number of spaces at the end of the row.
</div>