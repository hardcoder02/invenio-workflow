parser grammar GraphQueryParser;


// Helpful links:
// http://www.antlr.org/wiki/display/ANTLR3/Tree+construction
// http://www.antlr.org/grammar/1209225566284/PLSQL3.g
// http://antlrv3ide.sourceforge.net/
// http://jnb.ociweb.com/jnb/jnbJun2008.html

options {
  // We're going to output an AST.
  output = AST;
  backtrack=true;
  memoize=true;
  
  // We're going to use the tokens defined in our MathLexer grammar.
  tokenVocab = GraphQueryLexer;
}

tokens {
  FUNCTION;
  ARGUMENT_LIST;
  
  BASE_SELECT;
  JOIN_SELECT;
  CLAUSE;
  CLAUSE_ARGUMENTS;
  
  C_SELECT;
  C_FROM;
  C_TAB_READER;
  C_WHERE;  
  C_GROUP_BY;
  C_HAVING;
  C_ORDER_BY;
  C_MERGE_BY;
  C_SPLIT_BY;
  
  P_FROM_TYPE_GRAPH;
  P_FROM_TYPE_ELEMENT;
  P_FROM_TYPE_NODE;
  P_FROM_TYPE_EDGE;
  P_FROM_TYPE_ATTRIBUTE;
  
  CONTEXT_PROP;
  CONTEXT_PARAMS;
}

@header { package invenio.op.parser; }

query
	: selectStatement EOF!;


selectStatement
	: contextParams? baseSelectStatement STATEMENT_TERMINATOR!
	| contextParams? joinStatement STATEMENT_TERMINATOR!
	;

contextParams
	: LEFT_BRACE contextProperty (COMMA contextProperty)* RIGHT_BRACE
	-> ^(CONTEXT_PARAMS contextProperty*)
	;

contextProperty
	: n=NAME ASSIGN val=STRING_LITERAL
	-> ^(CONTEXT_PROP $n $val)
	; 


baseSelectStatement
	: clauseSelect ( (clauseFrom clauseTabReader) | (S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN) | (S_FROM LEFT_PAREN joinStatement RIGHT_PAREN) ) clauseWhere? (clauseMergeBy | clauseSplitBy | clauseGroupBy)? clauseHaving? clauseOrderBy?
	-> ^(BASE_SELECT baseSelectStatement? joinStatement? clauseTabReader? clauseFrom? clauseWhere? clauseGroupBy? clauseMergeBy? clauseSplitBy? clauseHaving? clauseOrderBy? clauseSelect? )
	;
	
joinStatement
	: ( b1 = baseSelectStatement | (LEFT_PAREN j1 = joinStatement RIGHT_PAREN)) S_JOIN (b2=baseSelectStatement | (LEFT_PAREN j2=joinStatement RIGHT_PAREN)) (ON f1=function IS_EQUAL f2=function)?
	-> ^(JOIN_SELECT $b1? $j1? $b2? $j2? $f1? $f2?)
	;

clauseSelect
	: S_SELECT (STAR | (selectExpr (COMMA selectExpr)*) )
	-> ^(CLAUSE ^(C_SELECT ^(CLAUSE_ARGUMENTS STAR? selectExpr* )))
	;

selectExpr
	: NAME
	 | (function AS! NAME)
	;


clauseOrderBy
	: S_ORDER_BY orderByExpr (COMMA orderByExpr)*
		-> ^(CLAUSE ^(C_ORDER_BY ^(CLAUSE_ARGUMENTS orderByExpr (orderByExpr)*)))
	;

orderByExpr
	: NAME
	 | function
	;

clauseHaving
	: S_HAVING f=function
		-> ^(CLAUSE ^(C_HAVING ^(CLAUSE_ARGUMENTS $f)))
	;

clauseMergeBy
	: S_MERGE_BY mergeByDistinct BY (mergeByExpr (COMMA mergeByExpr)* )?
		-> ^(CLAUSE ^(C_MERGE_BY ^(CLAUSE_ARGUMENTS mergeByDistinct (mergeByExpr)*)))
	;

mergeByDistinct
	: DISTINCT -> TRUE
	 | -> FALSE	// default
	;

mergeByExpr
	: NAME
	 | (function AS! NAME)
	;

clauseSplitBy
	: S_SPLIT_BY n=NAME
		-> ^(CLAUSE ^(C_SPLIT_BY ^(CLAUSE_ARGUMENTS $n)))
	;

clauseGroupBy
	: S_GROUP_BY groupByExpr (COMMA groupByExpr)*
		-> ^(CLAUSE ^(C_GROUP_BY ^(CLAUSE_ARGUMENTS groupByExpr (groupByExpr)*)))
	;

groupByExpr
	: NAME
	 | (function AS! NAME)
	;

clauseWhere
	: S_WHERE f=function
		-> ^(CLAUSE ^(C_WHERE ^(CLAUSE_ARGUMENTS $f)))
	;

clauseFrom
	: S_FROM TYPE fr=fromType AS as=NAME
		-> ^(CLAUSE ^(C_FROM ^(CLAUSE_ARGUMENTS $fr $as)))
	;

fromType
	: FROM_TYPE_GRAPH -> P_FROM_TYPE_GRAPH
	 | FROM_TYPE_ELEMENT -> P_FROM_TYPE_ELEMENT
	 | FROM_TYPE_NODE -> P_FROM_TYPE_NODE
	 | FROM_TYPE_EDGE -> P_FROM_TYPE_EDGE
	 | FROM_TYPE_ATTRIBUTE -> P_FROM_TYPE_ATTRIBUTE
	 ;

clauseTabReader
	: S_TAB_READER nf=STRING_LITERAL ef=STRING_LITERAL
		-> ^(CLAUSE ^(C_TAB_READER ^(CLAUSE_ARGUMENTS $nf $ef)))
	;


function
	: fn=NAME LEFT_PAREN argument_list RIGHT_PAREN
		-> ^(FUNCTION $fn argument_list)
	;

argument_list
	: ( 
	  argument (COMMA argument)* -> ^(ARGUMENT_LIST argument (argument)*)
	  |  -> ^(ARGUMENT_LIST) // nothing
	  )
	;

argument
	: ( STRING_LITERAL
	  | BOOLEAN_LITERAL
	  | NULL_LITERAL
	  | FLOAT
	  | INTEGER
	  | function
	  )
	;
