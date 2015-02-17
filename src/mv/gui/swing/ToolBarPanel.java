package mv.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ToolBarPanel extends JPanel {
	private GUIControler guiCtrl;
	
	ToolBarPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI() {
		JButton stepButton = new JButton();
		stepButton.setIcon(createImageIcon("step.png"));
		stepButton.setToolTipText("Step");
		this.add(stepButton);
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.step();
			}
		});
		
		JButton runButton = new JButton();
		runButton.setIcon(createImageIcon("run.png"));
		runButton.setToolTipText("Run");
		this.add(runButton);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.run();
			}
		});
		
		JButton quitButton = new JButton();
		quitButton.setIcon(createImageIcon("exit.png"));
		runButton.setToolTipText("Quit");
		this.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.quit();
			}
		});
	}
	
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MainWindow.class.getResource(path);
		if (imgURL != null) return new ImageIcon(imgURL);
		return null;
	}
}
