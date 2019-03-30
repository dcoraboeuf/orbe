/*
 * Created on 9 janv. 2005
 * $Id: Value.java,v 1.1 2007/07/18 18:41:16 guinnessman Exp $
 */
package net.sf.doolin.util.expression;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Value
 * 
 * @version $Revision: 1.1 $
 * @author Damien Coraboeuf
 */
public class Value extends Buffer
{
	/**
	 * Log
	 */
	private final static Log log = LogFactory.getLog(Value.class);

	/**
	 * Constructor
	 */
	public Value()
	{
		super(EXPR_VALUE);
	}

	/**
	 * String
	 */
	public String toString()
	{
		return "$" + getValue();
	}

	/**
	 * @see com.mandragor.utils.pattern.Expression#evaluate(java.util.Locale,
	 *           java.util.Map)
	 */
	public String evaluate(Locale locale, Map context)
	{
		String code = getValue();
		// Get the context key
		String contextKey = StringUtils.substringBefore(code, ".");
		// Get the bean from the context
		Object contextBean = context.get(contextKey);
		if (contextBean == null)
		{
			return "";
		}
		// Get the property
		Object property = contextBean;
		String propertyName = StringUtils.substringAfter(code, ".");
		if (StringUtils.isNotEmpty(propertyName))
		{
			try
			{
				property = BeanUtils.getProperty(contextBean, propertyName);
			}
			catch (Exception ex)
			{
				log.error("Cannot get property " + propertyName + " into "
						+ contextBean, ex);
			}
		}
		// Ok
		return ObjectUtils.toString(property, "");
	}
}

