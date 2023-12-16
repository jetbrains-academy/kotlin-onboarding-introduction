In the nest two tasks we will implement functions to be able to check the user input if it is correct.

### Task

Implement the `isCorrectInput` function, that accepts a string `userInput`
and checks if it is correct: 1) the length of the `userInput` is 1 and 2) the `userInput` is an english letter.
If `userInput` is correct, the function  returns `true`, and `false` otherwise.

<div class="hint" title="Push me to see the new signature of the getHiddenSecret function">

The signature of the function is:
```kotlin
fun isCorrectInput(userInput: String): Boolean
```
</div>

This function should have the following behavior:
- inform the user if the length of the input is incorrect:
  ```text
  The length of your guess should be 1! Try again!
  ```

- inform the user if the input it not an english letter:
  ```text
    You should input only English letters! Try again!
    ```

**Note**: to avoid typos, just copy the text from here and paste it into your code.

You can implement this function in any way you choose, but we _recommend_ looking into the [`isLetter`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/is-letter.html) built-in function.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="Hint" title="Push me to learn how to check if the size of the userInput is incorrect">

You can use `length` to get the number of letters in `userInput`:
```kotlin
val size = userInput.length
```
Then, you need to compare it with `1`.
</div>

<div class="Hint" title="Push me to learn more about the isLetter built-in function">

The built-in function <a href='https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/is-letter.html'>`isLetter`</a> checks if the passed symbol 
is an English letter and can be applied only to one letter at once:
```kotlin
println("AB12"[0].isLetter()) // true, since `A` is an english letter
println("AB12"[1].isLetter()) // true, since `B` is an english letter
println("AB12"[2].isLetter()) // true, since `1` is NOT an english letter
println("AB12"[3].isLetter()) // true, since `2` is NOT an english letter
```
</div>
