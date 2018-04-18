tree grammar GraphQueryTree;

options {
  // We're going to process an AST whose nodes are of type CommonTree.
  ASTLabelType = CommonTree;

  // We're going to use the tokens defined in
  // both our GraphQueryLexer and GraphQueryParser grammars.
  // The GraphQueryParser grammar already includes
  // the tokens defined in the GraphQueryLexer grammar.
  tokenVocab = GraphQueryParser;
}

@header {

package invenio.op.parser;

import qng.core.logical.LogicalConstant;
import qng.core.logical.LogicalExpression;
import qng.core.logical.LogicalOperator;
import qng.core.logical.LogicalOperatorImpl;

import invenio.op.operation.impl.FromOperation;
import invenio.op.operation.impl.SelectOperation;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

}

@members {
  // currenty removes quotes - assumes that they are the first and last character in the String
  private static String convertFromStringLiteral(String text) {
  	if (text == null)
  		return null;
  	else
    	return text.substring(1, text.length() - 1);
  }
}


query returns [LogicalOperator result]
	: selectStatement { return $selectStatement.result; }
	;


selectStatement returns [LogicalOperator result]
scope { Map<String, String> contextParams; }
@init { $selectStatement::contextParams = new HashMap<String, String>(); }
	: (
	 (contextParams? baseSelect { $result = $baseSelect.result; } )
	 | (contextParams? joinSelect { $result = $joinSelect.result; } )
	)
	{
		for ( Map.Entry<String, String> p : $selectStatement::contextParams.entrySet() ) {
			$result.setContextParam(p.getKey(), p.getValue());
		}
	}
	;


contextParams
	: ^(CONTEXT_PARAMS (
		^(CONTEXT_PROP n=NAME val=STRING_LITERAL) {$selectStatement::contextParams.put($n.text, convertFromStringLiteral($val.text) ); }
	)* ) 
	;

baseSelect returns [LogicalOperator result]
@init { LogicalOperator last = null; }
	: ^(BASE_SELECT (s=selectStatement{last=$s.result;})? (c=clause[last] { last = $c.result; })+ ) {
		$result = last;
	}
	;
	
joinSelect returns [LogicalOperator result]
@init {
	$result = new LogicalOperatorImpl(Operations.JOIN.getName());
}
	: ^(JOIN_SELECT 
			left=selectStatement { $result.addOperand( left ); }
			right=selectStatement { $result.addOperand( right ); }
			( f1=function f2=function { $result.addOperand( new LogicalConstant(f1) ); $result.addOperand( new LogicalConstant(f2) ); } )?
		)
	;

clause[LogicalOperator last] returns [LogicalOperator result]
	: ^(CLAUSE r=operation[last]) { $result = r; }
	;
	
operation[LogicalOperator last] returns [LogicalOperator result]
@init {
	LogicalOperator op = null;
	List<LogicalExpression> args = null;
}

	: (
		  (s = c_select { op = $s.operator; args = $s.params; } )
  		| (f = c_from { op = $f.operator; args = $f.params; } )
  		| (tr = c_tab_reader { op = $tr.operator; args = $tr.params; } )
  		| (w = c_where { op = $w.operator; args = $w.params; } )
  		| (g = c_group_by  { op = $g.operator; args = $g.params; } )
  		| (m = c_merge_by  { op = $m.operator; args = $m.params; } )
  		| (sb = c_split_by  { op = $sb.operator; args = $sb.params; } )
  		| (h = c_having { op = $h.operator; args = $h.params; } )
  		| (o = c_order_by  { op = $o.operator; args = $o.params; } )
  		| (n = NAME  { op = new LogicalOperatorImpl( $n.text ); } )
  	)
  	{
  		$result = op;
  		if (last != null)
  			$result.addOperand(last);
  		if (args != null)
  			for ( LogicalExpression e : args ) {
  				if ( e instanceof LogicalOperator )	// special case when operand of operation is an operator - then enclose in a LogicalConstant
  					$result.addOperand( new LogicalConstant(e) );
  				else
	  				$result.addOperand( e );
	  		}
  	}
	;

c_select returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.SELECT.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_SELECT ^(CLAUSE_ARGUMENTS ( (p=select_all_param { $params.add(p); } ) | ( p=param { $params.add(p); } )* )    )   )
	;

c_order_by returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.ORDER_BY.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_ORDER_BY ^(CLAUSE_ARGUMENTS ( p=param { $params.add(p); } )*    )   )
	;

c_group_by returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.GROUP_BY.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_GROUP_BY ^(CLAUSE_ARGUMENTS ( p=param { $params.add(p); } )*    )   )
	;

c_merge_by returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.MERGE_BY.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_MERGE_BY ^(CLAUSE_ARGUMENTS ( d=merge_distinct_param { $params.add(d); } ) ( p=param { $params.add(p); } )*    )   )
	;

