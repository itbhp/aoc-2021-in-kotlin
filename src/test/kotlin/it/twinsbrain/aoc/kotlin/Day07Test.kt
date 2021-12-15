package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day07.part1
import it.twinsbrain.aoc.kotlin.Day07.part2
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day07Test {
  @Nested
  inner class Part1Test {
    @Test
    internal fun `1 crab`() {
      assertThat(part1("1")).isEqualTo(0)
    }

    @Test
    internal fun `odd number of crabs`() {
      assertThat(part1("1, 3, 0")).isEqualTo(3)
    }

    @Test
    internal fun `even number of crabs`() {
      assertThat(part1("1, 3, 0, 2")).isEqualTo(4)
    }
  }

  @Nested
  inner class Part2Test {
    @Test
    internal fun `1 crab`() {
      assertThat(part2("1")).isEqualTo(0)
    }

    @Test
    internal fun `any number of crabs`() {
      assertThat(part2("1, 5, 0")).isEqualTo(10)
    }
  }
}