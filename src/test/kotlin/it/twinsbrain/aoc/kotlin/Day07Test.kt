package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day07.alignCrabsSameHorizontalPos
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day07Test {
  @Test
  internal fun `1 crab`() {
    assertThat(alignCrabsSameHorizontalPos("1")).isEqualTo(0)
  }
}