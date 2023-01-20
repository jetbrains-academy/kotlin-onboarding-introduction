On this step you need to implement the core logic of the game. 

The tests for the tasks will be aimed at checking the following functions:

- `generateNewUserWord`, which generates a new sequence of underscores and already guessed letters
  by means of `secret`, `guess`, and `currentUserWord`. The `currentUserWord` must be stored with `separator`s, e.g., `B _ N _`;
- `isComplete`, which checks if the game is complete.

You can use the already defined variable `separator` that stores a space.
You just need to call it.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints


<div class="hint" title="Help with the `generateNewUserWord` function">

To implement the `generateNewUserWord` function, you should check each `secret`'s char.
To make a loop check over each `secret`'s char, you can use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/indices.html">`indices`</a> property:
   ```
   for (i in secret.indices) { ... }
   ```
It is the same as:
   ```
   for (i in 0 until secret.length) { ... }
   ```

Next, if the current `secret`'s char matches the `guess`'s char in the same position,
add the respective `secret`'s char to `newUserWord`; otherwise, add the `currentUserWord`'s char in the `i * 2` position.
Also, don't forget to add a separator at the loop's step.

Note, we use the `i * 2` position, since `currentUserWord` contains not only symbols
but also separators and thus has twice as many characters as `secret`.
</div>

<div class="hint" title="Help with the `isComplete` function">

To implement the `isComplete` function, it is not enough to check that `secret` and `currentGuess` match.
The main reason is that `currentGuess` has separators, while `secret` does not.
</div>