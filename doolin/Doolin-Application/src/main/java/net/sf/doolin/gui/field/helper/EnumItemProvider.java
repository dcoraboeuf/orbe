/*
 * Created on Jul 19, 2007
 */
package net.sf.doolin.gui.field.helper;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class EnumItemProvider implements ItemProvider {

	private Class<? extends Enum> enumType;

	public Class<? extends Enum> getEnumType() {
		return enumType;
	}

	public void setEnumType(Class<? extends Enum> enumType) {
		this.enumType = enumType;
	}

	public List getItems() {
		try {
			Method methodValues = enumType.getMethod("values", new Class[0]);
			Object[] items = (Object[]) methodValues.invoke(null, new Object[0]);
			return Arrays.asList(items);
		} catch (Exception ex) {
			throw new RuntimeException("Cannot get enumeration list from " + enumType);
		}
	}

}
