/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.doolin.util.Utils;

/**
 * Factory for OXML actions.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class OXMLActionFactory {

	/**
	 * Unique instance
	 */
	private static OXMLActionFactory instance = null;

	/**
	 * Get the instance
	 * 
	 * @return Action factory to be used
	 */
	public static OXMLActionFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			return createInstance();
		}
	}

	/**
	 * Creates the instance
	 */
	private static synchronized OXMLActionFactory createInstance() {
		if (instance != null) {
			return instance;
		} else {
			OXMLActionFactory temp = new OXMLActionFactory();
			instance = temp;
			return instance;
		}
	}

	private Map<String, Class<? extends OXMLAction>> classes = new HashMap<String, Class<? extends OXMLAction>>();

	/**
	 * Initialization
	 */
	private OXMLActionFactory() {
		classes.put("for", ForOXMLAction.class);
		classes.put("create", CreateOXMLAction.class);
		classes.put("check", CheckOXMLAction.class);
		classes.put("property", PropertyOXMLAction.class);
		classes.put("if", IfOXMLAction.class);
	}

	/**
	 * Creates an OXML action from a tag name.
	 * 
	 * @param name
	 *            Tag name
	 * @return Corresponding action
	 * @throws IOException
	 *             If the name cannot be converted to an action
	 */
	public OXMLAction createAction(String name) throws IOException {
		Class<? extends OXMLAction> actionClass = classes.get(name);
		if (actionClass != null) {
			OXMLAction action = Utils.newInstance(actionClass);
			return action;
		} else {
			throw new IOException("Cannot find any action for tag " + name);
		}
	}

}
