Let's do our game more fun!

### Task

Implement the `generateSecret` function to return a random secret for the game:
- add new arguments: `wordLength` and `alphabet`;

<div class="hint" title="Push me to see the new signature of the generateSecret function">

The signature of the function is:
```kotlin
fun generateSecret(wordLength: Int, alphabet: String): String
```
</div>

- implement the body that generates a random word with `wordLength` letters from the `alphabet` 
instead of always returning `ABCD`.

**Note**, you need to define a new variable `alphabet` in the `main` function and initialize it with the `ABCDEFGH` value to pass tests.
Don't forget to use `wordLength` and `alphabet` variables when calling the `generateSecret` function.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="Hint" title="Push me to learn how to implement the generateSecret function">

You can create a new list with `wordLength` elements with _random_ letters from `alphabet`.
Finally, you can use `joinToString` function with an empty separator (`""`) to build the final string.
</div>
