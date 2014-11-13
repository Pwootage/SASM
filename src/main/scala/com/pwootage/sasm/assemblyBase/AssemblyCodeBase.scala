/*
 * Copyright (c) 2014 Pwootage
 *
 * This file is part of SASM.
 *
 * SASM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SASM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SASM.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.pwootage.sasm.assemblyBase

/**
 * A block of Assembly code. Not much of this class is particularly efficent,
 * but to be perfectly honest, it doesn't even matter.
 *
 * @author Pwootage
 */
class AssemblyCodeBase(_instructions: Seq[AssemblyValue]) {
  var instructions: Seq[AssemblyValue] = _instructions

  def add(v: AssemblyValue): Unit = instructions = instructions :+ v

  def add(v: Seq[AssemblyValue]): Unit = instructions = instructions ++ v

  override def toString = instructions.toString()

  def compile(verbose: Boolean = false): Array[Byte] = {
    val lt = new SymbolLookupTable()
    pass1(lt, verbose)
    if (verbose) lt.foreach(println)
    pass2(lt, verbose)
  }

  private def pass1(lt: SymbolLookupTable, verbose: Boolean): Unit = {
    var currentIndex = 0L
    for (v <- instructions) {
      v match {
        case l: AssemblyLabel => lt.add(l.name, currentIndex)
        case _ =>
      }
//      println(s"${currentIndex.toHexString}\t $v")
      currentIndex += v.length(currentIndex)
    }
  }

  private def pass2(lt: SymbolLookupTable, verbose: Boolean): Array[Byte] = {
    //Probably not uber-efficent but it doesn't matter :)
    var currentIndex = 0L
    (for (v <- instructions) yield {
      try {
        val bin = v.toBinary(lt, currentIndex)
        if (verbose) {
          val binstring = bin.map(_.formatted("%02x")).fold("")(_ + _)
          val padding = (0 to math.max(2 - binstring.length / 4 + 1, 1)).map(_ => "\t").fold("")(_ + _)
          println(s"${currentIndex.toHexString}\t${binstring}${padding}$v")
        }
        currentIndex += v.length(currentIndex)
        bin
      } catch {
        case e:Throwable =>
          throw new Error(s"Error processing instruction $v", e)
      }
    }).fold(Array())(_ ++ _)
  }
}

object AssemblyCodeBase {
  def build(builder: AssemblyCodeBase => Unit) = {
    implicit var code = new AssemblyCodeBase(Seq())
    builder(code)
    code
  }
}
