package mv.view.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import mv.controler.Controler;
import mv.model.mvsystem.out.OutStream;

@SuppressWarnings("serial")
class OutputPanel extends JPanel {
	private Controler ctrl;
	private JTextArea outputTextArea;
	
	OutputPanel(Controler ctrl) {
		this.ctrl = ctrl;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Output"));
		outputTextArea = new JTextArea();
		OutStream outNew = new OutStreamGUI();
		outputTextArea.setEditable(false);
		this.add(outputTextArea);
		ctrl.setOutStream( outNew );
		
		this.setLayout(new BorderLayout(0,0));
		this.setMinimumSize(new Dimension (0, 100));
		this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
	}
	
	class OutStreamGUI implements OutStream {
		private OutStream old;
		
		public OutStreamGUI() {
			this.old = ctrl.getOutStream();
		}
		
		public void open() {
			// no hacer nada, suponemos que old ya está abierto
		}
		
		public void close() throws IOException {
			old.close();
		}
		
		public void writeChar(final int c) throws IOException { 
			old.writeChar(c);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {					
					outputTextArea.append( String.valueOf( (char) c) );
				}
			});
		}
		
		public void reset() throws IOException {
			old.reset();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {					
					outputTextArea.setText(null);
				}
			});
		}
	}
}