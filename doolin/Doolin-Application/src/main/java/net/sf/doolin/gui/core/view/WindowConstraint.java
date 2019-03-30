/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

public class WindowConstraint {
	
	private boolean closeable = true;
	
	private boolean maximizable = true;
	
	private boolean iconifiable = true;
	
	private boolean resizable = true;

	public boolean isCloseable() {
		return closeable;
	}

	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}

	/**
	 * @return the maximizable
	 */
	public boolean isMaximizable() {
		return maximizable;
	}

	/**
	 * @param maximizable the maximizable to set
	 */
	public void setMaximizable(boolean maximizable) {
		this.maximizable = maximizable;
	}

	/**
	 * @return the iconifiable
	 */
	public boolean isIconifiable() {
		return iconifiable;
	}

	/**
	 * @param iconifiable the iconifiable to set
	 */
	public void setIconifiable(boolean iconifiable) {
		this.iconifiable = iconifiable;
	}

	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

}
