package day16

import readInput
import kotlin.math.max

data class Data(val valves: MutableMap<String, List<String>> = mutableMapOf(), val rates: MutableMap<String, Int> = mutableMapOf())

fun main() {
    fun parseInput(input: List<String>) = input.fold(Data()) { data, line ->
        val sl = line.split(" ")
        val name = sl[1]
        val rate = sl[4].removePrefix("rate=").removeSuffix(";").toInt()
        val valves = sl.slice(9..sl.lastIndex).map { it.removeSuffix(",") }

        data.valves[name] = valves
        data.rates[name] = rate
        data
    }

    fun findMaxPressureRelease(
        valves: MutableMap<String, List<String>>,
        rates: MutableMap<String, Int>,
        valve: String,
        minutes: Int,
        openedValves: MutableSet<String>
    ): Int {
        if (minutes <= 0) return 0

        var newMinutes = minutes
        var pressure = 0
        val rate = rates[valve]!!
        if (rate != 0 && !openedValves.contains(valve)) {
            newMinutes--
            pressure = rate * newMinutes
            openedValves.add(valve)
        } else {
            openedValves.add(valve)
        }

        var maxPressure = 0
        for (v in valves[valve]!!) {
            maxPressure = max(maxPressure, findMaxPressureRelease(valves, rates, v, newMinutes - 1, openedValves))
        }

        return pressure + maxPressure
    }

    fun part1(input: List<String>): Int {
        val data = parseInput(input)
        val valves = data.valves
        val rates = data.rates

        val pressureRelease = findMaxPressureRelease(valves, rates, "AA", 30, mutableSetOf())
        return pressureRelease
    }

//    fun part2(input: List<String>): Int {
//        val data = parseInput(input)
//
//        return 0
//    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day16_test")) == 1651)
    println(part1(readInput("Day16")))

//    check(part2(readInput("Day01_test")) == 45000)
//    println(part2(readInput("Day01")))
}
