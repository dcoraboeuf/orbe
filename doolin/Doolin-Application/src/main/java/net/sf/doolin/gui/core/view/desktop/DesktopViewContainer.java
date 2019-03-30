/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.core.view.desktop;

import java.awt.Component;
import java.beans.PropertyVetoException;

import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionClose;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.view.AbstractFrameViewContainer;
import net.sf.doolin.gui.core.view.DefaultSize;
import net.sf.doolin.gui.core.view.Size;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DesktopViewContainer extends AbstractFrameViewContainer {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DesktopViewContainer.class);

	/**
	 * Internal frame
	 */
	private JInternalFrame frame;

	/**
	 * Container
	 */

	@Override
	protected void build() {
		super.build();
		// Creates the frame
		frame = new JInternalFrame();
		// Closing action
		frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		Action closeAction = getView().getCloseAction();
		// Default close action
		if (closeAction == null) {
			closeAction = new ActionClose();
		}
		// Setup of the close
		final Action actualCloseAction = closeAction;
		frame.addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				getView().execute(actualCloseAction);
			}

		});
		// TODO Menubar
		// Icon
		String iconId = getView().getIconId();
		if (StringUtils.isNotBlank(iconId)) {
			Icon icon = GUIUtils.getIcon(iconId, IconSize.MEDIUM);
			frame.setFrameIcon(icon);
		}
		// Container
		frame.setContentPane(getContainer());
	}

	/**
	 * Set the internal frame as active
	 */
	protected void setFrameActive() {
		// Displays the frame
		try {
			frame.setVisible(true);
			frame.setIcon(false);
			frame.setSelected(true);
			frame.toFront();
		} catch (PropertyVetoException ex) {
			log.error("Cannot select the internal frame", ex);
		}
	}

	public boolean display(View parentView) {
		// Title
		updateTitle();
		// Size
		Size size = getView().getSize();
		if (size == null) {
			// Creates a default
			DefaultSize def = new DefaultSize();
			def.setPack(true);
			size = def;
		}
		size.setSize(getView(), frame);
		// Activates
		setFrameActive();
		// Ok
		return true;
	}

	public Component getComponent() {
		return frame;
	}

	public void close() {
		frame.dispose();
	}

	@Override
	public void onViewDataChanged() {
		updateTitle();
	}

	protected void updateTitle() {
		String title = GUIUtils.getActualViewTitle(getView());
		frame.setTitle(title);
	}

	/**
	 * @see #setFrameActive()
	 */
	@Override
	public void activate() {
		setFrameActive();
	}

}
