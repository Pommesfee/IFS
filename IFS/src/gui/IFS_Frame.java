package gui;

import ifs.IFS;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import core.IFS_Manager;

public class IFS_Frame extends JFrame implements Runnable, ComponentListener {

	private static final long serialVersionUID = 1L;
	private String title = "IFS";

	// Titles
	private String btnGoTitle = "Start";
	private String btnContinueTitle = "Continue";
	private String btnPausetitle = "Pause";
	private String btnStopTitle = "Stop";
	private String btnClearTitle = "Löschen";

	private String lblSettingsText = "Settings";
	private String lblAdjustmentsText = "Adjustments";
	
	private String btnAddIFSTitle = "Add new IFS";
	private String btnPrintIFSTitle = "Print IFS-List";
	
//	private String btnSaveTitle = "Save";
//	private String btnLoadTitle = "Load";
	
	private String btnDeleteTitle = "Delete";

	// Thread Stuff
	private Thread thread = null;
	private boolean running = false;

	// Values
	private int canvasWidth = 350;
	private int canvasHeight = 350;
	private int canvasMinimumWidth = 200;
	private int canvasMinimumHeight = 200;

	private int ifsFrameHorizontalGap = 5;
	private int ifsFrameVerticalGap = 5;

	private int pnlToolHorizontalGap = 5;
	private int pnlToolVerticalGap = 5;

	// Widgets ---
	private JPanel pnlCanvas = null;
	private Canvas canvas = null;
	private Graphics gr = null;

	// Tool Panel/Buttons
	private JPanel pnlTool = null;
	private JButton btnGo = null;
	private JButton btnStop = null;
	private JButton btnPause = null;
	private JButton btnContinue = null;
	private JButton btnClear = null;

	// Tabbe Pane
	private JTabbedPane tabbedPane = null;

	// Tab Adjust
	private JPanel pnlAdj = null;
	private JLabel lblAdj = null;
	private JTextField fldOffsetX = null;
	private JTextField fldOffsetY = null;
	private JTextField fldScale = null;
	private JButton btnAdj = null;
	private JButton btnAdjClear = null;
	private boolean fldOffsetXChanged = false;
	private boolean fldOffsetYChanged = false;
	private boolean fldScaleChanged = false;

	// Tab Settings
	private JPanel pnlSet = null;
	private JLabel lblSet = null;
	private JComboBox<IFS> cmbIFS = null;
	private JButton btnAddIFS = null;
	private JButton btnPrintIFS = null;
//	private JButton btnSave = null;
//	private JButton btnLoad = null;
	private JButton btnDelete = null;

	// Objects
	private IFS_Manager ifsManager = null;

	public IFS_Frame() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle(title);

		this.initObjects();

		this.createWidgets();
		this.addWidgets();
		this.pack();

		this.setLocationRelativeTo(null);
		this.setMinimumSize(getContentPane().getPreferredSize());

