/*
 * Created on Oct 3, 2006
 */
package orbe.gui.context;

import java.io.File;

import javax.swing.undo.UndoManager;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.service.GUIStrings;
import orbe.gui.message.OrbeMessageContextDirty;
import orbe.gui.message.OrbeMessageContextEdit;
import orbe.gui.message.OrbeMessageContextSettings;
import orbe.gui.message.OrbeMessageFile;
import orbe.gui.task.OrbeTask;
import orbe.model.OrbeMap;
import orbe.model.OrbeMapConfig;
import orbe.model.OrbeMapFactory;

import org.apache.commons.lang.ObjectUtils;

/**
 * Contexte ouvert.
 * 
 * @author Damien Coraboeuf
 * @version $Id: OrbeContext.java,v 1.15 2006/11/29 15:22:25 guinnessman Exp $
 */
public class OrbeContext {

	private File file;

	private boolean dirty;

	private UndoManager undoManager = new UndoManager();

	private OrbeMap map;

	protected OrbeContext() {
		Bus.get().subscribe(OrbeMessageContextSettings.class, new Subscriber<OrbeMessageContextSettings>() {

			public void receive(OrbeMessageContextSettings message) {
				if (map == message.getValue()) {
					setDirty();
				}
			}

		});
	}

	public OrbeContext(File f) {
		this();
		file = f;
		map = OrbeMapFactory.getInstance().readMap(f);
		dirty = false;
		Bus.get().publish(new OrbeMessageFile(file));
	}

	public OrbeContext(OrbeMapConfig config) {
		this();
		file = null;
		map = OrbeMapFactory.getInstance().createMap(config);
		dirty = true;
	}

	public boolean isDirty() {
		return dirty;
	}

	public File getFile() {
		return file;
	}

	public OrbeMap getMap() {
		return map;
	}

	public void orbeMapChanged(OrbeMap map) {
		if (this.map == map) {
			setDirty();
		}
	}

	@Override
	public String toString() {
		return ObjectUtils.toString(file, GUIStrings.get("File.untitled"));
	}

	public void hasBeenSaved() {
		dirty = false;
		Bus.get().publish(new OrbeMessageContextDirty(this));
	}

	public void setFile(File f) {
		file = f;
		dirty = true;
	}

	public void addEdit(OrbeTask edit) {
		undoManager.addEdit(edit);
		if (edit.isSetDirty()) {
			setDirty();
		}
		Bus.get().publish(new OrbeMessageContextEdit(this));
	}

	public boolean canUndo() {
		return undoManager.canUndo();
	}

	public boolean canRedo() {
		return undoManager.canRedo();
	}

	public void undo() {
		if (undoManager.canUndo()) {
			undoManager.undo();
			setDirty();
			Bus.get().publish(new OrbeMessageContextEdit(this));
		}
	}

	public void redo() {
		if (undoManager.canRedo()) {
			undoManager.redo();
			setDirty();
			Bus.get().publish(new OrbeMessageContextEdit(this));
		}
	}

	public void setDirty() {
		dirty = true;
		Bus.get().publish(new OrbeMessageContextDirty(this));
	}

	public String getUndoName() {
		return undoManager.getUndoPresentationName();
	}

	public String getRedoName() {
		return undoManager.getRedoPresentationName();
	}

}
