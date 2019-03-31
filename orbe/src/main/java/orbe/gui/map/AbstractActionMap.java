/*
 * Created on 3 oct. 06
 */
package orbe.gui.map;

import orbe.gui.main.AbstractActionMain;

public abstract class AbstractActionMap extends AbstractActionMain {

	/**
	 * Default constructor.
	 */
	public AbstractActionMap() {
		setEnabled(false);
	}

	@Override
	protected void onContextChanged() {
		setEnabled(getContext() != null);
	}
}

