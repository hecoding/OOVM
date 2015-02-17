package cpu;

import ins.Instruction;
/** 
 * Emula la cpu de la máquina virtual, conteniendo la memoria y la pila. 
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
	 * @return bool
	 */
	public boolean step() {
		Instruction inst = null;
		boolean instruccionEjecutada = false;
		
		// No estamos de acuerdo con lo dicho en clase, no creemos que la cpu deba imprimir nada,
		// aunque para ello la implementación del main sea más complicada.
		
		inst = program.getInstruction (this.gestor.getPC());
		if (inst == null) {
			this.gestor.setHalt(true);
			
			return false;
		}
		else {
			System.out.println("Comienza la ejecución de " + this.getCurrentInstr());
			instruccionEjecutada = inst.execute(this.memory, this.stack, this.gestor);
					
			if (!instruccionEjecutada) {
				System.out.println("Error en la ejecución de la instrucción");
				
				return false;
			}
			else {
				if (!this.getHALT() && this.instruccionDesconocida()) {
					System.out.println("Error: instrucción errónea");
					this.gestor.setHalt(true);
					
					return false;
				}
				else {
					System.out.println("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator() + this.toString());
					gestor.incrementPC();
					if(this.gestor.getPC() == this.getProgramSize()) this.gestor.setHalt(true);
					
					return true;
				}
			}
		}
	}
	
	private boolean instruccionDesconocida() {
		return ((this.gestor.getPC() >= this.program.getSize()) || (this.gestor.getPC() < 0));
	}
	
	public Memory getMemory() {
		return this.memory;
	}
	
	public OperandStack getStack() {
		return this.stack;
	}
	
	public String getProgram() {
		return this.program.toString();
	}
	
	public int getProgramSize() {
		return this.program.getSize();
	}
	
	public Instruction getCurrentInstr() {
		return this.program.getInstruction(this.gestor.getPC());
	}
	
	public boolean getHALT () {
		
		return this.gestor.getHalt();
	}
	
	public String toString() {
		
		return this.memory.toString() + System.lineSeparator() + this.stack.toString();
	}
}