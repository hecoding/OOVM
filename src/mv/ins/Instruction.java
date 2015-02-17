package mv.ins;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public interface Instruction {
	
	/**
	 * Clase que ejecuta una instrucción.
	 * @param mem Memoria
	 * @param pila Pila
	 * @param gestor Pc
	 * @return
	 */
	public abstract void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out);
	public abstract Instruction parseOp (String s);
	
}