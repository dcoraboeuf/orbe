/*
 * Created on Aug 9, 2007
 */
package net.sf.doolin.gui.ext.template.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.doolin.gui.ext.template.TemplateEngine;
import net.sf.doolin.gui.ext.template.TemplateManager;
import net.sf.doolin.gui.ext.template.TemplateMessages;
import net.sf.doolin.util.CodeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Utility root ancestor for template manager implementations.
 * 
 * @author Damien Coraboeuf
 * @version $Id: AbstractTemplateManager.java,v 1.1 2007/08/10 18:10:41
 *          guinnessman Exp $
 */
public abstract class AbstractTemplateManager implements TemplateManager, InitializingBean {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(AbstractTemplateManager.class);

	/**
	 * Indexed list of engines
	 */
	private Map<String, TemplateEngine> engines = new HashMap<String, TemplateEngine>();

	/**
	 * Registers an engine.
	 * 
	 * @param engineName
	 *            Engine name
	 * @param engine
	 *            Engine to register
	 */
	protected void registerEngine(String engineName, TemplateEngine engine) {
		engines.put(engineName, engine);
	}

	/**
	 * @see net.sf.doolin.gui.ext.template.TemplateManager#getTemplateEngine(java.lang.String)
	 */
	public TemplateEngine getTemplateEngine(String engineName) {
		TemplateEngine engine = engines.get(engineName);
		if (engine != null) {
			return engine;
		} else {
			throw new CodeException(TemplateMessages.CANNOT_FIND_ENGINE, engineName);
		}
	}

	/**
	 * @see net.sf.doolin.gui.ext.template.TemplateManager#getTemplateEngineNames()
	 */
	public List<String> getTemplateEngineNames() {
		return Collections.unmodifiableList(new ArrayList<String>(engines.keySet()));
	}

	/**
	 * Returns the indexed list of engines
	 * 
	 * @return Indexed list of engines
	 */
	public Map<String, TemplateEngine> getEngines() {
		return engines;
	}

	/**
	 * Sets the indexed list of engines
	 * 
	 * @param engines
	 *            Indexed list of engines
	 */
	public void setEngines(Map<String, TemplateEngine> engines) {
		this.engines = engines;
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		// Initialization of the engines
		for (TemplateEngine engine : engines.values()) {
			log.info("Initializing engine " + engine);
			engine.init();
		}
	}

}
