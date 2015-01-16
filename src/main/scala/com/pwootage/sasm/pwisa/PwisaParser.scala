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
import com.pwootage.sasm.exceptions.{InvalidInstructionException, UnknownDirectiveException, UnknownInstructionException}
import com.pwootage.sasm.parser.AssemblerParser.AssemblerDialectBase
import PwisaConstants._
import com.pwootage.sasm.pwisa.PwisaInstructions.PwisaInstruction

/**
 * Parser that can parse instructions and directives from WelbornRISC
 */
object PwisaParser extends AssemblerDialectBase {

  import Registers._
  import Instructions._
  import AssemblyMode._

  override def directive(split: Seq[String]): Seq[AssemblyValue] = {
    val directive = split(0).toLowerCase
    directives.get(directive) match {
      case Some(x) => x(split.slice(1, split.length))
      case None => throw new UnknownDirectiveException(directive)
    }
  }

  override def instruction(split: Seq[String]): Seq[AssemblyValue] = {
    val instr = split(0).toLowerCase
    instructions.get(instr) match {
      case Some(x) => Seq(x(split.slice(1, split.length)))
      case None => throw new UnknownInstructionException(instr)
    }
  }

  val instructions = Map[String, Seq[String] => AssemblyValue](
    "trap" -> mode0(0x0),
    "sys" -> mode0(0x1),
    //math
    "add" -> mode1.dab(0x2, 0x0),
    "sub" -> mode1.dab(0x2, 0x1),
    "mul" -> mode1.dab(0x2, 0x2),
    "div" -> mode1.dab(0x2, 0x3),
    "mod" -> mode1.dab(0x2, 0x4),
    //bitwise
    "slz" -> mode1.dabi(0x3, 0x0),
    "srs" -> mode1.dabi(0x3, 0x1),
    "srz" -> mode1.dabi(0x3, 0x2),
    "and" -> mode1.dabi(0x3, 0x3),
    "or" -> mode1.dabi(0x3, 0x4),
    "xor" -> mode1.dabi(0x3, 0x5),
    //Immediates
    "ori" -> mode2(0x4),
    "andi" -> mode2(0x5),
    "addi" -> mode2(0x6),
    "exts" -> mode2(0x7),
    //mem
    "li" -> mode2(0x8),
    "ls" -> mode2(0x9),
    "lb" -> mode2(0xA),
    "lspr" -> mode2(0xB),
    "si" -> mode2(0xC),
    "ss" -> mode2(0xD),
    "sb" -> mode2(0xE),
    "sspr" -> mode2(0xF),
    //Branch
    "sfeq" -> mode1.dab(0x10, 0x0),
    "sfneq" -> mode1.dab(0x10, 0x1),
    "sflt" -> mode1.dab(0x10, 0x2),
    "sfle" -> mode1.dab(0x10, 0x3),
    "sfgt" -> mode1.dab(0x10, 0x4),
    "sfge" -> mode1.dab(0x10, 0x5),
    "bf" -> mode3(0x11),
    "bnf" -> mode3(0x12),
    "j" -> mode3(0x13),
    "jal" -> mode3(0x14),
    //FP
    "addf" -> mode1.dab(0x15, 0x0),
    "subf" -> mode1.dab(0x15, 0x1),
    "mulf" -> mode1.dab(0x15, 0x2),
    "divf" -> mode1.dab(0x15, 0x3),
    "ftoi" -> mode1.dab(0x15, 0x4),
    "itof" -> mode1.dab(0x15, 0x5),
    "sqrt" -> mode1.dab(0x15, 0x6),
    //Stack
    "push" -> mode3(0x16),
    "pop" -> mode3(0x17),
    "peek" -> mode3(0x18)
  )

  val directives = Map[String, Seq[String] => Seq[AssemblyValue]](
    ".int" -> { split => split.map(v => new IntDirective(v.toInt))},
    ".byt" -> { split => split.map(v => v.map(c => new ByteDirective(c.toByte))).fold(Seq())(_ ++ _)},
    ".aln" -> { split =>
      val v = if (split.length == 0) 4 else split(0).toInt
      Seq(AssemblyAlign(v))
    }
  )

  private def intToArray(v: Int) = Array[Byte](
    (v >> 24).toByte,
    (v >> 16).toByte,
    (v >> 8).toByte,
    v.toByte
  )

  class IntDirective(v: Int) extends AssemblyValue(8) {
    override def toBinary(lookup: SymbolLookupTable, address: Long): Array[Byte] = intToArray(v)

    override def toString = s"INT $v"
  }

  class ByteDirective(v: Byte) extends AssemblyValue(1) {
    override def toBinary(lookup: SymbolLookupTable, address: Long): Array[Byte] = Array(v)

    override def toString = s"BYTE $v"
  }

  private def getLabel(v: String) = AssemblyLabel(v)

  private def getReg(v: String) = v.toUpperCase match {
    case "R0" => R0
    case "R1" => R1
    case "R2" => R2
    case "R3" => R3
    case "R4" => R4
    case "R5" => R5
    case "R6" => R6
    case "R7" => R7
    case "R8" => R8
    case "R9" => R9
    case "R10" => R10
    case "R11" => R11
    case "R12" => R12
    case "R13" => R13
    case "R14" => R14
    case "R15" => R15
    case "R16" => R16
    case "R17" => R17
    case "R18" => R18
    case "R19" => R19
    case "R20" => R20
    case "R21" => R21
    case "R22" => R22
    case "R23" => R23
    case "R24" => R24
    case "R25" => R25
    case "R26" => R26
    case "R27" => R27
    case "R28" => R28
    case "R29" => R29
    case "R30" => R30
    case "R31" => R31
    case "P0" => P0
    case "P1" => P1
    case "P2" => P2
    case "P3" => P3
    case "P4" => P4
    case "P5" => P5
    case "P6" => P6
    case "P7" => P7
    case "LR" => LR
    case "SP" => SP
    case "FP" => FP
    case "PC" => PC
    case x => throw new InvalidInstructionException("Invalid register: " + x)
  }

  private def getImmed(v: String) = try Left(v.toInt) catch {
    case _: Throwable => Right(v)
  }

  def mode0(opcode: Int)(split: Seq[String]) = {
    PwisaInstruction(
      mode = Mode0,
      opcode = opcode,
      immediate = getImmed(split(0))
    )
  }

  object mode1 {
    def dabi(opcode: Int, subcode: Int)(split: Seq[String]) = {
      PwisaInstruction(
        mode = Mode1,
        opcode = opcode,
        regD = getReg(split(0)),
        regA = getReg(split(1)),
        regB = getReg(split(2)),
        subcode = subcode,
        immediate = getImmed(split(3))
      )
    }

    def dab(opcode: Int, subcode: Int)(split: Seq[String]) = {
      PwisaInstruction(
        mode = Mode1,
        opcode = opcode,
        regD = getReg(split(0)),
        regA = getReg(split(1)),
        regB = getReg(split(2)),
        subcode = subcode
      )
    }
  }

  def mode2(opcode: Int)(split: Seq[String]) = {
    PwisaInstruction(
      mode = Mode2,
      opcode = opcode,
      regD = getReg(split(0)),
      regA = getReg(split(1)),
      immediate = getImmed(split(2))
    )
  }

  def mode3(opcode: Int)(split: Seq[String]) = {
    PwisaInstruction(
      mode = Mode3,
      opcode = opcode,
      regD = getReg(split(0)),
      immediate = getImmed(split(1))
    )
  }

}
