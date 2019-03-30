/*
 * Created on Oct 1, 2007
 */
package net.sf.doolin.oxml;

/**
 * Contact class for the test
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class Contact {

	private String name;

	private String firstName;

	/**
	 * Returns the firstName
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstName
	 * 
	 * @param firstName
	 *            firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
