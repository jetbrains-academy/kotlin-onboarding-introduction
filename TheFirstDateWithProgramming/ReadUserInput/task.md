### Theory

We have previously talked about the _built-in_ functions in Kotlin. 
In addition to the already familiar functions for output to the console, 
Kotlin has the [`readlnOrNull`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/readln-or-null.html) function, which reads the values entered by the user and stores them in a variable:

```kotlin
val myValue = readlnOrNull()
// The myValue variable stores the user's input
```

For example, in our **Story twister** project, 
we need to ask the user a question and then remember their answer. 
That is exactly what the `readlnOrNull` function is for. 
The function tells the program to stop and wait for 
the user to enter some value into the console; then it allows the program to receive the value and, for example, write it into a variable.

It is important that in this case you **cannot** write:
```kotlin
val myValue: String = readlnOrNull()
// The myValue variable stores the user's input
```
It is connected to the null safety in Kotlin, we will consider it later, but if you are interested in this topic, you can read more in the [documentation](https://kotlinlang.org/docs/null-safety.html).
___

### Task

**Description**: ask the user three questions and add the answers 
to the _firstUserAnswer_, _secondUserAnswer_, and _thirdUserAnswer_ variables respectively.
The questions are:

What is TROTEN?

How did you spend your graduation?

Why does a spider need eight legs?

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Clarification">

As a result, the user's interaction with the game will look like this:

![User interaction example](../../utils/src/main/resources/images/part1/first.date/user_input.gif "User interaction example")

</div>
