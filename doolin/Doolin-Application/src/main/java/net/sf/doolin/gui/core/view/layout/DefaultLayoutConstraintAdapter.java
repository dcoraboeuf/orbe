/*
 * Created on Aug 10, 2007
 */
package net.sf.doolin.gui.core.view.layout;

/**
 * This implementation uses the configurated constraint as the actual
 * constraint.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultLayoutConstraintAdapter.java,v 1.1 2007/08/10 16:54:41 guinnessman Exp $
 * @param <C>
 *            Type of constraint
 */
public class DefaultLayoutConstraintAdapter<C> implements LayoutConstraintAdapter<C, C> {

	public C adapt(C constraint) {
		return constraint;
	}

}
