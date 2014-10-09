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

import com.pwootage.sasm.javaClasses.classFile.JavaAttributes.JavaAttributeInfo

/**
 * Represents a java method
 *
 * @author Pwootage
 */
class JavaMethodInfo
(
  val access_flags: Short,
  val name: String,
  val descriptor: String,
  val attributes: Array[JavaAttributeInfo]
  ) {

}
