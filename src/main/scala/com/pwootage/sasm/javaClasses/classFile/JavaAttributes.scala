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

package com.pwootage.sasm.javaClasses.classFile

import com.pwootage.sasm.javaClasses.classFile.JavaClassConstants.ClassInfo


object JavaAttributes {

  /**
   * Attributes for methods, fields, classes
   *
   * @author Pwootage
   */
  class JavaAttributeInfo(val name: String)

  class ConstantValueAttribute extends JavaAttributeInfo("ConstantValue")

  case class ConstantValueAttributeLong(value: Long) extends ConstantValueAttribute

  case class ConstantValueAttributeFloat(value: Float) extends ConstantValueAttribute

  case class ConstantValueAttributeDouble(value: Double) extends ConstantValueAttribute

  case class ConstantValueAttributeInteger(value: Int) extends ConstantValueAttribute

  case class ConstantValueAttributeString(value: String) extends ConstantValueAttribute

  case class CodeAttribute
  (
    max_stack: Int,
    max_locals: Int,
    code: Array[Byte],
    exeption_table: Array[ExceptionTableEntry],
    attributes: Array[JavaAttributeInfo]
    ) extends JavaAttributeInfo("Code")

  class ExceptionTableEntry
  (
    val start_pc: Short,
    val end_pc: Short,
    val handler_pc: Short,
    val catch_type: Short
    )

  case class StackMapTable
  (
    entries: Array[StackMapTableEntry]
    ) extends JavaAttributeInfo("StackMapTable")

  class StackMapTableEntry
  (
    //TODO: Figure out what in the world these are
    //if it's just for bytecode verification I might be lazy for a while
    )

  case class ExceptionsAttribute
  (
  exceptions: Array[ClassInfo]
    ) extends JavaAttributeInfo("Exceptions")


}



