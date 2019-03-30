/*
 * Created on Nov 7, 2006
 */
package orbe.gui.message;

import orbe.gui.context.OrbeContext;

public class OrbeMessageContextChanged extends OrbeSimpleMessage<OrbeContext> {

	public OrbeMessageContextChanged(OrbeContext value) {
		super(value);
	}

}
