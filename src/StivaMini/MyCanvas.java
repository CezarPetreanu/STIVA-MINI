package StivaMini;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class MyCanvas extends Canvas implements Serializable{
	private int size;
	private int gridSize;
	
	public MyCanvas(int size, int gridSize) {
		this.size = size;
		this.gridSize = gridSize;
	}
	
	public MyCanvas() {
		this.size = 480;
		this.gridSize = 16;
	}
	
	public int getUnitSize() {
		return size/gridSize;
	}
	
	public int getGridSize() {
		return gridSize;
	}
	public int getCanvasSize() {
		return size;
	}
	
	public void drawBackground(Graphics g) {
		int unit = getUnitSize();
		for(int i=0; i<gridSize; i++) {
			for(int j=0; j<gridSize; j++) {
				if((i+j)%2 == 0)
					g.setColor(Color.lightGray);
				else
					g.setColor(Color.gray);
				g.fillRect(i*unit, j*unit, unit, unit);
			}
		}
	}

	public void reset(int size, int gridSize) {
		this.size = size;
		this.gridSize = gridSize;
		
	}
}
