package mv.controler;

import java.io.FileNotFoundException;
import java.io.IOException;

import mv.model.cpu.CPU;
import mv.model.cpu.ProgramMV;
import mv.model.exceptions.CPUParserException;
import mv.model.mvsystem.in.InStream;
import mv.model.mvsystem.out.OutStream;

public abstract class Controler {
	CPU cpu ;
	
	public Controler(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void step() {
		cpu.step();
	}
	
	public void stepn(int n) {
		this.cpu.stepn(n);
	}
	
	public void	pop() {
		this.cpu.pop();
	}
	
	public void	push(int v) {
		this.cpu.push(v);
	}
	
	public void memorySet(int i, int v) {
		this.cpu.memorySet(i, v);
	}
	
	public void loadProgram(String source) {
		try {
			this.cpu.loadProgram(ProgramMV.readProgram(source));
		} catch (FileNotFoundException e) {}
		catch (CPUParserException e) {
			this.cpu.loadProgram(null);
		}
	}
	
	public ProgramMV getProgram() {
		return this.cpu.getProgram();
	}
	
	public void setInStream(InStream in) {
		this.cpu.setInStream(in);
	}
	
	public InStream getInStream() {
		return this.cpu.getInStream();
	}
	
	public void setOutStream(OutStream out) {
		this.cpu.setOutStream(out);
	}
	
	public OutStream getOutStream() {
		return this.cpu.getOutStream();
	}

	public void	run() {
		this.cpu.run();
	}
	
	public void pause() {
		this.cpu.pause();
	}
	
	public void resume() {
		this.cpu.setPause(false);
	}
	
	public void reset() throws IOException {
		this.cpu.reset();
	}
	
	public abstract void start(); // un método abstracto, depende del modo
	
	public void quit() {
		try {
			this.cpu.getOutStream().close();
			this.cpu.getInStream().close();
		} catch (IOException e) { }
		
		System.exit(0);
	}
	
	public boolean isHALT() {
		return this.cpu.isHALT();
	}

	public int getPC() {
		return this.cpu.getPC();
	}

	public boolean isPaused() {
		return this.cpu.isPaused();
	}
	
	public void setDelay(int n) {
		this.cpu.setDelay(n);
	}
}
