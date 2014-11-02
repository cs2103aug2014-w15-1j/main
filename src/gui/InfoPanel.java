package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

public class InfoPanel extends JPanel{

	private static final long serialVersionUID = -6143438348516086903L;
	
	private boolean isNextPage;
	private boolean isPreviousPage;
	
	
	public InfoPanel() {
		super();
		this.setLayout(new FlowLayout());
		add(new BasicArrowButton(BasicArrowButton.EAST), BorderLayout.EAST);
	}
	
	
	public static void main(String[] args){
		InfoPanel j = new InfoPanel();
		
	}
	
}
