/*
 * Created on 1 mars 2006
 */
package net.sf.doolin.util;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;

/**
 * Status of remote resource that can be accessed using HTTP. Different types of
 * status are defined like below:
 * <ul>
 * <li>Found - the resource does exist. A timestamp (last modified date) is
 * associated with the returned status.</li>
 * <li>Not found - the resource does not exist (404)</li>
 * <li>Failure - cannot access the resource for any reason.</li>
 * </ul>
 * 
 * @author Damien Coraboeuf
 * @version $Id: RemoteStatus.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public class RemoteStatus {

	/**
	 * Code
	 */
	private RemoteStatusCode statusCode;

	/**
	 * Exception
	 */
	private CodeException codeException;

	/**
	 * Timestamp of remote resource
	 */
	private Date timestamp;

	/**
	 * Get the status from a remote resource.
	 * 
	 * After this constructor has been called, the <code>statusCode</code>
	 * will be set to:
	 * <ul>
	 * <li>FOUND - if the URL could be reached and if the
	 * <code>last-modified</code> HTTP header was correctly set.
	 * <li>NOT_FOUND - if the URL couldn't be reached
	 * <li>FAILURE - if any error occured when trying the reach the URL (in
	 * such case, the <code>codeException</code> is set and contains the cause
	 * exception)
	 * </ul>
	 * 
	 * @param remoteURL
	 *            Remote URL to get the status from.
	 */
	public RemoteStatus(URL remoteURL) {
		if (remoteURL == null) {
			throw new IllegalArgumentException("remoteURL cannot be null");
		}
		// Creates a HTTP client
		HttpClient client = new HttpClient();
		// Creates a HEAD call
		HeadMethod headMethod = new HeadMethod(remoteURL.toString());
		try {
			// Executes the call
			int returnedStatus = client.executeMethod(headMethod);
			if (returnedStatus == HttpStatus.SC_OK) {
				// Retrieve just the last modified header value.
				String lastModified = headMethod.getResponseHeader("last-modified").getValue();
				// Parse the date
				Date lastModifiedDate;
				try {
					lastModifiedDate = DateUtil.parseDate(lastModified);
					// Found status
					statusCode = RemoteStatusCode.FOUND;
					timestamp = lastModifiedDate;
				} catch (DateParseException ex) {
					// Failure
					statusCode = RemoteStatusCode.FAILURE;
					codeException = new CodeException(StringCodes.STRING_REMOTESTATUS_DATE_PARSING_ERROR, ex, lastModified);
				}
			} else if (returnedStatus == HttpStatus.SC_NOT_FOUND) {
				// Not found
				statusCode = RemoteStatusCode.NOT_FOUND;
			} else {
				// Failure
				statusCode = RemoteStatusCode.FAILURE;
				codeException = new CodeException(StringCodes.STRING_REMOTESTATUS_STATUS, returnedStatus);
			}
		} catch (IOException ex) {
			// Failure
			statusCode = RemoteStatusCode.FAILURE;
			codeException = new CodeException(StringCodes.STRING_REMOTESTATUS_IO, ex, ex.getLocalizedMessage());
		}
	}

	/**
	 * @return Returns the statusCode.
	 */
	public RemoteStatusCode getStatusCode() {
		return statusCode;
	}

	/**
	 * @return Returns the timestamp.
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @return Returns the codeException.
	 */
	public CodeException getCodeException() {
		return codeException;
	}

	/**
	 * 
	 */
}
