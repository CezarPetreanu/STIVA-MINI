package StivaMini;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class ImagePreview {
	
	private List <Color[][]> layer = new ArrayList<>();
	
	public ImagePreview(List <Color[][]> layer) {
		this.layer = layer;
	}
	
	public void show() {
		JFrame frame = new JFrame("STIVA MINI. Image preview");
		frame.setResizable(false);
		frame.setSize(400, 450);
		frame.setVisible(true);
		
		Container content = frame.getContentPane();
		PreviewArea previewArea = new PreviewArea(layer);
		content.add(previewArea);
	}
}
