package mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import mv.cpu.OperandStack;


@SuppressWarnings("serial")
public class StackPanel extends JPanel {
	private GUIControler guiCtrl;
	private JList<Integer> stackArea;
	private JTextField stackElem;
	private DefaultListModel<Integer> model;
	StackPanel(GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setBorder(new TitledBorder("Operand Stack"));
		model = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(model);
		JLabel label = new JLabel("value");
		label.setName("value");

		stackElem = new JTextField(3);
		
		JPanel toolBar = new JPanel();
		toolBar.add(label);
		toolBar.add(stackElem);
		
		JButton pushButton = new JButton();
		pushButton.setToolTipText("Push");
		pushButton.setText("Push");
		toolBar.add(pushButton);
		pushButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.push(stackElem.getText());
			}
		});
		
		JButton popButton = new JButton();
		popButton.setToolTipText("Pop");
		popButton.setText("Pop");
		toolBar.add(popButton);
		popButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.pop();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		panel.add(new JScrollPane(stackArea), BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.PAGE_END);
		
		this.setLayout(new BorderLayout(0, 0));
		this.setPreferredSize(new Dimension(300, 300));
		this.add(panel, BorderLayout.CENTER);
	}
	
	void updateView() {
		OperandStack<Integer> operandStack = guiCtrl.getOperandStack();
		model.clear();
		
		for (int i = 0; i < operandStack.size(); i++) 
			model.addElement(operandStack.elementAt(i));
	}
}
