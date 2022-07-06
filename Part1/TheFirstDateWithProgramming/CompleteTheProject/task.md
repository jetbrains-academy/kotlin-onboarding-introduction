### Theory

Some variables should not follow [the main names' convention](https://kotlinlang.org/docs/coding-conventions.html#function-names),
that we learned in the previous step. The exception is `const` variables.
In this case, they are marked with the keyword `const` and [should use](https://kotlinlang.org/docs/coding-conventions.html#property-names) uppercase
underscore-separated names like `const val MY_VARIABLE = "My text"`.
We will learn the real difference between `const` and `val` in future lessons,
currently, it is enough to use just `val` variables.

Congratulations, you've almost finished your first project! 

___

### Task

**Description**: Print the real questions and the user's answers for them. 
The real questions are stored in `FIRST_QUESTION`, `SECOND_QUESTION`, and `THIRD_QUESTION` variables.

Before the real questions print the text: 'Now let's have fun!'
