/*
 * Created on Oct 26, 2006
 */
package orbe.hex;

import java.text.MessageFormat;

public class HexError extends RuntimeException {

	public HexError(String pattern, Object... params) {
		super(MessageFormat.format(pattern, params));
	}

}
