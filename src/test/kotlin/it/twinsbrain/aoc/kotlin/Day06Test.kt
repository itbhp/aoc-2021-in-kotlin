package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day06.Fish
import it.twinsbrain.aoc.kotlin.Day06.World
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
      )
    ).isEqualTo(
      World(
        listOf(
          Fish(2), Fish(3), Fish(2), Fish(0), Fish(1)
        )
      )
    )
  }
}