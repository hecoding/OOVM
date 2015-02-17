package mv.view.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import mv.controler.Controler;
import mv.model.cpu.Memory;
import mv.model.cpu.OperandStack;
import mv.model.cpu.ProgramMV;
import mv.model.ins.Instruction;
import mv.model.observer.CPUObserver;
import mv.model.observer.Observable;

@SuppressWarnings("serial")
class ProgramPanel extends JPanel implements CPUObserver {
	private Controler ctrl;
	private ProgramTableModel model;
	private ProgramMV program;
	private	int pc;
	
	ProgramPanel(Controler ctrl, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		cpu.addObserver(this);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initGUI();
			}
		});
	}
	
	ProgramPanel (Controler guiCtrl) {
		this.ctrl = guiCtrl;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Program"));
		
		model = new ProgramTableModel();
		JTable table = new JTable(model);
		
		table.setTableHeader(null);
		table.setShowGrid(false);
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.setFont(new Font("Courier", Font.PLAIN, 16));
		
		JScrollPane finalTable = new JScrollPane(table);
		finalTable.setMinimumSize(new Dimension (152, 0));
		this.add(finalTable);
		
		program = ctrl.getProgram();
		pc = 0;
		showProgram();
	}
	
	private void showProgram() {
		model.refresh(this.program, this.pc);
	}
	
	class ProgramTableModel extends AbstractTableModel {
		String[] colNames = {"PC", "Instruction"};
		String[][] progTable = new String [0][0];
		
		public ProgramTableModel() {
			refresh(ctrl.getProgram(), ctrl.getPC());
		}
		
		public void refresh (ProgramMV program, int pc) {
			String[] instrucciones;
			
			instrucciones = program.toString().split(System.lineSeparator());
			
			if(instrucciones != null) {
				progTable = new String [instrucciones.length][2];
				for (int i = 0; i < instrucciones.length; i++) {
					progTable[i][1] = instrucciones[i];
				}
				
				if (pc < instrucciones.length)
					progTable[pc][0] = "*";
				
				fireTableDataChanged();
			}
		}
		
		public int getRowCount() {
			return progTable.length;
		}
		
		public String getColumnName(int col){
			return colNames[col];
			}
		
		public int getColumnCount() {
			return colNames.length;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			return progTable[rowIndex][columnIndex];
		}
	}
	
	public void onStartInstrExecution(Instruction instr) {
	}

	public void onEndInstrExecution(int pc, OperandStack<Integer> stack, Memory<Integer> memory) {
		this.pc = pc;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				showProgram();
			}
		});
	}

	public void onStartRun() {
	}

	public void onEndRun() {
	}

	public void onPause() {
	}

	public void onError(String msg) {
	}

	public void onHalt() {
	}

	public void onReset(ProgramMV program) {
		this.pc = 0;
		this.program = program;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {				
				showProgram();
			}
		});
	}
}
