package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day03.Rates.Companion.zero
import it.twinsbrain.aco.kotlin.Day03.rates
import it.twinsbrain.aco.kotlin.common.FileModule.readInput

typealias NumberOfZeros = Int
typealias NumberOfOnes = Int

fun main() {
  val day3Input = readInput("/inputs/day3.txt")
  val rates = rates(day3Input)
  println(rates.gammaRate.value * rates.epsilonRate.value)
}

object Day03 {

  fun rates(list: List<String>): Rates {
    return list.takeIf { list.isNotEmpty() }
      ?.let { bitsList->
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

  private data class BitCounters(val zerosCounter: List<Int>, val onesCounter: List<Int>) {

    fun updateWith(bits: String): BitCounters {
      return BitCounters(
        zerosCounter.updateCountersWith(bits) { bit -> bit == '0' },
        onesCounter.updateCountersWith(bits) { bit -> bit == '1' }
      )
    }

    private fun List<Int>.updateCountersWith(bits: String, predicate: (Char) -> Boolean): List<Int> {
      return bits.toList()
        .zip(this)
        .map { (bit, counter) -> if (predicate(bit)) counter + 1 else counter }
    }

    fun gammaRate(): Int = rate(zerosCounter, onesCounter) { numberOfZeros, numberOfOnes -> numberOfZeros > numberOfOnes }

    fun epsilonRate(): Int = rate(zerosCounter, onesCounter) { numberOfZeros, numberOfOnes -> numberOfZeros < numberOfOnes }

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