import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.6.10" apply true
    application
}

buildscript {
    extra["kotlin_version"] = "1.6.10"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.6.10"))
    }
}

fun printOutput(output: Any): Task {
    return tasks.create("printOutput") {
        println("#educational_plugin_check(er_version 1")
        val lines = output.toString().split("(?<=\n)|(?=\n)")
        for (line in lines) {
            println("#educational_plugin$line")
        }
    }
}

allprojects {
    apply {
        plugin("application")
        plugin("java")
        plugin("kotlin")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
        implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
        implementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
        runtimeOnly("org.junit.platform:junit-platform-console:1.8.2")
    }

    tasks {
        withType<JavaCompile> {
            sourceCompatibility = "9"
            targetCompatibility = "9"
        }
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "9"
        }

        withType<Test> {
            useJUnitPlatform()

            outputs.upToDateWhen { false }

            addTestListener(object : TestListener {
                override fun beforeSuite(suite: TestDescriptor) {}
                override fun beforeTest(testDescriptor: TestDescriptor) {}
                override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
                    if (result.resultType == TestResult.ResultType.FAILURE) {
                        val message = result.exception?.message ?: "Wrong answer"
                        val lines = message.split("\n")
                        println("#educational_plugin FAILED + ${lines[0]}")
                        lines.subList(1, lines.size).forEach { line ->
                            println("#educational_plugin$line")
                        }
                        // we need this to separate output of different tests
                        println()
                    }
                }

                override fun afterSuite(suite: TestDescriptor, result: TestResult) {}
            })
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src")
        getByName("test").java.srcDirs("test")
    }
}

configure(subprojects.filter { it.name != "utils" }) {
    dependencies {
        implementation(project(":utils"))
    }
}
