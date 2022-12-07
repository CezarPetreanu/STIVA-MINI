package StivaMini;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePreview {
	
	private List <Color[][]> layer = new ArrayList<>();
	
	PreviewArea previewArea;
	JButton buttonLeft;
	JButton buttonRight;
	
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonLeft)
				previewArea.rotateLeft();
			else if(e.getSource() == buttonRight)
				previewArea.rotateRight();
		}
	};
	
	public ImagePreview(List <Color[][]> layer) {
		this.layer = layer;
	}
	
	public void show() {
		JFrame frame = new JFrame("STIVA MINI. Image preview");
		frame.setResizable(false);
		frame.setSize(400, 500);
		frame.setVisible(true);
		
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		
		previewArea = new PreviewArea(layer);
		content.add(previewArea, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		buttonLeft = new JButton("Left");
		buttonLeft.addActionListener(actionListener);
		buttonRight = new JButton("Right");
		buttonRight.addActionListener(actionListener);
		
		panel.add(buttonLeft);
		panel.add(buttonRight);
		
		content.add(panel, BorderLayout.SOUTH);
	}
}
