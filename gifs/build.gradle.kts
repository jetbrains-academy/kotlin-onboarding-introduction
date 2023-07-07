import java.nio.file.Path
import java.nio.file.Paths

group = rootProject.group
version = rootProject.version

val suffixes = listOf("FinishTheGame", "FinishGame", "CreateTheApp", "FinishTheApp", "CompleteTheProject")
val stepsForGifs = rootProject.subprojects.filter { suffixes.any { s -> s in it.name } }

dependencies {
    stepsForGifs.forEach {
        implementation(project(":${it.name}"))
    }
}

val andersonVersion = "0.3.1"

tasks.register<Exec>("installAnderson") {
    group = "gifs"
    setCommandLine("pip3", "install", "git+https://github.com/GirZ0n/anderson.git@v$andersonVersion")
}

abstract class GenerateGif : Exec() {
    @get:Input
    abstract val lessonPackageName: Property<String>

    @get:Input
    abstract val gifConfigName: Property<String>

    @get:Input
    abstract val output: Property<Path>

    override fun exec() {
        val packageName = "org.jetbrains.kotlin.course.${lessonPackageName.get()}"

        val jarPath = Paths.get("build", "libs", "gifs-all.jar")
        val runnerClasspath = "$packageName.runners.${gifConfigName.get()}Kt"

        val configPath = Paths.get(
            "src",
            "main",
            "kotlin",
            packageName.replace(".", File.separator),
            "configs",
            "${gifConfigName.get()}.yaml"
        )

        setCommandLine(
            "anderson",
            "java -cp $jarPath $runnerClasspath",
            output.get().toString(),
            configPath.toString(),
        )

        super.exec()
    }
}


val lessonNameToGifConfigNames =
    mapOf("almost.done" to listOf("ChooseFilter", "ChoosePicture", "GetPicture", "Photoshop", "TrimmedPicture"))


val lessonGradleTasks = lessonNameToGifConfigNames.forEach { (lessonName, gifConfigNames) ->
    val gifGradleTasks = gifConfigNames.map {
        tasks.register<GenerateGif>("generate-$lessonName-$it") {
            dependsOn(tasks.findByName("installAnderson"))
            dependsOn(tasks.shadowJar)
            group = "gifs"

            lessonPackageName.set(lessonName)
            gifConfigName.set(it)
            output.set(
                rootProject.childProjects["utils"]!!.projectDir.toPath()
                    .resolve(Path.of("src", "main", "resources", "images", "part1", lessonName))
            )
        }
    }

    tasks.register("generate-$lessonName") {
        group = "gifs"
        setDependsOn(gifGradleTasks)
    }
}

tasks.register("generateGifs") {
    dependsOn(lessonGradleTasks)
    group = "gifs"
}
