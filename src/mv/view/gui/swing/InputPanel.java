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
import mv.model.mvsystem.in.InStream;

@SuppressWarnings("serial")
class InputPanel extends JPanel {
	private Controler ctrl;
	private JTextArea inputTextArea;
	InStreamGUI inNew;
	
	InputPanel(Controler ctrl) {
		this.ctrl = ctrl;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Input"));
		inputTextArea = new JTextArea();
		inNew = new InStreamGUI();
		this.add(inputTextArea);
		inputTextArea.setEditable(false);
		ctrl.setInStream( inNew );
		
		this.setLayout(new BorderLayout(0,0));
		this.setMinimumSize(new Dimension (0, 100));
		this.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
	}
	
	class InStreamGUI implements InStream {
		InStream old;
		StringBuilder content;
		int pos;
		
		public InStreamGUI() {
			content = new StringBuilder();
			this.old = ctrl.getInStream();
			initStream();
		}
		
		private void initStream() {
			pos = 0;
			int a = -1;
			
			try {
				a = old.read();
				
				while(a != -1) {
					content.insert(pos, (char) a);
					a = old.read();
					pos++;
				}
			} catch (IOException e) {}
			
			pos = 0;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {					
					inputTextArea.setText(content.toString());
				}
			});
		}
		
		public void open() {} // suponemos que old ya está abierto
		public void close() {
			try {
				old.close();
			} catch (IOException e) {}
		}
		
		public int read() {
			char c;
			
			if (pos == content.length()) return -1;
			c = content.charAt(pos);
			
			if (c != 10 && c != 13) {
				content.setCharAt(pos, '*');
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {					
					inputTextArea.setText(content.toString());
				}
			});
			pos++;
			
			return (int) c; 
		}

		public void reset() throws IOException {
			content.delete(0, content.length());
			old.reset();
			initStream();
		}
	}
}