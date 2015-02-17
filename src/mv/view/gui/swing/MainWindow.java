package mv.view.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mv.controler.Controler;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.MemoryObserver;
import mv.model.observer.Observable;
import mv.model.observer.StackObserver;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements CPUObserver {
	private static Controler ctrl;
	private Observable<CPUObserver> cpu;
	private Observable<StackObserver<Integer>> stack;
	private Observable<MemoryObserver<Integer>> memory;
	
	private MenuBarPanel menuBar;
	private ToolBarPanel toolBar;
	private ProgramPanel programView;
	private StackPanel stackView;
	private MemoryPanel memoryView;
	private InputPanel inputView;
	private OutputPanel outputView;
	private StatusBarPanel statusView;
	
	public MainWindow(Controler controler, Observable<CPUObserver> cpu, Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory) {
		super("Virtual Machine");
		
		this.cpu = cpu;
		this.stack = stack;
		this.memory = memory;
		ctrl = controler;
		ctrl.setDelay(200);
		
		cpu.addObserver(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}

	private void initGUI() {
		menuBar = new MenuBarPanel(ctrl);
		toolBar = new ToolBarPanel(ctrl, cpu);
		programView = new ProgramPanel(ctrl, cpu);
		stackView = new StackPanel(ctrl, stack, cpu);
		memoryView = new MemoryPanel(ctrl, memory, cpu);
		inputView = new InputPanel(ctrl);
		outputView = new OutputPanel(ctrl);
		statusView = new StatusBarPanel(cpu, stack, memory);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel aplicationPanel = new JPanel(new GridBagLayout());
		
		mainPanel.add(menuBar, BorderLayout.PAGE_START);
		mainPanel.add(aplicationPanel, BorderLayout.CENTER);
		mainPanel.add(statusView, BorderLayout.PAGE_END);
		this.add(mainPanel);
	
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.CENTER;
		aplicationPanel.add(toolBar, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 3;
		c.fill = GridBagConstraints.VERTICAL;
		aplicationPanel.add(programView, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		aplicationPanel.add(stackView, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		aplicationPanel.add(memoryView, c);
		
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 2;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		aplicationPanel.add(inputView, c);
		
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 3;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		aplicationPanel.add(outputView, c);
		
		this.addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent e) {
				closeOperation();
			}

			public void windowActivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
				
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

			public void windowOpened(WindowEvent arg0) {
			}
		});

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setSize(new Dimension(780,690));
		this.setMinimumSize(new Dimension(660, 550));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void closeOperation() { // puede estar aquí, o en SwingControler
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Are you sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) ctrl.quit();
	}
	
	public void onStartInstrExecution(Instruction instr) {
	}
	
	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
	}
	
	public void onStartRun() {
	}
	
	public void onEndRun() {
	}

	public void onPause() {
	}
	
	public void onError(String msg) {
		reportError(msg, "Error");
	}
	
	public void onHalt() {
	}
	
	public void onReset(ProgramMV program) {
	}

	public static void reportError(String msg, String title) {
		if (!ctrl.isPaused()) ctrl.pause();
		
		JOptionPane.showMessageDialog(new JFrame(), msg, title, JOptionPane.ERROR_MESSAGE);
	}
}
