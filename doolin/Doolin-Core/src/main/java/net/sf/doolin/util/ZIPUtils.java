/*
 * Created on 5 mai 2005
 */
package net.sf.doolin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Utility methods to zip / unzip data
 * 
 * @author damien
 * @version $Id: ZIPUtils.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
public class ZIPUtils {
	/**
	 * Zip a byte array
	 * 
	 * @param data
	 *            Bytes to compress
	 * @return Compressed bytes
	 */
	public static byte[] zip(byte[] data) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			try {
				GZIPOutputStream zout = new GZIPOutputStream(out);
				try {
					zout.write(data);
				} finally {
					zout.close();
				}
			} finally {
				out.close();
			}
		} catch (IOException ex) {
			throw new CodeException(StringCodes.STRING_ERROR_CANNOT_ZIP, ex);
		}
		// Ok
		return out.toByteArray();
	}

	/**
	 * Unzip a byte array
	 * 
	 * @param data
	 *            Zipped bytes
	 * @return Uncompressed bytes
	 */
	public static byte[] unzip(byte[] data) {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		try {
			try {
				GZIPInputStream zin = new GZIPInputStream(in);
				try {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					try {
						int read;
						byte[] buffer = new byte[1024];
						while ((read = zin.read(buffer)) > 0) {
							out.write(buffer, 0, read);
						}
					} finally {
						out.close();
					}
					return out.toByteArray();
				} finally {
					zin.close();
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new CodeException(StringCodes.STRING_ERROR_CANNOT_UNZIP, ex);
		}
	}
}
