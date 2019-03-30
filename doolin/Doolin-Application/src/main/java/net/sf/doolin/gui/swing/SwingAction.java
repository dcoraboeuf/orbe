/*
 * Created on Jul 17, 2007
 */
package net.sf.doolin.gui.swing;

import java.awt.AWTKeyStroke;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.Application;
import net.sf.doolin.gui.core.Action;
import net.sf.doolin.gui.core.ActionListener;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.core.icon.IconSize;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.service.MnemonicInfo;
import net.sf.doolin.gui.service.MnemonicInfoFactory;

/**
 * This Swing action encapsulates a Doolin GUI action.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingAction.java,v 1.1 2007/08/10 16:54:38 guinnessman Exp $
 */
public class SwingAction extends AbstractAction {

	private Action action;

	private IconSize iconSize;

	private View view;

	private ActionControler controler;

	/**
	 * Constructor.
	 * 
	 * @param action
	 *            Encapsulated action
	 * @param view
	 *            Hosting view
	 * @param controler
	 *            Controler for the action before execution
	 * @param iconSize
	 *            Requested icon size for the any icon.
	 */
	public SwingAction(Action action, View view, ActionControler controler, IconSize iconSize) {
		// Constructor
		this.controler = controler;
		this.action = action;
		this.view = view;
		this.iconSize = iconSize;
		// Setup
		setup();
		// Listens for action state
		this.action.addActionListener(new ActionListener() {
			public void onActionChanged(Action a) {
				if (a == SwingAction.this.action) {
					setup();
				}
			}
		});
	}

	/**
	 * Setup of the Swing action according to the Doolin action.
	 * 
	 */
	protected void setup() {
		// Name
		String actionName = action.getName();
		String actionText = GUIStrings.get(actionName);
		MnemonicInfoFactory labelInfoFactory = Application.getApplication().getService(MnemonicInfoFactory.class);
		MnemonicInfo labelInfo = labelInfoFactory.getLabelInfo(actionText);
		labelInfo.configureAction(this);
		// Icon
		if (iconSize != null) {
			String actionIconIdKey = actionName + ".icon";
			String actionIconId = GUIStrings.getIfPresent(actionIconIdKey);
			if (StringUtils.isNotBlank(actionIconId)) {
				// Get the icon
				Icon icon = GUIUtils.getIcon(actionIconId, iconSize);
				if (icon != null) {
					putValue(SMALL_ICON, icon);
				}
			}
		}
		// Shortcut
		String actionShortcutKey = actionName + ".key";
		String actionShortcut = GUIStrings.getIfPresent(actionShortcutKey);
		if (StringUtils.isNotBlank(actionShortcut)) {
			AWTKeyStroke keyStroke = AWTKeyStroke.getAWTKeyStroke(actionShortcut);
			putValue(ACCELERATOR_KEY, keyStroke);
		}
		// Tip
		String actionTipKey = actionName + ".tip";
		String actionTip = GUIStrings.getIfPresent(actionTipKey);
		if (StringUtils.isNotBlank(actionTip)) {
			putValue(SHORT_DESCRIPTION, actionTip);
		}
		// Enabling
		setEnabled(action.isEnabled());
	}

	public void actionPerformed(ActionEvent e) {
		action.setView(view);
		// Prepares the action (listeners, context...)
		if (controler != null) {
			controler.control(action);
		}
		// Go
		action.execute();
	}
}
