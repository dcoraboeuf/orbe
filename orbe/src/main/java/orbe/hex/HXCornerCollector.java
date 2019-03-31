/*
 * Created on Nov 6, 2006
 */
package orbe.hex;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to collect a list of corner for a edge line.
 * 
 * @author Guinness Man
 * @version $Id: HXCornerCollector.java,v 1.1 2006/12/04 18:15:50 guinnessman Exp $
 */
public class HXCornerCollector implements HXCornerIterator {

	private ArrayList<HXCorner> corners = new ArrayList<HXCorner>();

	private boolean controlDuplicates;

	public void clear() {
		corners.clear();
	}

	public List<HXCorner> getCorners() {
		return corners;
	}

	public void forCorner(HXCorner c) {
		if (controlDuplicates && corners.contains(c)) {
			throw new IllegalStateException("Corner has already been added: " + c);
		}
		corners.add(c);
	}

	public boolean isControlDuplicates() {
		return controlDuplicates;
	}

	public void setControlDuplicates(boolean controlDuplicates) {
		this.controlDuplicates = controlDuplicates;
	}

}
