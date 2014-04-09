package gui;

import ifs.IFS;
import ifs.IFS_Function;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IFS_NewIFSPopup extends JDialog {

	private static final long serialVersionUID = 1L;
	
	JPanel pnlNameStuff = null;
	JLabel lblAdd = null;
	JTextField fldName = null;

	JPanel pnlCenter = null;

	JPanel pnlValues = null;

	private ArrayList<IFS_FunctionPanel> functionPanels = null;

	private JPanel pnlBtn = null;
	private JButton btnAddFuncPnl = null;

	JPanel pnlTool = null;
	JButton btnAdd = null;
	JButton btnCancel = null;

	private IFS_Frame ifs_Frame = null;

	public IFS_NewIFSPopup(IFS_Frame ifs_Frame) {
		super(ifs_Frame);

		this.ifs_Frame = ifs_Frame;

		functionPanels = new ArrayList<IFS_FunctionPanel>();

		setTitle("Add new IFS");

		createWidgets();
		addWidets();

		pack();
	}

	private void createWidgets() {

		// Name Stuff
		pnlNameStuff = new JPanel();

		lblAdd = new JLabel();
		lblAdd.setText("Name: ");

		fldName = new JTextField();
		fldName.setPreferredSize(new Dimension(85, 25));
		fldName.setMinimumSize(new Dimension(85, 25));

		// Values
		pnlCenter = new JPanel();

		pnlValues = new JPanel();

		pnlBtn = new JPanel();

		btnAddFuncPnl = new JButton();
		btnAddFuncPnl.setText("Add new Function");
		btnAddFuncPnl.setPreferredSize(new Dimension(130, 25));
		btnAddFuncPnl.setMinimumSize(new Dimension(130, 25));
		btnAddFuncPnl.addActionListener(new AddFunctionActionListener(this));

		// Tools
		pnlTool = new JPanel();

		btnAdd = new JButton();
		btnAdd.setText("Add");
		btnAdd.setPreferredSize(new Dimension(85, 25));
		btnAdd.setMinimumSize(new Dimension(85, 25));
		btnAdd.addActionListener(new AddAction());
		btnCancel = new JButton();
		btnCancel.setText("Cancel");
		btnCancel.setPreferredSize(new Dimension(85, 25));
		btnCancel.setMinimumSize(new Dimension(85, 25));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	private void addWidets() {
		getContentPane().setLayout(new BorderLayout(5, 5));

		pnlNameStuff.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlNameStuff.add(lblAdd);
		pnlNameStuff.add(fldName);

		pnlValues.setLayout(new GridLayout(0, 1));

		for (int i = 0; i < functionPanels.size(); i++) {
			pnlValues.add(functionPanels.get(i));
		}

		pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlBtn.add(btnAddFuncPnl);

		pnlCenter.setLayout(new BorderLayout(5, 5));
		pnlCenter.add(pnlValues, BorderLayout.CENTER);
		pnlCenter.add(pnlBtn, BorderLayout.PAGE_END);

		pnlTool.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlTool.add(btnAdd);
		pnlTool.add(btnCancel);

		getContentPane().add(pnlNameStuff, BorderLayout.PAGE_START);
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		getContentPane().add(pnlTool, BorderLayout.PAGE_END);
	}

	private class AddAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<IFS_Function> functions = new ArrayList<IFS_Function>();

			for (int i = 0; i < functionPanels.size(); i++) {
				functions.add(new IFS_Function(Double
						.parseDouble(functionPanels.get(i).getFldValueA()
								.getText()), Double.parseDouble(functionPanels
						.get(i).getFldValueB().getText()), Double
						.parseDouble(functionPanels.get(i).getFldValueC()
								.getText()), Double.parseDouble(functionPanels
						.get(i).getFldValueD().getText()), Double
						.parseDouble(functionPanels.get(i).getFldValueE()
								.getText()), Double.parseDouble(functionPanels
						.get(i).getFldValueF().getText()), Double
						.parseDouble(functionPanels.get(i).getFldValuePercent()
								.getText())));
			}

			ifs_Frame.getIfsManager().addIFS(
					new IFS(fldName.getText(),
							ifs_Frame.getCanvas().getWidth(), ifs_Frame
									.getCanvas().getHeight(), functions, false));
			ifs_Frame.getCmbIFS().addItem(
					ifs_Frame.getIfsManager().getIFSList()
							.get(ifs_Frame.getIfsManager().getSize() - 1));
			dispose();
		}
	}
	
	private class AddFunctionActionListener implements ActionListener {
		
		private IFS_NewIFSPopup parent = null;
		
		public AddFunctionActionListener(IFS_NewIFSPopup parent) {
			this.parent = parent;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (functionPanels.size() < 7) {
				functionPanels.add(new IFS_FunctionPanel(parent));
				pnlValues.add(functionPanels.get(functionPanels.size() - 1));

				validate();
				pack();
			}
		}
	}
	
}
