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

/**
 * Constant definitions for PWISA
 */
object PwisaConstants {

  object Instructions {
    val Trap = 0x0
    val Sys = 0x1

    object Math {
      def apply = 0x2

      val Add = 0x0
      val Sub = 0x1
      val Mul = 0x2
      val Div = 0x3
      val Mod = 0x4
    }

    object Bitwise {
      def apply = 0x3

      val Slz = 0x0
      val Sls = 0x1
      val Srz = 0x2
      val And = 0x3
      val Or = 0x4
      val Xor = 0x5
    }

    val Ori = 0x4
    val Andi = 0x5
    val Addi = 0x6
    val Exts = 0x7
    val Li = 0x8
    val Ls = 0x9
    val Lb = 0xA
    val Lspr = 0xB
    val Si = 0xC
    val Ss = 0xD
    val Sb = 0xE
    val Sspr = 0xF

    object Branch {
      def apply = 0x10

      val Sfeq = 0x0
      val Sfne = 0x1
      val Sflt = 0x2
      val Sfle = 0x3
      val Sfgt = 0x4
      val Sfge = 0x5
    }

    val Bf = 0x11
    val Bnf = 0x12
    val J = 0x13
    val Jal = 0x14

    object Fp {
      def apply = 0x15

      val Addf = 0x0
      val Subf = 0x1
      val Mulf = 0x2
      val Divf = 0x3
      val Ftoi = 0x4
      val Itof = 0x5
      val sqrt = 0x6
    }

  }

  object Registers {
    val R0 = 0
    val R1 = 1
    val R2 = 2
    val R3 = 3
    val R4 = 4
    val R5 = 5
    val R6 = 6
    val R7 = 7
    val R8 = 8
    val R9 = 9
    val R10 = 10
    val R11 = 11
    val R12 = 12
    val R13 = 13
    val R14 = 14
    val R15 = 15
    val R16 = 16
    val R17 = 17
    val R18 = 18
    val R19 = 19
    val R20 = 20
    val R21 = 21
    val R22 = 22
    val R23 = 23
    val R24 = 24
    val R25 = 25
    val R26 = 26
    val R27 = 27
    val R28 = 28
    val R29 = 29
    val R30 = 30
    val R31 = 31

    val PZ = R0
    val P0 = R1
    val P1 = R2
    val P2 = R3
    val P3 = R4
    val P4 = R5
    val P5 = R6
    val P6 = R7
    val P7 = R8
    val RV = R9

    val LR = R28
    val FP = R29
    val SP = R30
    val PC = R31
  }

  object Masks {

    object Mode0 {
      object Opcode {
        val Mask = 0x3F
        val Offset = 26
      }
      object Immediate {
        val Mask = 0x3FFFFFF
        val Offset = 0
      }
    }

    object Mode1 {
      object Opcode {
        val Mask = 0x3F
        val Offset = 26
      }
      object RegD {
        val Mask = 0x1F
        val Offset = 21
      }
      object RegA {
        val Mask = 0x1F
        val Offset = 16
      }
      object RegB {
        val Mask = 0x1F
        val Offset = 11
      }
      object Subcode {
        val Mask = 0x7
        val Offset = 8
      }
      object Immediate {
        val Mask = 0xFF
        val Offset = 0
      }
    }

    object Mode2 {
      object Opcode {
        val Mask = 0x3F
        val Offset = 26
      }
      object RegD {
        val Mask = 0x1F
        val Offset = 21
      }
      object RegA {
        val Mask = 0x1F
        val Offset = 16
      }
      object Immediate {
        val Mask = 0xFFFF
        val Offset = 0
      }
    }

    object Mode3 {
      object Opcode {
        val Mask = 0x3F
        val Offset = 26
      }
      object RegD {
        val Mask = 0x1F
        val Offset = 21
      }
      object Immediate {
        val Mask = 0x1FFFFF
        val Offset = 0
      }
    }

    private def getMask(mask: String, letter: String) = {
      val maskStr = mask.replaceAll(letter, "1").replaceAll("[A-Z]", "0")
      BigInt(maskStr, 2).toLong >>> getOffset(mask, letter)
    }

    private def getOffset(mask: String, letter: String) = {
      val maskStr = mask.replaceAll(letter, "1").replaceAll("[A-Z]", "0")
      mask.length - maskStr.lastIndexOf("1") - 1
    }
  }

  trait AssemblyMode
  object AssemblyMode {
    case object Mode0 extends AssemblyMode
    case object Mode1 extends AssemblyMode
    case object Mode2 extends AssemblyMode
    case object Mode3 extends AssemblyMode
  }
}
