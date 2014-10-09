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

import java.io.InputStream

import com.pwootage.sasm.javaClasses.classFile.JavaAttributes.JavaAttributeInfo
import com.pwootage.sasm.javaClasses.classFile.JavaClassConstants.JavaClassConstant

/**
 * Represents a java class in binary form.
 *
 * @author Pwootage
 */
class JavaClass {
  val magic: Int = 0xCAFEBABE
  var minor_version: Short = 0
  var major_version: Short = 52

  var constant_pool: Array[JavaClassConstant] = Array()
  var access_flags: Short = 0
  var this_class: Short = 1
  var super_class: Short = 0
  var interfaces: Array[Short] = Array()
  var fields: Array[JavaFieldInfo] = Array()
  var methods: Array[JavaMethodInfo] = Array()
  var attributes: Array[JavaAttributeInfo] = Array()
}
