package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.TextField;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Cursor;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Dimension;

import logic.RunLogic;

import javax.swing.JDesktopPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.BoxLayout;

import java.awt.Window.Type;

/**
 * class BasicGUI: contains the basic structure of GUI
 * 
 * @author JJ 
 * 		   GUI is mainly constructs by 4 container: titlePanel(for title),
 *         desktopPanel(for main), toolBar(for helper) and inputPanel(for input)
 *         And inside each container, there exist a hierarchy of container and
 *         field.
 *         Note: singleton pattern is applied in this class 
 */
public class BasicGui extends JFrame {

	/*
	 * ====================================================================
	 * ===================== START OF PRIVATE FIELD =======================
	 * ====================================================================
	 */

	private static final long serialVersionUID = 1L;
	private static BasicGui instance;

	private JPanel titlePanel;
	private TextField titleWindow;

	private JToolBar toolBar;
	private JTextArea HelpWindow;

	private JDesktopPane desktopPanel;
	private JPanel FeedbackPanel;
	private JTextField feedbackWindow;
	private JPanel mainPanel;
	private JTextPane mainWindow;

	private JPanel inputPanel;
	private JTextField inputWindow;

	// constants for FRAME initialization (unit in pixel)
	private final static int TOP_LEFT_X_VALUE = 100;
	private final static int TOP_LEFT_Y_VALUE = 100;
	private final static int FRAME_WIDTH = 400;
	private final static int FRAME_HEIGHT = 500;

	/*
	 * ====================================================================
	 * ===================== END OF PRIVATE FIELD =========================
	 * ====================================================================
	 */

	/*
	 * ====================================================================
	 * ===================== START OF PUBLIC METHOD =======================
	 * ====================================================================
	 */

	/**
	 * method BasicGUI: constructor of GUI
	 */
	private BasicGui() {
		setUndecorated(true);
		setOpacity(0.75f);
		setRootPaneCheckingEnabled(false);
		setType(Type.UTILITY);
//		try {
//		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//		        if ("Nimbus".equals(info.getName())) {
//		            UIManager.setLookAndFeel(info.getClassName());
//		            break;
//		        }
//		    }
//		} catch (Exception e) {
//		    // If Nimbus is not available, you can set the GUI to another look and feel.
//		}
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		getContentPane().setFocusTraversalPolicyProvider(true);
		setVisible(true);
		constructContentPane();
		constructTitlePane();
		constructBodyPane();
		constructHelperPane();
		constructInputPane();
	}

	public static BasicGui getInstance() {
		if(instance == null) {
			instance = new BasicGui();
		}
		return instance;
	}

	public void setTitleText(String text) {
		titleWindow.setText(text);
	}

	public void setFeedbackText(String text) {
		feedbackWindow.setText(text);
	}

	public void setMainText(String text) {
		mainWindow.setText(text);
	}
	public void showLayered() {
		MultiLayeredPanel layered = new MultiLayeredPanel();
		mainPanel.removeAll();
		mainPanel.add(layered);
	}

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */
	private void constructInputPane() {
		inputPanel = new JPanel();
		getContentPane().add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLayout(new BorderLayout(0, 0));

		inputWindow = new JTextField();
		inputPanel.add(inputWindow);
		inputWindow.setBackground(Color.WHITE);
		inputWindow.addActionListener(new EnterKeyListener(inputWindow));
		inputWindow.setText("Input");
		inputWindow.setColumns(30);
	}

	private void constructHelperPane() {
		toolBar = new JToolBar();
		toolBar.setBorder(null);
		toolBar.setFloatable(false);
		toolBar.setSize(new Dimension(2314, 0));
		toolBar.setRollover(true);
		getContentPane().add(toolBar, BorderLayout.EAST);

		HelpWindow = new JTextArea();
		HelpWindow.setTabSize(1);
		HelpWindow.setRows(2);
		HelpWindow.setWrapStyleWord(true);
		toolBar.add(HelpWindow);
		HelpWindow.setBorder(null);
		HelpWindow.setEditable(false);
		HelpWindow.setBackground(new Color(0, 255, 204));
		HelpWindow.setText("command");
	}

	private void constructTitlePane() {
		titlePanel = new JPanel();
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));

		titleWindow = new TextField();
		titlePanel.add(titleWindow);
		titleWindow.setEditable(false);
		titleWindow
				.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		titleWindow.setText("Today is Sep 29 2014");
		titleWindow.setForeground(Color.WHITE);
		titleWindow.setBackground(Color.PINK);
	}

	private void constructBodyPane() {
		desktopPanel = new JDesktopPane();
		desktopPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(desktopPanel, BorderLayout.CENTER);
		desktopPanel.setLayout(new BorderLayout(0, 0));

		FeedbackPanel = new JPanel();
		desktopPanel.add(FeedbackPanel, BorderLayout.SOUTH);
		FeedbackPanel.setBackground(new Color(255, 255, 255));
		FeedbackPanel.setBorder(null);
		FeedbackPanel.setLayout(new BorderLayout(0, 0));

		feedbackWindow = new JTextField();
		FeedbackPanel.add(feedbackWindow, BorderLayout.NORTH);
		feedbackWindow.setMinimumSize(new Dimension(10, 8));
		feedbackWindow.setBorder(null);
		feedbackWindow.setFont(new Font("Ayuthaya", Font.PLAIN, 13));
		feedbackWindow.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackWindow.setBackground(new Color(153, 204, 255));
		feedbackWindow.setEditable(false);
		feedbackWindow.setText("Task Added!");
		feedbackWindow.setColumns(20);

		mainPanel = new JPanel();
		mainPanel.setRequestFocusEnabled(false);
		mainPanel.setOpaque(false);
		mainPanel.setDoubleBuffered(false);
		mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPanel.setBackground(Color.PINK);
		mainPanel.setBorder(null);
		desktopPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		mainWindow = new JTextPane();
		mainWindow.setBorder(null);
		mainWindow.setContentType("text/html");
		mainWindow.setFocusable(false);
		mainWindow.setEditable(false);
		mainWindow.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPanel.add(mainWindow);
		mainWindow.setFont(new Font("Calibri", Font.PLAIN, 29));
		mainWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mainWindow.setForeground(Color.BLACK);
		mainWindow.setText("<html><li>Hi</li></html>");
		mainWindow.setBackground(Color.WHITE);
	}

	private void constructContentPane() {
		getContentPane().setEnabled(false);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(TOP_LEFT_X_VALUE, TOP_LEFT_Y_VALUE, FRAME_WIDTH, FRAME_HEIGHT);
	}

	

}
