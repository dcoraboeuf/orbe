/*
 * Created on Aug 17, 2007
 */
package net.sf.doolin.gui.auth.support;

import java.text.MessageFormat;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.core.validation.ValidationError;
import net.sf.doolin.gui.core.validation.ValidationReport;

/**
 * Default implementation for the authentication failed reporter.
 * 
 * @author Damien Coraboeuf
 * @version $Id: DefaultAuthenticationFailedReporter.java,v 1.1 2007/08/17 15:06:55 guinnessman Exp $
 * @param <T>
 *            Type of login form
 */
public class DefaultAuthenticationFailedReporter<T> implements AuthenticationFailedReporter<T> {

	private String messageLine = "\u2022 {0}\n";

	private String messageCode = "net.sf.doolin.gui.AuthenticationFailedMessage";

	/**
	 * Displays the errors in a message box.
	 * 
	 * @see net.sf.doolin.gui.auth.support.AuthenticationFailedReporter#report(net.sf.doolin.gui.core.validation.ValidationReport,
	 *      java.lang.Object)
	 */
	public void report(ValidationReport report, T form) {
		if (!report.isOk()) {
			StringBuffer displayedMessage = new StringBuffer();
			for (ValidationError error : report.getValidationErrors()) {
				String errorMessage = error.getDetailedMessage();
				String errorLine = MessageFormat.format(messageLine, errorMessage);
				displayedMessage.append(errorLine);
			}
			GUIUtils.getAlertManager().error((View)null, messageCode, displayedMessage);
		}
	}

	/**
	 * Returns the format used to display a line. The {0} wildcard is used to
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
