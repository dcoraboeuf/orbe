/*
 * Created on Nov 24, 2006
 */
package orbe.gui;

/**
 * Attributes used in the application.
 * 
 * @author Damien Coraboeuf
 * @version $Id: IAttributes.java,v 1.1 2006/11/24 15:20:35 guinnessman Exp $
 */
public interface IAttributes {

	/**
	 * Nom de fichier à ouvrir au démarrage de l'application.
	 * 
	 * @see Main#main
	 * @see Startup#start(doolin.guif.GUIFApplication)
	 */
	String APPLICATION_STARTUP_FILE = "Startup.File";
}
