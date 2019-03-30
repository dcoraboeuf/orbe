/*
 * Created on Jul 27, 2007
 */
package net.sf.doolin.gui.swing;

import javax.swing.SwingConstants;

/**
 * Horizontal alignment values
 * 
 * @author Damien Coraboeuf
 * @version $Id: HAlignment.java,v 1.1 2007/07/31 15:33:05 guinnessman Exp $
 */
public enum HAlignment {
	/**
	 * @see SwingConstants#LEFT
	 */
	LEFT(SwingConstants.LEFT),
	/**
	 * @see SwingConstants#RIGHT
	 */
	RIGHT(SwingConstants.RIGHT),
	/**
	 * @see SwingConstants#LEADING
	 */
	LEADING(SwingConstants.LEADING),
	/**
	 * @see SwingConstants#TRAILING
	 */
	TRAILING(SwingConstants.TRAILING),
	/**
	 * @see SwingConstants#CENTER
	 */
	CENTER(SwingConstants.CENTER);

	private int alignment;

	private HAlignment(int align) {
		alignment = align;
	}

	/**
	 * Corresponding Swing alignment
	 * 
	 * @return Alignment constant
	 * @see SwingConstants
	 */
	public int getAlignment() {
		return alignment;
	}
}
