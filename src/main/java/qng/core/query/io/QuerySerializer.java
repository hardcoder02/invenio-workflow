package qng.core.query.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXB;

import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

// TODO: use full-blown JAXB, or consider eCore
// TODO: handle LogicalVariable
public class QuerySerializer {
	public static void saveQuery(LogicalExpression query, File file) {
		QueryBean queryBean = buildQueryBean(query);
		
		JAXB.marshal(queryBean, file);
	}
	
	public static LogicalExpression loadQuery(File file) {
		QueryBean queryBean = JAXB.unmarshal(file, QueryBean.class);
		
		return buildQuery(queryBean);
	}
	
	public static void saveQuery(LogicalExpression query, OutputStream os) {
		QueryBean queryBean = buildQueryBean(query);
		
		JAXB.marshal(queryBean, os);
	}
	
	public static LogicalExpression loadQuery(InputStream is) {
		QueryBean queryBean = JAXB.unmarshal(is, QueryBean.class);
		
		return buildQuery(queryBean);
	}
	
	
	private static QueryBean buildQueryBean(LogicalExpression query) {
		QueryBean queryBean = new QueryBean();
		
		queryBean.setRoot( buildLogicalExpressionBean(query) );
		
		return queryBean;
	}
	
	private static LogicalExpressionBean buildLogicalExpressionBean(LogicalExpression expr) {
		LogicalExpressionBean bean = new LogicalExpressionBean();
		
		bean.setName( expr.getName() );
		
		if ( expr instanceof LogicalOperator ) {
			bean.setOperation( buildLogicalOperationBean( (LogicalOperator)expr) );
		}
		else if ( expr instanceof LogicalConstant ) {
			bean.setConstant( buildLogicalConstantBean( (LogicalConstant) expr) );
		}
		
		return bean;
	}
	
	private static LogicalOperationBean buildLogicalOperationBean( LogicalOperator op ) {
		LogicalOperationBean bean = new LogicalOperationBean();
		
		for ( LogicalExpression exp : op.getOperands() ) {
			bean.getOperand().add( buildLogicalExpressionBean( exp ));
		}
		
		return bean;
	}
	
	private static LogicalConstantBean buildLogicalConstantBean( LogicalConstant c ) {
		LogicalConstantBean bean = new LogicalConstantBean();
		
		Object val = c.getValue();
		if ( val != null && val instanceof LogicalExpression ) {
			bean.setValue( buildLogicalExpressionBean( (LogicalExpression) val ));
		}
		else if ( val != null && val.getClass().isEnum() ) {
			LogicalEnumBean e = new LogicalEnumBean();
			e.setEnumClass( val.getClass().getName() );
			e.setEnumValue( ((Enum)val).name() );
			bean.setValue( e.encode() );
		}
		else
			bean.setValue( val );
		
		return bean;
	}
	
	private static LogicalExpression buildQuery(QueryBean queryBean) {
		LogicalExpressionBean exprBean = queryBean.getRoot();
		
		return buildLogicalExpression( exprBean );
	}
	
	private static LogicalExpression buildLogicalExpression(LogicalExpressionBean exprBean) {
		if ( isConstant(exprBean) ) {
			return buildLogicalConstant( exprBean.getName(), exprBean.getConstant() );
		}
		else if ( isOperation(exprBean) ) {
			return buildLogicalOperation( exprBean.getName(), exprBean.getOperation() );
		}
		else
			return null;
	}

	private static LogicalOperator buildLogicalOperation(String name, LogicalOperationBean opBean) {
		LogicalOperator op = new LogicalOperatorImpl(name);
		
		for ( LogicalExpressionBean operandBean : opBean.getOperand() ) {
			op.addOperand( buildLogicalExpression( operandBean ) );
		}
		return op;
	}

	// TODO fix
	private static LogicalExpression buildLogicalConstant(String name, LogicalConstantBean constBean) {
		Object val = constBean.getValue();
		if ( val != null && val instanceof LogicalExpressionBean ) {
			return new LogicalConstant(name, buildLogicalExpression( (LogicalExpressionBean) val ));
		}
		else if (  val != null && (val instanceof String) &&
				LogicalEnumBean.isEnum( val.toString() )) {
			LogicalEnumBean b = new LogicalEnumBean();
			b.decode( val.toString() );
			try {
				Class<?> c = Class.forName( b.getEnumClass() );
				if ( !c.isEnum() )
					throw new IllegalArgumentException("Class " + b.getEnumClass() + " is not an enum");
				Object[] enumValues = c.getEnumConstants();
				
				for (int i = 0; i < enumValues.length; i++) {
					if (enumValues[i].toString().equalsIgnoreCase( b.getEnumValue() ))
						return new LogicalConstant(name, enumValues[i] );
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}
		else {
			LogicalConstant c = new LogicalConstant(name, val);
			return c;
		}
	}

	private static boolean isOperation(LogicalExpressionBean exprBean) {
		return ( exprBean.getOperation() != null );
	}

	private static boolean isConstant(LogicalExpressionBean exprBean) {
		return ( exprBean.getConstant() != null );
	}
	
}

