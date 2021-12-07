package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day04.Cell
import it.twinsbrain.aoc.kotlin.common.FileModule

typealias Row = List<Cell>

fun main() {
  val day4Input = FileModule.readInput("/inputs/day04.txt")
  val checkedBoard = Day04.winners(day4Input)
  println("First: ${checkedBoard.firstWinner}")
  println("Last: ${checkedBoard.lastWinner}")
}

object Day04 {
  fun winners(input: List<String>): BingoGame {
    val numbers = drawnNumbers(input)
    val game = from(input.drop(1))
    numbers.forEach { number ->
      game.draw(number)
    }
    return game
  }

  fun drawnNumbers(input: List<String>) = input[0].split(",").map { it.toInt() }

  fun from(input: List<String>): BingoGame {
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
    return BingoGame(list)
  }

  data class BingoGame(val charts: List<BingoChart>) {
    private val winnersScore = mutableListOf<Int>()
    fun draw(number: Int) {
      charts.forEach { chart ->
        when (val result = chart.check(number)) {
          is Won -> winnersScore.add(number * result.score)
          NothingRelevant -> Unit
        }
      }
    }

    val firstWinner: Int
      get() {
        return winnersScore.first()
      }

    val lastWinner: Int
      get() {
        return winnersScore.last()
      }
  }

  sealed class CheckResult
  object NothingRelevant : CheckResult()
  data class Won(val score: Int) : CheckResult()


  data class BingoChart(val cells: List<Row>) {
    private val columns: Collection<List<Cell>> by lazy {
      val flatten: List<Cell> = cells.flatten()
      flatten.groupBy { it.y }.values
    }
    private var alreadyWon = false

    fun check(number: Int): CheckResult {
      if (alreadyWon) {
        return NothingRelevant
      }
      cells.forEach { row ->
        row.forEach { cell ->
          cell.check(number)
        }
      }
      return if (gotBingo()) {
        alreadyWon = true
        Won(score())
      } else {
        NothingRelevant
      }
    }

    private fun score(): Int {
      return cells.flatten().filter { !it.checked }.sumOf { it.value }
    }

    private fun gotBingo(): Boolean {
      return cells.any { row -> row.all { it.checked } } ||
        columns.any { column -> column.all { it.checked } }
    }
  }

  data class Cell(val x: Int, val y: Int, val value: Int, var checked: Boolean = false) {
    fun check(number: Int) {
      if (number == value) {
        checked = true
      }
    }
  }
}
