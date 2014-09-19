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

package com.pwootage.sasm.elf

import java.nio.ByteBuffer

import ELFConstants._

/**
 * ELF Program Header
 */
class ELFProgramHeader {
  var `type`: Int = PT_NULL
  var flags: Int = 0
  var offset: Long = 0
  var vaddr: Long = 0
  var paddr: Long = 0
  var filesz: Long = 0
  var memsz: Long = 0
  var align: Long = 0
}

object ELFProgramHeader {
  def apply(bb: ByteBuffer)(implicit header: ELFHeader): ELFProgramHeader = {
    val ret = new ELFProgramHeader()
    if (header.`class` == ELF_CLASS_32) {
      ret.`type` = bb.getInt
      ret.offset = bb.getInt
      ret.vaddr = bb.getInt
      ret.paddr = bb.getInt
      ret.filesz = bb.getInt
      ret.memsz = bb.getInt
      ret.flags = bb.getInt
      ret.align = bb.getInt
    } else {
      ret.`type` = bb.getInt
      ret.flags = bb.getInt
      ret.vaddr = bb.getLong
      ret.paddr = bb.getLong
      ret.filesz = bb.getLong
      ret.memsz = bb.getLong
      ret.align = bb.getLong
    }
    ret
  }
}