package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.common.FileModule.readInput
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03AcceptanceTest {

  @Test
  internal fun acceptanceTest() {
    val day3Input = readInput("/inputs/day3.txt")
    val rates = Day03.rates(day3Input)
    val rating2 = Day03.ratings(day3Input)
    val expectedPart1 = 741950
    val expectedPart2 = 903810
    assertThat(rates.gammaRate.value * rates.epsilonRate.value)
      .isEqualTo(expectedPart1)
    assertThat(rating2.oxygen.value * rating2.co2.value)
      .isEqualTo(expectedPart2)
  }
}