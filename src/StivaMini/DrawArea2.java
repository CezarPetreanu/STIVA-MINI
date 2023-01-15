package StivaMini;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawArea2 extends JFrame {

	private Color color;
	private int currentLayer;
	private int numberOfLayers;
	private JPanel contentPane;
	private tools tool;
	private List <Color[][]> layer = new ArrayList<>();

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
		setBounds(100, 100, 746, 617);
		
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
		
		JButton btnDrawFill = new JButton("   Fill   ");
		panel_7.add(btnDrawFill);
		
		JButton btnDrawEraser = new JButton("Eraser");
		panel_7.add(btnDrawEraser);
		
		JLabel lblColor = new JLabel("Color");
		panel_7.add(lblColor);
		
		JButton btnColorPicker = new JButton("          ");
		btnColorPicker.setFocusable(false);
		btnColorPicker.setBackground(new Color(0, 0, 0));
		panel_7.add(btnColorPicker);
		
		Panel panel_8 = new Panel();
		panelDraw.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(null);
		
		MyCanvas canvas = new MyCanvas(16) {
			public void paint(Graphics g)
            {
				drawBackground(g);
            }
		};
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Graphics g = canvas.getGraphics();
				int unit = canvas.getUnitSize();
				int x = e.getX()/unit;
				int y = e.getY()/unit;
				g.fillRect(x*unit, y*unit, unit, unit);
			}
		});
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Graphics g = canvas.getGraphics();
				int unit = canvas.getUnitSize();
				int x = e.getX()/unit;
				int y = e.getY()/unit;
				g.fillRect(x*unit, y*unit, unit, unit);
			}
		});
		canvas.setBackground(new Color(255, 255, 255));
		canvas.setBounds(10, 10, 480, 480);
		panel_8.add(canvas);
		
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
