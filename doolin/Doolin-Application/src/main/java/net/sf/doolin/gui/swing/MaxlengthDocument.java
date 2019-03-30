/*
 * Created on 26 juil. 2003
 */
package net.sf.doolin.gui.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Maxlength document listener
 * 
 * @version $Id: MaxlengthDocument.java,v 1.1 2007/07/25 18:10:39 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class MaxlengthDocument extends PlainDocument {
	/**
	 * Maximum length
	 */
	private int maxLength;

	/**
	 * Constructor
	 */
	public MaxlengthDocument(int length) {
		maxLength = length;
	}

	/**
	 * Returns the maxLength.
	 */
	public int getMaxLength() {
		return maxLength;
	}

	public void insertString(int offset, String s, AttributeSet a) throws BadLocationException {
		if (s != null) {
			if (getLength() + s.length() > maxLength) {
				return;
			} else {
				super.insertString(offset, s, a);
			}
		}
	}

}