/*
 * Created on Nov 16, 2006
 */
package orbe.model.style;

/**
 * Abstract definition of a style.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Style.java,v 1.3 2006/11/17 12:05:18 guinnessman Exp $
 */
public interface Style<S extends Style> {

	/**
	 * @return ID du style
	 */
	int getId();

	/**
	 * @param id
	 *            ID of the style
	 */
	void setId(int id);

	/**
	 * @return Nom du style
	 */
	String getName();

	/**
	 * Création d'une copie du style
	 * 
	 * @return Copie du style
	 */
	Style createCopy();

	/**
	 * Copy from another style
	 * 
	 * @param style
	 *            Style to copy from.
	 */
	void copy(S style);

}
