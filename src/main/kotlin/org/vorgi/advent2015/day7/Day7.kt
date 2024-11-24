package org.vorgi.org.vorgi.advent2015.day7

import org.vorgi.org.vorgi.advent2015.Utils

class Day7 {

  class Value(val string: String) {
    fun getValue(values: MutableMap<String, UShort>) : UShort? {
      if(string.isUShort()) {
        return string.toUShort()
      }
      return values[string]
    }
  }

  abstract class Instruction {
    abstract fun execute(values: MutableMap<String, UShort>): Boolean
  }

  class Assignment(val varName: String, val value: Value) : Instruction() {
    override fun execute(values: MutableMap<String, UShort>): Boolean {
      val outValue=value.getValue(values)
      if(outValue==null) {
        return false
      }
      values[varName] = outValue
      return true
    }

    override fun toString(): String {
      return "$varName = $value"
    }
  }

  class And(val outVarName: String, val var1Name: Value, val var2Name: Value) : Instruction() {
    override fun execute(values: MutableMap<String, UShort>): Boolean {
      val in1 = var1Name.getValue(values)
      val in2 = var2Name.getValue(values)

      if (in1 != null && in2 != null) {
        values[outVarName] = in1 and in2
      } else {
        return false
      }
      return true
    }

    override fun toString(): String {
      return "$outVarName = $var1Name AND $var2Name"
    }
  }

  class Or(val outVarName: String, val var1Name: Value, val var2Name: Value) : Instruction() {
    override fun execute(values: MutableMap<String, UShort>): Boolean {
      val in1 = var1Name.getValue(values)
      val in2 = var2Name.getValue(values)

      if (in1 != null && in2 != null) {
        values[outVarName] = in1 or in2
      } else {
        return false
      }
      return true
    }

    override fun toString(): String {
      return "$outVarName = $var1Name OR $var2Name"
    }
  }

  class LShift(val outVarName: String, val inVarName: Value, val amount: Value) : Instruction() {
    override fun execute(values: MutableMap<String, UShort>): Boolean {
      val in1 = inVarName.getValue(values)
      val amountValue = amount.getValue(values)
      if (in1 != null && amountValue!=null) {
        values[outVarName] = in1.shl(amountValue)
      } else {
        return false
      }
      return true
    }

    override fun toString(): String {
      return "$outVarName = $inVarName LShift $amount"
    }
  }

  class RShift(val outVarName: String, val inVarName: Value, val amount: Value) : Instruction() {
    override fun execute(values: MutableMap<String, UShort>): Boolean {
      val in1 = inVarName.getValue(values)
      val amountValue = amount.getValue(values)
      if (in1 != null && amountValue!=null) {
        values[outVarName] = in1.shr(amountValue)
      } else {
        return false
      }
      return true
    }

    override fun toString(): String {
      return "$outVarName = $inVarName RShift $amount"
    }
  }

  class Not(val outVarName: String, val inVarName: Value) : Instruction() {
    override fun execute(values: MutableMap<String, UShort>): Boolean {
      val inVar = inVarName.getValue(values)
      if (inVar != null) {
        values[outVarName] = inVar.inv()
      } else {
        return false
      }
      return true
    }

    override fun toString(): String {
      return "$outVarName = NOT $inVarName"
    }
  }

  fun start() {
    val input1 = """x AND y -> d
                  |x OR y -> e
                  |x LSHIFT 2 -> f
                  |y RSHIFT 2 -> g
                  |NOT x -> h
                  |NOT y -> i
                  |123 -> x
                  |456 -> y""".trimMargin()

    var instructions1 = parseInput(input1.split("\n"))

    println(instructions1)

    val values1 = executeInstructions(instructions1)

    println(values1)

    var input2 = Utils.readInput("day7")

    val instructions2 = parseInput(input2)

    val values2 = executeInstructions(instructions2)

    println("values2\"[a]\" = ${values2["a"]}")

    val aValue=values2["a"]

    values2.keys.forEach { key ->
      values2[key]=0U
    }

    val values3= mutableMapOf<String,UShort>()

    if(aValue!=null) {
      values3["b"] = aValue
    }

    executeInstructions(instructions2,values3)
    println("values3\"[a]\" = ${values2["a"]}")
  }

  private fun executeInstructions(
    instructionList: List<Instruction>,
    values: MutableMap<String, UShort> = mutableMapOf()
  ): MutableMap<String, UShort> {

    val instructionsToExecute:MutableList<Instruction> = mutableListOf()
    instructionsToExecute.addAll(instructionList)

    println("executing ${instructionsToExecute.size} instructions")

    while(instructionsToExecute.isNotEmpty()) {
      val executedInstructions= mutableListOf<Instruction>()
      instructionsToExecute.forEach { instruction ->
        val executed = instruction.execute(values)
        if (!executed) {
          //println("not executed $instruction")
        } else {
          executedInstructions+=instruction
        }
      }
      if(executedInstructions.isEmpty()) {
        println("found nothing new. giving up")
        instructionsToExecute.forEach {
          println(it)
        }
        break
      }
      executedInstructions.forEach { instruction ->
        instructionsToExecute.remove(instruction)
      }

      println("remaining instructions ${instructionsToExecute.size}")
    }
    return values
  }

  private fun parseInput(lines: List<String>): List<Instruction> {
    val instructions = mutableListOf<Instruction>()

    lines.forEach { line ->
      val split = line.split(" ")
      when (split.size) {
        3 -> {
            instructions += Assignment(split[2], Value(split[0]))
        }

        4 -> {
          instructions += Not(split[3], Value(split[1]))
        }

        5 -> {
          val instructionName = split[1]
          when (instructionName) {
            "AND" -> {
              instructions += And(split[4], Value(split[0]), Value(split[2]))
            }

            "OR" -> {
              instructions += Or(split[4], Value(split[0]), Value(split[2]))
            }

            "LSHIFT" -> {
              instructions += LShift(split[4], Value(split[0]), Value(split[2]))
            }

            "RSHIFT" -> {
              instructions += RShift(split[4], Value(split[0]), Value(split[2]))
            }
          }
        }
      }
    }
    return instructions
  }

}

private fun UShort.shr(amount: UShort): UShort {
  return this.toInt().shr(amount.toInt()).toUShort()
}

private fun UShort.shl(amount: UShort): UShort {
  return this.toInt().shl(amount.toInt()).toUShort()
}

fun String.isUShort(): Boolean {
  return try {
    toUShort()
    true
  } catch (e: NumberFormatException) {
    false
  }
}

fun main() {
  Day7().start()
}