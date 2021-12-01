package it.twinsbrain.aco.kotlin

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
