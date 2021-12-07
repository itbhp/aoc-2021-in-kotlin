package it.twinsbrain.aoc.kotlin

object Day05 {

  fun howManyIntersections(input: List<String>): Int {
    TODO()
  }

  private val regexp = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")

  fun segments(input: List<String>): List<Segment> =
    input.map {
      val (x1, y1, x2, y2) = regexp.find(it)!!.destructured
      Segment(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt()))
    }

  data class Point(val x: Int, val y: Int)
  data class Segment(val start: Point, val end: Point) {
    fun intersect(another: Segment): Point? = TODO()
  }
}