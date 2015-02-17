package mv.model.observer;

import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;

public interface CPUObserver {
	public void onStartInstrExecution(Instruction instr);
	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory);
	public void onStartRun();
	public void onEndRun(); // Se puede pasar m�s par�metros (en la parte opcional) para indicar si
	// la ejecuci�n ha terminado o la hemos parad
	public void onPause();
	public void onError(String msg);
	public void onHalt();
	public void onReset(ProgramMV program);
}
