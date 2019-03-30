/*
 * Created on 19 oct. 06
 */
package orbe.hex;

import java.io.Serializable;

public class HXPoint implements Serializable {

	private static final long serialVersionUID = 1;

	public int i;

	public int j;

	public HXPoint() {
		i = j = 0;
	}

	public HXPoint(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public HXPoint(HXPoint p) {
		this.i = p.i;
		this.j = p.j;
	}

	@Override
	public String toString() {
		return "(" + i + "," + j + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof HXPoint) {
			HXPoint item = (HXPoint) obj;
			return (this.i == item.i && this.j == item.j);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return i * 1000 + j;
	}
}
