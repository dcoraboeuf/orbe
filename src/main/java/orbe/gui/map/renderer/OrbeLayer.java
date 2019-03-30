/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.renderer;

import orbe.gui.map.core.OrbeRenderer;
import orbe.model.OrbeMap;

public interface OrbeLayer extends OrbeRenderer {

	Layer getId();
	
	String getTitle();

	boolean isVisible(OrbeMap map);
	
}

