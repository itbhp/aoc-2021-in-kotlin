package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.Point
import it.twinsbrain.aoc.kotlin.Day05.Segment
import it.twinsbrain.aoc.kotlin.Day05.segments
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day05Test {

  @Test
  internal fun `should adapt`() {
    assertThat(
      segments(
        listOf(
          "556,286 -> 341,71",
          "337,201 -> 782,646"
        )
      )
    ).isEqualTo(
      listOf(
        Segment(Point(556, 286), Point(341, 71)),
        Segment(Point(337, 201), Point(782, 646))
      )
    )
  }

  @Nested
  @Disabled
  inner class IntersectTest {

    @Test
    internal fun `no intersection`() {
      assertThat(
        Segment(Point(0, 1), Point(0,10))
          .intersect(
            Segment(Point(1, 1), Point(1,10))
          )
      ).isNull()
    }

    @Test
    internal fun `same start`() {
      assertThat(
        Segment(Point(0, 1), Point(0,10))
          .intersect(
            Segment(Point(0, 1), Point(1,10))
          )
      ).isEqualTo(Point(0, 1))
    }

    @Test
    internal fun `same end`() {
      assertThat(
        Segment(Point(0, 1), Point(0,10))
          .intersect(
            Segment(Point(1, 1), Point(0,10))
          )
      ).isEqualTo(Point(0, 10))
    }

    @Test
    internal fun `in the middle`() {
      assertThat(
        Segment(Point(2, 1), Point(2,10))
          .intersect(
            Segment(Point(0, 5), Point(3,5))
          )
      ).isEqualTo(Point(2, 5))
    }
  }
}