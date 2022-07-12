**TODO: add a task before this task with explanation of loop concept**

### Theory

What does a string usually consist of?
Actually a string is a sequence of characters (letters in our case).
Therefore, when working with strings, we can think of them as a _list of letters_.

Kotlin has special data type for working with such sequences - [`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/).
For now, we need to know the following:
1) `List` is collection that contains data of the _same_ type, e.g. only strings or ints.
3) `List` is an ordered collections (each element has the position),
   The position of the first element in a list is zero.

Let's look at an example in the context of our task:
the word `ABCDDD` can be divided into six letters: `A`, `B`, `C`, `D`, `D`, `D`.
The list in this case will consist of six elements - `A`, `B`, `C`, `D`, `D`, `D`,
and each of them has positions: `A` - 0, `B` - 1, `C` - 2, `D` - 3, `D` - 4, `D` - 5.

To get an element in a list by position number,
it is enough to refer to it in square brackets:
```kotlin
// Get B character
"ABCDDD"[1]
```

Kotlin has many built-in functions for working with such collections,
for example, to search for a value, filtering, and so on.
```kotlin
// Get the first symbol for the word
"ABCDDD".first()
```

**TODO: is it ok to use lambda expressions here, add function example**

Often these built-in functions accept [lambda expressions](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions).
They are functions that are not declared but are passed immediately as an expression.
Consider the following example:
```kotlin
// Keep only A symbol
"ABCDDD".filter { symbol: Char -> symbol == 'A' }
```
In this case, we are using a function that is not declared separately,
does not have a name, but which will be applied to **each** element of the collection.
It takes one parameter `Char` (character) and compares it to character `A` (for character we need to use single quotes).

This is the same if we made a function
```kotlin
fun isSymbolA(symbol: Char) = symbol == 'A'
```
and call it on each symbol from the word `ABCDDD`.

If the type of the arguments is clear from the context, then they can be omitted:
```kotlin
"ABCDDD".filter { symbol -> symbol == 'A' }
```

Also, if a built-in function has a lambda function with one argument,
then this argument already has a built-in name `it`, in which case it can also be omitted:
```kotlin
"ABCDDD".filter { it == 'A' }
```


___

### Task

**Description**: implement _countPositionalMatchings_ function by using Kotlin built-in functions.

<div class="Hint">
The main idea of the algorithm is to keep only letters that are equal and have the same index. 
Next, we can just return the number of these letters.
</div>

<div class="Hint">
To find the number of positional matchings you can consider the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/filter-indexed.html"><code>filterIndexed</code></a> function
</div>

<div class="Hint">
To get the number characters in the word you can use <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/length.html#length"><code>length</code></a>:
<code>"ABCDDD".length</code>  
</div>
