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
 X  X  X  X  X 
/ \/ \/ \/ \/ \
\ /\ /\ /\ /\ /
 X  X  X  X  X 
/ \/ \/ \/ \/ \
\ /\ /\ /\ /\ /
 X  X  X  X  X 
/ \/ \/ \/ \/ \
\ /\ /\ /\ /\ /
 X  X  X  X  X 
```
</div>

The `canvasGaps` generator should build a rectangle `width` x `height` from the pattern,
but remove interleaved elements.

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
 X     X     X 
/ \   / \   / \
\ /   \ /   \ /
 X     X     X 
    X     X    
   / \   / \   
   \ /   \ /   
    X     X    
 X     X     X 
/ \   / \   / \
\ /   \ /   \ /
 X     X     X 
```
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

TODO: add hints