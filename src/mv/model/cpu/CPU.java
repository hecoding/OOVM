package mv.model.cpu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import mv.model.exceptions.cpuExceptions.MVError;
import mv.model.exceptions.cpuExceptions.NoInstructionsException;
import mv.model.ins.Instruction;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;
import mv.model.observer.CPUObserver;
import mv.model.observer.Observable;
/** 
 * Emula la CPU de la máquina virtual, conteniendo la memoria y la pila. 
 * Así como las implementaciones de las instrucciones del repertorio. 
 * @author Samuel Lapuente y Héctor Laria. 
 */
public class CPU implements Observable<CPUObserver> {
	private Memory<Integer> memory;
	private OperandStack<Integer> stack;
	private ExecutionManager gestor;
	private ProgramMV program;
	private InStream inStream;
	private OutStream outStream;
	private ArrayList<CPUObserver> observers;
	private int delay;
	private static boolean error;
	
	public CPU() {
		this.memory = new Memory<Integer>();
		this.stack = new OperandStack<Integer>();
		this.gestor = new ExecutionManager();
		this.program = null;
		this.inStream = null;
		this.outStream = null;
		this.delay = 0;
		error = false;
		
		this.observers = new ArrayList<CPUObserver>();
	}
	
	/**
	 * Método que se encarga de ejecutar una instrucción, tratando los errores y finalizando la ejecución
	 * en caso de error fatal.
	 * @return True si la ejecución ha tenido éxito, false en caso contrario.
	 * @throws NoInstructionsException si no hay ningún programa que ejecutar.
	 */
	public void step() {
		Instruction inst = null;
		error = false;
		
		try {
			// esta instrucción nunca va a dar ArrayIndexOutOfBoundsException porque se comprueba antes de salir de step (para saltos)
			inst = this.getCurrentInstr();
			
			this.notifyStartInstructionExecution(inst);
			
			inst.execute(this.memory, this.stack, this.gestor, this.inStream, this.outStream);
			
		}
		catch (EmptyStackException | NoSuchElementException e) {
			error = true;
			this.notifyError("Faltan operandos en la pila");
		}
		catch (IllegalArgumentException e) { // div entre cero, load/store/jump negativo
			error = true;
			this.notifyError(e.getMessage());
		}
		catch (NullPointerException e) {
			error = true;
			this.notifyError(e.getMessage());
		} catch (NoInstructionsException e) {
			error = true;
			this.notifyError(e.getMessage());
			this.gestor.setHalt(true);
		}
		
		if (!error)
			gestor.incrementPC();
		this.notifyEndInstructionExecution();
		
		if (this.finDePrograma() && !error) {
			this.gestor.setHalt(true);
			this.notifyHalt();
		}
	}
	
	public void stepn(int n) {
		int i = 0;
		
		while ((i < n) && !error && !this.isHALT()) {
			this.step();
			
			i++;
		}
	}
	
	public void step(Instruction inst) {
		error = false;
		
		this.notifyStartInstructionExecution(inst);
		// ojo que no son los mismos catches
		try {
			inst.execute(this.memory, this.stack, this.gestor, this.inStream, this.outStream);
			
		}
		catch (EmptyStackException | NoSuchElementException e) {
			error = true;
			this.notifyError("Faltan operandos en la pila");
		}
		catch (IllegalArgumentException e) { // div entre cero, load/store/jump negativo
			error = true;
			this.notifyError(e.getMessage());
		}
		catch (NullPointerException e) { // cargar/guardar de memoria
			error = true;
			this.notifyError(e.getMessage());
		}
		
		this.notifyEndInstructionExecution();
	}
	
	public void run() {
		this.notifyStartRun();
		
		this.setPause(false);
		
		while (!this.isHALT() && !this.isPaused() && !error) {
			this.step();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				this.notifyError(e.getMessage());
			}
		}
		
		this.notifyEndRun();
		if (!error && !this.isPaused())
			this.notifyHalt();
	}
	
	public void verboseRun() throws NoInstructionsException {
		while (!this.isHALT() && !error) {

			System.out.println("Comienza la ejecución de "+ this.getCurrentInstr());
			
			this.step();

			System.out.println("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator()
					+ this.memory.toString() + System.lineSeparator() + this.stack.toString());
		}
		
		this.notifyEndRun();
		if (!error && !this.isPaused())
			this.notifyHalt();
	}
	
	public void pause() {
		this.gestor.setPause(true);
		
		this.notifyPause();
	}

	public void loadProgram(ProgramMV p) {
		if (p == null) this.notifyError("Invalid program");
		else {
			this.program = p;
			this.stack.reset();
			this.memory.reset();
			this.gestor.reset();
			error = false;
			this.notifyReset(p);
		}
	}
	
	public void reset() throws IOException {
		this.loadProgram(this.program);
		inStream.reset();
		outStream.reset();
	}

	public void push (int n) {
		error = false;
		this.stack.push(n);
	}
	
	public Integer pop() {
		Integer num;
		error = false;
		
		try {
			 num = this.stack.pop();
		} catch (EmptyStackException e) {
			this.notifyError(e.getMessage());
			throw e;
		}
		
		return num;
	}
	
	private boolean finDePrograma() {
		
		return this.gestor.getPC() == this.getProgramSize();
	}
	
	private int getProgramSize() {
		
		return this.program.getSize();
	}
	
	private void notifyStartInstructionExecution(Instruction inst) {
		for (CPUObserver obs : this.observers)
			obs.onStartInstrExecution(inst);
	}
	
	private void notifyEndInstructionExecution() {
		for (CPUObserver obs : this.observers)
			obs.onEndInstrExecution(this.getPC(), this.getOperandStack(), this.getMemory());
	}
	
	private void notifyStartRun() {
		for (CPUObserver obs : this.observers)
			obs.onStartRun();
	}
	
	private void notifyEndRun() {
		for (CPUObserver obs : this.observers)
			obs.onEndRun();
	}
	
	private void notifyPause() {
		for (CPUObserver obs : this.observers)
			obs.onPause();
	}
	
	private void notifyReset(ProgramMV p) {
		for (CPUObserver obs : this.observers)
			obs.onReset(p);
	}
	
	private void notifyHalt() {
		for (CPUObserver obs : this.observers)
			obs.onHalt();
	}
	
	private void notifyError(String s) {
		for (CPUObserver obs : this.observers)
			obs.onError(s);
	}
	
	public Instruction getCurrentInstr() throws NoInstructionsException {
		
		return this.program.getInstruction(this.gestor.getPC());
	}
	
	public boolean isHALT() {
		
		return this.gestor.getHalt() || this.gestor.getPC() < 0 || this.gestor.getPC() >= this.program.getSize();
	}
	
	public boolean isPaused() {
		return this.gestor.getPause();
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
		error = false;
		this.memory.guardar(pos, dato);
	}
	
	public void setPause (boolean b) {
		this.notifyStartRun();
		
		this.gestor.setPause(b);
	}
	
	public void setDelay (int n) {
		if (n >= 0)
			this.delay = n;
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
	
	public void addObserver(CPUObserver o) {
		observers.add(o);
	}
	
	public void removeObserver(CPUObserver o) {
		observers.remove(o);
	}
}