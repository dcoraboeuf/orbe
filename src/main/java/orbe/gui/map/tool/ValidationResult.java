/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.tool;

/**
 * D�finit le r�sultat d'une validation.
 * 
 * @see ToolText#editValidate()
 * @author Damien Coraboeuf
 * @version $Id: ValidationResult.java,v 1.1 2006/11/23 08:53:01 guinnessman Exp $
 */
public enum ValidationResult {

	/**
	 * Validation ok avec changement
	 */
	VALIDATED,
	/**
	 * Validation ok, mais sans changement
	 */
	VALIDATED_NOCHANGE,
	/**
	 * Validation manqu�e
	 */
	FAILURE;

}
