package search

import java.io.File

class Search {
    companion object {
        val list = mutableListOf<String>()
        val map = mutableMapOf<String, Int>()
        val found = mutableListOf<Int>()
        var result = 0
        const val text = "Enter a name or email to search all suitable people."
    }

    private fun menu() {
        println("=== Menu ===")
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")

        when(readLine()!!.toInt()) {
            1 -> {
                findPerson()
                println()
            }
            2 -> {
                printAll()
                println()
            }
            0 -> {
                println()
                println("Bye!")
            }
            else -> {
                println()
                println("Incorrect option! Try again.")
                println()
                menu()
            }
        }
    }

    fun addPearson() {
        val fileName = "names.txt"
        val lines = File(fileName).readLines()
        for ((i, add) in lines.withIndex()) {
            list += add
            map[list[i].split(" ").toString()] = i
        }
        menu()
    }

    private fun findPerson() {
        println("Select a matching strategy: ALL, ANY, NONE")
        when (readLine()!!){
            "ANY" -> any()
            "ALL" -> all()
            "NONE" -> none()
        }

        println()
        menu()
    }

    private fun all() {
        var i = 0
        println(text)
        val keyword = readLine()!!.split(" ")

        loop@ while (i <= keyword.lastIndex && keyword[i].length > 1) {
            for ((aux, search) in map.keys.withIndex()) {
                if (keyword[i].toLowerCase() in search.toLowerCase()) {
                    if (aux in found) {
                        break@loop
                    } else {
                        if (found.size == 0 && i > 0) {
                        } else {
                            result++
                            found += aux
                        }
                    }
                }
            }
            i++
        }
        if (result != 0) {
            println("$result persons found: ")
            for (index in found.indices) {
                println(list[found[index]])
            }
        } else {
            println("No matching people found.")
        }
        result = 0
        found.clear()
    }

    private fun any() {
        println(text)
        val keyword = readLine()!!.split(" ")
        var i = 0
        while (i <= keyword.lastIndex && keyword[i].length > 1) {
            for ((aux, search) in map.keys.withIndex()) {
                if (keyword[i].toLowerCase() in search.toLowerCase()) {
                    result++
                    found += aux
                }
            }
            i++
        }
        if (result != 0) {
            println("$result persons found: ")
            for (index in found.indices) {
                println(list[found[index]])
            }
        } else {
            println("No matching people found.")
        }
        result = 0
        found.clear()
    }

    private fun none() {
        println(text)
        var i = 0
        val keyword = readLine()!!.split(" ")

        while (i <= keyword.lastIndex && keyword[i].length > 1) {
            for ((aux, search) in map.keys.withIndex()) {
                if (keyword[i].toLowerCase() in search.toLowerCase()) {
                    result++
                    found += aux
                }
            }
            i++
        }
        if (result != 0) {
            println("$result persons found: ")
            for (index in list.indices) {
                if (index in found) {
                    continue
                } else {
                    println(list[index])
                }
            }
        } else {
            println("No matching people found.")
        }
        result = 0
        found.clear()
    }

    private fun printAll() {
        println()
        println("=== List of people ===")
        for (print in list.indices) {
            println(list[print])
        }
        println()
        menu()
    }
}
fun main() {
    Search().addPearson()
}