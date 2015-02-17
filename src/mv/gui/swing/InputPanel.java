package mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import mv.mvSystem.in.InStream;

@SuppressWarnings("serial")
class InputPanel extends JPanel {
	private GUIControler guiCtrl;
	private JTextArea inputTextArea;
	
	InputPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Input"));
		inputTextArea = new JTextArea();
		InStream inNew = new InStreamGUI();
		this.add(inputTextArea);
		inputTextArea.setEditable(false);
		guiCtrl.setInStream( inNew );
		
		this.setLayout(new BorderLayout(0,0));
		this.setPreferredSize(new Dimension (0, 100));
		this.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
	}
	
	class InStreamGUI implements InStream {
		InStream old;
		StringBuilder content;
		int pos;
		
		public InStreamGUI() {
			content = new StringBuilder();
			this.old = guiCtrl.getInStream();
			pos = 0;
			// 1. leer toda la entrada del old, y construir el StringBuilder content
			int a = -1;
			try {
				a = old.read();
				while(a != -1) {
					content.insert(pos, (char) a);
					a = old.read();
					pos++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pos = 0;
			// 2. mostrar el contenido de content en el inputTextArea
			inputTextArea.setText(content.toString());
		}
		
		public void open() {} // suponemos que old ya está abierto
		public void close() { try {
			old.close();
		} catch (IOException e) {
			e.printStackTrace();
		} } // cerrar old también
		
		public int read() {
			char c;
			
			// 1. si pos == content.length() entonces ya no hay más caracteres
			if(pos == content.length()) return -1;
			// 2. consultar el caracter c el la posición pos del content
			c= content.charAt(pos);
			// 3. si c no es salto de linea (c!=10 y c!=13) lo cambiamos con * en content
			if(c != 10 && c != 13) {
				content.setCharAt(pos, '*');
			}
			// 4. actualizar el inputTextArea;
			inputTextArea.setText(content.toString());
			// 5. actualizar pos
			pos++;
			// 6. devolver c;
			return (int) c; 
	}

}}