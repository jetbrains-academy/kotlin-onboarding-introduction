Let's add one more function that helps to solve the task.

### Task

Implement the `applyFilter` function, which accepts a picture,
and a filter name, applies the `trimPicture` function to the picture and finally
applies the given filter, and returns the updated picture. To apply a filter, 
just call one of the already defined functions `applyBordersFilter` or `applySquaredFilter`.

<div class="hint" title="Click me to see the signature of the applyFilter function">

The signature of the function is:
```kotlin
fun applyFilter(picture: String, filter: String): String
```
</div>

The possible values for the `filter` argument:

- `borders` – adds a border to the image, calls the `applyBordersFilter` function
- `squared` - replicates the image 4 times, calls `applySquaredFilter` function

The `applyFilter` function should inform the user about an unexpected filter name by throwing an error.
