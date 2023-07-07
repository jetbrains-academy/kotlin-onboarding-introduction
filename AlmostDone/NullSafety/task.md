### Theory

1. The `null` value
2. Null safety
3. The `!!` operator
4. The `Elvis` operator and the smart casts mechanism
5. How the `safeReadLine` function works

#### 1. The `null` value

When defining a type, you can specify that it can also have a special `null` value. 
It's a `null` reference, which doesn't refer to anything.
What this means in more detail, we will learn in the following parts of the course; 
for now, it is enough to know some basic things.

To indicate that a type might be `null`, you should add `?` to the type, for example:
```kotlin
// 'a' can be String or null
var a: String? = null
```

If a value can be `null`, then the various built-in functions that we talked about earlier 
cannot automatically work with such a value, for example:
```kotlin
var a: String? = null
a.length // INCORRECT!!

var a: String = "text"
a.length // CORRECT
```

#### 2. Null safety

To work correctly with such _nullable_ values, Kotlin provides the [null safety](https://kotlinlang.org/docs/null-safety.html) mechanism.
In simple words, in Kotlin, it is necessary to handle `null` values separately for the program to work correctly.
The simplest processing mechanisms are the [`!!`](https://kotlinlang.org/docs/null-safety.html#the-operator) and [`?:`](https://kotlinlang.org/docs/null-safety.html#elvis-operator) (the Elvis operator) operators.

#### 3. The `!!` operator

The `!!` operator simply discards the `null` value and works with the type as if there 
could be no `null` value. However, if the program encounters the `null` value, 
it will exit with the error ([`Null pointer exception`](https://kotlinlang.org/docs/null-safety.html#nullable-types-and-non-null-types), or `NPE`).
```kotlin
var a: String? = null
a!!.length // CORRECT, but will throw NPE
```

#### 4. The `Elvis` operator and the smart casts mechanism

The `Elvis` operator checks if the value is `null` and handles the `null` value separately.
```kotlin
var a: String? = null
a?.length ?: error("Find null value") // CORRECT, the case with null will be handled separately
```

The latter example with the Elvis operator is the same as the following code:
```kotlin
var a: String? = null
if (a != null) {
    a.length // We can use just length here (without ?) thanks to the smart casts mechanism
} else {
    error("Find null value")
}
```

In this example, we noted the [smart casts mechanism](https://kotlinlang.org/docs/typecasts.html#smart-casts).
It is a special mechanism in Kotlin that can define some cases when a nullable value is always non-null.

#### 5. How the `safeReadLine` function works

In previous assignments, we sometimes used the pre-defined `safeReadLine` function instead of built-in `readlnOrNull`. 
The main reason is that `readlnOrNull` returns a _nullable_ value (`String?`). 
The pre-defined `safeReadLine` function processed the input received from the user with the Elvis operator:
it returns the string or throws an error if the `null` value was received.
___

### Task

**Description**: implement the `safeReadLine` function, which returns the string the user inputs or throws an error 
if the `null` value was received.

Also, implement the `chooseFilter` function, which asks the user to choose a filter (`borders` or `squared`, the full text is `Please choose the filter: 'borders' or 'squared'.`) and returns it.
This function has to use the `safeReadLine` function. 
If the user inputs an incorrect filter name, the function should ask to input the right one `Please input 'borders' or 'squared'`:

![`chooseFilter` function work](../../utils/src/main/resources/images/part1/almost.done/choose_filter.gif "`chooseFilter` function work")

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="An efficient way to use `when`">

The <code>when</code> expression allows you to use several values in one branch and define a variable in place:

```kotlin
when (val input = safeReadLine()) {
    "firstValue", "secondValue" -> {
        TODO()
    }
    else -> TODO()
}
```
</div>
