/*
 * Created on 15 aoutt 07
 */
package net.sf.doolin.gui.monitor.support;

import net.sf.doolin.gui.core.View;
import net.sf.doolin.gui.monitor.TaskMonitor;
import net.sf.doolin.gui.monitor.TaskMonitorFactory;

public class DefaultTaskMonitorFactory implements TaskMonitorFactory {

	public TaskMonitor createMonitor(View view) {
		return new DefaultTaskMonitor(view);
	}

}

