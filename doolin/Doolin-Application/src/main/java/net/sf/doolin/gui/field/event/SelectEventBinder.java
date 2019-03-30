/*
 * Created on Aug 13, 2007
 */
package net.sf.doolin.gui.field.event;

import java.awt.ItemSelectable;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import net.sf.doolin.gui.core.action.ActionControler;
import net.sf.doolin.gui.field.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Binds a selection event to an action. Only components that are instances of
 * the following classes / interfaces can be binded.
 * 
 * <ul>
 * <li>{@link ItemSelectable}
 * <li>{@link JTable}
 * <li>{@link JTree}
 * <li>{@link JList}
 * </ul>
 * 
 * In case the component cannot be bound, a warning is put in the log but no
 * exception is generated.
 * 
 * @author Damien Coraboeuf
 * @version $Id: SelectEventBinder.java,v 1.1 2007/08/14 11:48:51 guinnessman
 *          Exp $
 */
public class SelectEventBinder extends AbstractEventBinder {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(SelectEventBinder.class);

	public void bind(Field field) {
		JComponent component = field.getFocusableComponent();
		bind(component, field);
	}

	/**
	 * Binds an action to a component.
	 * 
	 * @param component
	 *            Component to bind the action to.
	 * @param field
	 *            Binded field
	 */
	protected void bind(JComponent component, Field field) {
		if (component instanceof ItemSelectable) {
			bindItemSelectable((ItemSelectable) component, field);
		} else if (component instanceof JTable) {
			bindTable((JTable) component, field);
		} else if (component instanceof JTree) {
			bindTree((JTree) component, field);
		} else if (component instanceof JList) {
			bindList((JList) component, field);
		} else {
			log.warn("Cannot bind a select event on component of class " + component.getClass().getName());
		}
	}

	/**
	 * Binding for a selectable item
	 * 
	 * @param component
	 *            Component to bind the action to.
	 * @param field
	 *            Binded field
	 */
	protected void bindItemSelectable(ItemSelectable component, final Field field) {
		component.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				runAction(field, e.getItem());
			}

		});
	}

	/**
	 * Binding for a JList
	 * 
	 * @param list
	 *            Component to bind the action to.
	 * @param field
	 *            Binded field
	 */
	protected void bindList(final JList list, final Field field) {
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Object item = list.getSelectedValue();
					runAction(field, item);
				}
			}

		});
	}

	/**
	 * Binding for a JTree
	 * 
	 * @param tree
	 *            Component to bind the action to.
	 * @param field
	 *            Binded field
	 */
	protected void bindTree(final JTree tree, final Field field) {
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				if (e.isAddedPath()) {
					Object item = tree.getLastSelectedPathComponent();
					runAction(field, item);
				}
			}

		});
	}

	/**
	 * Binding for a JTable
	 * 
	 * @param table
	 *            Component to bind the action to.
	 * @param field
	 *            Binded field
	 */
	protected void bindTable(final JTable table, final Field field) {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Point p = new Point(table.getSelectedColumn(), table.getSelectedRow());
					runAction(field, p);
				}
			}

		});
	}

	/**
	 * Runs an action against a selected item. If the target action implements
	 * <code>{@link SelectionAction}</code>, the selection is set first.
	 * 
	 * @param field
	 *            Binded field
	 * @param item
	 *            Selected item
	 */
	@SuppressWarnings("unchecked")
	protected void runAction(Field field, final Object item) {
		getEventAction().execute(field.getForm().getView(), field, new ActionControler() {
		
			public void control(net.sf.doolin.gui.core.Action action) {
				// Selection
				if (action instanceof SelectionAction) {
					SelectionAction selectionAction = (SelectionAction) action;
					selectionAction.setSelection(item);
				}
			}
		
		});
	}
}
