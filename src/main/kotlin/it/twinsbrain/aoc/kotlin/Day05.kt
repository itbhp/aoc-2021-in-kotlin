package it.twinsbrain.aoc.kotlin

import it.twinsbrain.aoc.kotlin.Day05.Segment.Companion.segment
import java.lang.Integer.min
import kotlin.math.max

object Day05 {

  fun howManyIntersections(input: List<String>): Int {
    val flatMap: List<Point> = segments(input).flatMap { it.points() }
    val groupBy: Map<Point, List<Point>> = flatMap.groupBy { it }.filter { it.value.size > 1 }
    return groupBy.keys.size
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
      fun segment(x1: Int, y1: Int, x2: Int, y2: Int): Segment {
        if (x2 < x1) {
          return Segment(Point(x2, y2), Point(x1, y1))
        }
        return Segment(Point(x1, y1), Point(x2, y2))
      }
    }

    fun points(): List<Point> {
      if (end.y == start.y) {
        return (start.x..end.x).map { Point(it, start.y) }.toList()
      }
      if (end.x == start.x) {
        val miny = min(start.y, end.y)
        val maxy = max(start.y, end.y)
        return (miny..maxy).map { Point(start.x, it) }.toList()
      }
      val slope = (end.y - start.y) / (end.x - start.x)
      val list = mutableListOf(start)
      var previous = start
      var reachedEnd = false
      while (!reachedEnd) {
        val next = Point(previous.x + 1, previous.y + slope)
        if (next == end) {
          reachedEnd = true
        }
        list.add(next)
        previous = next
      }
      return list
    }
  }
}