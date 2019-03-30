/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.Preferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultSize extends AbstractSize {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DefaultSize.class);

	private int width = 100;

	private int height = 100;

	private boolean pack;

	private boolean maximized = false;

	private boolean saveInPrefs = false;

	public int getHeight() {
		return height;
	}

	public void setHeight(int heigth) {
		this.height = heigth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setSize(View view, Component component) {
		if (component instanceof Window) {
			Window window = (Window) component;
			setWindowSize(view, window);
		} else if (component instanceof JInternalFrame) {
			JInternalFrame frame = (JInternalFrame) component;
			setInternalFrameSize(view, frame);
		} else {
			log.error("Cannot set the size to this object: " + component.getClass().getName());
		}
	}

	protected void setInternalFrameSize(View view, JInternalFrame frame) {
		// Preferences
		if (saveInPrefs) {
			String prefKey = view.getId() + ".size";
			Preferences preferences = GUIUtils.getPreferences();
			width = preferences.getInt(prefKey + ".width", width);
			height = preferences.getInt(prefKey + ".height", height);
		}
		// Pack
		if (pack) {
			frame.pack();
		} else {
			// Set the size
			if (width == 0 || height == 0) {
				frame.pack();
			} else {
				Dimension dimension = new Dimension(width, height);
				frame.setSize(dimension);
			}
			// Maximized ?
			if (maximized) {
				try {
					frame.setMaximum(true);
				} catch (PropertyVetoException ex) {
					log.error("Canot maximize the internal frame", ex);
				}
			}
		}
	}

	protected void setWindowSize(View view, Window window) {
		// Preferences
		if (saveInPrefs) {
			String prefKey = view.getId() + ".size";
			Preferences preferences = GUIUtils.getPreferences();
			width = preferences.getInt(prefKey + ".width", width);
			height = preferences.getInt(prefKey + ".height", height);
			maximized = preferences.getBoolean(prefKey + ".maximized", false);
		}
		// Pack
		if (pack) {
			window.pack();
		}
		// Simple size
		else {
			window.setSize(width, height);
		}
		// Zoom (frame only)
		if (window instanceof Frame) {
			Frame frame = (Frame) window;
			if (maximized) {
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			}
		}
	}

	public boolean isMaximized() {
		return maximized;
	}

	public void setMaximized(boolean maximized) {
		this.maximized = maximized;
	}

	public boolean isSaveInPrefs() {
		return saveInPrefs;
	}

	public void setSaveInPrefs(boolean saveInPrefs) {
		this.saveInPrefs = saveInPrefs;
	}

	public boolean isPack() {
		return pack;
	}

	public void setPack(boolean pack) {
		this.pack = pack;
	}

	public void saveSize(View view, Component component) {
		if (saveInPrefs) {
			log.debug("Save size in prefs for " + view.getId());
			if (component instanceof Window) {
				Window window = (Window) component;
				saveWindowSize(view, window);
			} else if (component instanceof JInternalFrame) {
				JInternalFrame frame = (JInternalFrame) component;
				saveInternalFrameSize(view, frame);
			} else {
				log.error("Cannot set the size to this object: " + component.getClass().getName());
			}
			Preferences preferences = GUIUtils.getPreferences();
			String prefKey = view.getId() + ".size";
			preferences.setInt(prefKey + ".width", width);
			preferences.setInt(prefKey + ".height", height);
			preferences.setBoolean(prefKey + ".maximized", maximized);
			preferences.save();
		}
	}

	protected void saveWindowSize(View view, Window window) {
		width = window.getWidth();
		height = window.getHeight();
		maximized = false;
		if (window instanceof Frame) {
			Frame frame = (Frame) window;
			maximized = (frame.getExtendedState() == Frame.MAXIMIZED_BOTH);
		}
	}

	protected void saveInternalFrameSize(View view, JInternalFrame frame) {
		width = frame.getWidth();
		height = frame.getHeight();
		maximized = frame.isMaximum();
	}

}
