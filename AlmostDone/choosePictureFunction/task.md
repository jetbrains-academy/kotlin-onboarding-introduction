In this task we will implement a function to provide a choice to the user which 
of the pre-defined pictures they want to change.

### Task

Implement `choosePicture` picture that chooses one pre-defined picture by its name.

<div class="hint" title="Push me to see the signature of the choosePicture function">

The signature of the function is:
```kotlin
fun choosePicture(): String
```
</div>

You should ask the user to choose a picture by its name: 
`Please, choose a picture. The possible options: <picture names>`.
You need to re-ask this question while user inputs one of the correct options.

The list of pre-defined pictures you can get by the pre-defined function `allPictures`:

```kotlin
println(allPictures()) // [spongeBob, simba, brianGriffin, cat, pig, fox, monkey, elephant, android, apple]
```

To get a picture by its name, you can use the pre-defined function `getPictureByName`,
which returns `String?` – an image – or `null` if the name is incorrect:

```kotlin
println(getPictureByName("brianGriffin")) // returns a picture, since "brianGriffin" is in allPictures() result
println(getPictureByName("myPicture")) // returns NULL, since "myPicture" is NOT in allPictures() result
```

**Note**: to avoid typos, just copy the text from here and paste it into your code.

The `choosePicture` should work as follows:

![`choosePicture` function work](../../utils/src/main/resources/images/part1/almost.done/choose_picture.gif "`choosePicture` function work")

