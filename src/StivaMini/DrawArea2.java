package StivaMini;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Label;
import javax.swing.JScrollPane;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class DrawArea2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawArea2 frame = new DrawArea2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrawArea2() {
		setTitle("STIVA MINI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 525);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New...");
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		
		JMenu mnOpenRecent = new JMenu("Open Recent");
		mnFile.add(mnOpenRecent);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExport = new JMenuItem("Export...");
		mnFile.add(mntmExport);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmTutorial = new JMenuItem("Tutorial");
		mnHelp.add(mntmTutorial);
		
		JMenuItem mntmDocumentation = new JMenuItem("Documentation");
		mnHelp.add(mntmDocumentation);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelDraw = new JPanel();
		tabbedPane.addTab("Draw", null, panelDraw, null);
		panelDraw.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDrawMessage = new JLabel("...");
		panelDraw.add(lblDrawMessage, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panelDraw.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JButton btnLayerMoveUp = new JButton("\u2B9D");
		panel_4.add(btnLayerMoveUp);
		
		JButton btnLayerMoveDown = new JButton("\u2B9F");
		panel_4.add(btnLayerMoveDown);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnLayerAdd = new JButton("Add");
		panel_5.add(btnLayerAdd);
		
		JButton btnLayerDelete = new JButton("Delete");
		panel_5.add(btnLayerDelete);
		
		JList list = new JList();
		panel_2.add(list, BorderLayout.CENTER);
		
		JLabel lblDrawLayers = new JLabel("Layers");
		panel_2.add(lblDrawLayers, BorderLayout.NORTH);
		
		JPanel panel_6 = new JPanel();
		panelDraw.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTools = new JLabel("Tools");
		panel_6.add(lblTools, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JButton btnDrawPencil = new JButton("Pencil ");
		panel_7.add(btnDrawPencil);
		
		JButton btnNewButton_1 = new JButton("   Fill   ");
		panel_7.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eraser");
		panel_7.add(btnNewButton_2);
		
		JLabel lblColor = new JLabel("Color");
		panel_7.add(lblColor);
		
		JButton btnNewButton = new JButton(" ");
		panel_7.add(btnNewButton);
		
		JPanel panelPreview = new JPanel();
		tabbedPane.addTab("Preview", null, panelPreview, null);
		panelPreview.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelPreview.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPreviewMessage = new JLabel("...");
		panel.add(lblPreviewMessage, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		
		JButton btnPreviewLeft = new JButton("\u2B9C");
		panel_1.add(btnPreviewLeft);
		
		JButton btnPreviewPlay = new JButton("\u25b6");
		panel_1.add(btnPreviewPlay);
		
		JButton btnPreviewRight = new JButton("\u2B9E");
		btnPreviewRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnPreviewRight);
	}

}
