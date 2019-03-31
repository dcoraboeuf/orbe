/*
 * Created on Oct 3, 2006
 */
package orbe.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import orbe.gui.map.renderer.Layer;
import orbe.model.style.DefaultRepositoryHexTerrain;
import orbe.model.style.RepositoryHexTerrain;
import orbe.model.style.RepositoryStyleLine;
import orbe.model.style.RepositoryTextStyle;

import org.apache.commons.lang.StringUtils;

/**
 * Settings pour une carte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeSettings.java,v 1.14 2006/11/29 16:45:31 guinnessman Exp $
 */
public class OrbeSettings implements Serializable {

	private static final long serialVersionUID = 1;

	private Scale scale = new Scale();

	private Color background = Color.WHITE;

	private Grid grid = new Grid();

	private LinkedHashSet<Layer> visibleLayers = new LinkedHashSet<Layer>();

	private RepositoryHexTerrain terrains = DefaultRepositoryHexTerrain.createRepository();
	
	private RepositoryTextStyle textStyles = RepositoryTextStyle.createDefaultRepository();
	
	private RepositoryStyleLine styleLineList = RepositoryStyleLine.createDefaultRepository();

	public OrbeSettings() {
		for (Layer layer : Layer.values()) {
			if (layer.isDefaultVisible()) {
				visibleLayers.add(layer);
			}
		}
	}

	public Scale getScale() {
		return scale;
	}

	public void setScale(Scale scale) {
		this.scale = scale;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public RepositoryHexTerrain getTerrains() {
		return terrains;
	}

	public void setTerrains(RepositoryHexTerrain terrains) {
		this.terrains = terrains;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public boolean isLayerVisible(Layer id) {
		return visibleLayers.contains(id);
	}

	public String getEncodedVisibleLayers() {
		return StringUtils.join(visibleLayers.iterator(), ";");
	}

	public void setEncodedVisibleLayers(String layers) {
		visibleLayers = new LinkedHashSet<Layer>();
		String[] tokens = StringUtils.split(layers, ";");
		for (String id : tokens) {
			visibleLayers.add(Layer.valueOf(id));
		}
	}

	public Set<Layer> getVisibleLayers() {
		return visibleLayers;
	}

	public void setVisibleLayers(Set<Layer> visibleLayers) {
		this.visibleLayers.clear();
		this.visibleLayers.addAll(visibleLayers);
	}

	public RepositoryTextStyle getTextStyles() {
		return textStyles;
	}

	public void setTextStyles(RepositoryTextStyle textStyles) {
		this.textStyles = textStyles;
	}

	public RepositoryStyleLine getStyleLineList() {
		return styleLineList;
	}

	public void setStyleLineList(RepositoryStyleLine styleLineList) {
		this.styleLineList = styleLineList;
	}
}
