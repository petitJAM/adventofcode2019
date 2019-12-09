import java.io.File

fun readInputFile(fileName: String): String {
    val x = ClassLoader.getSystemResource(fileName)
    val file = File(x.file)
    return file.readText().trim()
}
