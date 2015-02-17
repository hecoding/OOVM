package mv.cpu;
import java.util.Vector;

import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.ins.Instruction;
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
	
	public int getSize() {
		return this.instruccion.size();
	}
	
	public Instruction getInstruction (int i) throws NoInstructionsException {
		
		if (this.instruccion.size() == 0) throw new NoInstructionsException(); // irrecuperable, por tanto checked exception
		
		return this.instruccion.get(i);
	}
	
	public String toString() {
		String cadena = "El programa instroducido es:" + System.lineSeparator();
		
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
