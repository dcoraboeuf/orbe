/*
 * Created on 9 mars 2005
 */
package net.sf.doolin.gui.swing;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import net.sf.doolin.util.PatternFileFilter;

import net.sf.doolin.gui.MessageCodes;
import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.GUIStrings;
import net.sf.doolin.gui.service.Preferences;

/**
 * This class allows the browsing of files or directories.
 * 
 * @author Damien Coraboeuf
 * @version $Id: FileBrowser.java,v 1.2 2007/08/10 16:54:40 guinnessman Exp $
 */
public class FileBrowser {
	private boolean modeDirectory;

	private boolean modeSave;

	private String fileFilter = "*.*";

	private String fileDescription;

	private File file;

	/**
	 * Returns the file description.
	 * 
	 * @return File description.
	 */
	public String getFileDescription() {
		return fileDescription;
	}

	/**
	 * Sets the file description.
	 * 
	 * @param fileDescription
	 *            File description
	 */
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	/**
	 * Gets the file filter.
	 * 
	 * @return File filter.
	 * @see PatternFileFilter
	 */
	public String getFileFilter() {
		return fileFilter;
	}

	/**
	 * Sets the file filter.
	 * 
	 * @param fileFilter
	 *            File filter.
	 * @see PatternFileFilter
	 */
	public void setFileFilter(String fileFilter) {
		this.fileFilter = fileFilter;
	}

	/**
	 * Gets if the browsing is addressing only directories.
	 * 
	 * @return <code>true</code> if only directories are browsed.
	 */
	public boolean isModeDirectory() {
		return modeDirectory;
	}

	/**
	 * Sets if the browsing is addressing only directories.
	 * 
	 * @param modeDirectory
	 *            <code>true</code> if only directories are browsed
	 */
	public void setModeDirectory(boolean modeDirectory) {
		this.modeDirectory = modeDirectory;
	}

	/**
	 * Gets the browsing mode.
	 * 
	 * @return <code>true</code> if files/directories are to be saved,
	 *         <code>false</code> if they have to be opened.
	 */
	public boolean isModeSave() {
		return modeSave;
	}

	/**
	 * Sets the browsing mode.
	 * 
	 * @param modeSave
	 *            <code>true</code> if files/directories are to be saved,
	 *            <code>false</code> if they have to be opened.
	 */
	public void setModeSave(boolean modeSave) {
		this.modeSave = modeSave;
	}

	/**
	 * Browses for a file or a directory. This method launches a file browser.
	 * 
	 * @param component
	 *            Parent component for the file browser dialog
	 * @return Selected file or directory or <code>null</code> if none has
	 *         been selected.
	 */
	public File browse(Component component) {
		JFileChooser chooser = new JFileChooser() {

			@Override
			public void approveSelection() {
				File file = getSelectedFile();
				boolean ok;
				if (file != null && modeSave && !modeDirectory) {
					// Checks the name
					String fileName = file.getName();
					PatternFileFilter patternFileFilter = new PatternFileFilter(fileFilter);
					if (!patternFileFilter.accept(fileName)) {
						fileName = patternFileFilter.format(fileName);
						file = new File(file.getParentFile(), fileName);
						setSelectedFile(file);
					}
					// Checks existency
					if (file.exists()) {
						String message = GUIStrings.get(MessageCodes.PROMPT_FILE_EXISTS, file.getName());
						int result = JOptionPane.showConfirmDialog(this, message, "", JOptionPane.YES_NO_OPTION);
						ok = (result == JOptionPane.YES_OPTION);
					} else {
						ok = true;
					}
				} else {
					ok = true;
				}
				// Goes on
				if (ok) {
					super.approveSelection();
				}
			}

		};

		// Directory mode
		chooser.setFileSelectionMode(modeDirectory ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_ONLY);
		// Filter
		if (modeDirectory) {
			chooser.setFileFilter(new FileFilter() {

				public String getDescription() {
					return fileDescription;
				}

				public boolean accept(File f) {
					return f.isDirectory();
				}

			});
		} else {
			final PatternFileFilter patternFileFilter = new PatternFileFilter(this.fileFilter);
			chooser.setFileFilter(new FileFilter() {
				public boolean accept(File f) {
					return f.isDirectory() || patternFileFilter.accept(f);
				}

				public String getDescription() {
					return fileDescription;
				}
			});
		}
		// Get initial file from preferences
		Preferences preferences = GUIUtils.getPreferences();
		if (file == null) {
			file = preferences.getFile(FileBrowser.class.getName());
		}
		// Initial file
		if (file != null) {
			if (file.isDirectory()) {
				chooser.setCurrentDirectory(file);
			} else {
				chooser.setCurrentDirectory(file.getParentFile());
			}
		}
		// Browse
		int result;
		if (modeSave) {
			result = chooser.showSaveDialog(component);
		} else {
			result = chooser.showOpenDialog(component);
		}
		// Result
		if (result == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			if (f != null) {
				// Saves selection in preferences
				preferences.setFile(FileBrowser.class.getName(), f);
				preferences.save();
				file = f;
				return file;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Set the initial file
	 * 
	 * @param f
	 *            Initial selection
	 */
	public void setFile(File f) {
		file = f;
	}

}
