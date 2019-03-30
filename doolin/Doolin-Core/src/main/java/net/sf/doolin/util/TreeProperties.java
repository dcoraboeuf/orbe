/*
 * Created on Jul 31, 2007
 */
package net.sf.doolin.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Wraps a property set or a map as a tree view.
 * 
 * @param <T>
 *            Type of objects stored in the tree properties.
 * @author Damien Coraboeuf
 * @version $Id: TreeProperties.java,v 1.1 2007/07/31 16:43:36 guinnessman Exp $
 */
public class TreeProperties<T> {

	/**
	 * Root map
	 */
	private TreeMap<String, Object> rootMap = new TreeMap<String, Object>();

	/**
	 * Constructor for an empty tree.
	 * 
	 */
	public TreeProperties() {
	}

	/**
	 * Constructor from a map.
	 * 
	 * @param map
	 *            Map
	 */
	public TreeProperties(Map<String, T> map) {
		for (Map.Entry<String, T> entry : map.entrySet()) {
			String key = entry.getKey();
			T value = entry.getValue();
			if (StringUtils.isNotBlank(key) && value != null) {
				put(key, value);
			}
		}
	}

	/**
	 * Constructor from a property set.
	 * 
	 * @param properties
	 *            Properties
	 */
	public TreeProperties(Properties properties) {
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String key = ObjectUtils.toString(entry.getKey(), null);
			@SuppressWarnings("unchecked")
			T value = (T) entry.getValue();
			if (StringUtils.isNotBlank(key) && value != null) {
				put(key, value);
			}
		}
	}

	/**
	 * Registers a value using a key. The key nodes are separated by dots (".").
	 * 
	 * @param key
	 *            Key
	 * @param value
	 *            Value to store
	 */
	public void put(String key, T value) {
		put(rootMap, key, value);
	}

	private void put(Map<String, Object> map, String key, T value) {
		String prefix = StringUtils.substringBefore(key, ".");
		String suffix = StringUtils.substringAfter(key, ".");
		// Node
		if (StringUtils.isBlank(suffix)) {
			map.put(prefix, value);
		}
		// Map
		else {
			// Get or create the map
			@SuppressWarnings("unchecked")
			Map<String, Object> submap = (Map<String, Object>) map.get(prefix);
			if (submap == null) {
				submap = new TreeMap<String, Object>();
				map.put(prefix, submap);
			}
			// Registers the suffix
			put(submap, suffix, value);
		}
	}

	/**
	 * Gets a value using a key. The key nodes are separated by dots (".").
	 * 
	 * @param key
	 *            Key
	 * @return Associated value or null
	 */
	public T get(String key) {
		return get(rootMap, key);
	}

	/**
	 * Gets a subtree using a key. The key nodes are separated by dots (".").
	 * 
	 * @param key
	 *            Key
	 * @return Associated tree or null
	 */
	public TreeProperties<T> getTree(String key) {
		return getTree(rootMap, key);
	}

	private T get(Map<String, Object> map, String key) {
		String prefix = StringUtils.substringBefore(key, ".");
		String suffix = StringUtils.substringAfter(key, ".");
		// Node
		if (StringUtils.isBlank(suffix)) {
			Object value = map.get(prefix);
			if (value instanceof Map) {
				throw new IllegalArgumentException(key + " contains a set of properties and does not define a node.");
			} else {
				@SuppressWarnings("unchecked")
				T t = (T) value;
				return t;
			}
		}
		// Map
		else {
			// Get the map
			@SuppressWarnings("unchecked")
			Map<String, Object> submap = (Map<String, Object>) map.get(prefix);
			if (submap == null) {
				return null;
			}
			// Looks for the suffix
			return get(submap, suffix);
		}
	}

	private TreeProperties<T> getTree(Map<String, Object> map, String key) {
		String prefix = StringUtils.substringBefore(key, ".");
		String suffix = StringUtils.substringAfter(key, ".");
		// Node
		if (StringUtils.isBlank(suffix)) {
			Object value = map.get(prefix);
			if (value instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, T> submap = (Map<String, T>) value;
				return new TreeProperties<T>(submap);
			} else {
				throw new IllegalArgumentException(key + " defines a node and does not contain a set of properties.");
			}
		}
		// Map
		else {
			// Get the map
			@SuppressWarnings("unchecked")
			Map<String, Object> submap = (Map<String, Object>) map.get(prefix);
			if (submap == null) {
				return null;
			}
			// Looks for the suffix
			return getTree(submap, suffix);
		}
	}

	/**
	 * @return An iteration over all map entries in this tree.
	 */
	public Iterable<Pair<String, TreeProperties<T>>> entries() {
		ArrayList<Pair<String, TreeProperties<T>>> list = new ArrayList<Pair<String, TreeProperties<T>>>();
		// Loop over entries
		for (Map.Entry<String, Object> entry : rootMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, T> submap = (Map<String, T>) value;
				TreeProperties<T> subTree = new TreeProperties<T>(submap);
				list.add(new Pair<String, TreeProperties<T>>(key, subTree));
			}
		}
		// Ok
		return list;
	}

}
