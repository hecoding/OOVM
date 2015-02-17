package mv.view.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import mv.controler.Controler;

@SuppressWarnings("serial")
public class MenuBarPanel extends JMenuBar {
	private Controler ctrl;
	private JFileChooser fc;
	private File file;
	
	public MenuBarPanel(Controler ctrl) {
		this.ctrl = ctrl;
		this.fc = new JFileChooser();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	// El JFileChooser puede causar una InterruptedException al cerrar el programa en
	// procesadores quadcore debido a un bug de sun.awt.Disposer. Info y solución:
	// http://stackoverflow.com/questions/7769885/interruptedexception-after-cancel-file-open-dialog-1-6-0-26
	
	private void initGUI() {
		JMenu archivo = new JMenu("Archivo");
		archivo.setMnemonic(KeyEvent.VK_A);
		this.add(archivo);
		
		JMenuItem abrirArchivo = new JMenuItem("Abrir archivo");
		abrirArchivo.setMnemonic(KeyEvent.VK_O);
		archivo.add(abrirArchivo);
		abrirArchivo.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n = fc.showOpenDialog(null);
				if (n == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					ctrl.loadProgram(file.getPath());
				}
			}
		});
		
		archivo.addSeparator();
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_E);
		archivo.add(salir);
		salir.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.closeOperation();
			}
		});
		
		
		JMenu opciones = new JMenu("Opciones");
		opciones.setMnemonic(KeyEvent.VK_O);
		this.add(opciones);
		
		JMenu ejecucion = new JMenu("Ejecución");
		opciones.add(ejecucion);
		
		JRadioButtonMenuItem noDelay = new JRadioButtonMenuItem("Ocultar pasos intermedios");
		noDelay.setMnemonic(KeyEvent.VK_D);
		noDelay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ctrl.setDelay(0);
			}
		});
		
		JRadioButtonMenuItem highSpeed = new JRadioButtonMenuItem("Velocidad alta");
		highSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setDelay(10);
			}
		});
		
		JRadioButtonMenuItem mediumSpeed = new JRadioButtonMenuItem("Velocidad media");
		mediumSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setDelay(100);
			}
		});
		
		JRadioButtonMenuItem lowSpeed = new JRadioButtonMenuItem("Velocidad baja");
		lowSpeed.setSelected(true);
		lowSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.setDelay(200);
			}
		});
		
		ButtonGroup speeds = new ButtonGroup();
		speeds.add(noDelay);
		speeds.add(highSpeed);
		speeds.add(mediumSpeed);
		speeds.add(lowSpeed);
		
		ejecucion.add(noDelay);
		ejecucion.add(highSpeed);
		ejecucion.add(mediumSpeed);
		ejecucion.add(lowSpeed);
		
	}

}
