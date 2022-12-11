package day08

import readInput

fun main() {
    fun parseInput(input: List<String>) = input.map { row -> row.split("").filter { it.isNotEmpty() }.map { it.toInt() } }

//    fun isEdge(data: List<List<Int>>, r: Int, c: Int): Boolean {
//       return r == 0 || r == data.lastIndex || c == 0 || c == data[r].lastIndex
//    }

//    fun isVisibleFromLeft(data: List<List<Int>>, r: Int, c: Int): Boolean {
//        if (data[r][c] <= data[r][c-1]) return false
//
//        var column = c - 1
//        while (column > 0 && data[r][column] >= data[r][column - 1]) {
//            column--
//        }
//
//        return column == 0
//    }
//
//    fun isVisibleFromTop(data: List<List<Int>>, r: Int, c: Int): Boolean {
//        if (data[r][c] <= data[r - 1][c]) return false
//
//        var row = r - 1
//        while (row > 0 && data[row][c] >= data[row - 1][c]) {
//            row--
//        }
//
//        return row == 0
//    }
//
//    fun isVisibleFromRight(data: List<List<Int>>, r: Int, c: Int): Boolean {
//        if (data[r][c] <= data[r][c + 1]) return false
//
//        var column = c + 1
//        while (column < data[r].lastIndex && data[r][column] >= data[r][column + 1]) {
//            column++
//        }
//
//        return column == data[r].lastIndex
//    }
//
//    fun isVisibleFromBottom(data: List<List<Int>>, r: Int, c: Int): Boolean {
//        if (data[r][c] <= data[r + 1][c]) return false
//
//        var row = r + 1
//        while (row < data.lastIndex && data[row][c] >= data[row + 1][c]) {
//            row++
//        }
//
//        return row == data.lastIndex
//    }
//
//    fun isVisible(data: List<List<Int>>, r: Int, c: Int): Boolean {
//        return  isEdge(data, r, c) || isVisibleFromLeft(data, r, c) || isVisibleFromTop(data, r, c) || isVisibleFromRight(data, r, c) || isVisibleFromBottom(data, r, c)
//    }

//    fun part1(input: List<String>): Int {
//        val data = parseInput(input)
//        var visibleTrees = 0
//
//        for (r in data.indices) {
//            for (c in data[r].indices) {
//                if (isVisible(data, r, c)) {
//                    visibleTrees++
//                }
//            }
//        }
//
////        println(visibleTrees)
//        return visibleTrees
//    }

    fun findLeftDistance(data: List<List<Int>>, row: Int, col: Int): Int {
        var distance = 0

        var c = col
        while (c > 0) {
            distance++
            if(data[row][c - 1] >= data[row][col]) break
            c--
        }

        return distance
    }

    fun findTopDistance(data: List<List<Int>>, row: Int, col: Int): Int {
        var distance = 0

        var r = row
        while (r > 0) {
            distance++
            if(data[r - 1][col] >= data[row][col]) break
            r--
        }

        return distance
    }

    fun findRightDistance(data: List<List<Int>>, row: Int, col: Int): Int {
        var distance = 0

        var c = col
        while (c < data[row].lastIndex) {
            distance++
            if(data[row][c + 1] >= data[row][col]) break
            c++
        }

        return distance

    }

    fun findBottomDistance(data: List<List<Int>>, row: Int, col: Int): Int {
        var distance = 0

        var r = row
        while (r < data.lastIndex) {
            distance++
            if(data[r + 1][col] >= data[row][col]) break
            r++
        }

        return distance
    }

    fun findDistance(data: List<List<Int>>, r: Int, c: Int): Int {
        val leftDistance = findLeftDistance(data, r, c)
        val topDistance = findTopDistance(data, r, c)
        val rightDistance = findRightDistance(data, r, c)
        val bottomDistance = findBottomDistance(data, r, c)

//        println("$r,$c -> $leftDistance,$topDistance,$rightDistance,$bottomDistance")

        return  leftDistance * topDistance * rightDistance * bottomDistance
    }

    fun part2(input: List<String>): Int {
        val data = parseInput(input)
        var maxDistance = 0

        for (r in data.indices) {
            for (c in data[r].indices) {
                val distance = findDistance(data, r, c)
                if (distance > maxDistance) maxDistance = distance
            }
        }

        return maxDistance
    }

    // test if implementation meets criteria from the description, like:
//    check(part1(readInput("Day08_test")) == 21)
//    println(part1(readInput("Day08")))

    check(part2(readInput("Day08_test")) == 8)
    println(part2(readInput("Day08")))
}
