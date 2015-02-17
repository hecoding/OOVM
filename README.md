# OOVM
This is a virtual machine made during our second course (2013 - 2014) with the purpose of learn Object Oriented Programming.
It has three modes which can switched invoking with the `-m` parameter; interactive (CLI), batch and windows (GUI).
The default mode is interactive.   
Comments, method names and some variables are in spanish because it was the language in which it was teached.

This program emulates a regular machine having a stack and a memory, gets "assembler" code as the program to run, input data, and gives (or only shows) output data.   
In windowed and interactive mode it also has controls to start, pause, stop and restart the execution.

## Usage
`mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]`
* `-h`, `--help` show help.
* `-a` File with ASM code to execute. Required in batch mode.
* `-i` Input of the machine for the program.
* `-m` Operating mode (batch | interactive | window ). Interactive by default.
* `-o` File where the ouput is saved.

## Instruction set
| Arithmetics | Bool | Branch | Comparation | Sequential |
|:-----------:|:----:|:------:|:-----------:|:----------:|
|ADD          |AND   |BF      |EQ           |DUP|
|DIV          |OR    |BT      |GT           |FLIP|
|MUL          |NOT   |JUMP    |LE           |HALT|
|SUB          |      |JUMP [IND]|LT         |IN|
|             |      |RBF     |             |LOAD|
|             |      |RBT     |             |LOAD [IND]|
|             |      |RJUMP   |             |OUT|
|             |      |        |             |POP|
|             |      |        |             |PUSH|
|             |      |        |             |STORE|
|             |      |        |             |STORE [IND]|

## Design patterns
We are also at least using the following patterns:
* MVC
* Observer
* Command

## Screenshots
Use `-m window -a progr.txt -i in.txt -o out.txt` to run the example program.

<img src="/GUIsample.png" align="left" height="531px" width="600px" >
<img src="/GUIrunningsample.png" align="left" height="531px" width="600px" >
