package net.sf.doolin.gui.core.support;

import net.sf.doolin.util.Utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

/**
 * Calls a method to get a new bean.
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class DelegateDataFactory implements DataFactory<Object> {

	private Object bean;

	private String method;

	@Override
	public Object createData() {
		Validate.notNull("bean", "Target bean must not be null");
		Validate.isTrue(StringUtils.isNotBlank("method"), "Method name must not be null");

		Object value = Utils.callMethod(bean, method);

		return value;
	}

	/**
	 * Returns the target bean
	 * 
	 * @return Target bean
	 */
	public Object getBean() {
		return bean;
	}

	/**
	 * Sets the target bean
	 * 
	 * @param bean
	 *            Target bean
	 */
	public void setBean(Object bean) {
		this.bean = bean;
	}

	/**
	 * Returns the method to be called on the target bean
	 * 
	 * @return Method name
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method to be called on the target bean
	 * 
	 * @param method
	 *            Method name
	 */
	public void setMethod(String method) {
		this.method = method;
	}

}
