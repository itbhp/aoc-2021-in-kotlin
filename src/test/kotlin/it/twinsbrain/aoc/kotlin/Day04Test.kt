package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day04.BingoChart
import it.twinsbrain.aoc.kotlin.Day04.Board
import it.twinsbrain.aoc.kotlin.Day04.Cell
import it.twinsbrain.aoc.kotlin.Day04.boardFrom
import it.twinsbrain.aoc.kotlin.Day04.lotteryNumbers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day04Test {
  @Test
  internal fun `should read lottery numbers`() {
    assertThat(lotteryNumbers(listOf("17,58,52,49,72,33")))
      .isEqualTo(listOf(17, 58, 52, 49, 72, 33))
  }

  @Test
  internal fun `should read the bing charts`() {
    assertThat(
      boardFrom(
        listOf(
          "",
          "59 98 84 27 56",
          "17 35 18 64 34",
          "62 16 74 26 55",
          "21 99  1 19 93",
          "65 68 53 24 73",
          "",
          "1 86 98 16  6",
          "93 69 33 49 71",
          "54 43 77 29 47",
          "82 73 99 31 27",
          "28 48 36 89 20",
        )
      )
    ).isEqualTo(
      Board(
        listOf(
          BingoChart(
            listOf(
              listOf(Cell(0, 0, 59), Cell(0, 1, 98), Cell(0, 2, 84), Cell(0, 3, 27), Cell(0, 4, 56)),
              listOf(Cell(1, 0, 17), Cell(1, 1, 35), Cell(1, 2, 18), Cell(1, 3, 64), Cell(1, 4, 34)),
              listOf(Cell(2, 0, 62), Cell(2, 1, 16), Cell(2, 2, 74), Cell(2, 3, 26), Cell(2, 4, 55)),
              listOf(Cell(3, 0, 21), Cell(3, 1, 99), Cell(3, 2, 1), Cell(3, 3, 19), Cell(3, 4, 93)),
              listOf(Cell(4, 0, 65), Cell(4, 1, 68), Cell(4, 2, 53), Cell(4, 3, 24), Cell(4, 4, 73)),
            )
          ),
          BingoChart(
            listOf(
              listOf(Cell(0, 0, 1), Cell(0, 1, 86), Cell(0, 2, 98), Cell(0, 3, 16), Cell(0, 4, 6)),
              listOf(Cell(1, 0, 93), Cell(1, 1, 69), Cell(1, 2, 33), Cell(1, 3, 49), Cell(1, 4, 71)),
              listOf(Cell(2, 0, 54), Cell(2, 1, 43), Cell(2, 2, 77), Cell(2, 3, 29), Cell(2, 4, 47)),
              listOf(Cell(3, 0, 82), Cell(3, 1, 73), Cell(3, 2, 99), Cell(3, 3, 31), Cell(3, 4, 27)),
              listOf(Cell(4, 0, 28), Cell(4, 1, 48), Cell(4, 2, 36), Cell(4, 3, 89), Cell(4, 4, 20))
            )
          )
        )
      )
    )
  }
}