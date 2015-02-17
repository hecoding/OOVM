package ins;

import cpu.ExecutionManager;
import cpu.Memory;
import cpu.OperandStack;

public interface Instruction {
	
	/**
	 * Clase que ejecuta una instrucción.
	 * @param mem Memoria
	 * @param pila Pila
	 * @param gestor Pc
	 * @return
	 */
	public abstract boolean execute (Memory mem, OperandStack pila, ExecutionManager gestor);
	public abstract Instruction parseOp (String s);
	
}