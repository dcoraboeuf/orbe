/*
 * Created on Aug 8, 2007
 */
package net.sf.doolin.gui.wizard.support;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import net.sf.doolin.util.task.TaskListener;

import net.sf.doolin.gui.core.support.GUITask;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.wizard.WizardView;

/**
 * This wizard step executes a task and displays a progress bar along the
 * execution of this task. When it is finished, the buttons are available.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TaskWizardStep.java,v 1.2 2007/08/15 09:05:24 guinnessman Exp $
 */
public class TaskWizardStep extends AbstractWizardStep implements TaskListener {

	private JPanel container;

	private JLabel progressName;

	private JProgressBar progressBar;

	private WizardView wizardView;

	private Thread runningTask;

	private GUITask task;

	/**
	 * Creates the task step component.
	 * 
	 * @see JProgressBar
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#init()
	 */
	public void init() {
		container = new JPanel(new GridBagLayout());

		progressBar = new JProgressBar();
		progressName = new JLabel(" ");

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.SOUTH;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 20, 4, 20);
		container.add(progressName, gc);

		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.NORTH;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 20, 0, 20);
		container.add(progressBar, gc);
	}

	public JComponent getComponent() {
		return container;
	}

	public boolean onClose() {
		if (runningTask != null) {
			runningTask.interrupt();
			runningTask = null;
		}
		// Stops the progress bar
		progressBar.setIndeterminate(false);
		// Resumes the navigation
		wizardView.getWizard().resume();
		// Ok, can close
		return true;
	}

	/**
	 * Sets the view data. It is not used.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#setViewData(java.lang.Object)
	 */
	public void setViewData(Object viewData) {
	}

	/**
	 * Sets the associated view.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#setWizardView(net.sf.doolin.gui.wizard.WizardView)
	 */
	public void setWizardView(WizardView wizardView) {
		this.wizardView = wizardView;
	}

	/**
	 * Starts the task.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#start()
	 */
	public void start() {
		task.setView(wizardView);
		task.setTaskListener(this);

		runningTask = new Thread(task, task.getName());
		runningTask.setDaemon(true);

		progressBar.setIndeterminate(true);
		wizardView.getWizard().suspend();

		runningTask.start();
	}

	/**
	 * Returns the task to run
	 * 
	 * @return GUITask
	 */
	public GUITask getTask() {
		return task;
	}

	/**
	 * Sets the task to run
	 * 
	 * @param task
	 *            GUITask
	 */
	public void setTask(GUITask task) {
		this.task = task;
	}

	public void onException(Throwable th) {
		runningTask = null;
		// Stops the progress bar
		progressBar.setIndeterminate(false);
		// Resumes the navigation
		wizardView.getWizard().resume();
		// Displays the alert
		GUIUtils.displayException(wizardView, th);
	}

	public void onFinish() {
		runningTask = null;
		// Stops the progress bar
		progressBar.setIndeterminate(false);
		// Resumes the navigation
		wizardView.getWizard().resume();
	}

	/**
	 * Updates the progress status.
	 * 
	 * @see net.sf.doolin.util.task.TaskListener#onProgress(int,
	 *      java.lang.String)
	 */
	public void onProgress(int progress, final String stepName) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				progressName.setText(stepName);
			}

		});
	}

	/**
	 * Does not validate.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#validate(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void validate(ValidationReport validationReport) {
	}

	/**
	 * Does nothing.
	 * 
	 * @see net.sf.doolin.gui.wizard.model.WizardStep#display(net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void display(ValidationReport report) {
	}

	public void setMaximum(final int newMaximum) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				progressBar.setMaximum(newMaximum);
			}

		});
	}

}
