### Theory

We are already familiar with the `if` expression, however, 
it is not always convenient to use it. 
For example, if we need to check a large number of values.
In such cases, you can use the [`when`](https://kotlinlang.org/docs/control-flow.html#when-expression) expression.

For example, the following code:
```kotlin
fun checkNumber(x: Int) {
    if (x > 0) {
        println("The positive number")
    } else if (x < 0) {
        println("The negative number")
    } else {
        println("The zero number")
    }
}
```
can be replaced into this one:
```kotlin
fun checkNumber(x: Int) {
    when {
        x > 0 -> println("The positive number")
        x < 0 -> println("The negative number")
        else -> println("The zero number")
    }
}
```
This view is shorter and easier to read.

In addition, you can specify a variable that you want to compare 
with some value and just specify these values below. For example:

```kotlin
fun checkNumber(x: Int): Int {
    return if (x == 0) {
        x + 5
    } else if (x == 10) {
        x - 5
    } else {
        x / 10
    }
}
```
can be replaced into:
```kotlin
fun checkNumber(x: Int): Int {
    return when (x) {
        0 -> { x + 5 }
        10 -> { x - 5 }
        else -> { x / 10 }
    }
}
```

When using `when`, it is useful to handle edge cases, 
for example, you can simply end the execution of the program, 
informing the user about errors. 
You can use the `error` function:
```kotlin
fun checkNumber(x: Int): Int {
    return when (x) {
        0 -> { x + 5 }
        10 -> { x - 5 }
        else -> error("Unexpected number")
    }
}
```

___

### Task

**Description**: Implement the `applyFilter` function that accepts a picture and a filter name, 
applies the given filter, and returns the updated picture.

In this project we will implement two obligatory filters (but you can add any others if you want):

- `borders` - adds a border to the image:
<div class="hint">
TODO: example
</div>

- `squared` - replicates the image 4 times:
<div class="hint">
TODO: example
</div>

The function should inform user about an unexpected filter name.

In this task you should also add two functions with `TODO` (we will implement them in the next tasks):
- `applyBordersFilter`, that accepts a picture and applies the `borders` filter;
- `applySquaredFilter`, that accepts a picture and applies the `squared` filter.
