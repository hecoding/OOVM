package mv.view.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import mv.controler.Controler;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.MemoryObserver;
import mv.model.observer.Observable;

@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements MemoryObserver<Integer>, CPUObserver {
	private Controler ctrl;
	Observable<CPUObserver> cpu;
	Observable<MemoryObserver<Integer>> memory;
	private MVTableModel model;
	private JTextField memPos;
	private JTextField memValue;
	private JButton setButton;
	
	MemoryPanel(Controler ctrl, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		this.cpu = cpu;
		this.memory = memory;
		
		memory.addObserver(this);
		cpu.addObserver(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Memory"));
		this.setLayout(new BorderLayout());
		
		model = new MVTableModel();
		JTable table = new JTable(model);
		
		JLabel posLabel = new JLabel ("Position");
		memPos = new JTextField(3);
		JLabel valueLabel = new JLabel ("Value");
		memValue = new JTextField(3);
		setButton = new JButton("Set");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int pos = Integer.parseInt(memPos.getText());
					int val = Integer.parseInt(memValue.getText());
					
					if (pos < 0) MainWindow.reportError("Position must be a positive number", "Input Error");
					else {
						ctrl.memorySet(pos, val);
					}
				} catch (NumberFormatException e) {
					MainWindow.reportError("The imput must be a number", "Input Error");
				}}
		});
		
		JPanel toolBar = new JPanel();
		toolBar.add(posLabel);
		toolBar.add(memPos);
		toolBar.add(valueLabel);
		toolBar.add(memValue);
		toolBar.add(setButton);
		
		JScrollPane scroll = new JScrollPane(table);
		
		this.add(scroll, BorderLayout.CENTER);
		this.add(toolBar, BorderLayout.PAGE_END);
		
		this.setMinimumSize(new Dimension(300, 300));
	}

	private class MVTableModel extends AbstractTableModel {
		String[] colNames = {"Adress", "Value"};
		TreeMap<Integer, Integer> content;
		
		public MVTableModel() {
			content = new TreeMap<Integer, Integer>();
		}
		
		public void setValue (int index, int value) {
			content.put(index, value);

			fireTableDataChanged();
		}
		
		public int getColumnCount() {
			return colNames.length;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object pos = content.keySet().toArray()[rowIndex];
			
			if (columnIndex == 0) return pos;
			else return content.get(pos);
		}
		
		public void reset() {
			content.clear();

			fireTableDataChanged();
		}
		
		public int getRowCount() {
			return content.size();
		}
		
		public String getColumnName(int col) {
			return colNames[col];
		}
	}

	public void onWrite(final int index, final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				model.setValue(index, value);
			}
		});
	}

	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				model.reset();
			}
		});
	}

	public void onStartInstrExecution(Instruction instr) {
	}

	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
	}

	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setButton.setEnabled(false);
			}
		});
		
	}

	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setButton.setEnabled(true);
			}
		});
	}

	public void onPause() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setButton.setEnabled(true);
			}
		});
	}

	public void onError(String msg) {
	}

	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setButton.setEnabled(false);
			}
		});
	}

	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setButton.setEnabled(true);
			}
		});
	}
}



