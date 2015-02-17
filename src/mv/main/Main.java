package mv.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.cli.ParseException;

import mv.Interface;
import mv.command.CommandInterpreter;
import mv.command.CommandParser;
import mv.cpu.CPU;
import mv.cpu.ProgramMV;
import mv.exceptions.CPUParserException;
import mv.exceptions.cpuExceptions.HardwareException;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.ins.InstructionParser;
import mv.mvSystem.MVSystem;

/** 
 * @author Samuel Lapuente y Héctor Laria. 2º A 
 */
public class Main {
	
	 /** 
     * Se encarga de la ejecución y la entrada/salida. 
     * También muestra los errores que se puedan dar. 
     * @param args Argumentos de entrada. 
     */
	public static void main(String[] args) {
		CPU cpu = new CPU();
		ProgramMV programa = new ProgramMV();
		String cadena = null, direccPrograma = null;
		Scanner sc = new Scanner(System.in);
		
		Interface interf = new Interface();
		try {
			direccPrograma = interf.parse(args);
			
			if (interf.enteredProgramFile()) {
				Scanner archivoPrograma = new Scanner (new FileReader (direccPrograma));
				
				while (archivoPrograma.hasNext()) {
					cadena = archivoPrograma.nextLine();
					
					programa.addInstruction(InstructionParser.parse(cadena));
				}
				archivoPrograma.close();
			}
			else if (interf.helpAsked())
				interf.printHelp();
			
			else {
				System.out.println("Introduzca el programa fuente: ");
				
				do {
					cadena = sc.nextLine();
					
					if (!cadena.equalsIgnoreCase("END")) {
						try {
							programa.addInstruction(InstructionParser.parse(cadena));
							
						} catch (CPUParserException e) {
							System.err.println("Error: " + e.getMessage());
						}
					}
				} while (!cadena.equalsIgnoreCase("END"));
			}
			
			cpu.loadProgram (programa);
			
			CommandInterpreter.configureCommandInterpreter (cpu, interf.isInteractiveMode());
			CommandInterpreter com;
			MVSystem.in.open(); // podríamos haber puesto también los open privados, en el constructor
			MVSystem.out.open();
			
			if (interf.isInteractiveMode())
				System.out.println(cpu.getProgram() + System.lineSeparator());
			
			while (!cpu.getHALT() && !CommandInterpreter.isQuit()) {
				try {
					if (interf.isInteractiveMode()) {
						System.out.print("> ");
						com = CommandParser.parse (sc.nextLine());
					}
					else
						com = CommandParser.parse ("run");
					
					com.executeCommand();
				}
				catch (CPUParserException e) {
					System.err.println("Error: Comando incorrecto");
				}
				catch (HardwareException e) {
					System.err.println(e.getMessage());
					
					if (!interf.isInteractiveMode())
						throw e;
				}
			}
			
		} catch (ParseException | FileNotFoundException e) {
			System.err.println ("Uso incorrecto: " + e.getMessage()  + System.lineSeparator() +
					"Use -h|--help para más detalles.");
			System.exit(1);
		} catch (CPUParserException e) {
			System.err.println("Error: " + e.getMessage() + ". Línea: " + cadena);
			
		} catch (NoInstructionsException e) {
			System.err.println("No hay instrucciones. Por favor inténtelo de nuevo.");
			
		}catch (HardwareException e) {
			
		}catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			
		} finally {
			sc.close();
			try {
				MVSystem.out.close();
				MVSystem.in.close();
			} catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			} catch (Exception e) {}
			
			System.exit(2);
		}
	}
}