package mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import mv.cpu.Memory;
import mv.cpu.Memory.MemCell;

@SuppressWarnings("serial")
public class MemoryPanel extends JPanel {
	private GUIControler guiCtrl;
	private MemoryTableModel model;
	private JTextField memPos;
	private JTextField memValue;
	private Memory<Integer> memory;
	
	MemoryPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		memory = guiCtrl.getMemory();
		initGUI();
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Memory"));
		this.setLayout(new BorderLayout());
		model = new MemoryTableModel();
		JTable table = new JTable(model);
		JButton set = new JButton("Set");
		memPos = new JTextField("", 3);
		memValue = new JTextField("", 3);
		JLabel posLabel = new JLabel ("Position");
		JLabel valueLabel = new JLabel ("Value");
		
		JScrollPane scroll = new JScrollPane(table);
		
		JPanel position = new JPanel(new FlowLayout());
		position.add(posLabel, FlowLayout.LEFT);
		position.add(memPos, FlowLayout.CENTER);
		
		JPanel value = new JPanel(new FlowLayout());
		position.add(valueLabel, FlowLayout.LEFT);
		position.add(memValue, FlowLayout.CENTER);
		
		JPanel toolBar = new JPanel(new FlowLayout());
		toolBar.add(position, FlowLayout.LEFT);
		toolBar.add(value, FlowLayout.CENTER);
		toolBar.add(set, FlowLayout.RIGHT);
		
		this.add(scroll, BorderLayout.CENTER);
		this.add(toolBar, BorderLayout.PAGE_END);
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guiCtrl.memorySet(memPos.getText(), memValue.getText());
				updateView();}
		});
		
		this.setPreferredSize(new Dimension(300, 300));
	}
	void updateView() {
		model.refresh();
	}
	


	class MemoryTableModel extends AbstractTableModel {
		String[] colNames = {"Adress", "Value"};
		int[][] memTable = new int [0][0];
		
		public MemoryTableModel() {
			refresh();
		}
		
		public void refresh(){
			ArrayList<MemCell<Integer>> array = memory.getArray();
			if(array != null) {
				memTable = new int [array.size()][2];
				for (int i = 0; i < array.size(); i++) {
					memTable[i][0] = array.get(i).getPos();
					memTable[i][1] = array.get(i).getValue();
				}
				fireTableDataChanged();
			}
		}
		
		public int getRowCount() {
			return memTable.length;
		}
		
		public String getColumnName(int col){
			return colNames[col];
			}
		
		public int getColumnCount() {
			return colNames.length;
		}
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			return memTable[rowIndex][columnIndex];
		}
	}	
}



