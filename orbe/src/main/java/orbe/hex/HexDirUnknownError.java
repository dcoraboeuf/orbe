/*
 * Created on Oct 26, 2006
 */
package orbe.hex;


public class HexDirUnknownError extends HexError {

	public HexDirUnknownError(int dir) {
		super ("Unknown hex direction {0}", dir);
	}
	
}

