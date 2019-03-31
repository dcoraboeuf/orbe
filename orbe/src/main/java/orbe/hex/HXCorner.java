/*
 * Created on Nov 29, 2006
 */
package orbe.hex;

/**
 * Définition d'un coin d'hexagone. Attention, deux définitions peuvent en
 * réalité désigner le même coin physique. En réalité, un même coin peut être
 * désigné par trois instances différentes de <code>HXCorner</code>, selon
 * l'hex de référence.
 * 
 * @author Damien Coraboeuf
 */
public class HXCorner {

	public HXCorner() {
	}

	public HXCorner(HXPoint p, int corner) {
		this.hex = new HXPoint(p);
		this.corner = corner;
		normalize();
	}

	public HXCorner(HXCorner c) {
		this.hex = new HXPoint(c.hex);
		this.corner = c.corner;
		normalize();
	}

	public HXCorner(int i, int j, int corner) {
		this.hex = new HXPoint(i, j);
		this.corner = corner;
		normalize();
	}

	public void normalize() {
		switch (corner) {
		case 2:
			corner = 0;
			hex = HXGeom.offsetHex(hex, 2);
			break;
		case 3:
			corner = 1;
			hex = HXGeom.offsetHex(hex, 3);
			break;
		case 4:
			corner = 0;
			hex = HXGeom.offsetHex(hex, 3);
			break;
		case 5:
			corner = 1;
			hex = HXGeom.offsetHex(hex, 4);
			break;
		case 0:
		case 1:
		default:
			// Nothing to do
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof HXCorner) {
			HXCorner item = (HXCorner) obj;
			item.normalize();
			this.normalize();
			return (item.hex.equals(this.hex) && (item.corner == this.corner));
		} else {
			return false;
		}
	}

	/**
	 * Hex de r�f�rence
	 */
	public HXPoint hex;

	/**
	 * Indicateur du coin
	 */
	public int corner;

}
