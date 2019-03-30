package net.sf.doolin.util.copy;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Copy service
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class CopyService {

	private static final PropertyCopier defaultPropertyCopier = new DefaultPropertyCopier();

	private Map<String, PropertyCopier> copiers = new HashMap<String, PropertyCopier>();
	
	public void registerCopier (Class sourceClass, Class targetClass, String propertyName, PropertyCopier copier) {
		String key = sourceClass.getName() + "#" + targetClass.getName() + "#" + propertyName;
		copiers.put(key, copier);
	}

	public void copy(Object source, Object target) {
		// Get all the properties of the source
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(source);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String propertyName = propertyDescriptor.getName();
			// Skipping 'class'
			if ("class".equals(propertyName)) {
				// Does nothing
			}
			// Other properties
			else {
				if (PropertyUtils.isReadable(source, propertyName) && PropertyUtils.isWriteable(target, propertyName)) {
					// Copy specification
					PropertyCopier copier = getPropertyCopier(source, target, propertyName);
					// No specification
					if (copier == null) {
						defaultPropertyCopier.copy(this, propertyName, source, target);
					}
					// Uses the specification
					else {
						copier.copy(this, propertyName, source, target);
					}
				}
			}
		}
	}

	protected PropertyCopier getPropertyCopier(Object source, Object target, String propertyName) {
		String key = source.getClass().getName() + "#" + target.getClass().getName() + "#" + propertyName;
		return copiers.get(key);
	}

}
