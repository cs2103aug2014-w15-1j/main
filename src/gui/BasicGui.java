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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextField;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Cursor;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Dialog.ModalExclusionType;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.SystemColor;

import java.util.ArrayList;

import logic.JDate;

/**
 * class BasicGUI: contains the all visual composition and structure of GUI
 * 
 * <p>
 * GUI is mainly constructs by 3 main container: MeunuArea(for title),
 * MainArea(for main) and ControlArea(for input) And inside each container,
 * there exist a hierarchy of container and field. All components contribute to
 * the appearance and layout of GUI. In particular, components inside mainPanel
 * are substitutable, which allows the customization to some extent.
 * 
 * <p>
 * <strong>Note</strong>: singleton pattern is applied in this class.
 * 
 * @author A0119391A
 * 
 */
public class BasicGui extends JFrame {

	/*
	 * ====================================================================
	 * ===================== START OF PRIVATE FIELD =======================
	 * ====================================================================
	 */

	private static final long serialVersionUID = 1L;
	// instance of BasicGui
	private static BasicGui instance;

	// all relevant components
	private JPanel menuArea;
	private JPanel titlePanel;
	private JTextField titleWindow;

	private JPanel mainArea;
	private JPanel FeedbackPanel;
	private TransColorTextField feedbackWindow;
	private JPanel mainPanel;
	private JTextPane mainWindow;

	private JPanel controlArea;
	private JPanel inputPanel;
	private JTextField inputWindow;

	// constants for component size (unit in pixel)
	private final static int TOP_LEFT_X_VALUE = 100;
	private final static int TOP_LEFT_Y_VALUE = 100;
	private final static int FRAME_WIDTH = 1000;
	private final static int FRAME_HEIGHT = 640;
	private final static int MINIMUM_FRAME_WIDTH = 500;
	private final static int MINIMUM_FRAME_HEIGHT = 320;

	private final static int MENU_AREA_WIDTH = FRAME_WIDTH;
	private final static int MENU_AREA_HEIGHT = 30;

	String inputWindowHelperText = "Enter your command here:";
	// color info
	private final static Color COLOR_TITLE_WINDOW_BACK = Color.WHITE;
	private final static Color COLOR_TITLE_WINDOW_FORE = new Color(66, 161,
			223, 220);
	private final static Color COLOR_TITLE_WINDOW_BORDER = new Color(66, 161,
			223, 255);
	// font info
	private final static Font FONT_MAIN_WINDOW = new Font("Calibri",
			Font.PLAIN, 29);
	private final static Font FONT_FEEDBACK_WINDOW = new Font("Ayuthaya",
			Font.PLAIN, 13);

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

	public static BasicGui getInstance() {
		if (instance == null) {
			instance = new BasicGui();
		}
		return instance;
	}

	public void setTitleText(String text) {
		titleWindow.setText(text);
	}

	public void setFeedbackText(String text) {
		feedbackWindow.setTextTransColor(text);
	}

	public void showLayered() {
		MultiLayeredPanel layered = new MultiLayeredPanel();
		mainPanel.removeAll();
		mainPanel.add(layered);
	}

	public TaskListPanel showListed(ArrayList<String> a, ArrayList<String> b,
			ArrayList<String> c, ArrayList<Boolean> d) {
		TaskListPanel listed = new TaskListPanel(a, b, c, d);
		changeMainPanel(listed);

		return listed;
	}

	public AttributePanel ShowDetailed(ArrayList<String> a, ArrayList<String> b) {
		AttributePanel detailed = new AttributePanel(a, b);
		changeMainPanel(detailed);

		return detailed;

	}

	public void refreshMainPanel() {
		mainPanel.validate();
	}
	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */

	/*
	 * ====================================================================
	 * ===================== START OF Private METHOD =======================
	 * ====================================================================
	 */

	/**
	 * method BasicGUI: constructor of GUI. It is private as the singleton
	 * pattern is applied.
	 */
	private BasicGui() {
		constructFrame();
		constructContentPanel();

		constructMenuArea();
		constructTitlePanel();
		constructTitleWindow();

		constructMainArea();
		constructMainPanel();
		constructMainWindow();
		constructFeedbackPanel();
		constructFeedbackWindow();

		constructControlArea();
		constructInputPanel();
		constructInputWindow();

		validate();
	}

