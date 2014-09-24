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

package com.pwootage.sasm

import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.nio.file.{StandardOpenOption, OpenOption, Paths}

import com.pwootage.sasm.assemblyBase.{AssemblyAlign, SymbolLookupTable, AssemblyValue, AssemblyCodeBase}
import com.pwootage.sasm.elf._

object TestMain {
  def main(args: Array[String]): Unit = {
    //    val path = Paths.get("/Users/pwootage/vm-shared/kernel/out/pwkern-100")
    //    val fc = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)
    //    val bb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
    //    val file = ELFFile(bb)
    //    println(file)

    val res = AssemblyCodeBase.build { implicit code =>
      import com.pwootage.sasm.welbornRISC.WelbornRISC._
      jmp('test1)
      jmp('test2)
      'test1 |;
      code.add(AssemblyAlign(1024))
      'test2 |;
      jmr(R0)
    }
    val out = new FileOutputStream("test.bin")
    out.write(res.compile())
    out.close()
  }
}
