package it.twinsbrain.aoc.kotlin

import kotlin.math.abs

object Day07 {
  fun alignCrabsSameHorizontalPos(input: String): Int {
    val crabsPos = parseCrabsPositions(input)
    val numberOfCrabs = crabsPos.size
    return costToMoveToMedian(numberOfCrabs, crabsPos)
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