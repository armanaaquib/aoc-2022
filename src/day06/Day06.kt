package day06

import readInput
import readInputAsText

fun main() {
    fun parseInput(input: String) = input.split("").filter { it.isNotEmpty() }.map { it[0] }

    fun part1(input: String): Int {
        val data = parseInput(input)

        var index = 4

        while (index <= data.size) {
            val start = index - 4

            if(data.slice(start until index).toSet().size == 4) {
                return index
            }

            index++
        }

        return -1
    }

    fun part2(input: String): Int {
        val data = parseInput(input)

        var index = 14

        while (index <= data.size) {
            val start = index - 14

            if(data.slice(start until index).toSet().size == 14) {
                return index
            }

            index++
        }

        return -1
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInputAsText("Day06_test")) == 7)
    println(part1(readInputAsText("Day06")))

    check(part2(readInputAsText("Day06_test")) == 19)
    println(part2(readInputAsText("Day06")))
}
