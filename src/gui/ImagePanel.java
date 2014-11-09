package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * <code>ImagePanel</code> is a customized swing container that extends
 * <code>JPanel</code>. It allows the addition of background image.
 * 
 * @author A0119391A
 * 
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage background;
	
	private String defaultImageFilePath = "images/background.jpg";

	
	/********************************************
	 ************** Constructor *****************
	 ********************************************/
	/**
	 * create a new ImagePanel using default background image
	 */
	public ImagePanel() {
		try {
			background = ImageIO.read(ResourceLoader.load(defaultImageFilePath));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * create a new <code>ImagePanel</code> given a background image. if the
	 * given path is invalid, default background image is used instead.
	 * 
	 * @param path
	 *            file path of image
	 */
	public ImagePanel(String path) {
		try {
			background = ImageIO.read(ResourceLoader.load(path));
        } catch (IOException ex) {
        	try {
				background = ImageIO.read(new File(defaultImageFilePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
		

	}

	/********************************************
	 ************* Public Method ****************
	 ********************************************/
	@Override
	public Dimension getPreferredSize() {
		return background == null ? super.getPreferredSize() : new Dimension(
				background.getWidth(), background.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
	}	
}