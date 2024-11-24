package org.vorgi.org.vorgi.advent2015.day6

import org.vorgi.org.vorgi.advent2015.Utils
import kotlin.math.min
import kotlin.math.max

class Day6 {
  class LightField(val width: Int, val height: Int) {

    val lights = List(height) { y ->
      MutableList(width) { x ->
        false
      }
    }

    fun getAt(x: Int, y: Int): Boolean {
      return lights[y][x]
    }

    fun setAt(x: Int, y: Int, newValue: Boolean) {
      lights[y][x] = newValue
    }

    fun turnOn(x1: Int, y1: Int, x2: Int, y2: Int) {
      var startX = min(x1, x2)
      var endX = max(x1, x2)
      var startY = min(y1, y2)
      var endY = max(y1, y2)
      for (x in startX..endX) {
        for (y in startY..endY) {
          setAt(x, y, true)
        }
      }
    }

    fun turnOff(x1: Int, y1: Int, x2: Int, y2: Int) {
      var startX = min(x1, x2)
      var endX = max(x1, x2)
      var startY = min(y1, y2)
      var endY = max(y1, y2)
      for (x in startX..endX) {
        for (y in startY..endY) {
          setAt(x, y, newValue = false)
        }
      }
    }

    fun toggle(x1: Int, y1: Int, x2: Int, y2: Int) {
      var startX = min(x1, x2)
      var endX = max(x1, x2)
      var startY = min(y1, y2)
      var endY = max(y1, y2)
      for (x in startX..endX) {
        for (y in startY..endY) {
          setAt(x, y, !getAt(x, y))
        }
      }
    }

    fun count(): Int {
      var count = 0
      lights.forEach { row ->
        row.forEach {
          if (it) {
            count++
          }
        }
      }
      return count
    }

    fun performInstructions(instructions: List<String>) {
      for (line in instructions) {
        if (line.startsWith("turn on")) {
          val split = line.split(" ")
          val from = split[2].split(",")
          val to = split[4].split(",")
          turnOn(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())
        } else if (line.startsWith("turn off")) {
          val split = line.split(" ")
          val from = split[2].split(",")
          val to = split[4].split(",")
          turnOff(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())
        } else if (line.startsWith("toggle")) {
          val split = line.split(" ")
          val from = split[1].split(",")
          val to = split[3].split(",")
          toggle(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())
        } else {
          throw IllegalArgumentException("unknown")
        }
      }
    }
  }

  class LightField2(val width: Int, val height: Int) {
    val lights = List(height) { y ->
      MutableList(width) { x ->
        0
      }
    }

    fun getAt(x: Int, y: Int): Int {
      return lights[y][x]
    }

    fun setAt(x: Int, y: Int, newValue: Int) {
      lights[y][x] = newValue
    }

    fun turnOn(x1: Int, y1: Int, x2: Int, y2: Int) {
      var startX = min(x1, x2)
      var endX = max(x1, x2)
      var startY = min(y1, y2)
      var endY = max(y1, y2)
      for (x in startX..endX) {
        for (y in startY..endY) {
          setAt(x, y, getAt(x, y) + 1)
        }
      }
    }

    fun turnOff(x1: Int, y1: Int, x2: Int, y2: Int) {
      val startX = min(x1, x2)
      val endX = max(x1, x2)
      val startY = min(y1, y2)
      val endY = max(y1, y2)
      for (x in startX..endX) {
        for (y in startY..endY) {
          var newValue=getAt(x, y )- 1
          if(newValue<0)  {
            newValue=0
          }
          setAt(x, y, newValue)
        }
      }
    }

    fun toggle(x1: Int, y1: Int, x2: Int, y2: Int) {
      var startX = min(x1, x2)
      var endX = max(x1, x2)
      var startY = min(y1, y2)
      var endY = max(y1, y2)
      for (x in startX..endX) {
        for (y in startY..endY) {
          setAt(x, y, getAt(x, y) + 2)
        }
      }
    }

    fun count(): Long {
      var count = 0L
      lights.forEach { row ->
        row.forEach { value ->
          count += value
        }
      }
      return count
    }

    fun performInstructions(instructions: List<String>) {
      for (line in instructions) {
        if (line.startsWith("turn on")) {
          val split = line.split(" ")
          val from = split[2].split(",")
          val to = split[4].split(",")
          turnOn(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())
        } else if (line.startsWith("turn off")) {
          val split = line.split(" ")
          val from = split[2].split(",")
          val to = split[4].split(",")
          turnOff(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())
        } else if (line.startsWith("toggle")) {
          val split = line.split(" ")
          val from = split[1].split(",")
          val to = split[3].split(",")
          toggle(from[0].toInt(), from[1].toInt(), to[0].toInt(), to[1].toInt())
        } else {
          throw IllegalArgumentException("unknown")
        }
      }
    }


  }

  fun start() {
    var lightField1 = LightField(1000, 1000)

    lightField1.turnOn(0, 0, 999, 999)
    lightField1.toggle(0, 0, 999, 0)
    lightField1.toggle(499, 499, 500, 500)

    var result1 = lightField1.count()
    println("result1 = ${result1}")

    val input1 = Utils.readInput("day6")

    lightField1 = LightField(1000, 1000)

    lightField1.performInstructions(input1)

    var result2 = lightField1.count()
    println("result2 = ${result2}")

    var lightField2=LightField2(1000,1000)
    lightField2.turnOn(0,0,0,0)
    lightField2.toggle(0,0,999,999)

    var result3 = lightField2.count()
    println("result3 = ${result3}")

    lightField2= LightField2(1000,1000)

    lightField2.performInstructions(input1)

    val result4=lightField2.count()
    println("result4 = ${result4}")
  }
}

fun main() {
  Day6().start()
}