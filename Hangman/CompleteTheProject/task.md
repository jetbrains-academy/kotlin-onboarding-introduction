On this step you need to finish the game and implement interactions with the user.

<div class="hint" title="The game's example">

![The game's example](../../utils/src/main/resources/images/part1/hangman/game.gif "The game's example")

</div>

The game should interact with the user through the console. The initial message will make the game's rules clear:
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
For example, the word `BANK` will be `_ _ _ _`, where `_` is stored in the `underscore` variable and the space in the `separator` variable.

In addition, the variables `wordLength` and `maxAttemptsCount` are also already defined in the project.
The word that the computer chooses must be randomly selected from a list of words `words`.
This list is also already defined in the project.

The tests for this step will be aimed at checking the following functions:

- `isCorrectInput`, which checks if the user input is one English letter;
- `safeUserInput`, which uses `isCorrectInput` and also converts the input to uppercase;

Also, the `main` function will be checked - you need to print the game rules and next start a game round.

At the end of the game, the user should be informed about the results:
- if the user lost: `Sorry, you lost! My word is <secret>`
- if the user guessed the word: `Congratulations! You guessed it!`

<div class="hint" title="Messages for the game">

The tests check only the functions and string constants described above,
but the game's example has several additional messages, e.g., about incorrect input.
If you would like to make the game better, you can add them, the full list of the messages is below:

- `Please input your guess.`
- `The length of your guess should be 1! Try again!`
- `You should input only English letters! Try again!`
- `Sorry, the secret does not contain the symbol: <guess>. The current word is: <currentUserWord>`
- `Great! This letter is in the word! The current word is: <newUserWord>`

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Line breaks in different OS">

Different operating systems use different line break characters.
Use the predefined variable `newLineSymbol` with a newline character instead of `\n` to
separate lines correctly.
</div>

<div class="hint" title="Help with the `isCorrectInput` function">

To implement the `isCorrectInput` function, you should check for two conditions.
The first condition should check if the `length` of the user input is one character.

The second condition should check if the English alphabet is used in the input.
To check it, the built-in function <a href='https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/is-letter.html'>`isLetter`</a> can be used for a char.
If you find that the length of the input entered by the user is equal to one,
then you can use the character at position zero and check if the character is an English letter.
</div>

<div class="hint" title="Help with the `safeUserInput` function">

To implement the `safeUserInput` function, you should ask the user to input the string
again in case it is not correct (if `isCorrectInput` does not return `true`).
To make the string uppercase, you can use the built-in function <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/uppercase.html">`uppercase`</a>.
</div>

