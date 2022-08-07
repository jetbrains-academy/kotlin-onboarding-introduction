### Theory

You are already familiar with the concept of variable from the last lesson. 
However, all this time you were only working with `string` variables.
However, in programming, it is common to use variables of _different_ types.

For example, `String` type stores strings like `"ABCD"`, 
and `Int` type stores integer numbers like `55`.
In addition, you should pay attention to 
the special type [`Boolean`](https://kotlinlang.org/docs/basic-types.html#booleans), 
which can only have two values - `true` (yes) or `false` (no).

Variable types differ in their functionality, for example, 
if we add two numbers `2` and `3`, we get a new number `5`. 
However, if we represent them as strings, we get `"2" + "3" = "23"`.
You can find a complete list of base types in the [official documentation](https://kotlinlang.org/docs/basic-types.html).

___

### Task

**Description**: Add variables of different types to customize the game 
and display this information on the console:
- `Int` variable `wordLength` to define the length of the word
- `Int` variable `maxAttemptsCount` to define the max numbers of user attempts
- `String` variable `secretExample` to define an example of the hidden word (to explain it to user)

You can initialize these variables with any value you like in the future, but currently lets define them as follows:
```text
wordLength with value 4
maxAttemptsCount with value 3
secretExample with value ACEB
```

Also, display the game rules to the console:
```text
Welcome to the game! 

Two people play this game, one guesses a word (a sequence of letters), the other guesses it. In this case, the computer guesses the word. A sequence of <wordLength> letters is guessed (for example, <secretExample>). Several attempts are given to guess it (max number is <maxAttemptsCount>). For each attempt, the number of complete matches (letter and position) and partial (letter only) is reported. 

For example, for a BCDF guess (with <secretExample> guessed) there will be 1 full match (C), 1 partial match (B).
```