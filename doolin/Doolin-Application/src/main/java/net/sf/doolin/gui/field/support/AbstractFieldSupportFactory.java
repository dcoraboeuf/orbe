/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.field.support;

import java.util.HashMap;

import net.sf.doolin.util.Utils;

public abstract class AbstractFieldSupportFactory implements FieldSupportFactory {

	private HashMap<Class, Class> supportClasses = new HashMap<Class, Class>();

	public AbstractFieldSupportFactory() {
		init();
	}

	public <T extends FieldSupport> void registerFieldSupport(Class<T> fieldSupportClass, Class<? extends T> fieldSupportImplClass) {
		supportClasses.put(fieldSupportClass, fieldSupportImplClass);
	}

	protected abstract void init();

	@SuppressWarnings("unchecked")
	public <T extends FieldSupport> T getFieldSupport(Class<T> fieldSupportClass) {
		Class supportImplementation = supportClasses.get(fieldSupportClass);
		if (supportImplementation != null) {
			return (T) Utils.newInstance(supportImplementation);
		} else {
			throw new IllegalArgumentException("No implementation can be found for field support " + fieldSupportClass.getName());
		}
	}

}
