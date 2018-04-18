lexer grammar GraphQueryLexer;

@header { package invenio.op.parser; }

options {
  // We're going to output an AST.
  output = AST;
  backtrack=true;
  memoize=true;
}

tokens {
  FUNCTION;
  ARGUMENT_LIST;
  
  BASE_SELECT;
  JOIN_SELECT;
  CLAUSE;
  TAB_READER;
  SELECT;
  FROM;
  ORDER_BY;
}

@members {
    public static void main(String[] args) throws Exception {
        GraphQueryLexer lex = new GraphQueryLexer(new ANTLRStringStream("func1(abc(2),def(),3)"));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        GraphQueryParser parser = new GraphQueryParser(tokens);

        try {
        	GraphQueryParser.query_return parserResult =
            	parser.query(); // start rule method
            CommonTree tree = (CommonTree) parserResult.getTree();
            System.out.println(tree);
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
}


query
	: selectStatement EOF!;


selectStatement
	: baseSelectStatement STATEMENT_TERMINATOR!
	| joinStatement STATEMENT_TERMINATOR!
	;




baseSelectStatement
	: clauseSelect clauseOrderBy? clauseGroupBy? clauseWhere? ( (clauseFrom clauseTabReader) | ('FROM' LEFT_PAREN baseSelectStatement RIGHT_PAREN) | ('FROM' LEFT_PAREN joinStatement RIGHT_PAREN) )
	-> ^(BASE_SELECT clauseSelect clauseOrderBy? clauseGroupBy? clauseWhere? (clauseFrom clauseTabReader)? baseSelectStatement? joinStatement?)
	;
	
joinStatement
	: ( b1 = baseSelectStatement | (LEFT_PAREN j1 = joinStatement RIGHT_PAREN)) 'JOIN' (b2=baseSelectStatement | (LEFT_PAREN j2=joinStatement RIGHT_PAREN))
	-> ^(JOIN_SELECT $b1? $j1? $b2? $j2?)
	;

clauseSelect
	: 'SELECT'
	-> ^(CLAUSE SELECT)
	;
	
clauseOrderBy
	: 'ORDER BY'
		-> ^(CLAUSE ORDER_BY)
	;

clauseGroupBy
	: 'GROUP BY'
	;

clauseWhere
	: 'WHERE'
	;

clauseFrom
	: 'FROM'
		-> ^(CLAUSE FROM)
	;

clauseTabReader
	: 'TAB READER' nf=STRING_LITERAL ef=STRING_LITERAL
		-> ^(CLAUSE TAB_READER $nf $ef)
	;

/*
function
	: fn=UNQUOTED LEFT_PAREN argument_list RIGHT_PAREN
		-> ^(FUNCTION $fn argument_list)
	;

argument_list
	: ( 
	  argument (COMMA argument)* -> ^(ARGUMENT_LIST argument (argument)*)
	  |  -> ^(ARGUMENT_LIST) // nothing
	  )
	;

argument
	: ( UNQUOTED
	  | function
	  )
	;

*/
ASSIGN: '=';
SIGN: '+' | '-';
LEFT_PAREN: '(';
RIGHT_PAREN: ')';
COMMA	:	',';
STATEMENT_TERMINATOR	:	';';

//UNQUOTED	:	~(LEFT_PAREN | RIGHT_PAREN | COMMA )+ {System.out.println(getText());};

NUMBER: INTEGER | FLOAT;
fragment FLOAT: INTEGER '.' '0'..'9'+;
fragment INTEGER: '0' | SIGN? '1'..'9' '0'..'9'*;
NAME: LETTER (LETTER | DIGIT | '_')*;
//STRING_LITERAL: '"' NONCONTROL_CHAR* '"';
STRING_LITERAL: '"' ~('"')* '"';

fragment NONCONTROL_CHAR: LETTER | DIGIT | SYMBOL | SPACE;
fragment LETTER: LOWER | UPPER;
fragment LOWER: 'a'..'z';
fragment UPPER: 'A'..'Z';
fragment DIGIT: '0'..'9';
fragment SPACE: ' ' | '\t';

// Note that SYMBOL does not include the double-quote character.
fragment SYMBOL: '!' | '#'..'/' | ':'..'@' | '['..'`' | '{'..'~';

// Windows uses \r\n. UNIX and Mac OS X use \n.
// To use newlines as a terminator,
// they can't be written to the hidden channel!
NEWLINE: ('\r'? '\n')+;
WHITESPACE: SPACE+ { $channel = HIDDEN; };