package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.Segment.Companion.segment
import it.twinsbrain.aoc.kotlin.common.FileModule
import it.twinsbrain.aoc.kotlin.common.MathModule
import java.lang.Integer.min
import kotlin.math.max

fun main() {
  val day5Input = FileModule.readInput("/inputs/day05.txt")
  val intersections = Day05.howManyIntersections(day5Input)
  println(intersections)
}

object Day05 {

  fun howManyIntersections(input: List<String>): Int {
    val points: List<Point> = segments(input).flatMap { it.points() }
    return points
      .groupBy { it }
      .filter { it.value.size > 1 }
      .keys.size
  }

  private val regexp = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")

  fun segments(input: List<String>): List<Segment> =
    input.map {
      val (x1, y1, x2, y2) = regexp.find(it)!!.destructured
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
      end.y == start.y -> onHorizontalSegment()
      end.x == start.x -> onVerticalSegment()
      else -> onDiagonalSegment()
    }

    private fun onHorizontalSegment() = (start.x..end.x).map { Point(it, start.y) }

    private fun onVerticalSegment(): List<Point> {
      val miny = min(start.y, end.y)
      val maxy = max(start.y, end.y)
      return (miny..maxy).map { Point(start.x, it) }
    }

    private fun onDiagonalSegment(): List<Point> {
      val (numerator, denominator) = MathModule.Fraction.from(end.y - start.y, end.x - start.x)
      val yRange = if (numerator > 0) (start.y..end.y step numerator) else (start.y downTo end.y step (-numerator))
      val xRange = start.x..end.x step denominator
      return xRange.zip(yRange).map { (x, y) -> Point(x, y) }
    }
  }
}