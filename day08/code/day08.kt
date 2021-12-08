package day8

import java.io.File

fun main() {
    when (System.getenv("part")) {
        "part2" -> println(solution2(readInput("day08/input.txt")))
        else -> println(solution1(readInput("day08/input.txt")))
    }
}

fun solution1(lines: List<String>) = lines.flatMap { it.split(" | ")[1].trim().split(" ") }
    .filter { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }.size

fun solution2(lines: List<String>): Int {
    return lines.asSequence()
        .map { it.split(" | ") }
        .map { Pair(SignalParser(it[0]), it[1]) }
        .map { (parser, displayed) -> displayed.split(" ").map { parser.asNumberString(it) } }
        .map { it.joinToString("").toInt() }
        .sum()
}

fun testInput() = readInput("day08/test.txt").onEach { println(it) }
fun readInput(path: String) = File(path).readLines()

class SignalParser(exampleData: String) {

    private val one: String
    private val four: String
    private val eight: String

    init {
        val numbers = exampleData.split(" ").map { it.toSortedSet().joinToString("") }
        this.one = numbers.first { it.isOne() }
        this.four = numbers.first { it.isFour() }
        this.eight = numbers.first { it.isEight() }
    }

    fun asNumberString(signal: String): String {
        val orderedSignal = signal.toSortedSet().joinToString("")
        return when {
            orderedSignal.isZero() -> "0"
            orderedSignal.isOne() -> "1"
            orderedSignal.isTwo() -> "2"
            orderedSignal.isThree() -> "3"
            orderedSignal.isFour() -> "4"
            orderedSignal.isFive() -> "5"
            orderedSignal.isSix() -> "6"
            orderedSignal.isSeven() -> "7"
            orderedSignal.isEight() -> "8"
            orderedSignal.isNine() -> "9"
            else -> throw RuntimeException("Unable to parse $orderedSignal with $this")
        }
    }

    private fun String.isZero() = this.length == 6 && eight.minus(four).inc(one) in this
    private fun String.isOne() = this.length == 2
    private fun String.isTwo() = this.length == 5 && four.minus(one) !in this && one !in this
    private fun String.isThree() = this.length == 5 && one in this
    private fun String.isFour() = this.length == 4
    private fun String.isFive() = this.length == 5 && four.minus(one) in this
    private fun String.isSix() = this.length == 6 && one !in this
    private fun String.isSeven() = this.length == 3
    private fun String.isEight() = this.length == 7
    private fun String.isNine() = this.length == 6 && four in this

    operator fun String.minus(other: String): String = this.filter { it !in other }
    operator fun String.contains(other: String): Boolean = other.all { this.contains(it) }
    fun String.inc(other: String): String = (this + other).toSortedSet().joinToString("")

}


