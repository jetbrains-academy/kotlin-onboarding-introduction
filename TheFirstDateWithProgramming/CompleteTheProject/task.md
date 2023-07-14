### Theory

Congratulations, you've almost finished your first project! 

<div class="hint" title="Extra theory">

I'll tell you a little trick on how you can write [comments](https://kotlinlang.org/docs/basic-syntax.html#comments)
for other developers (or for yourself) in your code.
To do this, use a double slash at the beginning of the code line:
```kotlin
fun main() {
    // My comment, I can write whatever I want here
}
```
Comments are usually left for the most difficult parts of the code.
They make it easier to later understand what the code is doing.

</div>
___

### Task

**Description**: Print the real questions and the user's answers to them. 
The real questions are stored in the already **predefined** `firstQuestion`, `secondQuestion`, and `thirdQuestion` variables.
Before the real questions, print the text: 
```text
Now let's have fun!
```

_Predefined_ means that you can access these variables 
because the course creator put them in the project and added the necessary values. 
For example, you can write `println("First question: $firstQuestion")` to print the value from the **predefined** `firstQuestion` variable and an additional text before (or after) it.

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="What does $ mean?">

String literals may contain template expressions – pieces of code that are 
evaluated and whose results are concatenated into the string. 
[A template expression](https://kotlinlang.org/docs/strings.html#string-templates) starts with a dollar sign (`$`) and consists of either a name or an expression in curly braces.

To insert something into a string, you can use the following construction:
```kotlin
val a = 5
println("a = $a") // a = 5 will be printed
```
</div>

<div class="hint" title="Game's example">

The game should look like this:

![The game's example](../../utils/src/main/resources/images/part1/first.date/game.gif "The game's example")

</div>
