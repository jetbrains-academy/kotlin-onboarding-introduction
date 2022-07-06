### Theory

In Kotlin, as in any other programming language, 
already exist many implemented functions (**built-in** functions). 
You may have noticed one of them in the previous task - `println`. 
It allows you to display the text into the console that is passed as an _argument_. 
The argument, in this case, is needed so that the same function can perform 
the _same_ action with _different_ data.

For example, if you want to display two words `One` and `Two` on different lines, 
then in both cases you need to use the [`println`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/println.html#println) function, but _with different arguments_:
```kotlin
println("One")
println("Two")
```
The output is:
```text
One
Two
```

Kotlin also has another similar function - [`print`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/print.html#print). 
The only difference from `println` is that it does not wrap text to a new line. 
Thus, if we replace the `println` function from the previous example 
with the `print` function, we get the following result:

```kotlin
print("One")
print("Two")
```
The output is:
```text
OneTwo
```

It is **important** to note that the text that needs to be printed to the console 
must be enclosed in _double-quotes_.
___

### Task

**Description**: print the following text using `print` or `println` functions:
```text
Hello! I will ask you several questions. 
Please answer all of them and be honest with me!
```