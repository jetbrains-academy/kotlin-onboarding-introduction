[![official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Gradle Build](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build.yml)
[![Gradle Build With Detekt](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build-with-detekt.yml/badge.svg)](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build-with-detekt.yml)
[![Gradle Test](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-test.yml/badge.svg)](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-test.yml)

# Introduction to Kotlin

This course is designed for novices in Kotlin
and focuses on the basic concepts of the Kotlin language.

Each lesson of the course is built in the form of a project:
step by step, by completing different small tasks,
you will get a finished small project in the end.
At the end of each lesson, an additional similar project will be offered:
it includes all the topics of the lesson but is not divided into small tasks.

Note, this course **does not provide the detailed explanation** of the basic concepts
like variables, if just offers to introduce you how to use it in Kotlin and can just remind the definitions.

All topics will be accompanied by links to [the official Kotlin documentation](https://kotlinlang.org/docs/home.html),
which you can read later.

**The covered topics:**
- the entry point for a Kotlin program;
- variables;
- functions;
- loops;
- ranges;
- `if` expressions;
- multi-row strings and string builder;
- basic facts about the null safety mechanism;
- basic facts about collections and lambda expressions.

After this course you will be ready to write basic console applications in the Kotlin-like style.

## Getting started

**TODO: add course link**
This course is available on the marketplace and can be installed from the 
[IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE directly, but you can also use this course in 
the course creator mode or create a course archive from the source code.

### Getting started: create a course preview from the source code

**TODO: add link about course preview**
You can create a course preview from the source code:
1. Clone this repository: 
```text
git clone https://github.com/jetbrains-academy/csc-kotlin-course.git
```

2. Build the project:
```text
./gradlew build
```

3. Install the [EduTools](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educational-products.html) plugin from the marketplace

4. Create a new course preview **TODO: add a link**

### Getting started: create a course archive

**TODO: add link about course archive**
You can create a course archive from the source code:
1. Clone this repository:
```text
git clone https://github.com/jetbrains-academy/csc-kotlin-course.git
```

2. Build the project:
```text
./gradlew build
```

3. Install the [EduTools](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educational-products.html) plugin from the marketplace

4. Create a new course archive **TODO: add a link**

## Run tests

To run tests locally you just need to build the project and run the following command:

```text
./gradlew test
```

The tests use Java Reflection API under the hood to check the user tasks.

## Want to know more?

If you have questions about this course, tasks or find some errors,
you may ask questions and participate in discussions on repository [issues](https://github.com/jetbrains-academy/csc-kotlin-course/issues).

## Contribution

Please be sure to review [project's contributing guidelines](./contributing.md) to learn how to help the project.