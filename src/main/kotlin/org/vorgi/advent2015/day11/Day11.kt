package org.vorgi.org.vorgi.advent2015.day11

class Day11 {
  fun start() {
    var input1="hxbxxyzz"

    while(true) {
      input1 = countUp(input1)

      if(checkPassword(input1)) {
        break
      }
    }
    println("input1 = ${input1}")

    println("check3: ${checkTwoDoubles("hxbxxyzz")}")
  }

  fun String.replaceChar(index: Int, newChar: Char): String {
    require(index in 0 until this.length) { "Index out of bounds" }
    return this.substring(0, index) + newChar + this.substring(index + 1)
  }

  fun checkThreeLetters(input:String) : Boolean {
    for(i in 0..input.length-3) {
      var c=input[i]
      if(input[i+1]==c.inc() && input[i+2]==c.inc().inc()) {
        return true
      }
    }
    return false
  }

  fun checkTwoDoubles(input:String) : Boolean {
    val doubles= mutableSetOf<Char>()
    for(index in 0..<input.length-1) {
      if(input[index]==input[index+1]) {
        doubles+=input[index]
      }
    }
    return doubles.size>=2
  }

  fun checkPassword(input:String) : Boolean {
    return checkThreeLetters(input) && checkIllegal(input) && checkTwoDoubles(input)
  }

  fun checkIllegal(input: String) : Boolean {
    return !(input.contains('i') || input.contains('o') || input.contains('l'))
  }

  private fun countUp(input: String): String {
    var text=input
    var index=input.length-1
    var carry=0
    do {
      var c=text[index]
      c=c.inc()
      if(c>'z') {
        c='a'
        carry=1
      } else {
        carry=0
      }
      text=text.replaceChar(index,c)
      index--
    } while(index>=0 && carry!=0)
    return text
  }
}

fun main() {
  Day11().start()
}