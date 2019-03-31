/*
 * Created on 6 dec. 06
 */
package orbe.gui.form;

import java.io.File;

import net.sf.doolin.gui.service.Memento;
import net.sf.doolin.gui.service.Preferences;

import orbe.gui.context.OrbeContext;

/**
 * Export d'une carte.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FormExport.java,v 1.1 2006/12/06 12:00:36 guinnessman Exp $
 */
public class FormExport implements Memento {

	private static final String PREF_MARGIN = "FormExport.margin";

	private static final String PREF_FILE = "FormExport.file";

	private static final String PREF_SLICES = "FormExport.slices";

	private static final String PREF_SLICES_H = "FormExport.slicesH";

	private static final String PREF_SLICES_V = "FormExport.slicesV";

	/**
	 * Contexte associ�
	 */
	private OrbeContext context;

	/**
	 * Fichier de destination
	 */
	private File file;

	/**
	 * decoupage
	 */
	private boolean slices;
	
	/**
	 * Recoupement
	 */
	private int margin;
	
	/**
	 * decoupage
	 */
	private int slicesH = 1;
	private int slicesV = 1;

	/**
	 * Constructeur.
	 * 
	 * @param context
	 *            Contexte associ�.
	 */
	public FormExport(OrbeContext context) {
		this.context = context;
	}

	/**
	 * 
	 * @return Contexte associ�
	 */
	public OrbeContext getContext() {
		return context;
	}

	public void restoreMemento(Preferences preferences) {
		file = preferences.getFile(PREF_FILE);
		slices = preferences.getBoolean(PREF_SLICES, false);
		margin = preferences.getInt(PREF_MARGIN, 0);
		slicesH = preferences.getInt(PREF_SLICES_H, 1);
		slicesV = preferences.getInt(PREF_SLICES_V, 1);
	}

	public void saveMemento(Preferences preferences) {
		preferences.setFile(PREF_FILE, file);
		preferences.setBoolean(PREF_SLICES, slices);
		preferences.setInt(PREF_SLICES_H, slicesH);
		preferences.setInt(PREF_SLICES_V, slicesV);
		preferences.setInt(PREF_MARGIN, margin);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isSlices() {
		return slices;
	}

	public void setSlices(boolean slices) {
		this.slices = slices;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public int getSlicesH() {
		return slicesH;
	}

	public void setSlicesH(int slicesH) {
		this.slicesH = slicesH;
	}

	public int getSlicesV() {
		return slicesV;
	}

	public void setSlicesV(int slicesV) {
		this.slicesV = slicesV;
	}

}
