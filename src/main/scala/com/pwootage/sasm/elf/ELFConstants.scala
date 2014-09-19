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

package com.pwootage.sasm.elf

/**
 * ELF Constnats
 *
 * @author Pwootage
 */
object ELFConstants {
  //**Constants**//
  //Classes/Bitness:
  /**
   * Invalid
   */
  val ELF_CLASS_NONE: Byte = 0
  /**
   * 32 bit
   */
  val ELF_CLASS_32: Byte = 1
  /**
   * 64 bit
   */
  val ELF_CLASS_64: Byte = 2

  //Data Types/Endianess
  /**
   * Unknown
   */
  val ELF_DATA_NONE: Byte = 0
  /**
   * Two's compliment little-endian
   */
  val ELF_DATA_2LSB: Byte = 1
  /**
   * Two's compliment big-endian
   */
  val ELF_DATA_2MSB: Byte = 2

  //Versions
  /**
   * Invalid
   */
  val EV_NONE: Byte = 0
  /**
   * Version 1 (current version & first version)
   */
  val EV_CURRENT: Byte = 1

  //OS's
  val ELF_OSABI_NONE: Byte = 0
  val ELF_OSABI_SYSV: Byte = ELF_OSABI_NONE
  val ELF_OSABI_HPUX: Byte = 1
  val ELF_OSABI_NETBSD: Byte = 2
  val ELF_OSABI_GNU: Byte = 3
  val ELF_OSABI_LINUX: Byte = ELF_OSABI_GNU
  val ELF_OSABI_SOLARIS: Byte = 6
  val ELF_OSABI_AIX: Byte = 7
  val ELF_OSABI_IRIX: Byte = 8
  val ELF_OSABI_FREEBSD: Byte = 9
  val ELF_OSABI_TRU64: Byte = 10
  val ELF_OSABI_MODESTO: Byte = 11
  val ELF_OSABI_OPENBSD: Byte = 12
  val ELF_OSABI_ARM_AEABI: Byte = 64
  val ELF_OSABI_ARM: Byte = 97
  val ELF_OSABI_STANDALONE: Byte = 255.toByte

  //ELF types
  val ET_NONE: Byte = 0
  val ET_REL: Byte = 1
  val ET_EXEC: Byte = 2
  val ET_DYN: Byte = 3
  val ET_CORE: Byte = 4

  //ISA (there are a LOT of these D:)
  /** No machine */
  val EM_NONE: Short = 0
  /** AT&T WE 32100 */
  val EM_M32: Short = 1
  /** SUN SPARC */
  val EM_SPARC: Short = 2
  /** Intel 80386 */
  val EM_386: Short = 3
  /** Motorola m68k family */
  val EM_68K: Short = 4
  /** Motorola m88k family */
  val EM_88K: Short = 5
  /** Intel 80860 */
  val EM_860: Short = 7
  /** MIPS R3000 big-endian */
  val EM_MIPS: Short = 8
  /** IBM System/370 */
  val EM_S370: Short = 9
  /** MIPS R3000 little-endian */
  val EM_MIPS_RS3_LE: Short = 10

  /** HPPA */
  val EM_PARISC: Short = 15
  /** Fujitsu VPP500 */
  val EM_VPP500: Short = 17
  /** Sun's "v8plus" */
  val EM_SPARC32PLUS: Short = 18
  /** Intel 80960 */
  val EM_960: Short = 19
  /** PowerPC */
  val EM_PPC: Short = 20
  /** PowerPC 64-bit */
  val EM_PPC64: Short = 21
  /** IBM S390 */
  val EM_S390: Short = 22

