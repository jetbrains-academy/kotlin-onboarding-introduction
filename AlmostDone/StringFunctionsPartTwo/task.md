Let's implement the second filter!

### Task

Implement the `applySquaredFilter` function.
For the border symbol, please use the pre-defined variable `borderSymbol`, it stores `#`:
```kotlin
println(borderSymbol) // #
```

<div class="hint" title="Push me to see an example of applySquaredFilter work">

Here's an example of the function's work:
<p>
    <img src="../../utils/src/main/resources/images/part1/almost.done/when_hint_2.png" alt="Example of the function's work" width="400"/>
</p>
</div>

To make the picture prettier, add a separator between the picture and the border.
For the separator, please use the pre-defined variable `separator` that stores a space.
```kotlin
println("It is the value from the separator variable: $separator.") // It is the value from the separator variable:  .
```

**Note that the picture might not be a square, which means the width of different lines in the picture can be different.**
In other words, you need to pad the shorter lines with the `separator` to the length to make the image square.
To get the width of the picture, you cak use the predefined function `getPictureWidth`,
that returns the maximum of width from all lines from the picture.

<div class="hint" title="Push me to see an example of getPictureWidth work">

```kotlin
val pictureWidth = getPictureWidth(picture) // calculate the longest line from the picture and returns it's width
```

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

<div class="hint" title="Push me to see several examples how applySquaredFilter function should work">

First example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/squared/android.png" alt="Example of the function's work" width="400"/>

Second example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/squared/monkey.png" alt="Example of the function's work" width="400"/>
</div>

<div class="hint" title="Push me to learn how to run the applySquaredFilter function with pre-defined pictures">

To check how your function works, you can run it in <code>main</code> by passing one of the pre-defined pictures:

```kotlin
fun main() {
  applyFilter(simba, "squared")   // an example with simba picture
  applyFilter(monkey, "squared")  // an example with monkey picture
  applyFilter(android, "squared") // an example with android picture (this picture has different length of lines)
}
```
</div>


<div class="hint" title="Push me to learn the main idea of the algorithm">

You can use the `applyBordersFilter` function to add the borders, next 
create two `StringBuilder`s (one for the top and one for the bottom part), 
and put them row by row.
</div>

<div class="hint" title="Push me to learn how to implement the getPictureWidth function by my own">

If you want, you can try to implement your own implementation fo the `getPictureWidth` function:
split the picture by using <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/lines.html">`lines`</a> function
and next use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/max-of-or-null.html">`maxOfOrNull`</a> function to calculate
the maximum length from picture lines.
</div>

