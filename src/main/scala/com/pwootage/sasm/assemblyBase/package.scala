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

package com.pwootage.sasm

package object assemblyBase {

  /**
   * Base class for any part of assembly
   */
  abstract class AssemblyValue(len: Long => Long) {
    def this(len: Long) = this(_ => len)

    /**
     * Binary length of this value, in bytes
     */
    def length(address: Long): Long = len(address)

    /**
     * Convert into binary.
     * @param lookup Symbol lookup table if you need it
     */
    def toBinary(lookup: SymbolLookupTable, address: Long): Array[Byte]
  }

  /**
   * An assembly instruction - an actual instruction for the assembler
   */
  abstract class AssemblyInstruction(len: Int) extends AssemblyValue(len) {
  }

  /**
   * An assembly label. Used to mark a location.
   */
  case class AssemblyLabel(name: String) extends AssemblyValue(0) {
    override def toBinary(lookup: SymbolLookupTable, addr: Long): Array[Byte] =
      Array()
  }

  /**
   * Special assembly directive that aligns to the nearest boundry.
   */
  case class AssemblyAlign(size: Int) extends AssemblyValue(addr => size - (addr % size)) {
    override def toBinary(lookup: SymbolLookupTable, addr: Long): Array[Byte] =
      (1L to length(addr)).map(_ => 0.toByte).toArray
  }

  case class AssemblyRegister(num: Int)

}
