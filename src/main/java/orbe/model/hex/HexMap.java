/*
 * Created on Oct 23, 2006
 */
package orbe.model.hex;

import orbe.hex.HXPoint;

public interface HexMap {

	Hex getHex(int i, int j);

	void setHex(int i, int j, Hex hex);

	void iterate(HexIterator iterator);

	Hex getHex(HXPoint hxp);

	void setFlag(int tool, boolean b);

}
