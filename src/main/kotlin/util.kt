import java.io.File

fun readInputFile(fileName: String): List<String> {
    val x = ClassLoader.getSystemResource(fileName)
    val file = File(x.file)
    return file.readText().trim().split("\n")
}

fun List<String>.toIntList(): List<Int> {
    return this.map { it.toInt() }
}
