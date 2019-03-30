package net.sf.doolin.util.copy;

import net.sf.doolin.util.Utils;

/**
 * This copier just changes the class of the target property and copies children
 * properties.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * 
 */
public class NewClassPropertyCopier extends AbstractPropertyCopier {

	private Class targetPropertyClass;

	public NewClassPropertyCopier(Class targetPropertyClass) {
		this.targetPropertyClass = targetPropertyClass;
	}

	@Override
	public void copy(CopyService service, String name, Object source, Object target) {
		Object sourceProperty = getProperty(source, name);
		if (sourceProperty != null) {
			@SuppressWarnings("unchecked")
			Object targetProperty = Utils.newInstance(targetPropertyClass);
			service.copy(sourceProperty, targetProperty);
			setProperty(target, name, targetProperty);
		} else {
			setProperty(target, name, null);
		}
	}

}
