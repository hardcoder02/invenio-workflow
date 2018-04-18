lexer grammar GraphQueryLexer;

@header { package invenio.op.parser; }


S_JOIN: 'JOIN' | 'join';
S_SELECT: 'SELECT' | 'select';
S_FROM: 'FROM' | 'from';
S_TAB_READER: 'TAB READER' | 'tab reader';
S_WHERE: 'WHERE' | 'where';
S_GROUP_BY: 'GROUP BY' | 'group by';
S_HAVING: 'HAVING' | 'having';
S_ORDER_BY: 'ORDER BY' | 'order by';
S_MERGE_BY: 'MERGE' | 'merge';
S_SPLIT_BY: 'SPLIT BY' | 'split by';

AS: 'AS' | 'as';
TYPE: 'TYPE' | 'type';
STAR: '*';
DISTINCT: 'DISTINCT' | 'distinct';
BY: 'BY' | 'by';
ON: 'ON' | 'on';

ASSIGN: '=';
IS_EQUAL: '==';
SIGN: '+' | '-';
LEFT_PAREN: '(';
RIGHT_PAREN: ')';
COMMA	:	',';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';
DOT: '.';
STATEMENT_TERMINATOR	:	';';

//UNQUOTED	:	~(LEFT_PAREN | RIGHT_PAREN | COMMA )+ {System.out.println(getText());};

FROM_TYPE_GRAPH: 'GRAPH' | 'graph';
FROM_TYPE_ELEMENT: 'ELEMENT' | 'element';
FROM_TYPE_NODE: 'NODE' | 'node';
FROM_TYPE_EDGE: 'EDGE' | 'edge';
FROM_TYPE_ATTRIBUTE: 'ATTRIBUTE' | 'attribute';

NULL_LITERAL: 'NULL' | 'null';
BOOLEAN_LITERAL: TRUE | FALSE;
fragment TRUE: 'TRUE' | 'true';
fragment FALSE: 'FALSE' | 'false';

//NUMBER: INTEGER | FLOAT;
//fragment FLOAT: INTEGER '.' '0'..'9'+;
//fragment INTEGER: '0' | SIGN? '1'..'9' '0'..'9'*;
FLOAT: SIGN? '0'..'9'+ '.' '0'..'9'+;
INTEGER: SIGN? '0'..'9'+;


NAME: LETTER (LETTER | DIGIT | '_')*;
//DOT_NAME: NAME (DOT NAME)*;
//STRING_LITERAL: '"' NONCONTROL_CHAR* '"';
STRING_LITERAL: '"' ~('"')* '"';


//fragment NONCONTROL_CHAR: LETTER | DIGIT | SYMBOL | SPACE;
fragment LETTER: LOWER | UPPER;
fragment LOWER: 'a'..'z';
fragment UPPER: 'A'..'Z';
fragment DIGIT: '0'..'9';
fragment SPACE: ' ' | '\t';

// Note that SYMBOL does not include the double-quote character.
fragment SYMBOL: '!' | '#'..'/' | ':'..'@' | '['..'`' | '{'..'~';

// Windows uses \r\n. UNIX and Mac OS X use \n.
NEWLINE: ('\r'? '\n')+ { $channel = HIDDEN; };
WHITESPACE: SPACE+ { $channel = HIDDEN; };