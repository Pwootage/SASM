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

package com.pwootage.sasm.pwisa

import com.pwootage.sasm.assemblyBase._
import com.pwootage.sasm.pwisa.PwisaConstants.AssemblyMode
import PwisaConstants.Masks._

/**
 * Helpers for building Pwisa instructions
 */
object PwisaInstructions {

  private def intToArray(v: Int) = Array[Byte](
    (v >> 24).toByte,
    (v >> 16).toByte,
    (v >> 8).toByte,
    v.toByte
  )

  case class PwisaInstruction(mode: AssemblyMode,
                             opcode: Int,
                             regD: Int = 0,
                             regA: Int = 0,
                             regB: Int = 0,
                             subcode: Int = 0,
                             immediate: Either[Int, String] = 0) extends AssemblyInstruction(4) {
    private def imm(implicit lut: SymbolLookupTable) = immediate match {
      case Left(x) => x
      case Right(x) => lut.lookup(x).toInt //Truncates... which is fine, >2gb text is unlikely (and probably will crash)
    }
    override def toBinary(implicit lookup: SymbolLookupTable, address: Long) = {
      val intVal = mode match {
        case AssemblyMode.Mode0 =>
          (opcode & Mode0.Opcode.Mask) << Mode0.Opcode.Offset |
            (imm & Mode0.Immediate.Mask) << Mode0.Immediate.Offset
        case AssemblyMode.Mode1 =>
          (opcode & Mode1.Opcode.Mask) << Mode1.Opcode.Offset |
            (regD & Mode1.RegD.Mask) << Mode1.RegD.Offset |
            (regA & Mode1.RegA.Mask) << Mode1.RegA.Offset |
            (regB & Mode1.RegB.Mask) << Mode1.RegB.Offset |
            (subcode & Mode1.Subcode.Mask) << Mode1.Subcode.Offset |
            (imm & Mode1.Immediate.Mask) << Mode1.Immediate.Offset
        case AssemblyMode.Mode2 =>
          (opcode & Mode2.Opcode.Mask) << Mode2.Opcode.Offset |
            (regD & Mode2.RegD.Mask) << Mode2.RegD.Offset |
            (regA & Mode2.RegA.Mask) << Mode2.RegA.Offset |
            (imm & Mode2.Immediate.Mask) << Mode2.Immediate.Offset
        case AssemblyMode.Mode3 =>
          (opcode & Mode3.Opcode.Mask) << Mode3.Opcode.Offset |
            (regD & Mode3.RegD.Mask) << Mode3.RegD.Offset |
            (imm & Mode3.Immediate.Mask) << Mode3.Immediate.Offset
      }
      intToArray(intVal)
    }
  }


}
