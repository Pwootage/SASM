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

import java.util
import com.pwootage.sasm.exceptions.{DuplicateSymbolException, UnknownSymbolException}

import scala.collection.mutable

/**
 * A symbol -> address lookup table
 *
 * @author Pwootage
 */
class SymbolLookupTable {
  private val map = mutable.Map[String, Long]()

  def lookup(symbol: Symbol): Long = lookup(symbol.name)

  def lookup(symbol: AssemblyLabel): Long = lookup(symbol.name)

  def lookup(symbol: String): Long = map.get(symbol) match {
    case Some(x) => x
    case None => throw new UnknownSymbolException(symbol)
  }

  def add(symbol: String, addr: Long) = if (map.contains(symbol)) {
    throw new DuplicateSymbolException(symbol)
  } else {
    map.put(symbol, addr)
  }
}
