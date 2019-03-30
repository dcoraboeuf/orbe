/*
 * Created on 1 nov. 2005
 */
package net.sf.doolin.util;

import net.sf.doolin.util.PasswordUtils;
import junit.framework.TestCase;

/**
 * Unit test for {@link net.sf.doolin.util.PasswordUtils}.
 * 
 * @author guinnessman
 * @version $Id: TestPasswordUtils.java,v 1.1 2007/07/18 18:41:19 guinnessman Exp $
 */
public class TestPasswordUtils extends TestCase {

	/**
	 * Calculated digest for "test".
	 */
	public static final String DIGEST_TEST = "098f6bcd4621d373cade4e832627b4f6";

	/**
	 * Test method for 'doolin.utils.PasswordUtils.digestPassword(char[])'
	 */
	public void testDigestPassword() {
		String value = PasswordUtils.digestPassword("test".toCharArray());
		assertEquals(DIGEST_TEST, value);
	}

	/**
	 * Test method for 'doolin.utils.PasswordUtils.isBlankPassword(String)'
	 */
	public void testIsBlankPassword() {
		String digestBlankPassword = PasswordUtils.digestPassword(new char[0]);
		assertTrue(PasswordUtils.isBlankPassword(digestBlankPassword));
	}

}
