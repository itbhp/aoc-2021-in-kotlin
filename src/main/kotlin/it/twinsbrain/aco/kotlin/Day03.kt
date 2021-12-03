package it.twinsbrain.aco.kotlin

import it.twinsbrain.aco.kotlin.Day03.Rates.Companion.zero
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

    fun updateWith(bits: String): BitCounters {
      return BitCounters(
        zeros.updateCounters(bits) { bit -> bit == '0' },
        ones.updateCounters(bits) { bit -> bit == '1' }
      )
    }

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

    private fun MutableList<Int>.updateCounters(bits: String, predicate: (Char) -> Boolean): MutableList<Int> {
      return bits.toList()
        .zip(this)
        .map { (bit, counter) -> if (predicate(bit)) counter + 1 else counter }
        .toMutableList()
    }

    fun toRates(): Rates {
      val gammaRate = gammaRate()
      val epsilonRate = epsilonRate()
      return Rates(GammaRate(gammaRate), EpsilonRate(epsilonRate))
    }

    companion object {
      fun zero(numberOfBits: Int) = BitCounters(
        MutableList(numberOfBits) { 0 },
        MutableList(numberOfBits) { 0 }
      )
    }
  }

  fun rates(list: List<String>): Rates {
    if (list.isEmpty()) {
      return zero
    }
    return list.fold(BitCounters.zero(list[0].length)) { counters, bits ->
      counters.updateWith(bits)
    }.toRates()
  }

  data class Rates(val gammaRate: GammaRate, val epsilonRate: EpsilonRate){
    companion object{
      val zero = Rates(GammaRate(0), EpsilonRate(0))
    }
  }
  data class GammaRate(val value: Int)
  data class EpsilonRate(val value: Int)
}