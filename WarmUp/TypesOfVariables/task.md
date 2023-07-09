### Theory

You are already familiar with the concept of variable from the previous lesson. 
However, you've been only working with `String` variables so far.
Meanwhile, in programming, it is common to use variables of _different_ types.

For example, the `String` type stores strings, like `"ABCD"`, 
and the `Int` type stores integer numbers, like `55`.
In addition, you should pay attention to 
the special type [`Boolean`](https://kotlinlang.org/docs/basic-types.html#booleans), 
which can only have two values - `true` (yes) or `false` (no).

Variable types differ in their functionality. For example, 
if we add two numbers `2` and `3`, we get a new number `5`. 
However, if we represent them as strings, we get `"2" + "3" = "23"`.
You can find a complete list of base types in the [official documentation](https://kotlinlang.org/docs/basic-types.html).

___

### Task

**Description**: Add variables of different types to customize the game 
and display the game information in the console:
- `Int` variable `wordLength` to define the length of the word
- `Int` variable `maxAttemptsCount` to define the max number of user attempts
- `String` variable `secretExample` to define an example of the hidden word (to explain it to the user)

You can later initialize these variables with any value you like, but currently, let's define them as follows:
```text
wordLength with value 4
maxAttemptsCount with value 3
secretExample with value ACEB
```

Also, display the game rules to the console:
```text
Welcome to the game! 

Two people play this game: one chooses a word (a sequence of letters), the other guesses it. In this version, the computer chooses the word: a sequence of <wordLength> letters (for example, <secretExample>). The user has several attempts to guess it (the max number is <maxAttemptsCount>). For each attempt, the number of complete matches (letter and position) and partial matches (letter only) is reported. 

For example, with <secretExample> as the hidden word, the BCDF guess will give 1 full match (C) and 1 partial match (B).
```

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Line breaks in different OS">

  Different operating systems use different line break characters. 
  Use the predefined variable `newLineSymbol` with a newline character instead of `\n` to 
  separate lines correctly.
</div>
