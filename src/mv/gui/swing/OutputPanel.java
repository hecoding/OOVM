package mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.mvSystem.out.OutStream;

@SuppressWarnings("serial")
class OutputPanel extends JPanel {
	private GUIControler guiCtrl;
	private JTextArea outputTextArea;
	
	OutputPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		
		initGUI();
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Output"));
		outputTextArea = new JTextArea();
		OutStream outNew = new OutStreamGUI();
		outputTextArea.setEditable(false);
		this.add(outputTextArea);
		guiCtrl.setOutStream( outNew );
		
		this.setLayout(new BorderLayout(0,0));
		this.setPreferredSize(new Dimension (0, 100));
		this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
	}
	
	class OutStreamGUI implements OutStream {
		private OutStream old;
		
		public OutStreamGUI() {
			this.old = guiCtrl.getOutStream();
		}
		
		public void open() {
			// no hacer nada, suponemos que old ya está abierto
		}
		
		public void close() throws IOException {
			old.close();
		}
		
		public void writeChar(int c) throws IOException { 
			old.writeChar(c);
			outputTextArea.append( String.valueOf( (char) c) );
		}
	}
}