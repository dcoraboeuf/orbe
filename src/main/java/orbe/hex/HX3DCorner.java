/*
 * Created on 4 déc. 06
 */
package orbe.hex;

class HX3DCorner {

	public int x;

	public int y;

	public int z;

	public HX3DCorner() {
		x = y = z = 0;
	}

	public HX3DCorner(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * 
	 * @return java.lang.String
	 */
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
}
