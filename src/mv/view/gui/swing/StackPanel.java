package mv.view.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import mv.controler.Controler;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.Observable;
import mv.model.observer.StackObserver;


@SuppressWarnings("serial")
class StackPanel extends JPanel implements StackObserver<Integer>, CPUObserver {
	private Controler ctrl;
	private JList<Integer> stackArea;
	private JTextField stackElem;
	JButton pushButton;
	JButton popButton;
	private DefaultListModel<Integer> model;
	
	StackPanel(Controler ctrl, Observable<StackObserver<Integer>> stack, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		stack.addObserver(this);
		cpu.addObserver(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Operand Stack"));
		model = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(model);
		JLabel label = new JLabel("Value");
		label.setName("value");

		stackElem = new JTextField(3);
		
		JPanel toolBar = new JPanel();
		toolBar.add(label);
		toolBar.add(stackElem);
		
		pushButton = new JButton("Push");
		toolBar.add(pushButton);
		pushButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.push(new Integer(stackElem.getText()));
				} catch (Exception ex) {
					MainWindow.reportError("The input must be a number", "Stack Error");
				}
			}
		});
		
		popButton = new JButton("Pop");
		toolBar.add(popButton);
		popButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.pop();
				} catch (Exception ex) {
					MainWindow.reportError("Empty stack", "Stack Error");
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		panel.add(new JScrollPane(stackArea), BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.PAGE_END);
		
		this.setLayout(new BorderLayout(0, 0));
		this.setMinimumSize(new Dimension(300, 300));
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void onStartInstrExecution(Instruction instr) {
	}

	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
	}

	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				pushButton.setEnabled(false);
				popButton.setEnabled(false);
			}
		});
	}

	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
			}
		});
	}

	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
			}
		});
	}

	public void onError(String msg) {
	}

	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				pushButton.setEnabled(false);
				popButton.setEnabled(false);
			}
		});
	}

	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
			}
		});
	}

	public void onPush(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				model.addElement(value);
			}
		});
	}

	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				model.remove(model.getSize() - 1);
			}
		});
	}

	public void onStackReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				model.removeAllElements();
			}
		});
	}
}
