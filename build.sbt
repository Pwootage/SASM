import AssemblyKeys._

name := "sasm"

organization := "com.pwootage.sasm"

version := "0.1.0"

scalaVersion := "2.11.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

assemblySettings

mainClass in assembly := Some("com.pwootage.sasm.AssembleMain")
