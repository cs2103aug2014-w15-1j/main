package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

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
	private String imageFilePath;
	private String defaultImageFilePath = "backgroundw.jpg";

	float[] scales = { 1f, 1f, 1f, 1f };
	float[] offsets = new float[4];
	RescaleOp rop;

	/********************************************
	 ************** Constructor *****************
	 ********************************************/
	/**
	 * create a new ImagePanel using default background image
	 */
	public ImagePanel() {
		try {
			background = ImageIO.read(new File(defaultImageFilePath));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * create a new <code>ImagePanel</code> given a background image. if the given path is
	 * invalid, default background image is used instead.
	 * 
	 * @param path
	 *            file path of image
	 */
	public ImagePanel(String path) {
		File image = new File(imageFilePath);
		if (image.exists()) {
			try {
				background = ImageIO.read(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
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
		if (background != null) {
			int x = (getWidth() - background.getWidth()) / 2;
			int y = (getHeight() - background.getHeight()) / 2;
			g.drawImage(background, x, y, this);
		}
		int w = background.getWidth(null);
		int h = background.getHeight(null);
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		setOpacity(0.1f);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, rop, 0, 0);
	}

	/********************************************
	 ************* Private Method ****************
	 ********************************************/
	private void setOpacity(float opacity) {
		scales[3] = opacity;
		rop = new RescaleOp(scales, offsets, null);

	}

}