package net.sf.doolin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * Some I/O utility methods
 * 
 * @author CORABOEUF
 * @version $Id: IOUtils.java,v 1.1 2007/07/18 18:41:21 guinnessman Exp $
 */
public class IOUtils {
	/**
	 * Read a property file from resources
	 * 
	 * @param respath
	 *            Resource to be read
	 * @return Properties
	 */
	public static Properties readProperties(String respath) {
		try {
			Properties properties = new Properties();
			InputStream in = IOUtils.class.getResourceAsStream(respath);
			if (in == null) {
				throw new CodeException(StringCodes.STRING_ERROR_RESOURCE_NOT_FOUND, respath);
			}
			try {
				properties.load(in);
				return properties;
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new CodeException(StringCodes.STRING_ERROR_CANNOT_OPEN_PROPERTIES, ex, respath);
		}
	}

	/**
	 * Collects a list of files, according to a given pattern, from a root
	 * directory and recursively.
	 * 
	 * @param root
	 *            Directory to start from
	 * @param filter
	 *            File filter to use
	 * @param recursive
	 *            Indicates if the search must be recursive or not.
	 * @return List of found files, not <code>null</code>.
	 */
	public static Collection<File> listFiles(File root, FileFilter filter, boolean recursive) {
		ArrayList<File> results = new ArrayList<File>();
		collectFiles(results, root, filter, recursive);
		return results;
	}

	private static void collectFiles(ArrayList<File> results, File dir, FileFilter filter, boolean recursive) {
		File[] files = dir.listFiles();
		ArrayList<File> dirs = new ArrayList<File>();
		for (File file : files) {
			if (file.isDirectory()) {
				dirs.add(file);
			} else if (filter.accept(file)) {
				results.add(file);
			}
		}
		if (recursive) {
			for (File subdir : dirs) {
				collectFiles(results, subdir, filter, recursive);
			}
		}
	}

	/**
	 * Reads a text resource from a reference class and a resource path. The
	 * resource URL is determined by computing the resource path from the
	 * reference class.
	 * 
	 * If the reference class is <code>null</code>, the path is computed
	 * using the current class (i.e. the {@link IOUtils} class).
	 * 
	 * If the resource cannot be accessed, a CodeException with code
	 * {@link StringCodes#STRING_ERROR_RESOURCE_NOT_FOUND} is thrown.
	 * 
	 * @param referenceClass
	 *            Reference for the resource path
	 * @param path
	 *            Resource path from the reference class
	 * @param encoding
	 *            Encoding of the target resource
	 * @return Resource content
	 * @throws CodeException
	 *             If the resource cannot be found
	 */
	public static String readTextResource(Class referenceClass, String path, String encoding) throws CodeException {
		InputStream in;
		if (referenceClass != null) {
			in = referenceClass.getResourceAsStream(path);
		} else {
			in = IOUtils.class.getResourceAsStream(path);
		}
		if (in != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, encoding));
				try {
					StringBuffer buffer = new StringBuffer();
					char[] t = new char[1024];
					int read;
					while ((read = reader.read(t)) > 0) {
						buffer.append(t, 0, read);
					}
					return buffer.toString();
				} finally {
					reader.close();
				}
			} catch (IOException ex) {
				throw new CodeException(StringCodes.STRING_ERROR_RESOURCE_IO_ERROR, ex, path, ex);
			}
		} else {
			throw new CodeException(StringCodes.STRING_ERROR_RESOURCE_NOT_FOUND, path);
		}
	}
}