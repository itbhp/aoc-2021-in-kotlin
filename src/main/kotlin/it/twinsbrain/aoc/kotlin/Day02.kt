package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day02.Command.Companion.moveWithAim
import it.twinsbrain.aoc.kotlin.Day02.Command.Companion.moveWithoutAim
import it.twinsbrain.aoc.kotlin.Day02.move
import it.twinsbrain.aoc.kotlin.Day02.moveWithAim
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput

fun main() {
  val day2Input = readInput("/inputs/day2.txt")
  val part1Result = move(day2Input)
  val part2Result = moveWithAim(day2Input)
  println(part1Result.x * part1Result.y)
  println(part2Result.x * part2Result.y)
}

object Day02 {
  data class Submarine(val x: Int, val y: Int) {
    fun moveForward(units: Int): Submarine = this.copy(x = this.x + units)
    fun moveDown(units: Int): Submarine = this.copy(y = this.y + units)
    fun moveUp(units: Int): Submarine = this.copy(y = this.y - units)
  }

  data class AimedSubmarine(val x: Int, val y: Int, val aim: Int) {
    fun moveForward(units: Int): AimedSubmarine = this.copy(x = this.x + units, y = this.y + this.aim * units)
    fun moveDown(units: Int): AimedSubmarine = this.copy(aim = this.aim + units)
    fun moveUp(units: Int): AimedSubmarine = this.copy(aim = this.aim - units)
  }

  fun move(input: List<String>): Submarine =
    input.map(::toCommand)
      .fold(Submarine(0, 0)) { submarine, command -> command moveWithoutAim submarine }

  fun moveWithAim(input: List<String>): AimedSubmarine =
    input.map(::toCommand)
      .fold(AimedSubmarine(0, 0, 0)) { submarine, command -> command moveWithAim submarine }

  private fun toCommand(input: String): Command {
    val (commandName, unit) = input.split(" ")
    if (commandName == "down") {
      return Down(unit.toInt())
    }
    if (commandName == "up") {
      return Up(unit.toInt())
    }
    return Forward(unit.toInt())
  }

  private sealed class Command {
    companion object {
      infix fun Command.moveWithoutAim(submarine: Submarine): Submarine = when (this) {
        is Forward -> submarine.moveForward(this.units)
        is Down -> submarine.moveDown(this.units)
        is Up -> submarine.moveUp(this.units)
      }

      infix fun Command.moveWithAim(submarine: AimedSubmarine): AimedSubmarine = when (this) {
        is Forward -> submarine.moveForward(this.units)
        is Down -> submarine.moveDown(this.units)
        is Up -> submarine.moveUp(this.units)
      }
    }
  }

  private data class Forward(val units: Int) : Command()
  private data class Down(val units: Int) : Command()
  private data class Up(val units: Int) : Command()
}