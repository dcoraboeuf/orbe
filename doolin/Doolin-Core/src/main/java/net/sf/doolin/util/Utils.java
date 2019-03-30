/*
 * Created on 10 mars 2005
 */
package net.sf.doolin.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Set of utility methods.
 * 
 * @author damien
 * @version $Id: Utils.java,v 1.3 2007/07/31 15:32:49 guinnessman Exp $
 */
public class Utils {
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(Utils.class);

	/**
	 * ISO format
	 */
	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

	/**
	 * Get the default class name to use according to specifications done in
	 * JVM. Order of priority is :
	 * <ol>
	 * <li>System property whose name is <code>baseClass</code></li>
	 * <li><code>defaultClass</code></li>
	 * </ol>
	 * 
	 * @param baseClass
	 *            Class whose name is the reference
	 * @param defaultClass
	 *            Class to be used if no corresponding class is found
	 * @return Found class name TODO Read the META-INF/<i>baseClass</i> file
	 *         to get the class name
	 */
	public static String getClassName(Class baseClass, Class defaultClass) {
		String className = System.getProperty(baseClass.getName());
		if (StringUtils.isEmpty(className)) {
			return defaultClass.getName();
		} else {
			return className;
		}
	}

	/**
	 * List of properties for a set of objects
	 * 
	 * @param items
	 *            List of objects to loop on
	 * @param property
	 *            Property to get for each object
	 * @return List of all properties values
	 */
	public static List<Object> getProperties(Collection items, String property) {
		ArrayList<Object> properties = new ArrayList<Object>();
		for (Iterator i = items.iterator(); i.hasNext();) {
			Object item = i.next();
			try {
				Object value = PropertyUtils.getProperty(item, property);
				properties.add(value);
			} catch (Exception ex) {
				log.error("Cannot get property " + property + " on " + item);
			}
		}
		// Ok
		return properties;
	}

	/**
	 * Get a collection has a list
	 * 
	 * @param set
	 *            Incoming collection
	 * @param <T>
	 *            Type of items in the set
	 * @return Resulting list
	 */
	public static <T> List<T> asList(Collection<T> set) {
		if (set instanceof List) {
			return (List<T>) set;
		} else {
			return new ArrayList<T>(set);
		}
	}

