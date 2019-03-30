/*
 * Created on Jul 26, 2007
 */
package net.sf.doolin.gui.table;

/**
 * Defines the policy to apply on the table model when one cell is updated.
 * 
 * @author Damien Coraboeuf
 * @version $Id: UpdatePolicy.java,v 1.1 2007/07/31 15:33:03 guinnessman Exp $
 */
public enum UpdatePolicy {

	/**
	 * The whole table is considered as changed after a cell has been updated.
	 */
	TABLE,
	/**
	 * The whole row is considered as changed after a cell has been updated.
	 */
	ROW,
	/**
	 * The whole column is considered as changed after a cell has been updated.
	 */
	COLUMN,
	/**
	 * Only the call is concerned by the change
	 */
	CELL;

}
