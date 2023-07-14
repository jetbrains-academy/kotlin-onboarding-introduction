### Theory

1. The `random` function
2. How to create a new list with random elements
3. The `joinToString` function

Congratulations! The basic version of the game is ready! 
But it's not interesting to play it if the hidden word is always the same, is it?

#### 1. The `random` function

How can you generate a word? 
For example, you can specify a possible alphabet 
(a list of characters that can be used in the word) 
and **randomly** select the characters the desired number of times.

To do that, you can use a special function [`random`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/random.html), 
which works with a _list_ (or with a _string_, which, as we have already found out, 
can be represented as a list):

```kotlin
// Return any single symbol from the string "ABCD"
"ABCD".random()
```

#### 2. How to create a new list with random elements

To get a _list of random elements_, you need to create a list of the desired number 
of elements, specifying in a _lambda expression_ (the condition) 
how each element will be generated:
```kotlin
// Create a list with 5 elements, each of them is 6
List(5) { 6 }
// Create a list with 5 elements, each of them is 'A'
List(5) { 'A' }
// Create a list with 5 elements, each of them is a random symbol from the string "ABCD"
List(5) { "ABCD".random() }
```

#### 3. The `joinToString` function

To join a list of elements into a string, 
you can use the [`joinToString`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/join-to-string.html) 
function, passing as an argument the _separator_ - a character (or rather a _string_) 
that will be used to separate the elements:
```kotlin
// Separator is "; ", the resulting string is: "6; 6; 6; 6; 6"
List(5) { 6 }.joinToString("; ")
// The default separator is ", ", the resulting string is: "A, A, A, A, A"
List(5) { 'A' }.joinToString()
```

___

### Task

**Description**: implement the `generateSecret` function to return a random secret for the game:
- add new arguments: `wordLength` and `alphabet`;
- implement the body instead of always returning `ABCD`;
- add information about the alphabet to the `getGameRules` function with the following sentence:
` The possible symbols in the word: <alphabet>`:
```text
Welcome to the game! 

Two people play this game: one chooses a word (a sequence of letters), the other guesses it. In this version, the computer chooses the word: a sequence of <wordLength> letters (for example, <secretExample>). The user has several attempts to guess it (the max number is <maxAttemptsCount>). For each attempt, the number of complete matches (letter and position) and partial matches (letter only) is reported. The possible symbols in the word: <alphabet>. 

For example, with <secretExample> as the hidden word, the BCDF guess will give 1 full match (C) and 1 partial match (B).

```

Also, you need to define a new variable `alphabet` in the `main` function and initialize it with the `ABCDEFGH` value.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="The separator in the secret">
  Don't forget to use the right separator. In our case, the answer should be like "ABCD".
</div>
