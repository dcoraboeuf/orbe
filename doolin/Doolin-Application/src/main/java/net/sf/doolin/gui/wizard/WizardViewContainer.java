/*
 * Created on Aug 3, 2007
 */
package net.sf.doolin.gui.wizard;

import java.awt.Component;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.view.AbstractFrameViewContainer;
import net.sf.doolin.gui.core.view.Size;
import net.sf.doolin.gui.swing.SwingUtils;
import net.sf.doolin.gui.wizard.component.WizardDialog;
import net.sf.doolin.gui.wizard.component.WizardDialogControler;
import net.sf.doolin.gui.wizard.model.Wizard;
import net.sf.doolin.gui.wizard.model.WizardListener;
import net.sf.doolin.gui.wizard.model.WizardNavigationState;
import net.sf.doolin.gui.wizard.model.WizardStep;

/**
 * Top level container suitable for a wizard view. The contained view is
 * required to implement the <code>{@link WizardView}</code> interface.
 * 
 * @author Damien Coraboeuf
 * @version $Id: WizardViewContainer.java,v 1.1 2007/08/07 16:47:11 guinnessman
 *          Exp $
 */
public class WizardViewContainer extends AbstractFrameViewContainer implements WizardDialogControler, WizardListener {

	/**
	 * Wizard dialog
	 */
	private WizardDialog dialog;

	/**
	 * Wizard result
	 */
	private boolean ok;

	public void close() {
		Wizard w = getWizardView().getWizard();
		w.removeWizardListener(this);
		w.removeWizardListener(dialog);
		dialog.dispose();
	}

	@Override
	protected void build() {
		super.build();
		getWizardView().getWizard().addWizardListener(this);
	}

	public boolean display(View parentView) {
		// Creates the dialog
		createDialog(parentView);
		// Title
		updateTitle();
		// Setup the size & location
		Size size = getView().getSize();
		if (size != null) {
			size.setSize(getView(), dialog);
		}
		// Position
		Component parentComponent = parentView != null ? parentView.getComponent() : null;
		dialog.setLocationRelativeTo(parentComponent);
		// Initializes the wizard
		getWizardView().startWizard();
		// Ok
		ok = false;
		dialog.setVisible(true); /* Lock */
		// Result
		return ok;
	}

	/**
	 * Creates the wizard dialog.
	 * 
	 * @param parentView
	 *            Parent view
	 */
	protected void createDialog(View parentView) {
		// Parent component
		Component parentComponent = parentView != null ? parentView.getComponent() : null;
		// Creates the dialog
		dialog = SwingUtils.createWindow(WizardDialog.class, parentComponent);
		dialog.setControler(this);
		getWizardView().getWizard().addWizardListener(dialog);
		// Sets the container
		dialog.setViewContent(getContainer());
	}

	/**
	 * Updates the dialog title according to the view data.
	 * 
	 */
	protected void updateTitle() {
		String title = GUIUtils.getActualViewTitle(getView());
		dialog.setTitle(title);
	}

	/**
	 * Returns the wizard dialog.
	 * 
	 * @see net.sf.doolin.gui.core.ViewContainer#getComponent()
	 */
	public Component getComponent() {
		return dialog;
	}

	/**
	 * Gets the associated wizard view
	 * 
	 * @return Wizard view
	 * @see #getView()
	 */
	public WizardView getWizardView() {
		return (WizardView) getView();
	}

	/**
	 * @see Wizard#onClose()
	 * @see #close()
	 * @see net.sf.doolin.gui.wizard.component.WizardDialogControler#onCancel()
	 */
	public void onCancel() {
		if (getWizardView().getWizard().onClose()) {
			close();
		}
	}

	/**
	 * @see Wizard#nextStep()
	 * @see net.sf.doolin.gui.wizard.component.WizardDialogControler#onNext()
	 */
	public void onNext() {
		getWizardView().getWizard().nextStep();
	}

	/**
	 * 
	 * @see Wizard#previousStep()
	 * @see net.sf.doolin.gui.wizard.component.WizardDialogControler#onPrevious()
	 */
	public void onPrevious() {
		getWizardView().getWizard().previousStep();
	}

	/**
	 * 
	 * @see Wizard#finish()
	 * @see net.sf.doolin.gui.wizard.component.WizardDialogControler#onPrevious()
	 */
	public void onFinish() {
		getWizardView().getWizard().finish();
	}

	/**
	 * Closes every thing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardFinished(net.sf.doolin.gui.wizard.model.Wizard)
	 */
	public void wizardFinished(Wizard wizard) {
		ok = true;
		close();
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardNewStep(net.sf.doolin.gui.wizard.model.Wizard,
	 *      net.sf.doolin.gui.wizard.model.WizardStep)
	 */
	public void wizardNewStep(Wizard wizard, WizardStep step) {
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardResumed(net.sf.doolin.gui.wizard.model.Wizard,
	 *      net.sf.doolin.gui.wizard.model.WizardNavigationState)
	 */
	public void wizardResumed(Wizard wizard, WizardNavigationState restoredNavigationState) {
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardListener#wizardSuspended(net.sf.doolin.gui.wizard.model.Wizard)
	 */
	public void wizardSuspended(Wizard wizard) {
	}

	/**
	 * Does nothing
	 */
	@Override
	public void activate() {
	}

}
