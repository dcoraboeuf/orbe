/*
 * Created on 15 janv. 2006
 */
package net.sf.doolin.util.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This class implements a simple error handler that just stores the warnings
 * and errors that occur during an XML parsing.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SimpleErrorHandler.java,v 1.1 2007/07/25 18:23:11 guinnessman
 *          Exp $
 */
public class SimpleErrorHandler implements ErrorHandler {

	private ArrayList<SAXParseException> warnings = new ArrayList<SAXParseException>();

	private ArrayList<SAXParseException> errors = new ArrayList<SAXParseException>();

	public void warning(SAXParseException exception) throws SAXException {
		warnings.add(exception);
	}

	public void error(SAXParseException exception) throws SAXException {
		errors.add(exception);
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		errors.add(exception);
	}

	/**
	 * Returns if the handler contains some warnings.
	 * 
	 * @return <code>true</code> if some warnings have been generated.
	 */
	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	/**
	 * Returns if the handler contains some errors.
	 * 
	 * @return <code>true</code> if some errors have been generated.
	 */
	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	/**
	 * Logs all the warnings
	 * 
	 * @param log
	 *            Log to use
	 */
	public void logWarnings(Log log) {
		for (SAXParseException ex : warnings) {
			log.warn("Parsing warning : " + ex.getMessage());
		}
	}

	/**
	 * Throw the first error is any.
	 * 
	 * @throws SAXParseException
	 *             Thrown exception
	 */
	public void throwAnyError() throws SAXParseException {
		if (hasErrors()) {
			throw errors.get(0);
		}
	}

	/**
	 * Throw the first error or warning is any.
	 * 
	 * @throws SAXParseException
	 *             Thrown exception
	 */
	public void throwAnyWarningOrError() throws SAXParseException {
		throwAnyError();
		if (hasWarnings()) {
			throw warnings.get(0);
		}
	}

	/**
	 * Returns the list of warnings
	 * 
	 * @return List of warnings
	 */
	public List<SAXParseException> getWarnings() {
		return warnings;
	}

	/**
	 * Returns the list of errors
	 * 
	 * @return List of errors
	 */
	public List<SAXParseException> getErrors() {
		return errors;
	}
}
