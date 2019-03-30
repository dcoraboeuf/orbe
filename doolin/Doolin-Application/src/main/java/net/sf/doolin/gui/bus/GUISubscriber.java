/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.bus;

/**
 * This interface is responsible for the registration of a GUI component (view,
 * field, etc) to the Bus.
 * 
 * @see net.sf.doolin.bus.Bus
 * @author Damien Coraboeuf
 * @version $Id: GUISubscriber.java,v 1.1 2007/08/14 11:49:01 guinnessman Exp $
 * 
 * @param <T>
 *            Type of component to register.
 */
public interface GUISubscriber<T> {

	/**
	 * Registers a component.
	 * 
	 * @param component
	 *            Component to register.
	 */
	void register(T component);

}
