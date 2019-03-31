/*
 * Created on Oct 27, 2006
 */
package orbe.hex;

public class HX3D {

	public HX3D() {
		x = y = z = 0;
	}

	public HX3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString() {
		return (new StringBuilder("(")).append(x).append(",").append(y).append(",").append(z).append(")").toString();
	}

	public int x;

	public int y;

	public int z;
}
