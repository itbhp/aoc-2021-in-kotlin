package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.Segment.Companion.segment
import it.twinsbrain.aoc.kotlin.Day05.howManyIntersections
import it.twinsbrain.aoc.kotlin.common.FileModule
import it.twinsbrain.aoc.kotlin.common.FileModule.readInput
import it.twinsbrain.aoc.kotlin.common.MathModule.Fraction
import java.lang.Integer.min
import kotlin.math.max

fun main() {
  val day5Input = readInput("/inputs/day05.txt")
  println("Part1: ${howManyIntersections(day5Input)}")
  println("Part2: ${howManyIntersections(day5Input, true)}")
}

object Day05 {

  fun howManyIntersections(input: List<String>, shouldConsiderDiagonal: Boolean = false): Int {
    val segments =
      if (shouldConsiderDiagonal) segments(input)
      else segments(input).filter { it.isHorizontal() || it.isVertical() }
    val points: List<Point> = segments.flatMap { it.points() }
    return points
      .groupBy { it }
      .filter { it.value.size > 1 }
      .keys.size
  }

  private val regexp = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")

  fun segments(input: List<String>): List<Segment> =
    input.map {
      val (_, x1, y1, x2, y2) = regexp.matchEntire(it)!!.groupValues
      segment(x1.toInt(), y1.toInt(), x2.toInt(), y2.toInt())
    }

  data class Point(val x: Int, val y: Int)
  data class Segment(val start: Point, val end: Point) {
    companion object {
      fun segment(x1: Int, y1: Int, x2: Int, y2: Int): Segment =
        if (x2 < x1) {
          Segment(Point(x2, y2), Point(x1, y1))
        } else {
          Segment(Point(x1, y1), Point(x2, y2))
        }
    }

    fun points(): List<Point> = when {
      isHorizontal() -> onHorizontalSegment()
      isVertical() -> onVerticalSegment()
      else -> onDiagonalSegment()
    }

    fun isVertical() = end.x == start.x

    fun isHorizontal() = end.y == start.y

    private fun onHorizontalSegment() = (start.x..end.x).map { Point(it, start.y) }

    private fun onVerticalSegment(): List<Point> {
      val miny = min(start.y, end.y)
      val maxy = max(start.y, end.y)
      return (miny..maxy).map { Point(start.x, it) }
    }

    private fun onDiagonalSegment(): List<Point> {
      val (numerator, denominator) = Fraction.from(end.y - start.y, end.x - start.x)
      val yRange = if (numerator > 0) (start.y..end.y step numerator) else (start.y downTo end.y step (-numerator))
      val xRange = start.x..end.x step denominator
      return xRange.zip(yRange).map { (x, y) -> Point(x, y) }
    }
  }
}