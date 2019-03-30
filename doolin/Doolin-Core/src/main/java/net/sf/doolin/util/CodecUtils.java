/*
 * Created on 10 mars 2005
 */
package net.sf.doolin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Codec method
 * 
 * @author damien
 * @version $Id: CodecUtils.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
public class CodecUtils {
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(CodecUtils.class);

	/**
	 * Encode using Base 64
	 * 
	 * @param bytes
	 *            Bytes to encode
	 * @return Encoded bytes as a string
	 */
	public static String encodeBase64(byte[] bytes) {
		try {
			byte[] encodedBytes = Base64.encodeBase64(bytes);
			return new String(encodedBytes, "ASCII");
		} catch (Exception ex) {
			throw new CodeException(StringCodes.STRING_ERROR_CANNOT_ENCODE_BASE64, ex);
		}
	}

	/**
	 * Decode using Base 64
	 * 
	 * @param s
	 *            String to decode
	 * @return Decoded bytes
	 */
	public static byte[] decodeBase64(String s) {
		try {
			byte[] encodedBytes = s.getBytes("ASCII");
			return Base64.decodeBase64(encodedBytes);
		} catch (IOException ex) {
			throw new CodeException(StringCodes.STRING_ERROR_CANNOT_DECODE_BASE64, ex);
		}
	}

	/**
	 * Encode a given password using Base64
	 * 
	 * @param password
	 *            Password to encode
	 * @return Encoded password
	 */
	public static String encodeCharsBase64(char[] password) {
		try {
			String s = new String(password);
			byte[] bytes = s.getBytes("utf8");
			return encodeBase64(bytes);
		} catch (UnsupportedEncodingException e) {
			log.error("Cannot encode using Base64", e);
			return null;
		}
	}

	/**
	 * Decode a password that has been encoded using Base64
	 * 
	 * @param s
	 *            Encoded password
	 * @return Decoded password
	 * @see #encodeCharsBase64(char[])
	 */
	public static char[] decodeCharsBase64(String s) {
		try {
			byte[] bytes = decodeBase64(s);
			s = new String(bytes, "utf8");
			return s.toCharArray();
		} catch (UnsupportedEncodingException e) {
			log.error("Cannot encode using Base64", e);
			return null;
		}
	}

}
