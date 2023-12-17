We need to provide the user to be able to transform their own pictures. Let's do it!

### Task

Implement `getPicture` function that asks the user to choose a pre-defined picture or to input a custom picture.

<div class="hint" title="Push me to see the signature of the getPicture function">

The signature of the function is:
```kotlin
fun getPicture(): String
```
</div>

This function should work as follows:

First of all, ask this question: 
```text
Do you want to use a pre-defined picture or use a custom one? Please, input 'yes' for a pre-defined image or 'no' for a custom one
```

Next, read the user answer via `safeReadLine` function and process the output:

(1) If the user wants to choose a pre-defined picture, run the `choosePicture` function.

(2) If the user wants to upload a custom picture, ask them about it `Please, input a custom picture` (only single-line images need to be supported).

(3) If the user used an incorrect command, please inform the user about it `Please, input 'yes' or 'no'`.

**Note**: to avoid typos, just copy the text from here and paste it into your code.

The `getPicture` should work as follows:

![`getPicture` function work](../../utils/src/main/resources/images/part1/almost.done/get_picture.gif "`getPicture` function work")

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Push me to get a code style hint">

To check the user's answer in the `getPicture` function, 
it is most convenient to use the `when` expression instead a composite `if`.
</div>
