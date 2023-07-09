On this step you need to implement the `canvasGenerator` function, which accepts the `pattern`, `width`, and `height` that were inputted by the user.
This function should return a new string with a generated canvas picture.

In this project, you can use the already implemented functions and variables:

- the `newLineSymbol` variable, which stores a new line symbol;
- the `getPatternWidth` function, which accepts a pattern and calculates its width;

The `canvas` generator should build a rectangle `width` x `height` from the pattern.
The generator works according to the following algorithm:
1) The **first** level of the generated image does **not change** the pattern;
2) Each subsequent level in the generated image removes the top line from the pattern,
   but only if the pattern has more than one line;
3) When repeated **horizontally**, the pattern remains **unchanged**.

<div class="hint" title="The `canvas` filter examples">
  For example, consider the following pattern:

```text
 X
/ \
\ /
 X
```

The resulting 5 x 3 picture will be:

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

The first line of the initial pattern was removed from the second and further levels.
The wrong example shows image generation without removing the top line in the second and third levels.

However, if the size is 5 x 1, the resulting picture will be:

```text
            CORRECT:             INCORRECT:
 
            X  X  X  X  X         X  X  X  X  X
1st level: / \/ \/ \/ \/ \       / \/ \/ \/ \/ \
           \ /\ /\ /\ /\ /       \ /\ /\ /\ /\ /
            X  X  X  X  X 
```

In this case, we kept the first line because, according to the first point of the algorithm,
we don't need to change the first level of the generated picture.
</div>

The following functions will _not be checked_, but they can help you implement this project:
- `dropTopFromLine`, which accepts a line (any string, can be multi-row) and deletes the first line,
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

- `repeatHorizontally`, which accepts a `pattern` and the number of times it should be repeated horizontally (n), e.g.:
```text
n = 5
○○             ○○○○○○○○○○
○○    ---->    ○○○○○○○○○○
```