	private void constructFrame() {

		setRootPaneCheckingEnabled(false);
		setType(Type.UTILITY);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		getContentPane().setFocusTraversalPolicyProvider(true);
		setVisible(true);
	}

	private void constructContentPanel() {
		getContentPane().setEnabled(false);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(MINIMUM_FRAME_WIDTH,
				MINIMUM_FRAME_HEIGHT));
		setBounds(TOP_LEFT_X_VALUE, TOP_LEFT_Y_VALUE, FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void constructMenuArea() {
		menuArea = new JPanel();
		menuArea.setOpaque(false);
		menuArea.setPreferredSize(new Dimension(MENU_AREA_WIDTH,
				MENU_AREA_HEIGHT));
		menuArea.setLayout(new BorderLayout(0, 0));

		getContentPane().add(menuArea, BorderLayout.NORTH);
	}

	private void constructTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setOpaque(false);

		menuArea.add(titlePanel, BorderLayout.CENTER);
	}

	private void constructTitleWindow() {
		titleWindow = new JTextField();

		Font titleWindowFont = new Font("DIALOG", Font.ITALIC, 15);

		titleWindow.setFont(titleWindowFont);
		titleWindow.setEditable(false);
		titleWindow.setBackground(COLOR_TITLE_WINDOW_BACK);
		titleWindow.setForeground(COLOR_TITLE_WINDOW_FORE);
		titleWindow.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				COLOR_TITLE_WINDOW_BORDER));

		titlePanel.add(titleWindow, BorderLayout.CENTER);
	}

	private void constructMainArea() {
		mainArea = new ImagePanel();
		mainArea.setOpaque(true);
		mainArea.setLayout(new BorderLayout(0, 0));

		getContentPane().add(mainArea, BorderLayout.CENTER);

	}

	private void constructMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setRequestFocusEnabled(false);
		mainPanel.setOpaque(false);
		mainPanel.setDoubleBuffered(false);
		mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainPanel.setBorder(null);
		mainPanel.setLayout(new BorderLayout(0, 0));

		mainArea.add(mainPanel);
	}

	private void constructMainWindow() {
		mainWindow = new JTextPane();
		mainWindow.setOpaque(false);
		mainWindow.setBorder(null);
		mainWindow.setContentType("text/html");
		mainWindow.setFocusable(false);
		mainWindow.setEditable(false);
		mainWindow.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		mainWindow.setFont(FONT_MAIN_WINDOW);
		mainWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mainWindow.setText("<html><li>sample</li></html>");
		mainPanel.add(mainWindow);
	}

	private void constructFeedbackPanel() {
		FeedbackPanel = new JPanel();
		FeedbackPanel.setOpaque(false);
		FeedbackPanel.setBorder(null);
		FeedbackPanel.setLayout(new BorderLayout(0, 0));

		mainArea.add(FeedbackPanel, BorderLayout.SOUTH);
	}

	private void constructFeedbackWindow() {
		feedbackWindow = new TransColorTextField();
		feedbackWindow.setBorder(null);

		feedbackWindow.setFont(FONT_FEEDBACK_WINDOW);
		feedbackWindow.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackWindow.setEditable(false);

		FeedbackPanel.add(feedbackWindow, BorderLayout.NORTH);
	}

	private void constructControlArea() {
		controlArea = new JPanel();
		controlArea.setOpaque(false);
		controlArea.setBorder(null);
		controlArea.setLayout(new BorderLayout(0, 0));

		getContentPane().add(controlArea, BorderLayout.SOUTH);
	}

	private void constructInputPanel() {
		inputPanel = new JPanel();
		inputPanel.setOpaque(false);
		inputPanel.setLayout(new BorderLayout());

		controlArea.add(inputPanel);
	}

	private void constructInputWindow() {
		inputWindow = new JTextField();
		inputWindow.setOpaque(true);
		inputWindow.addActionListener(new EnterKeyListener(inputWindow));
		inputWindow.setText(inputWindowHelperText);
		inputWindow.selectAll();

		inputPanel.add(inputWindow);
		inputWindow.requestFocus();
	}

	/**
	 * a helper method to help change the inner component of main panel
	 * 
	 * @param panel
	 */
	private void changeMainPanel(CustomizedJPanel panel) {
		mainPanel.removeAll();
		mainPanel.add((Component) panel);
	}

	/*
	 * ====================================================================
	 * ===================== END OF PRIVATE FIELD =========================
	 * ====================================================================
	 */

}
