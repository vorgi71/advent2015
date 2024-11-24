package org.vorgi.org.vorgi.advent2015.day8

import org.vorgi.org.vorgi.advent2015.Utils

class Day8 {

  fun start() {
    val input1 = """""
      |"abc"
      |"aaa\"aaa"
      |"\x27"
    """.trimMargin().lines()

    val characters1 = processLines(input1)

    val result1 = subtractResults(input1, characters1)

    println("characters1 = ${characters1}")
    println("resul1: ${result1}")

    val input2 = Utils.readInput("day8")
    val characters2 = processLines(input2)
    val result2 = subtractResults(input2, characters2)

    println("result2 = ${result2}")

    val characters3 = unprocessLines(input1)

    println("characters3 = ${characters3}")
    val result3 = subtractResults(characters3,input1)
    println("result3 = ${result3}")

    val characters4 = unprocessLines(input2)
    val result4 = subtractResults(characters4,input2)

    println("result4 = ${result4}")
  }

  private fun subtractResults(input1: List<String>, characters1: List<String>): Any {
    val inputSize = input1.sumOf { it.length }
    val charactersSize = characters1.sumOf { it.length }

    return inputSize - charactersSize
  }

  private fun processLines(lines: List<String>): List<String> {
    return lines.map { line ->
      transformString(line)
    }
  }

  private fun unprocessLines(lines: List<String>): List<String> {
    return lines.map { line ->
      untransformString(line)
    }
  }

  private fun untransformString(input: String): String {
    val regexString = """(\\|")"""
    val regex = Regex(regexString)
    return "\"" + regex.replace(input) { matchResult ->
      "\\${matchResult.value}"
    } + "\""
  }

  private fun transformString(input: String): String {
    var lineToTransform = input.trim()
    if (lineToTransform.startsWith("\"")) {
      lineToTransform = lineToTransform.substring(1)
    }
    if (lineToTransform.endsWith("\"")) {
      lineToTransform = lineToTransform.substring(0..<lineToTransform.length - 1)
    }

    val regex = """\\\\|\\"|\\x[0-9a-fA-F]{2}"""
    val pattern = Regex(regex)
    return pattern.replace(lineToTransform) { matchResult ->
      when (val group = matchResult.groupValues[0]) {
        "\\\\" -> "\\"
        "\\\"" -> "\""
        else -> {
          group.substring(2).toInt(16).toChar().toString()
        }
      }
    }
  }
}

fun main() {
  Day8().start()
}