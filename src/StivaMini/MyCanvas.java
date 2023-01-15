package StivaMini;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas{
	public int size;
	
	public MyCanvas(int size) {
		this.size = size;
	}
	
	public MyCanvas() {
		this.size = 32;
	}
	
	public int getUnitSize() {
		return 480/size;
	}
	
	public void drawBackground(Graphics g) {
		int unit = getUnitSize();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if((i+j)%2 == 0)
					g.setColor(Color.lightGray);
				else
					g.setColor(Color.gray);
				g.fillRect(i*unit, j*unit, unit, unit);
			}
		}
	}
}
