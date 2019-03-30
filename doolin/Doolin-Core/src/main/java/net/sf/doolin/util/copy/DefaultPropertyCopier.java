package net.sf.doolin.util.copy;

/**
 * This copier just copies the value of the property, without any conversion.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * 
 */
public class DefaultPropertyCopier extends AbstractPropertyCopier {

	@Override
	public void copy(CopyService service, String name, Object source, Object target) {
		// Gets the property on the source
		Object property = getProperty(source, name);
		// Sets the property on the target
		setProperty(target, name, property);
	}

}
