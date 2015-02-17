package cpu;
import ins.Instruction;
/**
 * Clase que simula un programa, introduciendo las instrucciones en un array.
 * @author Samuel Lapuente y Héctor Laria
 *
 */
public class ProgramMV {
	private Instruction[] instruccion;
	private int capacity;
	private int size;
	
	public ProgramMV() {
		this.capacity = 30;
		this.instruccion = new Instruction[this.capacity];
		this.size = 0;
	}
	
	public void addInstruction (Instruction ins) {
		if (this.size >= this.capacity)
			this.resize();
		
		this.instruccion[this.size] = ins;
		this.size++;
	}
	
	private void resize() {
		int nuevaCapacity = (3 * this.capacity / 2) + 1;
		Instruction[] nuevoProgram = new Instruction[nuevaCapacity];
		
		for(int i=0; i < this.capacity; i++) { // Implementar .clone no merece para un array.
			nuevoProgram[i] = this.instruccion[i];
		}
		
		this.capacity = nuevaCapacity;
		this.instruccion = nuevoProgram;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Instruction getInstruction (int i) {
		if ((i >= this.size) || (i < 0)) return null;
		else return this.instruccion[i];
	}
	
	public String toString() {
		String cadena = "El programa instroducido es:" + System.lineSeparator();
		
		if (this.size == 0) cadena += "<vacío>";
		else {
			for (int i = 0; i < this.size; i++) {
				cadena += i + ": " + this.instruccion[i];
				if (i < this.size - 1) cadena += System.lineSeparator();
			}
		}
		
		return cadena;
	}
}
