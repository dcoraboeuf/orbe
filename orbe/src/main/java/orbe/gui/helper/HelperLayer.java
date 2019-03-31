/*
 * Created on Oct 25, 2006
 */
package orbe.gui.helper;

import java.util.Arrays;
import java.util.List;

import orbe.gui.map.renderer.Layer;

public class HelperLayer {

	public List<Layer> getLayers() {
		return Arrays.asList(Layer.values());
	}

}
