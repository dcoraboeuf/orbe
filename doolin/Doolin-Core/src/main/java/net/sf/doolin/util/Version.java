/*
 * Created on 8 oct. 2005
 */
package net.sf.doolin.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Definition of a version on three numbers.
 * 
 * @author damien
 * @version $Id: Version.java,v 1.1 2007/07/18 18:41:22 guinnessman Exp $
 */
public class Version implements Comparable<Version> {
	/**
	 * Major
	 */
	private int major;

	/**
	 * Minor
	 */
	private int minor;

	/**
	 * Patch
	 */
	private int patch;

	/**
	 * Constructor
	 * 
	 * @param major
	 *            First number
	 * @param minor
	 *            Second number
	 * @param patch
	 *            Last number
	 */
	public Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	/**
	 * Parsing of a version number
	 * 
	 * @param value
	 *            String to parse.
	 */
	public Version(String value) {
		this(0, 0, 0);
		String[] tokens = StringUtils.split(value, ".");
		int count = tokens.length;
		if (count < 1) {
			throw new IllegalArgumentException("Not enough digits in version "
					+ value);
		} else if (count > 3) {
			throw new IllegalArgumentException("Too more digits in version"
					+ value);
		} else {
			major = Integer.parseInt(tokens[0], 10);
			if (count > 1) {
				minor = Integer.parseInt(tokens[1], 10);
				if (count > 2) {
					patch = Integer.parseInt(tokens[2], 10);
				}
			}
		}
	}

	/**
	 * 0.0.0 version
	 */
	public Version() {

	}

	/**
	 * @return Returns the major.
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * @return Returns the minor.
	 */
	public int getMinor() {
		return minor;
	}

	/**
	 * @return Returns the patch.
	 */
	public int getPatch() {
		return patch;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return major * 100 + minor * 10 + patch;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Version) {
			Version v = (Version) obj;
			return new EqualsBuilder().append(major, v.major).append(minor,
					v.minor).append(patch, v.patch).isEquals();
		} else {
			return false;
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return major + "." + minor + "." + patch;
	}

	/**
	 * Compares two versions
	 * 
	 * @param o
	 *            Version to compare to.
	 * @return Result of comparison.
	 */
	public int compareTo(Version o) {
		if (major == o.major) {
			if (minor == o.minor) {
				if (patch == o.patch) {
					return 0;
				} else {
					return patch - o.patch;
				}
			} else {
				return minor - o.minor;
			}
		} else {
			return major - o.major;
		}
	}
}
