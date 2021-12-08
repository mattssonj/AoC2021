package day7

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    when (System.getenv("part")) {
        "part2" -> println(solution2(readInput("day07/input.txt")))
        else -> println(solution1(readInput("day07/input.txt")))
    }
}

fun solution1(crabPositions: List<Int>) = crabPositions.minOf { crabPos  -> crabPositions.sumOf { (crabPos - it).absoluteValue } }
fun solution2(crabPositions: List<Int>) = (0..crabPositions.maxOf { it }).minOf { somePos  -> crabPositions.sumOf { increasedMove(somePos, it) } }

fun readInput(path: String): List<Int> = File(path).readLines()[0].split(",").map { it.toInt() }

fun increasedMove(pos1: Int, pos2: Int) = if (pos1 - pos2 == 0) 0 else (1..(pos1 - pos2).absoluteValue).sum()