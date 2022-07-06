### Theory

In programming, the concept of a **variable** is very often used. 
A variable is a kind of box that stores some value. 
Each variable has a _name_, a _type_ and a _value_.

To declare a variable in Kotlin,
you need to use the [`val`](https://kotlinlang.org/docs/basic-syntax.html#variables) keyword, 
specify its _name_ and _type_, and after the `=` sign, 
specify the _value_ of this variable.

For example, to create a `firstAnswer` variable and leave it empty [`String`](https://kotlinlang.org/docs/basic-types.html#strings), 
you should write the following:
```kotlin
val firstAnswer: String = ""
```

It is **important** to note that the `val` variable _cannot be changed_. 
This means that if you put a value in it, then no other value can be there.

Often in Kotlin the variable type can be _missed_ if it can be inferred from the context:
```kotlin
val firstAnswer = ""
```

According to [the Kotlin naming convention](https://kotlinlang.org/docs/coding-conventions.html#function-names), 
variables should start with a lowercase letter and use [camel case](https://en.wikipedia.org/wiki/Camel_case) and no underscores.
For example, `firstAnswer` is correct and `first_answer` or `FirstAnswer` are incorrect.
___

### Task

**Description**: add empty `String` variables for the user answers with names 
_firstUserAnswer_, _secondUserAnswer_, and _thirdUserAnswer_.