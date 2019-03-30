package net.sf.doolin.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Digest doolin.utils
 * 
 * @author CORABOEUF
 * @version $Id: PasswordUtils.java,v 1.1 2007/07/18 18:41:20 guinnessman Exp $
 */
public class PasswordUtils {
	/**
	 * Blank password
	 */
	private final static String blankPassword;
	static {
		blankPassword = digestPassword(null);
	}

	/**
	 * Computes an encoded digest for a password
	 * 
	 * @param password
	 *            Password to encode
	 * @return Digest for the password
	 */
	public static String digestPassword(char[] password) {
		byte[] bytes = null;
		try {
			bytes = passwordToBytes(password);
			// Calculates the digest
			String digest = DigestUtils.md5Hex(bytes);
			// Ok
			return digest;
		} finally {
			// Clears all arrays
			if (bytes != null) {
				Arrays.fill(bytes, (byte) 0);
			}
			if (password != null) {
				Arrays.fill(password, (char) 0);
			}
		}
	}

	/**
	 * Converts a character password to a byte password using utf8
	 * 
	 * @param password
	 *            Password to convert
	 * @return Password as bytes
	 */
	public static byte[] passwordToBytes(char[] password) {
		try {
			// Get the bytes for the characters
			ByteArrayOutputStream bytesOutput = new ByteArrayOutputStream();
			try {
				// Writes the chars
				OutputStreamWriter writer = new OutputStreamWriter(bytesOutput,
						"UTF-8");
				try {
					if (password != null) {
						writer.write(password);
					}
				} finally {
					writer.close();
				}
				// Get the bytes
				byte[] bytes = bytesOutput.toByteArray();
				return bytes;
			} finally {
				bytesOutput.reset();
			}
		} catch (Exception ex) {
			throw new CodeException(
					StringCodes.STRING_ERROR_CANNOT_ENCODE_PASSWORD, ex);
		}
	}

	/**
	 * Program used to compute the digests of all password that are given on
	 * command line.
	 * 
	 * @param args
	 *            List of passwords.
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			char[] password = arg.toCharArray();
			String encoded = digestPassword(password);
			System.out.println(arg + " : " + encoded);
		}
	}

	/**
	 * Is the password blank ? This method uses the password with its encoded
	 * form.
	 * 
	 * @param encodedPassword
	 *            Encoded form of the password
	 * @return <code>true</code> if the underlying password is blank.
	 */
	public static boolean isBlankPassword(String encodedPassword) {
		return StringUtils.equals(blankPassword, encodedPassword);
	}

	/**
	 * TODO Encrypts a password
	 * 
	 * @param password
	 *            Password to crypt
	 * @return Encrypted password
	 */
	public static String encodePassword(char[] password) {
		try {
			return CodecUtils.encodeCharsBase64(password);
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException(
					StringCodes.STRING_ERROR_CANNOT_ENCODE_PASSWORD, ex);
		}
	}

	/**
	 * TODO Decrypt a password
	 * 
	 * @param s
	 *            Encrypted password
	 * @return Uncrypted password
	 */
	public static char[] decodePassword(String s) {
		try {
			return CodecUtils.decodeCharsBase64(s);
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException(
					StringCodes.STRING_ERROR_CANNOT_DECODE_PASSWORD, ex);
		}
	}
}
