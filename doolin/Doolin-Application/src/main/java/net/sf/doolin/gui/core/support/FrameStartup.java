/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.support;

import net.sf.doolin.gui.core.ApplicationManager;
import net.sf.doolin.gui.core.Startup;
import net.sf.doolin.gui.core.View;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Starts the application by launching a frame.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FrameStartup.java,v 1.3 2007/08/10 16:54:39 guinnessman Exp $
 */
public class FrameStartup implements Startup {
	
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(FrameStartup.class);
	
	/**
	 * View
	 */
	private View frame;
	
	/**
	 * Frame data factory
	 */
	private DataFactory frameDataFactory;
	
	/**
	 * Launches a frame.
	 * 
	 * @see net.sf.doolin.gui.core.Startup#start(net.sf.doolin.gui.core.ApplicationManager)
	 */
	public void start(ApplicationManager applicationManager) {
		log.info("Startup using frame " + frame.getId());
		Object frameData = null;
		if (frameDataFactory != null) {
			frameData = frameDataFactory.createData();
		}
		GUIUtils.getViewManager().openFrame(frame, frameData);
	}

	/**
	 * @return Returns the frame.
	 */
	public View getFrame() {
		return frame;
	}

	/**
	 * @param frame The frame to set.
	 */
	public void setFrame(View frame) {
		this.frame = frame;
	}

	public DataFactory getFrameDataFactory() {
		return frameDataFactory;
	}

	public void setFrameDataFactory(DataFactory frameDataFactory) {
		this.frameDataFactory = frameDataFactory;
	}

}
