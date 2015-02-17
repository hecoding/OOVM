package mv.controler;

import java.io.IOException;

import mv.model.cpu.CPU;
import mv.model.exceptions.cpuExceptions.MVError;

public class BatchControler extends Controler {

	public BatchControler(CPU cpu) {
		super(cpu);
	}

	public void start() {
		try {
			run();
		} catch (MVError e) {}
		
		try {
			cpu.getInStream().close();
			cpu.getOutStream().close();
		} catch (IOException e) {}
	}

}
