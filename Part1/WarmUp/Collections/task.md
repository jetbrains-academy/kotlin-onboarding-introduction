### Theory

1. `String` as a `List` of characters
2. How to get an element from the list?
3. Built-in functions to work with `List`
4. Lambda expressions
5. Different ways to work with arguments in the lambda expressions
6. `filterIndexed` function

#### 1. `String` as a `List` of characters

What does a string usually consist of?
Actually, a string is a sequence of characters (letters in our case).
Therefore, when working with strings, we can think of them as a _list of letters_.

Kotlin has a special data type for working with such sequences - [`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/).
For now, we need to know the following:
1) `List` is a collection that contains data of the _same_ type, e.g., only strings or ints.
3) `List` is an ordered collection (each element has a position),
   The position of the first element in a list is zero.

Let's look at an example in the context of our task:
the word `ABCDDD` can be divided into six letters: `A`, `B`, `C`, `D`, `D`, `D`.
The list in this case will consist of six elements - `A`, `B`, `C`, `D`, `D`, `D`,
and each of them has its position: `A` - 0, `B` - 1, `C` - 2, `D` - 3, `D` - 4, `D` - 5.

#### 2. How to get an element from the list?

To get an element in a list by the position number,
it is enough to refer to it in square brackets:
```kotlin
// Get B character
"ABCDDD"[1]
```

#### 3. Built-in functions to work with `List`

Kotlin has many built-in functions for working with such collections –
for example, searching for a value, filtering, and so on.
```kotlin
// Get the first symbol in the word, it will be A
"ABCDDD".first()
```

#### 3. Lambda expressions

Often these built-in functions accept [lambda expressions](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions).
We will talk about them in detail later, but currently it is enough to know that:
- they offer a special way to tell built-in functions what they should do with _each_ 
element from the collection;
- they have a special syntax.

Consider the following example that traverse a word and keep only `A` symbols:
```kotlin
var result = ""
for (symbol in "ABCDDD") {
   if (symbol == 'A') {
      result += symbol
   }
}
```

We used the already familiar constructions like `for` and `if`, but it can be rewritten via built-in functions and lambda expressions:

```kotlin
// Keep only A symbol
"ABCDDD".filter { symbol: Char -> symbol == 'A' }
```
In this case, we are using a lambda expression (a condition), 
which will be applied to **each** element of the collection via the built-in function `filter`.
The lambda expression takes one parameter `Char` (character) and compares it to character `A` 
(for characters we need to use single quotes).

The `->` indicates that the left part of the lambda expression with the arguments is over.
The right part of the lambda expression (after `->`) says what **exactly** we should do, e.g. compare the `symbol` with 'A'.

#### 4. Different ways to work with arguments in the lambda expressions

In the previous example we used the full form for the lambda expression arguments: name and type for the arguments.

If the type of the arguments is clear from the context (as it usually is with collections), 
then the type can be omitted:
```kotlin
"ABCDDD".filter { symbol -> symbol == 'A' }
```

Also, if you need only one argument,
then this argument already has a built-in name `it`, in which case it can also be omitted:
```kotlin
"ABCDDD".filter { it == 'A' }
```

#### 5. `filterIndexed` function

Kotlin has many built-in functions, e.g. we can filter something and manipulate not only with elements from the list but with its indices.
For example, we need to build a new word, which consists of characters that occur simultaneously in both words at the same positions.
The classic way is:
```kotlin
val secondWord = "AACAAA"
var result = ""
for ((index, symbol) in "ABCDDD".withIndex()) {
   if (secondWord[index] == symbol) {
      result += symbol
   }
}
// The result will be: "AC"
```

But we can use the [`filterIndexed`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter-indexed.html) function to make code shorter:
```kotlin
val secondWord = "AACAAA"
// The result will be: "AC"
"ABCDDD".filterIndexed { index, symbol -> secondWord[index] == symbol }
```

By the way, in this case, the lambda expression takes two arguments, so we use custom names for the arguments (`index` and `symbol`), not only `it`.

___

### Task

**Description**: implement the _countExactMatches_ function by using Kotlin built-in functions. Given the `guess` and the `secret`, the function should output the number of letters that match exactly down to position.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="Hint">
Here are several examples of the _countExactMatches_ function's work:

- guess = "ACEB", secret = "BCDF", result = 1;
- guess = "ABCD", secret = "DCBA", result = 0;
- guess = "AAAA", secret = "ABBB", result = 1;
- guess = "BBBB", secret = "BBDH", result = 2.
</div>

<div class="Hint">
The main idea of the algorithm is to keep only those letters that are equal and have the same index. 
Next, we can just return the number of these letters.
</div>

<div class="Hint">
To find the number of exact matches, you can consider the <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/filter-indexed.html"><code>filterIndexed</code></a> function.
</div>

<div class="Hint">
To get the number of characters in the word, you can use <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/length.html#length"><code>length</code></a>:
<code>"ABCDDD".length</code>  
</div>
