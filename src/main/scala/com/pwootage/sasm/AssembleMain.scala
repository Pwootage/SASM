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
import java.nio.file.Paths

import com.pwootage.sasm.assemblyBase.AssemblyCodeBase
import com.pwootage.sasm.pwisa.PwisaParser
import org.rogach.scallop._
import org.rogach.scallop.exceptions.{ScallopException, Exit, RequiredOptionNotFound}

import scala.io.Source

/**
 * Main entry-point for file assembly
 *
 * @author Pwootage
 */
object AssembleMain {

  class CmdArgConf(args: Seq[String]) extends LazyScallopConf(args) {
    version(s"${Info.name} ${Info.version} Copyright (c) 2014 Chris Freestone")
    banner( s"""Usage: ${Info.name} -f <format> [OPTIONS]... <files>...
             |${Info.name} is an assembler. It can support a number of formats.
             |(or, at least, will be able to.)
             |
             |This program comes with ABSOLUTELY NO WARRANTY
             |This is free software, and you are welcome to redistribute it
             |under certain conditions.
             |You should have received a copy of the GPL with this program,
             |usually in a file named COPYING. See this for more details.
             |
             |Options:
             |""".stripMargin)
    val output = opt[String]("out", short = 'o', descr = "Output File (default is stdout)", default = None)
    val dialect = opt[String]("format", short = 'f', descr = "ASM Dialect of input", required = true)
    val verbose = toggle("verbose", short = 'v', default = Some(false))
    validate(dialect) { d =>
      if (Dialects.contains(d)) {
        Right(Unit)
      } else {
        Left(s"Unknown dialect: $d")
      }
    }
    val files = trailArg[List[String]]("files", descr = "Files to compile", required = true)
  }

  val DialectPwisa = "pwisa"
  val Dialects = Seq(DialectPwisa)

  def main(args: Array[String]) {
    val conf = new CmdArgConf(args)
    conf initialize {
      case Exit() =>
        conf.printHelp()
        System.exit(0)
      case ScallopException(msg) =>
        println(msg)
        conf.printHelp()
        System.exit(1)
    }

    val infiles = conf.files().map(p => Paths.get(p))

    for (file <- infiles.map(_.toFile)) {
      if (file.isDirectory) {
        println("Input file is directory: " + file)
        System.exit(1)
      } else if (!file.exists) {
        println("Input file does not exist: " + file)
        System.exit(1)
      } else if (!file.isFile) {
        println("Input file is not a file: " + file)
        System.exit(1)
      }
    }

    val parse = conf.dialect() match {
      case DialectPwisa => PwisaParser.parseLine _
    }

    //I love functional programming
    val sources = infiles.map(f => Source.fromFile(f.toFile))

    val lines = sources.map(s => s.getLines()).fold(Iterator[String]())(_ ++ _)

    val code = AssemblyCodeBase.build { code =>
      code.add(lines.map(parse).fold(Seq())(_ ++ _))
    }

    sources.foreach(_.close())

    val out = conf.output.get match {
      case Some(x) => new FileOutputStream(x)
      case None => System.out
    }
    out.write(code.compile(verbose = conf.verbose()))
    out.flush()
    out.close()
  }
}
