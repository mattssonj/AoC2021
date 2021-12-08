package day5

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    when (System.getenv("part")) {
        "part2" -> println(solution2(readInput("day05/input.txt")))
        else -> println(solution1(readInput("day05/input.txt")))
    }
}

fun solution1(points: Points): Int {
    val mapOfPoints = mutableMapOf<Point, Int>()
    points.filter { isLine(it.first, it.second) }
        .forEach { drawLine(it.first, it.second, mapOfPoints) }
    return mapOfPoints.filter { it.value > 1 }.size
}

fun solution2(points: Points): Int {
    val mapOfPoints = mutableMapOf<Point, Int>()
    points.map { if (isLine(it.first, it.second)) Pair(it, true) else Pair(it, false) }
        .forEach { (it, isLine) ->
            if (isLine) drawLine(it.first, it.second, mapOfPoints)
            else drawDiagonal(it.first, it.second, mapOfPoints)
        }
    return mapOfPoints.filter { it.value > 1 }.size
}

fun drawLine(p1: Point, p2: Point, map: MutableMap<Point, Int>) {
    val (xMin, xMax) = if (p1.first > p2.first) Pair(p2.first, p1.first) else Pair(p1.first, p2.first)
    val (yMin, yMax) = if (p1.second > p2.second) Pair(p2.second, p1.second) else Pair(p1.second, p2.second)

    (xMin..xMax).forEach { x ->
        (yMin..yMax).forEach { y ->
            val p = Pair(x, y)
            map[p] = map.getOrDefault(p, 0) + 1
        }
    }
}

fun drawDiagonal(p1: Point, p2: Point, map: MutableMap<Point, Int>) {
    val xInc = if ((p1.first - p2.first) < 0) 1 else -1
    val yInc = if ((p1.second - p2.second) < 0) 1 else -1

    var nextPoint = p1
    (1..(p1.first - p2.first).absoluteValue + 1).forEach { _ ->
        map[nextPoint] = map.getOrDefault(nextPoint, 0) + 1
        nextPoint = nextPoint.copy(nextPoint.first + xInc, nextPoint.second + yInc)
    }
}

fun isLine(p1: Point, p2: Point): Boolean = p1.first == p2.first || p1.second == p2.second

fun readInput(path: String): Points = File(path).readLines()
    .map { it.split(" -> ").flatMap { it.split(",").chunked(2).map { Pair(it[0].toInt(), it[1].toInt()) } } }
    .map { Pair(it[0], it[1]) }

typealias Points = List<Pair<Point, Point>>
typealias Point = Pair<Int, Int>
