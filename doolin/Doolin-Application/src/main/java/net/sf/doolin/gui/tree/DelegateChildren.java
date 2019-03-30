/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.tree;

import java.util.Collection;

import net.sf.doolin.gui.field.helper.ContextItemProvider;
import net.sf.doolin.gui.field.helper.ItemProvider;

/**
 * This children definition implementation delegates the computation of children
 * objects to an <code>ItemProvider</code> instance.
 * 
 * @see ItemProvider
 * @author Damien Coraboeuf
 * @version $Id: DelegateChildren.java,v 1.3 2007/08/15 09:05:21 guinnessman Exp $
 */
public class DelegateChildren extends AbstractChildren {

	private ItemProvider itemProvider;

	public Collection getChildBeans(Object root) {
		if (itemProvider instanceof ContextItemProvider) {
			((ContextItemProvider) itemProvider).setContext(root);
		}
		return itemProvider.getItems();
	}

	/**
	 * @return Associated item provider
	 */
	public ItemProvider getItemProvider() {
		return itemProvider;
	}

	/**
	 * @param itemProvider
	 *            Associated item provider
	 */
	public void setItemProvider(ItemProvider itemProvider) {
		this.itemProvider = itemProvider;
	}

}
