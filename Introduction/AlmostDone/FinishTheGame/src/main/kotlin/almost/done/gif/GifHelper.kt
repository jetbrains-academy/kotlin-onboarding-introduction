package almost.done.gif

import chooseFilter
import choosePicture
import getPicture
import photoshop
import simba
import trimPicture

fun runChooseFilter() {
    chooseFilter()
}

fun runChoosePicture() {
    choosePicture()
}

fun runGetPicture() {
    getPicture()
}

fun runTrimmedPicture() {
     println("// The original image:")
     println(simba)
     readln()
     println("// The final image:")
     println(trimPicture(simba))
}

fun runPhotoshop() {
    photoshop()
}
