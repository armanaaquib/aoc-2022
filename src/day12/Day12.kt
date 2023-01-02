package day12

import readInput
import kotlin.math.min

fun main() {
    fun parseInput(input: List<String>) = input.map { it.toCharArray().toList() }

    fun findStart(heightMap: List<List<Char>>): Pair<Int, Int> {
        for (i in heightMap.indices) {
            for (j in heightMap[0].indices) {
                if (heightMap[i][j] == 'S') {
                    return Pair(i, j)
                }
            }
        }
        throw Error("Start not found")
    }

    fun isMovable(heightMap: List<List<Char>>, sp: Pair<Int, Int>, dp: Pair<Int, Int>): Boolean {
        val dest = if (heightMap[dp.first][dp.second] == 'E') 'z' else heightMap[dp.first][dp.second]
        val source = if (heightMap[sp.first][sp.second] == 'S') 'a' else heightMap[sp.first][sp.second]
        return dest - source <= 1
    }

    fun findNextPositions(heightMap: List<List<Char>>, step: Pair<Int, Int>): List<Pair<Int, Int>> {
        val i = step.first
        val j = step.second
        val steps = mutableListOf<Pair<Int, Int>>()

        if (j - 1 >= 0 && isMovable(heightMap, step, Pair(i, j - 1))) {
            steps.add(Pair(i, j - 1))
        }

        if (i - 1 >= 0 && isMovable(heightMap, step, Pair(i - 1, j))) {
            steps.add(Pair(i - 1, j))
        }

        if (j + 1 <= heightMap[i].lastIndex && isMovable(heightMap, step, Pair(i, j + 1))) {
            steps.add(Pair(i, j + 1))
        }

        if (i + 1 <= heightMap.lastIndex && isMovable(heightMap, step, Pair(i + 1, j))) {
            steps.add(Pair(i + 1, j))
        }

        return steps
    }

    fun findFewestSteps(start: Pair<Int, Int>, heightMap: List<List<Char>>): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(start)
        var steps = 0
        val visited = mutableSetOf(start)

        while (queue.isNotEmpty()) {
            val n = queue.size

            for (i in 1..n) {
                val pos = queue.removeFirst()
                if (heightMap[pos.first][pos.second] == 'E') return steps

                for (p in findNextPositions(heightMap, pos)) {
                    if (!visited.contains(p)) {
                        queue.addLast(p)
                        visited.add(p)
                    }
                }
            }

            steps++
        }


        return Int.MAX_VALUE
    }

    fun part1(input: List<String>): Int {
        val heightMap = parseInput(input)

        val start = findStart(heightMap)
        return findFewestSteps(start, heightMap)
    }

    fun findStarts(heightMap: List<List<Char>>): List<Pair<Int, Int>> {
        val starts = mutableListOf<Pair<Int, Int>>()

        for (i in heightMap.indices) {
            for (j in heightMap[0].indices) {
                if (heightMap[i][j] == 'S' || heightMap[i][j] == 'a') {
                    starts.add(Pair(i, j))
                }
            }
        }

        return starts
    }

    fun part2(input: List<String>): Int {
        val heightMap = parseInput(input)

        var fewestSteps = Int.MAX_VALUE

        for (start in findStarts(heightMap)) {
            fewestSteps = min(fewestSteps, findFewestSteps(start, heightMap))
        }

        return fewestSteps
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day12_test")) == 31)
    println(part1(readInput("Day12")))

    check(part2(readInput("Day12_test")) == 29)
    println(part2(readInput("Day12")))
}
