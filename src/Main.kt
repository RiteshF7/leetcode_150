import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {


    mergeSortedArray()


//    runBlocking {
//        DirGenerator().generate();
//    }

}

fun getCurrentProjectDir(): String {
    return System.getProperty("user.dir")
}