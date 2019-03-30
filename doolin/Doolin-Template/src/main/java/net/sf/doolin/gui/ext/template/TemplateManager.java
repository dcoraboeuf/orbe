/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.ext.template;

import java.util.List;

/**
 * Template management service.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TemplateManager.java,v 1.1 2007/08/10 18:10:40 guinnessman Exp $
 */
public interface TemplateManager {

	/**
	 * Gets an engine instance using its name
	 * 
	 * @param engine
	 *            Engine name
	 * @return Engine
	 */
	TemplateEngine getTemplateEngine(String engine);

	/**
	 * Gets the list of available engine
	 * 
	 * @return List of engine names
	 */
	List<String> getTemplateEngineNames();

}
