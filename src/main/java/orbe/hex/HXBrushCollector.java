/*
 * Created on Oct 27, 2006
 */
package orbe.hex;

import java.util.HashSet;
import java.util.Set;

public class HXBrushCollector implements HXPointIterator {

	public HXBrushCollector(int size) {
		collection = new HashSet<HXPoint>();
		this.size = size;
	}

	public void forHX(HXPoint centre) {
		Set<HXPoint> locs = HXGeom.getHexAround(centre, size);
		collection.addAll(locs);
	}

	private int size;

	private Set<HXPoint> collection;

	public Set<HXPoint> getLocs() {
		return collection;
	}
}
