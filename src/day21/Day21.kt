package day21

import readInput

fun main() {

    data class Operation(val op1: String, val op2: String, val operator: String)

    data class Data(val numbers: MutableMap<String, Long> = mutableMapOf(), val operations: MutableMap<String, Operation> = mutableMapOf())

    fun parseInput(input: List<String>) = input.fold(Data()) { data, it ->
        val (left, right) = it.split(": ")
        if (right.toLongOrNull() != null) {
            data.numbers[left] = right.toLong()
        } else {
            val (op1, operator, op2) = right.split(" ")
            data.operations[left] = Operation(op1, op2, operator)
        }
        data
    }


    fun calcNumber(monkey: String, numbers: MutableMap<String, Long>, operations: MutableMap<String, Operation>): Long {
        if (numbers[monkey] != null) {
            return numbers[monkey]!!
        }

        val operation = operations[monkey]!!
        val op1 = calcNumber(operation.op1, numbers, operations)
        val op2 = calcNumber(operation.op2, numbers, operations)

        val number = when(operation.operator) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "/" -> op1 / op2
            else -> 0
        }

        numbers[monkey] = number
        return number
    }

    fun part1(input: List<String>): Long {
        val data = parseInput(input)
        return calcNumber("root", data.numbers, data.operations)
    }

    fun part2(input: List<String>): Int {
        val data = parseInput(input)

        return 0
    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInput("Day21_test")) == 152L)
    println(part1(readInput("Day21")))

    check(part2(readInput("Day21_test")) == 301)
    println(part2(readInput("Day21")))
}