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

package com.pwootage.sasm.test.parser

import com.pwootage.sasm.welbornRISC.WelbornRISCParser
import org.scalatest._

class SpecialSplitSpec extends FlatSpec with Matchers with
OptionValues with Inside with Inspectors {

  val split = WelbornRISCParser.specialSplit _

  it should "split basic string on seperators" in {
    val str = "this that,the\tother"
    split(str) should be (Seq("this", "that", "the", "other"))
  }

  it should "ignore consecutive seperators" in {
    val str = "this   that ,,  the  \t \t other"
    split(str) should be (Seq("this", "that", "the", "other"))
  }

  it should "parse basic quoted strings" in {
    val str = "this that 'the other'"
    split(str) should be (Seq("this", "that", "the other"))
  }

  it should "parse quoted strings with any seperator" in {
    val str = "this that 'the \t,other"
    split(str) should be (Seq("this", "that", "the \t,other"))
  }

  it should "escape anything in a quoted string" in {
    val str = """this that 'the \o\t\h\e\r\ \'\\\n"""
    split(str) should be (Seq("this", "that", "the other \'\\\n"))
  }
}
