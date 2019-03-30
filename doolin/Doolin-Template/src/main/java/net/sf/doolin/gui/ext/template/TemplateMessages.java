/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.ext.template;

/**
 * Message codes for the Template Extension.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TemplateMessages.java,v 1.1 2007/08/10 18:10:40 guinnessman Exp $
 */
public interface TemplateMessages {

	/**
	 * No template engine can be found for name {0}.
	 */
	String CANNOT_FIND_ENGINE = "net.sf.doolin.gui.ext.template.CannotFindEngine";

	/**
	 * Cannot generate template:\n{0}
	 */
	String CANNOT_GENERATE_TEMPLATE = "net.sf.doolin.gui.ext.template.CannotGenerateTemplate";

	/**
	 * Cannot initialize "{0}" engine.\n{1}
	 */
	String CANNOT_INITIALIZE_ENGINE = "net.sf.doolin.gui.ext.template.CannotInitializeEngine";

	/**
	 * Cannot create template from \"{1}\" for \"{0}\" engine.\n{2}
	 */
	String CANNOT_CREATE_TEMPLATE = "net.sf.doolin.gui.ext.template.CannotCreateTemplate";

}
