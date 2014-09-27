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
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Dimension;

public class TaskView extends JFrame {
	
	private TextArea mainWindow;
	private TextField titleWindow;
	
	private JTextField inputWindow;
	private JTextField feedbackWindow;
	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskView frame = new TaskView();
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
	public TaskView() {
		getContentPane().setEnabled(false);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 307);
		
		titleWindow = new TextField();
		titleWindow.setEditable(false);
		titleWindow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		titleWindow.setText("Today is Sep 29 2014");
		titleWindow.setForeground(Color.WHITE);
		titleWindow.setBackground(new Color(0, 153, 204));
		getContentPane().add(titleWindow, BorderLayout.NORTH);
		
		JTextArea HelpWindow = new JTextArea();
		HelpWindow.setBorder(null);
		HelpWindow.setEditable(false);
		HelpWindow.setBackground(new Color(0, 255, 204));
		HelpWindow.setText("sdafafasfas");
		getContentPane().add(HelpWindow, BorderLayout.EAST);
		
		inputWindow = new JTextField();
		inputWindow.setBackground(UIManager.getColor("Button.background"));
		inputWindow.addActionListener(new inputHit());
		inputWindow.setText("Input");
		getContentPane().add(inputWindow, BorderLayout.SOUTH);
		inputWindow.setColumns(10);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBorder(null);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		mainWindow = new TextArea();
		mainWindow.setEditable(false);
		mainPanel.add(mainWindow);
		mainWindow.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		mainWindow.setForeground(new Color(0, 0, 0));
		mainWindow.setText("Do some thing!\nDo some thing more interesting!");
		mainWindow.setBackground(Color.WHITE);
		
		feedbackWindow = new JTextField();
		feedbackWindow.setMinimumSize(new Dimension(10, 8));
		feedbackWindow.setBorder(null);
		feedbackWindow.setFont(new Font("Ayuthaya", Font.PLAIN, 13));
		feedbackWindow.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackWindow.setBackground(new Color(153, 204, 255));
		feedbackWindow.setEditable(false);
		feedbackWindow.setText("Task Added!");
		mainPanel.add(feedbackWindow, BorderLayout.SOUTH);
		feedbackWindow.setColumns(20);
	}
	
	class inputHit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String nm = inputWindow.getText().trim();
			if (nm.length() == 0) {
				feedbackWindow.setText("empty input");
				return;
			} else {
				feedbackWindow.setText("input received");
			}
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
