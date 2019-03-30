/*
 * Created on 10 mars 2005
 */
package net.sf.doolin.gui.ext.template.velocity;

import java.io.InputStream;
import java.util.Locale;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

import net.sf.doolin.util.LocaleUtils;

/**
 * @author damien
 * @version $Id: GUIFVelocityResourceLoader.java,v 1.1 2005/11/01 10:14:28
 *          guinnessman Exp $
 */
public class VelocityResourceLoader extends ResourceLoader {

	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(VelocityResourceLoader.class);

	/**
	 * Nothing to initialize
	 */
	public void init(ExtendedProperties configuration) {
	}

	/**
	 * Loads the resource from classpath
	 */
	public InputStream getResourceStream(String name) throws ResourceNotFoundException {
		log.debug("Loading template from classpath : " + name);
		// Checks the name
		if (name == null || name.length() == 0) {
			throw new ResourceNotFoundException("No template name provided");
		} else {
			try {
				InputStream result = LocaleUtils.getResourceAsStream(name, Locale.getDefault());
				if (result == null) {
					throw new ResourceNotFoundException("Cannot find template at " + name);
				}
				return result;
			} catch (Exception fnfe) {
				throw new ResourceNotFoundException(fnfe.getMessage());
			}
		}
	}

	/**
	 * Defaults to return false.
	 */
	public boolean isSourceModified(Resource resource) {
		return false;
	}

	/**
	 * Defaults to return 0
	 */
	public long getLastModified(Resource resource) {
		return 0;
	}
}
