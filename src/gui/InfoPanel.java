package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

public class InfoPanel extends JPanel{

	private static final long serialVersionUID = -6143438348516086903L;
	
	private boolean isNextPage;
	private boolean isPreviousPage;
	
	
	public InfoPanel(boolean isPreviousPage, boolean isNextPage) {
		super();
		this.setLayout(new FlowLayout());
		this.add(createImageButton("nextArrowY"));
		
	}
	
	public JButton createImageButton(String path) {
		ImageIcon icon = new ImageIcon(path);
		JButton btn = new JButton(icon);
		return btn;
	}
	
	
	
	
	
	
}
