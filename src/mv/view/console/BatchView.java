package mv.view.console;

import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.Observable;

public class BatchView implements CPUObserver {
	
	public BatchView(Observable<CPUObserver> cpu) {
			cpu.addObserver(this);
	}

	public void onStartInstrExecution(Instruction instr) {
		
	}
	
	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
		
	}

	public void onStartRun() {
		
	}

	public void onEndRun() {
		
	}

	public void onError(String msg) {
		System.err.println(msg);
	}

	public void onHalt() {
		
	}

	public void onReset(ProgramMV program) {
		
	}

	public void onPause() {
		
	}
}