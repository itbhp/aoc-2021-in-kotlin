package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day01.howManySubsequentIncreases
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day01Test {

  @Test
  internal fun zeroIncreases() {
    assertThat(
      howManySubsequentIncreases(listOf(1, 1, 1, 1))
    ).isEqualTo(0)
  }

  @Test
  internal fun oneIncrease() {
    assertThat(
      howManySubsequentIncreases(listOf(1, 1, 2, 2))
    ).isEqualTo(1)
  }

  @Test
  internal fun multipleIncreases() {
    assertThat(
      howManySubsequentIncreases(listOf(1, 1, 2, 3))
    ).isEqualTo(2)
  }
}