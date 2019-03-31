/*
 * Created on Oct 20, 2006
 */
package orbe.hex;

public class HXRect {
	private HXPoint topLeft;

	private HXPoint bottomRight;

	public HXRect() {
		topLeft = new HXPoint();
		bottomRight = new HXPoint();
	}

	public HXRect(HXPoint tl, HXPoint br) {
		topLeft = new HXPoint(tl);
		bottomRight = new HXPoint(br);
	}

	public HXRect(int x1, int y1, int x2, int y2) {
		topLeft = new HXPoint(x1, y1);
		bottomRight = new HXPoint(x2, y2);
	}

	public HXRect(HXRect rect) {
		topLeft = new HXPoint(rect.getTopLeft());
		bottomRight = new HXPoint(rect.getBottomRight());
	}

	public HXPoint getTopLeft() {
		return topLeft;
	}

	public HXPoint getBottomRight() {
		return bottomRight;
	}

	public HXRect expand(int d) {
		HXRect rect = new HXRect(this);
		rect.topLeft = new HXPoint(rect.topLeft.i - d, rect.topLeft.j - d);
		rect.bottomRight = new HXPoint(rect.bottomRight.i + d, rect.bottomRight.j + d);
		return rect;
	}

	public int getMinI() {
		return topLeft.i;
	}

	public int getMinJ() {
		return topLeft.j;
	}

	public int getMaxI() {
		return bottomRight.i;
	}

	public int getMaxJ() {
		return bottomRight.j;
	}

	public HXRect intersect(HXRect bounds) {
		HXRect rect = new HXRect(this);
		rect.topLeft = new HXPoint(Math.max(rect.getMinI(), bounds.getMinI()), Math.max(rect.getMinJ(), bounds.getMinJ()));
		rect.bottomRight = new HXPoint(Math.min(rect.getMaxI(), bounds.getMaxI()), Math.min(rect.getMaxJ(), bounds.getMaxJ()));
		return rect;
	}

	@Override
	public String toString() {
		return topLeft + "," + bottomRight;
	}
}
