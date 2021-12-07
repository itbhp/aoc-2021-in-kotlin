package it.twinsbrain.aoc.kotlin.common

import kotlin.math.abs

object MathModule {
  data class Fraction(val numerator: Int, val denominator: Int) {
    companion object {
      fun from(numerator: Int, denominator: Int): Fraction =
        if (numerator == 0 || denominator == 0) {
          Fraction(numerator, denominator)
        } else {
          val gcd = gcd(numerator, denominator)
          Fraction(numerator / gcd, denominator / gcd)
        }
    }
  }

  private fun gcd(a: Int, b: Int): Int {
    var x = abs(a)
    var y = abs(b)
    while (x != y) {
      if (x > y) {
        x -= y
      } else {
        y -= x
      }
    }
    return x
  }
}