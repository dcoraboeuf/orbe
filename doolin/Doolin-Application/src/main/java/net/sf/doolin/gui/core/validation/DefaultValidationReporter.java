/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.validation;

import java.text.MessageFormat;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.AlertManager;

/**
 * Default implementation for the validation reporter service. It displays the
 * validation report using a message box.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultValidationReporter.java,v 1.1 2007/08/10 16:54:36 guinnessman Exp $
 */
public class DefaultValidationReporter extends AbstractValidationReporter {

	private String messageLine = "\u2022 {0}\n";

	private String messageCode = "net.sf.doolin.gui.ValidationReportMessage";

	/**
	 * Builds the validation report as a string and displays it in message box.
	 * 
	 * @see #getMessageCode()
	 * @see #getMessageLine()
	 * @see AlertManager#error(View, String, Object...)
	 * @see net.sf.doolin.gui.core.validation.ValidationReporter#report(net.sf.doolin.gui.core.View,
	 *      net.sf.doolin.gui.core.validation.ValidationReport)
	 */
	public void report(View view, ValidationReport report) {
		if (!report.isOk()) {
			StringBuffer displayedMessage = new StringBuffer();
			for (ValidationError error : report.getValidationErrors()) {
				String errorMessage = error.getDetailedMessage();
				String errorLine = MessageFormat.format(messageLine, errorMessage);
				displayedMessage.append(errorLine);
			}
			GUIUtils.getAlertManager().error(view, messageCode, displayedMessage);
		}
	}

	/**
	 * Returns the format used to display a line. The {0} wild-card is used to
	 * contains the message itself. It defaults to "\u2022 {0}\n".
	 * 
	 * @return Format used for a line.
	 */
	public String getMessageLine() {
		return messageLine;
	}

	/**
	 * Sets the format used to display a line. The {0} wildcard is used to
	 * contains the message itself.
	 * 
	 * @param messageLine
	 *            Format used for a line.
	 */
	public void setMessageLine(String messageLine) {
		this.messageLine = messageLine;
	}

	/**
	 * Returns the message code for the title of the report. It defaults to
	 * "net.sf.doolin.gui.ValidationReportMessage".
	 * 
	 * @return Message code
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * Sets the message code for the title of the report.
	 * 
	 * @param messageCode
	 *            Message code
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

}
