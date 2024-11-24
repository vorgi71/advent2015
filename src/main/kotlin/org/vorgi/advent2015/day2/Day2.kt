package org.vorgi.org.vorgi.advent2015.day2

import org.vorgi.org.vorgi.advent2015.Utils

class Day2 {

  fun start() {
    val text1 =
      """2x3x4
      |1x1x10
    """.trimMargin().split("\n").toList()
    val result1 = text1.sumOf { calculateArea(it) }
    println("result1 = ${result1}")

    check(result1 == 101)

    val text2 = Utils.readInput("day2")

    val result2 = text2.sumOf { calculateArea(it) }

    println("result2 = ${result2}")

    val result3=text1.sumOf { line -> calculateRibbon(line) }

    println("result3 = ${result3}")

    check(result3 == 48)

    val result4 = text2.sumOf { calculateRibbon(it) }

    println("result4 = ${result4}")
  }

  private fun calculateRibbon(line: String) : Int {
    val split = line.split("x").map { it.toInt() }
    val smallestSides = split.sorted()

    var length=2*smallestSides[0]+2*smallestSides[1]
    val area = split[0]*split[1]*split[2]
    length+=area

    return length
  }

  private fun calculateArea(line: String) : Int {
    val split = line.split("x").map { it.toInt() }
    val areas= listOf(split[0]*split[1],split[1]*split[2],split[2]*split[0])

    var totalArea = areas.sumOf { it*2 }
    totalArea += areas.min()

    println("area: $totalArea")

    return totalArea
  }

}

fun main() {
  val day2 = Day2()
  day2.start()
}