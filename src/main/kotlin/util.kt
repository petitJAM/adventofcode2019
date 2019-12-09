import java.io.File

inline class Input(private val inputStr: String) {

    fun toList(): List<String> {
        return inputStr.split("\n")
    }

    fun toIntList(): List<Int> {
        return toList().map { it.toInt() }
    }
}

fun readInputFile(fileName: String): Input {
    val resourceFile = File(ClassLoader.getSystemResource(fileName).file)
    return Input(resourceFile.readText().trim())
}
