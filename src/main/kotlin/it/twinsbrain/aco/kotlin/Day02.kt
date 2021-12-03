package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day02.Command.Companion.moveWithAim
import it.twinsbrain.aco.kotlin.Day02.Command.Companion.moveWithoutAim
import it.twinsbrain.aco.kotlin.Day02.move
import it.twinsbrain.aco.kotlin.Day02.moveWithAim
import it.twinsbrain.aco.kotlin.common.FileModule

fun main() {
  val day2Input = FileModule.readInput("/inputs/day2.txt")
  val part1Result = move(day2Input)
  val part2Result = moveWithAim(day2Input)
  println(part1Result.x * part1Result.y)
  println(part2Result.x * part2Result.y)
}

object Day02 {
  data class Position(val x: Int, val y: Int, val aim: Int = 0) {
    fun moveForward(units: Int): Position = this.copy(x = this.x + units)
    fun moveDown(units: Int): Position = this.copy(y = this.y + units)
    fun moveUp(units: Int): Position = this.copy(y = this.y - units)
    fun moveForwardWithAim(units: Int): Position = this.copy(x = this.x + units, y = this.y + this.aim * units)
    fun moveDownWithAim(units: Int): Position = this.copy(aim = this.aim + units)
    fun moveUpWithAim(units: Int): Position = this.copy(aim = this.aim - units)
  }

  fun move(input: List<String>): Position =
    input.map(::toCommand)
      .fold(Position(0, 0)) { position, command -> command moveWithoutAim position }

  fun moveWithAim(input: List<String>): Position =
    input.map(::toCommand)
      .fold(Position(0, 0, 0)) { position, command -> command moveWithAim position }

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
      infix fun Command.moveWithoutAim(previousPosition: Position): Position = when (this) {
        is Forward -> previousPosition.moveForward(this.units)
        is Down -> previousPosition.moveDown(this.units)
        is Up -> previousPosition.moveUp(this.units)
      }

      infix fun Command.moveWithAim(previousPosition: Position): Position = when (this) {
        is Forward -> previousPosition.moveForwardWithAim(this.units)
        is Down -> previousPosition.moveDownWithAim(this.units)
        is Up -> previousPosition.moveUpWithAim(this.units)
      }
    }
  }

  private data class Forward(val units: Int) : Command()
  private data class Down(val units: Int) : Command()
  private data class Up(val units: Int) : Command()
}