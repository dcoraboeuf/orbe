/*
 * Created on Sep 17, 2007
 */
package net.sf.doolin.oxml.action;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import net.sf.doolin.oxml.OXMLContext;
import net.sf.doolin.oxml.adapter.OXMLInstanceFactory;
import net.sf.doolin.util.Utils;
import net.sf.doolin.util.xml.DOMUtils;

/**
 * <code>create</code> action. This action creates an instance according to
 * its parameters, pushes it on the instance stack, evaluates all nested actions
 * and pops the instance back from the stack.
 * <p>
 * Parameters for this action are:
 * <ul>
 * <li> <code>class</code> - (required if <code>factory</code> is not
 * provided) Name of the instance type to create
 * <li> <code>factory </code> - (required if <code>class</code> is not
 * provided) Class name for an implementation of the
 * <code>{@link OXMLInstanceFactory}</code> interface, responsible for the
 * creation of the instance
 * <li><code>id</code> - (optional) If provided, the created instance will be
 * {@link OXMLContext#instancePut(String, Object) stored} into the context using
 * this id.
 * <li><code>root</code> - (optional) If set to <code>true</code>, the
 * created instance will be set as the {@link OXMLContext#setRoot(Object) root}
 * of the context. Only one action with this attribute set to <code>true</code>
 * is allowed by OXML configuration.
 * <li><code>property</code> - (optional) If provided, this attribute
 * indicates the name of the property of the current instance on the instance
 * stack to fill with the created instance.
 * <li><code>setter</code> - (optional) If provided, this attribute is the
 * name of a method to be called on the current instance on the instance stack
 * with the created instance as a unique parameter
 * <li><code>post</code> - (optional, defaults to <code>false</code>) The
 * <code>property</code> or the <code>setter</code> attribute is used by
 * default <i>before</i> the inner actions are evaluated. Putting this
 * attribute to <code>true</code> will perform this operation <i>after</i>
 * the inner actions have been evaluated.
 * </ul>
 * 
 * @author Damien Coraboeuf
 * @version $Id$
 */
public class CreateOXMLAction extends AbstractSequenceOXMLAction {

	private Class className;

	private Class factoryClass;

	private String id;

	private boolean root;

	private String property;

	private String setter;

	private boolean post = false;

	@Override
	@SuppressWarnings("unchecked")
	public void process(OXMLContext context) {
		// Creates the instace
		Object instance;
		if (className != null) {
			instance = Utils.newInstance(className);
		} else {
			OXMLInstanceFactory factory = (OXMLInstanceFactory) Utils.newInstance(factoryClass);
			instance = factory.createInstance(context);
		}
		// Identified instance
		if (StringUtils.isNotBlank(id)) {
			context.instancePut(id, instance);
		}
		// Root instance
		if (root) {
			context.setRoot(instance);
		}
		// Sets in parent
		if (!post) {
			setInParent(context, instance);
		}
		// Pushes the instance
		context.instancePush(instance);
		try {
			// Processes inner nodes
			super.process(context);
		} finally {
			// Removes the instance
			context.instancePop();
			// Sets in parent
			if (post) {
				setInParent(context, instance);
			}
		}
	}

	/**
	 * Sets the created instance into its parent
	 * 
	 * @param context
	 *            Execution context
	 * @param instance
	 *            Created instance
	 */
	protected void setInParent(OXMLContext context, Object instance) {
		// Setter instance
		if (StringUtils.isNotBlank(setter)) {
			Object currentInstance = context.instancePeek();
			try {
				MethodUtils.invokeMethod(currentInstance, setter, instance);
			} catch (Exception ex) {
				throw new RuntimeException("Cannot call method " + setter, ex);
			}
		}
		// Property instance
		else if (StringUtils.isNotBlank(property)) {
			try {
				BeanUtils.setProperty(context.instancePeek(), property, instance);
			} catch (Exception ex) {
				throw new RuntimeException("Cannot set property " + property, ex);
			}
		}
	}

	@Override
	public void parse(Element e) throws IOException {
		super.parse(e);
		className = DOMUtils.getClassAttribute(e, "class", null);
		id = DOMUtils.getAttribute(e, "id", false, null);
		root = DOMUtils.getBooleanAttribute(e, "root", false, false);
		property = DOMUtils.getAttribute(e, "property", false, null);
		setter = DOMUtils.getAttribute(e, "setter", false, null);
		post = DOMUtils.getBooleanAttribute(e, "post", false, false);
		factoryClass = DOMUtils.getClassAttribute(e, "factory", null);
	}

}
