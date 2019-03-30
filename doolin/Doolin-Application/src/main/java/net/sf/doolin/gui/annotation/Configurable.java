/*
 * Created on Jul 30, 2007
 */
package net.sf.doolin.gui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documents an attribute that is configurable in the Doolin GUI configuration
 * file.
 * 
 * @author Damien Coraboeuf
 * @version $Id: Configurable.java,v 1.2 2007/08/15 09:05:30 guinnessman Exp $
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Configurable {

	/**
	 * Defines if the target property is mandatory or not.
	 */
	boolean mandatory();
	
	/**
	 * Description of the attribute
	 */
	String description();
	
	/**
	 * Default value
	 */
	String defaultsTo() default "";

}
