### Theory

We have previously talked about the _built-in_ functions in Kotlin. 
In addition to the already familiar functions for output to the console, 
Kotlin has the [`readLine`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/read-line.html#readline) function for 
reading values into a variable that the user enters.

For example, in our **Poetry generator** project, 
we have to ask the user a question and then remember his answer. 
This is exactly what the `readLine` function is for. 
If the program contains this function, after running it stops and waits for 
the user to enter some value into the console, and then gives the opportunity 
to receive it in the program, and for example, write this value into a variable.

`String` values can contain the values of _other_ variables.
For example, to print some text to console with a value [the string templates](https://kotlinlang.org/docs/basic-types.html#string-templates) can be used:
```kotlin
val firstAnswer = "my text"
println("The value: $firstAnswer")
```
The printed text will be: `The value: my text`.
By other words, the value from the `firstAnswer` variable will be inserted into the string.

In this example the `firstAnswer` variable must be declared **before** using by `$`.
___

### Task

**Description**: ask the user three questions and add the answer 
into _firstUserAnswer_, _secondUserAnswer_, and _thirdUserAnswer_ variables.
The questions are:

(1) What is TROTEN?

(2) How did you spend your graduation?

(3) Why does a spider need eight legs?

<div class="hint">

In the result, the user interaction with the game will look like this:

[//]: # (![User interaction example]&#40;./src/main/resources/images/poetry_generator_mid.gif "User interaction example"&#41;)

</div>