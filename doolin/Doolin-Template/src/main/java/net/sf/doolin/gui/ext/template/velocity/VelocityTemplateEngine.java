/*
 * Created on 21 oct. 2004
 * $Id: VelocityTemplateEngine.java,v 1.1 2007/08/10 18:10:40 guinnessman Exp $
 */
package net.sf.doolin.gui.ext.template.velocity;

import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;

import net.sf.doolin.util.CodeException;
import net.sf.doolin.util.IOUtils;

import net.sf.doolin.gui.ext.template.TemplateMessages;
import net.sf.doolin.gui.ext.template.support.AbstractTemplateEngine;

/**
 * Template engine for Velocity
 * 
 * @version $Id: VelocityTemplateManager.java,v 1.1 2007/07/25 18:23:33
 *          guinnessman Exp $
 * @author Damien Coraboeuf
 */
public class VelocityTemplateEngine extends AbstractTemplateEngine {

	/**
	 * Initialization
	 */
	public void init() {
		try {
			Properties properties = IOUtils.readProperties("/net/sf/doolin/gui/ext/template/velocity/Velocity.properties");
			// Initialization
			Velocity.init(properties);
		} catch (Exception ex) {
			throw new CodeException(TemplateMessages.CANNOT_INITIALIZE_ENGINE, ex, "Velocity", ex);
		}
	}

	/**
	 * Loads the resource as a Velocity template.
	 * 
	 * @see net.sf.doolin.gui.ext.template.TemplateEngine#getTemplate(java.lang.String)
	 */
	public net.sf.doolin.gui.ext.template.Template getTemplate(String resourcePath) {
		try {
			Template velocityTemplate = Velocity.getTemplate(resourcePath, "UTF-8");
			return new VelocityTemplate(velocityTemplate);
		} catch (CodeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CodeException(TemplateMessages.CANNOT_CREATE_TEMPLATE, ex, "velocity", resourcePath, ex);
		}
	}

}
