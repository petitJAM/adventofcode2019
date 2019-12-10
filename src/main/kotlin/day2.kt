fun day2() {
    val input = readInputFile(2).inputStr.split(",").map(String::toInt)

    val modifiedInput = input.mapIndexed { index, i ->
        when (index) {
            1 -> 12
            2 -> 2
            else -> i
        }
    }

    val intcode = Intcode(modifiedInput)

    intcode.execute()

    val part1Answer = intcode.codes[0]

    println("Part 1: $part1Answer")

    part2(input)
}

private fun part2(initialProgram: List<Int>) {

    /*
     * position1 = noun
     * position2 = verb
     *
     * noun/verb -> [0, 99]
     *
     * what pair of inputs produces 19690720?
     */

    val target = 19690720

    var answer: Int? = null

    (0..99).forEach nounLoop@ { noun ->
        (0..99).forEach { verb ->
            val modifiedProgram = initialProgram.mapIndexed { index, i ->
                when (index) {
                    1 -> noun
                    2 -> verb
                    else -> i
                }
            }

            val intcode = Intcode(modifiedProgram)
            intcode.execute()
            val result = intcode.codes[0]
            if (result == target) {
                answer = 100 * noun + verb
                return@nounLoop
            }
        }
    }

    println("Part 2: $answer")
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
