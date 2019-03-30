/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.service.support;

import net.sf.doolin.gui.service.Memento;
import net.sf.doolin.gui.service.Preferences;

/**
 * Root ancestor for preferences implementations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractPreferences.java,v 1.1 2007/08/10 16:54:38 guinnessman
 *          Exp $
 */
public abstract class AbstractPreferences implements Preferences {

	/**
	 * @see Memento#restoreMemento(Preferences)
	 * @see net.sf.doolin.gui.service.Preferences#restoreMemento(net.sf.doolin.gui.service.Memento)
	 */
	public void restoreMemento(Memento memento) {
		memento.restoreMemento(this);
	}

	/**
	 * @see Memento#saveMemento(Preferences)
	 * @see net.sf.doolin.gui.service.Preferences#saveMemento(net.sf.doolin.gui.service.Memento)
	 */
	public void saveMemento(Memento memento) {
		memento.saveMemento(this);
	}

}
