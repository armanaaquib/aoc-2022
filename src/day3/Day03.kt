package day3

import readInput

fun main() {
    fun parseInput(input: List<String>) = input.map {
        val size = it.length
        val compartment1 = it.slice(0 until (size / 2)).split("").slice(1..size / 2)
        val compartment2 = it.slice(size / 2 until size).split("").slice(1..size / 2)
        listOf(compartment1, compartment2)
    }

    fun findFirstCommon(list1: List<String>, list2: List<String>): Char {
        val second = list2.toSet()
        
        for (s in list1) {
            if(second.contains(s))
                return s[0]
        }
        
        return '0'
    }

    fun getPriority(itemType: Char): Int {
        return if(itemType in 'a'..'z') {
            itemType.code - 96
        } else {
            itemType.code - 38
        }
    }

    fun part1(input: List<String>): Int {
        val data = parseInput(input)
        var prioritiesSum = 0

        for(rucksack in data) {
            val itemType = findFirstCommon(rucksack.first(), rucksack.last())
            val priority = getPriority(itemType)
            prioritiesSum += priority
        }

        return prioritiesSum
    }

    fun part2(input: List<String>): Int {
        var prioritiesSum = 0

        for(i in input.indices step 3) {
            val second = input[i + 1].toSet()
            val third = input[i + 2].toSet()

            for (c in input[i]) {
                if (second.contains(c) && third.contains(c)) {
                    val priority = getPriority(c)
                    prioritiesSum += priority
                    break
                }
            }
        }

        return prioritiesSum
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day03_test")) == 157)
    println(part1(readInput("Day03")))

    check(part2(readInput("Day03_test")) == 70)
    println(part2(readInput("Day03")))
}
