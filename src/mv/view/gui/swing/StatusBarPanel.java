package mv.view.gui.swing;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.MemoryObserver;
import mv.model.observer.Observable;
import mv.model.observer.StackObserver;

@SuppressWarnings("serial")
public class StatusBarPanel extends JPanel implements StackObserver<Integer>, MemoryObserver<Integer>, CPUObserver {
	Integer numIns;
	JLabel numInsLabel;
	JLabel maqParada;
	JCheckBox modifiedMemory;
	JCheckBox modifiedStack;
	
	StatusBarPanel (Observable<CPUObserver> cpu, Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory) {
		this.numIns = 0;
		
		cpu.addObserver(this);
		stack.addObserver(this);
		memory.addObserver(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		maqParada = new JLabel("Máquina parada");
		maqParada.setForeground(Color.RED);
		this.add(maqParada);
		
		JLabel numExecutedInstLabel = new JLabel("Num. Instrucciones ejecutadas: ");
		this.add(numExecutedInstLabel);
		numInsLabel = new JLabel(this.numIns.toString());
		this.add(numInsLabel);
		
		this.modifiedMemory = new JCheckBox();
		this.add(modifiedMemory);
		
		JLabel memMod = new JLabel("Memoria modificada");
		this.add(memMod);
		
		this.modifiedStack = new JCheckBox();
		this.add(this.modifiedStack);
		
		JLabel pilaMod = new JLabel("Pila modificada");
		this.add(pilaMod);
	}

	public void onStartInstrExecution(Instruction instr) {
		
	}

	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				numIns++;
				numInsLabel.setText(numIns.toString());
			}
		});
	}

	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				maqParada.setVisible(false);
			}
		});
	}

	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				maqParada.setVisible(true);
			}
		});
	}

	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				maqParada.setVisible(true);
			}
		});
	}

	public void onError(String msg) {
		
	}

	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				maqParada.setVisible(true);
			}
		});
	}

	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				maqParada.setVisible(true);
				numIns = 0;
				numInsLabel.setText(numIns.toString());
				modifiedMemory.setSelected(false);
				modifiedStack.setSelected(false);
			}
		});
	}

	public void onWrite(int index, Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				modifiedMemory.setSelected(true);
			}
		});
	}

	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				modifiedMemory.setSelected(false);
			}
		});
	}

	public void onPush(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				modifiedStack.setSelected(true);
			}
		});
	}

	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				modifiedStack.setSelected(true);
			}
		});
	}

	public void onStackReset() {
		
	}

}
