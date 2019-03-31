/*
 * Created on Nov 6, 2006
 */
package orbe.hex;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to collect a list of points for a hex line.
 * 
 * @author Guinness Man
 * @version $Id: HXPointCollector.java,v 1.3 2006/11/06 13:07:18 guinnessman Exp $
 */
public class HXPointCollector implements HXPointIterator {

	private ArrayList<HXPoint> points = new ArrayList<HXPoint>();

	private boolean controlDuplicates;

	public void clear() {
		points.clear();
	}

	public List<HXPoint> getPoints() {
		return points;
	}

	public void forHX(HXPoint hxp) {
		if (controlDuplicates && points.contains(hxp)) {
			throw new IllegalStateException("Point has already been added: " + hxp);
		}
		points.add(hxp);
	}

	public boolean isControlDuplicates() {
		return controlDuplicates;
	}

	public void setControlDuplicates(boolean controlDuplicates) {
		this.controlDuplicates = controlDuplicates;
	}

}
