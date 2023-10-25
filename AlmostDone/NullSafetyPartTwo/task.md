### Theory

If you need to do several actions when working with a `nullable` value,
you can use safe call operator (`?.`) together with the [`let`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/let.html) scope function from the standard library:
```kotlin
fun foo(x: String?): String {
    x?.let {
        println("x is not null!")
        return x
    }
    return ""
}
```
or
```kotlin
fun foo(x: String?): String {
    x?.let {
        println("x is not null!")
        return it
    }
    return ""
}
```

This code is the same as:
```kotlin
fun foo(x: String?): String {
    if (x != null) {
        println("x is not null!")
        return x
    }
    return ""
}
```

___

### Task

**Description**: implement two functions: `choosePicture` and `getPicture`:

- `choosePicture` chooses one pre-defined picture by its name. 
You should ask the user to choose a picture by its name: `Please, choose a picture. The possible options: <picture names>}`.
The list of pre-defined pictures you can get by the pre-defined function `allPictures`.
To get a picture by its name, you can use the pre-defined function `getPictureByName`, 
which returns `String?` – an image – or `null` if the name is incorrect:

![`choosePicture` function work](../../utils/src/main/resources/images/part1/almost.done/choose_picture.gif "`choosePicture` function work")

- `getPicture` asks the user to choose a pre-defined picture or to input a custom picture:
```text
Do you want to use a pre-defined picture or use a custom one? Please, input 'yes' for a pre-defined image or 'no' for a custom one
```
(1) If the user wants to choose a pre-defined picture, run the `choosePicture` function.

(2) If the user wants to upload a custom picture, ask them about it `Please, input a custom picture` (only single-line images need to be supported).

(3) If the user used an incorrect command, please inform the user about it `Please, input 'yes' or 'no'`:

![`getPicture` function work](../../utils/src/main/resources/images/part1/almost.done/get_picture.gif "`getPicture` function work")

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Code style hint">
  To check the user's answer in the <code>getPicture</code> function, 
  it is most convenient to use the <code>when</code> expression.
</div>
