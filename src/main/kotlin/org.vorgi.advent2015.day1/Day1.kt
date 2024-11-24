package org.vorgi.org.vorgi.advent2015.day1

import org.vorgi.org.vorgi.advent2015.Utils

class Day1 {
  fun evaluateText(text:String) : Int {
    var counter = 0
    text.forEach { c ->
      if(c=='(') {
        counter++
      }
      if(c==')') {
        counter--
      }
    }
    return counter
  }

  fun start() {
    val input1 = "(()(()("

    val result1 = evaluateText(input1)

    println("result1: $result1")

    check(result1 == 3)

    val input2 = Utils.readInputAsText("day1")

    val result2 = evaluateText(input2)
    println("result2 = $result2")

    val input3 = "())"

    val result3 = findMinusOne(input3)

    println("result3: $result3")

    check(result3==3)

    val result4 = findMinusOne(input2)

    println("result4: $result4")
  }

  private fun findMinusOne(text: String): Int {
    var index=0

    var floor=0
    while (floor != -1) {
      val c=text.get(index)
      if(c=='(') {
        floor++
      }
      if(c==')') {
        floor--
      }
      index++
    }
    return index
  }

}

fun main() {
  val day1=Day1()
  day1.start()
}