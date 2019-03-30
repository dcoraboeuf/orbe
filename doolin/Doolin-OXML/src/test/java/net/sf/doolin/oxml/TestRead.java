/*
 * Created on Oct 1, 2007
 */
package net.sf.doolin.oxml;

import java.util.List;

import net.sf.doolin.oxml.config.XMLOXMLConfig;
import net.sf.doolin.oxml.source.ResourceOXMLSource;
import net.sf.doolin.util.Utils;
import junit.framework.TestCase;

/**
 * Tests the reading of an XML source to a Java object.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class TestRead extends TestCase {

	/**
	 * Tests the reading.
	 * 
	 * @throws Exception
	 *             In case there is an error
	 */
	public void testRead() throws Exception {
		// Creates the config
		OXMLConfig config = new XMLOXMLConfig(Utils.getResource(getClass(), "Configuration.xml"));
		// Creates the reader
		OXMLReader<ContactList> reader = new OXMLReader<ContactList>(config);
		// Source
		ResourceOXMLSource source = new ResourceOXMLSource(getClass(), "SourceDocument.xml");
		// Reading
		ContactList contactList = reader.read(source);

		// Assertions
		assertNotNull(contactList);
		List<Contact> contacts = contactList.getContacts();
		assertNotNull(contacts);
		assertEquals(1, contacts.size());
		Contact contact = contacts.get(0);
		assertNotNull(contact);
		assertEquals("Coraboeuf", contact.getName());
		assertEquals("Damien", contact.getFirstName());
	}

}
