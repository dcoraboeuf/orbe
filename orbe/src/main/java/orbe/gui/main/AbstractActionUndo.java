/*
 * Created on Oct 27, 2006
 */
package orbe.gui.main;

import net.sf.doolin.bus.Bus;
import net.sf.doolin.bus.Subscriber;
import orbe.gui.context.OrbeContext;
import orbe.gui.message.OrbeMessageContextEdit;

public abstract class AbstractActionUndo extends AbstractActionMain {

	@Override
	protected void process() {
		if (canDo()) {
			doTask();
		}
	}

	protected abstract void doTask();

	/**
	 * Default constructor.
	 */
	public AbstractActionUndo() {
		Bus.get().subscribe(OrbeMessageContextEdit.class, new Subscriber<OrbeMessageContextEdit>() {
		
			public void receive(OrbeMessageContextEdit message) {
				OrbeContext context = message.getValue();
				if (context == getContext()) {
					changeState();
				}
			}
		
		});
		setEnabled(false);
	}

	@Override
	protected void onContextChanged() {
		setEnabled(getContext() != null && canDo());
	}

	protected abstract boolean canDo();

	protected abstract void changeState();

}
