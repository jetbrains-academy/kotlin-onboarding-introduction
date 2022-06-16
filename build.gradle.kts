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

subprojects.filter { it.name != "util" }.forEach { _ ->
    dependencies {
        implementation(project(":util"))
    }
}

allprojects {
    apply {
        plugin("application")
        plugin("java")
        plugin("kotlin")
    }

//    sourceCompatibility = JavaVersion.VERSION_1_8

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib", KotlinCompilerVersion.VERSION))
        implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
        runtimeOnly("org.junit.platform:junit-platform-console:1.8.2")
    }

    tasks {
        withType<JavaCompile> {
            sourceCompatibility = "1.8"
            targetCompatibility = "1.8"
        }
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
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
            addTestOutputListener { testDescriptor, outputEvent -> }
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src")
        getByName("test").java.srcDirs("src")
    }

//    val mainClassName = project.property("mainClass") ?: ""

//    if (project.hasProperty("educationalRun") && "true".equals(project.property("educationalRun").toString(), ignoreCase = true)) {
//        val runOutput = ByteArrayOutputStream()
//        tasks.run.setStandardOutput(runOutput)
//        tasks.run.doLast { printOutput(runOutput) }
//    }
}

//project(":util") {
//    dependencies {
//        implementation("junit:junit:4.12")
//    }
//}

//configure(subprojects.findAll { it.name != "util" }) {
//    dependencies {
//        implementation(project(":util").sourceSets.main.output)
//        testImplementation(project(":util").sourceSets.test.output)
//    }
//}
