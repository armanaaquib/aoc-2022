fun main() {
    fun part1(input: List<String>): Int {
        var mostCalories = Int.MIN_VALUE
        var currentCalories = 0

        for(calories in input) {
            if(calories == "") {
                if(currentCalories > mostCalories) {
                    mostCalories = currentCalories
                }
                currentCalories = 0
            } else {
                currentCalories += calories.toInt()
            }
        }

        if(currentCalories > mostCalories) {
            mostCalories = currentCalories
        }

        return mostCalories
    }

    fun part2(input: List<String>): Int {
        val listOfCalories = mutableListOf<Int>()
        var currentCalories = 0

        for(calories in input) {
            if(calories == "") {
                listOfCalories.add(currentCalories)
                currentCalories = 0
            } else {
                currentCalories += calories.toInt()
            }
        }

        listOfCalories.add(currentCalories)
        listOfCalories.sort()

        val size = listOfCalories.size
        return listOfCalories[size - 1] + listOfCalories[size - 2] + listOfCalories[size - 3]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
