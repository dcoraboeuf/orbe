/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree;

/**
 * Abstract root ancestor for children definition.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractChildren.java,v 1.2 2007/08/01 16:07:10 guinnessman Exp $
 */
public abstract class AbstractChildren implements Children {

	private String id;

	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            Node id for the children
	 */
	public void setId(String id) {
		this.id = id;
	}

}
