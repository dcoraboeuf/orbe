/*
 * Created on Nov 8, 2006
 */
package orbe.gui.message;

import java.io.File;

/**
 * Message généré à l'ouverture / sauvegarde d'un fichier.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeMessageFile.java,v 1.1 2006/11/29 11:41:59 guinnessman Exp $
 */
public class OrbeMessageFile extends OrbeSimpleMessage<File> {

	public OrbeMessageFile(File value) {
		super(value);
	}

}
