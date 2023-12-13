It's time to apply implemented functions and finish the game!

### Task

Replace `safeReadLine` function inside the `playGame` function with implemented 
on the previous step `safeUserInput` function.
Since the `safeUserInput` function requires `alphabet: String` argument, don't forget update the signature of 
the `playGame` function.

<div class="hint" title="Push me to see the new signature of the playGame function">

The signature of the function is:
```kotlin
fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int, alphabet: String): Unit
```
</div>

Finally, don't forget to use the `alphabet` argument inside the main function when you call the `playGame` function.

Good luck!

<div class="hint" title="Push me to see the final version of the game">

![The game's example](../../utils/src/main/resources/images/part1/warmup/game.gif "The game's example")

</div>
