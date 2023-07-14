### Theory

1. How to separate a string into lines?
2. The `repeat` function
3. What is `StringBuilder`?

#### 1. How to separate a string into lines?

When working with multiline strings, you can break them into lines and handle each one separately. 
This can be done using the [`lines`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/lines.html) function:
```kotlin
"""First line
Second line
""".lines()
```
The string will be converted into a list with two strings: `First line` and `Second line`.

#### 2. The `repeat` function

Also, to generate a string by repeating a symbol, you can use the [`repeat`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/repeat.html) function:
```kotlin
"@".repeat(4)
```
It will generate the following string: `@@@@`.

#### 3. What is `StringBuilder`?

To create strings, it is best to use a special mechanism [`StringBuilder`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/), 
which allows you to construct a long string from parts.
It also allows you to avoid copying the whole string each time it is updated, for example:
```kotlin
var myString = "It"
myString += "is"
myString += "string"
```
In this case, we will create a string with the text `It`, next – create it again, but with the additional text `is`, and so on.
This affects the execution time of the program, as well as the amount of computer resources it will 
consume.

`StringBuilder` is designed in such a way that it avoids this kind of copying. 
First, you should create a string, then apply the `append` function to update it, 
and finally, use the `toString` function to return the resulting string:
```kotlin
val sb = StringBuilder()
sb.append("It")
sb.append("is")
sb.append("string")
sb.toString()
```
___

### Task

**Description**: implement the `applyBordersFilter` function. 
For the border symbol, please use the pre-defined variable `borderSymbol`.

To make the picture prettier, add a separator between the picture and the border.
For the separator, please use the pre-defined variable `separator`.
Note that the picture might not be a square, which means the width of different lines in the picture can be different.
In other words, you need to pad the shorter lines with the `separator` to the length to make the image square.

In addition, the project already stores the `newLineSymbol` variable, which can be used to split the picture into lines.

Here's an example of the function's work:
<p>
    <img src="../../utils/src/main/resources/images/part1/almost.done/when_hint_1.png" alt="Example of the function's work" width="200"/>
</p>


If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Several examples how applyBordersFilter function should work">

First example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/borders/android.png" alt="Example of the function's work" width="200"/>

Second example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/borders/monkey.png" alt="Example of the function's work" width="200"/>

</div>


<div class="hint" title="Help with determining the width of a picture">
  To define the picture width, you can use the pre-defined function <code>getPictureWidth</code>, which accepts a picture.
  
  If you want, you can try to implement it by yourself: split the picture by using <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/lines.html"><code>lines</code></a> function 
  and next use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/max-of-or-null.html"><code>maxOfOrNull</code></a> function.   
</div>

<div class="hint" title="The length of the top and bottom edges">
    The length of the top and bottom edges of the border will be 4 characters longer than the width of the initial picture, 
    since we add the <code>borderSymbol</code> and the <code>separator</code> on both sides of the image.
</div>

<div class="hint" title="Pre-defined variable to check your code">
   To check how your function works, you can run it in <code>main</code> by passing the pre-defined variable <code>simba</code>.
</div>
