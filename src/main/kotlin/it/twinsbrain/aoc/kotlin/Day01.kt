package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day01.howManyIncreases
import it.twinsbrain.aoc.kotlin.Day01.howManySubsequentIncreases
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput

object Day01 {
  // part1
  fun howManySubsequentIncreases(input: List<Int>): Int =
    input
      .take(input.size - 1)
      .zip(input.drop(1))
      .count { (prev, next) -> prev < next }

  // part2
  fun howManyIncreases(input: List<Int>): Int {
    val compacted = mutableListOf<Int>()
    for (i in 0..input.size - 2) {
      if (i < input.size - 2) {
        compacted.add(input[i] + input[i + 1] + input[i + 2])
      } else {
        break
      }
    }
    return howManySubsequentIncreases(compacted)
  }
}

fun main() {
  val day1Input = readInput("/inputs/day01.txt")
    .map { it.toInt() }
  println(howManySubsequentIncreases(day1Input))
  println(howManyIncreases(day1Input))
}
