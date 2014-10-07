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

package com.pwootage.sasm.java.classFile

/**
 * Contains classes that represent constants within a java class file
 *
 * @author Pwootage
 */
object JavaClassConstants {
  /** Class constant base class */
  abstract class JavaClassConstant(val tag: Byte)

  class UTF8Info(val str: String) extends JavaClassConstant(1)

  class IntegerInfo(val value: Int) extends JavaClassConstant(3)

  class FloatInfo(val value: Float) extends JavaClassConstant(4)

  class LongInfo(val value: Long) extends JavaClassConstant(5)

  class DoubleInfo(val value: Double) extends JavaClassConstant(6)

  class ClassInfo(val name_index: Short) extends JavaClassConstant(7)

  class StringInfo(val string_index: Short) extends JavaClassConstant(8)

  class FieldRefInfo(val class_index: Short, val name_and_type_index: Short) extends JavaClassConstant(9)

  class MethodRefInfo(val class_index: Short, val name_and_type_index: Short) extends JavaClassConstant(10)

  class InterfaceMethodRefInfo(val class_index: Short, val name_and_type_index: Short) extends JavaClassConstant(11)

  class NameAndTypeInfo(val name_index: Short, val descriptor_index: Short) extends JavaClassConstant(12)

  class MethodHandleInfo(val reference_kind: Byte, val reference_index: Short) extends JavaClassConstant(15)

  class MethodTypeInfo(val descriptor_index: Short) extends JavaClassConstant(16)

  class InvokeDynamicInfo(val bootstrap_method_attr_index: Short, val name_and_type_index: Short) extends JavaClassConstant(18)



}
