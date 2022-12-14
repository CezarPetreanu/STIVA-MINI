package StivaMini;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class PreviewArea extends JComponent{
	private Image image;
	private Graphics2D g2;
	private int numberOfLayers;
	private int rot;
	
	
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
		/*
		g2.setPaint(Color.white);
		for(int i=0; i<12; i++)
			for(int j=0; j<12; j++) {
				if((i+j)%2 == 0)
					g2.setPaint(Color.lightGray);
				else
					g2.setPaint(Color.gray);
				g2.fillRect(i*32, j*32, 32, 32);
			}
		*/
		g2.clearRect(0, 0, getWidth(), getHeight());
		for(int i=0; i<numberOfLayers; i++) {
			Image imageLayer = paintLayer(i);
			
			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.scale(1, 1);
			trans.translate(100, 160-i*8);
			trans.rotate(Math.toRadians(rot), imageLayer.getWidth(null)/2, imageLayer.getHeight(null)/2);
			g2.drawImage(imageLayer, trans, null);
			//g2.drawImage(imageLayer, 80, 80-i*4, null);
			repaint();
		}
	}
	
	public BufferedImage paintLayer(int layerNumber) {
		BufferedImage imageLayer = new BufferedImage(160, 160, BufferedImage.TYPE_INT_ARGB);;
		Graphics2D g2Layer;
		g2Layer = (Graphics2D) imageLayer.getGraphics();
		g2Layer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				if(layer.get(layerNumber)[i][j] != null)
					g2Layer.setPaint(layer.get(layerNumber)[i][j]);
				else
					g2Layer.setPaint(new Color(0, 0, 0, 0));
				g2Layer.fillRect(i*16, j*16, 16, 16);
			}

		
		return imageLayer;
	}

	public void rotateRight() {
		rot += 5;
		if(rot >= 360)
			rot = rot%360;
		clear();
	}
	
	public void rotateLeft() {
		rot -= 5;
		if(rot < 0)
			rot = 360+rot;
		clear();
	}
}
