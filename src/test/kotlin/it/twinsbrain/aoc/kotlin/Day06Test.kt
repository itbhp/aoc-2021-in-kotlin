package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06Part1.Fish
import it.twinsbrain.aoc.kotlin.Day06Part1.parse
import it.twinsbrain.aoc.kotlin.Day06Part1.worldAfter
import it.twinsbrain.aoc.kotlin.Day06Part2.Generation
import it.twinsbrain.aoc.kotlin.Day06Part2.nextGenerationAfter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day06Test {

  @Nested
  inner class Part2 {
    @Test
    internal fun `should parse`() {
      assertThat(
        Day06Part2.parse(
          listOf("3,2,1,4")
        )
      ).isEqualTo(
        Generation(
          oneDayUntilReset = 1,
          twoDaysUntilReset = 1,
          threeDaysUntilReset = 1,
          fourDaysUntilReset = 1
        )
      )
    }

    @Test
    internal fun `should parse many items same bucket`() {
      assertThat(
        Day06Part2.parse(
          listOf("3,2,1,4,3,5,6,6")
        )
      ).isEqualTo(
        Generation(
          oneDayUntilReset = 1,
          twoDaysUntilReset = 1,
          threeDaysUntilReset = 2,
          fourDaysUntilReset = 1,
          fiveDaysUntilReset = 1,
          sixDaysUntilReset = 2
        )
      )
    }

    @Test
    internal fun `after 1 day`() {
      assertThat(
        nextGenerationAfter(
          listOf("3,4,3,1,2"),// 0, 1, 2 (2), 3
          1
        )
      ).isEqualTo(
        Generation(
          zeroDaysUntilReset = 1,
          oneDayUntilReset = 1,
          twoDaysUntilReset = 2,
          threeDaysUntilReset = 1
        )
      )
    }

    @Test
    internal fun `after 2 days`() {
      assertThat(
        nextGenerationAfter(
          listOf("3,4,3,1,2"),
          // after 1 day -> 2,3,2,0,1
          // after 2 day -> 1,2,1,6,8,0
          2
        )
      ).isEqualTo(
        Generation(
          zeroDaysUntilReset = 1,
          oneDayUntilReset = 2,
          twoDaysUntilReset = 1,
          sixDaysUntilReset = 1,
          eightDaysUntilReset = 1
        )
      )
    }
  }

  @Nested
  inner class Part1 {

    @Test
    internal fun `should parse`() {
      assertThat(
        parse(
          listOf("3,2,1,4")
        )
      ).isEqualTo(
        listOf(
          Fish(3), Fish(2), Fish(1), Fish(4)
        )
      )
    }

    @Test
    internal fun `after 1 day`() {
      assertThat(
        worldAfter(
          listOf("3,4,3,1,2"),
          1
        ).fishes
      ).containsExactlyInAnyOrder(
        Fish(2),
        Fish(3),
        Fish(2),
        Fish(0),
        Fish(1)
      )
    }

    @Test
    internal fun `after 2 days`() {
      assertThat(
        worldAfter(
          listOf("3,4,3,1,2"),
          // after 1 day -> 2,3,2,0,1
          2
        ).fishes
      ).containsExactlyInAnyOrder(
        Fish(1),
        Fish(2),
        Fish(1),
        Fish(6),
        Fish(0),
        Fish(8)
      )
    }
  }
}