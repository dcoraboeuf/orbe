/*
 * Created on Sep 18, 2007
 */
package orbe.gui.main;

import javax.swing.JToolBar;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.core.view.ToolbarItem;
import orbe.gui.context.OrbeContext;
import orbe.gui.message.OrbeMessageContextChanged;

public abstract class AbstractToolbarElement implements ToolbarItem {

	protected static final int CBO_HEIGHT = 30;

	public void createToolbarItem(JToolBar j, View view) {
		Bus.get().subscribe(OrbeMessageContextChanged.class, new Subscriber<OrbeMessageContextChanged>() {

			public void receive(OrbeMessageContextChanged message) {
				OrbeContext context = message.getValue();
				onContextChanged(context);
			}

		});
	}

	protected abstract void onContextChanged(OrbeContext context);

}
