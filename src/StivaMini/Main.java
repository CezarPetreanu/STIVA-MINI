package StivaMini;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	JButton buttonPencil;
	JButton buttonFill;
	JButton buttonEraser;
	JButton buttonClear;
	JButton buttonColor;
	JButton buttonTest;
	DrawArea drawArea;
	
	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonClear)
				drawArea.clear();
			else if(e.getSource() == buttonColor) {
				Color newColor = JColorChooser.showDialog(null, "Pick a color", drawArea.getColor());
				drawArea.setColor(newColor);
			}
			else if(e.getSource() == buttonPencil) {
				drawArea.setTool(tools.pencil);
				drawArea.setColor(drawArea.getColor());
				buttonPencil.setEnabled(false);
				buttonFill.setEnabled(true);
				buttonEraser.setEnabled(true);
			}
			else if(e.getSource() == buttonFill) {
				drawArea.setTool(tools.fill);
				drawArea.setColor(drawArea.getColor());
				buttonPencil.setEnabled(true);
				buttonFill.setEnabled(false);
				buttonEraser.setEnabled(true);
			}
			else if(e.getSource() == buttonEraser) {
				drawArea.setTool(tools.eraser);
				buttonPencil.setEnabled(true);
				buttonFill.setEnabled(true);
				buttonEraser.setEnabled(false);
			}
		}
	};
	
	public static void main(String[] args) {
		new Main().show();
	}
	
	public void show() {
		JFrame frame = new JFrame("STIVA MINI");
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		drawArea = new DrawArea();
		content.add(drawArea, BorderLayout.CENTER);
		
		JPanel filePanel = new JPanel();
		buttonClear = new JButton("New");
		buttonClear.addActionListener(actionListener);
		filePanel.add(buttonClear);
		content.add(filePanel, BorderLayout.NORTH);
		
		JPanel controlPanel = new JPanel();
		buttonPencil = new JButton("Pencil");
		buttonPencil.addActionListener(actionListener);
		buttonPencil.setEnabled(false);
		buttonFill = new JButton("Fill");
		buttonFill.addActionListener(actionListener);
		buttonEraser = new JButton("Eraser");
		buttonEraser.addActionListener(actionListener);
		buttonColor = new JButton("Colors");
		buttonColor.addActionListener(actionListener);
		
		controlPanel.add(buttonPencil);
		controlPanel.add(buttonFill);
		controlPanel.add(buttonEraser);
		controlPanel.add(buttonColor);
		content.add(controlPanel, BorderLayout.SOUTH);
		
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
