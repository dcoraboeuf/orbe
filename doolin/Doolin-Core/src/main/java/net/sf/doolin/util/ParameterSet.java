/*
 * Created on 13 oct. 2004
 */
package net.sf.doolin.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * Set of parameters that provides the developer a list of utility methods to
 * retrieve values.
 * 
 * @version $Id: ParameterSet.java,v 1.2 2007/07/31 15:32:49 guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class ParameterSet {
	/**
	 * Parameters
	 */
	private HashMap<String, String> parameters = new HashMap<String, String>();

	/**
	 * Empty constructor
	 */
	public ParameterSet() {
	}

	/**
	 * Constructor from an existing map
	 * 
	 * @param map
	 *            Existing map. This map is not directly used by the parameter
	 *            set since it is duplicated.
	 */
	public ParameterSet(Map<String, String> map) {
		parameters = new HashMap<String, String>(map);
	}

	/**
	 * Constructor from a set of properties
	 * 
	 * @param properties
	 *            Set of properties to get parameters from
	 */
	public ParameterSet(Properties properties) {
		parameters = new HashMap<String, String>();
		Iterator<Object> names = properties.keySet().iterator();
		while (names.hasNext()) {
			String name = (String) names.next();
			String value = properties.getProperty(name);
			parameters.put(name, value);
		}
	}

	/**
	 * Add a parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param value
	 *            Parameter's value
	 */
	public void addParam(String name, String value) {
		parameters.put(name, value);
	}

	/**
	 * Adds an integer parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param value
	 *            Parameter's value
	 */
	public void addParam(String name, int value) {
		addParam(name, String.valueOf(value));
	}

	/**
	 * Adds a boolean parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param value
	 *            Parameter's value
	 */
	public void addParam(String name, boolean value) {
		addParam(name, String.valueOf(value));
	}

	/**
	 * Adds a set of parameters
	 * 
	 * @param params
	 *            Parameters to be added
	 */
	public void addParams(Map<String, String> params) {
		parameters.putAll(params);
	}

	/**
	 * Get a parameter as a string
	 * 
	 * @param name
	 *            Parameter's name
	 * @return Parameter's value or <code>null</code> if not found.
	 */
	public String getParam(String name) {
		return parameters.get(name);
	}

	/**
	 * Get a string parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param mandatory
	 *            Indicates if the parameter <i>must</i> be present.
	 * @param defaultValue
	 *            Value to be used if the parameter is not found and if it is
	 *            not mandatory.
	 * @return Parameter's value or <code>null</code> if not found.
	 * @throws MissingParameterException
	 *             If the parameter is not found when it is mandatory.
	 */
	public String getParam(String name, boolean mandatory, String defaultValue) throws MissingParameterException {
		String value = getParam(name);
		if (StringUtils.isEmpty(value)) {
			if (mandatory) {
				throw new MissingParameterException(name);
			} else {
				return defaultValue;
			}
		} else {
			return value;
		}
	}

	/**
	 * Get an int parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param mandatory
	 *            Indicates if the parameter <i>must</i> be present.
	 * @param defaultValue
	 *            Value to be used if the parameter is not found and if it is
	 *            not mandatory.
	 * @return Parameter's value or <code>null</code> if not found.
	 * @throws MissingParameterException
	 *             If the parameter is not found when it is mandatory.
	 */
	public int getIntParam(String name, boolean mandatory, int defaultValue) throws MissingParameterException {
		String value = getParam(name);
		if (StringUtils.isEmpty(value)) {
			if (mandatory) {
				throw new MissingParameterException(name);
			} else {
				return defaultValue;
			}
		} else {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException ex) {
				throw new MissingParameterException(name);
			}
		}
	}

	/**
	 * Get a double parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param mandatory
	 *            Indicates if the parameter <i>must</i> be present.
	 * @param defaultValue
	 *            Value to be used if the parameter is not found and if it is
	 *            not mandatory.
	 * @return Parameter's value or <code>null</code> if not found.
	 * @throws MissingParameterException
	 *             If the parameter is not found when it is mandatory.
	 */
	public double getDoubleParam(String name, boolean mandatory, double defaultValue) throws MissingParameterException {
		String value = getParam(name);
		if (StringUtils.isEmpty(value)) {
			if (mandatory) {
				throw new MissingParameterException(name);
			} else {
				return defaultValue;
			}
		} else {
			try {
				return Double.parseDouble(value);
			} catch (NumberFormatException ex) {
				throw new MissingParameterException(name);
			}
		}
	}

	/**
	 * Get a boolean parameter
	 * 
	 * @param name
	 *            Parameter's name
	 * @param mandatory
	 *            Indicates if the parameter <i>must</i> be present.
	 * @param defaultValue
	 *            Value to be used if the parameter is not found and if it is
	 *            not mandatory.
	 * @return Parameter's value or <code>null</code> if not found.
	 * @throws MissingParameterException
	 *             If the parameter is not found when it is mandatory.
	 */
	public boolean getBooleanParam(String name, boolean mandatory, boolean defaultValue) throws MissingParameterException {
		String value = getParam(name);
		if (StringUtils.isEmpty(value)) {
			if (mandatory) {
				throw new MissingParameterException(name);
			} else {
				return defaultValue;
			}
		} else {
			return Boolean.valueOf(value).booleanValue();
		}
	}

	/**
	 * Read-only access to parameters
	 * 
	 * @return A read-only version of the underlying parameter's map.
	 */
	public Map<String, String> getParams() {
		return Collections.unmodifiableMap(parameters);
	}

	/**
	 * Gets a parameter as a constant name for a given class and then uses this
	 * constant name to get its actual value.
	 * 
	 * For example, the call to
	 * <code>getConstant("key", SwingConstants.class, true, null)</code> will
	 * return the value for <code>SwingConstant.LEFT</code> if the value
	 * associated with "key" is "LEFT".
	 * 
	 * @param name
	 *            Key name
	 * @param type
	 *            Type that contains the constant definition
	 * @param mandatory
	 *            <code>true</code> if the value is required
	 * @param defaultValue
	 *            Default value if the value is not required and if the key is
	 *            not found
	 * @return Value or <code>null</code>
	 */
	public Object getConstant(String name, Class<?> type, boolean mandatory, Object defaultValue) {
		String value = getParam(name, mandatory, null);
		if (value == null) {
			return defaultValue;
		} else {
			try {
				Field field = type.getField(value);
				return field.get(null);
			} catch (Exception ex) {
				throw new RuntimeException("Cannot get field " + value + " on " + type.getName(), ex);
			}
		}
	}
}
