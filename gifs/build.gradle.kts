group = rootProject.group
version = rootProject.version

val suffixes = listOf("FinishTheGame", "FinishGame", "CreateTheApp", "FinishTheApp", "CompleteTheProject")
val stepsForGifs = rootProject.subprojects.filter { suffixes.any{ s -> s in it.name } }

dependencies {
    stepsForGifs.forEach {
        implementation(project(":${it.name}"))
    }
}
