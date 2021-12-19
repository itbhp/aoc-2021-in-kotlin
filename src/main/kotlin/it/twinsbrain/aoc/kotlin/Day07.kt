package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day07.part1
import it.twinsbrain.aoc.kotlin.Day07.part2
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput
import kotlin.math.abs

fun main() {
  val input = readInput("/inputs/day07.txt")
  println("Part1: ${part1(input[0])}")
  println("Part2: ${part2(input[0])}")
}

object Day07 {
  fun part1(input: String): Int {
    val crabsPos = parseCrabsPositions(input)
    val numberOfCrabs = crabsPos.size
    return costToMoveToMedian(numberOfCrabs, crabsPos)
  }

  fun part2(input: String): Long {
    val crabsPos = parseCrabsPositions(input)
    val average: Int = crabsPos.average().toInt()
    return crabsPos.sumOf { position -> cost(abs(position - average)) }.toLong()
  }

  private fun cost(n: Int): Int {
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