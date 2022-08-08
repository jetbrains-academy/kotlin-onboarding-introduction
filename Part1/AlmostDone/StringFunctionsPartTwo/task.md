### Theory

You already know enough about Kotlin to solve this task! 
So let's get straight to the point.

___

### Task

**Description**: implement the `applySquaredFilter` function.
For the border symbol please use the pre-defined variable `borderSymbol`.

To make the picture prettier, add a separator between the picture and the border.
For separator, please use the pre-defined variable `separator`.

In addition, the project already stores the `newLineSymbol` variable that can be used to split the picture into lines.

An example of the working this function:
```text
// The initial image:
┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈
▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈
▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈
┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈
┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈
┈┈▕╲▂┊╰━━┻━╱┈┈┈
┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈

// The final image:
######################################
# ┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈ ## ┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈ #
# ▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈ ## ▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈ #
# ▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈ ## ▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈ #
# ┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈ ## ┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈ #
# ┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈ ## ┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈ #
# ┈┈▕╲▂┊╰━━┻━╱┈┈┈ ## ┈┈▕╲▂┊╰━━┻━╱┈┈┈ #
# ┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈ ## ┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈ #
######################################
# ┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈ ## ┈╱▔╲▂╱╱╱╱▂╱▔╲┈┈ #
# ▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈ ## ▕▔╲┈╱▔╲┈┈╱╲╱▔▏┈ #
# ▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈ ## ▕▏┈▏╱▉╲┈┈╱▉╲▕▏┈ #
# ┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈ ## ┈╲▃▏▔▔▔╲▂▂▂▕╱┈┈ #
# ┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈ ## ┈┈┈▏┊┊┳┊╲▂╱┳▏┈┈ #
# ┈┈▕╲▂┊╰━━┻━╱┈┈┈ ## ┈┈▕╲▂┊╰━━┻━╱┈┈┈ #
# ┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈ ## ┈┈╱┈┈▔▔╲▂▂╱╲┈┈┈ #
######################################
```

<div class="hint">
   You can use <code>applyBordersFilter</code> function to add the borders, and next 
   to create two <code>StringBuilder</code>s (for the top and for the bottom parts), 
   and put them row by row.
</div>

<div class="hint">
   To check how works your function you can run it in <code>main</code> by passing the pre-defined variable <code>simba</code>
</div>