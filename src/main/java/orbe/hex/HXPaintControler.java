/*
 * Created on Nov 8, 2006
 */
package orbe.hex;

/**
 * This interface is used to control painting operations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: HXPaintControler.java,v 1.1 2006/11/08 13:34:59 guinnessman Exp $
 */
public interface HXPaintControler {

	/**
	 * Is this point candidate for filling?
	 * 
	 * @param hx
	 *            Hex to control
	 * @return Result of test
	 */
	boolean canFill(HXPoint hx);

	/**
	 * Actually fills the hex
	 * 
	 * @param hx
	 *            Hex to fill
	 */
	void fill(HXPoint hx);

}
