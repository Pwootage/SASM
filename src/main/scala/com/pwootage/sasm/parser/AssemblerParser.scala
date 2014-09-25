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

package com.pwootage.sasm.parser

import com.pwootage.sasm.assemblyBase.{AssemblyLabel, AssemblyValue}

/**
 * Parses Assembly source code and produces instructions based on it.
 *
 * @author Pwootage
 */
object AssemblerParser {

  /**
   * An assembler dialect is responsable for parsing lines of assembly
   */
  trait AssemblerDialect {
    def parseLine(line: String): Seq[AssemblyValue]
  }

  /**
   * Base assembly dialect, for syntaxes similar to:
   *
   * [label:] (INSTRUCTION|.DIRECTIVE) [Operand...]
   */
  abstract class AssemblerDialectBase {
    def parseLine(line: String): Seq[AssemblyValue] = {
      var res = Seq[AssemblyValue]()
      var split = specialSplit(line)
      if (split.length == 0) {
        return res
      }
      if (split(0).endsWith(":")) {
        res = res :+ AssemblyLabel(split(0).substring(0, split(0).length - 1))
        split = split.slice(1, split.length)
      }
      if (split.length == 0) {
        return res
      }
      res ++ (if (split(0).startsWith(".")) {
        directive(split)
      } else {
        instruction(split)
      })
    }

    /**
     * Nasty gross procedural string parsing. Probably should be refactored to a better state machine.
     */
    def specialSplit(line: String): Seq[String] = {
      var res = Seq[String]()
      var index = 0
      var str = ""
      var inString = false
      var done = false

      def addStr(): Unit = {
        if (str.length > 0) {
          res = res :+ str
          str = ""
        }
      }

      while (!done && index < line.length) {
        val c = line(index)
        if (c == ',' || c == ' ' || c == '\t') {
          if (inString) {
            str += c
          } else {
            addStr()
          }
        } else if (c == '\\') {
          if (inString && index + 1 < line.length) {
            val nc = line(index + 1)
            if (nc == 'n') {
              str += '\n'
            } else if (nc == '0') {
              str += '\0'
            } else if (nc == 'x') {
              val hex = line.substring(index + 2, index + 4)
              str += Integer.parseInt(hex, 16).toChar
              index += 2
            } else {
              str += nc
            }
            index += 1
          }
        } else if (c == '\'') {
          if (inString) {
            addStr()
            inString = false
          } else {
            inString = true
            addStr()
          }
        } else if (c == ';') {
          //Comments!
          done = true
        } else {
          str += c
        }
        index += 1
      }

      addStr()
      res
    }

    def directive(split: Seq[String]): Seq[AssemblyValue]

    def instruction(split: Seq[String]): Seq[AssemblyValue]
  }

}
