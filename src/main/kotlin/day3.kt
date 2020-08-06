fun day3() {
    val (wirePath1, wirePath2) = readInputFile(3)
        .toList()
        .map { wire ->
            Path(wire.split(","))
        }
}

class Path(pathThings: List<String>) {

    val instructions = pathThings.map { Instruction(it) }

    fun enumerateAllPointsOnPath(): List<Point> {
        instructionsToPoints() // enumerate as pairs of elements (0/1, 1/2, 2/3, ...)
        TODO()
    }

    private fun instructionsToPoints(): List<Point> {
        Point(0, 0)
        TODO()
    }

    class Instruction(str: String) {

        val direction = Direction.from(str[0])
        val distance = str.substring(1).toInt()

        enum class Direction(val directionStr: Char) {
            L('L'),
            R('R'),
            U('U'),
            D('D');

            companion object {

                @Throws(IllegalArgumentException::class)
                fun from(char: Char): Direction {
                    return when (char) {
                        'L' -> L
                        'R' -> R
                        'U' -> U
                        'D' -> D
                        else -> throw IllegalArgumentException("Unknown direction char: $char")
                    }
                }
            }
        }
    }
}

data class Point(val x: Int, val y: Int)
