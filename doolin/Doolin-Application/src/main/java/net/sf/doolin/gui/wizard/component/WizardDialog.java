/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard.component;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.sf.doolin.gui.MessageCodes;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.wizard.model.Wizard;
import net.sf.doolin.gui.wizard.model.WizardListener;
import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Dialog for a wizard.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardDialog.java,v 1.3 2007/08/10 16:54:45 guinnessman Exp $
 */
public class WizardDialog extends JDialog implements WizardListener {

	private JPanel contentPane;

	private JButton buttonPrevious;

	private JButton buttonNext;

	private JButton buttonFinish;

	private JButton buttonCancel;

	private WizardDialogControler controler;

	/**
	 * Top-level constructor.
	 * 
	 */
	public WizardDialog() {
		super();
		init();
	}

	/**
	 * Constructor over a dialog
	 * 
	 * @param owner
	 *            Dialog
	 */
	public WizardDialog(Dialog owner) {
		super(owner);
		init();
	}

	/**
	 * Constructor over a frame
	 * 
	 * @param owner
	 *            Frame
	 */
	public WizardDialog(Frame owner) {
		super(owner);
		init();
	}

	/**
	 * Initialization
	 */
	protected void init() {
		// General properties
		setModal(true);

		// Content
		contentPane = createContentPane();
		setContentPane(contentPane);

		// Default button
		getRootPane().setDefaultButton(buttonFinish);

		// Closing action is like cancelling
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				onCancel();
			}

		});
	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		// TODO Header
		// Commands
		panel.add(createCommandPanel(), BorderLayout.PAGE_END);
		// Ok
		return panel;
	}

	private JPanel createCommandPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		panel.setBorder(BorderFactory.createEtchedBorder());
		// Previous
		buttonPrevious = new JButton(GUIStrings.get(MessageCodes.BUTTON_PREVIOUS));
		buttonPrevious.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onPrevious();
			}

		});
		// Next
		buttonNext = new JButton(GUIStrings.get(MessageCodes.BUTTON_NEXT));
		buttonNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onNext();
			}

		});
		// Finish
		buttonFinish = new JButton(GUIStrings.get(MessageCodes.BUTTON_FINISH));
		buttonFinish.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onFinish();
			}

		});
		// Cancel
		// TODO Binds on ESCAPE key
		buttonCancel = new JButton(GUIStrings.get(MessageCodes.BUTTON_CANCEL));
		buttonCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onCancel();
			}

		});
		// Installs all buttons
		panel.add(buttonPrevious);
		panel.add(buttonNext);
		panel.add(buttonFinish);
		panel.add(buttonCancel);
		// Initial state
		buttonPrevious.setEnabled(false);
		buttonNext.setEnabled(false);
		buttonFinish.setEnabled(false);
		buttonCancel.setEnabled(true);
		// Ok
		return panel;
	}

	/**
	 * On previous action
	 */
	protected void onPrevious() {
		try {
			controler.onPrevious();
		} catch (Throwable ex) {
			GUIUtils.displayException(controler.getWizardView(), ex);
		}
	}

	/**
	 * On next action
	 */
	protected void onNext() {
		try {
			controler.onNext();
		} catch (Throwable ex) {
			GUIUtils.displayException(controler.getWizardView(), ex);
		}
	}

	/**
	 * On finish action
	 */
	protected void onFinish() {
		try {
			controler.onFinish();
		} catch (Throwable ex) {
			GUIUtils.displayException(controler.getWizardView(), ex);
		}
	}

	/**
	 * On cancel action
	 */
	protected void onCancel() {
		controler.onCancel();
	}

	/**
	 * Sets the content of this dialog
	 * 
	 * @param container
	 *            Content
	 */
	public void setViewContent(JPanel container) {
		contentPane.add(container, BorderLayout.CENTER);
	}

	/**
	 * @return Returns the controler.
	 */
	public WizardDialogControler getControler() {
		return controler;
	}

	/**
	 * @param controler
	 *            The controler to set.
	 */
	public void setControler(WizardDialogControler controler) {
		this.controler = controler;
	}

	/**
	 * Updates the button status.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardNewStep()
	 */
	public void wizardNewStep(Wizard wizard, WizardStep step) {
		// Get the navigation state
		WizardNavigationState state = wizard.getNavigationState(step);
		// Adjust buttons
		setNavigationState(state);
	}

	/**
	 * Sets the navigation state
	 * 
	 * @param state
	 *            Navigation state
	 */
	protected void setNavigationState(WizardNavigationState state) {
		buttonPrevious.setEnabled(state.isPreviousPossible());
		buttonNext.setEnabled(state.isNextPossible());
		buttonFinish.setEnabled(state.isFinishPossible());
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardFinished(net.sf.doolin.gui.wizard.model.Wizard)
	 */
	public void wizardFinished(Wizard wizard) {
	}

	public void wizardResumed(Wizard wizard, WizardNavigationState restoredNavigationState) {
		setNavigationState(restoredNavigationState);
	}

	public void wizardSuspended(Wizard wizard) {
		WizardNavigationState state = new WizardNavigationState();
		state.setPreviousPossible(false);
		state.setNextPossible(false);
		state.setFinishPossible(false);
		setNavigationState(state);
	}

}
