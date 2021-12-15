package it.twinsbrain.aoc.kotlin

import kotlin.math.abs

object Day07 {
  fun alignCrabsSameHorizontalPos(input: String): Int {
    val crabsPos = input.split(",").map { it.trim().toInt() }.sorted()
    val numberOfCrabs = crabsPos.size
    return costToMoveToMedian(numberOfCrabs, crabsPos)
  }

  private fun costToMoveToMedian(numberOfCrabs: Int, crabsPos: List<Int>): Int {
    val median = if (numberOfCrabs % 2 == 0) {
      (crabsPos[(numberOfCrabs / 2) - 1] + crabsPos[numberOfCrabs / 2]) / 2
    } else {
      crabsPos[(numberOfCrabs / 2)]
    }
    return crabsPos.sumOf { abs(it - median) }
  }
}