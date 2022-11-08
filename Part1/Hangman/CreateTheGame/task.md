Hello! This lesson focuses on the topics that you covered in the previous lesson.
The main difference is that the final project will not be divided into intermediate stages
and you can try to implement it yourself from scratch.
We have no doubt that you will succeed!

----

<p align="center">
    <img src="../../../utils/src/main/resources/images/part1/Hangman/game.png" alt="Hangman" width="400"/>
</p>

The project of this lesson is **Hangman**.
The purpose of this game is to guess the word chosen by the computer.
You can try to implement the whole game yourself; however, if you need help, 
refer to the hints.

The game should interact with the user through the console. The initial message will make the game's rules clearer:
```text
Welcome to the game!

In this game, you need to guess the word chosen by the computer.
The hidden word will appear as a sequence of underscore characters, one per letter.
You have <maxAttemptsCount> attempts to guess the word.
All words are English words, consisting of <wordLength> letters.
At each attempt, you should enter one letter; 
if it is present in the hidden word, all matches will be displayed.

For example, if the word CAT was chosen by the computer, _ _ _ will be displayed first,
since the word has 3 letters.
If you enter the letter A, you will see _ A _, and so on.

Good luck with the game!
```

The word must be hidden exactly in the `_ ` format per each letter. 
The necessary symbols already exist in the project and are stored in the variables `separator` and `underscore`. 
You just need to call them.

In addition, the variables `wordLength` and `maxAttemptsCount` are also already defined in the project.
The word that the computer chooses must be randomly selected from a list of words `words`. 
This list is also already defined in the project.

The tests for the tasks will be aimed at checking the following functions:

- `isCorrectInput`, which checks if the user input is one English letter;
- `safeUserInput`, which uses `isCorrectInput` and also converts the input to uppercase;
- `generateNewUserWord`, which generates a new sequence of underscores and already guessed letters 
by means of `secret`, `guess`, and `currentUserWord`;
- `isComplete`, which checks if the game is complete.

At the end of the game, the user should be informed about the results:
- if the user lost: `Sorry, you lost! My word is <secret>`
- if the user guessed the word: `Congratulations! You guessed it!`

An example of this game:

![The game's example](../../../utils/src/main/resources/images/part1/Hangman/game.gif "The game's example")

In order for the picture to fit, additional line breaks were added.
You don't need to add them when solving the task.

<div class="hint">

Different operating systems use different line break characters.
Use the predefined variable `newLineSymbol` with a newline character instead of `\n` to
separate lines correctly.
</div>

<div class="hint">

  To implement the `isCorrectInput` function, you should check two conditions. 
  The first condition should check if the `length` of the user input is one character. 

  The second condition should check if the English alphabet is used in the input.
  To check it, the built-in function <a href='https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/is-letter.html'>`isLetter`</a> can be used for a char.
  If you find that the length of the input entered by the user is equal to one, 
  then you can use the character at position zero and check if the character is an English letter.
</div>

<div class="hint">

   To implement the `safeUserInput` function, you should ask the user to input the string 
   again in case it is not correct (if `isCorrectInput` does not return `true`). 
   To make the string uppercase, you can use the built-in function <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/uppercase.html">`uppercase`</a>.
</div>

<div class="hint">

   To implement the `generateNewUserWord` function, you should check each `secret`'s char.
   To make a loop check each `secret`'s char, you can use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/indices.html">`indices`</a> property:
   ```
   for (i in secret.indices) { ... }
   ```
   It is the same as:
   ```
   for (i in 0 until a.length) { ... }
   ```
   
   Next, if the current `secret`'s char matches the `guess`'s char in the same position, 
   add the respective `secret`'s char to `newUserWord`; otherwise, add the `currentUserWord`'s char in the `i * 2` position.
   Also, don't forget to add a separator at the loop's step.

   Note, we use the `i * 2` position, since `currentUserWord` contains not only symbols 
   but also separators and thus has twice as many characters as `secret`.
</div>

<div class="hint">

   To implement the `isComplete` function, it is not enough to check that `secret` and `currentGuess` match.
   The main reason is that `currentGuess` has separators, while `secret` does not.
</div>
