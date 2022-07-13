### Theory

Great, you've done a good job implementing the `countPositionalMatchings` function, 
probably using the `filterIndexed` function and the `length` property on the remaining word.
Now it is great time paying attention to another built-int function for working with collections.

___

### Task

**Description**: implement _countLettersMatchings_ function by using Kotlin built-in functions.

<div class="Hint">
The main idea of the algorithm is to keep only letters from <code>guess</code> that are in <code>secret</code>. 
Next, we can just return the number of these letters minus the number of letters, 
that we can calculate via <code>countPositionalMatchings</code> function.
</div>

<div class="Hint">
To find the number of letters from <code>guess</code> that are in <code>secret</code> 
you can consider the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/filter.html"><code>filter</code></a> function
</div>
