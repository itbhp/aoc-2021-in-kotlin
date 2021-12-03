package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day01.howManyIncreases
import it.twinsbrain.aco.kotlin.Day01.howManySubsequentIncreases
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day01Test {

  @Nested
  inner class Part1Test {
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

  @Nested
  inner class Part2Test {
    @Test
    internal fun zeroIncreases() {
      assertThat(
        howManyIncreases(listOf(1, 1, 1, 1, 1, 1))
      ).isEqualTo(0)
    }

    @Test
    internal fun multipleIncreases() {
      // listOf(1, 2, 2, 1, 2, 3)
      // listOf(5, 5, 5, 6)
      assertThat(
        howManyIncreases(listOf(1, 2, 2, 1, 2, 3))
      ).isEqualTo(1)
    }
  }
}