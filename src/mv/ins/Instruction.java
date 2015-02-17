package mv.ins;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;

public interface Instruction {
	
	/**
	 * Clase que ejecuta una instrucción.
	 * @param mem Memoria
	 * @param pila Pila
	 * @param gestor Pc
	 * @return
	 */
	public abstract void execute (Memory mem, OperandStack pila, ExecutionManager gestor);
	public abstract Instruction parseOp (String s);
	
}