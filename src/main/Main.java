package main;

import java.util.Scanner;

import command.CommandInterpreter;
import command.CommandParser;
import cpu.CPU;
import cpu.ProgramMV;
import ins.Instruction;
import ins.InstructionParser;

/** 
 * @author Samuel Lapuente y H�ctor Laria. 2� A 
 */
public class Main {

	 /** 
     * Se encarga de la ejecuci�n y la salida por consola. 
     * Se encarga tambi�n de interpretar los errores del usuario. 
     * @param args Argumentos de entrada. 
     */
	public static void main(String[] args) {
		CPU cpu = new CPU();
		ProgramMV programa = new ProgramMV();
		Scanner sc = new Scanner(System.in);
		Instruction instruction;
		String cadena;
		
		System.out.println("Introduzca el programa fuente: ");
		do {
			cadena = sc.nextLine();
			
			if (!cadena.equalsIgnoreCase("END")) {
				instruction = InstructionParser.parse(cadena);
				
				if (instruction == null)
					System.out.println("Error: Instrucci�n incorrecta");
				else
					programa.addInstruction(instruction);
			}
		} while (!cadena.equalsIgnoreCase("END"));
		
		cpu.loadProgram (programa);
		System.out.println(cpu.getProgram() + System.lineSeparator());
		
		CommandInterpreter.configureCommandInterpreter(cpu);
		
		while (!cpu.getHALT() && !CommandInterpreter.isQuit()) {
			System.out.print("> ");
			cadena = sc.nextLine();
			CommandInterpreter com = CommandParser.parse(cadena);
			
			if (com == null)
				System.out.println("Error: Comando incorrecto");
			else {
				if(!com.executeCommand())
					System.out.println("Error en la ejecuci�n del comando " + com);
			}
		}
		
		sc.close();
	}
}