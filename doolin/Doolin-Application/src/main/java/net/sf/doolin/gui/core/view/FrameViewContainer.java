/*
 * Created on Jul 16, 2007
 */
package net.sf.doolin.gui.core.view;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionExit;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.SwingFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This container is using a JFrame to display a view.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FrameViewContainer.java,v 1.6 2007/08/10 16:54:37 guinnessman
 *          Exp $
 */
public class FrameViewContainer extends AbstractFrameViewContainer {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(FrameViewContainer.class);

	private JFrame frame;

	@Override
	protected void build() {
		super.build();
		// Creates the frame
		frame = new JFrame();
		// Closing action
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Action closeAction = getView().getCloseAction();
		// Default close action
		if (closeAction == null) {
			closeAction = new ActionExit();
		}
		// Setup of the close
		final Action actualCloseAction = closeAction;
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				getView().execute(actualCloseAction);
			}

		});
		// Menubar
		Menubar menubar = getView().getMenubar();
		if (menubar != null) {
			// Get the Swing factory
			SwingFactory swingFactory = GUIUtils.getSwingFactory();
			// Builds the menubar
			swingFactory.setMenubar(getView(), frame);
		}
		// Toolbars
		List<Toolbar> toolbars = getView().getToolbars();
		if (toolbars != null && !toolbars.isEmpty()) {
			// Get the Swing factory
			SwingFactory swingFactory = GUIUtils.getSwingFactory();
			// Builds the toolbars
			swingFactory.setToolbars(getView(), getContainer());
		}
		// Icon
		String iconId = getView().getIconId();
		if (StringUtils.isNotBlank(iconId)) {
			Icon icon = GUIUtils.getIcon(iconId, IconSize.MEDIUM);
			if (icon != null) {
				// TODO Uses a utility method for the conversion
				frame.setIconImage(((ImageIcon) icon).getImage());
			} else {
				log.warn("Cannot find icon " + iconId + " for size " + IconSize.MEDIUM);
			}
		}
		// Container
		frame.setContentPane(getContainer());
	}

	public boolean display(View parentView) {
		// Title
		updateTitle();
		// Setup the size & location
		Size size = getView().getSize();
		if (size != null) {
			size.setSize(getView(), frame);
		}
		frame.setLocationByPlatform(true);
		// Actual display
		frame.setVisible(true);
		frame.toFront();
		return true;
	}

	public Component getComponent() {
		return frame;
	}

	public void close() {
		Size size = getView().getSize();
		if (size != null) {
			size.saveSize(getView(), frame);
		}
		frame.dispose();
	}

	@Override
	public void onViewDataChanged() {
		updateTitle();
	}

	/**
	 * Updates the title of the frame using the actual title of the view
	 * 
	 * @see GUIUtils#getActualViewTitle(View)
	 */
	protected void updateTitle() {
		String title = GUIUtils.getActualViewTitle(getView());
		frame.setTitle(title);
	}

	/**
	 * @see JFrame#toFront()
	 */
	@Override
	public void activate() {
		frame.toFront();
	}

}
