In previous assignments, we sometimes used the pre-defined `safeReadLine` function instead of built-in `readlnOrNull`.
The main reason is that `readlnOrNull` returns a _nullable_ value (`String?`).
The pre-defined `safeReadLine` function processed the input received from the user with the Elvis operator:
it returns the string or throws an error if the `null` value was received.
It's time to implement this function by yourself!

### Task

Implement the `safeReadLine` function, which returns the string the user inputs or throws an error
if the `null` value was received.

<div class="hint" title="Click me to see the signature of the safeReadLine function">

The signature of the function is:
```kotlin
fun safeReadLine(): String
```
</div>
