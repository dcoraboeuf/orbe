/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

import java.math.BigDecimal;

/**
 * The zoom factor has changed.
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageViewZoom.java,v 1.1 2006/11/07 14:22:12 guinnessman Exp $
 */
public class OrbeMessageViewZoom extends OrbeSimpleMessage<BigDecimal> {

	public OrbeMessageViewZoom(BigDecimal value) {
		super(value);
	}

}

