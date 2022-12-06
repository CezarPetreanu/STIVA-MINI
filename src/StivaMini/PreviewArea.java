package StivaMini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class PreviewArea extends JComponent{
	private Image image;
	private Graphics2D g2;
	
	public PreviewArea() {
		setDoubleBuffered(false);
	}
	
	protected void paintComponent(Graphics g) {
		if(image == null) {
			image = createImage(384, 384);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			clear();
		}
		g.drawImage(image,  0,  0, null);
	}
	
	public void clear() {
		g2.setPaint(Color.white);
		for(int i=0; i<12; i++)
			for(int j=0; j<12; j++) {
				if((i+j)%2 == 0)
					g2.setPaint(Color.lightGray);
				else
					g2.setPaint(Color.gray);
				g2.fillRect(i*32, j*32, 32, 32);
			}
		repaint();
	}
}
