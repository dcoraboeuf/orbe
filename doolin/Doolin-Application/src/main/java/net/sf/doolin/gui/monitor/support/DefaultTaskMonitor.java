/*
 * Created on 15 aoutt 07
 */
package net.sf.doolin.gui.monitor.support;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.monitor.TaskMonitor;
import net.sf.doolin.util.task.Task;
import net.sf.doolin.util.task.TaskListener;

public class DefaultTaskMonitor implements TaskMonitor, TaskListener {

	private View view;

	private boolean cancellable = true;

	private boolean doCycle = false;

	private int maximum = 100;

	private String title;

	private JDialog dialog;

	private String cancelOption;

	private String message;

	private String initialStatus;

	private JLabel labelNote;

	private JProgressBar progressBar;

	private JOptionPane optionPane;

	private boolean cancelled;

	public DefaultTaskMonitor(View view) {
		this.view = view;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancellable(boolean cancellable) {
		this.cancellable = cancellable;
	}

	public void setDoCycle(boolean doCycle) {
		this.doCycle = doCycle;
	}

	public void setMaximum(int max) {
		maximum = max;
		if (progressBar != null) {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					progressBar.setMaximum(maximum);
				}

			});
		}
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Creates the dialog
	 * 
	 * @return Created dialog
	 */
	protected JDialog createDialog() {
		// Cancel option
		cancelOption = UIManager.getString("OptionPane.cancelButtonText");
		// Label for the note
		labelNote = new JLabel(initialStatus != null ? initialStatus : " ");
		labelNote.setFont(labelNote.getFont().deriveFont((float) (labelNote.getFont().getSize() - 1)));
		// Progress bar
		progressBar = new JProgressBar();
		progressBar.setMaximum(maximum);
		if (cancellable) {
			optionPane = new JOptionPane(new Object[] { message, labelNote, progressBar }, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] { cancelOption }, null);
		} else {
			optionPane = new JOptionPane(new Object[] { message, labelNote, progressBar }, JOptionPane.INFORMATION_MESSAGE);
		}
		// Displays the dialog
		dialog = optionPane.createDialog(view != null ? view.getComponent() : null, title);
		// Ok
		return dialog;
	}

	public void start(Task task) {
		// Creates the dialog
		dialog = createDialog();
		dialog.pack();
		// Setup the task for monitoring
		task.setTaskListener(this);
		// Starts the tread
		Thread t = new Thread(task, task.getName());
		t.setDaemon(true);
		t.start();
		// Starts the monitor
		dialog.setVisible(true);
		// Cancelled ?
		if (optionPane.getValue() != null && optionPane.getValue().equals(cancelOption)) {
			cancelled = true;
			t.interrupt();
		}
	}
	
	protected void finish () {
		if (dialog != null) {
			dialog.dispose();
		}
	}

	public void onException(Throwable th) {
		finish();
		GUIUtils.displayException(view, th);
	}

	public void onFinish() {
		finish();
	}

	public void onProgress(int progress, final String progressName) {
		int value = progressBar.getValue();
		value += progress;
		if (doCycle) {
			value = value % maximum;
		}
		final int finalValue = value;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				labelNote.setText(progressName);
				progressBar.setValue(finalValue);
			}
		});
	}

	public void setInitialStatus(String status) {
		initialStatus = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
