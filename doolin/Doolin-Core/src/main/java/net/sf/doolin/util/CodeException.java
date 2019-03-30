/*
 * Created on 4 juil. 2004
 * $Id: CodeException.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
package net.sf.doolin.util;

import java.util.Locale;

/**
 * Model for exceptions that are using a code and some parameters for their
 * message
 * 
 * @version $Id: CodeException.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class CodeException extends RuntimeException implements Localizable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Code
	 */
	private String code;

	/**
	 * Parameters
	 */
	private Object[] parameters;

	/**
	 * Copy
	 * 
	 * @param ex
	 *            Exception to copy
	 */
	public CodeException(CodeException ex) {
		super(ex.code, ex);
		this.code = ex.code;
		this.parameters = ex.parameters;
	}

	/**
	 * Only one code
	 * 
	 * @param code
	 *            Code of the exception
	 */
	public CodeException(String code) {
		super(code);
		this.code = code;
		this.parameters = null;
	}

	/**
	 * Constructor with some parameters
	 * 
	 * @param code
	 *            Code of the exception
	 * @param params
	 *            Parameters of the exception
	 */
	public CodeException(String code, Object... params) {
		super(code);
		this.code = code;
		parameters = params;
	}

	/**
	 * Full constructor
	 * 
	 * @param code
	 *            Code of the exception
	 * @param error
	 *            Source exception
	 * @param params
	 *            Parameters of the exception
	 */
	public CodeException(String code, Throwable error, Object... params) {
		super(code);
		initCause(error);
		this.code = code;
		parameters = params;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return Returns the parameters.
	 */
	public Object[] getParameters() {
		return parameters;
	}

	/**
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	public String getLocalizedMessage() {
		return getLocalizedMessage(Locale.getDefault());
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return getLocalizedMessage();
	}

	public String getLocalizedMessage(Locale locale) {
		return Strings.get(locale, getCode(), getParameters());
	}
}