	/**
	 * Generates a unique ID
	 * 
	 * @return Generated UID
	 * @see UUID#randomUUID()
	 * @see UUID#getMostSignificantBits()
	 */
	public static long generateUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.getMostSignificantBits();
	}

	/**
	 * Parses a URL query
	 * 
	 * @param url
	 *            URL to parse
	 * @return Extracted parameters. The returned map can be empty but never
	 *         <code>null</code>.
	 */
	public static Map<String, String> parseQuery(URL url) {
		String query = url.getQuery();
		return parseQuery(query);
	}

	/**
	 * Parses a query
	 * 
	 * @param query
	 *            Query to parse
	 * @return Extracted parameters. The returned map can be empty but never
	 *         <code>null</code>.
	 */
	public static Map<String, String> parseQuery(String query) {
		HashMap<String, String> params = new HashMap<String, String>();
		if (StringUtils.isNotBlank(query)) {
			String[] tokens = StringUtils.split(query, "&");
			for (String token : tokens) {
				String[] pair = StringUtils.split(token, "=");
				if (pair.length == 1) {
					params.put(pair[0], pair[0]);
				} else if (pair.length == 2) {
					params.put(pair[0], pair[1]);
				}
			}
		}
		// Ok
		return params;
	}

	/**
	 * Converts a sequence of strings to a map. Each string must have the format :
	 * &lt;key&gt;=&lt;value&gt;.
	 * 
	 * @param strings
	 *            List of strings
	 * @return Corresponding map (not <code>null</code>)
	 */
	public static Map<String, String> toMap(String[] strings) {
		HashMap<String, String> map = new HashMap<String, String>();
		if (strings != null && strings.length != 0) {
			for (String token : strings) {
				if (StringUtils.isNotBlank(token)) {
					String[] tokens = StringUtils.split(token, "=");
					if (tokens.length == 1) {
						map.put(tokens[0], tokens[0]);
					} else {
						map.put(tokens[0], tokens[1]);
					}
				}
			}
		}
		return map;
	}

	/**
	 * Formats a date as ISO 8601 format
	 * 
	 * @param date
	 *            Date to format
	 * @return Formatted date
	 */
	public static String toISOFormat(Date date) {
		if (date == null) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat(ISO_DATE_FORMAT);
			return format.format(date);
		}
	}

	/**
	 * Parses a date as ISO 8601
	 * 
	 * @param value
	 *            ISO8601 date
	 * @return Parsed date
	 * @throws ParseException
	 *             If the value cannot be parsed
	 */
	public static Date fromISOFormat(String value) throws ParseException {
		if (StringUtils.isEmpty(value)) {
			return null;
		} else {
			SimpleDateFormat format = new SimpleDateFormat(ISO_DATE_FORMAT);
			return format.parse(value);
		}
	}

	/**
	 * Copy properties from one bean to the other
	 * 
	 * @param destination
	 *            Destination bean
	 * @param origin
	 *            Origin bean
	 */
	public static void copyProperties(Object destination, Object origin) {
		try {
			BeanUtils.copyProperties(destination, origin);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Cannot copy properties", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Cannot copy properties", e.getTargetException());
		}
	}

	/**
	 * Creates a new instance
	 * 
	 * @param clazz
	 *            Type of the instance to create
	 * @param <T>
	 *            Type of the class
	 * @return Instance of the class
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			T o = clazz.newInstance();
			return o;
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException(StringCodes.STRING_UTILS_NEW_INSTANCE_ERROR, ex, clazz.getName());
		}
	}

	/**
	 * Creates a new instance of a class given its name.
	 * 
	 * @param className
	 *            Type name of the instance to create
	 * @return Instance of the given type
	 */
	public static Object newInstance(String className) {
		try {
			Class clazz = Class.forName(className);
			Object o = clazz.newInstance();
			return o;
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException(StringCodes.STRING_UTILS_NEW_INSTANCE_ERROR, ex, className);
		}
	}

	/**
	 * Creates a new instance of a class given its name and a set of properties
	 * 
	 * @param className
	 *            Type name of the instance to create
	 * @param params
	 *            Parameters to set in the newly created instance
	 * @return Instance of the given type where properties have been set
	 *         according to the given parameters
	 * @see #setProperties(Object, ParameterSet)
	 * @see #newInstance(String)
	 */
	public static Object newInstance(String className, ParameterSet params) {
		Object o = newInstance(className);
		setProperties(o, params);
		return o;
	}

	/**
	 * Set the properties of an object
	 * 
	 * @param o
	 *            Object to set
	 * @param params
	 *            Parameters to set in the given object
	 */
	public static void setProperties(Object o, ParameterSet params) {
		if (params != null) {
			for (Map.Entry<String, String> entry : params.getParams().entrySet()) {
				String name = entry.getKey();
				String value = entry.getValue();
				try {
					BeanUtils.setProperty(o, name, value);
				} catch (Exception ex) {
					throw new CodeException(StringCodes.STRING_UTILS_SET_PROPERTIES_ERROR, ex, o, name, value, ex.getLocalizedMessage());
				}
			}
		}
	}

	/**
	 * Duplicates an object using serialization.
	 * 
	 * @param o
	 *            Object to close
	 * @param <T>
	 *            Type of the object
	 * @return Cloned object
	 * @see SerializationUtils#clone(Serializable)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T o) {
		return (T) SerializationUtils.clone(o);
	}

	/**
	 * Get an annotation from the class or from its parent classes
	 * 
	 * @param oClass
	 *            Class to search annotation in.
	 * @param aClass
	 *            Annotation to search
	 * @param <A>
	 *            Annotation type
	 * @return Annotation or <code>null</code> if not found.
	 */
	public static <A extends Annotation> A getAnnotation(Class<?> oClass, Class<A> aClass) {
		A a = oClass.getAnnotation(aClass);
		if (a != null) {
			return a;
		} else {
			Class<?> superClass = oClass.getSuperclass();
			if (superClass != null) {
				return getAnnotation(superClass, aClass);
			} else {
				return null;
			}
		}
	}

	/**
	 * Get the simplified (short) name of a class. It the class name without the
	 * package and/or encloding class name.
	 * 
	 * @param c
	 *            Class whose name must be simplified
	 * @return Simplified class name
	 */
	public static String getSimpleName(Class c) {
		return StringUtils.substringAfterLast(c.getName(), ".");
	}

	/**
	 * Protected call to <code>Class.forName</code>
	 * 
	 * @param className
	 *            Class name to get the Class instance from
	 * @return Class instance
	 */
	public static Class forClass(String className) {
		try {
			Class clazz = Class.forName(className);
			return clazz;
		} catch (Exception ex) {
			throw new CodeException(StringCodes.STRING_UTILS_FOR_CLASS_ERROR, ex, className);
		}
	}

	/**
	 * Gets the value for a constant.
	 * 
	 * @param type
	 *            Type that contains the constant
	 * @param name
	 *            Name of the constant
	 * @return Constant value
	 */
	public static Object getConstant(Class type, String name) {
		try {
			Field constant = type.getField(name);
			return constant.get(null);
		} catch (Exception ex) {
			throw new RuntimeException("Cannot get constant " + name + " from " + type.getName(), ex);
		}
	}

	/**
	 * Gets a resource URL from a reference class and a resource path. The
	 * resource URL is determined by computing the resource path from the
	 * reference class.
	 * 
	 * If the reference class is <code>null</code>, the path is computed
	 * using the current class (i.e. the {@link Utils} class).
	 * 
	 * If the URL cannot be accessed, a CodeException with code
	 * {@link StringCodes#STRING_ERROR_RESOURCE_NOT_FOUND} is thrown.
	 * 
	 * @param referenceClass
	 *            Reference for the resource path
	 * @param path
	 *            Resource path from the reference class
	 * @return Resource URL
	 * @throws CodeException
	 *             If the URL cannot be found
	 */
	public static URL getResource(Class referenceClass, String path) throws CodeException {
		URL url;
		if (referenceClass != null) {
			url = referenceClass.getResource(path);
		} else {
			url = Utils.class.getResource(path);
		}
		if (url != null) {
			return url;
		} else {
			throw new CodeException(StringCodes.STRING_ERROR_RESOURCE_NOT_FOUND, path);
		}
	}

	/**
	 * Calls a method using introspection.
	 */
	public static Object callMethod(Object target, String methodName, Object... parameters) {
		try {
			return MethodUtils.invokeMethod(target, methodName, parameters);
		} catch (InvocationTargetException ex) {
			Throwable tex = ex.getTargetException();
			if (tex instanceof CodeException) {
				throw (CodeException) tex;
			} else {
				throw new CodeException(StringCodes.STRING_UTILS_METHODCALL_ERROR, tex, target, methodName, tex);
			}
		} catch (Exception ex) {
			throw new CodeException(StringCodes.STRING_UTILS_METHODCALL_ERROR, ex, target, methodName, ex);
		}
	}
}
