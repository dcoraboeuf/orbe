/*
 * Created on 5 déc. 06
 */
package orbe.model.style;

/**
 * Flags pour les styles de ligne, utilisés pour la lecture des fichiers au
 * format 0.2.
 * 
 * @author Damien Coraboeuf
 * @version $Id: StyleLineFlags.java,v 1.1 2006/12/05 16:01:48 guinnessman Exp $
 */
public interface StyleLineFlags {
	int FLAG_02_KIND_HEX = 0;

	int FLAG_02_KIND_EDGE = 1;

	int FLAG_02_KIND_OTHER = 2;
}
