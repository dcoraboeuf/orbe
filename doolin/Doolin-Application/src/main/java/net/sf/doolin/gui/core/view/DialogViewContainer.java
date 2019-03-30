/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.core.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import net.sf.doolin.gui.MessageCodes;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationReport;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.swing.SwingUtils;

public class DialogViewContainer extends AbstractFrameViewContainer {

	private boolean ok;

	private JDialog dialog;

	protected void createDialog(View parentView) {
		// Parent component
		Component parentComponent = parentView != null ? parentView.getComponent() : null;
		// Creates the dialog
		dialog = SwingUtils.createWindow(JDialog.class, parentComponent);
		dialog.setModal(true);
		// Closing action == cancel
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				onCancel();
			}

		});

		// Content pane
		JPanel contentPane = new JPanel(new BorderLayout());

		// Main content
		contentPane.add(getContainer(), BorderLayout.CENTER);

		// Button bar
		// TODO Uses an extension parameter on the view to check if the command
		// bar is present
		contentPane.add(createCommandPanel(), BorderLayout.PAGE_END);
		
		// Set the content
		dialog.setContentPane(contentPane);
	}

	protected JPanel createCommandPanel() {
		JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		commandPanel.setBorder(BorderFactory.createEtchedBorder());
		// Ok button
		addButtonOk(commandPanel);
		// Cancel button
		addButtonCancel(commandPanel);
		// End
		return commandPanel;
	}

	protected void addButtonCancel(JPanel commandPanel) {
		JButton buttonCancel = new JButton(GUIStrings.get(MessageCodes.BUTTON_CANCEL));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});
		commandPanel.add(buttonCancel);
		dialog.getRootPane().registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	protected void addButtonOk(JPanel commandPanel) {
		JButton buttonOk = new JButton(GUIStrings.get(MessageCodes.BUTTON_OK));
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});
		commandPanel.add(buttonOk);
		dialog.getRootPane().setDefaultButton(buttonOk);
	}

	protected void onOK() {
		ValidationReport report = new ValidationReport();
		getView().validate(report);
		// Displays the validation report result
		getView().display(report);
		// Result
		ok = report.isOk();
		// Closing ?
		if (ok) {
			close();
		}
	}

	protected void onCancel() {
		ok = false;
		close();
	}

	public void close() {
		dialog.dispose();
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
		// TODO Set the focus
		// Ok
		ok = false;
		dialog.setVisible(true); /* Lock */
		// Result
		return ok;
	}

	protected void updateTitle() {
		String title = GUIUtils.getActualViewTitle(getView());
		dialog.setTitle(title);
	}

	public Component getComponent() {
		return dialog;
	}
	
	/**
	 * Does nothing
	 */
	@Override
	public void activate() {
	}

}
