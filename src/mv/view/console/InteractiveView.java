package mv.view.console;

import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.Observable;

public class InteractiveView implements CPUObserver {
	public InteractiveView(Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
	}
	
	public void onStartInstrExecution(Instruction instr) {
		System.out.println("Comienza la ejecución de "+ instr);
	}

	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
		System.out.println("El estado de la máquina tras ejecutar la instrucción es:" + System.lineSeparator()
				+ memory + System.lineSeparator() + stack);
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
