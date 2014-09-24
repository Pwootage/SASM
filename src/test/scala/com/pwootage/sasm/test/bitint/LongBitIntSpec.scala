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

package com.pwootage.sasm.test.bitint

class LongBitIntSpec extends BitIntSpec {

  import com.pwootage.sasm.bitint.LongBitIntImpl._

  def $ = LongBitIntImpl

  it should "add" in {
    val a = $(3)
    val b = $(5)
    (a + b).toLong should be(8)
  }

  it should "add and overflow correctly" in {
    val a = $(value = 15, bits = 4)
    val b = $(value = 4, bits = 4)
    (a + b).toLong should be(3)
  }

  it should "subtract" in  {
    val a = $(value = 5, bits = 4)
    val b = $(value = 3, bits = 4)
    (a - b).toLong should be (2)
  }

  it should "subtract with correct overflow" in {
    val a = $(value = 8, bits = 4) // -8
    val b = $(value = 1, bits = 4) // 1
    (a - b).toLong should be (7)
  }

  it should "do two's complimenet signed numbers with correct extension to long" in {
    val a = $(value = 15, bits = 4)
    a.toLong should be(15)
    a.toSignedLong should be(-1)
  }

  it should "bitwise not" in {
    val a = $(value = 0xA5, bits = 8)
    //0xA5 = 1010 0101
    //0x5A = 0101 1010
    (~a).toLong should be (0x5A)
  }

  it should "bitwise and" in {
    val a = $(value = 0xFAFA, bits = 16)
    val b = $(value = 0x0F0F, bits = 16)
    (a & b).toLong should be (0x0A0A)
  }
  
  it should "bitwise or" in {
    val a = $(value = 0xF0F0, bits = 16)
    val b = $(value = 0x0F0F, bits = 16)
    (a | b).toLong should be (0xFFFF)
  }

  it should "bitwise xor" in {
    val a = $(value = 0xA5A5, bits = 16)
    val b = $(value = 0xFF00, bits = 16)
    (a ^ b).toLong should be (0x5AA5)
  }

  it should "do nothing with + operator" in {
    val a = $(value = 7, bits = 4)
    val b = $(value = 15, bits = 4)
    (+a).toLong should be (7)
    (+b).toLong should be (15)
    (+b).toSignedLong should be (-1)
  }

  it should "mathematically invert" in {
    val a = $(value = 7, bits = 4)
    (-a).toSignedLong should be (-7)
    (-a).toLong should be (9)
  }

  it should "shift right with sign extension" in {
    val a = $(value = 0x5A, bits = 8)
    val b = $(value = 0xA5, bits = 8)
    (a >> 1).toLong should be (0x2D)
    (b >> 1).toLong should be (0xD2)
  }

  it should "shift right with no sign extenxion" in {
    val a = $(value = 0x5A, bits = 8)
    val b = $(value = 0xA5, bits = 8)
    (a >>> 1).toLong should be (0x2D)
    (b >>> 1).toLong should be (0x52)
  }

  it should "shift left" in {
    val a = $(value = 0x5A, bits = 8)
    val b = $(value = 0xA5, bits = 8)
    (a << 1).toLong should be (0xB4)
    (b << 1).toLong should be (0x4A)
  }
}
