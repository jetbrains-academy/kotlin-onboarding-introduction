### Theory

To work with multiline strings, you can break them into lines and work with each one separately. 
This can be done using the [`split`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/split.html) function, 
passing as an argument the character at which to split the string (in this case, the newline character):
```kotlin
"""First line
Second line
""".split("\n")
```
will be converted into a list with two strings: `First line` and `Second line`.

Also, to generate a string by repeating a symbol, you can use the [`repeat`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/repeat.html) function:
```kotlin
"@".repeat(4)
```
will generate the following string: `@@@@`.

To create strings, it is best to use a special mechanism [`StringBuilder`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/) 
that allows you to construct a large string from parts.
It avoids copying the whole string each time it is updated, for example:
```kotlin
var myString = "It"
myString += "is"
myString += "string"
```
in this case, we will create a string with text `It`, next create it again, but with the addition text `is` an so on.
This affects the execution time of the program, as well as the amount of resources it will 
consume on the computer.

`StringBuilder` is designed in such a way that it avoids this kind of copying. 
To use it, you should create it, then use the `append` function to update it, 
and finally use `toString` function to return the final string:
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
For the border symbol please use the pre-defined variable `borderSymbol`.

To make the picture prettier, add a separator between the picture and the border.
For separator, please use the pre-defined variable `separator`.

In addition, the project already stores the `newLineSymbol` variable that can be used to split the picture into lines.

An example of the working this function:
```text
// The initial image:
┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈
▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈
▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈
┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈
┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈
┈┈▕╲▂┊╰━━┻━╱┈┈┈
┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈

// The final image:
###################
# ┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈ #
# ▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈ #
# ▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈ #
# ┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈ #
# ┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈ #
# ┈┈▕╲▂┊╰━━┻━╱┈┈┈ #
# ┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈ #
###################
```

<div class="hint">
  To define the picture width tou can use the pre-defined function <code>getPictureWidth</code>, that accepts a picture.
  
  If you want, you can try to implement it by yourself: split the picture by <code>newLineSymbol</code>, 
  and next use <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/max-of-or-null.html"><code>maxOfOrNull</code></a> function.   
</div>

<div class="hint">
    The length of the top and bottom edges of the border will be more than the width of the initial picture by 4, 
    since we add the <code>borderSymbol</code> and the <code>separator</code> on both sides to the width of the image.
</div>

<div class="hint">
   To check how works your function you can run it in <code>main</code> by passing the pre-defined variable <code>simba</code>
</div>
