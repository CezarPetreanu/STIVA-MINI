package StivaMini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class DrawArea extends JComponent{
	private Image image;
	private Graphics2D g2;
	private int mouseX;
	private int mouseY;
	private Color color;
	
	private tools tool;
	
	private Color[][] layer = new Color[10][10];
	
	public DrawArea() {
		color = Color.black;
		tool = tools.pencil;
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
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
				this.layer[i][j] = null;
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
			if(this.tool == tools.pencil) {
				if(this.layer[pixelX][pixelY] != this.color)
				{
					this.layer[pixelX][pixelY] = this.color;
					g2.fillRect(pixelX*32, pixelY*32, 32, 32);
				}
			}
			else if(this.tool == tools.eraser) {
				if(this.layer[pixelX][pixelY] != null) {
					this.layer[pixelX][pixelY] = null;
					if((pixelX+pixelY)%2 == 0)
						g2.setPaint(Color.lightGray);
					else
						g2.setPaint(Color.gray);
					g2.fillRect(pixelX*32, pixelY*32, 32, 32);
				}
			}
			else if(this.tool == tools.fill) {
				if(this.layer[pixelX][pixelY] != this.color) {
					fillColor(pixelX, pixelY, this.layer[pixelX][pixelY]);
					screenUpdate();
				}
			}
			repaint();
		}
	}
	
	private void screenUpdate() {
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				if(this.layer[i][j] != null) {
					g2.setPaint(this.layer[i][j]);
					g2.fillRect(i*32, j*32, 32, 32);
				}	
			}
		g2.setPaint(this.color);	
	}

	private void fillColor(int pixelX, int pixelY, Color seekColor) {
		if(pixelX >= 0 && pixelY >= 0 && pixelX <= 9 && pixelY <= 9 && this.layer[pixelX][pixelY] == seekColor) {
			this.layer[pixelX][pixelY] = this.color;
			fillColor(pixelX+1, pixelY, seekColor);
			fillColor(pixelX-1, pixelY, seekColor);
			fillColor(pixelX, pixelY+1, seekColor);
			fillColor(pixelX, pixelY-1, seekColor);
		}
	}

	public void setColor(Color newColor) {
		this.color = newColor;
		g2.setPaint(color);
	}
	public Color getColor() {
		return this.color;
	}
	
	public void setTool(tools newTool) {
		this.tool = newTool;
	}
}
