/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.renderer;

import net.sf.doolin.gui.service.GUIStrings;
import orbe.hex.HXRect;
import orbe.model.OrbeMap;

import org.apache.commons.lang.ObjectUtils;

public abstract class AbstractLayer implements OrbeLayer {

	public String getTitle() {
		return GUIStrings.get("Layer." + getId());
	}
	
	public final boolean isVisible(OrbeMap map) {
		return map.getSettings().isLayerVisible(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof AbstractLayer) {
			OrbeLayer item = (OrbeLayer) obj;
			return ObjectUtils.equals(this.getId(), item.getId());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return ObjectUtils.hashCode(getId());
	}

	protected HXRect getHXMapBounds(OrbeMap map) {
		return new HXRect(0, 0, map.getWidth() - 1, map.getHeight() - 1);
	}

}
