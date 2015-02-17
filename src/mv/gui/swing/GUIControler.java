package mv.gui.swing;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mv.cpu.CPU;
import mv.cpu.Memory;
import mv.cpu.OperandStack;
import mv.cpu.ProgramMV;
import mv.mvSystem.in.InStream;
import mv.mvSystem.out.OutStream;

public class GUIControler {
	private CPU cpu;
	private MainWindow gui;
	
	GUIControler(CPU cpu, MainWindow gui) {
		this.cpu = cpu;
		this.gui = gui;
	}
	
	private void reportError(String msg, String title) {
		JOptionPane.showMessageDialog(new JFrame(), msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	void step() {
		try {
			cpu.step();
		} catch (Exception e) {
			if (cpu.isHALT())
				this.reportError("Program finished", "CPU");
			else
				this.reportError(e.getMessage(), "Step Error");
		}
		gui.updateView();
	}
	
	void run() {
		try {
			if (cpu.isHALT())
				this.reportError("Program finished", "CPU");
			else{
				cpu.run();
				gui.updateView();
			}
			
		} catch (Exception e) {
			
			this.reportError(e.getMessage(), "Run Error");
		}
	}
	
	void push(String s) {
		try {
			cpu.push(new Integer(s));
		} catch (Exception e) {
			this.reportError("The imput must be a number", "Push Error");
		}
		gui.updateView();
	}
	void pop() {
		try {
			cpu.pop();
		} catch (Exception e) {
			this.reportError("Empty Stack", "Pop Error");
		}
		gui.updateView();
	}
	void quit() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			try {
				cpu.getInStream().close();
				cpu.getOutStream().close();
			} catch (IOException e) {
				this.reportError(e.getMessage(), "Stream Error");
			}
			System.exit(0);
		}
	}
	void memorySet(String n1, String n2) {
		try {
			int pos = new Integer(n1);
			int val = new Integer(n2);
			
			if (pos < 0) this.reportError("Position must be a positive number", "Input Error");
			else {
				cpu.memorySet(pos, val);
			}
		} catch (NumberFormatException e) {
			this.reportError("The imput must be a number", "Input Error");
		}
	}
	int getPC() {return cpu.getPC(); }
	void setInStream(InStream in) { cpu.setInStream(in); }
	void setOutStream(OutStream out) { cpu.setOutStream(out); }
	InStream getInStream() { return cpu.getInStream(); }
	OutStream getOutStream() { return cpu.getOutStream(); }
	ProgramMV getProgram() {return cpu.getProgram(); }
	OperandStack<Integer> getOperandStack() {return cpu.getOperandStack();}
	Memory<Integer> getMemory() { return cpu.getMemory(); }
}
