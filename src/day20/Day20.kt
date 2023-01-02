package day20

import readInput

fun main() {
    fun parseInput(input: List<String>) = input.map { it.toInt() }


    fun part1(input: List<String>): Int {
        val data = parseInput(input)
        println(data)
        return 0
    }

//    fun part2(input: List<String>): Int {
//        val data = parseInput(input)
//
//        return 0
//    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day20_test")) == 3)
    println(part1(readInput("Day20")))

//    check(part2(readInput("Day01_test")) == 45000)
//    println(part2(readInput("Day01")))
}