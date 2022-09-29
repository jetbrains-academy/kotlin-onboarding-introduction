### Theory

If you need to do several actions when working with a `nullable` value, 
you can use the [`let`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/let.html) construction:
```kotlin
fun foo(x: String?): String {
    x?.let {
        return x
    }
    return ""
}
```
or
```kotlin
fun foo(x: String?): String {
    x?.let {
        return it
    }
    return ""
}
```

This code is the same as:
```kotlin
fun foo(x: String?): String {
    if (x != null) {
      return x
    }
    return ""
}
```

___

### Task

**Description**: implement two functions: `choosePicture` and `getPicture`:

- `choosePicture` chooses one pre-defined picture by its name. 
You should ask the user to choose a picture by its name.
The list of pre-defined pictures you can get by the pre-defined function `allPictures`.
To get the picture by its name, you can use the pre-defined function `getPictureByName`, 
which returns `String?` - an image or `null` if the name is incorrect.
- `getPicture` asks the user to choose a pre-defined picture or to input a custom picture.
If the user wants to choose a pre-defined picture, run the `choosePicture` function. 
If the user wants to upload a custom picture, ask them about it.

**TODO: add a gif with example**

<div class="hint">
  To check the user's answer in the <code>getPicture</code> function, 
  it is more convenient to use the <code>when</code> expression.
</div>
