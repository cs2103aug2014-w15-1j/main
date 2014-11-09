package gui;

import java.io.IOException;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import javax.imageio.ImageIO;

import java.util.logging.Level;
import java.util.logging.Logger;




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

	private static Logger logger = Logger.getLogger("ImagePanel");
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
			logger.log(Level.SEVERE, "default image cannot be loaded");
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
        	logger.log(Level.WARNING, "given image cannot be loaded");
        	ex.printStackTrace();
        	try {
    			background = ImageIO.read(ResourceLoader.load(defaultImageFilePath));
    		} catch (IOException e) {
    			logger.log(Level.SEVERE, "default image cannot be loaded");
    			ex.printStackTrace();
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