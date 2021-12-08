package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06Part1.countFish
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06AcceptanceTest {

  @Test
  internal fun acceptanceTest() {
    val sample = readInput("/inputs/day06_sample.txt")
    assertThat(countFish(sample, 18)).isEqualTo(26)
    assertThat(countFish(sample, 80)).isEqualTo(5934)
  }
}