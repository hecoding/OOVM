package mv.model.ins;

import mv.model.cpu.ExecutionManager;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

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