/*
 * Created on Jul 24, 2007
 */
package net.sf.doolin.gui.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

import net.sf.doolin.util.unit.Unit;
import net.sf.doolin.util.unit.ValueUnit;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class provides a number of Swing-dedicated utility methods.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public final class SwingUtils {

	private static Log log = LogFactory.getLog(SwingUtils.class);

	private SwingUtils() {
	}

	public static void setPreferredSize(Component component) {
		component.validate();
		Dimension d = component.getPreferredSize();
		component.setSize(d);
	}

	/**
	 * Change the size, by using a multiplicator
	 */
	public static void setFontSize(JComponent label, float mult) {
		Font font = label.getFont();
		float size = font.getSize2D();
		label.setFont(font.deriveFont(size * mult));
	}

	/**
	 * Change the style
	 */
	public static void setFontStyle(JComponent label, int style) {
		label.setFont(label.getFont().deriveFont(style));
	}

	/**
	 * Associates an action listener to a key binding
	 */
	public static void associateKey(JComponent component, int scope, String key, Action action) {
		KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
		Object actionName = action.getValue(Action.NAME);
		component.getInputMap(scope).put(keyStroke, actionName);
		component.getActionMap().put(actionName, action);
	}

	/**
	 * Set the width of a column
	 */
	public static void setTableColumnWidth(JTable table, int column, ValueUnit width, boolean resizable) {
		int tableWidth = table.getWidth();
		int screenWidth = width.convert(Unit.PIXEL, tableWidth).getValue().intValue();
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		if (resizable) {
			tableColumn.setResizable(true);
			tableColumn.setMinWidth(0);
			tableColumn.setMaxWidth(800);
		} else {
			tableColumn.setMinWidth(screenWidth);
			tableColumn.setMaxWidth(screenWidth);
			tableColumn.setResizable(false);
		}
		tableColumn.setPreferredWidth(screenWidth);
	}

	/**
	 * Creates an instance of a frame or a dialog
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createWindow(Class<T> windowClass, Component parent) {
		try {
			if (parent == null) {
				return windowClass.newInstance();
			} else {
				Window parentWindow = SwingUtilities.getWindowAncestor(parent);
				if (parentWindow != null) {
					return (T) ConstructorUtils.invokeConstructor(windowClass, parentWindow);
				} else {
					return windowClass.newInstance();
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException("Cannot instantiate instance of " + windowClass.getName());
		}
	}

	/**
	 * Set the <code>button</code> as default button for the container of the
	 * <code>component</code>.
	 * 
	 * @param component
	 *            Component whose container must have a default button.
	 * @param button
	 *            Button to set as default
	 */
	public static void setDefaultButton(Component component, JButton button) {
		JRootPane rootPane = SwingUtilities.getRootPane(component);
		if (rootPane != null) {
			rootPane.setDefaultButton(button);
		} else {
			log.warn("Cannot set default button because no root pane has been found.");
		}
	}

}
