package day4

import readInput

fun main() {
    fun parseInput(input: List<String>) =
        input.map { pair -> pair.split(",").map { sections -> sections.split("-").map { it.toInt() } } }


    fun isCover(range1: List<Int>, range2: List<Int>): Boolean {
        return range2[0] >= range1[0] && range2[1] <= range1[1]
    }

    fun part1(input: List<String>): Int {
        val data = parseInput(input)

        var overlappingPairs = 0

        for (pair in data) {
            if(isCover(pair[0], pair[1]) || isCover(pair[1], pair[0])) {
                overlappingPairs++
            }
        }

        return overlappingPairs
    }

    fun isOverlap(range1: List<Int>, range2: List<Int>): Boolean {
        return range2[0] >= range1[0] && range2[0] <= range1[1]
    }

    fun part2(input: List<String>): Int {
        val data = parseInput(input)

        var overlappingPairs = 0

        for (pair in data) {
            if(isOverlap(pair[0], pair[1]) || isOverlap(pair[1], pair[0])) {
                overlappingPairs++
            }
        }

        return overlappingPairs
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day04_test")) == 2)
    println(part1(readInput("Day04")))

    check(part2(readInput("Day04_test")) == 4)
    println(part2(readInput("Day04")))
}
