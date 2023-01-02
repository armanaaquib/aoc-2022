package day07

import readInputAsText

data class File(val name: String, val size: Int)
data class Directory(
    val name: String,
    val files: MutableList<File> = mutableListOf(),
    val directories: MutableList<Directory> = mutableListOf(),
    val parent: Directory?
)

fun main() {
    fun parseFiles(list: List<String>): MutableList<File> {
        val files = mutableListOf<File>()
        list.forEach {
            val (c, name) = it.split(" ")
            if (c != "dir") {
                files.add(File(name, c.toInt()))
            }
        }
        return files
    }

    fun parseDirectories(list: List<String>, parent: Directory): MutableList<Directory> {
        val directories = mutableListOf<Directory>()
        list.forEach {
            val (c, name) = it.split(" ")
            if (c == "dir") {
                directories.add(Directory(name, parent = parent))
            }
        }
        return directories
    }

    fun parseFileSystem(commands: List<List<String>>): Directory {
        val root = Directory("/", parent = null)
        var pwd = root

        for (command in commands) {
            when(command[0].subSequence(0, 2)) {
                "cd" -> {
                    val path = command[0].removePrefix("cd ")
                    pwd = if (path == "..") {
                        pwd.parent ?: error("Invalid move.")
                    } else {
                        pwd.directories.find { it.name == path } ?: error("Directory not found.")
                    }
                }
                "ls" -> {
                    pwd.directories += parseDirectories(command.drop(1), pwd)
                    pwd.files += parseFiles(command.drop(1))
                }
            }
        }
        return root
    }

    fun parseInput(input: String): Directory {
        val commands = input.split("$ ").drop(2).map { it.trim().lines() }
        return parseFileSystem(commands)
    }

    fun calculateSizes(directory: Directory, sizes: MutableMap<String, Long>): Long {
        var size = 0L

        for (file in directory.files) {
            size += file.size
        }

        for (dir in directory.directories) {
            size += calculateSizes(dir, sizes)
        }

//        sizes[directory.name] = (sizes[directory.name] ?: 0) + size
        sizes[directory.name] = size
        return size
    }

    fun part1(input: String): Long {
        val directory = parseInput(input)
        val sizes = mutableMapOf<String, Long>()
        calculateSizes(directory, sizes)
        println(sizes)
        return sizes.values.filter { it <= 100_000 }.sum()
    }
//
//    fun part2(input: List<String>): Int {
//        val data = parseInput(input)
//
//
//        return 2
//    }

    // test if implementation meets criteria from the description, like:
    check(part1(readInputAsText("Day07_test")) == 95437L)
    println(part1(readInputAsText("Day07")))

//    check(part2(readInput("Day02_test")) == 12)
//    println(part2(readInput("Day02")))
}
