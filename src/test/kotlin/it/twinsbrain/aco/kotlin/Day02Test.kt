package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day02.Position
import it.twinsbrain.aco.kotlin.Day02.move
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day02Test {

  @Test
  internal fun `on no commands`() {
    assertThat(move(emptyList())).isEqualTo(Position(0,0))
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
    ).isEqualTo(Position(6,0))
  }
}