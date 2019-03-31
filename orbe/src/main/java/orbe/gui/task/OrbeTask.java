/*
 * Created on Oct 26, 2006
 */
package orbe.gui.task;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import orbe.gui.map.core.OrbeControler;

public abstract class OrbeTask extends AbstractUndoableEdit {

	private OrbeControler orbeControler;

	public OrbeTask(OrbeControler orbeControler) {
		this.orbeControler = orbeControler;
	}

	protected void setDirty() {
		orbeControler.getContext().setDirty();
	}

	public OrbeControler getControler() {
		return orbeControler;
	}

	@Override
	public void redo() throws CannotRedoException {
		process();
		super.redo();
	}

	@Override
	public void undo() throws CannotUndoException {
		process();
		super.undo();
	}

	public abstract void process();

	/**
	 * @return <code>true</code> si la tâche induit une modification de la
	 *         structure du fichier. Renvoit <code>true</code> par défaut.
	 */
	public boolean isSetDirty() {
		return true;
	}
	
	public abstract String getPresentationName();
}
