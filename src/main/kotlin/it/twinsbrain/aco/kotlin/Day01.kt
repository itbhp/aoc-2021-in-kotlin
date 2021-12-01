package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.common.FileModule.readInput

object Day01 {
  // part1
  fun howManySubsequentIncreases(input: List<Int>): Int {
    val pairs: List<Pair<Int, Int>> = input.take(input.size - 1).zip(input.subList(1, input.size))
    return pairs.count { p ->
      val (prev, next) = p
      prev < next
    }
  }
}

fun main() {
  val day1Input = readInput("/inputs/day1.txt")
    .map { it.toInt() }
  println(Day01.howManySubsequentIncreases(day1Input))
}
