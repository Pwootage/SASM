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

import java.nio.{ByteOrder, ByteBuffer}

import com.pwootage.sasm.elf.exceptions._
import ELFConstants._

/**
 * ELF Main header
 */
class ELFHeader() {
  var magic: Int = 0x464C457f
  var `class`: Byte = ELF_CLASS_64
  var data: Byte = ELF_DATA_2LSB
  var ei_version: Byte = EV_CURRENT
  var osabi: Byte = ELF_OSABI_SYSV
  var osabi_version: Byte = 0
  val padding: Array[Byte] = new Array[Byte](7)
  var `type`: Short = ET_EXEC
  var machine: Short = EM_X86_64
  var version: Int = EV_CURRENT
  var entry: Long = 0
  var phoff: Long = 0
  var shoff: Long = 0
  var flags: Int = 0
  var eh_size: Int = 64
  var ph_size: Short = 0
  var ph_num: Short = 0
  var sh_size: Short = 0
  var sh_num: Short = 0
  var sh_str_index: Short = 0
}

/**
 * Helper object to load things
 */
object ELFHeader {
  import Utils._

  def apply(bb: ByteBuffer) = {
    implicit val header = new ELFHeader()
    //Must load magic in LITTLE ENDIAN (because that's what I'm expecting)
    bb.order(ByteOrder.LITTLE_ENDIAN)
    val magic = bb.getInt
    if (magic != header.magic) throw new InvalidELFException("Invalid magic value")
    header.`class` = bb.get
    header.data = bb.get
    if (header.data == ELF_DATA_2LSB) {
      bb.order(ByteOrder.LITTLE_ENDIAN)
    } else {
      bb.order(ByteOrder.BIG_ENDIAN)
    }
    if (bb.get != header.version) throw new InvalidELFException("Invalid ELF_I version!")
    header.osabi = bb.get
    header.osabi_version = bb.get
    bb.get(header.padding)
    header.`type` = bb.getShort
    header.machine = bb.getShort
    if (bb.getInt != header.version) throw new InvalidELFException("Invalid ELF version")

    header.entry = bb.getWord
    header.phoff = bb.getWord
    header.shoff = bb.getWord
    header.flags = bb.getInt
    header.eh_size = bb.getShort
    header.ph_size = bb.getShort
    header.ph_num = bb.getShort
    header.sh_size = bb.getShort
    header.sh_num = bb.getShort
    header.sh_str_index = bb.getShort
    header
  }
}