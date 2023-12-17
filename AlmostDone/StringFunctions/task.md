In this task we will implement one of the filters.

### Task

Implement the `applyBordersFilter` function. 
For the border symbol, please use the pre-defined variable `borderSymbol`, it stores `#`:
```kotlin
println(borderSymbol) // #
```

<div class="hint" title="Push me to see an example of applyBordersFilter work">

Here's an example of the function's work:
<p>
    <img src="../../utils/src/main/resources/images/part1/almost.done/when_hint_1.png" alt="Example of the function's work" width="200"/>
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

<div class="hint" title="Push me to see several examples how applyBordersFilter function should work">

First example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/borders/android.png" alt="Example of the function's work" width="200"/>

Second example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/borders/monkey.png" alt="Example of the function's work" width="200"/>

</div>

<div class="hint" title="Push me to learn how to run the applyBordersFilter function with pre-defined pictures">

To check how your function works, you can run it in <code>main</code> by passing one of the pre-defined pictures:

```kotlin
fun main() {
  applyFilter(simba, "borders")   // an example with simba picture
  applyFilter(monkey, "borders")  // an example with monkey picture
  applyFilter(android, "borders") // an example with android picture (this picture has different length of lines)
}
```
</div>

<div class="hint" title="Push me to get a hint how to calculate the length of the top and bottom edges for the new picture">

The length of the top and bottom edges of the border will be 4 characters longer than the width of the initial picture, 
since we add the <code>borderSymbol</code> and the <code>separator</code> on both sides of the image.
</div>


<div class="hint" title="Push me to learn how to implement the getPictureWidth function by my own">

  If you want, you can try to implement your own implementation fo the `getPictureWidth` function: 
  split the picture by using <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/lines.html">`lines`</a> function 
  and next use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/max-of-or-null.html">`maxOfOrNull`</a> function to calculate 
  the maximum length from picture lines.   
</div>

