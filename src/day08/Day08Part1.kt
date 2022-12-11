package day08

import readInput

fun main() {
    fun parseInput(input: List<String>) = input.map { row -> row.split("").filter { it.isNotEmpty() }.map { it.toInt() } }

    fun lefViewCount(data: List<List<Int>>, visited: MutableList<MutableList<Boolean>>) {
        for (r in data.indices) {
            var height = -1
            for (c in data[r].indices) {
                if(data[r][c] > height) {
                    height = data[r][c]
                    visited[r][c] = true
                }
            }
        }
    }

    fun topViewCount(data: List<List<Int>>, visited: MutableList<MutableList<Boolean>>) {
        for (c in data[0].indices) {
            var height = -1
            for (r in data.indices) {
                if(data[r][c] > height) {
                    height = data[r][c]
                    visited[r][c] = true
                }
            }
        }
    }

    fun rightViewCount(data: List<List<Int>>, visited: MutableList<MutableList<Boolean>>) {
        for (r in data.indices) {
            var height = -1
            for (c in data[r].lastIndex downTo  0) {
                if(data[r][c] > height) {
                    height = data[r][c]
                    visited[r][c] = true
                }
            }
        }
    }

    fun bottomViewCount(data: List<List<Int>>, visited: MutableList<MutableList<Boolean>>) {
        for (c in data[data.lastIndex].indices) {
            var height = -1
            for (r in data.lastIndex downTo 0) {
                if(data[r][c] > height) {
                    height = data[r][c]
                    visited[r][c] = true
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val data = parseInput(input)
        val visited = MutableList(data.size) { MutableList(data[0].size) { false } }

        lefViewCount(data, visited)
        topViewCount(data, visited)
        rightViewCount(data, visited)
        bottomViewCount(data, visited)

        println(visited)

        return visited.sumOf { r -> r.count { it } }
    }

//    fun part2(input: List<String>): Int {
//        val data = parseInput(input)
//
//        return score
//    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day08_test")) == 21)
    println(part1(readInput("Day08")))

//    check(part2(readInput("Day02_test")) == 12)
//    println(part2(readInput("Day02")))
}
