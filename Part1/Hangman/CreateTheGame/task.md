Hello! This lesson focuses on the topics that you covered in the last lesson.
The main difference is that the final project will not be divided into intermediate stages,
and you can try to implement it yourself from scratch.
We have no doubt that you will succeed!

----

The project of this lesson is **Hangman**.
The purpose of this game is to guess the word that the computer guessed.
You can try to implement the whole game yourself, however, if you need help, 
refer to the hints.

The game should interact with the user through the console. The initial message will make the game's rules clearer:
```text
Welcome to the game!

In this game, you need to guess the word made by the computer.
The hidden word will appear as a sequence of dashes, one dash means one letter.
You have <maxAttemptsCount> attempts to guess the word.
All words are English words, consisting of <wordLength> letters.
Each attempt you should enter any one letter, 
if it is in the hidden word, all matches will be guessed.

For example, if the word CAT was guessed, _ _ _ will be displayed first,
since the word has 3 letters.
If you enter the letter A, you will see _ A _ and so on.

Good luck in the game!
```

The word must be hidden exactly in the `_ ` format for one letter. 
The symbols for them already exist in the project and are stored in variables: `separator` and `dash`. 
You just need to call them.

In addition, the variables `wordLength` and `maxAttemptsCount` are also already defined in the project.
The word that the computer guesses must be randomly selected from a list of words `words`. 
This list is also already defined in the project.

Tests of the tasks will be aimed at checking the following functions:

- `isCorrectInput`, that checks if the user input is a one english letter;
- `safeUserInput`, that uses `isCorrectInput` and also change the input to the uppercase;
- `generateNewUserWord`, that generate a new sequence of the dashes and already guessed words 
by a secret, guess and currentUserWord;
- `isComplete`, that checks if the game is complete

In the end of the game the user should be informed about the results:
- if the user lost: `Sorry, you lost! My word is <secret>`
- if the user guessed: `Congratulations! You guessed!`

An example of the game: **TODO: add a gif**

**TODO: hints**