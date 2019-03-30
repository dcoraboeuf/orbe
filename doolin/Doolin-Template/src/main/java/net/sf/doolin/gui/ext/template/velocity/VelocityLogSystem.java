/*
 * Created on 2 avr. 2005
 */
package net.sf.doolin.gui.ext.template.velocity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

/**
 * Log of Velocity
 * @author damien
 * @version $Id: VelocityLogSystem.java,v 1.1 2007/08/10 18:10:40 guinnessman Exp $
 */
public class VelocityLogSystem implements LogSystem
{
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(VelocityLogSystem.class);

	/** 
	 * No init needed
	 */
	public void init(RuntimeServices runtimeServices) throws Exception
	{	
	}

	/** 
	 * Log
	 */
	public void logVelocityMessage(int level, String message)
	{
		log.debug(message);
	}

}

