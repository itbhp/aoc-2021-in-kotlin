package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06Part1.countFish
import it.twinsbrain.aoc.kotlin.Day06Part2.nextGenerationAfter
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput

fun main() {
  val day6Input = readInput("/inputs/day06.txt")
  println("Part1: ${countFish(day6Input, 80)}")
  println("Part2: ${nextGenerationAfter(day6Input, 256).count}")
}

object Day06Part2 {

  fun nextGenerationAfter(input: List<String>, days: Int): Generation {
    val initial: Generation = parse(input)
    return (1..days).fold(initial) { previous, _ ->
      val moveForward = previous.next()
      val reset = previous.reset()
      reset + moveForward
    }
  }

  fun parse(input: List<String>): Generation {
    val numbers = input[0].split(",").map { it.toInt() }
    val numbersCounts = numbers.groupBy { it }.mapValues { it.value.size }
    return Generation(
      zeroDaysUntilReset = numbersCounts[0] ?: 0,
      oneDayUntilReset = numbersCounts[1] ?: 0,
      twoDaysUntilReset = numbersCounts[2] ?: 0,
      threeDaysUntilReset = numbersCounts[3] ?: 0,
      fourDaysUntilReset = numbersCounts[4] ?: 0,
      fiveDaysUntilReset = numbersCounts[5] ?: 0,
      sixDaysUntilReset = numbersCounts[6] ?: 0,
      sevenDaysUntilReset = numbersCounts[7] ?: 0,
      eightDaysUntilReset = numbersCounts[8] ?: 0
    )
  }

  data class Generation(
    val zeroDaysUntilReset: Int = 0,
    val oneDayUntilReset: Int = 0,
    val twoDaysUntilReset: Int = 0,
    val threeDaysUntilReset: Int = 0,
    val fourDaysUntilReset: Int = 0,
    val fiveDaysUntilReset: Int = 0,
    val sixDaysUntilReset: Int = 0,
    val sevenDaysUntilReset: Int = 0,
    val eightDaysUntilReset: Int = 0
  ) {
    val count: Int
      get() {
        return zeroDaysUntilReset +
          oneDayUntilReset +
          twoDaysUntilReset +
          threeDaysUntilReset +
          fourDaysUntilReset +
          fiveDaysUntilReset +
          sixDaysUntilReset +
          sevenDaysUntilReset +
          eightDaysUntilReset
      }

    fun reset(): Generation =
      if (zeroDaysUntilReset > 0) {
        Generation(
          sixDaysUntilReset = this.zeroDaysUntilReset,
          eightDaysUntilReset = this.zeroDaysUntilReset,
        )
      } else Generation()

    fun next(): Generation = this.copy(
      zeroDaysUntilReset = oneDayUntilReset,
      oneDayUntilReset = twoDaysUntilReset,
      twoDaysUntilReset = threeDaysUntilReset,
      threeDaysUntilReset = fourDaysUntilReset,
      fourDaysUntilReset = fiveDaysUntilReset,
      fiveDaysUntilReset = sixDaysUntilReset,
      sixDaysUntilReset = sevenDaysUntilReset,
      sevenDaysUntilReset = sevenDaysUntilReset,
      eightDaysUntilReset = 0
    )

    operator fun plus(other: Generation): Generation = this.copy(
      zeroDaysUntilReset = this.zeroDaysUntilReset + other.zeroDaysUntilReset,
      oneDayUntilReset = this.oneDayUntilReset + other.oneDayUntilReset,
      twoDaysUntilReset = this.twoDaysUntilReset + other.twoDaysUntilReset,
      threeDaysUntilReset = this.threeDaysUntilReset + other.threeDaysUntilReset,
      fourDaysUntilReset = this.fourDaysUntilReset + other.fourDaysUntilReset,
      fiveDaysUntilReset = this.fiveDaysUntilReset + other.fiveDaysUntilReset,
      sixDaysUntilReset = this.sixDaysUntilReset + other.sixDaysUntilReset,
      sevenDaysUntilReset = this.sevenDaysUntilReset + other.sevenDaysUntilReset,
      eightDaysUntilReset = this.eightDaysUntilReset + other.eightDaysUntilReset
    )
  }
}

object Day06Part1 {

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

    private fun moveForward(): World {
      val newFishes: List<Fish> = this.fishes
        .groupBy { it }
        .map { it.key to it.value.size }
        .flatMap { (f, count) -> (1..count).map { Fish(f.daysUntilReset - 1) } }
      return World(newFishes)
    }
  }

  data class Fish(var daysUntilReset: Int)
}