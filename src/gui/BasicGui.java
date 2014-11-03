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
 * class BasicGUI: contains the basic structure of GUI
 * 
 * @author JJ GUI is mainly constructs by 4 container: titlePanel(for title),
 *         desktopPanel(for main), toolBar(for helper) and inputPanel(for input)
 *         And inside each container, there exist a hierarchy of container and
 *         field. Note: singleton pattern is applied in this class
 */
public class BasicGui extends JFrame {

	/*
	 * ====================================================================
	 * ===================== START OF PRIVATE FIELD =======================
	 * ====================================================================
	 */

	private static final long serialVersionUID = 1L;
	private static BasicGui instance;


	private JPanel menuArea;
	private JMenuBar menuBar;
	private JPanel menuPanel;
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
	
	private JToolBar helperArea;
	private JPanel helperPanel;
	private JTextArea HelpWindow;
	
	
	// constants for FRAME initialization (unit in pixel)
	private final static int TOP_LEFT_X_VALUE = 100;
	private final static int TOP_LEFT_Y_VALUE = 100;
	private final static int FRAME_WIDTH = 1000;
	private final static int FRAME_HEIGHT = 600;
	
	int pX,pY;

	/*
	 * ====================================================================
	 * ===================== END OF PRIVATE FIELD =========================
	 * ====================================================================
	 */

	

//	private void constructgetContentPane()() {
//		GraphicsEnvironment ge = GraphicsEnvironment
//				.getLocalGraphicsEnvironment();
//		GraphicsDevice gd = ge.getDefaultScreenDevice();
//		boolean isPerPixelTranslucencySupported = gd
//				.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);
//
//		// If translucent windows aren't supported, exit.
//		if (!isPerPixelTranslucencySupported) {
//			System.out.println("Per-pixel translucency is not supported");
//			System.exit(0);
//		}
//		getContentPane() = new PerPixelTranslucencyPanel();
//			
//
//		setContentPane(getContentPane());
//		getContentPane().setLayout(new BorderLayout());
//
//	}

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
		constructFrame();
		
		constructContentPanel();
		
		constructMenuArea();
		//constructMenuBar();
		constructTitlePanel();
		constructTitleWindow();
		
		constructMainArea();
		constructMainPanel();
		constructMainWindow();
		constructFeedbackPanel();
		constructFeedbackWindow();
		
//		constructHelperArea();
//		constructHelperPane();
//		constructHelperWindow();
		
		constructControlArea();
		constructInputPanel();
		constructInputWindow();
		
