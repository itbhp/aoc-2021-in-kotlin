package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day03.Rates.Companion.zero
import it.twinsbrain.aco.kotlin.Day03.rates
import it.twinsbrain.aco.kotlin.Day03.ratings
import it.twinsbrain.aco.kotlin.common.FileModule.readInput

typealias NumberOfZeros = Int
typealias NumberOfOnes = Int

typealias Bit = Char

fun main() {
  val day3Input = readInput("/inputs/day3.txt")
  val rates = rates(day3Input)
  val rating2 = ratings(day3Input)
  println("Part1: " + rates.gammaRate.value * rates.epsilonRate.value)
  println("Part2: " + rating2.oxygen.value * rating2.co2.value)
}

object Day03 {

  data class Rating(val oxygen: OxygenGeneratorRating, val co2: CO2ScrubberRating) {
    companion object {
      val zero = Rating(OxygenGeneratorRating(0), CO2ScrubberRating(0))
    }
  }

  data class OxygenGeneratorRating(val value: Int)
  data class CO2ScrubberRating(val value: Int)

  private object RatingModule {

    fun co2(list: List<String>): Int = computeRating(list, { zeros, ones -> ones < zeros })

    fun oxygenRating(list: List<String>): Int = computeRating(list, { zeros, ones -> ones >= zeros })

    private tailrec fun computeRating(
      list: List<String>,
      shouldFilterOnes: (NumberOfZeros, NumberOfOnes) -> Boolean,
      index: Int = 0
    ): Int {
      val onesCount = list.count { bits -> bits[index] == '1' }
      val zerosCount = list.size - onesCount
      val value = if (shouldFilterOnes(zerosCount, onesCount)) '1' else '0'
      val filtered = list.filterBy(index, value)
      return if (filtered.size == 1) {
        filtered.first().toInt(2)
      } else {
        computeRating(filtered, shouldFilterOnes, index + 1)
      }
    }

    private fun List<String>.filterBy(index: Int, value: Bit): List<String> =
      filter { bits -> bits[index] == value }
  }

  fun ratings(list: List<String>): Rating {
    if (list.isEmpty()) {
      return Rating.zero
    }
    val oxygen = OxygenGeneratorRating(RatingModule.oxygenRating(list))
    val co2 = CO2ScrubberRating(RatingModule.co2(list))
    return Rating(oxygen, co2)
  }

  fun rates(list: List<String>): Rates {
    return list.takeIf { list.isNotEmpty() }
      ?.let { bitsList ->
        val numberOfBits = bitsList[0].length
        val initialCounters = BitCounters.zero(numberOfBits)
        return bitsList.fold(initialCounters) { counters, bits ->
          counters.updateWith(bits)
        }.toRates()
      }
      ?: zero
  }

  data class Rates(val gammaRate: GammaRate, val epsilonRate: EpsilonRate) {
    companion object {
      val zero = Rates(GammaRate(0), EpsilonRate(0))
    }
  }

  data class GammaRate(val value: Int)
  data class EpsilonRate(val value: Int)

  private data class BitCounters(val zerosCounters: List<Int>, val onesCounters: List<Int>) {

    fun updateWith(bits: String): BitCounters {
      return BitCounters(
        zerosCounters.updateCountersWith(bits) { bit -> bit == '0' },
        onesCounters.updateCountersWith(bits) { bit -> bit == '1' }
      )
    }

    private fun List<Int>.updateCountersWith(bits: String, predicate: (Char) -> Boolean): List<Int> {
      return bits.toList()
        .zip(this)
        .map { (bit, counter) -> if (predicate(bit)) counter + 1 else counter }
    }

    fun gammaRate(): Int =
      rate(zerosCounters, onesCounters) { numberOfZeros, numberOfOnes -> numberOfZeros > numberOfOnes }

    fun epsilonRate(): Int =
      rate(zerosCounters, onesCounters) { numberOfZeros, numberOfOnes -> numberOfZeros < numberOfOnes }

    private fun rate(
      zeros: List<Int>,
      ones: List<Int>,
      predicate: (NumberOfZeros, NumberOfOnes) -> Boolean
    ) = if (ones.isEmpty()) {
      0
    } else {
      Integer.parseInt(
        zeros.zip(ones)
          .map { (numberOfZeros, numberOfOnes) -> if (predicate(numberOfZeros, numberOfOnes)) '0' else '1' }
          .joinToString(""),
        2
      )
    }

    fun toRates(): Rates {
      val gammaRate = gammaRate()
      val epsilonRate = epsilonRate()
      return Rates(GammaRate(gammaRate), EpsilonRate(epsilonRate))
    }

    companion object {
      fun zero(numberOfBits: Int): BitCounters {
        val initialCounters = generateSequence { 0 }.take(numberOfBits).toList()
        return BitCounters(initialCounters, initialCounters)
      }
    }
  }
}