/*
 * Created on 3 oct. 06
 */
package orbe.gui.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import orbe.gui.map.renderer.Layer;
import orbe.model.OrbeSettings;

/**
 * Encapsulation d'OrbeSettings pour edition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormSettings.java,v 1.1 2006/10/04 06:26:46 guinnessman Exp $
 */
public class FormSettings {

	private OrbeSettings settings;

	public FormSettings(OrbeSettings settings) {
		this.settings = settings;
	}

	public OrbeSettings getSettings() {
		return settings;
	}

	public List<Layer> getVisibleLayers() {
		return new ArrayList<Layer>(settings.getVisibleLayers());
	}

	public void setVisibleLayers(List<Layer> visibleLayers) {
		settings.setVisibleLayers(new HashSet<Layer>(visibleLayers));
	}

}
