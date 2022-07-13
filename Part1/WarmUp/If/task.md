### Theory

The conditional operator ([`if`](https://kotlinlang.org/docs/control-flow.html#if-expression) expression) 
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
or 
```kotlin
if (x > y) {
    println(x)
} else {
    // The opposite condition: x <= y
    println(y)
}
```

For the `else` branch, the reverse of the original condition is used, for example,
for `x > y` the opposite condition is `x <= y`.

In Kotlin `if` expression can be also assigned into a variable 
or be a returned value in a function, in this case the **last** operator in each branch will be returned:
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
For input `x = 5` and `y = 15` the result will be `max = 15`, and otherwise, 
if `x = 20` and `y = 15` the result will be `max = 20`.

For the function it might be the same:
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
or in the full notation:
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

**Description**: implement `isComplete` function. It should return `true` 
if `secret` and `guess` are the same and `false` otherwise.

<div class="hint">
If you compare two variables to get <code>Boolean</code> result, you can use a short notation, 
for example, the following code:
<pre>
fun isNotEqual(a: Int, b: Int) = if (a != b) {
    true
} else {
    false
}
</pre>
can be replaced into this one:
<pre>
fun isNotEqual(a: Int, b: Int) = a != b
</pre>
</div>