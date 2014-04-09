package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IFS_FunctionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblValueA = null;
	private JLabel lblValueB = null;
	private JLabel lblValueC = null;
	private JLabel lblValueD = null;
	private JLabel lblValueE = null;
	private JLabel lblValueF = null;
	private JLabel lblValuePercent = null;

	private JTextField fldValueA = null;
	private JTextField fldValueB = null;
	private JTextField fldValueC = null;
	private JTextField fldValueD = null;
	private JTextField fldValueE = null;
	private JTextField fldValueF = null;
	private JTextField fldValuePercent = null;
	
	private JButton btnRemove = null;

	private IFS_NewIFSPopup parent = null;
	
	public IFS_FunctionPanel(IFS_NewIFSPopup parent) {
		
		this.parent = parent;
		
		lblValueA = new JLabel();
		lblValueA.setText("A: ");

		lblValueB = new JLabel();
		lblValueB.setText("B: ");

		lblValueC = new JLabel();
		lblValueC.setText("C: ");

		lblValueD = new JLabel();
		lblValueD.setText("D: ");

		lblValueE = new JLabel();
		lblValueE.setText("E: ");

		lblValueF = new JLabel();
		lblValueF.setText("F: ");

		lblValuePercent = new JLabel();
		lblValuePercent.setText("%: ");

		fldValueA = new JTextField();
		fldValueA.setPreferredSize(new Dimension(40, 25));
		fldValueA.setMinimumSize(new Dimension(40, 25));

		fldValueB = new JTextField();
		fldValueB.setPreferredSize(new Dimension(40, 25));
		fldValueB.setMinimumSize(new Dimension(40, 25));

		fldValueC = new JTextField();
		fldValueC.setPreferredSize(new Dimension(40, 25));
		fldValueC.setMinimumSize(new Dimension(40, 25));

		fldValueD = new JTextField();
		fldValueD.setPreferredSize(new Dimension(40, 25));
		fldValueD.setMinimumSize(new Dimension(40, 25));

		fldValueE = new JTextField();
		fldValueE.setPreferredSize(new Dimension(40, 25));
		fldValueE.setMinimumSize(new Dimension(40, 25));

		fldValueF = new JTextField();
		fldValueF.setPreferredSize(new Dimension(40, 25));
		fldValueF.setMinimumSize(new Dimension(40, 25));

		fldValuePercent = new JTextField();
		fldValuePercent.setPreferredSize(new Dimension(40, 25));
		fldValuePercent.setMinimumSize(new Dimension(40, 25));
		
		btnRemove = new JButton();
		btnRemove.setText("Remove");
		btnRemove.setPreferredSize(new Dimension(40, 25));
		btnRemove.setMinimumSize(new Dimension(40, 25));
		btnRemove.addActionListener(new RemoveActionListener(this));

		this.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		this.add(lblValueA);
		this.add(fldValueA);

		this.add(lblValueB);
		this.add(fldValueB);

		this.add(lblValueC);
		this.add(fldValueC);

		this.add(lblValueD);
		this.add(fldValueD);

		this.add(lblValueE);
		this.add(fldValueE);

		this.add(lblValueF);
		this.add(fldValueF);

		this.add(lblValuePercent);
		this.add(fldValuePercent);
		
		this.add(btnRemove);

	}

	public JTextField getFldValueA() {
		return fldValueA;
	}

	public JTextField getFldValueB() {
		return fldValueB;
	}

	public JTextField getFldValueC() {
		return fldValueC;
	}

	public JTextField getFldValueD() {
		return fldValueD;
	}

	public JTextField getFldValueE() {
		return fldValueE;
	}

	public JTextField getFldValueF() {
		return fldValueF;
	}

	public JTextField getFldValuePercent() {
		return fldValuePercent;
	}

	private class RemoveActionListener implements ActionListener {
		
		IFS_FunctionPanel panel = null;
		
		public RemoveActionListener(IFS_FunctionPanel panel) {
			this.panel = panel;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			panel.parent.remove(panel);
			panel.parent.validate();
			panel.parent.repaint();
		}
		
	}
	
}