		WindowListener winListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer = JOptionPane.showConfirmDialog(IFS_Frame.this,
						"Wollen sie das Programm wirklich beenden ?",
						"Wirklich beenden ?", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION) {
					getIfsManager().saveIFS("rsc/IFSListe.ifs");
					System.exit(0);
				}
			}
		};
		this.addWindowListener(winListener);

		gr = canvas.getGraphics();

		this.addComponentListener(this);
	}

	private void initObjects() {
		ifsManager = new IFS_Manager();

		getIfsManager().loadDefaultIFS("rsc/IFSListeStandart.ifs");
		getIfsManager().loadIFS("rsc/IFSListe.ifs");
		
	}

	private void createWidgets() {

		// Canvas Panel
		if (pnlCanvas == null) {
			pnlCanvas = new JPanel();
			pnlCanvas.setLayout(new BorderLayout());
			pnlCanvas.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(5, 5, 0, 0),
					BorderFactory.createLineBorder(Color.DARK_GRAY, 1)));
		}

		// Canvas
		if (canvas == null) {
			canvas = new Canvas();
			canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
			canvas.setMinimumSize(new Dimension(canvasMinimumWidth,
					canvasMinimumHeight));
			canvas.setBackground(Color.white);
		}

		// Tool Panel
		if (pnlTool == null) {
			pnlTool = new JPanel();
			pnlTool.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(0, 5, 5, 5),
					BorderFactory.createLineBorder(Color.DARK_GRAY, 1)));
		}

		// Button Go
		if (btnGo == null) {
			btnGo = new JButton();
			btnGo.setPreferredSize(new Dimension(80, 25));
			btnGo.setMaximumSize(new Dimension(80, 25));
			btnGo.setMinimumSize(new Dimension(80, 25));
			btnGo.setText(btnGoTitle);
			btnGo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onStart();
				}
			});
		}

		// Button Pause
		if (btnPause == null) {
			btnPause = new JButton();
			btnPause.setPreferredSize(new Dimension(80, 25));
			btnPause.setMaximumSize(new Dimension(80, 25));
			btnPause.setMinimumSize(new Dimension(80, 25));
			btnPause.setText(btnPausetitle);
			btnPause.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onPause();
				}
			});
			btnPause.setEnabled(false);
		}

		// Button Continue
		if (btnContinue == null) {
			btnContinue = new JButton();
			btnContinue.setPreferredSize(new Dimension(80, 25));
			btnContinue.setMaximumSize(new Dimension(80, 25));
			btnContinue.setMinimumSize(new Dimension(80, 25));
			btnContinue.setText(btnContinueTitle);
			btnContinue.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onContinue();
				}
			});
			btnContinue.setEnabled(false);
		}

		// Button Stop
		if (btnStop == null) {
			btnStop = new JButton();
			btnStop.setPreferredSize(new Dimension(80, 25));
			btnStop.setMaximumSize(new Dimension(80, 25));
			btnStop.setMinimumSize(new Dimension(80, 25));
			btnStop.setText(btnStopTitle);
			btnStop.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					onStop();
				}
			});
			btnStop.setEnabled(false);
		}

		// Button Clear
		if (btnClear == null) {
			btnClear = new JButton();
			btnClear.setPreferredSize(new Dimension(80, 25));
			btnClear.setMaximumSize(new Dimension(80, 25));
			btnClear.setMinimumSize(new Dimension(80, 25));
			btnClear.setText(btnClearTitle);
			btnClear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					clearCanvas();
				}
			});
			btnClear.setEnabled(false);
		}

		// Tabbed Pane
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createEmptyBorder(5, 0, 0, 5),
					BorderFactory.createLineBorder(Color.DARK_GRAY, 1)));
		}

		// Panel Set
		if (pnlSet == null) {
			pnlSet = new JPanel();
			pnlSet.setBackground(Color.white);
		}

		// Label Settings
		if (lblSet == null) {
			lblSet = new JLabel();
			lblSet.setText(lblSettingsText);
			lblSet.setFont(lblSet.getFont().deriveFont(Font.BOLD + Font.ITALIC,
					15));
			lblSet.setBackground(Color.LIGHT_GRAY);
			lblSet.setOpaque(true);
			lblSet.setPreferredSize(new Dimension(130, 25));
			lblSet.setMinimumSize(new Dimension(130, 25));
			lblSet.setMaximumSize(new Dimension(130, 25));
			lblSet.setHorizontalAlignment(SwingConstants.CENTER);
		}

		// Button Add new IFS
		if (btnAddIFS == null) {
			btnAddIFS = new JButton();
			btnAddIFS.setPreferredSize(new Dimension(130, 25));
			btnAddIFS.setMinimumSize(new Dimension(130, 25));
			btnAddIFS.setMaximumSize(new Dimension(130, 25));
			btnAddIFS.setText(btnAddIFSTitle);
			btnAddIFS.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					IFS_NewIFSPopup popup = new IFS_NewIFSPopup(IFS_Frame.this);
					popup.setLocationRelativeTo(IFS_Frame.this);
					popup.setVisible(true);
				}
			});
		}

		// Button Print IFS-List
		if (btnPrintIFS == null) {
			btnPrintIFS = new JButton();
			btnPrintIFS.setPreferredSize(new Dimension(130, 25));
			btnPrintIFS.setMinimumSize(new Dimension(130, 25));
			btnPrintIFS.setMaximumSize(new Dimension(130, 25));
			btnPrintIFS.setText(btnPrintIFSTitle);
			btnPrintIFS.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getIfsManager().printList();
				}
			});
		}
		
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setPreferredSize(new Dimension(130, 25));
			btnDelete.setMinimumSize(new Dimension(130, 25));
			btnDelete.setMaximumSize(new Dimension(130, 25));
			btnDelete.setText(btnDeleteTitle);
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getIfsManager().removeIFS((IFS) cmbIFS.getSelectedItem());
					upDateCmb();
				}
			});
		}
		
