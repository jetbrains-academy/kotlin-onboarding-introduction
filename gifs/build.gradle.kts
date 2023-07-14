import java.nio.file.Path
import java.nio.file.Paths

group = rootProject.group
version = rootProject.version

dependencies {
    rootProject.subprojects.filter { "CompleteTheProject" in it.name  }.forEach {
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
        val packageName = "jetbrains.kotlin.course.gifs.${lessonPackageName.get()}"

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


val lessonNameToGifConfigNames = mapOf(
    "first.date" to listOf("UserInput", "Game"),
    "chat" to listOf("Game"),
    "warmup" to listOf("SafeUserInput", "Game"),
    "hangman" to listOf("Game"),
    "almost.done" to listOf("ChooseFilter", "ChoosePicture", "GetPicture", "Photoshop", "TrimmedPicture"),
    "last.push" to listOf("App"),
)


val lessonGradleTasks = lessonNameToGifConfigNames.map { (lessonName, gifConfigNames) ->
    val gifGradleTasks = gifConfigNames.map {
        tasks.register<GenerateGif>("generate-$lessonName-$it") {
            dependsOn(tasks.shadowJar)
            dependsOn(tasks.findByName("installAnderson"))
            tasks.findByName("installAnderson")!!.shouldRunAfter(tasks.shadowJar)

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
    setDependsOn(lessonGradleTasks)
    group = "gifs"
}
