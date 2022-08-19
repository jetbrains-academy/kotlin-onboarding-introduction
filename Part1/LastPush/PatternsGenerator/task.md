Hello! This lesson focuses on the topics that you covered in the last lesson.
The main difference is that the final project will not be divided into intermediate stages,
and you can try to implement it yourself from scratch.
We have no doubt that you will succeed!

----

The project of this lesson is **Patterns generator**.
The purpose of this project is to create an application 
for automatically generating character images of a given size by a user pattern.

Firstly, you need to ask the user:
```text
Do you want to use a pre-defined pattern or use a custom one?
Please, input 'yes' for a pre-defined pattern or 'no' for a custom one
```

You need to handle the user's answer and ask the question again, if the answer is incorrect.
If the user wants to use a pre-defined pattern, you just need to ask to input it.
If the user want to use a pre-defined pattern, 
you need to ask to choose on of the pre-defined patterns. 
You can get a list with all of them to call the already defined `allPatterns` function.

Secondly, you need to ask the user to choose the generator: `canvas` or `canvasGaps`.
And finally, to ask the user input the `width` and `height` of the generated picture.


The `canvas` generator should build a rectangle `width` x `height` from the pattern.
The generator works according to the following algorithm:
1) The **first** level of the generated image does **not change** the pattern;
2) Each subsequent level in the generated image removes the top line from the pattern, 
but only if the pattern has more than one line;
3) When repeated **vertically**, the pattern remains **unchanged**.

<div class="hint">
  For example, for the pattern:

```text
 X
/ \
\ /
 X
```

and size 5 x 3 the resulting picture will be:

```text
            CORRECT:             INCORRECT:

            X  X  X  X  X         X  X  X  X  X 
1st level: / \/ \/ \/ \/ \       / \/ \/ \/ \/ \
           \ /\ /\ /\ /\ /       \ /\ /\ /\ /\ /
            X  X  X  X  X         X  X  X  X  X 
2nd level: / \/ \/ \/ \/ \        X  X  X  X  X
           \ /\ /\ /\ /\ /       / \/ \/ \/ \/ \
            X  X  X  X  X        \ /\ /\ /\ /\ / 
3rd level: / \/ \/ \/ \/ \        X  X  X  X  X 
           \ /\ /\ /\ /\ /        X  X  X  X  X 
            X  X  X  X  X        / \/ \/ \/ \/ \ 
                                 \ /\ /\ /\ /\ / 
                                  X  X  X  X  X
```

The first line of the initial pattern was removed from the second and next levels.
The wrong example shows image generation without removing top line on the second and third levels.

But if the size will be 5 x 1 the resulting picture will be:

```text
            CORRECT:             INCORRECT:
 
            X  X  X  X  X         X  X  X  X  X
1st level: / \/ \/ \/ \/ \       / \/ \/ \/ \/ \
           \ /\ /\ /\ /\ /       \ /\ /\ /\ /\ /
            X  X  X  X  X 
```

In this case we kept the first line because according to the first point of the algorithm 
we don't need to change the first level of the generated picture.
</div>

The `canvasGaps` generator should build a rectangle `width` x `height` from the pattern,
but remove interleaved elements.
The generator works according to the following algorithm:
1) **All levels** of the generated image does **not change** the pattern;
2) Gap is equal to a rectangle of spaces, the dimensions 
of which are equal to the width and height of the initial pattern;
3) In every **odd level**, the gap should be in **even** positions, 
and in every **even level** - in **odd**;
4) When repeated **vertically**, the pattern remains **unchanged**.

<div class="hint">
  For example, for the pattern:

```text
 X
/ \
\ /
 X
```

and size 5 x 3 the resulting picture will be:

```text
            CORRECT:             INCORRECT: 
            
            X     X     X         X     X     X 
1st level: / \   / \   / \       / \   / \   / \
           \ /   \ /   \ /       \ /   \ /   \ /
            X     X     X         X     X     X
               X     X              / \   / \
2nd level:    / \   / \             \ /   \ /    
              \ /   \ /              X     X
               X     X           / \   / \   / \
            X     X     X        \ /   \ /   \ /    
3rd level: / \   / \   / \        X     X     X 
           \ /   \ /   \ /
            X     X     X 
```

All levels of the generated image does not change the pattern.
</div>

