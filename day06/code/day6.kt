package day6

import java.io.File

fun main() {
    when (System.getenv("part")) {
        "part2" -> println(solution2(readInput("day06/input.txt")))
        else -> println(solution1(readInput("day06/input.txt")))
    }
}

fun solution1(fish: List<Int>) = fish.sumOf { nextDay(Evolution(it, 80)) }.toInt()
fun solution2(fish: List<Int>) = fish.sumOf { nextDay(Evolution(it, 256)) }

fun nextDay(evo: Evolution): Long {
    if (evo.isLastDay) return 1
    if (evo.isBreeding) return getCachedOrCalc(evo.mother()) + getCachedOrCalc(evo.breed())
    return getCachedOrCalc(evo.nextDay())
}

data class Evolution(val fish: Int, val day: Int) {
    val isLastDay = day == 0
    val isBreeding = fish == 0
    fun mother(): Evolution = Evolution(6, day - 1)
    fun breed(): Evolution = Evolution(8, day - 1)
    fun nextDay(): Evolution = Evolution(fish - 1, day - 1)
}

val cache = mutableMapOf<Evolution, Long>()

fun getCachedOrCalc(evo: Evolution): Long {
    if (cache.containsKey(evo)) return cache.getValue(evo)
    cache[evo] = nextDay(evo)
    return cache.getValue(evo)
}

fun readInput(path: String): List<Int> = File(path).readLines()[0].split(",").map { it.toInt() }