package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06.countFish
import it.twinsbrain.aoc.kotlin.common.FileModule

fun main() {
  val day6Input = FileModule.readInput("/inputs/day06.txt")
  println(countFish(day6Input, 80))
}

object Day06 {

  fun countFish(input: List<String>, simulationDays: Int): Int {
    val worldAfter = worldAfter(input, simulationDays)
    return worldAfter.fishCount()
  }

  fun worldAfter(input: List<String>, after: Int): World {
    val initialWorld = World(parse(input))
    return (1..after).fold(initialWorld) { previous, _ -> previous.newGeneration() }
  }

  fun parse(input: List<String>): List<Fish> = input
    .flatMap {
      it.split(",")
        .map { n ->
          Fish(n.toInt())
        }
    }

  data class World(val fishes: List<Fish>) {
    fun fishCount() = fishes.size
    fun newGeneration(): World {
      val (toReset, others) = fishes.partition { it.daysUntilReset == 0 }
      val resetWorld = World(
        toReset.flatMap { listOf(Fish(6), Fish(8)) }
      )
      val movedForwardWorld = World(others).moveForward()
      return resetWorld.union(movedForwardWorld)
    }

    private fun union(other: World): World = World(this.fishes + other.fishes)

    private fun moveForward(): World = World(this.fishes.map { Fish(it.daysUntilReset - 1) })
  }

  data class Fish(var daysUntilReset: Int)
}