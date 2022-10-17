### Theory

Great, you've done a good job implementing the `countPositionalMatchings` function, 
probably using the `filterIndexed` function and the `length` property on the remaining word.
Now it is great time to pay attention to another built-int function for working with collections.



___

### Task

**Description**: implement the _countLettersMatchings_ function by using Kotlin built-in functions.

<div class="Hint">
There are several examples of working _countLettersMatchings_ function:

- guess = "ACEB", secret = "BCDF", result = 1;
- guess = "ABCD", secret = "DCBA", result = 4;
- guess = "AAAA", secret = "ABBB", result = 0;
- guess = "BBBB", secret = "BBDH", result = 0.
</div>

<div class="Hint">

The main idea of the algorithm is to count letters in `secret` that are in `guess` and vice versa and calculate the minimum of them.
Next, we can just return the number of these letters minus the number of the letters 
that we can calculate via the <code>countPositionalMatchings</code> function.
</div>

<div class="Hint">

To find the number of letters from <code>guess</code> that are in <code>secret</code> and vice versa, 
you can consider the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/filter.html"><code>filter</code></a> function.
</div>

<div class="Hint">

To calculate the minimum of two values you can use the built-in function <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/min-of.html"><code>minOf</code></a> instead of `if` operator.
</div>
