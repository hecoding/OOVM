package mv.model.cpu;
/**
 * Clase que se encarga del pc, es decir, el contador de programa
 * @author Samuel Lapuente y Héctor Laria
 *
 */

public class ExecutionManager {
	private int currentPC;
	private int nextPC;
	private boolean halt;
	private boolean pause;
	
	public ExecutionManager() {
		this.currentPC = 0;
		this.nextPC = 1;
		this.halt = false;
		this.pause = false;
	}
	
	public int getPC() {
		return this.currentPC;
	}
	
	public int getNextPC() {
		return this.nextPC;
	}
	
	public void setNextPC (int n) {
		this.nextPC = n;
	}
	
	public void incrementPC() {
		this.currentPC = this.nextPC;
		this.nextPC++;
	}
	
	public void reset() {
		this.currentPC = 0;
		this.nextPC = 1;
		this.halt = false;
		this.pause = false;
	}
	
	public void setHalt (boolean b) {
		this.halt = b;
	}
	
	public boolean getHalt() {
		return this.halt;
	}
	
	public void setPause(boolean b) {
		this.pause = b;
	}

	public boolean getPause() {
		return this.pause;
	}
}
