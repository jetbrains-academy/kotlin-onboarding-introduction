### Theory

Well, it looks like the game is ready! But what else is left to do?

0. Why do we need to inform the user about problems?
1. The `isEmpty` function
2. The `isNotEmpty` function
3. How to simplify the `if` operator

#### 0. Why do we need to inform the user about problems?

When writing programs, it is essential to consider _possible_ behaviour 
scenarios and process them. For example, in the current version of the game, 
the user may enter a word that does not match the current game parameters 
(the alphabet, word length, and so on), and if so, it is necessary to process 
the case and inform the user about the problem. 
In this case, the user can fix this problem and continue with the game.

#### 1. The `isEmpty` function

As we already know, Kotlin has many built-in functions. 
If you need to check some list (or string) for emptiness, 
you can use the [`isEmpty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-empty.html) and [`isNotEmpty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html) built-in functions:
```kotlin
if (someString.length == 0) {
    TODO("Not implemented yet")
}
```
It is the same as: 
```kotlin
if (someString.isEmpty()) {
    TODO("Not implemented yet")
}
```

#### 2. The `isNotEmpty` function

Another example: 
```kotlin
if (someString.length != 0) {
    TODO("Not implemented yet")
}
```
It is the same as:
```kotlin
if (someString.isNotEmpty()) {
    TODO("Not implemented yet")
}
```

#### 3. How to simplify the `if` operator

As mentioned earlier, in Kotlin, we can use complex conditions with multiple branches. 
However, if the conditional is used inside a function with `return`, 
the `else` word can be omitted:
```kotlin
fun myFunction(a: Int): String {
    if (a > 0) {
        return "Positive"
    } else if (a == 0) {
        return "Zero"
    } else {
        return "Negative"
    }
}
```
It is equal to:
```kotlin
fun myFunction(a: Int): String {
    if (a > 0) {
        return "Positive"
    }
    if (a == 0) {
        return "Zero"
    }
    return "Negative"
}
```
___

### Task

**Description**: implement the `safeUserInput` function, which accepts `wordLength` and `alphabet`
and handles incorrect user input:

- before reading the user input, print the requirements: 

```text
Please input your guess. It should be of length <wordLength>, and each symbol should be from the alphabet: <alphabet>.
```
- to read the line of user input, use the `safeReadLine` function as earlier (or your own implementation of it);
- to check the correctness of the user's input, implement the `isCorrectInput` function, 
which accepts `userInput`, `wordLength`, and `alphabet` and returns `true` if the input is correct and `false` otherwise. 
This function should have the following behaviour:
  - inform the user if the length of the input is incorrect:
    ```text
    The length of your guess should be <wordLength> characters! Try again!
    ```
  - inform the user if the input contains wrong symbols:
  ```text
    All symbols in your guess should be the <alphabet> alphabet characters! Try again!
    ```
- use these functions in the game process.

Here's an example of the `safeUserInput` function's work:

![The safeUserInput example](../../utils/src/main/resources/images/part1/warmup/safe_user_input.gif "The safeUserInput example")

In order for the picture to fit, additional line breaks were added.
You don't need to add them when solving the task.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Several built-in functions">
  To check if all symbols in the user's input are correct, you 
  can use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter.html"><code>filter</code></a> 
function and check the resulting string with the 
  <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-empty.html"><code>isEmpty</code></a> or 
  <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html"><code>isNotEmpty</code></a>
    function.
</div>
