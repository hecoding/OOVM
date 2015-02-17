package mv.cpu;

import mv.exceptions.cpuExceptions.HardwareException;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.exceptions.insException.NoArgsException;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;
/** 
 * Emula la CPU de la máquina virtual, conteniendo la memoria y la pila. 
 * Así como las implementaciones de las instrucciones del repertorio. 
 * @author Samuel Lapuente y Héctor Laria. 
 */
public class CPU {
	private Memory memory;
	private OperandStack stack;
	private ExecutionManager gestor;
	private ProgramMV program;
	
	public CPU() {
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.gestor = new ExecutionManager();
		this.program = null;
	}
	/**
	 * Carga el programa
	 * @param program Programa introducido por el usuario
	 */
	public void loadProgram (ProgramMV program) {
		this.program = program;
	}
	
	/**
	 * Método que se encarga de ejecutar una instrucción, tratando los errores y finalizando la ejecución
	 * en caso de error fatal.
	 * @return True si la ejecución ha tenido éxito, false en caso contrario.
	 * @throws NoInstructionsException si no hay ningún programa que ejecutar.
	 */
	public boolean step() throws NoInstructionsException {
		Instruction inst = null;
		
		try {
			// esta instrucción nunca va a dar ArrayIndexOutOfBoundsException porque se comprueba antes de salir de step (para saltos)
			inst = this.getCurrentInstr();
			
			inst.execute(this.memory, this.stack, this.gestor);
			
		}
		catch (NotEnoughArgsException e) {
			throw new HardwareException (inst, e.getMessage());
		}
		catch (NoArgsException e) {
			throw new HardwareException (inst, e.getMessage());
		}
		catch (IllegalArgumentException e) { // div entre cero, load/store/jump negativo
			throw new HardwareException (inst, e.getMessage());
		}
		catch (NullPointerException e) { // cargar/guardar de memoria
			throw new HardwareException (inst, e.getMessage());
		} catch (NoInstructionsException e) {
			this.gestor.setHalt(true);
			throw e;
		}
		
		
		if (this.siguientePCIlegal()) throw new HardwareException(inst, "Posición ilegal");
		else gestor.incrementPC();
		
		if (this.finDePrograma()) this.gestor.setHalt(true);
		
		
		return true;
	}
	
	public boolean step(Instruction inst) throws NoInstructionsException {
		// ojo que no son los mismos catches
		try {
			inst.execute(this.memory, this.stack, this.gestor);
			
		}
		catch (NotEnoughArgsException e) {
			throw new HardwareException (inst, e.getMessage());
		}
		catch (NoArgsException e) {
			throw new HardwareException (inst, e.getMessage());
		}
		catch (IllegalArgumentException e) { // div entre cero, load/store/jump negativo
			throw new HardwareException (inst, e.getMessage());
		}
		catch (NullPointerException e) { // cargar/guardar de memoria
			throw new HardwareException (inst, e.getMessage());
		}
		
		return true;
	}
	
	private boolean finDePrograma() {
		
		return this.gestor.getPC() == this.getProgramSize();
	}
	
	private boolean siguientePCIlegal() {
		
		return this.gestor.getNextPC() > this.program.getSize();
	}
	
	private int getProgramSize() {
		
		return this.program.getSize();
	}
	
	public Instruction getCurrentInstr() throws NoInstructionsException {
		
		return this.program.getInstruction(this.gestor.getPC());
	}
	
	public String getProgram() {
		
		return this.program.toString();
	}
	
	public boolean getHALT () {
		
		return this.gestor.getHalt();
	}
	
	public String toString() {
		
		return this.memory.toString() + System.lineSeparator() + this.stack.toString();
	}
}