c_split_by returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.SPLIT_BY.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_SPLIT_BY ^(CLAUSE_ARGUMENTS (p=param { $params.add(p); } )    )   )
	;

c_having returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.HAVING.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_HAVING ^(CLAUSE_ARGUMENTS ( p=param { $params.add(p); } )    )   )
	;

c_where returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.WHERE.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_WHERE ^(CLAUSE_ARGUMENTS ( p=param { $params.add(p); } )    )   )
	;


c_from returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.FROM.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_FROM ^(CLAUSE_ARGUMENTS ( fp=from_type_param { $params.add(fp); } ) ( p=param { $params.add(p); } )    )   )
	;

c_tab_reader returns [LogicalOperator operator, List<LogicalExpression> params]
@init {
	$operator = new LogicalOperatorImpl( Operations.TAB_READER.getName() ); 
	$params = new ArrayList<LogicalExpression>();
 }
	: ^(C_TAB_READER ^(CLAUSE_ARGUMENTS ( p=param { $params.add(p); } )* ))
	;

param returns[LogicalExpression result]
	: ( arg_string { $result = $arg_string.result; } )
	 | ( arg_boolean { $result = $arg_boolean.result; } )
	 | ( arg_name { $result = $arg_name.result; } )
	 | ( arg_null { $result = $arg_null.result; } )
	 | ( arg_float { $result = $arg_float.result; } )
	 | ( arg_integer { $result = $arg_integer.result; } )
	 | ( function { $result = $function.result; } )
	;

select_all_param returns[LogicalConstant result]
	: STAR { $result = new LogicalConstant(SelectOperation.SELECT_ALL); }
	;
	
merge_distinct_param returns[LogicalConstant result]
	: (TRUE { $result = new LogicalConstant(true); } )
	| (FALSE { $result = new LogicalConstant(false); } )
	;

from_type_param returns[LogicalConstant result]
	: ( P_FROM_TYPE_GRAPH { $result = new LogicalConstant(FromOperation.TYPE.GRAPH); } )
	 | ( P_FROM_TYPE_ELEMENT { $result = new LogicalConstant(FromOperation.TYPE.ELEMENT); } )
	 | ( P_FROM_TYPE_NODE { $result = new LogicalConstant(FromOperation.TYPE.VERTEX); } )
	 | ( P_FROM_TYPE_EDGE { $result = new LogicalConstant(FromOperation.TYPE.EDGE); } )
	 | ( P_FROM_TYPE_ATTRIBUTE { $result = new LogicalConstant(FromOperation.TYPE.ATTRIBUTE); } )
	 ;


/*
query returns [LogicalExpression result]
	: function {$result = $function.result;};
*/



function returns [LogicalOperator result]
	: ^(FUNCTION name=NAME argument_list) {
		$result = new LogicalOperatorImpl( Operators.getOperatorName($name.text, false, true) );
		for (LogicalExpression exp : $argument_list.result) {
			$result.addOperand(exp);
		}
	}
	;


argument_list returns [List<LogicalExpression> result]
scope { List<LogicalExpression> current; }
@init { $argument_list::current = new ArrayList<LogicalExpression>(); }
	: ( 
	   ^(ARGUMENT_LIST (argument)*) {$result = $argument_list::current ;}
	  )
	;

// does not include arg_name
argument
	: ( arg_string { $argument_list::current.add( $arg_string.result ); }
	  | arg_boolean { $argument_list::current.add( $arg_boolean.result ); }
	  | arg_null { $argument_list::current.add( $arg_null.result ); }
	  | arg_float { $argument_list::current.add( $arg_float.result ); }
	  | arg_integer { $argument_list::current.add( $arg_integer.result ); }
	  | function { $argument_list::current.add( $function.result ); }
	  )
	;

arg_string returns [LogicalConstant result]
	: STRING_LITERAL {
	 	String s = $STRING_LITERAL.text;
		$result = new LogicalConstant( convertFromStringLiteral(s) ); // removes quotes
	}
	;
	
arg_name returns [LogicalConstant result]
	: NAME {
	 	String s = $NAME.text;
		$result = new LogicalConstant( s );
	}
	;
	
arg_boolean returns [LogicalConstant result]
	: BOOLEAN_LITERAL {
	 	String s = $BOOLEAN_LITERAL.text;
	 	if ( "TRUE".equalsIgnoreCase(s) )
			$result = new LogicalConstant( true );
		else
			$result = new LogicalConstant( false );
	}
	;

arg_null returns [LogicalConstant result]
	: NULL_LITERAL {
		$result = new LogicalConstant( null );
	}
	;

arg_float returns [LogicalConstant result]
	: FLOAT {
		$result = new LogicalConstant( Double.parseDouble($FLOAT.text ) );
	}
	;

arg_integer returns [LogicalConstant result]
	: INTEGER {
		$result = new LogicalConstant( Integer.parseInt($INTEGER.text ) );
	}
	;