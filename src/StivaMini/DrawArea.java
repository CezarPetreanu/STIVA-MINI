package StivaMini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class DrawArea extends JComponent{
	private Image image;
	private Graphics2D g2;
	private int mouseX;
	private int mouseY;
	private Color color;
	
	private int currentLayer;
	private int numberOfLayers;
	
	private tools tool;
	
	private List <Color[][]> layer = new ArrayList<>();
	
	public DrawArea() {
		color = Color.black;
		tool = tools.pencil;
		currentLayer = 0;
		numberOfLayers = 1;
		layer.add(new Color[10][10]);
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(tool == tools.fill) {
					mouseX = e.getX();
					mouseY = e.getY();
					int pixelX = mouseX/32;
					int pixelY = mouseY/32;
					
					if(layer.get(currentLayer)[pixelX][pixelY] != color) {
						fillColor(pixelX, pixelY, layer.get(currentLayer)[pixelX][pixelY]);
						screenUpdate();
					}
				}
				else
					drawPixel(e);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				drawPixel(e);
			}
		});
	}
	
	protected void paintComponent(Graphics g) {
		if(image == null) {
			image = createImage(320, 320);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			clear();
		}
		g.drawImage(image,  0,  0, null);
	}
	
	public void clear() {
		g2.setPaint(Color.white);
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				if((i+j)%2 == 0)
					g2.setPaint(Color.lightGray);
				else
					g2.setPaint(Color.gray);
				g2.fillRect(i*32, j*32, 32, 32);
				layer.get(currentLayer)[i][j] = null;
			}
				
		g2.setPaint(color);
		repaint();
	}
	
	private void drawPixel(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		int pixelX = mouseX/32;
		int pixelY = mouseY/32;
		
		if(g2 != null && pixelX >= 0 && pixelY >= 0 && pixelX <= 9 && pixelY <= 9) {
			if(tool == tools.pencil) {
				if(layer.get(currentLayer)[pixelX][pixelY] != color)
				{
					layer.get(currentLayer)[pixelX][pixelY] = color;
					g2.fillRect(pixelX*32, pixelY*32, 32, 32);
				}
			}
			else if(tool == tools.eraser) {
				if(layer.get(currentLayer)[pixelX][pixelY] != null) {
					layer.get(currentLayer)[pixelX][pixelY] = null;
					if((pixelX+pixelY)%2 == 0)
						g2.setPaint(Color.lightGray);
					else
						g2.setPaint(Color.gray);
					g2.fillRect(pixelX*32, pixelY*32, 32, 32);
				}
			}
			repaint();
		}
	}
	
	private void screenUpdate() {
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				if(layer.get(currentLayer)[i][j] != null) {
					g2.setPaint(layer.get(0)[i][j]);
					g2.fillRect(i*32, j*32, 32, 32);
				}	
			}
		g2.setPaint(color);
		repaint();
	}

	private void fillColor(int pixelX, int pixelY, Color seekColor) {
		if(pixelX >= 0 && pixelY >= 0 && pixelX <= 9 && pixelY <= 9 && layer.get(currentLayer)[pixelX][pixelY] == seekColor) {
			layer.get(currentLayer)[pixelX][pixelY] = color;
			fillColor(pixelX+1, pixelY, seekColor);
			fillColor(pixelX-1, pixelY, seekColor);
			fillColor(pixelX, pixelY+1, seekColor);
			fillColor(pixelX, pixelY-1, seekColor);
		}
	}

	public void setColor(Color newColor) {
		if(newColor != null) {
			color = newColor;
			g2.setPaint(color);
		}
	}
	public Color getColor() {
		return color;
	}
	
	public void setTool(tools newTool) {
		tool = newTool;
	}
}