  /** NEC V800 series */
  val EM_V800: Short = 36
  /** Fujitsu FR20 */
  val EM_FR20: Short = 37
  /** TRW RH-32 */
  val EM_RH32: Short = 38
  /** Motorola RCE */
  val EM_RCE: Short = 39
  /** ARM */
  val EM_ARM: Short = 40
  /** Digital Alpha */
  val EM_FAKE_ALPHA: Short = 41
  /** Hitachi SH */
  val EM_SH: Short = 42
  /** SPARC v9 64-bit */
  val EM_SPARCV9: Short = 43
  /** Siemens Tricore */
  val EM_TRICORE: Short = 44
  /** Argonaut RISC Core */
  val EM_ARC: Short = 45
  /** Hitachi H8/300 */
  val EM_H8_300: Short = 46
  /** Hitachi H8/300H */
  val EM_H8_300H: Short = 47
  /** Hitachi H8S */
  val EM_H8S: Short = 48
  /** Hitachi H8/500 */
  val EM_H8_500: Short = 49
  /** Intel Merced */
  val EM_IA_64: Short = 50
  /** Stanford MIPS-X */
  val EM_MIPS_X: Short = 51
  /** Motorola Coldfire */
  val EM_COLDFIRE: Short = 52
  /** Motorola M68HC12 */
  val EM_68HC12: Short = 53
  /** Fujitsu MMA Multimedia Accelerator */
  val EM_MMA: Short = 54
  /** Siemens PCP */
  val EM_PCP: Short = 55
  /** Sony nCPU embeeded RISC */
  val EM_NCPU: Short = 56
  /** Denso NDR1 microprocessor */
  val EM_NDR1: Short = 57
  /** Motorola Start*Core processor */
  val EM_STARCORE: Short = 58
  /** Toyota ME16 processor */
  val EM_ME16: Short = 59
  /** STMicroelectronic ST100 processor */
  val EM_ST100: Short = 60
  /** Advanced Logic Corp. Tinyj emb.fam */
  val EM_TINYJ: Short = 61
  /** AMD x86-64 architecture */
  val EM_X86_64: Short = 62
  /** Sony DSP Processor */
  val EM_PDSP: Short = 63

  /** Siemens FX66 microcontroller */
  val EM_FX66: Short = 66
  /** STMicroelectronics ST9+ 8/16 mc */
  val EM_ST9PLUS: Short = 67
  /** STmicroelectronics ST7 8 bit mc */
  val EM_ST7: Short = 68
  /** Motorola MC68HC16 microcontroller */
  val EM_68HC16: Short = 69
  /** Motorola MC68HC11 microcontroller */
  val EM_68HC11: Short = 70
  /** Motorola MC68HC08 microcontroller */
  val EM_68HC08: Short = 71
  /** Motorola MC68HC05 microcontroller */
  val EM_68HC05: Short = 72
  /** Silicon Graphics SVx */
  val EM_SVX: Short = 73
  /** STMicroelectronics ST19 8 bit mc */
  val EM_ST19: Short = 74
  /** Digital VAX */
  val EM_VAX: Short = 75
  /** Axis Communications 32-bit embedded processor */
  val EM_CRIS: Short = 76
  /** Infineon Technologies 32-bit embedded processor */
  val EM_JAVELIN: Short = 77
  /** Element 14 64-bit DSP Processor */
  val EM_FIREPATH: Short = 78
  /** LSI Logic 16-bit DSP Processor */
  val EM_ZSP: Short = 79
  /** Donald Knuth's educational 64-bit processor */
  val EM_MMIX: Short = 80
  /** Harvard University machine-independent object files */
  val EM_HUANY: Short = 81
  /** SiTera Prism */
  val EM_PRISM: Short = 82
  /** Atmel AVR 8-bit microcontroller */
  val EM_AVR: Short = 83
  /** Fujitsu FR30 */
  val EM_FR30: Short = 84
  /** Mitsubishi D10V */
  val EM_D10V: Short = 85
  /** Mitsubishi D30V */
  val EM_D30V: Short = 86
  /** NEC v850 */
  val EM_V850: Short = 87
  /** Mitsubishi M32R */
  val EM_M32R: Short = 88
  /** Matsushita MN10300 */
  val EM_MN10300: Short = 89
  /** Matsushita MN10200 */
  val EM_MN10200: Short = 90
  /** picoJava */
  val EM_PJ: Short = 91
  /** OpenRISC 32-bit embedded processor */
  val EM_OPENRISC: Short = 92
  /** ARC Cores Tangent-A5 */
  val EM_ARC_A5: Short = 93
  /** Tensilica Xtensa Architecture */
  val EM_XTENSA: Short = 94
  /** ARM AARCH64 */
  val EM_AARCH64: Short = 183
  /** Tilera TILEPro */
  val EM_TILEPRO: Short = 188
  /** Xilinx MicroBlaze */
  val EM_MICROBLAZE: Short = 189
  /** Tilera TILE-Gx */
  val EM_TILEGX: Short = 191

  // PH types
  /** Program header table entry unused */
  var PT_NULL = 0
  /** Loadable program segment */
  var PT_LOAD = 1
  /** Dynamic linking information */
  var PT_DYNAMIC = 2
  /** Program interpreter */
  var PT_INTERP = 3
  /** Auxiliary information */
  var PT_NOTE = 4
  /** Reserved */
  var PT_SHLIB = 5
  /** Entry for header table itself */
  var PT_PHDR = 6
  /** Thread-local storage segment */
  var PT_TLS = 7

  // PH Flags (same as linux file perms!)
  /** Executable */
  var PF_X = 1 << 0
  /** Writable */
  var PF_W = 1 << 1
  /** Readable */
  var PF_R = 1 << 2
}
