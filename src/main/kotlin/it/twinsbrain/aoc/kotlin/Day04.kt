package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day04.Cell

typealias Row = List<Cell>

object Day04 {
  fun winner(input: List<String>): Int {
    val lotteryNumbers = lotteryNumbers(input)
    boardFrom(input)
    TODO()
  }

  fun lotteryNumbers(input: List<String>) = input[0].split(",").map { it.toInt() }

  fun boardFrom(input: List<String>): Board {
    val list: List<BingoChart> = input
      .chunked(6)
      .map { bingoChartInput ->
        val rows: List<Row> = bingoChartInput.drop(1)
          .mapIndexed { i, s ->
            val values = s.split(" ").filter { it.isNotEmpty() }
            values.mapIndexed { j, numberAsString ->
              Cell(i, j, numberAsString.toInt())
            }
          }
        BingoChart(rows)
      }
    return Board(list)
  }

  data class Board(val charts: List<BingoChart>)
  data class BingoChart(val cells: List<Row>)
  data class Cell(val x: Int, val y: Int, val value: Int)
}