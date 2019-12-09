import kotlin.math.floor

fun day1() {
    val inputs = readInputFile(1).toIntList()

    val fuelForMassModules = inputs
        .map(::calculateFuel)
        .sum()

    println("Part 1: $fuelForMassModules")

    val totalFuel = inputs
        .map(::calculateFuel)
        .map { fuelForMassModule ->
            var fuelForFuel = calculateFuel(fuelForMassModule)
            var sum = fuelForMassModule
            while (fuelForFuel > 0) {
                sum += fuelForFuel
                fuelForFuel = calculateFuel(fuelForFuel)
            }
            sum
        }
        .sum()

    println("Part 2: $totalFuel")
}

private fun calculateFuel(mass: Int): Int {
    val fuel = floor(mass / 3f).toInt() - 2
    return if (fuel < 0) 0 else fuel
}
