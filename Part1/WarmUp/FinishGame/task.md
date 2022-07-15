### Theory

So it looks like the game is ready! But what else is left to do?

When writing programs, it is essential to consider _possible_ behaviour 
scenarios and process them. For example, in the current version of the game, 
the user may enter a word that does not match the current game parameters 
(alphabet, word length, and so on), and in this case, it is necessary to process 
this case and inform the user.

As we already know, Kotlin has many built-in functions. 
If you need to check some list (or string) for emptiness, 
you can use [`isEmpty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-empty.html) and [`isNotEmpty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html) built-in functions:
```kotlin
if (someString.length == 0) {
    TODO("Not implemented yet")
}
```
is the same with 
```kotlin
if (someString.isEmpty()) {
    TODO("Not implemented yet")
}
```
or 
```kotlin
if (someString.length != 0) {
    TODO("Not implemented yet")
}
```
is the same with
```kotlin
if (someString.isNotEmpty()) {
    TODO("Not implemented yet")
}
```

As mentioned earlier, complex conditions with multiple branches can be used in Kotin. 
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
equals to:
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

**Description**: implement `safeUserInput` function, that accepts `wordLength` and `alphabet`
and handle incorrect user input:

- before reading the user input, print the requirements: 

```text
Please, input your guess. It should be <wordLength> size and each symbol should be from the alphabet: <alphabet>.
```
- to read line user input use `safeReadLine` function as earlier (or your implementation if it is);
- to check the correctness of the user's input implement `isCorrectInput` function, 
that accepts `userInput`, `wordLength`, and `alphabet` and returns `true`, if the input is correct and `false` otherwise. 
This function should have the following behaviour:
  - inform user if the length of the input is incorrect:
    ```text
    The length of your guess should be <wordLength>! Try again!
    ```
  - inform user if the input contains wrong symbols:
  ```text
    All symbols in your guess should be from the alphabet: <alphabet>! Try again!
    ```
- use these functions in the game process

**TODO: add gif with safe input example**

<div class="hint">
  To check if all symbols in the user's input are correct you 
  can use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter.html"><code>filter</code></a> 
function and check the resulting string with 
  <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-empty.html"><code>isEmpty</code></a> or 
  <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html"><code>isNotEmpty</code></a>
    function.
</div>
