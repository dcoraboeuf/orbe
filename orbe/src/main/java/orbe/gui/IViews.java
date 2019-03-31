/*
 * Created on Tue Oct 03 14:42:35 CEST 2006
 */
package orbe.gui;

/**
 * Path and identifiers of views.
 * 
 * @version $Id: IViews.java,v 1.8 2006/12/06 12:00:31 guinnessman Exp $
 */
public interface IViews {

	/**
	 * ID of the main frame
	 */
	String ID_FRAME_MAIN = "FrameMain";

	/**
	 * New map
	 */
	String ID_DIALOG_NEW = "DialogNew";

	/**
	 * Map configuration
	 */
	String ID_DIALOG_MAP_SETTINGS = "DialogMapSettings";

	/**
	 * Map terrains
	 */
	String ID_DIALOG_MAP_TERRAINS = "DialogMapTerrains";

	/**
	 * Edition des styles de texte
	 */
	String ID_DIALOG_MAP_TEXT_STYLES = "DialogMapTextStyles";

	/**
	 * Edition des styles de ligne
	 */
	String ID_DIALOG_MAP_STYLE_LINE = "DialogMapStyleLine";

	/**
	 * Export de la carte
	 */
	String ID_DIALOG_EXPORT = "DialogExport";

}
