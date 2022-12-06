package day5

import readInput

fun main() {
    fun parseInput(input: List<String>) =
        input.map {
            val line = it.split(" ")
            listOf(
                line[1].toInt(),
                line[3].toInt(),
                line[5].toInt()
            )
        }

    val testStacks = listOf(
        mutableListOf(),
        mutableListOf('Z', 'N'),
        mutableListOf('M', 'C', 'D'),
        mutableListOf('P')
    )

    val stacks = listOf(
        mutableListOf(),
        mutableListOf('S', 'T', 'H', 'F', 'W', 'R'),
        mutableListOf('S', 'G', 'D', 'Q', 'W'),
        mutableListOf('B', 'T', 'W'),
        mutableListOf('D', 'R', 'W', 'T', 'N', 'Q', 'Z', 'J'),
        mutableListOf('F', 'B', 'H', 'G', 'L', 'V', 'T', 'Z'),
        mutableListOf('L', 'P', 'T', 'C', 'V', 'B', 'S', 'G'),
        mutableListOf('Z', 'B', 'R', 'T', 'W', 'G', 'P'),
        mutableListOf('N', 'G', 'M', 'T', 'C', 'J', 'R'),
        mutableListOf('L', 'G', 'B', 'W'),
    )

    fun createMessage(stacks: List<List<Char>>): String {
        val message = mutableListOf<Char>()

        for(i in 1 until  stacks.size) {
           message.add(stacks[i].last())
        }

        return message.joinToString("")
    }

    fun part1(input: List<String>, stacks: List<MutableList<Char>>): String {
        val data = parseInput(input)

        for (move in data) {
            val noOfCrates = move[0]
            val from = move[1]
            val to = move[2]

            for (i in 1..noOfCrates) {
                stacks[to].add(stacks[from].removeLast())
            }
        }

        return createMessage(stacks)
    }

    fun part2(input: List<String>, stacks: List<MutableList<Char>>): String {
        val data = parseInput(input)

        for (move in data) {
            val noOfCrates = move[0]
            val from = move[1]
            val to = move[2]

            val crates = MutableList(noOfCrates) { 'x' }
            for (i in 0 until  noOfCrates) {
                crates[crates.lastIndex - i] = stacks[from].removeLast()
            }

            stacks[to].addAll(crates)
        }

        return createMessage(stacks)
    }

    // test if implementation meets criteria from the description, like:
//    check(part1(readInput("Day05_test"), testStacks) == "CMZ")
//    println(part1(readInput("Day05"), stacks))

    check(part2(readInput("Day05_test"), testStacks) == "MCD")
    println(part2(readInput("Day05"), stacks))
}
