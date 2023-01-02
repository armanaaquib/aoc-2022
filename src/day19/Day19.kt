package day19

import readInputAsText

fun main() {
    fun parseInput(input: String) = input.split("\n\n")
        .map { elf -> elf.split("\n").map { it.toInt() } }

    fun part1(input: String): Int {
        val data = parseInput(input)

        return data.maxOf { it.sum() }
    }

    fun part2(input: String): Int {
        val data = parseInput(input)

        return data.map { it.sum() }.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInputAsText("Day01_test")) == 24000)
    println(part1(readInputAsText("Day01")))

    check(part2(readInputAsText("Day01_test")) == 45000)
    println(part2(readInputAsText("Day01")))


    /*
    -> clay-collecting robots -> clay -> obsidian-collecting robots -> obsidian -> geode-cracking robots

    Ore-collecting robots -> Ore
     */
}
