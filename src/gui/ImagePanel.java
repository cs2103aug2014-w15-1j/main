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


public class ImagePanel extends JPanel {

    private BufferedImage background;
    float[] scales = { 1f, 1f, 1f, 0.5f };
    float[] offsets = new float[4];
    RescaleOp rop;
    
    public ImagePanel() {
        try {
            background = ImageIO.read(new File("background.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return background == null ? super.getPreferredSize() : new Dimension(background.getWidth(), background.getHeight());
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
        BufferedImage bi = new
            BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        setOpacity(0.1f);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(bi, rop, 0, 0);
    }
    
    private void setOpacity(float opacity) {
    	 scales[3] = opacity;
         rop = new RescaleOp(scales, offsets, null);
        
    }
  

}