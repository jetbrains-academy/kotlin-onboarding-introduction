### Theory

Great, you've done a good job implementing the `countExactMatches` function, 
probably using the `filterIndexed` function and the `length` property on the remaining word (but of course, you could implement it in any different way).
Now it is great time to pay more attention to built-in functions for working with collections and implement the _countPartialMatches_ function.

___

### Task

**Description**: implement the _countPartialMatches_ function by using Kotlin built-in functions. The function takes the _secret_ and the _guess_,
and returns the number of matched letters between them that are not in the same positions.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="Hint" title="Examples of the `countPartialMatches` function's work">

Here are several examples of the _countPartialMatches_ function's work:

- guess = "ACEB", secret = "BCDF", result = 1;
- guess = "ABCD", secret = "DCBA", result = 4;
- guess = "AAAA", secret = "ABBB", result = 0;
- guess = "BBBB", secret = "BBDH", result = 0.
</div>

<div class="Hint" title="The main idea of the algorithm">

The main idea of the algorithm is to count the letters in `secret` that are in `guess` and vice versa and calculate the minimum of them.
Next, we can just return the number of these letters minus the number of the letters 
that we can calculate via the <code>countExactMatches</code> function.
</div>

<div class="Hint" title="The `filter` built-in function">

To find the number of the letters from <code>guess</code> that are in <code>secret</code> and vice versa, 
you can consider the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/filter.html"><code>filter</code></a> function.
</div>

<div class="Hint" title="The `minOf` built-in function">

To calculate the minimum of two values, you can use the built-in function <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/min-of.html"><code>minOf</code></a> instead of the `if` operator.
</div>
