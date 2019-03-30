/*
 * Created on Nov 29, 2006
 */
package orbe.hex;

/**
 * D�finition d'un coin d'hexagone. Attention, deux d�finitions peuvent en
 * r�alit� d�signer le m�me coin physique. En r�alit�, un m�me coin peut �tre
 * d�sign� par trois instances diff�rentes de <code>HXCorner</code>, selon
 * l'hex de r�f�rence.
 * 
 * @author Damien Coraboeuf
 * @version $Id: HXCorner.java,v 1.2 2006/12/04 15:35:09 guinnessman Exp $
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
