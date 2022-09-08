### Theory

When defining a type, you can specify that it can also have the special `null` value. 
It's a `null` reference that doesn't refer to anything.
What this means in more detail we will learn in next parts of the course, 
for now it is enough to know some basic things.

To indicate that a type might be `null`, you should add `?` to the type, for example:
```kotlin
// 'a' can be String or null
var a: String? = null
```

If a value can be `null`, then the various built-in functions that we talked about earlier 
cannot automatically work with such the value, for example:
```kotlin
var a: String? = null
a.length // INCORRECT!!

var a: String = "text"
a.length // CORRECT
```

To work correctly with such _nullable_ values, the [null-safety](https://kotlinlang.org/docs/null-safety.html) mechanism in Kotlin exists.
In simple words, in Kotlin it is necessary to handle `null` values separately for the program to work correctly.
The simplest processing mechanisms are [`!!`](https://kotlinlang.org/docs/null-safety.html#the-operator) and [`?:`](https://kotlinlang.org/docs/null-safety.html#elvis-operator) (elvis operator) operators.

`!!` operator simply discards the `null` value and works with the type as if there 
could be no `null` value. However, if the program meets with the `null` value, 
it will exit with the error ([`Null pointer exception`](https://kotlinlang.org/docs/null-safety.html#nullable-types-and-non-null-types), or `NPE`).
```kotlin
var a: String? = null
a!!.length // CORRECT, but will throw NPE

var a: String? = null
a?.length ?: error("Find null value") // CORRECT, the case with null will be handled separately
```

The last example with the elvis operator is the same with the following code:
```kotlin
var a: String? = null
if (a != null) {
    a.length // We can use just length here (without ?) thanks to the smart-casts mechanism
} else {
    error("Find null value")
}
```

In this example we noted the [smart-casts mechanism](https://kotlinlang.org/docs/typecasts.html#smart-casts).
It is a special mechanism in Kotlin that can define some cases when a nullable value is always not null.

In past assignments, we sometimes used the pre-defined `safeReadLine` function instead of built-in `readLine`. 
The main reason of it is `readLine` returns a _nullable_ value (`String?`). 
The pre-defined `safeReadLine` function processed the input received from the user using the elvis operator:
returns the string or throws an error if the `null` value was received.
___

### Task

**Description**: implement `safeReadLine` function, that returns the string user input or throws an error 
if the `null` value was received.

Also implement the `chooseFilter` function, that ask user to choose a filter (`borders` or `squared`) and returns it.
This function has to use `safeReadLine` function. 
If the user input an incorrect filter's name, the function should ask to input the right name.

**TODO: add a gif with example**

<div class="hint">

<code>when</code> expression allow you to use several values in one branch and define a variable in place:

```kotlin
when (val input = safeReadLine()) {
    "firstValue", "secondValue" -> {
        TODO()
    }
    else -> TODO()
}
```
</div>