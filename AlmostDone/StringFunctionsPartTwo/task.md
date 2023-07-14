### Theory

You already know enough about Kotlin to solve this task! 
So let's get straight to the point.

___

### Task

**Description**: implement the `applySquaredFilter` function.
For the border symbol, please use the pre-defined variable `borderSymbol`.

To make the picture prettier, add a separator between the picture and the border.
For the separator, please use the pre-defined variable `separator`.
Note that the picture might not be a square, which means the width of different lines in the picture can be different.
In other words, you need to pad the shorter lines with the `separator` to the length to make the image square.

In addition, the project already stores the `newLineSymbol` variable, which can be used to split the picture into lines.

Here's an example of the function's work:
<p>
    <img src="../../utils/src/main/resources/images/part1/almost.done/when_hint_2.png" alt="Example of the function's work" width="400"/>
</p>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Several examples how applyBordersFilter function should work">

First example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/squared/android.png" alt="Example of the function's work" width="400"/>

Second example:
<img src="../../utils/src/main/resources/images/part1/almost.done/examples/squared/monkey.png" alt="Example of the function's work" width="400"/>

</div>

<div class="hint" title="The main idea of the algorithm">

   You can use the <code>applyBordersFilter</code> function to add the borders, next 
   create two <code>StringBuilder</code>s (one for the top and one for the bottom part), 
   and put them row by row.
</div>

<div class="hint" title="Pre-defined variable to check your code">

   To check how your function works, you can run it in <code>main</code> by passing the pre-defined variable <code>simba</code>.
</div>
