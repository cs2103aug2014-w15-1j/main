package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class PerPixelTranslucencyPanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			final int R = 255;
			final int G = 255;
			final int B = 255;

			Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B,
					200), 0.0f, getHeight(), new Color(R, G, B, 255),
					true);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(p);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
