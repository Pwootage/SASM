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

import java.io.{FileInputStream, FileReader, FileOutputStream}

import com.pwootage.sasm.assemblyBase.{AssemblyAlign, AssemblyCodeBase, AssemblyValue}
import com.pwootage.sasm.welbornRISC.WelbornRISCParser
import com.pwootage.sasm.welbornRISC.WelbornRISC._

object TestMain {
  def main(args: Array[String]): Unit = {
    //    val path = Paths.get("/Users/pwootage/vm-shared/kernel/out/pwkern-100")
    //    val fc = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)
    //    val bb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
    //    val file = ELFFile(bb)
    //    println(file)

    val ins = adi(R0, -1234567)
    val insArr = ins.toBinary(null, 0)
    val insLong = (0 to 7).map(i => (insArr(i).toLong & 0xFF) << (i * 8)).fold(0.toLong)(_ | _)
    val ins2 = InstructionBase.fromLong(insLong)

    val res = AssemblyCodeBase.build { implicit code =>
      val file = scala.io.Source.fromFile("/Users/pwootage/school/4380/wriscvm/test_files/count.s")
      val src = file.getLines()
      src.map(l => WelbornRISCParser.parseLine(l)).foreach(i => i.foreach(code.add))
      file.close()
    }
    val out = new FileOutputStream("count.bin")
    out.write(res.compile(verbose = true))
    out.close()
  }
}
