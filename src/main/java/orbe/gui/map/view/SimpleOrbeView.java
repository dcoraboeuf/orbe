/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.view;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D.Double;

import orbe.gui.map.core.ViewSettings;
import orbe.model.OrbeMap;

@Deprecated
public class SimpleOrbeView extends AbstractOrbeView {

	public SimpleOrbeView(OrbeMap map, ViewSettings settings) {
		super(map, settings);
	}

	public void paint(OrbeMap map, Graphics2D g, Double zone) {
		getRenderer().paint(map, getViewSettings(), g, zone);
	}

	public void refresh(OrbeMap map, Double zone) {
		// Direct drawing, no refresh is needed
	}

}
