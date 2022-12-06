package day2

import readInput

fun main() {
    fun parseInput(input: List<String>) = input.map { it.split(" ") }

    fun part1(input: List<String>): Int {
        val data = parseInput(input)
        val shapeScores = mapOf(Pair("X", 1), Pair("Y", 2), Pair("Z", 3))
        val scores = mapOf(
            Pair(listOf("A", "X"), 3),
            Pair(listOf("A", "Y"), 6),
            Pair(listOf("A", "Z"), 0),
            Pair(listOf("B", "X"), 0),
            Pair(listOf("B", "Y"), 3),
            Pair(listOf("B", "Z"), 6),
            Pair(listOf("C", "X"), 6),
            Pair(listOf("C", "Y"), 0),
            Pair(listOf("C", "Z"), 3),
        )

        var score = 0
        for (round in data) {
            score += shapeScores[round[1]]!!
            score += scores[round]!!
        }

        return score
    }

    fun part2(input: List<String>): Int {
        val data = parseInput(input)
        val shapeScores = mapOf(Pair("X", 1), Pair("Y", 2), Pair("Z", 3))
        val scores = mapOf(Pair("X", 0), Pair("Y", 3), Pair("Z", 6))
        val wins = mapOf(
            Pair("A", "Y"),
            Pair("B", "Z"),
            Pair("C", "X"),
        )
        val draws = mapOf(
            Pair("A", "X"),
            Pair("B", "Y"),
            Pair("C", "Z"),
        )
        val loses = mapOf(
            Pair("A", "Z"),
            Pair("B", "X"),
            Pair("C", "Y"),
        )

        val choices = mapOf(
            Pair("X", loses),
            Pair("Y", draws),
            Pair("Z", wins),
        )

        var score = 0
        for (round in data) {
            val o = round[0]
            val m = round[1]
            score += scores[m]!!
            score += shapeScores[choices[m]!![o]]!!
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day02_test")) == 15)
    println(part1(readInput("Day02")))

    check(part2(readInput("Day02_test")) == 12)
    println(part2(readInput("Day02")))
}
