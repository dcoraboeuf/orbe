/*
 * Created on Aug 7, 2007
 */
package net.sf.doolin.gui.wizard.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngineManager;

import org.apache.commons.lang.StringUtils;

import net.sf.doolin.gui.core.support.GUIUtils;
import net.sf.doolin.gui.service.ScriptEvaluator;

/**
 * Wizard navigation based on rules.
 * 
 * @author Damien Coraboeuf
 * @version $Id: RuleWizardNavigation.java,v 1.1 2007/08/07 16:47:10 guinnessman
 *          Exp $
 */
public class RuleWizardNavigation extends AbstractRuleWizardNavigation {

	private String ruleEngine;

	private String ruleFunction;

	private Map<String, String> rules = new HashMap<String, String>();

	private Set<String> endSteps = new HashSet<String>();

	/**
	 * @see net.sf.doolin.gui.wizard.rule.AbstractRuleWizardNavigation#computeNextStep(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	protected String computeNextStep(String name, Object data) {
		// Get the associated rule
		String rule = rules.get(name);
		// If no rule is available, there is no further step
		if (StringUtils.isBlank(rule)) {
			return null;
		}
		// Creates the context
		HashMap<String, Object> context = new HashMap<String, Object>();
		context.put("formData", data);
		context.put("nextStep", "");
		// Evaluates the rule
		ScriptEvaluator scriptEvaluator = GUIUtils.getService(ScriptEvaluator.class);
		scriptEvaluator.evaluate(ruleEngine, rule, context);
		// Result
		String nextStep = (String) context.get("nextStep");
		// Ok
		return nextStep;
	}

	/**
	 * Finish is available if no further step is available or if the step name
	 * is part of the end steps.
	 * 
	 * @see #getEndSteps()
	 * @see #isNextAvailable(String)
	 * @see net.sf.doolin.gui.wizard.rule.AbstractRuleWizardNavigation#isFinishAvailable(java.lang.String)
	 */
	@Override
	protected boolean isFinishAvailable(String name) {
		return !isNextAvailable(name) || (endSteps != null && endSteps.contains(name));
	}

	/**
	 * A next step is available if no further step is available
	 * 
	 * @see net.sf.doolin.gui.wizard.rule.AbstractRuleWizardNavigation#isNextAvailable(java.lang.String)
	 */
	@Override
	protected boolean isNextAvailable(String name) {
		return rules.containsKey(name);
	}

	/**
	 * Returns the name of the rule engine
	 * 
	 * @return Rule engine name
	 * @see ScriptEngineManager#getEngineByName(String)
	 */
	public String getRuleEngine() {
		return ruleEngine;
	}

	/**
	 * Sets the name of the rule engine
	 * 
	 * @param ruleEngine
	 *            Rule engine name
	 * @see ScriptEngineManager#getEngineByName(String)
	 */
	public void setRuleEngine(String ruleEngine) {
		this.ruleEngine = ruleEngine;
	}

	/**
	 * Returns the map of rules. Map of rules is indexed by wizard steps ids and
	 * contain scripts to execute.
	 * 
	 * @return Rules
	 */
	public Map<String, String> getRules() {
		return rules;
	}

	/**
	 * Sets the rules.
	 * 
	 * @param rules
	 *            Rules
	 * @see #getRules()
	 */
	public void setRules(Map<String, String> rules) {
		this.rules = rules;
	}

	/**
	 * Returns the end steps
	 * 
	 * @return Set of end step names
	 */
	public Set<String> getEndSteps() {
		return endSteps;
	}

	/**
	 * Sets the end steps
	 * 
	 * @param endSteps
	 *            Set of end step names
	 */
	public void setEndSteps(Set<String> endSteps) {
		this.endSteps = endSteps;
	}

	/**
	 * Returns the function to call
	 * 
	 * @return Function name
	 */
	public String getRuleFunction() {
		return ruleFunction;
	}

	/**
	 * Sets the function to call
	 * 
	 * @param ruleFunction
	 *            Function name
	 */
	public void setRuleFunction(String ruleFunction) {
		this.ruleFunction = ruleFunction;
	}

}
