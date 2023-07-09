### Theory

1. What is the conditional operator?
2. The conditional operator as an expression: variables
3. The conditional operator as an expression: functions

#### 1. What is the conditional operator?

The conditional operator (the [`if`](https://kotlinlang.org/docs/control-flow.html#if-expression) expression) 
is used when the program has some choice. 
For example, if the variable contains a positive number, display it on the screen, 
otherwise do nothing. 
Or, if the guess matches the secret, end the game.

Consider the following example:
```kotlin
if (y > 0) {
    println(y)
}
```
Another example: 
```kotlin
if (x > y) {
    println(x)
} else {
    // The opposite condition: x <= y
    println(y)
}
```

For the `else` branch, the reverse of the original condition is used: for example,
for `x > y`, the opposite condition is `x <= y`.

#### 2. The conditional operator as an expression: variables

In Kotlin, the `if` expression can be also assigned to a variable 
or be a returned value in a function; in such a case, the **last** operator in each branch will be returned:
```kotlin
val max = if (x > y) {
    println(x)
    // if x > y return x
    x
} else {
    // The opposite condition: x <= y
    println(y)
    // if x <= y return y
    y
}
```
For input `x = 5` and `y = 15`, the result will be `max = 15`; and otherwise, 
if `x = 20` and `y = 15`, the result will be `max = 20`.

#### 3. The conditional operator as an expression: functions

It is the same with functions:
```kotlin
fun max(x: Int, y: Int) = if (x > y) {
    println(x)
    // if x > y return x
    x
  } else {
    // The opposite condition: x <= y
    println(y)
    // if x <= y return y
    y
  }
```
Or, in the full notation:
```kotlin
fun max(x: Int, y: Int): Int {
    return if (x > y) {
      println(x)
      // if x > y return x
      x
    } else {
      // The opposite condition: x <= y
      println(y)
      // if x <= y return y
      y
    }
}
```

### Task

**Description**: implement the `isComplete` function. It should return `true` 
if `secret` and `guess` are the same and `false` otherwise.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Code style hint">

If you compare two variables to get a <code>Boolean</code> result, you can use the short notation. 
For example, the following code:
```
fun isNotEqual(a: Int, b: Int) = if (a != b) {
    true
} else {
    false
}
```
can be replaced with this one:
```
fun isNotEqual(a: Int, b: Int) = a != b
```
</div>
