import AssemblyKeys._

name := "sasm"

organization := "com.pwootage.sasm"

version := "0.1.0"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.rogach" %% "scallop" % "0.9.5"
)

assemblySettings

mainClass in assembly := Some("com.pwootage.sasm.AssembleMain")

sourceGenerators in Compile <+= (sourceManaged in Compile, version, name) map { (d, v, n) =>
  val file = d / "info.scala"
  IO.write(file, """package com.pwootage.sasm
                   |object Info {
                   |  val version = "%s"
                   |  val name = "%s"
                   |}
                   |""".stripMargin.format(v, n))
  Seq(file)
}
