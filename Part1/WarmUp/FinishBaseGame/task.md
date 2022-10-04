### Theory

To use complex conditions in `if` expressions, 
special [built-in operations](https://kotlinlang.org/docs/basic-types.html#booleans) `||` or `&&` can be used:
`||` is true if _at least one_ condition is true;
`&&` is true if _all_ conditions are true:
```kotlin
// Will be true if x > 5 OR y > 5, e.g., x = 3, y = 6 (true) or x = 6, y = 6 (true)
if (x > 5 || y > 5) {
    TODO("Not implemented yet")
}
```
```kotlin
// Will be true if x > 5 AND y > 5, e.g., for x = 3, y = 6 (false) or x = 6, y = 6 (true)
if (x > 5 && y > 5) {
    TODO("Not implemented yet")
}
```

An `if` expression can have not only two `if..else` branches, but also some intermediate ones:
```kotlin
if (x > 0) {
    TODO("Not implemented yet")
} else if (x == 0) {
    TODO("Not implemented yet")
}
```
Another example: 
```kotlin
if (x > 0) {
    TODO("Not implemented yet")
} else if (x == 0) {
    TODO("Not implemented yet")
} else if (x < 0 && x != 5) {
    TODO("Not implemented yet")
} else {
    TODO("Not implemented yet")
}
```

In addition, if you are working with a loop (whether `for` or `while`), 
you can stop it with a special [`break`](https://kotlinlang.org/docs/returns.html) expression.
___

### Task

**Description**: finish the game:
- Implement the function `printRoundResults` to 
print the number of full matches and the number of partial matches in the current round.
It should accept two arguments - `secret` and `guess`. 
The printed text should be the following:
```text
Your guess has <positionalMatchings> full matches, and <lettersMatchings> partial matches.
```
- Add a variable that counts the number of user's attempts.
- Add a function `isWin` that accepts three arguments: `complete`, `attempts`, and `maxAttemptsCount`
and returns `true` only if the user guessed the word and spent _not more_ than `maxAttemptsCount` attempts.
- Add a function `isLost` that accepts three arguments: `complete`, `attempts`, and `maxAttemptsCount`
and returns `true` only if the user did not guess the word and spent _more_ than `maxAttemptsCount` attempts.
- Add a condition to check if the user won or lost: `Sorry, you lost! :( My word is <secret>` and `Congratulations! You guessed it!`
