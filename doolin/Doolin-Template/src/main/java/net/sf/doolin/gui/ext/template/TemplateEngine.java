/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.ext.template;

/**
 * Definition for a template engine.
 * 
 * @author Damien Coraboeuf
 * @version $Id: TemplateEngine.java,v 1.1 2007/08/10 18:10:40 guinnessman Exp $
 */
public interface TemplateEngine {

	/**
	 * Predefined template engine: Velocity.
	 */
	public final static String VELOCITY = "Velocity";

	/**
	 * Creates a template for the given resource.
	 * 
	 * @param resourcePath
	 *            Path to the template resource definition
	 * @return Reusable remplate
	 */
	Template getTemplate(String resourcePath);

	/**
	 * Initialization of the engine.
	 * 
	 */
	void init();

}
