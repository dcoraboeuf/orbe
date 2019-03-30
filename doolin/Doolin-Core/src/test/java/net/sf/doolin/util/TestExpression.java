/*
 * Created on 3 fevr. 2006
 */
package net.sf.doolin.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.sf.doolin.util.CodeLabel;
import net.sf.doolin.util.expression.ExpressionString;

import junit.framework.TestCase;

/**
 * Test of dynamic expressions.
 * 
 * @author coraboeuf
 * @version $Id: TestExpression.java,v 1.2 2007/07/31 15:32:48 guinnessman Exp $
 */
public class TestExpression extends TestCase {

	/**
	 * @throws Exception
	 */
	public void testSimple() throws Exception {
		String pattern = "Test";
		String value = evaluate(pattern, null);
		assertEquals("Test", value);
	}

	/**
	 * @throws Exception
	 */
	public void testParamSimple() throws Exception {
		String pattern = "Test $id$item";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", "1");
		params.put("item", "Item");
		String value = evaluate(pattern, params);
		assertEquals("Test 1Item", value);
	}

	/**
	 * @throws Exception
	 */
	public void testParamProperty() throws Exception {
		String pattern = "$code.code and $code.label";
		HashMap<String, Object> params = new HashMap<String, Object>();
		CodeLabel codeLabel = new CodeLabel("C", "Code");
		params.put("code", codeLabel);
		String value = evaluate(pattern, params);
		assertEquals("C and Code", value);
	}

	/**
	 * @throws Exception
	 */
	public void testParamEscape() throws Exception {
		String pattern = "Test_$item\\.xml";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("item", "Item");
		String value = evaluate(pattern, params);
		assertEquals("Test_Item.xml", value);
	}

	/**
	 * @throws Exception
	 */
	public void testParenthesis() throws Exception {
		String pattern = "category($category.id)";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("category", new Category(2));
		String value = evaluate(pattern, params);
		assertEquals("category(2)", value);
	}

	/**
	 * @param pattern
	 * @param params
	 * @return Result of the evaluation
	 * @throws Exception
	 */
	protected String evaluate(String pattern, Map<String, Object> params) throws Exception {
		ExpressionString expressionString = new ExpressionString(pattern);
		if (params != null) {
			expressionString.putContext(params);
		}
		return expressionString.compute(Locale.getDefault());
	}

	/**
	 * @author Damien Coraboeuf
	 * @version $Id: TestExpression.java,v 1.2 2007/07/31 15:32:48 guinnessman Exp $
	 */
	public static class Category {
		private int id;

		/**
		 * @param id
		 */
		public Category(int id) {
			this.id = id;
		}

		/**
		 * @return Category id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 */
		public void setId(int id) {
			this.id = id;
		}
	}

}
