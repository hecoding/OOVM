package mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel {
	private GUIControler guiCtrl;
	private JTextArea programTextArea;
	ProgramPanel (GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Program"));
		programTextArea = new JTextArea();
		programTextArea.setPreferredSize(new Dimension (125, 0));
		programTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		programTextArea.setEditable(false);
		this.setMinimumSize(new Dimension (138, 0));
		this.add(new JScrollPane(programTextArea));
		
	}
	
	
	void updateView() {
		String[] instrucciones;
		String ins = "";
		
		instrucciones = guiCtrl.getProgram().toString().split(System.lineSeparator());
		int pc = guiCtrl.getPC();
		
		if (pc < instrucciones.length)
			instrucciones[pc] = "*" + instrucciones[pc];
		else
			instrucciones[instrucciones.length - 1] = instrucciones[instrucciones.length - 1];
		
		for (String s : instrucciones)
			ins += s + System.lineSeparator();
		
		programTextArea.setText(ins);
	}
}
