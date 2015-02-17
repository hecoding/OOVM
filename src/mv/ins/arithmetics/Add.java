package mv.ins.arithmetics;

import mv.cpu.ExecutionManager;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.exceptions.insException.NotEnoughArgsException;
import mv.ins.Instruction;

public class Add extends Arithmetics {
	
	public Add() {
		super();
	}

	public void execute(Memory mem, OperandStack pila, ExecutionManager gestor) {
		if (pila.getSize() < 2) throw new NotEnoughArgsException();
		
		int cima = pila.desapilar();
		int subcima = pila.desapilar();

		pila.apilar(subcima + cima);
	}
	
	protected Instruction crear() {
		return new Add();
	}
	
	public String toString() {
		return "ADD";
	}
}
