package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.common.FileModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day04AcceptanceTest {
  @Test
  internal fun acceptanceTest() {
    val day4Input = FileModule.readInput("/inputs/day04_sample.txt")
    val board = Day04.winners(day4Input)
    assertThat(board.firstWinner).isEqualTo(4512)
  }
}