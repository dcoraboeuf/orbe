/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.service.support;

import java.awt.Component;

import javax.swing.JOptionPane;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.service.ConfirmResult;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.util.CodeException;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultAlertManager extends AbstractAlertManager {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(DefaultAlertManager.class);

	/**
	 * Max line length for a message
	 */
	private int maxLineLength = 100;

	public void displayException(View view, Throwable th) {
		try {
			throw th;
		} catch (CodeException ex) {
			displayCodeException(view, ex);
		} catch (Throwable ex) {
			CodeException cex = new CodeException("net.sf.doolin.gui.GenericException", ex, ex);
			displayCodeException(view, cex);
		}
	}

	protected void displayCodeException(View view, CodeException ex) {
		// Log the error
		if (ex.getCause() != null) {
			log.error(ex.getLocalizedMessage(), ex);
		}
		// Displays in an alert box
		error(view != null ? view.getComponent() : null, ex.getCode(), ex.getParameters());
	}

	/**
	 * Wraps a message so it can be displayed on several lines
	 * 
	 * @param message
	 *            Message to wrap
	 * @return Message wrapped on several lines (if necessary)
	 */
	public String wrapMessage(String message) {
		int length = message.length();
		if (length < maxLineLength) {
			return message;
		} else {
			message = WordUtils.wrap(message, maxLineLength, System.getProperty("line.separator"), false);
			return message;
		}
	}

	public int getMaxLineLength() {
		return maxLineLength;
	}

	public void setMaxLineLength(int maxLineLength) {
		this.maxLineLength = maxLineLength;
	}

	public void error(View view, String code, Object... params) {
		doError(view != null ? view.getComponent() : null, code, params);
	}

	public void error(Component component, String code, Object... params) {
		doError(component, code, params);
	}

	protected void doError(Component component, String code, Object... params) {
		String message = GUIStrings.get(code, params);
		message = wrapMessage(message);
		JOptionPane.showMessageDialog(component, message, GUIStrings.get("net.sf.doolin.gui.ErrorMessageTitle"), JOptionPane.ERROR_MESSAGE);
	}

	public boolean confirm(View view, String code, Object... params) {
		return doConfirm(view != null ? view.getComponent() : null, code, params);
	}

	public boolean confirm(Component component, String code, Object... params) {
		return doConfirm(component, code, params);
	}

	protected boolean doConfirm(Component component, String code, Object... params) {
		String message = GUIStrings.get(code, params);
		message = wrapMessage(message);
		int result = JOptionPane.showConfirmDialog(component, message, GUIStrings.get("net.sf.doolin.gui.ConfirmMessageTitle"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return (result == JOptionPane.YES_OPTION);
	}

	public void message(View view, String code, Object... params) {
		doMessage(view != null ? view.getComponent() : null, code, params);
	}

	public void message(Component component, String code, Object... params) {
		doMessage(component, code, params);
	}

	protected void doMessage(Component component, String code, Object... params) {
		String message = GUIStrings.get(code, params);
		message = wrapMessage(message);
		JOptionPane.showMessageDialog(component, message, GUIStrings.get("net.sf.doolin.gui.PlainMessageTitle"), JOptionPane.INFORMATION_MESSAGE);
	}

	public ConfirmResult confirmSave(View view, String code, Object... params) {
		return doConfirmSave(view != null ? view.getComponent() : null, code, params);
	}

	public ConfirmResult confirmSave(Component parent, String code, Object... params) {
		return doConfirmSave(parent, code, params);
	}

	protected ConfirmResult doConfirmSave(Component parent, String code, Object... params) {
		String message = GUIStrings.get(code, params);
		message = wrapMessage(message);
		int result = JOptionPane.showConfirmDialog(parent, message, "", JOptionPane.YES_NO_CANCEL_OPTION);
		switch (result) {
		case JOptionPane.YES_OPTION:
			return ConfirmResult.SAVE_AND_CLOSE;
		case JOptionPane.NO_OPTION:
			return ConfirmResult.CLOSE;
		default:
			return ConfirmResult.CANCEL;
		}
	}

}
