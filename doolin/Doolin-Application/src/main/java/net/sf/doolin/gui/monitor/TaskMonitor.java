/*
 * Created on 15 août 07
 */
package net.sf.doolin.gui.monitor;

import net.sf.doolin.util.task.Task;

public interface TaskMonitor {

	void setCancellable(boolean cancellable);

	void setMaximum(int max);

	void setDoCycle(boolean doCycle);

	void start(Task task);

	boolean isCancelled();

	void setTitle(String title);
	
	void setInitialStatus (String status);
	
	void setMessage (String message);

}
