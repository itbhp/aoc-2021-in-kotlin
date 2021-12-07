package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.Point
import it.twinsbrain.aoc.kotlin.Day05.Segment
import it.twinsbrain.aoc.kotlin.Day05.segments
import org.assertj.core.api.Assertions.assertThat
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
}