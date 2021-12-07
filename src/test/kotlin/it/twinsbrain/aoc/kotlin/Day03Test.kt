package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day03.rates
import it.twinsbrain.aoc.kotlin.Day03.ratings
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day03Test {
  @Nested
  inner class GammaRateTest {
    @Test
    internal fun emptyData() {
      assertThat(rates(emptyList()).gammaRate.value).isEqualTo(0)
    }

    @Test
    internal fun oneValue() {
      assertThat(
        rates(
          listOf("000000000100")
        ).gammaRate.value
      ).isEqualTo(4)
    }

    @Test
    internal fun moreValues() {
      assertThat(
        rates(
          listOf(
            "101001100010",
            "101000100010",
            "111000100010",
          )
        ).gammaRate.value
      ).isEqualTo(2594)
    }
  }

  @Nested
  inner class EpsilonRateTest {
    @Test
    internal fun emptyData() {
      assertThat(rates(emptyList()).epsilonRate.value).isEqualTo(0)
    }

    @Test
    internal fun oneValue() {
      assertThat(
        rates(
          listOf("000000000100"),
          //      111111111011
        ).epsilonRate.value
      ).isEqualTo(4091)
    }

    @Test
    internal fun moreValues() {
      assertThat(
        rates(
          listOf(
            "101001100010",
            "101000100010",
            "111000100010",
          // 010111011101
          )
        ).epsilonRate.value
      ).isEqualTo(1501)
    }
  }

  @Nested
  inner class OxygenRateTest {
    @Test
    internal fun emptyData() {
      assertThat(ratings(emptyList()).oxygen.value).isEqualTo(0)
    }

    @Test
    internal fun moreValues() {
      assertThat(
        ratings(
          listOf(
            "000",
            "111",
            "101",
            // 111
          )
        ).oxygen.value
      ).isEqualTo(7)
    }
  }

  @Nested
  inner class Co2ScrubberRateTest {
    @Test
    internal fun emptyData() {
      assertThat(ratings(emptyList()).co2.value).isEqualTo(0)
    }

    @Test
    internal fun moreValues() {
      assertThat(
        ratings(
          listOf(
            "000",
            "011",
            "101",
            //101
          )
        ).co2.value
      ).isEqualTo(5)
    }
  }
}