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

object TestMain {
  def main(args: Array[String]): Unit = {
    //    val path = Paths.get("/Users/pwootage/vm-shared/kernel/out/pwkern-100")
    //    val fc = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE)
    //    val bb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size())
    //    val file = ELFFile(bb)
    //    println(file)

    val res = AssemblyCodeBase.build { implicit code =>
      import com.pwootage.sasm.welbornRISC.WelbornRISC._
      val file = scala.io.Source.fromFile("test.s")
      val src = file.getLines()
      src.map(l => WelbornRISCParser.parseLine(l)).foreach(i => i.foreach(code.add))
      file.close()
      code.instructions.foreach(println)
      Seq[AssemblyValue](
        jmp('test1),
        jmp('test2),
        'test1,
        AssemblyAlign(1024),
        'test2,
        jmr(R0)
      )//.foreach(i => code.add(i))

      val InstructionBase(o,a,r1,r2,im) = add(R0, R1)
      print(o,a,r1,r2,im);
    }
    val out = new FileOutputStream("test.bin")
    out.write(res.compile())
    out.close()
  }
}
