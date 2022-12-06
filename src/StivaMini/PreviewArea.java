package StivaMini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class PreviewArea extends JComponent{
	private Image image;
	private Graphics2D g2;
	private int numberOfLayers;
	
	private List <Color[][]> layer = new ArrayList<>();
	
	public PreviewArea(List <Color[][]> layer) {
		setDoubleBuffered(false);
		this.layer = layer;
		numberOfLayers = this.layer.size();
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
		Image layerImage = paintLayer();
		repaint();
	}
	
	public Image paintLayer() {
		Image imageLayer;
		Graphics2D g2Layer;
		imageLayer = createImage(160, 160);
		g2Layer = (Graphics2D) image.getGraphics();
		g2Layer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				if(layer.get(0)[i][j] != null) {
					g2Layer.setPaint(layer.get(0)[i][j]);
					g2Layer.fillRect(i*16, j*16, 16, 16);
				}
			}
		
		return imageLayer;
	}
}
