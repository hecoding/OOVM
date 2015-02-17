package mv.gui.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mv.cpu.CPU;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private CPU cpu;
	private ToolBarPanel toolBar;
	private ProgramPanel program;
	private StackPanel stack;
	private MemoryPanel memory;
	private InputPanel input;
	private OutputPanel output;
	private GUIControler guiCtrl;

	public MainWindow(CPU cpu) {
		super("Virtual Machine");
		this.cpu = cpu;
		initGUI();
		updateView();
	}

	private void initGUI() {
		guiCtrl = new GUIControler(cpu, this);
		toolBar = new ToolBarPanel(guiCtrl);
		program = new ProgramPanel(guiCtrl);
		stack = new StackPanel(guiCtrl);
		memory = new MemoryPanel(guiCtrl);
		input = new InputPanel(guiCtrl);
		output = new OutputPanel(guiCtrl);

	
		JPanel mainPanel = new JPanel(new GridBagLayout());
		this.add(mainPanel);
	
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.CENTER;
		mainPanel.add(toolBar, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 3;
		c.fill = GridBagConstraints.VERTICAL;
		mainPanel.add(program, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(stack, c);
		
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(memory, c);
		
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 2;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(input, c);
		
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 3;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(output, c);
		
		this.addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent e) {
				guiCtrl.quit();
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
		this.setMinimumSize(this.getSize());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	void updateView() {
		program.updateView();
		stack.updateView();
		memory.updateView();
	}
}
