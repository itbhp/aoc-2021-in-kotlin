package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06Part1.countFish
import it.twinsbrain.aoc.kotlin.Day06Part2.nextGenerationAfter
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput

fun main() {
  val day6Input = readInput("/inputs/day06.txt")
  println("Part1: ${countFish(day6Input, 80)}")
  println("Part2: ${nextGenerationAfter(day6Input, 256).count()}")
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
    val resetDays = Array(9) { 0 }
    numbersCounts.forEach { (k, v) ->
      resetDays[k] = v
    }
    return Generation(resetDays)
  }

  data class Generation(
    val resetDays: Array<Int>,
  ) {

    fun count(): Int = resetDays.sum()

    fun reset(): Generation {
      val numberOfFishToReset = resetDays[0]
      return Generation(
        Array(9) { 0 }.apply {
          this[6] = numberOfFishToReset
          this[8] = numberOfFishToReset
        }
      )
    }

    fun moveForward(): Generation {
      val newResetDays = Array(9) { 0 }
      (0..7).map { index ->
        newResetDays[index] = this.resetDays[index + 1]
      }
      return Generation(newResetDays)
    }

    operator fun plus(other: Generation): Generation {
      this.resetDays.mapIndexed { index, value ->
        this.resetDays[index] = value + other.resetDays[index]
      }
      return this
    }

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as Generation

      if (!resetDays.contentEquals(other.resetDays)) return false

      return true
    }

    override fun hashCode(): Int {
      return resetDays.contentHashCode()
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