### Theory

1. What is the `when` expression?
2. Using the `when` expression: checking the variable's value
3. The `error` function

#### 1. What is the `when` expression?

We are already familiar with the `if` expression; however, 
it may not be convenient in all cases: 
for example, when we need to check a large number of values.
In such cases, you can use the [`when`](https://kotlinlang.org/docs/control-flow.html#when-expression) expression.

For example, take a look at the following code:
```kotlin
fun checkNumber(x: Int) {
    if (x > 0) {
        println("A positive number")
    } else if (x < 0) {
        println("A negative number")
    } else {
        println("The zero number")
    }
}
```
The above code can be replaced with the following:
```kotlin
fun checkNumber(x: Int) {
    when {
        x > 0 -> println("A positive number")
        x < 0 -> println("A negative number")
        else -> println("The zero number")
    }
}
```
Now it is shorter and easier to read.

#### 2. Using the `when` expression: checking the variable's value

In addition, you can specify a variable that you want to compare 
with some values and just list these values below. For example:

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
It can be replaced with:
```kotlin
fun checkNumber(x: Int): Int {
    return when (x) {
        0 -> { x + 5 }
        10 -> { x - 5 }
        else -> { x / 10 }
    }
}
```

#### 3. The `error` function

The `when` expression is useful in handling edge cases: 
for example, you can simply end the execution of the program 
and inform the user about errors. 
You can use the [`error`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/error.html) function:
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

**Description**: Implement the `applyFilter` function, which accepts a **trimmed** picture and a filter name, 
applies the given filter, and returns the updated picture.

In this project, we will implement two obligatory filters (but you can add any others if you want):

- `borders` – adds a border to the image:
<div class="hint" title="An example of the borders filter">

An example of this filter:
<p>
    <img src="../../utils/src/main/resources/images/part1/almost.done/when_hint_1.png" alt="Filter example" width="200"/>
</p>

</div>

- `squared` - replicates the image 4 times:
<div class="hint" title="An example of the squared filter">

An example of this filter:
<p>
    <img src="../../utils/src/main/resources/images/part1/almost.done/when_hint_2.png" alt="Filter example" width="400"/>
</p>

</div>

The function should inform the user about an unexpected filter name.

In this task, you should also add two functions with `TODO` (we will implement them in the following tasks):
- `applyBordersFilter`, which accepts a picture and applies the `borders` filter;
- `applySquaredFilter`, which accepts a picture and applies the `squared` filter.