		this.validate();
	}

	/**
	 * 
	 */
	private void constructFrame() {
		setUndecorated(true);
		//setOpacity(0.8f);
		setBackground(new Color(0, 0, 0, 0));
		setLocationRelativeTo(null);
		setRootPaneCheckingEnabled(false);
		setType(Type.UTILITY);
		
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		getContentPane().setFocusTraversalPolicyProvider(true);
		setVisible(true);
	}
	
	private void constructContentPanel() {
		getContentPane().setEnabled(false);
		getContentPane().setForeground(new Color(0,0,0));
		getContentPane().setBackground(new Color(0,0,0));
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(TOP_LEFT_X_VALUE, TOP_LEFT_Y_VALUE, FRAME_WIDTH, FRAME_HEIGHT);
	}
	private void constructMenuArea() {
		menuArea = new JPanel();
		menuArea.setOpaque(false);
		menuArea.setPreferredSize(new Dimension(FRAME_WIDTH,25));
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
		
		Font font1 = new Font("DIALOG", Font.ITALIC, 15); 
		 
		titleWindow.setFont(font1);
		titleWindow.setBackground(new Color(255, 255, 255, 230));
		//new Color(66, 161, 223, 220)
		titleWindow.setEditable(false);
		titleWindow.setText("Today is Sep 29 2014");
		
		titleWindow.setForeground(new Color(66, 161, 223, 220));
		titleWindow.setBorder(BorderFactory.createMatteBorder(1,0,1,0 ,new Color(66, 161, 223, 255) ));
		
		enableDraggableTitle();
		
		titlePanel.add(titleWindow, BorderLayout.CENTER);
	}

	private void enableDraggableTitle() {
		titleWindow.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me)
            {
                // Get x,y and store them
                pX=me.getX();
                pY=me.getY();
            }
        });
		titleWindow.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent me)
            {
                // Set the location
                // get the current location x-co-ordinate and then get
                // the current drag x co-ordinate, add them and subtract most recent
                // mouse pressed x co-ordinate
                // do same for y co-ordinate
                setLocation(getLocation().x+me.getX()-pX,getLocation().y+me.getY()-pY);
            }
        });
	}
	private void constructMainArea() {
		mainArea = new ImagePanel();
		mainArea.setOpaque(true);
		mainArea.setBackground(new Color(0,0,0,255));
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
		mainPanel.setLayout(new BorderLayout(0,0));
		
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
		mainWindow.setFont(new Font("Calibri", Font.PLAIN, 29));
		mainWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mainWindow.setText("<html><li>sample</li></html>");
		mainWindow.setBackground(new Color(255,255,255,144));
		
		
		mainPanel.add(mainWindow);
	}
		

	private void constructFeedbackPanel() {
		FeedbackPanel = new JPanel();
		FeedbackPanel.setOpaque(false);
		FeedbackPanel.setBackground(new Color(255, 255, 255, 0));
		FeedbackPanel.setBorder(null);
		FeedbackPanel.setLayout(new BorderLayout(0, 0));
		
		mainArea.add(FeedbackPanel, BorderLayout.SOUTH);
	}
	
	private void constructFeedbackWindow() {
		feedbackWindow = new TransColorTextField();
		feedbackWindow.setMinimumSize(new Dimension(10, 8));
		feedbackWindow.setBorder(null);
		feedbackWindow.setFont(new Font("Ayuthaya", Font.PLAIN, 13));
		feedbackWindow.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackWindow.setEditable(false);
		feedbackWindow.setTextTransColor("Task Added!");
		feedbackWindow.setColumns(20);
		
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
		//inputWindow.setBackground(new Color(255, 255, 255, 255));
		inputWindow.addActionListener(new EnterKeyListener(inputWindow));
		inputWindow.setText("Enter your"
				+ " command here:");
		inputWindow.selectAll();
		inputWindow.setColumns(30);
		
		inputPanel.add(inputWindow);
		inputWindow.requestFocus();
	}

	private void constructHelperArea() {
		helperArea = new JToolBar();
		helperArea.setBorder(null);
		helperArea.setFloatable(false);
		helperArea.setSize(new Dimension(2314, 0));
		helperArea.setRollover(true);
		
		getContentPane().add(helperArea, BorderLayout.EAST);
	}
	
	private void constructHelperPane() {
		helperPanel = new JPanel();
		helperPanel.setBorder(null);
		helperPanel.setLayout(new BorderLayout());
		
		helperArea.add(helperPanel);
	}

	/**
	 * 
	 */
	private void constructHelperWindow() {
		HelpWindow = new JTextArea();
		HelpWindow.setTabSize(1);
		HelpWindow.setRows(2);
		HelpWindow.setWrapStyleWord(true);
		HelpWindow.setBorder(null);
		HelpWindow.setEditable(false);
		HelpWindow.setBackground(new Color(135, 206, 250, 220));
		HelpWindow.setText("command");
		
		helperPanel.add(HelpWindow);
	}

	

	

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
		mainPanel.validate();
	}
	public void showListed(ArrayList<String> a, ArrayList<String> b, ArrayList<String> c,  ArrayList<Boolean> d, boolean pre, boolean nxt, int highlightedLine, boolean multiple,String highlightedDate) {
		ColumnListPanel listed = new ColumnListPanel(a, b, c, d, pre, nxt, highlightedLine,  multiple,highlightedDate);
		mainPanel.removeAll();
		mainPanel.add(listed);
		mainPanel.validate();
	}
	
	public void ShowDetailed(ArrayList<String> a, ArrayList<String> b, int highlightedProperty) {
		AttributePanel detailed = new AttributePanel(a, b, highlightedProperty);
		mainPanel.removeAll();
		mainPanel.add(detailed);
		mainPanel.validate();
		
	}
	

	/*
	 * ====================================================================
	 * ===================== END OF PUBLIC METHOD =========================
	 * ====================================================================
	 */
//	public static void main(String[] args){
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//            	BasicGui gui = BasicGui.getInstance();
//            }
//        });
//		
//	}

}
