[![official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Gradle Build](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build.yml)
[![Gradle Build With Detekt](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build-with-detekt.yml/badge.svg)](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-build-with-detekt.yml)
[![Gradle Test](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-test.yml/badge.svg)](https://github.com/jetbrains-academy/csc-kotlin-course/actions/workflows/gradle-test.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Kotlin Onboarding: Introduction

This course is designed for novices in Kotlin
and focuses on the basic concepts of the Kotlin language.

Each lesson of the course is built in the form of a project:
step by step, by completing different small tasks,
you will get a finished small project in the end.
At the end of each lesson, an additional similar project will be offered:
it includes all the topics of the lesson but is not contain the theory part.

Note, this course **does not provide a detailed explanation** of the basic concepts,
like variables: it just shows how to use them in Kotlin and can briefly remind you the definitions.

All topics will be accompanied by links to [the official Kotlin documentation](https://kotlinlang.org/docs/home.html),
which you can read later.

**Topics covered:**
- the entry point for a Kotlin program;
- variables;
- functions;
- loops;
- ranges;
- `if` expressions;
- multi-row strings and string builder;
- basic facts about the null safety mechanism;
- basic facts about collections and lambda expressions.

After this course, you will be ready to write basic console applications in the Kotlin-like style.

## Technical requirements

Before starting this course, check the following requirements.

1. Your computer needs to have a stable internet connection.
2. Git version control system needs to be installed on your computer (link to the git site: https://git-scm.com/).
3. Make sure that the path to the root folder of the course does not contain spaces, special characters, or non-latin characters.

The course is integrated into the [Intellij Idea IDE](https://www.jetbrains.com/idea/), which has a community free license. 
You may use this license to complete the course. 
If you have some troubles with the course installation, fell free to contact us by email education@jetbrains.com.


## Getting started

**TODO: add course link**
This course is available on JetBrains Marketplace and can be installed from the 
[IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE directly, but you can also use this course in 
the course creator mode or create a course archive from the source code.

### Getting started: create a course preview from the source code

You can create a [course preview](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educator-start-guide.html#preview_course) from the source code:
1. Clone this repository: 
```text
git clone https://github.com/jetbrains-academy/csc-kotlin-course.git
```

2. Build the project:
```text
./gradlew build
```

3. Install the [EduTools](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educational-products.html) plugin from JetBrains Marketplace.

4. Create a new [course preview](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educator-start-guide.html#preview_course).

### Getting started: create a course archive

You can create a [course archive](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educator-start-guide.html#fe7010f2) from the source code:
1. Clone this repository:
```text
git clone https://github.com/jetbrains-academy/csc-kotlin-course.git
```

2. Build the project:
```text
./gradlew build
```

3. Install the [EduTools](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educational-products.html) plugin from JetBrains Marketplace.

4. Create a new [course archive](https://plugins.jetbrains.com/plugin/10081-edutools/docs/educator-start-guide.html#fe7010f2).

## Run tests

To run tests locally, you just need to build the project and run the following command:

```text
./gradlew test
```

The tests use the Java Reflection API under the hood to check the user tasks.

## Want to know more?

If you have questions about the course or the tasks or if you find some errors,
you can ask questions and participate in discussions in repository [issues](https://github.com/jetbrains-academy/csc-kotlin-course/issues).

## Contribution

Please be sure to review the [project's contributing guidelines](./contributing.md) to learn how to help the project.
