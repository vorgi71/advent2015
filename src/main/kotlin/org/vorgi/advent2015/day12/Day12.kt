package org.vorgi.org.vorgi.advent2015.day12

import org.vorgi.org.vorgi.advent2015.Utils

class Day12 {
  fun start() {
    val input1="[-1434,2,3]"
    var regexText="""[-0-9]+"""
    val regex=Regex(regexText)

    var result1=regex.findAll(input1)

    result1.forEach {
      println(">${it.value}<")
    }
    
    val input2= Utils.readInputAsText("day12")
    var result2=regex.findAll(input2)
    var sum3=result2.sumOf { 
      it.value.toInt()
    }

    println("sum3 = ${sum3}")
  }
}

fun main() {
  Day12().start()
}