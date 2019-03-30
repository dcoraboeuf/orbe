/*
 * Created on Sep 18, 2007
 */
package net.sf.doolin.gui.service.support;

import java.awt.Cursor;
import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.gui.service.CursorFactory;

/**
 * Ancestor for cursor factories
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public abstract class AbstractCursorFactory implements CursorFactory {

	/**
	 * List of cursors
	 */
	private Map<String, Cursor> cursors = new HashMap<String, Cursor>();

	/**
	 * @see net.sf.doolin.gui.service.CursorFactory#getCursor(java.lang.String)
	 */
	public Cursor getCursor(String cursorId) {
		Cursor cursor = cursors.get(cursorId);
		if (cursor != null) {
			return cursor;
		} else {
			cursor = loadCursor(cursorId);
			cursors.put(cursorId, cursor);
			return cursor;
		}
	}

	/**
	 * Loads a cursor according to its name
	 * 
	 * @param name
	 *            Cursor name
	 * @return Custom cursor or <code>null</code> if none is defined for this
	 *         name.
	 */
	protected abstract Cursor loadCursor(String name);

}
