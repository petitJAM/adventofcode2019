fun day2() {
    val input = readInputFile(2).inputStr.split(",").map(String::toInt)

    val intcode = Intcode(input)

    intcode.execute()

    val part1Answer = intcode.codes[0]

    println("Part 1: $part1Answer")
}

class Intcode(codes: List<Int>) {

    constructor(vararg codes: Int) : this(codes.toList())

    private val _codes = codes.toMutableList()

    val codes: List<Int>
        get() = _codes

    class InvalidOpcodeException(opcode: Int) : RuntimeException("Unknown opcode: $opcode")

    fun execute() {
        var position = 0

        executionLoop@ while (true) {
            when (val opcode = _codes[position]) {
                1 -> {
                    val arg1 = _codes[position + 1]
                    val arg2 = _codes[position + 2]
                    val arg3 = _codes[position + 3]

                    _codes[arg3] = _codes[arg1] + _codes[arg2]
                }
                2 -> {
                    val arg1 = _codes[position + 1]
                    val arg2 = _codes[position + 2]
                    val arg3 = _codes[position + 3]

                    _codes[arg3] = _codes[arg1] * _codes[arg2]
                }
                99 -> {
                    break@executionLoop
                }
                else -> {
                    throw InvalidOpcodeException(opcode)
                }
            }
            position += 4
        }
    }
}
