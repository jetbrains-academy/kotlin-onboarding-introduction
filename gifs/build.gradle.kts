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

abstract class GenerateGif : Exec() {
    @get:Input
    abstract val courseTaskName: Property<String>

    @get:Input
    abstract val gifName: Property<String>

    @get:Input
    abstract val output: Property<Path>

    override fun exec() {
        val packageName = "org.jetbrains.kotlin.course.${courseTaskName.get()}"

        val jarPath = Paths.get("build", "libs", "gifs-all.jar")
        val runnerClasspath = "$packageName.runners.${gifName.get()}Kt"

        val configPath = Paths.get(
            "src",
            "main",
            "kotlin",
            packageName.replace(".", File.separator),
            "configs",
            "${gifName.get()}.yaml"
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


// TODO: namings
val gifsToGenerate = mapOf(
    "almost.done" to listOf("ChooseFilter", "ChoosePicture", "GetPicture", "Photoshop")
)


gifsToGenerate.forEach { (taskName, gifNames) ->
    val gifTasks = gifNames.map {
        tasks.register<GenerateGif>("generate-$taskName-$it") {
            group = "gifs"
            courseTaskName.set(taskName)
            gifName.set(it)
            output.set(buildDir.toPath()) // TODO: save to the resources folder
        }
    }

    tasks.register("generate-$taskName") {
        group = "gifs"
        setDependsOn(gifTasks)
    }
}
