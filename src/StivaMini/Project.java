package StivaMini;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Project implements Serializable{
	private List <Color[][]> layer = new ArrayList<>();
	private boolean newProject = true;
	private boolean modified = false;
	private int currentLayer;
	private int numberOfLayers;
	private DefaultTableModel tableModel;
	private String path;
	
	public Project(List<Color[][]> layer, boolean newProject, boolean modified, int currentLayer, int numberOfLayers,
			 DefaultTableModel tableModel, String path) {
		this.layer = layer;
		this.newProject = newProject;
		this.modified = modified;
		this.currentLayer = currentLayer;
		this.numberOfLayers = numberOfLayers;
		this.tableModel = tableModel;
		this.path = path;
	}

	public List<Color[][]> getLayer() {
		return layer;
	}

	public void setLayer(List<Color[][]> layer) {
		this.layer = layer;
	}

	public boolean isNewProject() {
		return newProject;
	}

	public void setNewProject(boolean newProject) {
		this.newProject = newProject;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public int getCurrentLayer() {
		return currentLayer;
	}

	public void setCurrentLayer(int currentLayer) {
		this.currentLayer = currentLayer;
	}

	public int getNumberOfLayers() {
		return numberOfLayers;
	}

	public void setNumberOfLayers(int numberOfLayers) {
		this.numberOfLayers = numberOfLayers;
	}


	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	
	
}
