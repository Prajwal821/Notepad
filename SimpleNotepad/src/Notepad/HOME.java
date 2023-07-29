package Notepad;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//
//public class HOME {
//
//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HOME window = new HOME();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public HOME() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//}
/////
//import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JWindow;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
//import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//import java.awt.GridLayout;
//import java.awt.GridBagLayout;
//import java.awt.CardLayout;
//import javax.swing.JTextField;
//import java.awt.Panel;
//import java.awt.TextField;
//import javax.swing.JTree;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
//import javax.swing.JPanel;
//import java.awt.Button;
//import java.awt.Label;
//import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.DropMode;
//import javax.swing.JButton;

public class HOME implements ActionListener {
    
	JFrame window;
	// Text Area
	JTextArea textArea; 
	
	JScrollPane scrollPane;
	
	boolean wordWrapOn = false;
	//Top menu bar
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuFormat, menuColor;
	// File menu
	JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
	// Edit Menu
	JMenuItem iUndo, iRedo;
	//Format menu
	JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28; 
	JMenu menuFont, menuFontSize;
	// COLOR MENU
	JMenuItem iColor1, iColor2, iColor3,iColor4;
	
	Function_File file = new Function_File(this);
	Function_Format format = new Function_Format(this);
	Function_Color color = new Function_Color(this);
	Function_Edit edit = new Function_Edit(this);
	
	KeyHandler keyHandler = new KeyHandler(this);
	
	UndoManager um = new UndoManager();
	
	public static void main(String[] args) {
		new HOME();
	}
	
	public HOME() {
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createFormatMenu();
		createColorMenu();
		
		format.selectedFont = "";
		format.createFont(16);
		format.wordWrap();
		
		color.changeColor("White");
		window.setVisible(true);
		
	}
	
	public void createWindow() {
		window = new JFrame("Notepad");
		window.setSize(820, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		window.getContentPane().add(desktopPane, BorderLayout.NORTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 784, 23);
		desktopPane.add(toolBar);
		
		JTextPane textPane_1 = new JTextPane();
		window.getContentPane().add(textPane_1, BorderLayout.CENTER);
	}
	
	public void createTextArea() {
		
		textArea = new JTextArea();
		textArea.setFont(format.arial);
		
		textArea.addKeyListener(keyHandler);
		
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				// TODO Auto-generated method stub
				um.addEdit(e.getEdit());
			}
		});
		
		textArea.setLineWrap(true);
		
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		window.getContentPane().add(scrollPane);
	}
	
	public void createMenuBar() {
		 menuBar = new JMenuBar();
		 window.setJMenuBar(menuBar);
		 
		 menuFile = new JMenu("File");
		 menuBar.add(menuFile);
		 
		 menuEdit = new JMenu("Edit");
		 menuBar.add(menuEdit);
		 
		 menuFormat = new JMenu("Format");
		 menuBar.add(menuFormat);
		 
		 menuColor = new JMenu("Color");
		 menuBar.add(menuColor);
	}
	
	public void createFileMenu() {
		
		iNew = new JMenuItem("New");
		iNew.setSelected(true);
		iNew.setBackground(new Color(240, 240, 240));
		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		menuFile.add(iNew);
		
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);

		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);

		iSaveAs = new JMenuItem("Save As...");
		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("SaveAs");
		menuFile.add(iSaveAs);

		iExit = new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		menuFile.add(iExit);

	}
	
	public void createEditMenu() {
		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);
		
		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);
	}
	
	public void createFormatMenu() {
		
		iWrap = new JMenuItem("Word Wrap: Off");
		iWrap.addActionListener(this);
		iWrap.setActionCommand("Word Wrap");
		menuFormat.add(iWrap);
		
		menuFont = new JMenu("Font");
		menuFormat.add(menuFont);
		
		iFontArial = new JMenuItem("Arial");
		iFontArial.addActionListener(this);
		iFontArial.setActionCommand("Arial");
		menuFont.add(iFontArial);
		
		iFontCSMS = new JMenuItem("Comic Sans MS");
		iFontCSMS.addActionListener(this);
		iFontCSMS.setActionCommand("Comic Sans MS");
		menuFont.add(iFontCSMS);
		
		iFontTNR = new JMenuItem("Times New Roman");
		iFontTNR.addActionListener(this);
		iFontTNR.setActionCommand("Times New Roman");
		menuFont.add(iFontTNR);
		
		
		menuFontSize = new JMenu("Font Size");
		menuFormat.add(menuFontSize);
		
		iFontSize8 = new JMenuItem("8");
		iFontSize8.addActionListener(this);
		iFontSize8.setActionCommand("size8");
		menuFontSize.add(iFontSize8);
		
		iFontSize12 = new JMenuItem("12");
		iFontSize12.addActionListener(this);
		iFontSize12.setActionCommand("size12");
		menuFontSize.add(iFontSize12);
		
		iFontSize16 = new JMenuItem("16");
		iFontSize16.addActionListener(this);
		iFontSize16.setActionCommand("size16");
		menuFontSize.add(iFontSize16);
		
		iFontSize20 = new JMenuItem("20");
		iFontSize20.addActionListener(this);
		iFontSize20.setActionCommand("size20");
		menuFontSize.add(iFontSize20);
		
		iFontSize24 = new JMenuItem("24");
		iFontSize24.addActionListener(this);
		iFontSize24.setActionCommand("size24");
		menuFontSize.add(iFontSize24);
		
		iFontSize28 = new JMenuItem("28");
		iFontSize28.addActionListener(this);
		iFontSize28.setActionCommand("size28");
		menuFontSize.add(iFontSize28);
		
	}
	
	public void createColorMenu() {
		
		iColor1 = new JMenuItem("White");
		iColor1.addActionListener(this);
		iColor1.setActionCommand("White");
		menuColor.add(iColor1);
		
		iColor2 = new JMenuItem("Black");
		iColor2.addActionListener(this);
		iColor2.setActionCommand("Black");
		menuColor.add(iColor2);
		
		iColor3 = new JMenuItem("Blue");
		iColor3.addActionListener(this);
		iColor3.setActionCommand("Blue");
		menuColor.add(iColor3);
		
		iColor4 = new JMenuItem("Green");
		iColor4.addActionListener(this);
		iColor4.setActionCommand("Green");
		menuColor.add(iColor4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		switch (command) {
		case "New": file.newFile(); break;
		
		case "Open": file.open(); break;
		
		case "Save": file.save(); break;
		
		case "SaveAs": file.saveAs(); break;
		
		case "Exit": file.exit(); break;
		
		case "Word Wrap": format.wordWrap(); break;
		
		case "Undo": edit.undo(); break;
		case "Redo": edit.redo(); break;
		
		case "Arial": format.setFont(command); break;
		case "Comic Sans MS": format.setFont(command); break;
		case "Times New Roman": format.setFont(command); break;
		
		case "size8": format.createFont(8); break;
		case "size12": format.createFont(12); break;
		case "size16": format.createFont(16); break;
		case "size20": format.createFont(20); break;
		case "size24": format.createFont(24); break;
		case "size28": format.createFont(28); break;
		
		case "White": color.changeColor(command); break;
		case "Black": color.changeColor(command); break;
		case "Blue": color.changeColor(command); break;
		case "Green": color.changeColor(command); break;
		
		}
    }
}

