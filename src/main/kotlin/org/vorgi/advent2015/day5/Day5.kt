package org.vorgi.org.vorgi.advent2015.day5

import org.vorgi.org.vorgi.advent2015.Utils


class Day5 {

  fun isNice(string: String): Boolean {
    // check if three vowels are present
    var vowelCount = 0
    for (index in 0..<string.length) {
      val c = string[index]
      if ("aeiou".contains(c)) {
        vowelCount++
      }
      if (vowelCount >= 3) {
        break
      }
    }
    if (vowelCount < 3) {
      return false
    }
    // now check for double
    var doubleFound = false
    for (index in 0..<string.length - 1) {
      if (string[index] == string[index + 1]) {
        doubleFound = true
        break
      }
    }
    if (!doubleFound) {
      return false
    }

    // now check for ab cd pq xy
    return !(string.contains("ab") ||
        string.contains("cd") ||
        string.contains("pq") ||
        string.contains("xy"))
  }

  inline fun hasOverlappingPair(input: String): Boolean {
    for (i in 0 until input.length - 1) {
      val pair = input.substring(i, i + 2)
      if (input.substring(i + 2).contains(pair)) {
        return true
      }
    }
    return false
  }

  inline fun hasLetterTriplet(input:String) : Boolean {
    for(index in 0..<input.length-2) {
      if(input[index]==input[index+2] && input[index+1]!=input[index]) {
        return true
      }
    }
    return false
  }

  fun isNice2(string: String): Boolean {
    if(!hasOverlappingPair(string)) {
      return false
    }
    if(!hasLetterTriplet(string)) {
      return false
    }
    return true
  }

  fun start() {
    val result1=isNice("ugknbfdgicrmopn")
    println("result1 = $result1")

    val text2 = Utils.readInput("day5")
    var result2 = 0
    for(line in text2) {
      if(isNice(line)) {
        result2++
      }
    }
    println("result2 = ${result2}")

    val result3=isNice2("ieodomkazucvgmuy")
    println("result3 = $result3")
    var result4 =0
    for(line in text2) {
      if(isNice2(line)) {
        result4++
      }
    }
    println("result4 = ${result4}")
  }

}

fun main() {
  val day5 = Day5()
  day5.start()
}