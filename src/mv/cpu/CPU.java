package mv.cpu;

import java.util.EmptyStackException;

import mv.exceptions.cpuExceptions.MVError;
import mv.exceptions.cpuExceptions.NoInstructionsException;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;
/** 
 * Emula la CPU de la máquina virtual, conteniendo la memoria y la pila. 
 * Así como las implementaciones de las instrucciones del repertorio. 
 * @author Samuel Lapuente y Héctor Laria. 
 */
public class CPU {
	private Memory<Integer> memory;
	private OperandStack<Integer> stack;
	private ExecutionManager gestor;
	private ProgramMV program;
	private InStream inStream;
	private OutStream outStream;
	
	public CPU() {
		this.memory = new Memory<Integer>();
		this.stack = new OperandStack<Integer>();
		this.gestor = new ExecutionManager();
		this.program = null;
		this.inStream = null;
		this.outStream = null;
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
	public void step() throws NoInstructionsException {
		Instruction inst = null;
		
		try {
			// esta instrucción nunca va a dar ArrayIndexOutOfBoundsException porque se comprueba antes de salir de step (para saltos)
			inst = this.getCurrentInstr();
			
			inst.execute(this.memory, this.stack, this.gestor, this.inStream, this.outStream);
			
		}
		catch (EmptyStackException e) {
			throw new MVError (inst, "Faltan operandos en la pila");
		}
		catch (IllegalArgumentException e) { // div entre cero, load/store/jump negativo
			throw new MVError (inst, e.getMessage());
		}
		catch (NullPointerException e) { // cargar/guardar de memoria
			throw new MVError (inst, e.getMessage());
		} catch (NoInstructionsException e) {
			this.gestor.setHalt(true);
			throw e;
		}
		
		gestor.incrementPC();
		if (this.finDePrograma()) this.gestor.setHalt(true);
	}
	
	public void step(Instruction inst) throws NoInstructionsException {
		// ojo que no son los mismos catches
		try {
			inst.execute(this.memory, this.stack, this.gestor, this.inStream, this.outStream);
			
		}
		catch (EmptyStackException e) {
			throw new MVError (inst, "Faltan operandos en la pila");
		}
		catch (IllegalArgumentException e) { // div entre cero, load/store/jump negativo
			throw new MVError (inst, e.getMessage());
		}
		catch (NullPointerException e) { // cargar/guardar de memoria
			throw new MVError (inst, e.getMessage());
		}
		
	}
	
	public void run() throws NoInstructionsException {
		while (!this.isHALT()) {
			
			this.step();
		}
	}
	
	public void verboseRun() throws NoInstructionsException {
		while (!this.isHALT()) {

			System.out.println("Comienza la ejecución de "+ this.getCurrentInstr());

			this.step();

			System.out.println("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator()
					+ this.memory.toString() + System.lineSeparator() + this.stack.toString());
		}
	}
	
	public void push (int n) {
		this.stack.push(n);
	}
	
	public Integer pop() {
		return this.stack.pop();
	}
	
	private boolean finDePrograma() {
		
		return this.gestor.getPC() == this.getProgramSize();
	}
	
	private int getProgramSize() {
		
		return this.program.getSize();
	}
	
	public Instruction getCurrentInstr() throws NoInstructionsException {
		
		return this.program.getInstruction(this.gestor.getPC());
	}
	
	public boolean isHALT() {
		
		return this.gestor.getHalt() || this.gestor.getPC() < 0 || this.gestor.getPC() >= this.program.getSize();
	}
	
	public int getPC() {
		return this.gestor.getPC();
	}
	
	public ProgramMV getProgram() {
		
		return this.program;
	}
	
	public OperandStack<Integer> getOperandStack() {
		return this.stack;
	}
	
	public Memory<Integer> getMemory() {
		return this.memory;
	}
	
	public InStream getInStream() {
		return inStream;
	}
	
	public OutStream getOutStream() {
		return outStream;
	}
	
	public void memorySet(int pos, int dato) {
		this.memory.guardar(pos, dato);
	}
	
	public void setInStream (InStream s) {
		if (s == null) throw new MVError ("Cannot set inStream to null");
		else inStream = s;
	}
	
	public void setOutStream (OutStream s) {
		if (s == null) throw new MVError ("Cannot set inStream to null");
		else outStream = s;
	}
	
	public String toString() {
		Instruction currentInst;
		try {
			currentInst = this.getCurrentInstr();
		} catch (NoInstructionsException e) {
			currentInst = null;
		}
		
		return "Comienza la ejecución de " + currentInst + System.lineSeparator() + 
				"El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator() +
				this.memory.toString() + System.lineSeparator() + this.stack.toString();
	}
}