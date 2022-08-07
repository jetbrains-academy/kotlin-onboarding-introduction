### Theory

When writing code, it is convenient to divide it into independent units - 
[**functions**](https://kotlinlang.org/docs/functions.html), where each function performs a _specific_ action. 
For example, it prints something on the screen or evaluates the value of some expression.

We have already seen examples of functions in this course - for example, `main`, 
which we extended by new code, or _built-in_ functions such as `println` and `readLine`. 
Their peculiarity is that they perform some sequence of actions (_always the same_).
Each function, like `println`, can be called by its name.

To create a function, you need to write the `fun` keyword and give it some name:
```kotlin
fun myName() {
    // Some code
}
```

In addition, a function may have arguments. 
These arguments are available in the function's body. 
Arguments are declared in parentheses in the format `name: type`, 
and each of the argument can have a default value for example:
```kotlin
// By default intVariable has the default value 10
fun myName(intVariable: Int = 10, strVariable: String) {
    // The arguments are available in the function:
    println("$strVariable: $intVariable")
}
```

The function can also return a value.
To do this, you should specify the type of the return value 
and return it using the `return` keyword:

```kotlin
fun myName(intVariable: Int, strVariable: String): Int {
    // The arguments are available in the function:
    println("$strVariable: $intVariable")
    return intVariable + 5
}
```

If the function can be expressed in [one statement](https://kotlinlang.org/docs/idioms.html#single-expression-functions) (one action in the code), 
the `return` keyword, the type of the return value, and curly braces can be omitted. For example, 
```kotlin
fun myName(intVariable: Int): Int {
    return intVariable + 5
}
```
is equivalent to
```kotlin
fun myName(intVariable: Int) = intVariable + 5
```

Kotlin has a special [`TODO()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-t-o-d-o.html) function there 
that can be used as a temporary solution instead of implementing the function body:
```kotlin
fun myName(intVariable: Int): Int = TODO("Not implemented yet")
```

___

### Task

**Description**: add several function in the game:

- _getGameRules_, that has three arguments: _wordLength_, _maxAttemptsCount_, and _secretExample_ and return 
the text from the previous task about the game's rules
- _generateSecret_, that should return the hidden word. Let currently this function always return "ABCD"
- _countLettersMatchings_, that has two arguments (_secret_ and _guess_), 
and return the number of matched letters between them
- _countPositionalMatchings_, that has two arguments (_secret_ and _guess_),
and return the number of matched positions between them

Please, use the `TODO` function where needed