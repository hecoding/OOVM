package mv.cpu;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import mv.exceptions.CPUParserException;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.ins.Instruction;
import mv.ins.InstructionParser;
/**
 * Clase que simula un programa, introduciendo las instrucciones en un vector.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class ProgramMV {
	private Vector<Instruction> instruccion;
	
	public ProgramMV() {
		this.instruccion = new Vector<Instruction>();
	}
	
	public void addInstruction (Instruction ins) {
		if (ins != null)
			this.instruccion.add(ins);
	}
	
	public static ProgramMV readProgram (Scanner sc) {
		String cadena = null;
		ProgramMV programa = new ProgramMV();
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
		
		return programa;
	}
	
	public static ProgramMV readProgram (String direccPrograma) throws FileNotFoundException, CPUParserException {
		String cadena = null;
		ProgramMV programa = new ProgramMV();
		Scanner archivoPrograma = new Scanner (new FileReader (direccPrograma));
		
		try {
		while (archivoPrograma.hasNext()) {
			cadena = archivoPrograma.nextLine();
			
			programa.addInstruction(InstructionParser.parse(cadena));
		}
		} catch (CPUParserException e) {
			throw new CPUParserException ("Error: " + e.getMessage() + ". Línea: " + cadena);
		} finally {
			archivoPrograma.close();
		}
		
		return programa;
	}
	
	public int getSize() {
		return this.instruccion.size();
	}
	
	public Instruction getInstruction (int i) throws NoInstructionsException {
		
		if (this.instruccion.size() == 0) throw new NoInstructionsException(); // irrecuperable, por tanto checked exception
		
		return this.instruccion.get(i);
	}
	
	public String toString() {
		String cadena = "";
		
		if (this.instruccion.isEmpty()) return cadena + "<vacío>";
		else {
			for (int i = 0; i < this.instruccion.size(); i++) {
				cadena += i + ": " + this.instruccion.get(i);
				if (i < this.instruccion.size() - 1) cadena += System.lineSeparator();
			}
			
			return cadena;
		}
	}
}
