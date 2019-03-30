/*
 * Created on Jul 18, 2007
 */
package net.sf.doolin.gui.core.validation;

import net.sf.doolin.gui.core.View;

public interface ValidationReporter {
	
	void report (View view, ValidationReport report);

}