In this project you can use the already implemented functions and variables:
- `separator` variable, that stores a space;

- `newLineSymbol` variable, that stores a new line symbol;
- `getPatternWidth` function, that accepts the pattern and calculates its width;
- `getPatternByName` function, that accepts a pattern name and return the pattern if it exists or `null`;
- `allPatterns` function, that return names of all pre-defined patterns.

Tests of the tasks will be aimed at checking the following _six_ functions:
- `fillPatternRow`, that accepts a `patternRow` (one line from the pattern) and `patternWidth`
and fill the row `separator` to enlarge it to `patternWidth` size;

- `getPatternHeight`, that accepts a `pattern` and calculates its height;
- `canvasGenerator`, that accepts a `pattern`, a `width` and a `height` that were inputted by the user. 
This function should return a new string with a generated canvas picture.
- `canvasWithGapsGenerator`, that accepts a `pattern`, a `width` and a `height` that were inputted by the user.
This function should return a new string with a generated canvas with gaps picture.
- `applyGenerator`, that accepts a `pattern`, a `generatorName`, a `width` and a `height` that were inputted by the user.
This function should to call the necessary generator a return a generated picture.
- `getPattern`, that asks the user about choosing a pre-defined pattern or ask to input a custom one.

Also, the `main` function will be checked for the correctness.

These functions will _not be checked_, but can help you to implement this project:
- `dropTopFromLine`, that accepts a line (any string, can be multi-row) and delete the first line,
  e.g.:
```text
   .+------+                 
 .' |    .'|                .' |    .'|
+---+--+'  |    ----->     +---+--+'  |
|   |  |   |               |   |  |   |
|  ,+--+---+               |  ,+--+---+
|.'    | .'                |.'    | .' 
+------+'                  +------+'
```

- `repeatHorizontally`, that accepts a `pattern` and how many times it should be repeated horizontally (5), e.g:
```text
n = 5
○○             ○○○○○○○○○○
○○    ---->    ○○○○○○○○○○
```

- `repeatHorizontallyWithGaps`, that accepts a `pattern` and how many times it should be repeated horizontally (n), e.g:
```text
n = 5
○○             ○○  ○○  ○○
○○    ---->    ○○  ○○  ○○
```


An example of the game: **TODO: add a gif**

<div class="hint">

  To convert the user input into <code>Int</code> (to get width and height), 
  you can use <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/to-int.html"><code>toInt</code></a> function, e.g.:
  ```kotlin
  val width = safeReadLine().toInt()
  ```
  or <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/to-int-or-null.html"><code>toIntOrNull</code></a> to parse it more safely, e.g.:
  ```kotlin
    val width = safeReadLine().toIntOrNull() ?: error("Incorrect number!")
  ```
</div>

<div class="hint">

  To drop <code>n</code> last symbols from <code>String</code> you can use <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/drop-last.html"><code>dropLast</code></a> function, e.g.:
  ```kotlin
  val a = "MyText"
  println(a.dropLast(3)) // MyT
  ```
  If you need to drop <code>n</code> symbols from the beginning of a <code>String</code>, you can use <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/drop.html"><code>drop</code></a> function.
</div>

<div class="hint">

  To generate a string that consist of the repeating some symbols you can use the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/repeat.html"><code>repeat</code></a> function, e.g.:
  ```kotlin
  println("a".repeat(5)) // aaaaa
  ```
</div>


<div class="hint">
  To implement the <code>fillPatternRow</code> function, you need just to check 
  if the length of the current pattern row is less than pattern width. 
  If it is true, add the necessary number of spaces in the end of the row.
</div>

<div class="hint">
  The pattern height can be calculated as a number of the rows in the pattern.
</div>

<div class="hint">
  In industrial programming, code duplicates is best avoided and put into functions. 
  However, it is not always possible to write clear and readable code the first time. 
  Try to implement the <code>canvasGenerator</code> and <code>canvasWithGapsGenerator</code> functions 
  so that they pass all tests, and then start to improve the code. 
  This process is called <i>refactoring</i>.
</div>

<div class="hint">
  Don't forget to handle input errors in the <code>getPattern</code> function, 
  as well as when the user tries to enter the name of the generator (<code>canvas</code> or <code>canvasGaps</code>). 
  The program should not crash on invalid input, but should inform the user of the error and ask them 
  to try again.
</div>
