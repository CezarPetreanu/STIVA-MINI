package StivaMini;

import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {
		DrawArea drawArea = new DrawArea();
		drawArea.setVisible(true);
		drawArea.setLocationRelativeTo(null);
		drawArea.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

}
