package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06Part1.countFish
import it.twinsbrain.aoc.kotlin.Day06Part2.nextGenerationAfter
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput

fun main() {
  val day6Input = readInput("/inputs/day06.txt")
  println("Part1: ${countFish(day6Input, 80)}")
  println("Part2: ${nextGenerationAfter(day6Input, 256).count()}") // 1732821262171
}

object Day06Part2 {

  fun nextGenerationAfter(input: List<String>, days: Int): Generation {
    val initial: Generation = parse(input)
    return (1..days).fold(initial) { previous, _ ->
      val moveForward = previous.moveForward()
      val reset = previous.reset()
      reset + moveForward
    }
  }

  fun parse(input: List<String>): Generation {
    val numbers = input[0].split(",").map { it.toInt() }
    val numbersCounts = numbers.groupingBy { it }.eachCount()
    val daysUntilReset = Array(9) { 0L }
    numbersCounts.forEach { (k, v) ->
      daysUntilReset[k] = v.toLong()
    }
    return Generation(daysUntilReset)
  }

  data class Generation(val daysUntilReset: Array<Long>) {

    fun count(): Long = daysUntilReset.sum()

    fun reset(): Generation {
      val numberOfFishToReset = daysUntilReset[0]
      return Generation(
        Array(9) { 0L }.apply {
          this[6] = numberOfFishToReset
          this[8] = numberOfFishToReset
        }
      )
    }

    fun moveForward(): Generation {
      val newDaysUntilReset = Array(9) { 0L }
      (0..7).map { index ->
        newDaysUntilReset[index] = this.daysUntilReset[index + 1]
      }
      return Generation(newDaysUntilReset)
    }

    operator fun plus(other: Generation): Generation {
      val newDaysUntilReset = Array(9) { 0L }
      this.daysUntilReset.mapIndexed { index, value ->
        newDaysUntilReset[index] = value + other.daysUntilReset[index]
      }
      return Generation(newDaysUntilReset)
    }

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as Generation

      if (!daysUntilReset.contentEquals(other.daysUntilReset)) return false

      return true
    }

    override fun hashCode(): Int {
      return daysUntilReset.contentHashCode()
    }
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