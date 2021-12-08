package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.Point
import it.twinsbrain.aoc.kotlin.Day05.Segment
import it.twinsbrain.aoc.kotlin.Day05.Segment.Companion.segment
import it.twinsbrain.aoc.kotlin.Day05.howManyIntersections
import it.twinsbrain.aoc.kotlin.Day05.segments
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

internal class Day05Test {

  @Test
  internal fun `no intersections`() {
    assertThat(
      howManyIntersections(
        listOf(
          "0,1 -> 0,10",
          "1,0 -> 10,0"
        )
      )
    ).isEqualTo(0)
  }

  @Test
  internal fun `one intersection`() {
    assertThat(
      howManyIntersections(
        listOf(
          "0,1 -> 0,10",
          "0,1 -> 2,3"
        )
      )
    ).isEqualTo(1)
  }

  @Test
  internal fun `many intersection same point`() {
    assertThat(
      howManyIntersections(
        listOf(
          "0,1 -> 0,10",
          "0,0 -> 0,1",
          "0,1 -> 1,2",
        )
      )
    ).isEqualTo(1)
  }

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
        Segment(Point(341, 71), Point(556, 286)),
        Segment(Point(337, 201), Point(782, 646))
      )
    )
  }

  @Nested
  inner class SegmentsPointsTest {

    @ParameterizedTest
    @ArgumentsSource(ContainsFixtures::class)
    fun shouldWork(segment: Segment, points: List<Point>) {
      assertThat(segment.points()).isEqualTo(points)
    }
  }
}

class ContainsFixtures : ArgumentsProvider {
  override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> =
    Stream.of(
      Arguments.of(segment(0, 1, 0, 3), listOf(Point(0, 1), Point(0, 2), Point(0, 3))),
      Arguments.of(segment(1, 0, 3, 0), listOf(Point(1, 0), Point(2, 0), Point(3, 0))),
      Arguments.of(segment(0, 0, 2, 2), listOf(Point(0, 0), Point(1, 1), Point(2, 2))),
      Arguments.of(segment(0, 1, 1, 10), listOf(Point(0, 1), Point(1, 10))),
    )
}