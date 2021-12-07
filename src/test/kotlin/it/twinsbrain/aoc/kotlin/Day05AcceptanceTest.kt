package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.howManyIntersections
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05AcceptanceTest {
  @Test
  internal fun acceptancePart1Test() {
    val day5Input = readInput("/inputs/day05_sample.txt")
    assertThat(howManyIntersections(day5Input)).isEqualTo(5)
  }

  @Test
  internal fun acceptancePart2Test() {
    val day5Input = readInput("/inputs/day05_sample.txt")
    assertThat(howManyIntersections(day5Input, true)).isEqualTo(12)
  }
}