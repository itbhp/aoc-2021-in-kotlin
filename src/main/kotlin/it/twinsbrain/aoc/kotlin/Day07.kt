package it.twinsbrain.aoc.kotlin

import kotlin.math.abs

object Day07 {
  fun part1(input: String): Int {
    val crabsPos = parseCrabsPositions(input)
    val numberOfCrabs = crabsPos.size
    return costToMoveToMedian(numberOfCrabs, crabsPos)
  }

  fun part2(input: String): Int {
    val crabsPos = parseCrabsPositions(input)
    val average: Double = crabsPos.average()
    return crabsPos.sumOf { position -> cost(abs(position - average)) }.toInt()
  }

  private fun cost(n: Double): Double {
    return n * (n + 1) / 2
  }

  private fun parseCrabsPositions(input: String) = input.split(",").map { it.trim().toInt() }.sorted()

  private fun costToMoveToMedian(numberOfCrabs: Int, crabsPos: List<Int>): Int {
    val median = median(numberOfCrabs, crabsPos)
    return crabsPos.sumOf { abs(it - median) }
  }

  private fun median(numberOfCrabs: Int, crabsPos: List<Int>) =
    if (numberOfCrabs % 2 == 0) {
      (crabsPos[(numberOfCrabs / 2) - 1] + crabsPos[numberOfCrabs / 2]) / 2
    } else {
      crabsPos[(numberOfCrabs / 2)]
    }
}