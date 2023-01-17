package StivaMini;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class DrawArea extends JFrame {

	private Color color;
	private int currentLayer;
	private int numberOfLayers;
	private JPanel contentPane;
	private tools tool;
	private List <Color[][]> layer = new ArrayList<>();
	private JTable table;
	private int previewAngle;
	private int previewPixelSize;
	private int newSize;
	private String path;
	
	private boolean newProject = true;
	private boolean modified = false;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawArea frame = new DrawArea();
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
	public DrawArea() {
		color = Color.BLACK;
		tool = tools.pencil;
		currentLayer = 0;
		numberOfLayers = 0;
		
		setTitle("STIVA MINI");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 746, 617);
		
		
		
		// ---------------------- DRAW MODE ----------------------
		
		
		MyCanvas canvas = new MyCanvas(480, 16) {
			public void paint(Graphics g)
            {
				canvasUpdate(this, this.getGraphics());
            }
		};
		layer.add(new Color[canvas.getGridSize()][canvas.getGridSize()]);
		
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNewMenu = new JMenu("New...");
		mnFile.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("8x8");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modified == true) {
					String[] options = {"Save", "Don't Save", "Cancel"};
					int response = JOptionPane.showOptionDialog(null, "Do you want to save changes?",
			                "Unsaved changes",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(response == 1) {
						resetCanvas(canvas, 8);
						modified = false;
						newProject = true;
					}
					if(response == 0) {
						if(newProject == true)
							saveas();
						else
							save();
						resetCanvas(canvas, 8);
						modified = false;
						newProject = true;
					}
				}
				else {
					resetCanvas(canvas, 8);
					modified = false;
					newProject = true;
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("16x16");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modified == true) {
					String[] options = {"Save", "Don't Save", "Cancel"};
					int response = JOptionPane.showOptionDialog(null, "Do you want to save changes?",
			                "Unsaved changes",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(response == 1) {
						resetCanvas(canvas, 16);
						modified = false;
						newProject = true;
					}
					if(response == 0) {
						if(newProject == true)
							saveas();
						else
							save();
						resetCanvas(canvas, 16);
						modified = false;
						newProject = true;
					}
				}
				else {
					resetCanvas(canvas, 16);
					modified = false;
					newProject = true;
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("32x32");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modified == true) {
					String[] options = {"Save", "Don't Save", "Cancel"};
					int response = JOptionPane.showOptionDialog(null, "Do you want to save changes?",
			                "Unsaved changes",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(response == 1) {
						resetCanvas(canvas, 32);
						modified = false;
						newProject = true;
					}
					if(response == 0) {
						if(newProject == true)
							saveas();
						else
							save();
						resetCanvas(canvas, 32);
						modified = false;
						newProject = true;
					}
				}
				else {
					resetCanvas(canvas, 32);
					modified = false;
					newProject = true;
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JButton btnLayerMoveUp = new JButton("\u2B9D");
		JButton btnLayerMoveDown = new JButton("\u2B9F");
		btnLayerMoveUp.setEnabled(false);
		btnLayerMoveDown.setEnabled(false);
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showOpenDialog(new JFrame());
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToOpen = fileChooser.getSelectedFile();
				    if(fileToOpen.toString().substring(fileToOpen.toString().length() - 4).equals(".stv")) {
					    try {
							readFile(fileToOpen, canvas);
							invalidate();
							validate();
							repaint();
							canvas.reset(480, layer.get(0)[0].length);
							currentLayer = 0;
							DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
							tableModel.setRowCount(0);
							for(int i=numberOfLayers; i>=0; i--) {
								String data[] = {(String)("Layer "+i)};
								tableModel.addRow(data);
							}
							table.setRowSelectionInterval(numberOfLayers, numberOfLayers);
							canvasUpdate(canvas, canvas.getGraphics());
							btnLayerMoveDown.setEnabled(false);
						} catch (ClassNotFoundException | IOException e1) {
							e1.printStackTrace();
						}
				    }
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modified == true) {
					if(newProject == true)
						saveas();
					else
						save();
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveas();
			}
		});
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
		
		JPanel panel_2 = new JPanel();
		panelDraw.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
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
		
		panel_4.add(btnLayerMoveUp);
		
		
		
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
				Color newColor = JColorChooser.showDialog(null, "Color picker...", color);
				if(newColor != null)
					color = newColor;
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
				modified = true;
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
						modified = true;
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
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(modified == true) {
					String[] options = {"Save", "Don't Save", "Cancel"};
					int response = JOptionPane.showOptionDialog(null, "Do you want to save changes?",
			                "Unsaved changes",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(response == 1)
						System.exit(0);
					if(response == 0) {
						if(newProject == true)
							saveas();
						else
							save();
						System.exit(0);
					}
				}
				else
					System.exit(0);
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
	
	public void resetCanvas(MyCanvas canvas, int newSize) {
		int n = numberOfLayers;
		for(int i=0; i<n; i++)
			deleteLayer(canvas);
		canvas.reset(480, newSize);
		numberOfLayers = 0;
		currentLayer = 0;
		layer.remove(0);
		layer.add(new Color[newSize][newSize]);
		canvas.update(canvas.getGraphics());
		table.getModel().setValueAt("Layer 0",0,0);
	}
	
	public void writeFile(String filepath, JTable table) {
		 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            TableModel model = (TableModel)table.getModel();
            objectOut.writeObject(new Project(new ArrayList<Color[][]>(layer), newProject, modified, currentLayer, numberOfLayers, model, path));
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public void readFile(File file, MyCanvas canvas) throws IOException, ClassNotFoundException {
        Project result = null;

        try {
	        FileInputStream fis = new FileInputStream(file);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        result = (Project) ois.readObject();
	        layer = result.getLayer();
	        newProject = result.isNewProject();
	        modified = result.isModified();
	        currentLayer = result.getCurrentLayer();
	        numberOfLayers = result.getNumberOfLayers();
	        path = result.getPath();

        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

	public void saveas() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(new JFrame());
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			modified = false;
		    newProject = false;
		    File fileToSave = fileChooser.getSelectedFile();
		    if(fileToSave.getAbsolutePath()+".stv" == path)
		    	save();
		    else {
			    path = fileToSave.getAbsolutePath()+".stv";
			    modified = false;
				newProject = false;
			    writeFile(path, table);
		    }
		}
	}
	
	public void save() {
		modified = false;
		newProject = false;
		writeFile(path, table);
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
	
	public boolean isModified() {
		return modified;
	}
}
