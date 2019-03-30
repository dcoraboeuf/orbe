/*
 * Created on Aug 6, 2007
 */
package net.sf.doolin.gui.field.support.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.util.PatternFileFilter;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.field.FieldFile;
import net.sf.doolin.gui.field.event.EventAction;
import net.sf.doolin.gui.field.support.FileSupport;
import net.sf.doolin.gui.swing.FileBrowser;

/**
 * Default implementation for the file field.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SwingFileSupport.java,v 1.1 2007/08/07 16:47:05 guinnessman Exp $
 */
public class SwingFileSupport extends AbstractSwingInfoFieldSupport<FieldFile, JPanel> implements FileSupport {

	private JPanel container;

	private JButton browseButton;

	private JTextField pathText;

	private File file;

	private Color pathTextDefaultColor;
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	@Override
	protected JPanel createComponent() {
		// Container
		container = new JPanel(new BorderLayout());
		// Text
		pathText = new JTextField(20);
		pathTextDefaultColor = pathText.getForeground();
		pathText.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				onPathEdited();
			}

		});
		container.add(pathText, BorderLayout.CENTER);
		// Browse button
		browseButton = new JButton("...");
		browseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				onBrowse();
			}

		});
		container.add(browseButton, BorderLayout.LINE_END);
		// Ok
		return container;
	}

	/**
	 * This method is called when the path editor has lost its focus.
	 * 
	 */
	protected void onPathEdited() {
		String path = pathText.getText();
		if (StringUtils.isBlank(path)) {
			file = null;
		} else {
			File f = new File(path);
			// Checks the conformity of the file to the filter
			boolean isSave = getField().getMode() == FieldFile.Mode.SAVE;
			boolean ok;
			if (getField().isDirectory()) {
				if (isSave) {
					ok = true;
				} else {
					ok = f.exists() && f.isDirectory();
				}
			} else {
				if (isSave) {
					PatternFileFilter filter = new PatternFileFilter(getExpandedPattern());
					ok = filter.accept(f);
				} else {
					PatternFileFilter filter = new PatternFileFilter(getExpandedPattern());
					ok = filter.accept(f) && f.exists() && f.isFile() && f.canRead();
				}
			}
			if (ok) {
				updateFile(f);
				pathText.setForeground(pathTextDefaultColor);
			} else {
				updateFile(null);
				pathText.setForeground(Color.red);
			}
		}
	}

	protected void updateFile(File f) {
		File oldFile = file;
		file = f;
		propertyChangeSupport.firePropertyChange("file", oldFile, file);
	}

	/**
	 * This method is called when the user has clicked on the browse button.
	 * 
	 */
	protected void onBrowse() {
		FileBrowser browser = new FileBrowser();
		browser.setModeDirectory(getField().isDirectory());
		browser.setModeSave(getField().getMode() == FieldFile.Mode.SAVE);
		browser.setFileFilter(getExpandedPattern());
		browser.setFileDescription(GUIUtils.evaluate(getField().getFilterDescription(), "formData", getForm().getFormData()));
		browser.setFile(file);
		File f = browser.browse(getComponent());
		if (f != null) {
			updateFile(f);
			pathText.setText(file.getAbsolutePath());
			pathText.setForeground(pathTextDefaultColor);
		}
	}

	/**
	 * Gets the full file filter.
	 * 
	 * @return File filter
	 * @see PatternFileFilter
	 */
	protected String getExpandedPattern() {
		return GUIUtils.evaluate(getField().getFilter(), "formData", getForm().getFormData());
	}

	public File getFile() {
		return file;
	}

	public void setFile(File f) {
		file = f;
		// Text
		if (file != null) {
			pathText.setText(file.getAbsolutePath());
		} else {
			pathText.setText("");
		}
	}

	public void bindEditEvent(final EventAction eventAction) {
		propertyChangeSupport.addPropertyChangeListener("file", new PropertyChangeListener() {
		
			public void propertyChange(PropertyChangeEvent evt) {
				eventAction.execute(getView(), getField(), null);
			}
		
		});
	}

}
