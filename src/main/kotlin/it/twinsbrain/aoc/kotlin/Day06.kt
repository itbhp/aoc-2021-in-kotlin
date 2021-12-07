package it.twinsbrain.aoc.kotlin

object Day06 {

  fun countFish(input: List<String>, simulationDays: Int): Int {
    val worldAfter = worldAfter(input, simulationDays)
    return worldAfter.fishCount()
  }

  fun worldAfter(input: List<String>, after: Int): World {
    val initialWorld = World(parse(input))
    return (1..after).fold(initialWorld, ::simulate)
  }

  private fun simulate(previous: World, day: Int): World {
    return World(previous.fishes.map { Fish(it.daysUntilReset - 1) })
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
  }

  data class Fish(var daysUntilReset: Int)
}