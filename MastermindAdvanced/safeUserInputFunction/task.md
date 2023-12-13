In this task we will implement the `safeUserInput` function to help user to input only correct inputs.

### Task

Implement the `safeUserInput` function
which accepts `wordLength` and `alphabet`
and handles incorrect user input.

<div class="hint" title="Push me to see the new signature of the safeUserInput function">

The signature of the function is:
```kotlin
fun safeUserInput(wordLength: Int, alphabet: String): String
```
</div>

- before reading the user input, print the requirements:

```text
Please input your guess. It should be of length <wordLength>, and each symbol should be from the alphabet: <alphabet>.
```

**Note**: to avoid typos just copy the text from here and paste into your code.

- to read the line of user input, use the `safeReadLine` function as earlier (or your own implementation of it);
- to check the correctness of the user's input, implement use the `isCorrectInput` function that was implemented om the previous step.

Here's an example of the `safeUserInput` function's work:

![The safeUserInput example](../../utils/src/main/resources/images/part1/warmup/safe_user_input.gif "The safeUserInput example")

In order for the picture to fit, additional line breaks were added.
You don't need to add them when solving the task.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints
