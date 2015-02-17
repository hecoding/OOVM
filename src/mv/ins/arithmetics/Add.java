package mv.ins.arithmetics;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.ins.Instruction;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class Add extends Arithmetics {
	
	public Add() {
		super();
	}

	public void execute (Memory<Integer> mem, OperandStack<Integer> pila, ExecutionManager gestor, InStream in, OutStream out) {
		int cima = pila.pop();
		int subcima = pila.pop();

		pila.push(subcima + cima);
	}
	
	protected Instruction crear() {
		return new Add();
	}
	
	public String toString() {
		return "ADD";
	}
}
