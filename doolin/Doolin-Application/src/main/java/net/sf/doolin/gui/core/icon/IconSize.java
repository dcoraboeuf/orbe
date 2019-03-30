/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.icon;

/**
 * List of available sizes for icons.
 * 
 * @author Damien Coraboeuf
 * @version $Id: IconSize.java,v 1.2 2007/07/31 15:59:32 guinnessman Exp $
 */
public enum IconSize {
	/**
	 * Mini icon ({@value})
	 */
	MINI(12),
	/**
	 * Small icon ({@value})
	 */
	SMALL(16),
	/**
	 * Medium icon ({@value})
	 */
	MEDIUM(24),
	/**
	 * Large icon ({@value})
	 */
	LARGE(32),
	/**
	 * Big icon ({@value})
	 */
	BIG(64);
	/**
	 * Preferred size
	 */
	private int preferredSize;

	/**
	 * Constructor
	 */
	IconSize(int aPreferredSize) {
		preferredSize = aPreferredSize;
	}

	/**
	 * @return Preferred size in pixels
	 */
	public int getPreferredSize() {
		return preferredSize;
	}

	/**
	 * @return Smaller size or <code>null</code> if not available
	 */
	public IconSize getSmallerSize() {
		int ordinal = this.ordinal();
		if (ordinal == 0) {
			return null;
		} else {
			return IconSize.values()[ordinal - 1];
		}
	}

	/**
	 * @return Bigger size or <code>null</code> if not available
	 */
	public IconSize getBiggerSize() {
		IconSize[] sizes = IconSize.values();
		int count = sizes.length;
		int ordinal = this.ordinal();
		if (ordinal < count - 1) {
			return sizes[ordinal + 1];
		} else {
			return null;
		}
	}

}
