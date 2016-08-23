# SASM - Scala Assembler
This project aims to be an assembler implemented entirely in scala. This includes not only the assembler
but also the assembly code itself.

## Planned Features
*This project is clearly in very, very early stages at the moment.*

- ASM implementations
    - x86_64
    - PWISA
- Executable format support
    - ELF executable
    - ELF object

## License
Notes: Due to the fact that this project is GPL and the unusual way in
which you write the code to be assembled (which requires linking against
this library), all code compiled by SASM must also be GPL. Eventually
I will add the appropriate allowances so that this is not the case, but
at the moment I'm not particularly motivated to figure it out. If you
(for some reason) wanted to use this with not-GPL source, let me know,
and I can try to figure it out.

```
SASM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SASM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SASM.  If not, see <http://www.gnu.org/licenses/>.
```