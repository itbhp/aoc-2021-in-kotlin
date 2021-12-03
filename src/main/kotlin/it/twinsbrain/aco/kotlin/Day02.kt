package it.twinsbrain.aco.kotlin

object Day02 {
  data class Position(val x: Int, val y: Int) {
    fun moveForward(units: Int): Position = this.copy(x = this.x + units)
  }

  fun move(input: List<String>): Position {
    return input.map(::toCommand)
      .fold(Position(0, 0)) { position, command -> command.executeFrom(position) }
  }

  private fun toCommand(input: String): Command {
    val (commandName, unit) = input.split(" ")
    return Forward(unit.toInt())
  }

  private sealed class Command {
    fun executeFrom(previousPosition: Position): Position = when (this) {
      is Forward -> previousPosition.moveForward(this.units)
    }
  }

  private data class Forward(val units: Int) : Command()
}