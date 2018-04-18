// $ANTLR 3.4 GraphQueryTree.g 2011-10-23 13:09:15


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



import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GraphQueryTree extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AS", "ASSIGN", "BOOLEAN_LITERAL", "BY", "COMMA", "DIGIT", "DISTINCT", "DOT", "FALSE", "FLOAT", "FROM_TYPE_ATTRIBUTE", "FROM_TYPE_EDGE", "FROM_TYPE_ELEMENT", "FROM_TYPE_GRAPH", "FROM_TYPE_NODE", "INTEGER", "IS_EQUAL", "LEFT_BRACE", "LEFT_PAREN", "LETTER", "LOWER", "NAME", "NEWLINE", "NULL_LITERAL", "ON", "RIGHT_BRACE", "RIGHT_PAREN", "SIGN", "SPACE", "STAR", "STATEMENT_TERMINATOR", "STRING_LITERAL", "SYMBOL", "S_FROM", "S_GROUP_BY", "S_HAVING", "S_JOIN", "S_MERGE_BY", "S_ORDER_BY", "S_SELECT", "S_SPLIT_BY", "S_TAB_READER", "S_WHERE", "TRUE", "TYPE", "UPPER", "WHITESPACE", "ARGUMENT_LIST", "BASE_SELECT", "CLAUSE", "CLAUSE_ARGUMENTS", "CONTEXT_PARAMS", "CONTEXT_PROP", "C_FROM", "C_GROUP_BY", "C_HAVING", "C_MERGE_BY", "C_ORDER_BY", "C_SELECT", "C_SPLIT_BY", "C_TAB_READER", "C_WHERE", "FUNCTION", "JOIN_SELECT", "P_FROM_TYPE_ATTRIBUTE", "P_FROM_TYPE_EDGE", "P_FROM_TYPE_ELEMENT", "P_FROM_TYPE_GRAPH", "P_FROM_TYPE_NODE"
    };

    public static final int EOF=-1;
    public static final int AS=4;
    public static final int ASSIGN=5;
    public static final int BOOLEAN_LITERAL=6;
    public static final int BY=7;
    public static final int COMMA=8;
    public static final int DIGIT=9;
    public static final int DISTINCT=10;
    public static final int DOT=11;
    public static final int FALSE=12;
    public static final int FLOAT=13;
    public static final int FROM_TYPE_ATTRIBUTE=14;
    public static final int FROM_TYPE_EDGE=15;
    public static final int FROM_TYPE_ELEMENT=16;
    public static final int FROM_TYPE_GRAPH=17;
    public static final int FROM_TYPE_NODE=18;
    public static final int INTEGER=19;
    public static final int IS_EQUAL=20;
    public static final int LEFT_BRACE=21;
    public static final int LEFT_PAREN=22;
    public static final int LETTER=23;
    public static final int LOWER=24;
    public static final int NAME=25;
    public static final int NEWLINE=26;
    public static final int NULL_LITERAL=27;
    public static final int ON=28;
    public static final int RIGHT_BRACE=29;
    public static final int RIGHT_PAREN=30;
    public static final int SIGN=31;
    public static final int SPACE=32;
    public static final int STAR=33;
    public static final int STATEMENT_TERMINATOR=34;
    public static final int STRING_LITERAL=35;
    public static final int SYMBOL=36;
    public static final int S_FROM=37;
    public static final int S_GROUP_BY=38;
    public static final int S_HAVING=39;
    public static final int S_JOIN=40;
    public static final int S_MERGE_BY=41;
    public static final int S_ORDER_BY=42;
    public static final int S_SELECT=43;
    public static final int S_SPLIT_BY=44;
    public static final int S_TAB_READER=45;
    public static final int S_WHERE=46;
    public static final int TRUE=47;
    public static final int TYPE=48;
    public static final int UPPER=49;
    public static final int WHITESPACE=50;
    public static final int ARGUMENT_LIST=51;
    public static final int BASE_SELECT=52;
    public static final int CLAUSE=53;
    public static final int CLAUSE_ARGUMENTS=54;
    public static final int CONTEXT_PARAMS=55;
    public static final int CONTEXT_PROP=56;
    public static final int C_FROM=57;
    public static final int C_GROUP_BY=58;
    public static final int C_HAVING=59;
    public static final int C_MERGE_BY=60;
    public static final int C_ORDER_BY=61;
    public static final int C_SELECT=62;
    public static final int C_SPLIT_BY=63;
    public static final int C_TAB_READER=64;
    public static final int C_WHERE=65;
    public static final int FUNCTION=66;
    public static final int JOIN_SELECT=67;
    public static final int P_FROM_TYPE_ATTRIBUTE=68;
    public static final int P_FROM_TYPE_EDGE=69;
    public static final int P_FROM_TYPE_ELEMENT=70;
    public static final int P_FROM_TYPE_GRAPH=71;
    public static final int P_FROM_TYPE_NODE=72;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public GraphQueryTree(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public GraphQueryTree(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return GraphQueryTree.tokenNames; }
    public String getGrammarFileName() { return "GraphQueryTree.g"; }


      // currenty removes quotes - assumes that they are the first and last character in the String
      private static String convertFromStringLiteral(String text) {
      	if (text == null)
      		return null;
      	else
        	return text.substring(1, text.length() - 1);
      }



    // $ANTLR start "query"
    // GraphQueryTree.g:44:1: query returns [LogicalOperator result] : selectStatement ;
    public final LogicalOperator query() throws RecognitionException {
        LogicalOperator result = null;


        LogicalOperator selectStatement1 =null;


        try {
            // GraphQueryTree.g:45:2: ( selectStatement )
            // GraphQueryTree.g:45:4: selectStatement
            {
            pushFollow(FOLLOW_selectStatement_in_query69);
            selectStatement1=selectStatement();

            state._fsp--;


             return selectStatement1; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "query"


    protected static class selectStatement_scope {
        Map<String, String> contextParams;
    }
    protected Stack selectStatement_stack = new Stack();



    // $ANTLR start "selectStatement"
    // GraphQueryTree.g:49:1: selectStatement returns [LogicalOperator result] : ( ( ( contextParams )? baseSelect ) | ( ( contextParams )? joinSelect ) ) ;
    public final LogicalOperator selectStatement() throws RecognitionException {
        selectStatement_stack.push(new selectStatement_scope());
        LogicalOperator result = null;


        LogicalOperator baseSelect2 =null;

        LogicalOperator joinSelect3 =null;


         ((selectStatement_scope)selectStatement_stack.peek()).contextParams = new HashMap<String, String>(); 
        try {
            // GraphQueryTree.g:52:2: ( ( ( ( contextParams )? baseSelect ) | ( ( contextParams )? joinSelect ) ) )
            // GraphQueryTree.g:52:4: ( ( ( contextParams )? baseSelect ) | ( ( contextParams )? joinSelect ) )
            {
            // GraphQueryTree.g:52:4: ( ( ( contextParams )? baseSelect ) | ( ( contextParams )? joinSelect ) )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // GraphQueryTree.g:53:3: ( ( contextParams )? baseSelect )
                    {
                    // GraphQueryTree.g:53:3: ( ( contextParams )? baseSelect )
                    // GraphQueryTree.g:53:4: ( contextParams )? baseSelect
                    {
                    // GraphQueryTree.g:53:4: ( contextParams )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==CONTEXT_PARAMS) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // GraphQueryTree.g:53:4: contextParams
                            {
                            pushFollow(FOLLOW_contextParams_in_selectStatement101);
                            contextParams();

                            state._fsp--;


                            }
                            break;

                    }


                    pushFollow(FOLLOW_baseSelect_in_selectStatement104);
                    baseSelect2=baseSelect();

                    state._fsp--;


                     result = baseSelect2; 

                    }


                    }
                    break;
                case 2 :
                    // GraphQueryTree.g:54:5: ( ( contextParams )? joinSelect )
                    {
                    // GraphQueryTree.g:54:5: ( ( contextParams )? joinSelect )
                    // GraphQueryTree.g:54:6: ( contextParams )? joinSelect
                    {
                    // GraphQueryTree.g:54:6: ( contextParams )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==CONTEXT_PARAMS) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // GraphQueryTree.g:54:6: contextParams
                            {
                            pushFollow(FOLLOW_contextParams_in_selectStatement115);
                            contextParams();

                            state._fsp--;


                            }
                            break;

                    }


                    pushFollow(FOLLOW_joinSelect_in_selectStatement118);
                    joinSelect3=joinSelect();

                    state._fsp--;


                     result = joinSelect3; 

                    }


                    }
                    break;

            }



            		for ( Map.Entry<String, String> p : ((selectStatement_scope)selectStatement_stack.peek()).contextParams.entrySet() ) {
            			result.setContextParam(p.getKey(), p.getValue());
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            selectStatement_stack.pop();
        }
        return result;
    }
    // $ANTLR end "selectStatement"



    // $ANTLR start "contextParams"
    // GraphQueryTree.g:64:1: contextParams : ^( CONTEXT_PARAMS ( ^( CONTEXT_PROP n= NAME val= STRING_LITERAL ) )* ) ;
    public final void contextParams() throws RecognitionException {
        CommonTree n=null;
        CommonTree val=null;

        try {
            // GraphQueryTree.g:65:2: ( ^( CONTEXT_PARAMS ( ^( CONTEXT_PROP n= NAME val= STRING_LITERAL ) )* ) )
            // GraphQueryTree.g:65:4: ^( CONTEXT_PARAMS ( ^( CONTEXT_PROP n= NAME val= STRING_LITERAL ) )* )
            {
            match(input,CONTEXT_PARAMS,FOLLOW_CONTEXT_PARAMS_in_contextParams141); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // GraphQueryTree.g:65:21: ( ^( CONTEXT_PROP n= NAME val= STRING_LITERAL ) )*
                loop4:
                do {
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==CONTEXT_PROP) ) {
                        alt4=1;
                    }


                    switch (alt4) {
                	case 1 :
                	    // GraphQueryTree.g:66:3: ^( CONTEXT_PROP n= NAME val= STRING_LITERAL )
                	    {
                	    match(input,CONTEXT_PROP,FOLLOW_CONTEXT_PROP_in_contextParams148); 

                	    match(input, Token.DOWN, null); 
                	    n=(CommonTree)match(input,NAME,FOLLOW_NAME_in_contextParams152); 

                	    val=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_contextParams156); 

                	    match(input, Token.UP, null); 


                	    ((selectStatement_scope)selectStatement_stack.peek()).contextParams.put((n!=null?n.getText():null), convertFromStringLiteral((val!=null?val.getText():null)) ); 

                	    }
                	    break;

                	default :
                	    break loop4;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "contextParams"



    // $ANTLR start "baseSelect"
    // GraphQueryTree.g:70:1: baseSelect returns [LogicalOperator result] : ^( BASE_SELECT (s= selectStatement )? (c= clause[last] )+ ) ;
    public final LogicalOperator baseSelect() throws RecognitionException {
        LogicalOperator result = null;


        LogicalOperator s =null;

        LogicalOperator c =null;


         LogicalOperator last = null; 
        try {
            // GraphQueryTree.g:72:2: ( ^( BASE_SELECT (s= selectStatement )? (c= clause[last] )+ ) )
            // GraphQueryTree.g:72:4: ^( BASE_SELECT (s= selectStatement )? (c= clause[last] )+ )
            {
            match(input,BASE_SELECT,FOLLOW_BASE_SELECT_in_baseSelect187); 

            match(input, Token.DOWN, null); 
            // GraphQueryTree.g:72:18: (s= selectStatement )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==BASE_SELECT||LA5_0==CONTEXT_PARAMS||LA5_0==JOIN_SELECT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // GraphQueryTree.g:72:19: s= selectStatement
                    {
                    pushFollow(FOLLOW_selectStatement_in_baseSelect192);
                    s=selectStatement();

                    state._fsp--;


                    last=s;

                    }
                    break;

            }


            // GraphQueryTree.g:72:56: (c= clause[last] )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==CLAUSE) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // GraphQueryTree.g:72:57: c= clause[last]
            	    {
            	    pushFollow(FOLLOW_clause_in_baseSelect200);
            	    c=clause(last);

            	    state._fsp--;


            	     last = c; 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); 



            		result = last;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "baseSelect"



    // $ANTLR start "joinSelect"
    // GraphQueryTree.g:77:1: joinSelect returns [LogicalOperator result] : ^( JOIN_SELECT left= selectStatement right= selectStatement (f1= function f2= function )? ) ;
    public final LogicalOperator joinSelect() throws RecognitionException {
        LogicalOperator result = null;


        LogicalOperator left =null;

        LogicalOperator right =null;

        LogicalOperator f1 =null;

        LogicalOperator f2 =null;



        	result = new LogicalOperatorImpl(Operations.JOIN.getName());

        try {
            // GraphQueryTree.g:81:2: ( ^( JOIN_SELECT left= selectStatement right= selectStatement (f1= function f2= function )? ) )
            // GraphQueryTree.g:81:4: ^( JOIN_SELECT left= selectStatement right= selectStatement (f1= function f2= function )? )
            {
            match(input,JOIN_SELECT,FOLLOW_JOIN_SELECT_in_joinSelect231); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_selectStatement_in_joinSelect239);
            left=selectStatement();

            state._fsp--;


             result.addOperand( left ); 

            pushFollow(FOLLOW_selectStatement_in_joinSelect248);
            right=selectStatement();

            state._fsp--;


             result.addOperand( right ); 

            // GraphQueryTree.g:84:4: (f1= function f2= function )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==FUNCTION) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // GraphQueryTree.g:84:6: f1= function f2= function
                    {
                    pushFollow(FOLLOW_function_in_joinSelect259);
                    f1=function();

                    state._fsp--;


                    pushFollow(FOLLOW_function_in_joinSelect263);
                    f2=function();

                    state._fsp--;


                     result.addOperand( new LogicalConstant(f1) ); result.addOperand( new LogicalConstant(f2) ); 

                    }
                    break;

            }


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "joinSelect"



    // $ANTLR start "clause"
    // GraphQueryTree.g:88:1: clause[LogicalOperator last] returns [LogicalOperator result] : ^( CLAUSE r= operation[last] ) ;
    public final LogicalOperator clause(LogicalOperator last) throws RecognitionException {
        LogicalOperator result = null;


        LogicalOperator r =null;


        try {
            // GraphQueryTree.g:89:2: ( ^( CLAUSE r= operation[last] ) )
            // GraphQueryTree.g:89:4: ^( CLAUSE r= operation[last] )
            {
            match(input,CLAUSE,FOLLOW_CLAUSE_in_clause289); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_operation_in_clause293);
            r=operation(last);

            state._fsp--;


            match(input, Token.UP, null); 


             result = r; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "clause"



    // $ANTLR start "operation"
    // GraphQueryTree.g:92:1: operation[LogicalOperator last] returns [LogicalOperator result] : ( (s= c_select ) | (f= c_from ) | (tr= c_tab_reader ) | (w= c_where ) | (g= c_group_by ) | (m= c_merge_by ) | (sb= c_split_by ) | (h= c_having ) | (o= c_order_by ) | (n= NAME ) ) ;
    public final LogicalOperator operation(LogicalOperator last) throws RecognitionException {
        LogicalOperator result = null;


        CommonTree n=null;
        GraphQueryTree.c_select_return s =null;

        GraphQueryTree.c_from_return f =null;

        GraphQueryTree.c_tab_reader_return tr =null;

        GraphQueryTree.c_where_return w =null;

        GraphQueryTree.c_group_by_return g =null;

        GraphQueryTree.c_merge_by_return m =null;

        GraphQueryTree.c_split_by_return sb =null;

        GraphQueryTree.c_having_return h =null;

        GraphQueryTree.c_order_by_return o =null;



        	LogicalOperator op = null;
        	List<LogicalExpression> args = null;

        try {
            // GraphQueryTree.g:98:2: ( ( (s= c_select ) | (f= c_from ) | (tr= c_tab_reader ) | (w= c_where ) | (g= c_group_by ) | (m= c_merge_by ) | (sb= c_split_by ) | (h= c_having ) | (o= c_order_by ) | (n= NAME ) ) )
            // GraphQueryTree.g:98:4: ( (s= c_select ) | (f= c_from ) | (tr= c_tab_reader ) | (w= c_where ) | (g= c_group_by ) | (m= c_merge_by ) | (sb= c_split_by ) | (h= c_having ) | (o= c_order_by ) | (n= NAME ) )
            {
            // GraphQueryTree.g:98:4: ( (s= c_select ) | (f= c_from ) | (tr= c_tab_reader ) | (w= c_where ) | (g= c_group_by ) | (m= c_merge_by ) | (sb= c_split_by ) | (h= c_having ) | (o= c_order_by ) | (n= NAME ) )
            int alt8=10;
            switch ( input.LA(1) ) {
            case C_SELECT:
                {
                alt8=1;
                }
                break;
            case C_FROM:
                {
                alt8=2;
                }
                break;
            case C_TAB_READER:
                {
                alt8=3;
                }
                break;
            case C_WHERE:
                {
                alt8=4;
                }
                break;
            case C_GROUP_BY:
                {
                alt8=5;
                }
                break;
            case C_MERGE_BY:
                {
                alt8=6;
                }
                break;
            case C_SPLIT_BY:
                {
                alt8=7;
                }
                break;
            case C_HAVING:
                {
                alt8=8;
                }
                break;
            case C_ORDER_BY:
                {
                alt8=9;
                }
                break;
            case NAME:
                {
                alt8=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }

            switch (alt8) {
                case 1 :
                    // GraphQueryTree.g:99:5: (s= c_select )
                    {
                    // GraphQueryTree.g:99:5: (s= c_select )
                    // GraphQueryTree.g:99:6: s= c_select
                    {
                    pushFollow(FOLLOW_c_select_in_operation331);
                    s=c_select();

                    state._fsp--;


                     op = (s!=null?s.operator:null); args = (s!=null?s.params:null); 

                    }


                    }
                    break;
                case 2 :
                    // GraphQueryTree.g:100:7: (f= c_from )
                    {
                    // GraphQueryTree.g:100:7: (f= c_from )
                    // GraphQueryTree.g:100:8: f= c_from
                    {
                    pushFollow(FOLLOW_c_from_in_operation348);
                    f=c_from();

                    state._fsp--;


                     op = (f!=null?f.operator:null); args = (f!=null?f.params:null); 

                    }


                    }
                    break;
                case 3 :
                    // GraphQueryTree.g:101:7: (tr= c_tab_reader )
                    {
                    // GraphQueryTree.g:101:7: (tr= c_tab_reader )
                    // GraphQueryTree.g:101:8: tr= c_tab_reader
                    {
                    pushFollow(FOLLOW_c_tab_reader_in_operation365);
                    tr=c_tab_reader();

                    state._fsp--;


                     op = (tr!=null?tr.operator:null); args = (tr!=null?tr.params:null); 

                    }


                    }
                    break;
                case 4 :
                    // GraphQueryTree.g:102:7: (w= c_where )
                    {
                    // GraphQueryTree.g:102:7: (w= c_where )
                    // GraphQueryTree.g:102:8: w= c_where
                    {
                    pushFollow(FOLLOW_c_where_in_operation382);
                    w=c_where();

                    state._fsp--;


                     op = (w!=null?w.operator:null); args = (w!=null?w.params:null); 

                    }


                    }
                    break;
                case 5 :
                    // GraphQueryTree.g:103:7: (g= c_group_by )
                    {
                    // GraphQueryTree.g:103:7: (g= c_group_by )
                    // GraphQueryTree.g:103:8: g= c_group_by
                    {
                    pushFollow(FOLLOW_c_group_by_in_operation399);
                    g=c_group_by();

                    state._fsp--;


                     op = (g!=null?g.operator:null); args = (g!=null?g.params:null); 

                    }


                    }
                    break;
                case 6 :
                    // GraphQueryTree.g:104:7: (m= c_merge_by )
                    {
                    // GraphQueryTree.g:104:7: (m= c_merge_by )
                    // GraphQueryTree.g:104:8: m= c_merge_by
                    {
                    pushFollow(FOLLOW_c_merge_by_in_operation417);
                    m=c_merge_by();

                    state._fsp--;


                     op = (m!=null?m.operator:null); args = (m!=null?m.params:null); 

                    }


                    }
                    break;
                case 7 :
                    // GraphQueryTree.g:105:7: (sb= c_split_by )
                    {
                    // GraphQueryTree.g:105:7: (sb= c_split_by )
                    // GraphQueryTree.g:105:8: sb= c_split_by
                    {
                    pushFollow(FOLLOW_c_split_by_in_operation435);
                    sb=c_split_by();

                    state._fsp--;


                     op = (sb!=null?sb.operator:null); args = (sb!=null?sb.params:null); 

                    }


                    }
                    break;
                case 8 :
                    // GraphQueryTree.g:106:7: (h= c_having )
                    {
                    // GraphQueryTree.g:106:7: (h= c_having )
                    // GraphQueryTree.g:106:8: h= c_having
                    {
                    pushFollow(FOLLOW_c_having_in_operation453);
                    h=c_having();

                    state._fsp--;


                     op = (h!=null?h.operator:null); args = (h!=null?h.params:null); 

                    }


                    }
                    break;
                case 9 :
                    // GraphQueryTree.g:107:7: (o= c_order_by )
                    {
                    // GraphQueryTree.g:107:7: (o= c_order_by )
                    // GraphQueryTree.g:107:8: o= c_order_by
                    {
                    pushFollow(FOLLOW_c_order_by_in_operation470);
                    o=c_order_by();

                    state._fsp--;


                     op = (o!=null?o.operator:null); args = (o!=null?o.params:null); 

                    }


                    }
                    break;
                case 10 :
                    // GraphQueryTree.g:108:7: (n= NAME )
                    {
                    // GraphQueryTree.g:108:7: (n= NAME )
                    // GraphQueryTree.g:108:8: n= NAME
                    {
                    n=(CommonTree)match(input,NAME,FOLLOW_NAME_in_operation488); 

                     op = new LogicalOperatorImpl( (n!=null?n.getText():null) ); 

                    }


                    }
                    break;

            }



              		result = op;
              		if (last != null)
              			result.addOperand(last);
              		if (args != null)
              			for ( LogicalExpression e : args ) {
              				if ( e instanceof LogicalOperator )	// special case when operand of operation is an operator - then enclose in a LogicalConstant
              					result.addOperand( new LogicalConstant(e) );
              				else
            	  				result.addOperand( e );
            	  		}
              	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "operation"


    public static class c_select_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_select"
    // GraphQueryTree.g:124:1: c_select returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_SELECT ^( CLAUSE_ARGUMENTS ( (p= select_all_param ) | (p= param )* ) ) ) ;
    public final GraphQueryTree.c_select_return c_select() throws RecognitionException {
        GraphQueryTree.c_select_return retval = new GraphQueryTree.c_select_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.SELECT.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:129:2: ( ^( C_SELECT ^( CLAUSE_ARGUMENTS ( (p= select_all_param ) | (p= param )* ) ) ) )
            // GraphQueryTree.g:129:4: ^( C_SELECT ^( CLAUSE_ARGUMENTS ( (p= select_all_param ) | (p= param )* ) ) )
            {
            match(input,C_SELECT,FOLLOW_C_SELECT_in_c_select524); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_select527); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // GraphQueryTree.g:129:34: ( (p= select_all_param ) | (p= param )* )
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==STAR) ) {
                    alt10=1;
                }
                else if ( (LA10_0==UP||LA10_0==BOOLEAN_LITERAL||LA10_0==FLOAT||LA10_0==INTEGER||LA10_0==NAME||LA10_0==NULL_LITERAL||LA10_0==STRING_LITERAL||LA10_0==FUNCTION) ) {
                    alt10=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 0, input);

                    throw nvae;

                }
                switch (alt10) {
                    case 1 :
                        // GraphQueryTree.g:129:36: (p= select_all_param )
                        {
                        // GraphQueryTree.g:129:36: (p= select_all_param )
                        // GraphQueryTree.g:129:37: p= select_all_param
                        {
                        pushFollow(FOLLOW_select_all_param_in_c_select534);
                        p=select_all_param();

                        state._fsp--;


                         retval.params.add(p); 

                        }


                        }
                        break;
                    case 2 :
                        // GraphQueryTree.g:129:80: (p= param )*
                        {
                        // GraphQueryTree.g:129:80: (p= param )*
                        loop9:
                        do {
                            int alt9=2;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==BOOLEAN_LITERAL||LA9_0==FLOAT||LA9_0==INTEGER||LA9_0==NAME||LA9_0==NULL_LITERAL||LA9_0==STRING_LITERAL||LA9_0==FUNCTION) ) {
                                alt9=1;
                            }


                            switch (alt9) {
                        	case 1 :
                        	    // GraphQueryTree.g:129:82: p= param
                        	    {
                        	    pushFollow(FOLLOW_param_in_c_select546);
                        	    p=param();

                        	    state._fsp--;


                        	     retval.params.add(p); 

                        	    }
                        	    break;

                        	default :
                        	    break loop9;
                            }
                        } while (true);


                        }
                        break;

                }


                match(input, Token.UP, null); 
            }


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_select"


    public static class c_order_by_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_order_by"
    // GraphQueryTree.g:132:1: c_order_by returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS (p= param )* ) ) ;
    public final GraphQueryTree.c_order_by_return c_order_by() throws RecognitionException {
        GraphQueryTree.c_order_by_return retval = new GraphQueryTree.c_order_by_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.ORDER_BY.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:137:2: ( ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS (p= param )* ) ) )
            // GraphQueryTree.g:137:4: ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS (p= param )* ) )
            {
            match(input,C_ORDER_BY,FOLLOW_C_ORDER_BY_in_c_order_by583); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_order_by586); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // GraphQueryTree.g:137:36: (p= param )*
                loop11:
                do {
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==BOOLEAN_LITERAL||LA11_0==FLOAT||LA11_0==INTEGER||LA11_0==NAME||LA11_0==NULL_LITERAL||LA11_0==STRING_LITERAL||LA11_0==FUNCTION) ) {
                        alt11=1;
                    }


                    switch (alt11) {
                	case 1 :
                	    // GraphQueryTree.g:137:38: p= param
                	    {
                	    pushFollow(FOLLOW_param_in_c_order_by592);
                	    p=param();

                	    state._fsp--;


                	     retval.params.add(p); 

                	    }
                	    break;

                	default :
                	    break loop11;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_order_by"


    public static class c_group_by_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_group_by"
    // GraphQueryTree.g:140:1: c_group_by returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS (p= param )* ) ) ;
    public final GraphQueryTree.c_group_by_return c_group_by() throws RecognitionException {
        GraphQueryTree.c_group_by_return retval = new GraphQueryTree.c_group_by_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.GROUP_BY.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:145:2: ( ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS (p= param )* ) ) )
            // GraphQueryTree.g:145:4: ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS (p= param )* ) )
            {
            match(input,C_GROUP_BY,FOLLOW_C_GROUP_BY_in_c_group_by627); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_group_by630); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // GraphQueryTree.g:145:36: (p= param )*
                loop12:
                do {
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==BOOLEAN_LITERAL||LA12_0==FLOAT||LA12_0==INTEGER||LA12_0==NAME||LA12_0==NULL_LITERAL||LA12_0==STRING_LITERAL||LA12_0==FUNCTION) ) {
                        alt12=1;
                    }


                    switch (alt12) {
                	case 1 :
                	    // GraphQueryTree.g:145:38: p= param
                	    {
                	    pushFollow(FOLLOW_param_in_c_group_by636);
                	    p=param();

                	    state._fsp--;


                	     retval.params.add(p); 

                	    }
                	    break;

                	default :
                	    break loop12;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_group_by"


    public static class c_merge_by_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_merge_by"
    // GraphQueryTree.g:148:1: c_merge_by returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS (d= merge_distinct_param ) (p= param )* ) ) ;
    public final GraphQueryTree.c_merge_by_return c_merge_by() throws RecognitionException {
        GraphQueryTree.c_merge_by_return retval = new GraphQueryTree.c_merge_by_return();
        retval.start = input.LT(1);


        LogicalConstant d =null;

        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.MERGE_BY.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:153:2: ( ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS (d= merge_distinct_param ) (p= param )* ) ) )
            // GraphQueryTree.g:153:4: ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS (d= merge_distinct_param ) (p= param )* ) )
            {
            match(input,C_MERGE_BY,FOLLOW_C_MERGE_BY_in_c_merge_by671); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_merge_by674); 

            match(input, Token.DOWN, null); 
            // GraphQueryTree.g:153:36: (d= merge_distinct_param )
            // GraphQueryTree.g:153:38: d= merge_distinct_param
            {
            pushFollow(FOLLOW_merge_distinct_param_in_c_merge_by680);
            d=merge_distinct_param();

            state._fsp--;


             retval.params.add(d); 

            }


            // GraphQueryTree.g:153:83: (p= param )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==BOOLEAN_LITERAL||LA13_0==FLOAT||LA13_0==INTEGER||LA13_0==NAME||LA13_0==NULL_LITERAL||LA13_0==STRING_LITERAL||LA13_0==FUNCTION) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // GraphQueryTree.g:153:85: p= param
            	    {
            	    pushFollow(FOLLOW_param_in_c_merge_by690);
            	    p=param();

            	    state._fsp--;


            	     retval.params.add(p); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            match(input, Token.UP, null); 


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_merge_by"


    public static class c_split_by_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_split_by"
    // GraphQueryTree.g:156:1: c_split_by returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS (p= param ) ) ) ;
    public final GraphQueryTree.c_split_by_return c_split_by() throws RecognitionException {
        GraphQueryTree.c_split_by_return retval = new GraphQueryTree.c_split_by_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.SPLIT_BY.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:161:2: ( ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS (p= param ) ) ) )
            // GraphQueryTree.g:161:4: ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS (p= param ) ) )
            {
            match(input,C_SPLIT_BY,FOLLOW_C_SPLIT_BY_in_c_split_by725); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_split_by728); 

            match(input, Token.DOWN, null); 
            // GraphQueryTree.g:161:36: (p= param )
            // GraphQueryTree.g:161:37: p= param
            {
            pushFollow(FOLLOW_param_in_c_split_by733);
            p=param();

            state._fsp--;


             retval.params.add(p); 

            }


            match(input, Token.UP, null); 


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_split_by"


    public static class c_having_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_having"
    // GraphQueryTree.g:164:1: c_having returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_HAVING ^( CLAUSE_ARGUMENTS (p= param ) ) ) ;
    public final GraphQueryTree.c_having_return c_having() throws RecognitionException {
        GraphQueryTree.c_having_return retval = new GraphQueryTree.c_having_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.HAVING.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:169:2: ( ^( C_HAVING ^( CLAUSE_ARGUMENTS (p= param ) ) ) )
            // GraphQueryTree.g:169:4: ^( C_HAVING ^( CLAUSE_ARGUMENTS (p= param ) ) )
            {
            match(input,C_HAVING,FOLLOW_C_HAVING_in_c_having767); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_having770); 

            match(input, Token.DOWN, null); 
            // GraphQueryTree.g:169:34: (p= param )
            // GraphQueryTree.g:169:36: p= param
            {
            pushFollow(FOLLOW_param_in_c_having776);
            p=param();

            state._fsp--;


             retval.params.add(p); 

            }


            match(input, Token.UP, null); 


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_having"


    public static class c_where_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_where"
    // GraphQueryTree.g:172:1: c_where returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_WHERE ^( CLAUSE_ARGUMENTS (p= param ) ) ) ;
    public final GraphQueryTree.c_where_return c_where() throws RecognitionException {
        GraphQueryTree.c_where_return retval = new GraphQueryTree.c_where_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.WHERE.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:177:2: ( ^( C_WHERE ^( CLAUSE_ARGUMENTS (p= param ) ) ) )
            // GraphQueryTree.g:177:4: ^( C_WHERE ^( CLAUSE_ARGUMENTS (p= param ) ) )
            {
            match(input,C_WHERE,FOLLOW_C_WHERE_in_c_where810); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_where813); 

            match(input, Token.DOWN, null); 
            // GraphQueryTree.g:177:33: (p= param )
            // GraphQueryTree.g:177:35: p= param
            {
            pushFollow(FOLLOW_param_in_c_where819);
            p=param();

            state._fsp--;


             retval.params.add(p); 

            }


            match(input, Token.UP, null); 


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_where"


    public static class c_from_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_from"
    // GraphQueryTree.g:181:1: c_from returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_FROM ^( CLAUSE_ARGUMENTS (fp= from_type_param ) (p= param ) ) ) ;
    public final GraphQueryTree.c_from_return c_from() throws RecognitionException {
        GraphQueryTree.c_from_return retval = new GraphQueryTree.c_from_return();
        retval.start = input.LT(1);


        LogicalConstant fp =null;

        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.FROM.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:186:2: ( ^( C_FROM ^( CLAUSE_ARGUMENTS (fp= from_type_param ) (p= param ) ) ) )
            // GraphQueryTree.g:186:4: ^( C_FROM ^( CLAUSE_ARGUMENTS (fp= from_type_param ) (p= param ) ) )
            {
            match(input,C_FROM,FOLLOW_C_FROM_in_c_from854); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_from857); 

            match(input, Token.DOWN, null); 
            // GraphQueryTree.g:186:32: (fp= from_type_param )
            // GraphQueryTree.g:186:34: fp= from_type_param
            {
            pushFollow(FOLLOW_from_type_param_in_c_from863);
            fp=from_type_param();

            state._fsp--;


             retval.params.add(fp); 

            }


            // GraphQueryTree.g:186:76: (p= param )
            // GraphQueryTree.g:186:78: p= param
            {
            pushFollow(FOLLOW_param_in_c_from873);
            p=param();

            state._fsp--;


             retval.params.add(p); 

            }


            match(input, Token.UP, null); 


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_from"


    public static class c_tab_reader_return extends TreeRuleReturnScope {
        public LogicalOperator operator;
        public List<LogicalExpression> params;
    };


    // $ANTLR start "c_tab_reader"
    // GraphQueryTree.g:189:1: c_tab_reader returns [LogicalOperator operator, List<LogicalExpression> params] : ^( C_TAB_READER ^( CLAUSE_ARGUMENTS (p= param )* ) ) ;
    public final GraphQueryTree.c_tab_reader_return c_tab_reader() throws RecognitionException {
        GraphQueryTree.c_tab_reader_return retval = new GraphQueryTree.c_tab_reader_return();
        retval.start = input.LT(1);


        LogicalExpression p =null;



        	retval.operator = new LogicalOperatorImpl( Operations.TAB_READER.getName() ); 
        	retval.params = new ArrayList<LogicalExpression>();
         
        try {
            // GraphQueryTree.g:194:2: ( ^( C_TAB_READER ^( CLAUSE_ARGUMENTS (p= param )* ) ) )
            // GraphQueryTree.g:194:4: ^( C_TAB_READER ^( CLAUSE_ARGUMENTS (p= param )* ) )
            {
            match(input,C_TAB_READER,FOLLOW_C_TAB_READER_in_c_tab_reader907); 

            match(input, Token.DOWN, null); 
            match(input,CLAUSE_ARGUMENTS,FOLLOW_CLAUSE_ARGUMENTS_in_c_tab_reader910); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // GraphQueryTree.g:194:38: (p= param )*
                loop14:
                do {
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==BOOLEAN_LITERAL||LA14_0==FLOAT||LA14_0==INTEGER||LA14_0==NAME||LA14_0==NULL_LITERAL||LA14_0==STRING_LITERAL||LA14_0==FUNCTION) ) {
                        alt14=1;
                    }


                    switch (alt14) {
                	case 1 :
                	    // GraphQueryTree.g:194:40: p= param
                	    {
                	    pushFollow(FOLLOW_param_in_c_tab_reader916);
                	    p=param();

                	    state._fsp--;


                	     retval.params.add(p); 

                	    }
                	    break;

                	default :
                	    break loop14;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            match(input, Token.UP, null); 


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "c_tab_reader"



    // $ANTLR start "param"
    // GraphQueryTree.g:197:1: param returns [LogicalExpression result] : ( ( arg_string ) | ( arg_boolean ) | ( arg_name ) | ( arg_null ) | ( arg_float ) | ( arg_integer ) | ( function ) );
    public final LogicalExpression param() throws RecognitionException {
        LogicalExpression result = null;


        LogicalConstant arg_string4 =null;

        LogicalConstant arg_boolean5 =null;

        LogicalConstant arg_name6 =null;

        LogicalConstant arg_null7 =null;

        LogicalConstant arg_float8 =null;

        LogicalConstant arg_integer9 =null;

        LogicalOperator function10 =null;


        try {
            // GraphQueryTree.g:198:2: ( ( arg_string ) | ( arg_boolean ) | ( arg_name ) | ( arg_null ) | ( arg_float ) | ( arg_integer ) | ( function ) )
            int alt15=7;
            switch ( input.LA(1) ) {
            case STRING_LITERAL:
                {
                alt15=1;
                }
                break;
            case BOOLEAN_LITERAL:
                {
                alt15=2;
                }
                break;
            case NAME:
                {
                alt15=3;
                }
                break;
            case NULL_LITERAL:
                {
                alt15=4;
                }
                break;
            case FLOAT:
                {
                alt15=5;
                }
                break;
            case INTEGER:
                {
                alt15=6;
                }
                break;
            case FUNCTION:
                {
                alt15=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // GraphQueryTree.g:198:4: ( arg_string )
                    {
                    // GraphQueryTree.g:198:4: ( arg_string )
                    // GraphQueryTree.g:198:6: arg_string
                    {
                    pushFollow(FOLLOW_arg_string_in_param940);
                    arg_string4=arg_string();

                    state._fsp--;


                     result = arg_string4; 

                    }


                    }
                    break;
                case 2 :
                    // GraphQueryTree.g:199:5: ( arg_boolean )
                    {
                    // GraphQueryTree.g:199:5: ( arg_boolean )
                    // GraphQueryTree.g:199:7: arg_boolean
                    {
                    pushFollow(FOLLOW_arg_boolean_in_param952);
                    arg_boolean5=arg_boolean();

                    state._fsp--;


                     result = arg_boolean5; 

                    }


                    }
                    break;
                case 3 :
                    // GraphQueryTree.g:200:5: ( arg_name )
                    {
                    // GraphQueryTree.g:200:5: ( arg_name )
                    // GraphQueryTree.g:200:7: arg_name
                    {
                    pushFollow(FOLLOW_arg_name_in_param964);
                    arg_name6=arg_name();

                    state._fsp--;


                     result = arg_name6; 

                    }


                    }
                    break;
                case 4 :
                    // GraphQueryTree.g:201:5: ( arg_null )
                    {
                    // GraphQueryTree.g:201:5: ( arg_null )
                    // GraphQueryTree.g:201:7: arg_null
                    {
                    pushFollow(FOLLOW_arg_null_in_param976);
                    arg_null7=arg_null();

                    state._fsp--;


                     result = arg_null7; 

                    }


                    }
                    break;
                case 5 :
                    // GraphQueryTree.g:202:5: ( arg_float )
                    {
                    // GraphQueryTree.g:202:5: ( arg_float )
                    // GraphQueryTree.g:202:7: arg_float
                    {
                    pushFollow(FOLLOW_arg_float_in_param988);
                    arg_float8=arg_float();

                    state._fsp--;


                     result = arg_float8; 

                    }


                    }
                    break;
                case 6 :
                    // GraphQueryTree.g:203:5: ( arg_integer )
                    {
                    // GraphQueryTree.g:203:5: ( arg_integer )
                    // GraphQueryTree.g:203:7: arg_integer
                    {
                    pushFollow(FOLLOW_arg_integer_in_param1000);
                    arg_integer9=arg_integer();

                    state._fsp--;


                     result = arg_integer9; 

                    }


                    }
                    break;
                case 7 :
                    // GraphQueryTree.g:204:5: ( function )
                    {
                    // GraphQueryTree.g:204:5: ( function )
                    // GraphQueryTree.g:204:7: function
                    {
                    pushFollow(FOLLOW_function_in_param1012);
                    function10=function();

                    state._fsp--;


                     result = function10; 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "param"



    // $ANTLR start "select_all_param"
    // GraphQueryTree.g:207:1: select_all_param returns [LogicalConstant result] : STAR ;
    public final LogicalConstant select_all_param() throws RecognitionException {
        LogicalConstant result = null;


        try {
            // GraphQueryTree.g:208:2: ( STAR )
            // GraphQueryTree.g:208:4: STAR
            {
            match(input,STAR,FOLLOW_STAR_in_select_all_param1030); 

             result = new LogicalConstant(SelectOperation.SELECT_ALL); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "select_all_param"



    // $ANTLR start "merge_distinct_param"
    // GraphQueryTree.g:211:1: merge_distinct_param returns [LogicalConstant result] : ( ( TRUE ) | ( FALSE ) );
    public final LogicalConstant merge_distinct_param() throws RecognitionException {
        LogicalConstant result = null;


        try {
            // GraphQueryTree.g:212:2: ( ( TRUE ) | ( FALSE ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==TRUE) ) {
                alt16=1;
            }
            else if ( (LA16_0==FALSE) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // GraphQueryTree.g:212:4: ( TRUE )
                    {
                    // GraphQueryTree.g:212:4: ( TRUE )
                    // GraphQueryTree.g:212:5: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_merge_distinct_param1048); 

                     result = new LogicalConstant(true); 

                    }


                    }
                    break;
                case 2 :
                    // GraphQueryTree.g:213:4: ( FALSE )
                    {
                    // GraphQueryTree.g:213:4: ( FALSE )
                    // GraphQueryTree.g:213:5: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_merge_distinct_param1058); 

                     result = new LogicalConstant(false); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "merge_distinct_param"



    // $ANTLR start "from_type_param"
    // GraphQueryTree.g:216:1: from_type_param returns [LogicalConstant result] : ( ( P_FROM_TYPE_GRAPH ) | ( P_FROM_TYPE_ELEMENT ) | ( P_FROM_TYPE_NODE ) | ( P_FROM_TYPE_EDGE ) | ( P_FROM_TYPE_ATTRIBUTE ) );
    public final LogicalConstant from_type_param() throws RecognitionException {
        LogicalConstant result = null;


        try {
            // GraphQueryTree.g:217:2: ( ( P_FROM_TYPE_GRAPH ) | ( P_FROM_TYPE_ELEMENT ) | ( P_FROM_TYPE_NODE ) | ( P_FROM_TYPE_EDGE ) | ( P_FROM_TYPE_ATTRIBUTE ) )
            int alt17=5;
            switch ( input.LA(1) ) {
            case P_FROM_TYPE_GRAPH:
                {
                alt17=1;
                }
                break;
            case P_FROM_TYPE_ELEMENT:
                {
                alt17=2;
                }
                break;
            case P_FROM_TYPE_NODE:
                {
                alt17=3;
                }
                break;
            case P_FROM_TYPE_EDGE:
                {
                alt17=4;
                }
                break;
            case P_FROM_TYPE_ATTRIBUTE:
                {
                alt17=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // GraphQueryTree.g:217:4: ( P_FROM_TYPE_GRAPH )
                    {
                    // GraphQueryTree.g:217:4: ( P_FROM_TYPE_GRAPH )
                    // GraphQueryTree.g:217:6: P_FROM_TYPE_GRAPH
                    {
                    match(input,P_FROM_TYPE_GRAPH,FOLLOW_P_FROM_TYPE_GRAPH_in_from_type_param1078); 

                     result = new LogicalConstant(FromOperation.TYPE.GRAPH); 

                    }


                    }
                    break;
                case 2 :
                    // GraphQueryTree.g:218:5: ( P_FROM_TYPE_ELEMENT )
                    {
                    // GraphQueryTree.g:218:5: ( P_FROM_TYPE_ELEMENT )
                    // GraphQueryTree.g:218:7: P_FROM_TYPE_ELEMENT
                    {
                    match(input,P_FROM_TYPE_ELEMENT,FOLLOW_P_FROM_TYPE_ELEMENT_in_from_type_param1090); 

                     result = new LogicalConstant(FromOperation.TYPE.ELEMENT); 

                    }


                    }
                    break;
                case 3 :
                    // GraphQueryTree.g:219:5: ( P_FROM_TYPE_NODE )
                    {
                    // GraphQueryTree.g:219:5: ( P_FROM_TYPE_NODE )
                    // GraphQueryTree.g:219:7: P_FROM_TYPE_NODE
                    {
                    match(input,P_FROM_TYPE_NODE,FOLLOW_P_FROM_TYPE_NODE_in_from_type_param1102); 

                     result = new LogicalConstant(FromOperation.TYPE.VERTEX); 

                    }


                    }
                    break;
                case 4 :
                    // GraphQueryTree.g:220:5: ( P_FROM_TYPE_EDGE )
                    {
                    // GraphQueryTree.g:220:5: ( P_FROM_TYPE_EDGE )
                    // GraphQueryTree.g:220:7: P_FROM_TYPE_EDGE
                    {
                    match(input,P_FROM_TYPE_EDGE,FOLLOW_P_FROM_TYPE_EDGE_in_from_type_param1114); 

                     result = new LogicalConstant(FromOperation.TYPE.EDGE); 

                    }


                    }
                    break;
                case 5 :
                    // GraphQueryTree.g:221:5: ( P_FROM_TYPE_ATTRIBUTE )
                    {
                    // GraphQueryTree.g:221:5: ( P_FROM_TYPE_ATTRIBUTE )
                    // GraphQueryTree.g:221:7: P_FROM_TYPE_ATTRIBUTE
                    {
                    match(input,P_FROM_TYPE_ATTRIBUTE,FOLLOW_P_FROM_TYPE_ATTRIBUTE_in_from_type_param1126); 

                     result = new LogicalConstant(FromOperation.TYPE.ATTRIBUTE); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "from_type_param"



    // $ANTLR start "function"
    // GraphQueryTree.g:232:1: function returns [LogicalOperator result] : ^( FUNCTION name= NAME argument_list ) ;
    public final LogicalOperator function() throws RecognitionException {
        LogicalOperator result = null;


        CommonTree name=null;
        List<LogicalExpression> argument_list11 =null;


        try {
            // GraphQueryTree.g:233:2: ( ^( FUNCTION name= NAME argument_list ) )
            // GraphQueryTree.g:233:4: ^( FUNCTION name= NAME argument_list )
            {
            match(input,FUNCTION,FOLLOW_FUNCTION_in_function1153); 

            match(input, Token.DOWN, null); 
            name=(CommonTree)match(input,NAME,FOLLOW_NAME_in_function1157); 

            pushFollow(FOLLOW_argument_list_in_function1159);
            argument_list11=argument_list();

            state._fsp--;


            match(input, Token.UP, null); 



            		result = new LogicalOperatorImpl( Operators.getOperatorName((name!=null?name.getText():null), false, true) );
            		for (LogicalExpression exp : argument_list11) {
            			result.addOperand(exp);
            		}
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "function"


    protected static class argument_list_scope {
        List<LogicalExpression> current;
    }
    protected Stack argument_list_stack = new Stack();



    // $ANTLR start "argument_list"
    // GraphQueryTree.g:242:1: argument_list returns [List<LogicalExpression> result] : ( ^( ARGUMENT_LIST ( argument )* ) ) ;
    public final List<LogicalExpression> argument_list() throws RecognitionException {
        argument_list_stack.push(new argument_list_scope());
        List<LogicalExpression> result = null;


         ((argument_list_scope)argument_list_stack.peek()).current = new ArrayList<LogicalExpression>(); 
        try {
            // GraphQueryTree.g:245:2: ( ( ^( ARGUMENT_LIST ( argument )* ) ) )
            // GraphQueryTree.g:245:4: ( ^( ARGUMENT_LIST ( argument )* ) )
            {
            // GraphQueryTree.g:245:4: ( ^( ARGUMENT_LIST ( argument )* ) )
            // GraphQueryTree.g:246:5: ^( ARGUMENT_LIST ( argument )* )
            {
            match(input,ARGUMENT_LIST,FOLLOW_ARGUMENT_LIST_in_argument_list1195); 

            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // GraphQueryTree.g:246:21: ( argument )*
                loop18:
                do {
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==BOOLEAN_LITERAL||LA18_0==FLOAT||LA18_0==INTEGER||LA18_0==NULL_LITERAL||LA18_0==STRING_LITERAL||LA18_0==FUNCTION) ) {
                        alt18=1;
                    }


                    switch (alt18) {
                	case 1 :
                	    // GraphQueryTree.g:246:22: argument
                	    {
                	    pushFollow(FOLLOW_argument_in_argument_list1198);
                	    argument();

                	    state._fsp--;


                	    }
                	    break;

                	default :
                	    break loop18;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }


            result = ((argument_list_scope)argument_list_stack.peek()).current ;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            argument_list_stack.pop();
        }
        return result;
    }
    // $ANTLR end "argument_list"



    // $ANTLR start "argument"
    // GraphQueryTree.g:251:1: argument : ( arg_string | arg_boolean | arg_null | arg_float | arg_integer | function ) ;
    public final void argument() throws RecognitionException {
        LogicalConstant arg_string12 =null;

        LogicalConstant arg_boolean13 =null;

        LogicalConstant arg_null14 =null;

        LogicalConstant arg_float15 =null;

        LogicalConstant arg_integer16 =null;

        LogicalOperator function17 =null;


        try {
            // GraphQueryTree.g:252:2: ( ( arg_string | arg_boolean | arg_null | arg_float | arg_integer | function ) )
            // GraphQueryTree.g:252:4: ( arg_string | arg_boolean | arg_null | arg_float | arg_integer | function )
            {
            // GraphQueryTree.g:252:4: ( arg_string | arg_boolean | arg_null | arg_float | arg_integer | function )
            int alt19=6;
            switch ( input.LA(1) ) {
            case STRING_LITERAL:
                {
                alt19=1;
                }
                break;
            case BOOLEAN_LITERAL:
                {
                alt19=2;
                }
                break;
            case NULL_LITERAL:
                {
                alt19=3;
                }
                break;
            case FLOAT:
                {
                alt19=4;
                }
                break;
            case INTEGER:
                {
                alt19=5;
                }
                break;
            case FUNCTION:
                {
                alt19=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // GraphQueryTree.g:252:6: arg_string
                    {
                    pushFollow(FOLLOW_arg_string_in_argument1222);
                    arg_string12=arg_string();

                    state._fsp--;


                     ((argument_list_scope)argument_list_stack.peek()).current.add( arg_string12 ); 

                    }
                    break;
                case 2 :
                    // GraphQueryTree.g:253:6: arg_boolean
                    {
                    pushFollow(FOLLOW_arg_boolean_in_argument1231);
                    arg_boolean13=arg_boolean();

                    state._fsp--;


                     ((argument_list_scope)argument_list_stack.peek()).current.add( arg_boolean13 ); 

                    }
                    break;
                case 3 :
                    // GraphQueryTree.g:254:6: arg_null
                    {
                    pushFollow(FOLLOW_arg_null_in_argument1240);
                    arg_null14=arg_null();

                    state._fsp--;


                     ((argument_list_scope)argument_list_stack.peek()).current.add( arg_null14 ); 

                    }
                    break;
                case 4 :
                    // GraphQueryTree.g:255:6: arg_float
                    {
                    pushFollow(FOLLOW_arg_float_in_argument1249);
                    arg_float15=arg_float();

                    state._fsp--;


                     ((argument_list_scope)argument_list_stack.peek()).current.add( arg_float15 ); 

                    }
                    break;
                case 5 :
                    // GraphQueryTree.g:256:6: arg_integer
                    {
                    pushFollow(FOLLOW_arg_integer_in_argument1258);
                    arg_integer16=arg_integer();

                    state._fsp--;


                     ((argument_list_scope)argument_list_stack.peek()).current.add( arg_integer16 ); 

                    }
                    break;
                case 6 :
                    // GraphQueryTree.g:257:6: function
                    {
                    pushFollow(FOLLOW_function_in_argument1267);
                    function17=function();

                    state._fsp--;


                     ((argument_list_scope)argument_list_stack.peek()).current.add( function17 ); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "argument"



    // $ANTLR start "arg_string"
    // GraphQueryTree.g:261:1: arg_string returns [LogicalConstant result] : STRING_LITERAL ;
    public final LogicalConstant arg_string() throws RecognitionException {
        LogicalConstant result = null;


        CommonTree STRING_LITERAL18=null;

        try {
            // GraphQueryTree.g:262:2: ( STRING_LITERAL )
            // GraphQueryTree.g:262:4: STRING_LITERAL
            {
            STRING_LITERAL18=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_arg_string1289); 


            	 	String s = (STRING_LITERAL18!=null?STRING_LITERAL18.getText():null);
            		result = new LogicalConstant( convertFromStringLiteral(s) ); // removes quotes
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "arg_string"



    // $ANTLR start "arg_name"
    // GraphQueryTree.g:268:1: arg_name returns [LogicalConstant result] : NAME ;
    public final LogicalConstant arg_name() throws RecognitionException {
        LogicalConstant result = null;


        CommonTree NAME19=null;

        try {
            // GraphQueryTree.g:269:2: ( NAME )
            // GraphQueryTree.g:269:4: NAME
            {
            NAME19=(CommonTree)match(input,NAME,FOLLOW_NAME_in_arg_name1307); 


            	 	String s = (NAME19!=null?NAME19.getText():null);
            		result = new LogicalConstant( s );
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "arg_name"



    // $ANTLR start "arg_boolean"
    // GraphQueryTree.g:275:1: arg_boolean returns [LogicalConstant result] : BOOLEAN_LITERAL ;
    public final LogicalConstant arg_boolean() throws RecognitionException {
        LogicalConstant result = null;


        CommonTree BOOLEAN_LITERAL20=null;

        try {
            // GraphQueryTree.g:276:2: ( BOOLEAN_LITERAL )
            // GraphQueryTree.g:276:4: BOOLEAN_LITERAL
            {
            BOOLEAN_LITERAL20=(CommonTree)match(input,BOOLEAN_LITERAL,FOLLOW_BOOLEAN_LITERAL_in_arg_boolean1325); 


            	 	String s = (BOOLEAN_LITERAL20!=null?BOOLEAN_LITERAL20.getText():null);
            	 	if ( "TRUE".equalsIgnoreCase(s) )
            			result = new LogicalConstant( true );
            		else
            			result = new LogicalConstant( false );
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "arg_boolean"



    // $ANTLR start "arg_null"
    // GraphQueryTree.g:285:1: arg_null returns [LogicalConstant result] : NULL_LITERAL ;
    public final LogicalConstant arg_null() throws RecognitionException {
        LogicalConstant result = null;


        try {
            // GraphQueryTree.g:286:2: ( NULL_LITERAL )
            // GraphQueryTree.g:286:4: NULL_LITERAL
            {
            match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_arg_null1342); 


            		result = new LogicalConstant( null );
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "arg_null"



    // $ANTLR start "arg_float"
    // GraphQueryTree.g:291:1: arg_float returns [LogicalConstant result] : FLOAT ;
    public final LogicalConstant arg_float() throws RecognitionException {
        LogicalConstant result = null;


        CommonTree FLOAT21=null;

        try {
            // GraphQueryTree.g:292:2: ( FLOAT )
            // GraphQueryTree.g:292:4: FLOAT
            {
            FLOAT21=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_arg_float1359); 


            		result = new LogicalConstant( Double.parseDouble((FLOAT21!=null?FLOAT21.getText():null) ) );
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "arg_float"



    // $ANTLR start "arg_integer"
    // GraphQueryTree.g:297:1: arg_integer returns [LogicalConstant result] : INTEGER ;
    public final LogicalConstant arg_integer() throws RecognitionException {
        LogicalConstant result = null;


        CommonTree INTEGER22=null;

        try {
            // GraphQueryTree.g:298:2: ( INTEGER )
            // GraphQueryTree.g:298:4: INTEGER
            {
            INTEGER22=(CommonTree)match(input,INTEGER,FOLLOW_INTEGER_in_arg_integer1376); 


            		result = new LogicalConstant( Integer.parseInt((INTEGER22!=null?INTEGER22.getText():null) ) );
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "arg_integer"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\13\uffff";
    static final String DFA3_eofS =
        "\13\uffff";
    static final String DFA3_minS =
        "\1\64\1\2\2\uffff\1\3\1\2\1\64\1\31\1\43\2\3";
    static final String DFA3_maxS =
        "\1\103\1\2\2\uffff\1\70\1\2\1\103\1\31\1\43\1\3\1\70";
    static final String DFA3_acceptS =
        "\2\uffff\1\1\1\2\7\uffff";
    static final String DFA3_specialS =
        "\13\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\2\uffff\1\1\13\uffff\1\3",
            "\1\4",
            "",
            "",
            "\1\6\64\uffff\1\5",
            "\1\7",
            "\1\2\16\uffff\1\3",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\6\64\uffff\1\5"
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "52:4: ( ( ( contextParams )? baseSelect ) | ( ( contextParams )? joinSelect ) )";
        }
    }
 

    public static final BitSet FOLLOW_selectStatement_in_query69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextParams_in_selectStatement101 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_baseSelect_in_selectStatement104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextParams_in_selectStatement115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_joinSelect_in_selectStatement118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONTEXT_PARAMS_in_contextParams141 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CONTEXT_PROP_in_contextParams148 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAME_in_contextParams152 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_contextParams156 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BASE_SELECT_in_baseSelect187 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selectStatement_in_baseSelect192 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_clause_in_baseSelect200 = new BitSet(new long[]{0x0020000000000008L});
    public static final BitSet FOLLOW_JOIN_SELECT_in_joinSelect231 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_selectStatement_in_joinSelect239 = new BitSet(new long[]{0x0090000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_selectStatement_in_joinSelect248 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000004L});
    public static final BitSet FOLLOW_function_in_joinSelect259 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_function_in_joinSelect263 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CLAUSE_in_clause289 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_operation_in_clause293 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_c_select_in_operation331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_from_in_operation348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_tab_reader_in_operation365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_where_in_operation382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_group_by_in_operation399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_merge_by_in_operation417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_split_by_in_operation435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_having_in_operation453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_c_order_by_in_operation470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_operation488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_C_SELECT_in_c_select524 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_select527 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_select_all_param_in_c_select534 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_param_in_c_select546 = new BitSet(new long[]{0x000000080A082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_C_ORDER_BY_in_c_order_by583 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_order_by586 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_order_by592 = new BitSet(new long[]{0x000000080A082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_C_GROUP_BY_in_c_group_by627 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_group_by630 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_group_by636 = new BitSet(new long[]{0x000000080A082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_C_MERGE_BY_in_c_merge_by671 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_merge_by674 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_merge_distinct_param_in_c_merge_by680 = new BitSet(new long[]{0x000000080A082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_merge_by690 = new BitSet(new long[]{0x000000080A082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_C_SPLIT_BY_in_c_split_by725 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_split_by728 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_split_by733 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_C_HAVING_in_c_having767 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_having770 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_having776 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_C_WHERE_in_c_where810 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_where813 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_where819 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_C_FROM_in_c_from854 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_from857 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_from_type_param_in_c_from863 = new BitSet(new long[]{0x000000080A082040L,0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_from873 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_C_TAB_READER_in_c_tab_reader907 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_CLAUSE_ARGUMENTS_in_c_tab_reader910 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_c_tab_reader916 = new BitSet(new long[]{0x000000080A082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_arg_string_in_param940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_boolean_in_param952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_name_in_param964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_null_in_param976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_float_in_param988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_integer_in_param1000 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_param1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_select_all_param1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_merge_distinct_param1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_merge_distinct_param1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_FROM_TYPE_GRAPH_in_from_type_param1078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_FROM_TYPE_ELEMENT_in_from_type_param1090 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_FROM_TYPE_NODE_in_from_type_param1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_FROM_TYPE_EDGE_in_from_type_param1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_P_FROM_TYPE_ATTRIBUTE_in_from_type_param1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_function1153 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NAME_in_function1157 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_argument_list_in_function1159 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGUMENT_LIST_in_argument_list1195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_argument_in_argument_list1198 = new BitSet(new long[]{0x0000000808082048L,0x0000000000000004L});
    public static final BitSet FOLLOW_arg_string_in_argument1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_boolean_in_argument1231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_null_in_argument1240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_float_in_argument1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_integer_in_argument1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_argument1267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_arg_string1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_arg_name1307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_LITERAL_in_arg_boolean1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_LITERAL_in_arg_null1342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_arg_float1359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_arg_integer1376 = new BitSet(new long[]{0x0000000000000002L});

}