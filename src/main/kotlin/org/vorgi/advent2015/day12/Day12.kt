package org.vorgi.org.vorgi.advent2015.day12

import kotlinx.serialization.json.*
import org.vorgi.org.vorgi.advent2015.Utils
import kotlin.system.measureTimeMillis

class Day12 {
  fun start() {
    val timeMillis = measureTimeMillis {
      val input1 = "[-1434,2,3]"
      var regexText = """[-0-9]+"""
      val regex = Regex(regexText)

      var result1 = regex.findAll(input1)

      result1.forEach {
        println(">${it.value}<")
      }

      val input2 = Utils.readInputAsText("day12")
      var result2 = regex.findAll(input2)
      var sum2 = result2.sumOf {
        it.value.toInt()
      }

      println("sum2 = ${sum2}")

      val jsonObject = Json.parseToJsonElement(input2).jsonObject
      val sum3=sumJson(jsonObject)
      println("sum3: $sum3")
    }
    println("timeMillis = ${timeMillis}")
  }

  private fun sumJson(jsonElement: JsonElement) : Int {
    var sum=0
    if(!jsonElement.jsonObject.values.contains(JsonPrimitive("red"))) {
      for(element in jsonElement.jsonObject.values) {
        if(element is JsonPrimitive) {
          if(!element.isString) {
            sum+=element.int
          }
        }
        if(element is JsonObject) {
          sum+=sumJson(element)
        }
      }
    }
    return sum
  }

  private fun redFiter(input: String): String {
    var result=input
    val regex=Regex("""\{[^{}]*:"red"[^{}]*},?""")
    while(result.contains(regex)) {
      result=regex.replace(result,"")
    }
    return result
  }
}

fun main() {
  Day12().start()
}