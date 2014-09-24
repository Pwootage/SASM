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

package com.pwootage.sasm.bitint

/**
 * A implementation of BitInt based on a Long. Note this means it is limited
 * to 64 bits in length. This object also includes implicits to convert from
 * standard numeric types
 *
 * @author Pwootage
 */
object LongBitIntImpl {
  private def pow2(n: Long, v: Long = 1): Long = if (n <= 0) v else pow2(n - 1, v * 2)

  private val masks = (0L to 64L).map(n => pow2(n) - 1).toArray

  case class LongBitIntImpl(value: Long = 0, implicit val bits: Int = 64) extends BitInt {
    if (bits < 1) throw new IllegalArgumentException("Cannot make a int of length less than one")
    if (bits > 64) throw new NotImplementedError("This implementation is limited to 64 bits.")
    private val mask: Long = masks(bits)
    private val _value: Long = value & mask

    private def $(v: Long) = LongBitIntImpl(v, bits)

    override def +(other: BitInt): BitInt = $(_value + other.toLong)

    override def unary_~ : BitInt = $(~_value)

    override def &(other: BitInt): BitInt = $(_value & other.toLong)

    override def unary_- : BitInt = this.unary_~ + 1

    override def |(other: BitInt): BitInt = $(_value | other.toLong)

    override def >>>(places: Int): BitInt = $(value >>> places)

    override def >>(places: Int): BitInt = $(toSignedLong >> places)

    override def ^(other: BitInt): BitInt = $(_value ^ other.toLong)

    override def -(other: BitInt): BitInt = this.+(-other)

    override def <<(places: Int): BitInt = $(_value << places)

    override def unary_+ : BitInt = this

    /**
     * Returns the exact value of this BitInt
     */
    override def toLong: Long = _value

    override def toSignedLong: Long = {
      val sign = _value & (masks(bits - 1) + 1)
      sign match {
        case 0 => _value
        case _ => _value | (~mask)
      }
    }

    override def equals(obj: scala.Any): Boolean = obj.isInstanceOf[BitInt] && obj.asInstanceOf[BitInt].toSignedLong == toSignedLong
  }

  implicit def intToLongBitIntImpl(l: Int)(implicit bits: Int = 64): BitInt = LongBitIntImpl(l)

  implicit def longToLongBitIntImpl(l: Long)(implicit bits: Int = 64): BitInt = LongBitIntImpl(l)
}
