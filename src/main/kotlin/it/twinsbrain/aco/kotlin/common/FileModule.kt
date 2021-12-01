package it.twinsbrain.aco.kotlin.common

import arrow.core.andThen
import java.io.File
import java.net.URI

object FileModule {

  fun readInput(file: String): List<String> = (::uriFrom andThen ::readFile) (file)

  private fun uriFrom(path: String): URI =
    object {}.javaClass.getResource(path)!!.toURI()

  private fun readFile(path: URI): List<String> =
    File(path).readLines()
}