/*
 * Copyright (c) 2014 Pwootage
 *
 * This file is part of SASM86.
 *
 * SASM86 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SASM86 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SASM86.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.pwootage.sasm.bitint

/**
 * A integer of defined bit-length. Acts like an integer with exaclty the number of bits specified.
 * Has both signed and unsigned modes.
 *
 * @author Pwootage
 */
trait BitInt {
  /**
   * Two's compliment addition
   */
  def +(other: BitInt): BitInt

  /**
   * Two's compliment subtraction
   */
  def -(other: BitInt): BitInt

  //def *(other: BitInt): BitInt
  //def /(other: BitInt): BitInt

  /**
   * Bitwise not
   */
  def unary_~ : BitInt

  /**
   * Doesn't do anything
   */
  def unary_+ : BitInt

  /**
   * Two's compliment negative
   */
  def unary_- : BitInt

  /**
   * Left shift, n places, new bits are zero
   */
  def <<(places: Int): BitInt

  /**
   * Sign-extension right-shift.
   */
  def >>(places: Int): BitInt

  /**
   * Right shift, new bits are zero
   */
  def >>>(places: Int): BitInt

  /**
   * Bitwise XOR
   */
  def ^(other: BitInt): BitInt

  /**
   * Bitwise OR
   */
  def |(other: BitInt): BitInt

  /**
   * Bitwise AND
   */
  def &(other: BitInt): BitInt

  /**
   * Retrieve up to the lowest 64 bits from this BitInt.
   */
  def toLong: Long

  /**
   * Retrieve up to the lowest 64 bits from this BitInt, signed correctly
   */
  def toSignedLong: Long

}
