package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06.Fish
import it.twinsbrain.aoc.kotlin.Day06.parse
import it.twinsbrain.aoc.kotlin.Day06.worldAfter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {

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
        listOf("3,4,3,1,2"), // 1, 2, 3(2), 4 -> 0, 1, 2(2), 3 -> 0, 1(2), 2, 6, 8 -> 0(2), 1, 5, 6, 7, 8
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