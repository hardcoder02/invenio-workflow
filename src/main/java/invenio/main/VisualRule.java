package invenio.main;

import java.io.Serializable;

import prefuse.data.expression.Predicate;
import prefuse.data.expression.BooleanLiteral;

/**
 * VisualRule is a class that contains all of the information about a new rule
 * intended to change the visual display of a Graph
 * 
 */

public class VisualRule implements Serializable {

	Predicate m_predicate;
	int m_ruleType;
	
	String m_graphName;
	Object m_propertyValue;
	
	public static final int NODE_COLOR_RULE = 0;
	public static final int NODE_BORDERCOLOR_RULE = 1;
	public static final int NODE_SHAPE_RULE = 2;
	public static final int NODE_SIZE_RULE = 3;
	public static final int NODE_LABEL_RULE = 4;
	public static final int NODE_VISIBILITY_RULE = 5;
	public static final int NODE_SIZEUP_RULE = 6;
	public static final int NODE_MASS_RULE = 7;

	public static final int EDGE_COLOR_RULE = 8;
	public static final int EDGE_SIZE_RULE = 9;
	public static final int EDGE_SIZEUP_RULE = 10;
	public static final int EDGE_DASH_RULE = 11;
	public static final int EDGE_VISIBILITY_RULE = 12;
	public static final int EDGE_SPRING_RULE = 13;
	public static final int EDGE_RENDERER_RULE = 14;
	
	public static final int LAYOUT_RULE = 15;
	
	public VisualRule copy() {
		return new VisualRule(m_predicate, m_ruleType, m_propertyValue);
	}
	
	public VisualRule() {
		setRuleType(-1);
		setPropertyValue(new Object());
		setPredicate(new BooleanLiteral(true));
	}
	
	public VisualRule(Predicate predicate, int ruleType, Object propertyValue) {
		setRuleType(ruleType);
		setPropertyValue(propertyValue);
		setPredicate(predicate);
	}
	
	public Predicate setPredicate(Predicate predicate) {
		m_predicate = predicate;
		return m_predicate;
	}

	public int setRuleType(int ruleType) {
		m_ruleType = ruleType;
		return m_ruleType;
	}

	public Object setPropertyValue(Object propertyValue) {
		m_propertyValue = propertyValue;
		return m_propertyValue;
	}
	public Predicate getPredicate() {
		return m_predicate;
	}
	
	public int getRuleType() {
		return m_ruleType;
	}
	
	public Object getPropertyValue() {
		return m_propertyValue;
	}
}