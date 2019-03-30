package net.sf.doolin.util.copy;

/**
 * Specification for the copy of a property between two beans.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 * 
 */
public interface PropertyCopier {

	/**
	 * Copies a property from an object to another.
	 * 
	 * @param service
	 *            Copy service
	 * @param name
	 *            Property name
	 * @param source
	 *            Source object
	 * @param target
	 *            Target object
	 */
	void copy(CopyService service, String name, Object source, Object target);

}
