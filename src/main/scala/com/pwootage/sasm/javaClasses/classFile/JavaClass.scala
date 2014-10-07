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

import com.pwootage.sasm.javaClasses.classFile.JavaClassConstants.JavaClassConstant

/**
 * Represents a java class in binary form.
 *
 * @author Pwootage
 */
class JavaClass {
  val magic: Int = 0xCAFEBABE
  val minor_version: Short = 0
  val major_version: Short = 52

  def constant_pool_count: Short = constant_pool.length.toShort

  val constant_pool: Array[JavaClassConstant] = Array()
  val access_flags: Short = 0
  val this_class: Short = 1
  val super_class: Short = 0
  val interfaces: Array[Short] = Array()
  val fields: Array[JavaFieldInfo] = Array()
  val methods: Array[JavaMethodInfo] = Array()
//  val attributes: Array
}
