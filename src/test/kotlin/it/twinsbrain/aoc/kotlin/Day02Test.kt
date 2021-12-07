package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day02.AimedSubmarine
import it.twinsbrain.aoc.kotlin.Day02.Submarine
import it.twinsbrain.aoc.kotlin.Day02.move
import it.twinsbrain.aoc.kotlin.Day02.moveWithAim
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day02Test {

  @Nested
  inner class Part1Test {
    @Test
    internal fun `on no commands`() {
      assertThat(move(emptyList())).isEqualTo(Submarine(0, 0))
    }

    @Test
    internal fun `move forward`() {
      assertThat(
        move(
          listOf(
            "forward 4",
            "forward 2"
          )
        )
      ).isEqualTo(Submarine(6, 0))
    }

    @Test
    internal fun `move down`() {
      assertThat(
        move(
          listOf(
            "down 4",
            "down 2"
          )
        )
      ).isEqualTo(Submarine(0, 6))
    }

    @Test
    internal fun `move up`() {
      assertThat(
        move(
          listOf(
            "down 4",
            "up 2"
          )
        )
      ).isEqualTo(Submarine(0, 2))
    }
  }

  @Nested
  inner class Part2Test {
    @Test
    internal fun `on no commands`() {
      assertThat(moveWithAim(emptyList())).isEqualTo(AimedSubmarine(0, 0, 0))
    }

    @Test
    internal fun `move down`() {
      assertThat(
        moveWithAim(
          listOf(
            "down 4",
            "down 2"
          )
        )
      ).isEqualTo(AimedSubmarine(0, 0, 6))
    }

    @Test
    internal fun `move up`() {
      assertThat(
        moveWithAim(
          listOf(
            "down 4",
            "up 2"
          )
        )
      ).isEqualTo(AimedSubmarine(0, 0, 2))
    }

    @Test
    internal fun `move forward`() {
      assertThat(
        moveWithAim(
          listOf(
            "forward 4",
            "down 2",
            "forward 3"
          )
        )
      ).isEqualTo(AimedSubmarine(7, 6, 2))
    }
  }
}