package day10

import readInput

fun main() {
    data class Instruction(val command: String, val value: Int? = null)

    fun parseInput(input: List<String>) = input.map { line ->
        val c = line.split(" ").filter { it.isNotEmpty() }

        if(c[0] == "noop") {
            Instruction(c[0])
        } else {
            Instruction(c[0], c[1].toInt())
        }
    }

    fun part1(input: List<String>): Int {
        val instructions = parseInput(input)
        val cycles = setOf(20, 60, 100, 140, 180, 220)
        var noOfCycles = 0
        var x = 1
        var signalsStrength = 0

        instructions.forEach {
            noOfCycles++
            if (cycles.contains(noOfCycles)) {
                signalsStrength += noOfCycles * x
            }

            if (it.command == "addx") {
                noOfCycles++
                if (cycles.contains(noOfCycles)) {
                    signalsStrength += noOfCycles * x
                }
                x += it.value!!
            }

        }

        return signalsStrength
    }

    fun part2(input: List<String>) {
        val instructions = parseInput(input)

        var noOfCycles = 0
        var x = 1

        instructions.forEach {
//            println("$noOfCycles, $x")

            noOfCycles++

            if (noOfCycles >= x && noOfCycles < x + 3) {
                print("#")
            } else {
                print(".")
            }

            if (noOfCycles % 40 == 0) {
                noOfCycles = 0
                println()
            }

            if (it.command == "addx") {
                noOfCycles++
                if (noOfCycles >= x && noOfCycles < x + 3) {
                    print("#")
                } else {
                    print(".")
                }
                if (noOfCycles % 40 == 0) {
                    noOfCycles = 0
                    println()
                }
                x += it.value!!
            }
        }

    }

//     test if implementation meets criteria from the description, like:
    check(part1(readInput("Day10_test")) == 13140)
    println(part1(readInput("Day10")))

//    part2(readInput("Day10_test"))
    part2(readInput("Day10"))
}
