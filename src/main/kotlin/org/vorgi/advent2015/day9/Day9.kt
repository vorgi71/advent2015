package org.vorgi.org.vorgi.advent2015.day9

import org.vorgi.org.vorgi.advent2015.Utils

class Day9 {

  fun start() {
    val input1="""London to Dublin = 464
      |London to Belfast = 518
      |Dublin to Belfast = 141""".trimMargin().lines()

    val cities=parseFlights(input1)

    println("cities = ${cities.values}")

    val routes = findAllRoutes(cities.values.toList())

    println("routes = ${routes.minBy { data ->
      data.first
    }.first}")

    val input2 = Utils.readInput("day9")
    val cities2 = parseFlights(input2)

    val routes2 = findAllRoutes(cities2.values.toList())

    println("routes2 = ${routes2.minBy { data ->
      data.first
    }.first}")

    println("routes3 = ${routes2.maxBy { it.first }.first}")

  }

  private fun parseFlights(lines: List<String>): Map<String,City> {
    val cities= mutableMapOf<String,City>()
    lines.forEach { line->
      val split = line.split(" ")
      val targetCity = cities.computeIfAbsent(split[0]) {
        City(split[0])
      }
      val destinationCity = cities.computeIfAbsent(split[2]) {
        City(split[2])
      }
      targetCity.connections.add(Connection(destinationCity,split[4].toInt()))
      destinationCity.connections.add(Connection(targetCity,split[4].toInt()))
    }

    return cities
  }

  class City(private val name:String) {
    var connections:MutableList<Connection> = mutableListOf()

    override fun toString(): String {
      var returnValue="$name:"
      connections.forEach { connection ->
        returnValue+="\n  ${connection.target.name} ${connection.distance}"
      }
      return returnValue+"\n"
    }
  }

  data class Connection(val target:City, var distance:Int)

  private fun findAllRoutes(cities: List<City>): List<Pair<Int,List<City>>> {
    val allRoutes = mutableListOf<Pair<Int,List<City>>>()
    val visited = mutableSetOf<City>()

    fun findRoute(currentCity: City, currentDistance: Int, remainingCities: List<City>, visited:MutableSet<City>) {
      if (remainingCities.isEmpty()) {
        allRoutes.add(Pair(currentDistance,visited.plus(currentCity).toList()))
        return
      }

      visited.add(currentCity)
      for (connection in currentCity.connections) {
        if (!visited.contains(connection.target)) {
          val newDistance = currentDistance + connection.distance
          findRoute(connection.target, newDistance, remainingCities.minus(connection.target),visited)
        }
      }
      visited.remove(currentCity) // Backtrack for other routes
    }

    // Choose any city as starting point
    for(city in cities) {
      visited.clear()
      findRoute(city, 0, cities.minus(city),visited)
    }

    return allRoutes
  }
}


fun main() {
  Day9().start()
}