//		// Button Save
//		if (btnSave == null) {
//			btnSave = new JButton();
//			btnSave.setPreferredSize(new Dimension(130, 25));
//			btnSave.setMinimumSize(new Dimension(130, 25));
//			btnSave.setMaximumSize(new Dimension(130, 25));
//			btnSave.setText(btnSaveTitle);
//			btnSave.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					getIfsManager().saveIFS("IFSListe.ifs");
//				}
//			});
//		}
		
//		// Button Load
//		if (btnLoad == null) {
//			btnLoad = new JButton();
//			btnLoad.setPreferredSize(new Dimension(130, 25));
//			btnLoad.setMinimumSize(new Dimension(130, 25));
//			btnLoad.setMaximumSize(new Dimension(130, 25));
//			btnLoad.setText(btnLoadTitle);
//			btnLoad.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					getIfsManager().loadIFS("IFSListe.ifs");
//					for (int i = 0; i < ifsManager.getIFSList().size(); i++) {
//						cmbIFS.addItem(ifsManager.getIFSList().get(i));
//					}
//				}
//			});
//		}

		// IFS ComboBox
		if (cmbIFS == null) {
			cmbIFS = new JComboBox<IFS>();
			for (int i = 0; i < ifsManager.getIFSList().size(); i++) {
				cmbIFS.addItem(ifsManager.getIFSList().get(i));
			}
			cmbIFS.setPreferredSize(new Dimension(130, 25));
			cmbIFS.setMinimumSize(new Dimension(130, 25));
			cmbIFS.setMaximumSize(new Dimension(130, 25));
		}

		// Panel Adjust
		if (pnlAdj == null) {
			pnlAdj = new JPanel();
			pnlAdj.setBackground(Color.white);
		}

		// Label Adjust
		if (lblAdj == null) {
			lblAdj = new JLabel();
			lblAdj.setText(lblAdjustmentsText);
			lblAdj.setFont(lblSet.getFont().deriveFont(Font.BOLD + Font.ITALIC,
					15));
			lblAdj.setBackground(Color.LIGHT_GRAY);
			lblAdj.setOpaque(true);
			lblAdj.setPreferredSize(new Dimension(130, 25));
			lblAdj.setMinimumSize(new Dimension(130, 25));
			lblAdj.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		// Text Field Offset X
		if (fldOffsetX == null) {
			fldOffsetX = new JTextField();
			fldOffsetX.setText("x-offset ("
					+ getIfsManager().getSelectedIFS().getPoint().getOffsetX()
					+ ")");
			fldOffsetX.addFocusListener(new java.awt.event.FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if (fldOffsetX.getText().equalsIgnoreCase(
							"x-offset ("
									+ getIfsManager().getSelectedIFS()
											.getPoint().getOffsetX() + ")")) {
						fldOffsetX.setText("");
						setFldOffsetXChanged(true);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (fldOffsetX.getText().trim().isEmpty()) {
						fldOffsetX.setText("x-offset ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getOffsetX() + ")");
						setFldOffsetXChanged(false);
					}
				}

			});
			fldOffsetX.setPreferredSize(new Dimension(130, 25));
			fldOffsetX.setMinimumSize(new Dimension(130, 25));
		}

		// Textfield OffsetY
		if (fldOffsetY == null) {
			fldOffsetY = new JTextField();
			fldOffsetY.setText("y-offset ("
					+ getIfsManager().getSelectedIFS().getPoint().getOffsetY()
					+ ")");
			fldOffsetY.addFocusListener(new java.awt.event.FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if (fldOffsetY.getText().equalsIgnoreCase(
							"y-offset ("
									+ getIfsManager().getSelectedIFS()
											.getPoint().getOffsetY() + ")")) {
						fldOffsetY.setText("");
						setFldOffsetYChanged(true);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (fldOffsetY.getText().trim().isEmpty()) {
						fldOffsetY.setText("y-offset ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getOffsetY() + ")");
						setFldOffsetYChanged(false);
					}
				}

			});
			fldOffsetY.setPreferredSize(new Dimension(130, 25));
			fldOffsetY.setMinimumSize(new Dimension(130, 25));
		}

		// TextField Scale
		if (fldScale == null) {
			fldScale = new JTextField();
			fldScale.setText("scale ("
					+ getIfsManager().getSelectedIFS().getPoint().getScale()
					+ ")");
			fldScale.addFocusListener(new java.awt.event.FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					if (fldScale.getText().equalsIgnoreCase(
							"scale ("
									+ getIfsManager().getSelectedIFS()
											.getPoint().getScale() + ")")) {
						fldScale.setText("");
						setFldScaleChanged(true);
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (fldScale.getText().trim().isEmpty()) {
						fldScale.setText("scale ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getScale() + ")");
						setFldScaleChanged(false);
					}
				}

			});
			fldScale.setPreferredSize(new Dimension(130, 25));
			fldScale.setMinimumSize(new Dimension(130, 25));
		}

		// TODO Number Format Exception
		// Button Adjust
		if (btnAdj == null) {
			btnAdj = new JButton();
			btnAdj.setText("Adjust");
			btnAdj.setPreferredSize(new Dimension(130, 25));
			btnAdj.setMinimumSize(new Dimension(130, 25));
			btnAdj.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int xoffset = 0;
					int yoffset = 0;
					int scale = 0;
					if (!fldOffsetX.getText().trim().isEmpty()
							&& !fldOffsetX.getText().equalsIgnoreCase(
									"x-offset ("
											+ getIfsManager().getSelectedIFS()
													.getPoint().getOffsetX()
											+ ")")) {
						xoffset = Integer.parseInt(fldOffsetX.getText());
					}
					if (!fldOffsetY.getText().trim().isEmpty()
							&& !fldOffsetY.getText().equalsIgnoreCase(
									"y-offset ("
											+ getIfsManager().getSelectedIFS()
													.getPoint().getOffsetY()
											+ ")")) {
						yoffset = Integer.parseInt(fldOffsetY.getText());
					}
					if (!fldScale.getText().trim().isEmpty()
							&& !fldScale.getText().equalsIgnoreCase(
									"scale ("
											+ getIfsManager().getSelectedIFS()
													.getPoint().getScale()
											+ ")")) {
						scale = Integer.parseInt(fldScale.getText());
					}
					if (getFldOffsetXChanged()) {
						getIfsManager().getSelectedIFS().getPoint()
								.setOffsetX(xoffset);
						fldOffsetX.setText("x-offset ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getOffsetX() + ")");
						setFldOffsetXChanged(false);
					}
					if (getFldOffsetYChanged()) {
						getIfsManager().getSelectedIFS().getPoint()
								.setOffsetY(yoffset);
						fldOffsetY.setText("y-offset ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getOffsetY() + ")");
						setFldOffsetYChanged(false);
					}
					if (getFldScaleChanged()) {
						getIfsManager().getSelectedIFS().getPoint()
								.setScale(scale);
						fldScale.setText("scale ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getScale() + ")");
						setFldScaleChanged(false);
					}
				}
			});
		}

		// Button Adjust Clear
		if (btnAdjClear == null) {
			btnAdjClear = new JButton();
			btnAdjClear.setText("Adjust(Clear)");
			btnAdjClear.setPreferredSize(new Dimension(130, 25));
			btnAdjClear.setMinimumSize(new Dimension(130, 25));
			btnAdjClear.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int xoffset = 0;
					int yoffset = 0;
					int scale = 0;
					if (!fldOffsetX.getText().trim().isEmpty()
							&& !fldOffsetX.getText().equalsIgnoreCase(
									"x-offset ("
											+ getIfsManager().getSelectedIFS()
													.getPoint().getOffsetX()
											+ ")")) {
						xoffset = Integer.parseInt(fldOffsetX.getText());
					}
					if (!fldOffsetY.getText().trim().isEmpty()
							&& !fldOffsetY.getText().equalsIgnoreCase(
									"y-offset ("
											+ getIfsManager().getSelectedIFS()
													.getPoint().getOffsetY()
											+ ")")) {
						yoffset = Integer.parseInt(fldOffsetY.getText());
					}
					if (!fldScale.getText().trim().isEmpty()
							&& !fldScale.getText().equalsIgnoreCase(
									"scale ("
											+ getIfsManager().getSelectedIFS()
													.getPoint().getScale()
											+ ")")) {
						scale = Integer.parseInt(fldScale.getText());
					}
					if (getFldOffsetXChanged()) {
						getIfsManager().getSelectedIFS().getPoint()
								.setOffsetX(xoffset);
						fldOffsetX.setText("x-offset ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getOffsetX() + ")");
						setFldOffsetXChanged(false);
					}
					if (getFldOffsetYChanged()) {
						getIfsManager().getSelectedIFS().getPoint()
								.setOffsetY(yoffset);
						fldOffsetY.setText("y-offset ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getOffsetY() + ")");
						setFldOffsetYChanged(false);
					}
					if (getFldScaleChanged()) {
						getIfsManager().getSelectedIFS().getPoint()
								.setScale(scale);
						fldScale.setText("scale ("
								+ getIfsManager().getSelectedIFS().getPoint()
										.getScale() + ")");
						setFldScaleChanged(false);
					}
					clearCanvas();;
				}
			});
		}
	}

	private void addWidgets() {

		// Main frame Layout
		this.setLayout(new BorderLayout(ifsFrameHorizontalGap, ifsFrameVerticalGap));

		// Tool Panel
		pnlTool.setLayout(new FlowLayout(FlowLayout.CENTER, pnlToolHorizontalGap, pnlToolVerticalGap));
		pnlTool.add(btnGo);
		pnlTool.add(btnPause);
		pnlTool.add(btnContinue);
		pnlTool.add(btnStop);
		pnlTool.add(btnClear);

		// Settings Panel
		pnlSet.setLayout(new GridLayout(0, 1, 5, 5));
		//pnlSet.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlSet.add(lblSet);
		pnlSet.add(cmbIFS);
		pnlSet.add(btnAddIFS);
		pnlSet.add(btnPrintIFS);
		//pnlSet.add(btnSave);
		//pnlSet.add(btnLoad);
		pnlSet.add(btnDelete);

		// Panel Adjust
		pnlAdj.setLayout(new GridLayout(0, 1, 5, 5));
		//pnlAdj.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlAdj.add(lblAdj);
		pnlAdj.add(fldOffsetX);
		pnlAdj.add(fldOffsetY);
		pnlAdj.add(fldScale);
		pnlAdj.add(btnAdj);
		pnlAdj.add(btnAdjClear);

		// Tabbed Pane
		tabbedPane.add(pnlSet, 0);
		tabbedPane.setTitleAt(0, "Settings");
		tabbedPane.add(pnlAdj, 1);
		tabbedPane.setTitleAt(1, "Adjustments");
		tabbedPane.setTabLayoutPolicy(1);

		//Canvas Panel
		pnlCanvas.add(canvas, BorderLayout.CENTER);

		getContentPane().add(pnlCanvas, BorderLayout.CENTER);
		getContentPane().add(pnlTool, BorderLayout.PAGE_END);
		getContentPane().add(tabbedPane, BorderLayout.LINE_END);

	}

	private void setFldOffsetXChanged(boolean b) {
		this.fldOffsetXChanged = b;
	}

	private void setFldOffsetYChanged(boolean b) {
		this.fldOffsetYChanged = b;
	}

	private void setFldScaleChanged(boolean b) {
		this.fldScaleChanged = b;
	}

	private boolean getFldOffsetXChanged() {
		return this.fldOffsetXChanged;
	}

	private boolean getFldOffsetYChanged() {
		return this.fldOffsetYChanged;
	}

	private boolean getFldScaleChanged() {
		return this.fldScaleChanged;
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public IFS_Manager getIfsManager() {
		return ifsManager;
	}

	public JComboBox<IFS> getCmbIFS() {
		return this.cmbIFS;
	}

	private void clearCanvas() {
		gr.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
	}
	
	private void upDateCmb() {
		cmbIFS.removeAllItems();
		cmbIFS.validate();
		cmbIFS.repaint();
		for (int i = 0; i < getIfsManager().getSize(); i++) {
			cmbIFS.addItem(getIfsManager().getIFSList().get(i));
		}
	}

	private void onStart() {
		if (thread == null) {
			thread = new Thread(this);
			running = true;
			thread.start();
			btnGo.setEnabled(false);
			btnStop.setEnabled(true);
			btnPause.setEnabled(true);
			btnClear.setEnabled(true);

			IFS_Manager.setSelectedIFS(cmbIFS.getSelectedIndex());
		}
	}

	private void onStop() {
		running = false;
		thread = null;
		btnGo.setEnabled(true);
		btnStop.setEnabled(false);
		btnPause.setEnabled(false);
		btnContinue.setEnabled(false);
		btnClear.setEnabled(false);
		gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	private void onPause() {
		running = false;
		thread = null;
		btnContinue.setEnabled(true);
		btnPause.setEnabled(false);
	}

	private void onContinue() {
		if (thread == null) {
			thread = new Thread(this);
			running = true;
			thread.start();
			btnPause.setEnabled(true);
			btnContinue.setEnabled(false);
		}
	}

	private void onDraw() {
		getIfsManager().getSelectedIFS().drawPoint(gr);
		gr.drawString(
				"width: " + getCanvas().getWidth() + " | height: "
						+ getCanvas().getHeight(), 5, 15);
	}

	@Override
	public void run() {
		while (running) {
			
			// Auszuführendes IFS wählen
			int selectedIndex = cmbIFS.getSelectedIndex();
			IFS_Manager.setSelectedIFS(selectedIndex);

			// Von ausgewähltem IFS Funktionen ausführen
			int chF = getIfsManager().getSelectedIFS().chooseFunction();
			getIfsManager().getSelectedIFS().doChoosenFunction(chF);

			// Zeichnen
			onDraw();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		getIfsManager()
				.getSelectedIFS()
				.getPoint()
				.reCalculateOffsets(getCanvas().getWidth(),
						getCanvas().getHeight());
		fldOffsetX.setText("x-offset ("
				+ getIfsManager().getSelectedIFS().getPoint().getOffsetX()
				+ ")");
		fldOffsetY.setText("Y-offset ("
				+ getIfsManager().getSelectedIFS().getPoint().getOffsetY()
				+ ")");
		fldScale.setText("scale ("
				+ getIfsManager().getSelectedIFS().getPoint().getScale() + ")");

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

}
