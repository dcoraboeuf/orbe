/*
 * Created on 15 août 07
 */
package net.sf.doolin.gui.monitor;

import net.sf.doolin.gui.core.View;

public interface TaskMonitorFactory {

	TaskMonitor createMonitor(View view);

}
