package org.vorgi.org.vorgi.advent2015.day3

import org.vorgi.org.vorgi.advent2015.Utils


class Day3 {

  data class Point(val x:Int, val y:Int)

  fun start() {
    var text1="^v^v^v^v^v<><><>"

    var result1=processDirections(text1)

    println("result1 = $result1")
    check(result1==3)

    val text2= Utils.readInputAsText("day3")

    val result2=processDirections(text2)
    println("result2 = $result2")

    var text3="^v^v^v^v^v"

    val result3=processDirections2(text3)

    println("result3 = $result3")

    val result4=processDirections2(text2)
    println("result4 = $result4")
  }

  private fun processDirections(text: String): Int {
    var currentPos=Point(0,0)
    val posList:MutableList<Point> = mutableListOf(currentPos)

    text.forEach { c ->
      val newPoint= nextPos(c, currentPos)
      if(!posList.contains(newPoint)) {
        posList += (newPoint)
      }
      currentPos=newPoint

    }

    return posList.size
  }

  private fun processDirections2(text: String): Int {
    val currentPos= mutableListOf( Point(0,0), Point(0,0) )
    val posList:MutableList<Point> = mutableListOf(Point(0,0))

    var santaIndex=0

    text.forEach { c ->
      val newPoint= nextPos(c, currentPos[santaIndex])
      if(!posList.contains(newPoint)) {
        posList += (newPoint)
      }
      currentPos[santaIndex]=newPoint
      santaIndex++
      santaIndex%=2
    }

    return posList.size
  }

  private fun nextPos(
    c: Char,
    currentPos: Point
  ) = when (c) {
    '^' -> Point(currentPos.x, currentPos.y - 1)
    'v' -> Point(currentPos.x, currentPos.y + 1)
    '>' -> Point(currentPos.x + 1, currentPos.y)
    '<' -> Point(currentPos.x - 1, currentPos.y)
    else -> throw ArrayIndexOutOfBoundsException("fail")
  }


}

fun main() {
  val day3=Day3()
  day3.start()
}