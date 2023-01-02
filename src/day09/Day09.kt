package day09

import readInput
import kotlin.math.tanh

fun main() {
    data class Move(val direction: String, val steps: Int)
    data class Position(val row: Int, val col: Int)

    fun parseInput(input: List<String>) = input.map {
        val (d, s) = it.split(" ")
        Move(d, s.toInt())
    }

    fun part1(input: List<String>): Int {
        val moves = parseInput(input)
        var head = Position(100, 100)
        var tail = Position(100, 100)
        val tailPositions = mutableSetOf(tail)

        moves.forEach {

        }


        return tailPositions.size
    }

//    fun part2(input: List<String>) {
//    }

//     test if implementation meets criteria from the description, like:
    check(part1(readInput("Day09_test")) == 13)
    println(part1(readInput("Day09")))

//    part2(readInput("Day10_test"))
//    part2(readInput("Day10"))
}
