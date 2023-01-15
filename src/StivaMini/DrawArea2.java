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
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class DrawArea2 extends JFrame {

	private Color color;
	private int currentLayer;
	private int numberOfLayers;
	private JPanel contentPane;
	private tools tool;
	private List <Color[][]> layer = new ArrayList<>();
	private JTable table;
	private int previewAngle;
	private int previewPixelSize;
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
		color = Color.BLACK;
		tool = tools.pencil;
		currentLayer = 0;
		numberOfLayers = 0;
		
		setTitle("STIVA MINI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 617);
		
		
		
		// ---------------------- DRAW MODE ----------------------
		
		
		MyCanvas canvas = new MyCanvas() {
			public void paint(Graphics g)
            {
				canvasUpdate(this, this.getGraphics());
            }
		};
		layer.add(new Color[canvas.getGridSize()][canvas.getGridSize()]);
		
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
		JButton btnLayerMoveDown = new JButton("\u2B9F");
		
		btnLayerMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLayerMoveDown.setEnabled(true);
				int index = table.getSelectedRow();
				String text1 = table.getModel().getValueAt(index, 0).toString();
				String text2 = table.getModel().getValueAt(index-1, 0).toString();
				table.getModel().setValueAt(text2, index, 0);
				table.getModel().setValueAt(text1, index-1, 0);
				table.setRowSelectionInterval(index-1, index-1);
				Collections.swap(layer, numberOfLayers-index, numberOfLayers-index+1);
				if(index-1 == 0)
					btnLayerMoveUp.setEnabled(false);
				currentLayer++;
				canvasUpdate(canvas, canvas.getGraphics());
			}
		});
		btnLayerMoveUp.setEnabled(false);
		panel_4.add(btnLayerMoveUp);
		
		
		btnLayerMoveDown.setEnabled(false);
		btnLayerMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLayerMoveUp.setEnabled(true);
				int index = table.getSelectedRow();
				String text1 = table.getModel().getValueAt(index, 0).toString();
				String text2 = table.getModel().getValueAt(index+1, 0).toString();
				table.getModel().setValueAt(text2, index, 0);
				table.getModel().setValueAt(text1, index+1, 0);
				table.setRowSelectionInterval(index+1, index+1);
				Collections.swap(layer, numberOfLayers-index, numberOfLayers-index-1);
				if(index+1 == table.getRowCount()-1)
					btnLayerMoveDown.setEnabled(false);
				currentLayer--;
				canvasUpdate(canvas, canvas.getGraphics());
			}
		});
		panel_4.add(btnLayerMoveDown);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnLayerAdd = new JButton("Add");
		btnLayerAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLayer(canvas);
				int index = table.getSelectedRow();
				btnLayerMoveUp.setEnabled(true);
				btnLayerMoveDown.setEnabled(true);
				if(index == 0)
					btnLayerMoveUp.setEnabled(false);
				if(index == numberOfLayers)
					btnLayerMoveDown.setEnabled(false);
			}
		});
		panel_5.add(btnLayerAdd);
		
		JButton btnLayerDelete = new JButton("Delete");
		btnLayerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteLayer(canvas);
				int index = table.getSelectedRow();
				btnLayerMoveUp.setEnabled(true);
				btnLayerMoveDown.setEnabled(true);
				if(index == 0)
					btnLayerMoveUp.setEnabled(false);
				if(index == numberOfLayers)
					btnLayerMoveDown.setEnabled(false);
			}
		});
		panel_5.add(btnLayerDelete);
		
		JLabel lblDrawLayers = new JLabel("Layers");
		panel_2.add(lblDrawLayers, BorderLayout.NORTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Layer 0"},
			},
			new String[] {
				"Layers"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table.setRowSelectionInterval(table.getSelectedRow(), table.getSelectedRow());
				int index = table.getSelectedRow();
				btnLayerMoveUp.setEnabled(true);
				btnLayerMoveDown.setEnabled(true);
				if(index == 0)
					btnLayerMoveUp.setEnabled(false);
				if(index == numberOfLayers)
					btnLayerMoveDown.setEnabled(false);
				currentLayer = numberOfLayers-index;
				canvasUpdate(canvas, canvas.getGraphics());
			}
		});
		table.setRowSelectionInterval(0, 0);
		panel_2.add(table, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panelDraw.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTools = new JLabel("Tools");
		panel_6.add(lblTools, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JButton btnDrawPencil = new JButton("Pencil ");
		JButton btnDrawFill = new JButton("   Fill   ");
		JButton btnDrawEraser = new JButton("Eraser");
		btnDrawPencil.setEnabled(false);
		btnDrawPencil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = tools.pencil;
				btnDrawPencil.setEnabled(false);
				btnDrawFill.setEnabled(true);
				btnDrawEraser.setEnabled(true);
			}
		});
		btnDrawFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = tools.fill;
				btnDrawPencil.setEnabled(true);
				btnDrawFill.setEnabled(false);
				btnDrawEraser.setEnabled(true);
			}
		});
		btnDrawEraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool = tools.eraser;
				btnDrawPencil.setEnabled(true);
				btnDrawFill.setEnabled(true);
				btnDrawEraser.setEnabled(false);
			}
		});
		panel_7.add(btnDrawPencil);
		panel_7.add(btnDrawFill);
		panel_7.add(btnDrawEraser);
		
		JLabel lblColor = new JLabel("Color");
		panel_7.add(lblColor);
		
		JButton btnColorPicker = new JButton("          ");
		btnColorPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "Color picker...", color);
				btnColorPicker.setBackground(color);
			}
		});
		btnColorPicker.setFocusable(false);
		btnColorPicker.setBackground(new Color(0, 0, 0));
		panel_7.add(btnColorPicker);
		
		Panel panel_8 = new Panel();
		panelDraw.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(null);
		
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Graphics g = canvas.getGraphics();
				drawPixel(canvas, g, e);
			}
		});
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Graphics g = canvas.getGraphics();
				if(tool != tools.fill)
					drawPixel(canvas, g, e);
				else
				{
					int unit = canvas.getUnitSize();
					int x = e.getX()/unit;
					int y = e.getY()/unit;
					if(x >= 0 && x < canvas.getGridSize() && y >= 0 && y < canvas.getGridSize() &&
						tool == tools.fill && layer.get(currentLayer)[x][y] != color) {
						fillColor(x, y, layer.get(currentLayer)[x][y]);
						canvasUpdate(canvas, g);
					}
				}
			}
		});
		canvas.setBackground(new Color(255, 255, 255));
		canvas.setBounds(10, 10, canvas.getCanvasSize(), 480);
		panel_8.add(canvas);

		
		
		// ---------------------- PREVIEW MODE ----------------------
		previewPixelSize = 12;
		previewAngle = 0;
		
		
		JPanel panelPreview = new JPanel();
		tabbedPane.addTab("Preview", null, panelPreview, null);
		panelPreview.setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		panelPreview.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPreviewMessage = new JLabel("...");
		panelButtons.add(lblPreviewMessage, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panelButtons.add(panel_1, BorderLayout.CENTER);
		
		JButton btnPreviewLeft = new JButton("\u2B9C");
		panel_1.add(btnPreviewLeft);
		
		JButton btnPreviewRight = new JButton("\u2B9E");
		panel_1.add(btnPreviewRight);
		
		JPanel panelCanvas = new JPanel();
		panelPreview.add(panelCanvas, BorderLayout.CENTER);
		panelCanvas.setLayout(new BoxLayout(panelCanvas, BoxLayout.X_AXIS));
		
		
		Canvas canvasPreview = new Canvas() {
			public void paint(Graphics g)
            {
				drawPreview(this);
            }
		};
		panelCanvas.add(canvasPreview);
		
		btnPreviewRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvasPreview.getGraphics().clearRect(0, 0, getWidth(), getHeight());
				previewAngle+=10;
				drawPreview(canvasPreview);
			}
		});
		btnPreviewLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvasPreview.getGraphics().clearRect(0, 0, getWidth(), getHeight());
				previewAngle-=10;
				drawPreview(canvasPreview);
			}
		});
	}
	
	public void drawPreview(Canvas canvas) {
		Graphics g = canvas.getGraphics();
		int gridLength = layer.get(0)[0].length;
		g.setColor(new Color(238, 238, 238));
		g.drawRect(0, 0, canvas.getWidth(), canvas.getHeight());

		for(int i=0; i<=numberOfLayers; i++) {
			for(int j=0; j<20; j++) {
				Image imageLayer = paintLayer(canvas, i);
				
				AffineTransform identity = new AffineTransform();
				AffineTransform trans = new AffineTransform();
				trans.setTransform(identity);
				trans.scale(1, 0.5);
				trans.translate(getWidth()/2-(previewPixelSize*gridLength/2), getHeight()*4/3-(previewPixelSize*gridLength)-i*20-j);
				trans.rotate(Math.toRadians(previewAngle), previewPixelSize*gridLength/2, previewPixelSize*gridLength/2);
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(imageLayer, trans, null);
			}
		}
	}
	
	public BufferedImage paintLayer(Canvas canvas, int layerNumber) {
		BufferedImage imageLayer = new BufferedImage(480, 480, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2Layer;
		g2Layer = (Graphics2D) imageLayer.getGraphics();
		g2Layer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		int gridLength = layer.get(0)[0].length;
		for(int i=0; i<gridLength; i++)
			for(int j=0; j<gridLength; j++) {
				if(layer.get(layerNumber)[i][j] != null)
					g2Layer.setPaint(layer.get(layerNumber)[i][j]);
				else
					g2Layer.setPaint(new Color(0, 0, 0, 0));
				g2Layer.fillRect(i*12, j*12, 12, 12);
			}

		
		return imageLayer;
	}
	
	
	
	
	public void drawPixel(MyCanvas canvas, Graphics g, MouseEvent e) {
		int unit = canvas.getUnitSize();
		int x = e.getX()/unit;
		int y = e.getY()/unit;
		if(x >= 0 && x < canvas.getGridSize() && y >= 0 && y < canvas.getGridSize())
		if(tool == tools.pencil && layer.get(currentLayer)[x][y] != color) {
			if(g.getColor() != color) g.setColor(color);
			g.fillRect(x*unit, y*unit, unit, unit);
			layer.get(currentLayer)[x][y] = color;
		}
		else if(tool == tools.eraser && layer.get(currentLayer)[x][y] != null) {
			if((x+y)%2 == 0)
				g.setColor(Color.lightGray);
			else
				g.setColor(Color.gray);
			g.fillRect(x*unit, y*unit, unit, unit);
			if(currentLayer>0 && layer.get(currentLayer-1)[x][y] != null)
			{
				Color prevColor = layer.get(currentLayer-1)[x][y];
				g.setColor(new Color(prevColor.getRed(), prevColor.getGreen(), prevColor.getBlue(), 100));
				g.fillRect(x*unit, y*unit, unit, unit);
			}
			layer.get(currentLayer)[x][y] = null;
		}
	}

	private void canvasUpdate(MyCanvas canvas, Graphics g) {
		int canvasSize = canvas.getGridSize();
		int unit = canvas.getUnitSize();
		for(int i=0; i<canvasSize; i++)
			for(int j=0; j<canvasSize; j++) {
				if((i+j)%2 == 0)
					g.setColor(Color.lightGray);
				else
					g.setColor(Color.gray);
				g.fillRect(i*unit, j*unit, unit, unit);
				if(currentLayer>0 && layer.get(currentLayer-1)[i][j] != null)
				{
					Color prevColor = layer.get(currentLayer-1)[i][j];
					g.setColor(new Color(prevColor.getRed(), prevColor.getGreen(), prevColor.getBlue(), 100));
					g.fillRect(i*unit, j*unit, unit, unit);
				}
				if(layer.get(currentLayer)[i][j] != null) {
					g.setColor(layer.get(currentLayer)[i][j]);
					g.fillRect(i*unit, j*unit, unit, unit);
				}
			}
		g.setColor(color);
	}

	private void fillColor(int x, int y, Color seekColor) {
		int canvasSize = layer.get(currentLayer)[0].length;
		if(x >= 0 && y >= 0 && x < canvasSize && y < canvasSize && layer.get(currentLayer)[x][y] == seekColor) {
			layer.get(currentLayer)[x][y] = color;
			fillColor(x+1, y, seekColor);
			fillColor(x-1, y, seekColor);
			fillColor(x, y+1, seekColor);
			fillColor(x, y-1, seekColor);
		}
	}
	
	public void addLayer(MyCanvas canvas) {
		Graphics g = canvas.getGraphics();
		layer.add(currentLayer+1, new Color[canvas.getGridSize()][canvas.getGridSize()]);
		currentLayer++;
		numberOfLayers++;
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		String data[] = {(String)("Layer "+currentLayer)};
		tableModel.addRow(data);
		tableModel.moveRow(tableModel.getRowCount()-1, tableModel.getRowCount()-1, 0);
		canvasUpdate(canvas, g);
	}
	
	public void deleteLayer(MyCanvas canvas) {
		if(numberOfLayers > 0) {
			Graphics g = canvas.getGraphics();
			layer.remove(currentLayer);
			if(currentLayer > 0)
				currentLayer--;
			numberOfLayers--;
			DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
			int currentIndex = table.getSelectedRow();
			if(currentIndex == table.getRowCount()-1)
				currentIndex = table.getRowCount()-2;
			tableModel.removeRow(table.getSelectedRow());
			table.setRowSelectionInterval(currentIndex, currentIndex);
			canvasUpdate(canvas, g);
		}
	}

	public int getNumberOfLayers() {
		return numberOfLayers;
	}
	public void nextLayer(MyCanvas canvas) {
		Graphics g = canvas.getGraphics();
		if(currentLayer < numberOfLayers)
			currentLayer++;
		canvasUpdate(canvas, g);
	}
	public void prevLayer(MyCanvas canvas) {
		Graphics g = canvas.getGraphics();
		if(currentLayer > 0)
			currentLayer--;
		canvasUpdate(canvas, g);
	}

	public int getCurrentLayer() {
		return currentLayer;
	}
}
