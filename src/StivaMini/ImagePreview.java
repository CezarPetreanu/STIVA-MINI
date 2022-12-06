package StivaMini;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class ImagePreview {
	public void show() {
		JFrame frame = new JFrame("STIVA MINI. Image preview");
		frame.setResizable(false);
		frame.setSize(400, 450);
		frame.setVisible(true);
		
		Container content = frame.getContentPane();
		PreviewArea previewArea = new PreviewArea();
		content.add(previewArea);
	}
}
