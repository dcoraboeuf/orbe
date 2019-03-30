/*
 * Created on Nov 10, 2006
 */
package net.sf.doolin.bus;

/**
 * Level of priority for subscription.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Priority.java,v 1.2 2007/07/31 15:33:12 guinnessman Exp $
 */
public enum Priority {
	/**
	 * Lowest priority
	 */
	LOWEST,
	/**
	 * Low priority
	 */
	LOW,
	/**
	 * Normal (default) priority
	 */
	NORMAL,
	/**
	 * High priority
	 */
	HIGH,
	/**
	 * Highest priority
	 */
	HIGHEST;
}
