/*                                                                           
 * Created on Oct 2, 2007                                                    
 */
package net.sf.doolin.util;

import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

/**
 * Test of the {@link CodecUtils} class.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class TestCodecUtils extends TestCase {

	/**
	 * @see CodecUtils#encodeBase64(byte[])
	 * @throws Exception
	 *             In case of error
	 */
	public void testEncodeBase64() throws Exception {
		byte[] bytes = "abcdef".getBytes("UTF-8");
		String result = CodecUtils.encodeBase64(bytes);
		assertEquals("YWJjZGVm", result);
	}

	/**
	 * @throws UnsupportedEncodingException
	 *             If the result cannot be decoded
	 * @see CodecUtils#decodeBase64(String)
	 * 
	 */
	public void testDecodeBase64() throws UnsupportedEncodingException {
		byte[] bytes = CodecUtils.decodeBase64("YWJjZGVm");
		String result = new String(bytes, "UTF-8");
		assertEquals("abcdef", result);
	}

	/**
	 * @see CodecUtils#encodeCharsBase64(char[])
	 * 
	 */
	public void testEncodeCharsBase64() {
		String result = CodecUtils.encodeCharsBase64("abcdef".toCharArray());
		assertEquals("YWJjZGVm", result);
	}

	/**
	 * @see CodecUtils#decodeCharsBase64(char[])
	 * 
	 */
	public void testDecodeCharsBase64() {
		char[] chars = CodecUtils.decodeCharsBase64("YWJjZGVm");
		String result = new String(chars);
		assertEquals("abcdef", result);
	}

}