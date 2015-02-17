package mv.view.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import mv.controler.Controler;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.Observable;

@SuppressWarnings("serial")
class ToolBarPanel extends JPanel implements CPUObserver {
	private Controler ctrl;
	private JButton stepButton;
	private JButton runButton;
	private JButton pauseButton;
	private JButton resetButton;
	private JButton quitButton;
	
	ToolBarPanel(Controler ctrl, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		cpu.addObserver(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Actions"));
		
		stepButton = new JButton();
		stepButton.setIcon(createImageIcon("images/step.png"));
		stepButton.setToolTipText("Step");
		this.add(stepButton);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.step();
			}
		});
		
		runButton = new JButton();
		runButton.setIcon(createImageIcon("images/run.png"));
		runButton.setToolTipText("Run");
		this.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread (new Runnable() {
					public void run() {
						ctrl.run();
					}
				}).start();
			}
		});
		
		pauseButton = new JButton();
		pauseButton.setIcon(createImageIcon("images/pause.png"));
		pauseButton.setToolTipText("Pause");
		this.add(pauseButton);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.pause();
			}
		});
		
		resetButton = new JButton();
		resetButton.setIcon(createImageIcon("images/reset.png"));
		resetButton.setToolTipText("Reset");
		this.add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.reset();
				} catch (IOException e1) { }
			}
		});
		
		quitButton = new JButton();
		quitButton.setIcon(createImageIcon("images/exit.png"));
		quitButton.setToolTipText("Quit");
		this.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.closeOperation();
			}
		});
	}
	
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainWindow.class.getResource(path);
		if (imgURL != null) return new ImageIcon(imgURL);
		return null;
	}
	
	public void onStartInstrExecution(Instruction instr) {
		
	}

	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
		
	}

	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runButton.setEnabled(false);
				stepButton.setEnabled(false);
			}
		});
	}

	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runButton.setEnabled(true);
				stepButton.setEnabled(true);
			}
		});
	}

	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
			}
		});
	}

	public void onError(String msg) {
		
	}

	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(false);
				runButton.setEnabled(false);
				pauseButton.setEnabled(false);
			}
		});
	}

	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
				pauseButton.setEnabled(true);
			}
		});
	}
}
