package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.common.FileModule

typealias NumberOfZeros = Int
typealias NumberOfOnes = Int

fun main(){
  val day3Input = FileModule.readInput("/inputs/day3.txt")
  val rates = Day03.rates(day3Input)
  println(rates.gammaRate.value * rates.epsilonRate.value)
}

object Day03 {

  private data class BitCounters(val zeros: MutableList<Int>, val ones: MutableList<Int>) {

    fun gammaRate(): Int = rate(zeros, ones) { numberOfZeros, numberOfOnes -> numberOfZeros > numberOfOnes }

    fun epsilonRate(): Int = rate(zeros, ones) { numberOfZeros, numberOfOnes -> numberOfZeros < numberOfOnes }

    private fun rate(
      zeros: MutableList<Int>,
      ones: MutableList<Int>,
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

    fun updateWith(bits: String): BitCounters {
      initializeCounters(bits.length)
      return BitCounters(
        zeros.updateCounters(bits) { bit -> bit == '0' },
        ones.updateCounters(bits) { bit -> bit == '1' }
      )
    }

    private fun MutableList<Int>.updateCounters(bits: String, predicate: (Char) -> Boolean): MutableList<Int> {
      return bits.toList()
        .zip(this)
        .map { (bit, counter) -> if (predicate(bit)) counter + 1 else counter }
        .toMutableList()
    }

    private fun initializeCounters(size: Int) {
      if (zeros.isEmpty()) {
        repeat(size) { zeros.add(0) }
      }
      if (ones.isEmpty()) {
        repeat(size) { ones.add(0) }
      }
    }

    fun toRates(): Rates {
      val gammaRate = gammaRate()
      val epsilonRate = epsilonRate()
      return Rates(GammaRate(gammaRate), EpsilonRate(epsilonRate))
    }

    companion object {
      val zero = BitCounters(mutableListOf(), mutableListOf())
    }
  }

  fun rates(list: List<String>): Rates {
    val bitCounters = list.fold(BitCounters.zero) { counters, bits ->
      counters.updateWith(bits)
    }
    return bitCounters.toRates()
  }

  data class Rates(val gammaRate: GammaRate, val epsilonRate: EpsilonRate)
  data class GammaRate(val value: Int)
  data class EpsilonRate(val value: Int)
}