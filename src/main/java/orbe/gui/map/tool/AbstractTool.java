/*
 * Created on Oct 4, 2006
 */
package orbe.gui.map.tool;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.gui.core.View;
import orbe.gui.context.OrbeContext;
import orbe.gui.map.core.OrbeControler;
import orbe.gui.map.core.ToolSettings;
import orbe.gui.map.options.PanelOptions;
import orbe.gui.message.OrbeMessageTool;

/**
 * Implémentation par défaut des méthodes d'un outil.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractTool.java,v 1.11 2006/11/23 16:22:23 guinnessman Exp $
 */
public abstract class AbstractTool implements Tool {

	public PanelOptions getPanelOptions() {
		return null;
	}

	private OrbeControler controler;

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
	
	protected View getGUIFView () {
		return controler.getGUIFView();
	}

	/**
	 * Quand le curseur est dans la vue, réclame le focus.
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		getControler().getComponent().requestFocusInWindow();
	}

	public void mouseExited(MouseEvent e) {
		controler.setCursor(null);
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected ToolSettings getToolSettings() {
		return getControler().getToolSettings();
	}

	protected OrbeContext getContext() {
		return getControler().getContext();
	}

	public OrbeControler getControler() {
		return controler;
	}

	public void setControler(OrbeControler ctrl) {
		controler = ctrl;
	}

	/**
	 * Par défaut, intercepte la touche Escape pour désactiver l'outil courant.
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			onEscape();
		}
	}

	/**
	 * Cette méthode est appelée quand la touche Escape est tapée. Par défaut,
	 * cette méthode déselectionne l'outil.
	 */
	protected void onEscape() {
		Bus.get().publish(new OrbeMessageTool(null));
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Méthode utilitaire qui installe le curseur <code>cursor</code> dans le
	 * composant actif.
	 * 
	 * @param cursor
	 *            Curseur à installer.
	 */
	public void setCursor(Cursor cursor) {
		getControler().setCursor(cursor);
	}

	/**
	 * Par défaut ne fait rien.
	 * 
	 * @see orbe.gui.map.tool.Tool#register()
	 */
	public void register() {
	}

	/**
	 * Par défaut ne fait rien.
	 * 
	 * @see orbe.gui.map.tool.Tool#unregister()
	 */
	public void unregister() {
	}

}
