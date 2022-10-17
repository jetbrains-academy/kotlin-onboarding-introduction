### Theory

Sometimes, the same actions need to be repeated several times: 
for example, to play several rounds of a game or print the same text on the screen.
To solve this problem, you can use loops.
Loops can be executed while some condition is true (the [`while`](https://kotlinlang.org/docs/basic-syntax.html#while-loop) loop) 
or repeated a certain number of times (the [`for`](https://kotlinlang.org/docs/control-flow.html#for-loops) loop).

`While` loops often use `Boolean` values, for example:
```kotlin
while(y < 10) {
    // To do something
}
```
It will execute the actions (the loop's body) while the value in `y` is less than ten.
Here, the condition will be checked _first_, and next, if it is correct, 
the loop's body will be executed.
If you need another scenario: _first_ execute the loop's body and then check the condition,
you should use the `do..while` loop. In such a case, the body will be executed at least one time:
```kotlin
do {
    // To do something
} while(y < 10)
```

`For` loops often use [`Ranges`](https://kotlinlang.org/docs/basic-syntax.html#ranges) to define 
how many times the body of the loop will be executed:
```kotlin
for (i in 1..3) {
  // To do something
}
```
In this case, the body of the loop will be executed three times: `1 <= i <= 3`.
Kotlin has [several ways](https://kotlinlang.org/docs/idioms.html#iterate-over-a-range) 
to define the borders of a range; for example, the range `1 <= i < 3` can be defined as follows:
```kotlin
for (i in 1 until 3) {
  // To do something
}
```

Loops often require changing the value of a variable. 
To do that, you can use the [`var`](https://kotlinlang.org/docs/basic-syntax.html#variables) variable:
```kotlin
var y = 5
do {
    println(y)
    y += 2
} while(y < 10)
```
This code initializes the variable `y` with the value `5` and next changes the variable in the loop.
The loop will be executed in 3 steps:
1) y = 5. println(y). y = 7.
2) because y < 10, println(y), y = 9.
3) because y < 10, println(y), y = 11.
4) because y > 10, stop the loop.
In the end, the following numbers: 5, 7, and 9 will be printed in the console.

If you need to work with `Boolean` variables, you should omit the comparing part in the condition:
```kotlin
var b = true
while(b == true) {
    // To do something
}
```
It is equal to:

```kotlin
var b = true
while (b) {
    // To do something
}
```
An opposite example:
```kotlin
var b = false
while(b == false) {
    // To do something
}
```
It is equal to:

```kotlin
var b = false
while (!b) {
    // To do something
}
```


___

### Task

**Description**: define a new function `playGame`, which accepts `secret`, `wordLength`, and `maxAttemptsCount` 
and imitates the game process:
- Define a special variable with name `complete` to indicate if the game is over 
and make a loop that works while the game is running. 
- In the loop, you should ask the user to input a guess and write their answer into the `guess` variable: 
```text
Please input your guess. It should be <wordLength> characters long.
```

- Please, instead of the `readLine` function, use the `safeReadLine` function. 
It is a custom function from the course authors: 
it uses the [`elvis operator`](https://kotlinlang.org/docs/null-safety.html#elvis-operator), which will be covered in the future topics. 
If you want, you can try to use it with the familiar `readLine` function. 

- Define a function `isComplete`, which accepts `secret` and `guess` and checks if the game is over. 
For now, let it always return `true`. Call it after reading the user input and rewrite the `complete` variable.

- Call the `playGame` function in the `main` function. 
Don't forget to use the `generateSecret` function to get a secret.

<div class="Hint">
Use <code>do..while</code> for imitating the game process.
</div>

<div class="Hint">
The best type for the <code>complete</code> variable is <code>Boolean</code>, since it indicates only two game states.
</div>

<div class="Hint">

If you use the <code>do..while</code> loop with a <code>Boolean</code> variable as the condition, 
you can omit the initialization of the variable before the loop. For example, consider the following code:
```
var myBool = false
do {
    myBool = getNewValue()
} while (!myBool)
```
It can be replaced with:
```
var myBool: Boolean
do {
    myBool = getNewValue()
} while (!myBool)
```
You can do it only if the value of the variable is <b>changed</b> inside the loop.
</div>
