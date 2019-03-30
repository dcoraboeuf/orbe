package net.sf.doolin.util;

import java.util.Locale;

/**
 * Interface implemented by any object that can gives a string representation
 * different according to a given locale.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Localizable.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 */
public interface Localizable {

	/**
	 * Returns a message that is suitable for the given locale
	 * 
	 * @param locale
	 *            Locale to get the message for.
	 * @return Message for the given locale
	 */
	String getLocalizedMessage(Locale locale);

}
