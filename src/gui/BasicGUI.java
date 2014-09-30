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
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;

/**
 * class BasicGUI: contains the basic structure of GUI 
 * @author JJ
 * GUI is mainly constructs by 4 container: 
 * titlePanel(for title), desktopPanel(for main), toolBar(for helper) and inputPanel(for input)
 * And inside each container, there exist a hierarchy of container and field.
 */
public class BasicGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel titlePanel;
	private TextField titleWindow;
	
	private JToolBar toolBar;
	private JTextArea HelpWindow;
	
	private JDesktopPane desktopPanel;
	private JPanel FeedbackPanel;
	private JTextField feedbackWindow;
	private JLayeredPane layeredPaneForMain;
	private TextArea mainWindow;
	
	private JPanel inputPanel;
	private JTextField inputWindow;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicGUI frame = new BasicGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BasicGUI() {
		getContentPane().setEnabled(false);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 307);
		
		desktopPanel = new JDesktopPane();
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
		
		layeredPaneForMain = new JLayeredPane();
		desktopPanel.add(layeredPaneForMain, BorderLayout.NORTH);
		
		mainWindow = new TextArea();
		mainWindow.setBounds(0, 0, 380, 215);
		layeredPaneForMain.add(mainWindow);
		mainWindow.setEditable(false);
		mainWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mainWindow.setForeground(new Color(0, 0, 0));
		mainWindow.setText("Do some thing!\nDo some thing more interesting!");
		mainWindow.setBackground(Color.WHITE);
		
		toolBar = new JToolBar();
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
		
		titlePanel = new JPanel();
		getContentPane().add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		titleWindow = new TextField();
		titlePanel.add(titleWindow);
		titleWindow.setEditable(false);
		titleWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		titleWindow.setText("Today is Sep 29 2014");
		titleWindow.setForeground(Color.WHITE);
		titleWindow.setBackground(Color.PINK);
		
		inputPanel = new JPanel();
		getContentPane().add(inputPanel, BorderLayout.SOUTH);
		inputPanel.setLayout(new BorderLayout(0, 0));
		
		inputWindow = new JTextField();
		inputPanel.add(inputWindow);
		inputWindow.setBackground(Color.WHITE);
		inputWindow.addActionListener(new inputHit());
		inputWindow.setText("Input");
		inputWindow.setColumns(30);
	}
	
	/**
	 * method inputHit: a action listener for the enter key hit in input
	 * if the user hit the enter key, the input message will be send to logic
	 */
	class inputHit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = inputWindow.getText().trim();
			String emptyString = "";
			inputWindow.setText(emptyString);
			RunLogic.Logic(command);
			
		}
	}
	
	public void setTitleText(String text){
		titleWindow.setText(text); 
	}
	public void setFeedbackText(String text){
		feedbackWindow.setText(text); 
	}
	public void setMainText(String text){
		mainWindow.setText(text);
	}
}
