/*
 * Created on Nov 23, 2006
 */
package orbe.gui.map.tool.text;

import java.awt.event.MouseEvent;

import orbe.gui.map.tool.ToolText;

/**
 * Cette classe est utilis�e pour d�tacher des tâches de l'outil Texte
 * (d�placement, rotation) � d'autres classes.
 * 
 * @author Damien Coraboeuf
 * @version $Id: ToolTextDelegate.java,v 1.1 2006/11/23 16:22:22 guinnessman Exp $
 */
public abstract class ToolTextDelegate {

	/**
	 * Parent tool
	 */
	private ToolText tool;

	/**
	 * Initialisation.
	 * 
	 * @param tool
	 *            Outil cr�ant la d�l�gation.
	 */
	public ToolTextDelegate(ToolText tool) {
		this.tool = tool;
	}

	/**
	 * Initialisation de la d�l�gation
	 * 
	 * @param e
	 *            Ev�n�ment declench�
	 */
	public abstract void start(MouseEvent e);

	/**
	 * Continuation de la d�l�gation
	 * 
	 * @param e
	 *            Ev�n�ment declench�
	 */
	public abstract void drag(MouseEvent e);

	/**
	 * Fin de la d�l�gation
	 * 
	 * @param e
	 *            Ev�n�ment declench�
	 */
	public abstract void end(MouseEvent e);

	/**
	 * Annulation de la d�l�gation
	 */
	public abstract void cancel();

	/**
	 * @return Parent tool.
	 */
	public ToolText getTool() {
		return tool;
	}

}
