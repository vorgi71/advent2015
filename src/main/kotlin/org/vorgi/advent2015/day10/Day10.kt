package org.vorgi.org.vorgi.advent2015.day10

class Day10 {
  fun start() {
    var input1="1"
    for(i in 1..4) {
      input1=countAndTell(input1)
      println("> $input1")
    }

    var input2="1113222113"
    for(i in 1..40) {
      input2=countAndTell(input2)
    }
    println("input2: ${input2.length}")

    check(input2.length==252594)

    var input3="1113222113"
    for(i in 1..50) {
      input3=countAndTell(input3)
    }
    println("input3: ${input3.length}")

  }

  private fun countAndTell(input: String): String {
    var newString = StringBuilder()

    var currentChar=input[0]
    var currentCount=1
    for(i in 1..<input.length) {
      if(currentChar==input[i]) {
        currentCount++
      } else {
        newString.append(currentCount).append(currentChar)
        currentCount=1
        currentChar=input[i]
      }
    }
    newString.append(currentCount).append(currentChar)

    return newString.toString()
  }
}

fun main() {
  Day10().start()
}