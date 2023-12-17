On this step you need to finish the app.

### Task

Implement the `applyGenerator` function, that accepts `pattern`, `generatorName`, `width` and `height`
and trims the `pattern` and finally applies `canvasGenerator` or `canvasWithGapsGenerator` function.

<div class="hint" title="Push me to see the new signature of the applyGenerator function">

The signature of the function is:
```kotlin
fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String
```
</div>

The possible values for the `generatorName` argument:

- `canvas` – calls the `canvasGenerator` function
- `canvasGaps` - calls `canvasWithGapsGenerator` function

The `applyGenerator` function should inform the user about an unexpected filter name by throwing an error.

<div class="hint" title="Push me to see the patterns generator project example">

  ![The patterns generator example](../../utils/src/main/resources/images/part1/last.push/app.gif "The patterns generator example")

</div>


Also, the `main` function will be checked - just uncomment code in the `main` function.

Good luck!
