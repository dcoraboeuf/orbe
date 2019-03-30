/*
 * Created on Oct 1, 2007
 */
package net.sf.doolin.oxml;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact list class for the test
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class ContactList {

	private List<Contact> contacts = new ArrayList<Contact>();

	/**
	 * Adds a contact to the list
	 * 
	 * @param c
	 *            Contact to add
	 */
	public void addContact(Contact c) {
		contacts.add(c);
	}

	/**
	 * Returns the list of contacts
	 * 
	 * @return List of contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

}
