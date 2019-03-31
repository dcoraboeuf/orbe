/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.tool;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.options.PanelOptions;

/**
 * Définition d'un outil et des m�thodes qui lui permettent d'interagir avec
 * l'environnement Orbe.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Tool.java,v 1.6 2006/11/22 20:05:10 guinnessman Exp $
 */
public interface Tool extends MouseListener, MouseMotionListener, KeyListener {

	String getId();

	void setControler(OrbeControler controler);

	OrbeControler getControler();

	PanelOptions getPanelOptions();

	/**
	 * Notifie l'outil qu'il est maintenant actif.
	 */
	void register();

	/**
	 * Notifie l'outil qu'il est maintenant inactif.
	 */
	void unregister();
}
