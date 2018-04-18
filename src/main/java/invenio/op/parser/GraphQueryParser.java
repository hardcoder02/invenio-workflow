// $ANTLR 3.4 GraphQueryParser.g 2011-10-23 13:09:11
 package invenio.op.parser; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class GraphQueryParser extends Parser {
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public GraphQueryParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public GraphQueryParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[61+1];
         

    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return GraphQueryParser.tokenNames; }
    public String getGrammarFileName() { return "GraphQueryParser.g"; }


    public static class query_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "query"
    // GraphQueryParser.g:51:1: query : selectStatement EOF !;
    public final GraphQueryParser.query_return query() throws RecognitionException {
        GraphQueryParser.query_return retval = new GraphQueryParser.query_return();
        retval.start = input.LT(1);

        int query_StartIndex = input.index();

        Object root_0 = null;

        Token EOF2=null;
        GraphQueryParser.selectStatement_return selectStatement1 =null;


        Object EOF2_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // GraphQueryParser.g:52:2: ( selectStatement EOF !)
            // GraphQueryParser.g:52:4: selectStatement EOF !
            {
            root_0 = (Object)adaptor.nil();


            pushFollow(FOLLOW_selectStatement_in_query201);
            selectStatement1=selectStatement();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, selectStatement1.getTree());

            EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_query203); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, query_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "query"


    public static class selectStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectStatement"
    // GraphQueryParser.g:55:1: selectStatement : ( ( contextParams )? baseSelectStatement STATEMENT_TERMINATOR !| ( contextParams )? joinStatement STATEMENT_TERMINATOR !);
    public final GraphQueryParser.selectStatement_return selectStatement() throws RecognitionException {
        GraphQueryParser.selectStatement_return retval = new GraphQueryParser.selectStatement_return();
        retval.start = input.LT(1);

        int selectStatement_StartIndex = input.index();

        Object root_0 = null;

        Token STATEMENT_TERMINATOR5=null;
        Token STATEMENT_TERMINATOR8=null;
        GraphQueryParser.contextParams_return contextParams3 =null;

        GraphQueryParser.baseSelectStatement_return baseSelectStatement4 =null;

        GraphQueryParser.contextParams_return contextParams6 =null;

        GraphQueryParser.joinStatement_return joinStatement7 =null;


        Object STATEMENT_TERMINATOR5_tree=null;
        Object STATEMENT_TERMINATOR8_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // GraphQueryParser.g:56:2: ( ( contextParams )? baseSelectStatement STATEMENT_TERMINATOR !| ( contextParams )? joinStatement STATEMENT_TERMINATOR !)
            int alt3=2;
            switch ( input.LA(1) ) {
            case LEFT_BRACE:
                {
                int LA3_1 = input.LA(2);

                if ( (synpred2_GraphQueryParser()) ) {
                    alt3=1;
                }
                else if ( (true) ) {
                    alt3=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }
                }
                break;
            case S_SELECT:
                {
                int LA3_2 = input.LA(2);

                if ( (synpred2_GraphQueryParser()) ) {
                    alt3=1;
                }
                else if ( (true) ) {
                    alt3=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 2, input);

                    throw nvae;

                }
                }
                break;
            case LEFT_PAREN:
                {
                alt3=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // GraphQueryParser.g:56:4: ( contextParams )? baseSelectStatement STATEMENT_TERMINATOR !
                    {
                    root_0 = (Object)adaptor.nil();


                    // GraphQueryParser.g:56:4: ( contextParams )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==LEFT_BRACE) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // GraphQueryParser.g:56:4: contextParams
                            {
                            pushFollow(FOLLOW_contextParams_in_selectStatement214);
                            contextParams3=contextParams();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, contextParams3.getTree());

                            }
                            break;

                    }


                    pushFollow(FOLLOW_baseSelectStatement_in_selectStatement217);
                    baseSelectStatement4=baseSelectStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, baseSelectStatement4.getTree());

                    STATEMENT_TERMINATOR5=(Token)match(input,STATEMENT_TERMINATOR,FOLLOW_STATEMENT_TERMINATOR_in_selectStatement219); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:57:4: ( contextParams )? joinStatement STATEMENT_TERMINATOR !
                    {
                    root_0 = (Object)adaptor.nil();


                    // GraphQueryParser.g:57:4: ( contextParams )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==LEFT_BRACE) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // GraphQueryParser.g:57:4: contextParams
                            {
                            pushFollow(FOLLOW_contextParams_in_selectStatement225);
                            contextParams6=contextParams();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, contextParams6.getTree());

                            }
                            break;

                    }


                    pushFollow(FOLLOW_joinStatement_in_selectStatement228);
                    joinStatement7=joinStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, joinStatement7.getTree());

                    STATEMENT_TERMINATOR8=(Token)match(input,STATEMENT_TERMINATOR,FOLLOW_STATEMENT_TERMINATOR_in_selectStatement230); if (state.failed) return retval;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, selectStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "selectStatement"


    public static class contextParams_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "contextParams"
    // GraphQueryParser.g:60:1: contextParams : LEFT_BRACE contextProperty ( COMMA contextProperty )* RIGHT_BRACE -> ^( CONTEXT_PARAMS ( contextProperty )* ) ;
    public final GraphQueryParser.contextParams_return contextParams() throws RecognitionException {
        GraphQueryParser.contextParams_return retval = new GraphQueryParser.contextParams_return();
        retval.start = input.LT(1);

        int contextParams_StartIndex = input.index();

        Object root_0 = null;

        Token LEFT_BRACE9=null;
        Token COMMA11=null;
        Token RIGHT_BRACE13=null;
        GraphQueryParser.contextProperty_return contextProperty10 =null;

        GraphQueryParser.contextProperty_return contextProperty12 =null;


        Object LEFT_BRACE9_tree=null;
        Object COMMA11_tree=null;
        Object RIGHT_BRACE13_tree=null;
        RewriteRuleTokenStream stream_RIGHT_BRACE=new RewriteRuleTokenStream(adaptor,"token RIGHT_BRACE");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LEFT_BRACE=new RewriteRuleTokenStream(adaptor,"token LEFT_BRACE");
        RewriteRuleSubtreeStream stream_contextProperty=new RewriteRuleSubtreeStream(adaptor,"rule contextProperty");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // GraphQueryParser.g:61:2: ( LEFT_BRACE contextProperty ( COMMA contextProperty )* RIGHT_BRACE -> ^( CONTEXT_PARAMS ( contextProperty )* ) )
            // GraphQueryParser.g:61:4: LEFT_BRACE contextProperty ( COMMA contextProperty )* RIGHT_BRACE
            {
            LEFT_BRACE9=(Token)match(input,LEFT_BRACE,FOLLOW_LEFT_BRACE_in_contextParams242); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_BRACE.add(LEFT_BRACE9);


            pushFollow(FOLLOW_contextProperty_in_contextParams244);
            contextProperty10=contextProperty();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_contextProperty.add(contextProperty10.getTree());

            // GraphQueryParser.g:61:31: ( COMMA contextProperty )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==COMMA) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // GraphQueryParser.g:61:32: COMMA contextProperty
            	    {
            	    COMMA11=(Token)match(input,COMMA,FOLLOW_COMMA_in_contextParams247); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA11);


            	    pushFollow(FOLLOW_contextProperty_in_contextParams249);
            	    contextProperty12=contextProperty();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_contextProperty.add(contextProperty12.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            RIGHT_BRACE13=(Token)match(input,RIGHT_BRACE,FOLLOW_RIGHT_BRACE_in_contextParams253); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_BRACE.add(RIGHT_BRACE13);


            // AST REWRITE
            // elements: contextProperty
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 62:2: -> ^( CONTEXT_PARAMS ( contextProperty )* )
            {
                // GraphQueryParser.g:62:5: ^( CONTEXT_PARAMS ( contextProperty )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CONTEXT_PARAMS, "CONTEXT_PARAMS")
                , root_1);

                // GraphQueryParser.g:62:22: ( contextProperty )*
                while ( stream_contextProperty.hasNext() ) {
                    adaptor.addChild(root_1, stream_contextProperty.nextTree());

                }
                stream_contextProperty.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, contextParams_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "contextParams"


    public static class contextProperty_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "contextProperty"
    // GraphQueryParser.g:65:1: contextProperty : n= NAME ASSIGN val= STRING_LITERAL -> ^( CONTEXT_PROP $n $val) ;
    public final GraphQueryParser.contextProperty_return contextProperty() throws RecognitionException {
        GraphQueryParser.contextProperty_return retval = new GraphQueryParser.contextProperty_return();
        retval.start = input.LT(1);

        int contextProperty_StartIndex = input.index();

        Object root_0 = null;

        Token n=null;
        Token val=null;
        Token ASSIGN14=null;

        Object n_tree=null;
        Object val_tree=null;
        Object ASSIGN14_tree=null;
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // GraphQueryParser.g:66:2: (n= NAME ASSIGN val= STRING_LITERAL -> ^( CONTEXT_PROP $n $val) )
            // GraphQueryParser.g:66:4: n= NAME ASSIGN val= STRING_LITERAL
            {
            n=(Token)match(input,NAME,FOLLOW_NAME_in_contextProperty276); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(n);


            ASSIGN14=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_contextProperty278); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN14);


            val=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_contextProperty282); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(val);


            // AST REWRITE
            // elements: val, n
            // token labels: val, n
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleTokenStream stream_val=new RewriteRuleTokenStream(adaptor,"token val",val);
            RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 67:2: -> ^( CONTEXT_PROP $n $val)
            {
                // GraphQueryParser.g:67:5: ^( CONTEXT_PROP $n $val)
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CONTEXT_PROP, "CONTEXT_PROP")
                , root_1);

                adaptor.addChild(root_1, stream_n.nextNode());

                adaptor.addChild(root_1, stream_val.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, contextProperty_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "contextProperty"


    public static class baseSelectStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "baseSelectStatement"
    // GraphQueryParser.g:71:1: baseSelectStatement : clauseSelect ( ( clauseFrom clauseTabReader ) | ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN ) | ( S_FROM LEFT_PAREN joinStatement RIGHT_PAREN ) ) ( clauseWhere )? ( clauseMergeBy | clauseSplitBy | clauseGroupBy )? ( clauseHaving )? ( clauseOrderBy )? -> ^( BASE_SELECT ( baseSelectStatement )? ( joinStatement )? ( clauseTabReader )? ( clauseFrom )? ( clauseWhere )? ( clauseGroupBy )? ( clauseMergeBy )? ( clauseSplitBy )? ( clauseHaving )? ( clauseOrderBy )? ( clauseSelect )? ) ;
    public final GraphQueryParser.baseSelectStatement_return baseSelectStatement() throws RecognitionException {
        GraphQueryParser.baseSelectStatement_return retval = new GraphQueryParser.baseSelectStatement_return();
        retval.start = input.LT(1);

        int baseSelectStatement_StartIndex = input.index();

        Object root_0 = null;

        Token S_FROM18=null;
        Token LEFT_PAREN19=null;
        Token RIGHT_PAREN21=null;
        Token S_FROM22=null;
        Token LEFT_PAREN23=null;
        Token RIGHT_PAREN25=null;
        GraphQueryParser.clauseSelect_return clauseSelect15 =null;

        GraphQueryParser.clauseFrom_return clauseFrom16 =null;

        GraphQueryParser.clauseTabReader_return clauseTabReader17 =null;

        GraphQueryParser.baseSelectStatement_return baseSelectStatement20 =null;

        GraphQueryParser.joinStatement_return joinStatement24 =null;

        GraphQueryParser.clauseWhere_return clauseWhere26 =null;

        GraphQueryParser.clauseMergeBy_return clauseMergeBy27 =null;

        GraphQueryParser.clauseSplitBy_return clauseSplitBy28 =null;

        GraphQueryParser.clauseGroupBy_return clauseGroupBy29 =null;

        GraphQueryParser.clauseHaving_return clauseHaving30 =null;

        GraphQueryParser.clauseOrderBy_return clauseOrderBy31 =null;


        Object S_FROM18_tree=null;
        Object LEFT_PAREN19_tree=null;
        Object RIGHT_PAREN21_tree=null;
        Object S_FROM22_tree=null;
        Object LEFT_PAREN23_tree=null;
        Object RIGHT_PAREN25_tree=null;
        RewriteRuleTokenStream stream_S_FROM=new RewriteRuleTokenStream(adaptor,"token S_FROM");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_clauseSelect=new RewriteRuleSubtreeStream(adaptor,"rule clauseSelect");
        RewriteRuleSubtreeStream stream_clauseWhere=new RewriteRuleSubtreeStream(adaptor,"rule clauseWhere");
        RewriteRuleSubtreeStream stream_clauseMergeBy=new RewriteRuleSubtreeStream(adaptor,"rule clauseMergeBy");
        RewriteRuleSubtreeStream stream_clauseTabReader=new RewriteRuleSubtreeStream(adaptor,"rule clauseTabReader");
        RewriteRuleSubtreeStream stream_baseSelectStatement=new RewriteRuleSubtreeStream(adaptor,"rule baseSelectStatement");
        RewriteRuleSubtreeStream stream_clauseHaving=new RewriteRuleSubtreeStream(adaptor,"rule clauseHaving");
        RewriteRuleSubtreeStream stream_joinStatement=new RewriteRuleSubtreeStream(adaptor,"rule joinStatement");
        RewriteRuleSubtreeStream stream_clauseSplitBy=new RewriteRuleSubtreeStream(adaptor,"rule clauseSplitBy");
        RewriteRuleSubtreeStream stream_clauseOrderBy=new RewriteRuleSubtreeStream(adaptor,"rule clauseOrderBy");
        RewriteRuleSubtreeStream stream_clauseFrom=new RewriteRuleSubtreeStream(adaptor,"rule clauseFrom");
        RewriteRuleSubtreeStream stream_clauseGroupBy=new RewriteRuleSubtreeStream(adaptor,"rule clauseGroupBy");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // GraphQueryParser.g:72:2: ( clauseSelect ( ( clauseFrom clauseTabReader ) | ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN ) | ( S_FROM LEFT_PAREN joinStatement RIGHT_PAREN ) ) ( clauseWhere )? ( clauseMergeBy | clauseSplitBy | clauseGroupBy )? ( clauseHaving )? ( clauseOrderBy )? -> ^( BASE_SELECT ( baseSelectStatement )? ( joinStatement )? ( clauseTabReader )? ( clauseFrom )? ( clauseWhere )? ( clauseGroupBy )? ( clauseMergeBy )? ( clauseSplitBy )? ( clauseHaving )? ( clauseOrderBy )? ( clauseSelect )? ) )
            // GraphQueryParser.g:72:4: clauseSelect ( ( clauseFrom clauseTabReader ) | ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN ) | ( S_FROM LEFT_PAREN joinStatement RIGHT_PAREN ) ) ( clauseWhere )? ( clauseMergeBy | clauseSplitBy | clauseGroupBy )? ( clauseHaving )? ( clauseOrderBy )?
            {
            pushFollow(FOLLOW_clauseSelect_in_baseSelectStatement308);
            clauseSelect15=clauseSelect();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_clauseSelect.add(clauseSelect15.getTree());

            // GraphQueryParser.g:72:17: ( ( clauseFrom clauseTabReader ) | ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN ) | ( S_FROM LEFT_PAREN joinStatement RIGHT_PAREN ) )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==S_FROM) ) {
                int LA5_1 = input.LA(2);

                if ( (synpred5_GraphQueryParser()) ) {
                    alt5=1;
                }
                else if ( (synpred6_GraphQueryParser()) ) {
                    alt5=2;
                }
                else if ( (true) ) {
                    alt5=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // GraphQueryParser.g:72:19: ( clauseFrom clauseTabReader )
                    {
                    // GraphQueryParser.g:72:19: ( clauseFrom clauseTabReader )
                    // GraphQueryParser.g:72:20: clauseFrom clauseTabReader
                    {
                    pushFollow(FOLLOW_clauseFrom_in_baseSelectStatement313);
                    clauseFrom16=clauseFrom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseFrom.add(clauseFrom16.getTree());

                    pushFollow(FOLLOW_clauseTabReader_in_baseSelectStatement315);
                    clauseTabReader17=clauseTabReader();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseTabReader.add(clauseTabReader17.getTree());

                    }


                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:72:50: ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN )
                    {
                    // GraphQueryParser.g:72:50: ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN )
                    // GraphQueryParser.g:72:51: S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN
                    {
                    S_FROM18=(Token)match(input,S_FROM,FOLLOW_S_FROM_in_baseSelectStatement321); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_S_FROM.add(S_FROM18);


                    LEFT_PAREN19=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_baseSelectStatement323); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN19);


                    pushFollow(FOLLOW_baseSelectStatement_in_baseSelectStatement325);
                    baseSelectStatement20=baseSelectStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_baseSelectStatement.add(baseSelectStatement20.getTree());

                    RIGHT_PAREN21=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_baseSelectStatement327); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN21);


                    }


                    }
                    break;
                case 3 :
                    // GraphQueryParser.g:72:104: ( S_FROM LEFT_PAREN joinStatement RIGHT_PAREN )
                    {
                    // GraphQueryParser.g:72:104: ( S_FROM LEFT_PAREN joinStatement RIGHT_PAREN )
                    // GraphQueryParser.g:72:105: S_FROM LEFT_PAREN joinStatement RIGHT_PAREN
                    {
                    S_FROM22=(Token)match(input,S_FROM,FOLLOW_S_FROM_in_baseSelectStatement333); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_S_FROM.add(S_FROM22);


                    LEFT_PAREN23=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_baseSelectStatement335); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN23);


                    pushFollow(FOLLOW_joinStatement_in_baseSelectStatement337);
                    joinStatement24=joinStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_joinStatement.add(joinStatement24.getTree());

                    RIGHT_PAREN25=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_baseSelectStatement339); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN25);


                    }


                    }
                    break;

            }


            // GraphQueryParser.g:72:152: ( clauseWhere )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==S_WHERE) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // GraphQueryParser.g:72:152: clauseWhere
                    {
                    pushFollow(FOLLOW_clauseWhere_in_baseSelectStatement344);
                    clauseWhere26=clauseWhere();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseWhere.add(clauseWhere26.getTree());

                    }
                    break;

            }


            // GraphQueryParser.g:72:165: ( clauseMergeBy | clauseSplitBy | clauseGroupBy )?
            int alt7=4;
            switch ( input.LA(1) ) {
                case S_MERGE_BY:
                    {
                    alt7=1;
                    }
                    break;
                case S_SPLIT_BY:
                    {
                    alt7=2;
                    }
                    break;
                case S_GROUP_BY:
                    {
                    alt7=3;
                    }
                    break;
            }

            switch (alt7) {
                case 1 :
                    // GraphQueryParser.g:72:166: clauseMergeBy
                    {
                    pushFollow(FOLLOW_clauseMergeBy_in_baseSelectStatement348);
                    clauseMergeBy27=clauseMergeBy();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseMergeBy.add(clauseMergeBy27.getTree());

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:72:182: clauseSplitBy
                    {
                    pushFollow(FOLLOW_clauseSplitBy_in_baseSelectStatement352);
                    clauseSplitBy28=clauseSplitBy();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseSplitBy.add(clauseSplitBy28.getTree());

                    }
                    break;
                case 3 :
                    // GraphQueryParser.g:72:198: clauseGroupBy
                    {
                    pushFollow(FOLLOW_clauseGroupBy_in_baseSelectStatement356);
                    clauseGroupBy29=clauseGroupBy();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseGroupBy.add(clauseGroupBy29.getTree());

                    }
                    break;

            }


            // GraphQueryParser.g:72:214: ( clauseHaving )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==S_HAVING) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // GraphQueryParser.g:72:214: clauseHaving
                    {
                    pushFollow(FOLLOW_clauseHaving_in_baseSelectStatement360);
                    clauseHaving30=clauseHaving();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseHaving.add(clauseHaving30.getTree());

                    }
                    break;

            }


            // GraphQueryParser.g:72:228: ( clauseOrderBy )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==S_ORDER_BY) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // GraphQueryParser.g:72:228: clauseOrderBy
                    {
                    pushFollow(FOLLOW_clauseOrderBy_in_baseSelectStatement363);
                    clauseOrderBy31=clauseOrderBy();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_clauseOrderBy.add(clauseOrderBy31.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: clauseOrderBy, clauseSelect, clauseTabReader, joinStatement, clauseWhere, clauseHaving, clauseSplitBy, clauseMergeBy, clauseGroupBy, clauseFrom, baseSelectStatement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 73:2: -> ^( BASE_SELECT ( baseSelectStatement )? ( joinStatement )? ( clauseTabReader )? ( clauseFrom )? ( clauseWhere )? ( clauseGroupBy )? ( clauseMergeBy )? ( clauseSplitBy )? ( clauseHaving )? ( clauseOrderBy )? ( clauseSelect )? )
            {
                // GraphQueryParser.g:73:5: ^( BASE_SELECT ( baseSelectStatement )? ( joinStatement )? ( clauseTabReader )? ( clauseFrom )? ( clauseWhere )? ( clauseGroupBy )? ( clauseMergeBy )? ( clauseSplitBy )? ( clauseHaving )? ( clauseOrderBy )? ( clauseSelect )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(BASE_SELECT, "BASE_SELECT")
                , root_1);

                // GraphQueryParser.g:73:19: ( baseSelectStatement )?
                if ( stream_baseSelectStatement.hasNext() ) {
                    adaptor.addChild(root_1, stream_baseSelectStatement.nextTree());

                }
                stream_baseSelectStatement.reset();

                // GraphQueryParser.g:73:40: ( joinStatement )?
                if ( stream_joinStatement.hasNext() ) {
                    adaptor.addChild(root_1, stream_joinStatement.nextTree());

                }
                stream_joinStatement.reset();

                // GraphQueryParser.g:73:55: ( clauseTabReader )?
                if ( stream_clauseTabReader.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseTabReader.nextTree());

                }
                stream_clauseTabReader.reset();

                // GraphQueryParser.g:73:72: ( clauseFrom )?
                if ( stream_clauseFrom.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseFrom.nextTree());

                }
                stream_clauseFrom.reset();

                // GraphQueryParser.g:73:84: ( clauseWhere )?
                if ( stream_clauseWhere.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseWhere.nextTree());

                }
                stream_clauseWhere.reset();

                // GraphQueryParser.g:73:97: ( clauseGroupBy )?
                if ( stream_clauseGroupBy.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseGroupBy.nextTree());

                }
                stream_clauseGroupBy.reset();

                // GraphQueryParser.g:73:112: ( clauseMergeBy )?
                if ( stream_clauseMergeBy.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseMergeBy.nextTree());

                }
                stream_clauseMergeBy.reset();

                // GraphQueryParser.g:73:127: ( clauseSplitBy )?
                if ( stream_clauseSplitBy.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseSplitBy.nextTree());

                }
                stream_clauseSplitBy.reset();

                // GraphQueryParser.g:73:142: ( clauseHaving )?
                if ( stream_clauseHaving.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseHaving.nextTree());

                }
                stream_clauseHaving.reset();

                // GraphQueryParser.g:73:156: ( clauseOrderBy )?
                if ( stream_clauseOrderBy.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseOrderBy.nextTree());

                }
                stream_clauseOrderBy.reset();

                // GraphQueryParser.g:73:171: ( clauseSelect )?
                if ( stream_clauseSelect.hasNext() ) {
                    adaptor.addChild(root_1, stream_clauseSelect.nextTree());

                }
                stream_clauseSelect.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, baseSelectStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "baseSelectStatement"


    public static class joinStatement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "joinStatement"
    // GraphQueryParser.g:76:1: joinStatement : (b1= baseSelectStatement | ( LEFT_PAREN j1= joinStatement RIGHT_PAREN ) ) S_JOIN (b2= baseSelectStatement | ( LEFT_PAREN j2= joinStatement RIGHT_PAREN ) ) ( ON f1= function IS_EQUAL f2= function )? -> ^( JOIN_SELECT ( $b1)? ( $j1)? ( $b2)? ( $j2)? ( $f1)? ( $f2)? ) ;
    public final GraphQueryParser.joinStatement_return joinStatement() throws RecognitionException {
        GraphQueryParser.joinStatement_return retval = new GraphQueryParser.joinStatement_return();
        retval.start = input.LT(1);

        int joinStatement_StartIndex = input.index();

        Object root_0 = null;

        Token LEFT_PAREN32=null;
        Token RIGHT_PAREN33=null;
        Token S_JOIN34=null;
        Token LEFT_PAREN35=null;
        Token RIGHT_PAREN36=null;
        Token ON37=null;
        Token IS_EQUAL38=null;
        GraphQueryParser.baseSelectStatement_return b1 =null;

        GraphQueryParser.joinStatement_return j1 =null;

        GraphQueryParser.baseSelectStatement_return b2 =null;

        GraphQueryParser.joinStatement_return j2 =null;

        GraphQueryParser.function_return f1 =null;

        GraphQueryParser.function_return f2 =null;


        Object LEFT_PAREN32_tree=null;
        Object RIGHT_PAREN33_tree=null;
        Object S_JOIN34_tree=null;
        Object LEFT_PAREN35_tree=null;
        Object RIGHT_PAREN36_tree=null;
        Object ON37_tree=null;
        Object IS_EQUAL38_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_ON=new RewriteRuleTokenStream(adaptor,"token ON");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_IS_EQUAL=new RewriteRuleTokenStream(adaptor,"token IS_EQUAL");
        RewriteRuleTokenStream stream_S_JOIN=new RewriteRuleTokenStream(adaptor,"token S_JOIN");
        RewriteRuleSubtreeStream stream_baseSelectStatement=new RewriteRuleSubtreeStream(adaptor,"rule baseSelectStatement");
        RewriteRuleSubtreeStream stream_joinStatement=new RewriteRuleSubtreeStream(adaptor,"rule joinStatement");
        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // GraphQueryParser.g:77:2: ( (b1= baseSelectStatement | ( LEFT_PAREN j1= joinStatement RIGHT_PAREN ) ) S_JOIN (b2= baseSelectStatement | ( LEFT_PAREN j2= joinStatement RIGHT_PAREN ) ) ( ON f1= function IS_EQUAL f2= function )? -> ^( JOIN_SELECT ( $b1)? ( $j1)? ( $b2)? ( $j2)? ( $f1)? ( $f2)? ) )
            // GraphQueryParser.g:77:4: (b1= baseSelectStatement | ( LEFT_PAREN j1= joinStatement RIGHT_PAREN ) ) S_JOIN (b2= baseSelectStatement | ( LEFT_PAREN j2= joinStatement RIGHT_PAREN ) ) ( ON f1= function IS_EQUAL f2= function )?
            {
            // GraphQueryParser.g:77:4: (b1= baseSelectStatement | ( LEFT_PAREN j1= joinStatement RIGHT_PAREN ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==S_SELECT) ) {
                alt10=1;
            }
            else if ( (LA10_0==LEFT_PAREN) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // GraphQueryParser.g:77:6: b1= baseSelectStatement
                    {
                    pushFollow(FOLLOW_baseSelectStatement_in_joinStatement423);
                    b1=baseSelectStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_baseSelectStatement.add(b1.getTree());

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:77:33: ( LEFT_PAREN j1= joinStatement RIGHT_PAREN )
                    {
                    // GraphQueryParser.g:77:33: ( LEFT_PAREN j1= joinStatement RIGHT_PAREN )
                    // GraphQueryParser.g:77:34: LEFT_PAREN j1= joinStatement RIGHT_PAREN
                    {
                    LEFT_PAREN32=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_joinStatement428); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN32);


                    pushFollow(FOLLOW_joinStatement_in_joinStatement434);
                    j1=joinStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_joinStatement.add(j1.getTree());

                    RIGHT_PAREN33=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_joinStatement436); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN33);


                    }


                    }
                    break;

            }


            S_JOIN34=(Token)match(input,S_JOIN,FOLLOW_S_JOIN_in_joinStatement440); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_JOIN.add(S_JOIN34);


            // GraphQueryParser.g:77:85: (b2= baseSelectStatement | ( LEFT_PAREN j2= joinStatement RIGHT_PAREN ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==S_SELECT) ) {
                alt11=1;
            }
            else if ( (LA11_0==LEFT_PAREN) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // GraphQueryParser.g:77:86: b2= baseSelectStatement
                    {
                    pushFollow(FOLLOW_baseSelectStatement_in_joinStatement445);
                    b2=baseSelectStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_baseSelectStatement.add(b2.getTree());

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:77:111: ( LEFT_PAREN j2= joinStatement RIGHT_PAREN )
                    {
                    // GraphQueryParser.g:77:111: ( LEFT_PAREN j2= joinStatement RIGHT_PAREN )
                    // GraphQueryParser.g:77:112: LEFT_PAREN j2= joinStatement RIGHT_PAREN
                    {
                    LEFT_PAREN35=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_joinStatement450); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN35);


                    pushFollow(FOLLOW_joinStatement_in_joinStatement454);
                    j2=joinStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_joinStatement.add(j2.getTree());

                    RIGHT_PAREN36=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_joinStatement456); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN36);


                    }


                    }
                    break;

            }


            // GraphQueryParser.g:77:154: ( ON f1= function IS_EQUAL f2= function )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ON) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // GraphQueryParser.g:77:155: ON f1= function IS_EQUAL f2= function
                    {
                    ON37=(Token)match(input,ON,FOLLOW_ON_in_joinStatement461); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ON.add(ON37);


                    pushFollow(FOLLOW_function_in_joinStatement465);
                    f1=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_function.add(f1.getTree());

                    IS_EQUAL38=(Token)match(input,IS_EQUAL,FOLLOW_IS_EQUAL_in_joinStatement467); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IS_EQUAL.add(IS_EQUAL38);


                    pushFollow(FOLLOW_function_in_joinStatement471);
                    f2=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_function.add(f2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: b2, f1, f2, j2, j1, b1
            // token labels: 
            // rule labels: retval, j2, b1, b2, f1, j1, f2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_j2=new RewriteRuleSubtreeStream(adaptor,"rule j2",j2!=null?j2.tree:null);
            RewriteRuleSubtreeStream stream_b1=new RewriteRuleSubtreeStream(adaptor,"rule b1",b1!=null?b1.tree:null);
            RewriteRuleSubtreeStream stream_b2=new RewriteRuleSubtreeStream(adaptor,"rule b2",b2!=null?b2.tree:null);
            RewriteRuleSubtreeStream stream_f1=new RewriteRuleSubtreeStream(adaptor,"rule f1",f1!=null?f1.tree:null);
            RewriteRuleSubtreeStream stream_j1=new RewriteRuleSubtreeStream(adaptor,"rule j1",j1!=null?j1.tree:null);
            RewriteRuleSubtreeStream stream_f2=new RewriteRuleSubtreeStream(adaptor,"rule f2",f2!=null?f2.tree:null);

            root_0 = (Object)adaptor.nil();
            // 78:2: -> ^( JOIN_SELECT ( $b1)? ( $j1)? ( $b2)? ( $j2)? ( $f1)? ( $f2)? )
            {
                // GraphQueryParser.g:78:5: ^( JOIN_SELECT ( $b1)? ( $j1)? ( $b2)? ( $j2)? ( $f1)? ( $f2)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(JOIN_SELECT, "JOIN_SELECT")
                , root_1);

                // GraphQueryParser.g:78:20: ( $b1)?
                if ( stream_b1.hasNext() ) {
                    adaptor.addChild(root_1, stream_b1.nextTree());

                }
                stream_b1.reset();

                // GraphQueryParser.g:78:25: ( $j1)?
                if ( stream_j1.hasNext() ) {
                    adaptor.addChild(root_1, stream_j1.nextTree());

                }
                stream_j1.reset();

                // GraphQueryParser.g:78:30: ( $b2)?
                if ( stream_b2.hasNext() ) {
                    adaptor.addChild(root_1, stream_b2.nextTree());

                }
                stream_b2.reset();

                // GraphQueryParser.g:78:35: ( $j2)?
                if ( stream_j2.hasNext() ) {
                    adaptor.addChild(root_1, stream_j2.nextTree());

                }
                stream_j2.reset();

                // GraphQueryParser.g:78:40: ( $f1)?
                if ( stream_f1.hasNext() ) {
                    adaptor.addChild(root_1, stream_f1.nextTree());

                }
                stream_f1.reset();

                // GraphQueryParser.g:78:45: ( $f2)?
                if ( stream_f2.hasNext() ) {
                    adaptor.addChild(root_1, stream_f2.nextTree());

                }
                stream_f2.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, joinStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "joinStatement"


    public static class clauseSelect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseSelect"
    // GraphQueryParser.g:81:1: clauseSelect : S_SELECT ( STAR | ( selectExpr ( COMMA selectExpr )* ) ) -> ^( CLAUSE ^( C_SELECT ^( CLAUSE_ARGUMENTS ( STAR )? ( selectExpr )* ) ) ) ;
    public final GraphQueryParser.clauseSelect_return clauseSelect() throws RecognitionException {
        GraphQueryParser.clauseSelect_return retval = new GraphQueryParser.clauseSelect_return();
        retval.start = input.LT(1);

        int clauseSelect_StartIndex = input.index();

        Object root_0 = null;

        Token S_SELECT39=null;
        Token STAR40=null;
        Token COMMA42=null;
        GraphQueryParser.selectExpr_return selectExpr41 =null;

        GraphQueryParser.selectExpr_return selectExpr43 =null;


        Object S_SELECT39_tree=null;
        Object STAR40_tree=null;
        Object COMMA42_tree=null;
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_S_SELECT=new RewriteRuleTokenStream(adaptor,"token S_SELECT");
        RewriteRuleSubtreeStream stream_selectExpr=new RewriteRuleSubtreeStream(adaptor,"rule selectExpr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // GraphQueryParser.g:82:2: ( S_SELECT ( STAR | ( selectExpr ( COMMA selectExpr )* ) ) -> ^( CLAUSE ^( C_SELECT ^( CLAUSE_ARGUMENTS ( STAR )? ( selectExpr )* ) ) ) )
            // GraphQueryParser.g:82:4: S_SELECT ( STAR | ( selectExpr ( COMMA selectExpr )* ) )
            {
            S_SELECT39=(Token)match(input,S_SELECT,FOLLOW_S_SELECT_in_clauseSelect515); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_SELECT.add(S_SELECT39);


            // GraphQueryParser.g:82:13: ( STAR | ( selectExpr ( COMMA selectExpr )* ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==STAR) ) {
                alt14=1;
            }
            else if ( (LA14_0==NAME) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // GraphQueryParser.g:82:14: STAR
                    {
                    STAR40=(Token)match(input,STAR,FOLLOW_STAR_in_clauseSelect518); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STAR.add(STAR40);


                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:82:21: ( selectExpr ( COMMA selectExpr )* )
                    {
                    // GraphQueryParser.g:82:21: ( selectExpr ( COMMA selectExpr )* )
                    // GraphQueryParser.g:82:22: selectExpr ( COMMA selectExpr )*
                    {
                    pushFollow(FOLLOW_selectExpr_in_clauseSelect523);
                    selectExpr41=selectExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_selectExpr.add(selectExpr41.getTree());

                    // GraphQueryParser.g:82:33: ( COMMA selectExpr )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==COMMA) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // GraphQueryParser.g:82:34: COMMA selectExpr
                    	    {
                    	    COMMA42=(Token)match(input,COMMA,FOLLOW_COMMA_in_clauseSelect526); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA42);


                    	    pushFollow(FOLLOW_selectExpr_in_clauseSelect528);
                    	    selectExpr43=selectExpr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_selectExpr.add(selectExpr43.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }


                    }
                    break;

            }


            // AST REWRITE
            // elements: selectExpr, STAR
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 83:2: -> ^( CLAUSE ^( C_SELECT ^( CLAUSE_ARGUMENTS ( STAR )? ( selectExpr )* ) ) )
            {
                // GraphQueryParser.g:83:5: ^( CLAUSE ^( C_SELECT ^( CLAUSE_ARGUMENTS ( STAR )? ( selectExpr )* ) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:83:14: ^( C_SELECT ^( CLAUSE_ARGUMENTS ( STAR )? ( selectExpr )* ) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_SELECT, "C_SELECT")
                , root_2);

                // GraphQueryParser.g:83:25: ^( CLAUSE_ARGUMENTS ( STAR )? ( selectExpr )* )
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                // GraphQueryParser.g:83:44: ( STAR )?
                if ( stream_STAR.hasNext() ) {
                    adaptor.addChild(root_3, 
                    stream_STAR.nextNode()
                    );

                }
                stream_STAR.reset();

                // GraphQueryParser.g:83:50: ( selectExpr )*
                while ( stream_selectExpr.hasNext() ) {
                    adaptor.addChild(root_3, stream_selectExpr.nextTree());

                }
                stream_selectExpr.reset();

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, clauseSelect_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseSelect"


    public static class selectExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selectExpr"
    // GraphQueryParser.g:86:1: selectExpr : ( NAME | ( function AS ! NAME ) );
    public final GraphQueryParser.selectExpr_return selectExpr() throws RecognitionException {
        GraphQueryParser.selectExpr_return retval = new GraphQueryParser.selectExpr_return();
        retval.start = input.LT(1);

        int selectExpr_StartIndex = input.index();

        Object root_0 = null;

        Token NAME44=null;
        Token AS46=null;
        Token NAME47=null;
        GraphQueryParser.function_return function45 =null;


        Object NAME44_tree=null;
        Object AS46_tree=null;
        Object NAME47_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // GraphQueryParser.g:87:2: ( NAME | ( function AS ! NAME ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==NAME) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==LEFT_PAREN) ) {
                    alt15=2;
                }
                else if ( (LA15_1==EOF||LA15_1==COMMA||LA15_1==S_FROM) ) {
                    alt15=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // GraphQueryParser.g:87:4: NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    NAME44=(Token)match(input,NAME,FOLLOW_NAME_in_selectExpr566); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME44_tree = 
                    (Object)adaptor.create(NAME44)
                    ;
                    adaptor.addChild(root_0, NAME44_tree);
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:88:5: ( function AS ! NAME )
                    {
                    root_0 = (Object)adaptor.nil();


                    // GraphQueryParser.g:88:5: ( function AS ! NAME )
                    // GraphQueryParser.g:88:6: function AS ! NAME
                    {
                    pushFollow(FOLLOW_function_in_selectExpr573);
                    function45=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function45.getTree());

                    AS46=(Token)match(input,AS,FOLLOW_AS_in_selectExpr575); if (state.failed) return retval;

                    NAME47=(Token)match(input,NAME,FOLLOW_NAME_in_selectExpr578); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME47_tree = 
                    (Object)adaptor.create(NAME47)
                    ;
                    adaptor.addChild(root_0, NAME47_tree);
                    }

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, selectExpr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "selectExpr"


    public static class clauseOrderBy_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseOrderBy"
    // GraphQueryParser.g:92:1: clauseOrderBy : S_ORDER_BY orderByExpr ( COMMA orderByExpr )* -> ^( CLAUSE ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS orderByExpr ( orderByExpr )* ) ) ) ;
    public final GraphQueryParser.clauseOrderBy_return clauseOrderBy() throws RecognitionException {
        GraphQueryParser.clauseOrderBy_return retval = new GraphQueryParser.clauseOrderBy_return();
        retval.start = input.LT(1);

        int clauseOrderBy_StartIndex = input.index();

        Object root_0 = null;

        Token S_ORDER_BY48=null;
        Token COMMA50=null;
        GraphQueryParser.orderByExpr_return orderByExpr49 =null;

        GraphQueryParser.orderByExpr_return orderByExpr51 =null;


        Object S_ORDER_BY48_tree=null;
        Object COMMA50_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_S_ORDER_BY=new RewriteRuleTokenStream(adaptor,"token S_ORDER_BY");
        RewriteRuleSubtreeStream stream_orderByExpr=new RewriteRuleSubtreeStream(adaptor,"rule orderByExpr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // GraphQueryParser.g:93:2: ( S_ORDER_BY orderByExpr ( COMMA orderByExpr )* -> ^( CLAUSE ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS orderByExpr ( orderByExpr )* ) ) ) )
            // GraphQueryParser.g:93:4: S_ORDER_BY orderByExpr ( COMMA orderByExpr )*
            {
            S_ORDER_BY48=(Token)match(input,S_ORDER_BY,FOLLOW_S_ORDER_BY_in_clauseOrderBy591); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_ORDER_BY.add(S_ORDER_BY48);


            pushFollow(FOLLOW_orderByExpr_in_clauseOrderBy593);
            orderByExpr49=orderByExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_orderByExpr.add(orderByExpr49.getTree());

            // GraphQueryParser.g:93:27: ( COMMA orderByExpr )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // GraphQueryParser.g:93:28: COMMA orderByExpr
            	    {
            	    COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_clauseOrderBy596); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA50);


            	    pushFollow(FOLLOW_orderByExpr_in_clauseOrderBy598);
            	    orderByExpr51=orderByExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_orderByExpr.add(orderByExpr51.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            // AST REWRITE
            // elements: orderByExpr, orderByExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 94:3: -> ^( CLAUSE ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS orderByExpr ( orderByExpr )* ) ) )
            {
                // GraphQueryParser.g:94:6: ^( CLAUSE ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS orderByExpr ( orderByExpr )* ) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:94:15: ^( C_ORDER_BY ^( CLAUSE_ARGUMENTS orderByExpr ( orderByExpr )* ) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_ORDER_BY, "C_ORDER_BY")
                , root_2);

                // GraphQueryParser.g:94:28: ^( CLAUSE_ARGUMENTS orderByExpr ( orderByExpr )* )
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_orderByExpr.nextTree());

                // GraphQueryParser.g:94:59: ( orderByExpr )*
                while ( stream_orderByExpr.hasNext() ) {
                    adaptor.addChild(root_3, stream_orderByExpr.nextTree());

                }
                stream_orderByExpr.reset();

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, clauseOrderBy_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseOrderBy"


    public static class orderByExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "orderByExpr"
    // GraphQueryParser.g:97:1: orderByExpr : ( NAME | function );
    public final GraphQueryParser.orderByExpr_return orderByExpr() throws RecognitionException {
        GraphQueryParser.orderByExpr_return retval = new GraphQueryParser.orderByExpr_return();
        retval.start = input.LT(1);

        int orderByExpr_StartIndex = input.index();

        Object root_0 = null;

        Token NAME52=null;
        GraphQueryParser.function_return function53 =null;


        Object NAME52_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // GraphQueryParser.g:98:2: ( NAME | function )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==NAME) ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==LEFT_PAREN) ) {
                    alt17=2;
                }
                else if ( (LA17_1==EOF||LA17_1==COMMA||LA17_1==ON||LA17_1==RIGHT_PAREN||LA17_1==STATEMENT_TERMINATOR||LA17_1==S_JOIN) ) {
                    alt17=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // GraphQueryParser.g:98:4: NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    NAME52=(Token)match(input,NAME,FOLLOW_NAME_in_orderByExpr634); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME52_tree = 
                    (Object)adaptor.create(NAME52)
                    ;
                    adaptor.addChild(root_0, NAME52_tree);
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:99:5: function
                    {
                    root_0 = (Object)adaptor.nil();


                    pushFollow(FOLLOW_function_in_orderByExpr640);
                    function53=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function53.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, orderByExpr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "orderByExpr"


    public static class clauseHaving_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseHaving"
    // GraphQueryParser.g:102:1: clauseHaving : S_HAVING f= function -> ^( CLAUSE ^( C_HAVING ^( CLAUSE_ARGUMENTS $f) ) ) ;
    public final GraphQueryParser.clauseHaving_return clauseHaving() throws RecognitionException {
        GraphQueryParser.clauseHaving_return retval = new GraphQueryParser.clauseHaving_return();
        retval.start = input.LT(1);

        int clauseHaving_StartIndex = input.index();

        Object root_0 = null;

        Token S_HAVING54=null;
        GraphQueryParser.function_return f =null;


        Object S_HAVING54_tree=null;
        RewriteRuleTokenStream stream_S_HAVING=new RewriteRuleTokenStream(adaptor,"token S_HAVING");
        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // GraphQueryParser.g:103:2: ( S_HAVING f= function -> ^( CLAUSE ^( C_HAVING ^( CLAUSE_ARGUMENTS $f) ) ) )
            // GraphQueryParser.g:103:4: S_HAVING f= function
            {
            S_HAVING54=(Token)match(input,S_HAVING,FOLLOW_S_HAVING_in_clauseHaving651); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_HAVING.add(S_HAVING54);


            pushFollow(FOLLOW_function_in_clauseHaving655);
            f=function();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_function.add(f.getTree());

            // AST REWRITE
            // elements: f
            // token labels: 
            // rule labels: f, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"rule f",f!=null?f.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 104:3: -> ^( CLAUSE ^( C_HAVING ^( CLAUSE_ARGUMENTS $f) ) )
            {
                // GraphQueryParser.g:104:6: ^( CLAUSE ^( C_HAVING ^( CLAUSE_ARGUMENTS $f) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:104:15: ^( C_HAVING ^( CLAUSE_ARGUMENTS $f) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_HAVING, "C_HAVING")
                , root_2);

                // GraphQueryParser.g:104:26: ^( CLAUSE_ARGUMENTS $f)
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_f.nextTree());

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, clauseHaving_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseHaving"


    public static class clauseMergeBy_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseMergeBy"
    // GraphQueryParser.g:107:1: clauseMergeBy : S_MERGE_BY mergeByDistinct BY ( mergeByExpr ( COMMA mergeByExpr )* )? -> ^( CLAUSE ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS mergeByDistinct ( mergeByExpr )* ) ) ) ;
    public final GraphQueryParser.clauseMergeBy_return clauseMergeBy() throws RecognitionException {
        GraphQueryParser.clauseMergeBy_return retval = new GraphQueryParser.clauseMergeBy_return();
        retval.start = input.LT(1);

        int clauseMergeBy_StartIndex = input.index();

        Object root_0 = null;

        Token S_MERGE_BY55=null;
        Token BY57=null;
        Token COMMA59=null;
        GraphQueryParser.mergeByDistinct_return mergeByDistinct56 =null;

        GraphQueryParser.mergeByExpr_return mergeByExpr58 =null;

        GraphQueryParser.mergeByExpr_return mergeByExpr60 =null;


        Object S_MERGE_BY55_tree=null;
        Object BY57_tree=null;
        Object COMMA59_tree=null;
        RewriteRuleTokenStream stream_BY=new RewriteRuleTokenStream(adaptor,"token BY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_S_MERGE_BY=new RewriteRuleTokenStream(adaptor,"token S_MERGE_BY");
        RewriteRuleSubtreeStream stream_mergeByDistinct=new RewriteRuleSubtreeStream(adaptor,"rule mergeByDistinct");
        RewriteRuleSubtreeStream stream_mergeByExpr=new RewriteRuleSubtreeStream(adaptor,"rule mergeByExpr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // GraphQueryParser.g:108:2: ( S_MERGE_BY mergeByDistinct BY ( mergeByExpr ( COMMA mergeByExpr )* )? -> ^( CLAUSE ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS mergeByDistinct ( mergeByExpr )* ) ) ) )
            // GraphQueryParser.g:108:4: S_MERGE_BY mergeByDistinct BY ( mergeByExpr ( COMMA mergeByExpr )* )?
            {
            S_MERGE_BY55=(Token)match(input,S_MERGE_BY,FOLLOW_S_MERGE_BY_in_clauseMergeBy685); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_MERGE_BY.add(S_MERGE_BY55);


            pushFollow(FOLLOW_mergeByDistinct_in_clauseMergeBy687);
            mergeByDistinct56=mergeByDistinct();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_mergeByDistinct.add(mergeByDistinct56.getTree());

            BY57=(Token)match(input,BY,FOLLOW_BY_in_clauseMergeBy689); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_BY.add(BY57);


            // GraphQueryParser.g:108:34: ( mergeByExpr ( COMMA mergeByExpr )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NAME) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // GraphQueryParser.g:108:35: mergeByExpr ( COMMA mergeByExpr )*
                    {
                    pushFollow(FOLLOW_mergeByExpr_in_clauseMergeBy692);
                    mergeByExpr58=mergeByExpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_mergeByExpr.add(mergeByExpr58.getTree());

                    // GraphQueryParser.g:108:47: ( COMMA mergeByExpr )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==COMMA) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // GraphQueryParser.g:108:48: COMMA mergeByExpr
                    	    {
                    	    COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_clauseMergeBy695); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA59);


                    	    pushFollow(FOLLOW_mergeByExpr_in_clauseMergeBy697);
                    	    mergeByExpr60=mergeByExpr();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_mergeByExpr.add(mergeByExpr60.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }


            // AST REWRITE
            // elements: mergeByExpr, mergeByDistinct
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 109:3: -> ^( CLAUSE ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS mergeByDistinct ( mergeByExpr )* ) ) )
            {
                // GraphQueryParser.g:109:6: ^( CLAUSE ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS mergeByDistinct ( mergeByExpr )* ) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:109:15: ^( C_MERGE_BY ^( CLAUSE_ARGUMENTS mergeByDistinct ( mergeByExpr )* ) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_MERGE_BY, "C_MERGE_BY")
                , root_2);

                // GraphQueryParser.g:109:28: ^( CLAUSE_ARGUMENTS mergeByDistinct ( mergeByExpr )* )
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_mergeByDistinct.nextTree());

                // GraphQueryParser.g:109:63: ( mergeByExpr )*
                while ( stream_mergeByExpr.hasNext() ) {
                    adaptor.addChild(root_3, stream_mergeByExpr.nextTree());

                }
                stream_mergeByExpr.reset();

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, clauseMergeBy_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseMergeBy"


    public static class mergeByDistinct_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mergeByDistinct"
    // GraphQueryParser.g:112:1: mergeByDistinct : ( DISTINCT -> TRUE | -> FALSE );
    public final GraphQueryParser.mergeByDistinct_return mergeByDistinct() throws RecognitionException {
        GraphQueryParser.mergeByDistinct_return retval = new GraphQueryParser.mergeByDistinct_return();
        retval.start = input.LT(1);

        int mergeByDistinct_StartIndex = input.index();

        Object root_0 = null;

        Token DISTINCT61=null;

        Object DISTINCT61_tree=null;
        RewriteRuleTokenStream stream_DISTINCT=new RewriteRuleTokenStream(adaptor,"token DISTINCT");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // GraphQueryParser.g:113:2: ( DISTINCT -> TRUE | -> FALSE )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==DISTINCT) ) {
                alt20=1;
            }
            else if ( (LA20_0==BY) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // GraphQueryParser.g:113:4: DISTINCT
                    {
                    DISTINCT61=(Token)match(input,DISTINCT,FOLLOW_DISTINCT_in_mergeByDistinct736); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DISTINCT.add(DISTINCT61);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 113:13: -> TRUE
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(TRUE, "TRUE")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:114:5: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 114:5: -> FALSE
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(FALSE, "FALSE")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, mergeByDistinct_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "mergeByDistinct"


    public static class mergeByExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "mergeByExpr"
    // GraphQueryParser.g:117:1: mergeByExpr : ( NAME | ( function AS ! NAME ) );
    public final GraphQueryParser.mergeByExpr_return mergeByExpr() throws RecognitionException {
        GraphQueryParser.mergeByExpr_return retval = new GraphQueryParser.mergeByExpr_return();
        retval.start = input.LT(1);

        int mergeByExpr_StartIndex = input.index();

        Object root_0 = null;

        Token NAME62=null;
        Token AS64=null;
        Token NAME65=null;
        GraphQueryParser.function_return function63 =null;


        Object NAME62_tree=null;
        Object AS64_tree=null;
        Object NAME65_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // GraphQueryParser.g:118:2: ( NAME | ( function AS ! NAME ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==NAME) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==LEFT_PAREN) ) {
                    alt21=2;
                }
                else if ( (LA21_1==EOF||LA21_1==COMMA||LA21_1==ON||LA21_1==RIGHT_PAREN||LA21_1==STATEMENT_TERMINATOR||(LA21_1 >= S_HAVING && LA21_1 <= S_JOIN)||LA21_1==S_ORDER_BY) ) {
                    alt21=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // GraphQueryParser.g:118:4: NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    NAME62=(Token)match(input,NAME,FOLLOW_NAME_in_mergeByExpr760); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME62_tree = 
                    (Object)adaptor.create(NAME62)
                    ;
                    adaptor.addChild(root_0, NAME62_tree);
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:119:5: ( function AS ! NAME )
                    {
                    root_0 = (Object)adaptor.nil();


                    // GraphQueryParser.g:119:5: ( function AS ! NAME )
                    // GraphQueryParser.g:119:6: function AS ! NAME
                    {
                    pushFollow(FOLLOW_function_in_mergeByExpr767);
                    function63=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function63.getTree());

                    AS64=(Token)match(input,AS,FOLLOW_AS_in_mergeByExpr769); if (state.failed) return retval;

                    NAME65=(Token)match(input,NAME,FOLLOW_NAME_in_mergeByExpr772); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME65_tree = 
                    (Object)adaptor.create(NAME65)
                    ;
                    adaptor.addChild(root_0, NAME65_tree);
                    }

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, mergeByExpr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "mergeByExpr"


    public static class clauseSplitBy_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseSplitBy"
    // GraphQueryParser.g:122:1: clauseSplitBy : S_SPLIT_BY n= NAME -> ^( CLAUSE ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS $n) ) ) ;
    public final GraphQueryParser.clauseSplitBy_return clauseSplitBy() throws RecognitionException {
        GraphQueryParser.clauseSplitBy_return retval = new GraphQueryParser.clauseSplitBy_return();
        retval.start = input.LT(1);

        int clauseSplitBy_StartIndex = input.index();

        Object root_0 = null;

        Token n=null;
        Token S_SPLIT_BY66=null;

        Object n_tree=null;
        Object S_SPLIT_BY66_tree=null;
        RewriteRuleTokenStream stream_S_SPLIT_BY=new RewriteRuleTokenStream(adaptor,"token S_SPLIT_BY");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // GraphQueryParser.g:123:2: ( S_SPLIT_BY n= NAME -> ^( CLAUSE ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS $n) ) ) )
            // GraphQueryParser.g:123:4: S_SPLIT_BY n= NAME
            {
            S_SPLIT_BY66=(Token)match(input,S_SPLIT_BY,FOLLOW_S_SPLIT_BY_in_clauseSplitBy784); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_SPLIT_BY.add(S_SPLIT_BY66);


            n=(Token)match(input,NAME,FOLLOW_NAME_in_clauseSplitBy788); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(n);


            // AST REWRITE
            // elements: n
            // token labels: n
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleTokenStream stream_n=new RewriteRuleTokenStream(adaptor,"token n",n);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 124:3: -> ^( CLAUSE ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS $n) ) )
            {
                // GraphQueryParser.g:124:6: ^( CLAUSE ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS $n) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:124:15: ^( C_SPLIT_BY ^( CLAUSE_ARGUMENTS $n) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_SPLIT_BY, "C_SPLIT_BY")
                , root_2);

                // GraphQueryParser.g:124:28: ^( CLAUSE_ARGUMENTS $n)
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_n.nextNode());

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, clauseSplitBy_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseSplitBy"


    public static class clauseGroupBy_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseGroupBy"
    // GraphQueryParser.g:127:1: clauseGroupBy : S_GROUP_BY groupByExpr ( COMMA groupByExpr )* -> ^( CLAUSE ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS groupByExpr ( groupByExpr )* ) ) ) ;
    public final GraphQueryParser.clauseGroupBy_return clauseGroupBy() throws RecognitionException {
        GraphQueryParser.clauseGroupBy_return retval = new GraphQueryParser.clauseGroupBy_return();
        retval.start = input.LT(1);

        int clauseGroupBy_StartIndex = input.index();

        Object root_0 = null;

        Token S_GROUP_BY67=null;
        Token COMMA69=null;
        GraphQueryParser.groupByExpr_return groupByExpr68 =null;

        GraphQueryParser.groupByExpr_return groupByExpr70 =null;


        Object S_GROUP_BY67_tree=null;
        Object COMMA69_tree=null;
        RewriteRuleTokenStream stream_S_GROUP_BY=new RewriteRuleTokenStream(adaptor,"token S_GROUP_BY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_groupByExpr=new RewriteRuleSubtreeStream(adaptor,"rule groupByExpr");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // GraphQueryParser.g:128:2: ( S_GROUP_BY groupByExpr ( COMMA groupByExpr )* -> ^( CLAUSE ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS groupByExpr ( groupByExpr )* ) ) ) )
            // GraphQueryParser.g:128:4: S_GROUP_BY groupByExpr ( COMMA groupByExpr )*
            {
            S_GROUP_BY67=(Token)match(input,S_GROUP_BY,FOLLOW_S_GROUP_BY_in_clauseGroupBy818); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_GROUP_BY.add(S_GROUP_BY67);


            pushFollow(FOLLOW_groupByExpr_in_clauseGroupBy820);
            groupByExpr68=groupByExpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_groupByExpr.add(groupByExpr68.getTree());

            // GraphQueryParser.g:128:27: ( COMMA groupByExpr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMA) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // GraphQueryParser.g:128:28: COMMA groupByExpr
            	    {
            	    COMMA69=(Token)match(input,COMMA,FOLLOW_COMMA_in_clauseGroupBy823); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA69);


            	    pushFollow(FOLLOW_groupByExpr_in_clauseGroupBy825);
            	    groupByExpr70=groupByExpr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_groupByExpr.add(groupByExpr70.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            // AST REWRITE
            // elements: groupByExpr, groupByExpr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 129:3: -> ^( CLAUSE ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS groupByExpr ( groupByExpr )* ) ) )
            {
                // GraphQueryParser.g:129:6: ^( CLAUSE ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS groupByExpr ( groupByExpr )* ) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:129:15: ^( C_GROUP_BY ^( CLAUSE_ARGUMENTS groupByExpr ( groupByExpr )* ) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_GROUP_BY, "C_GROUP_BY")
                , root_2);

                // GraphQueryParser.g:129:28: ^( CLAUSE_ARGUMENTS groupByExpr ( groupByExpr )* )
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_groupByExpr.nextTree());

                // GraphQueryParser.g:129:59: ( groupByExpr )*
                while ( stream_groupByExpr.hasNext() ) {
                    adaptor.addChild(root_3, stream_groupByExpr.nextTree());

                }
                stream_groupByExpr.reset();

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, clauseGroupBy_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseGroupBy"


    public static class groupByExpr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "groupByExpr"
    // GraphQueryParser.g:132:1: groupByExpr : ( NAME | ( function AS ! NAME ) );
    public final GraphQueryParser.groupByExpr_return groupByExpr() throws RecognitionException {
        GraphQueryParser.groupByExpr_return retval = new GraphQueryParser.groupByExpr_return();
        retval.start = input.LT(1);

        int groupByExpr_StartIndex = input.index();

        Object root_0 = null;

        Token NAME71=null;
        Token AS73=null;
        Token NAME74=null;
        GraphQueryParser.function_return function72 =null;


        Object NAME71_tree=null;
        Object AS73_tree=null;
        Object NAME74_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // GraphQueryParser.g:133:2: ( NAME | ( function AS ! NAME ) )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==NAME) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==LEFT_PAREN) ) {
                    alt23=2;
                }
                else if ( (LA23_1==EOF||LA23_1==COMMA||LA23_1==ON||LA23_1==RIGHT_PAREN||LA23_1==STATEMENT_TERMINATOR||(LA23_1 >= S_HAVING && LA23_1 <= S_JOIN)||LA23_1==S_ORDER_BY) ) {
                    alt23=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // GraphQueryParser.g:133:4: NAME
                    {
                    root_0 = (Object)adaptor.nil();


                    NAME71=(Token)match(input,NAME,FOLLOW_NAME_in_groupByExpr861); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME71_tree = 
                    (Object)adaptor.create(NAME71)
                    ;
                    adaptor.addChild(root_0, NAME71_tree);
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:134:5: ( function AS ! NAME )
                    {
                    root_0 = (Object)adaptor.nil();


                    // GraphQueryParser.g:134:5: ( function AS ! NAME )
                    // GraphQueryParser.g:134:6: function AS ! NAME
                    {
                    pushFollow(FOLLOW_function_in_groupByExpr868);
                    function72=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function72.getTree());

                    AS73=(Token)match(input,AS,FOLLOW_AS_in_groupByExpr870); if (state.failed) return retval;

                    NAME74=(Token)match(input,NAME,FOLLOW_NAME_in_groupByExpr873); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NAME74_tree = 
                    (Object)adaptor.create(NAME74)
                    ;
                    adaptor.addChild(root_0, NAME74_tree);
                    }

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, groupByExpr_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "groupByExpr"


    public static class clauseWhere_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseWhere"
    // GraphQueryParser.g:137:1: clauseWhere : S_WHERE f= function -> ^( CLAUSE ^( C_WHERE ^( CLAUSE_ARGUMENTS $f) ) ) ;
    public final GraphQueryParser.clauseWhere_return clauseWhere() throws RecognitionException {
        GraphQueryParser.clauseWhere_return retval = new GraphQueryParser.clauseWhere_return();
        retval.start = input.LT(1);

        int clauseWhere_StartIndex = input.index();

        Object root_0 = null;

        Token S_WHERE75=null;
        GraphQueryParser.function_return f =null;


        Object S_WHERE75_tree=null;
        RewriteRuleTokenStream stream_S_WHERE=new RewriteRuleTokenStream(adaptor,"token S_WHERE");
        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // GraphQueryParser.g:138:2: ( S_WHERE f= function -> ^( CLAUSE ^( C_WHERE ^( CLAUSE_ARGUMENTS $f) ) ) )
            // GraphQueryParser.g:138:4: S_WHERE f= function
            {
            S_WHERE75=(Token)match(input,S_WHERE,FOLLOW_S_WHERE_in_clauseWhere885); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_WHERE.add(S_WHERE75);


            pushFollow(FOLLOW_function_in_clauseWhere889);
            f=function();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_function.add(f.getTree());

            // AST REWRITE
            // elements: f
            // token labels: 
            // rule labels: f, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_f=new RewriteRuleSubtreeStream(adaptor,"rule f",f!=null?f.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 139:3: -> ^( CLAUSE ^( C_WHERE ^( CLAUSE_ARGUMENTS $f) ) )
            {
                // GraphQueryParser.g:139:6: ^( CLAUSE ^( C_WHERE ^( CLAUSE_ARGUMENTS $f) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:139:15: ^( C_WHERE ^( CLAUSE_ARGUMENTS $f) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_WHERE, "C_WHERE")
                , root_2);

                // GraphQueryParser.g:139:25: ^( CLAUSE_ARGUMENTS $f)
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_f.nextTree());

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, clauseWhere_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseWhere"


    public static class clauseFrom_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseFrom"
    // GraphQueryParser.g:142:1: clauseFrom : S_FROM TYPE fr= fromType AS as= NAME -> ^( CLAUSE ^( C_FROM ^( CLAUSE_ARGUMENTS $fr $as) ) ) ;
    public final GraphQueryParser.clauseFrom_return clauseFrom() throws RecognitionException {
        GraphQueryParser.clauseFrom_return retval = new GraphQueryParser.clauseFrom_return();
        retval.start = input.LT(1);

        int clauseFrom_StartIndex = input.index();

        Object root_0 = null;

        Token as=null;
        Token S_FROM76=null;
        Token TYPE77=null;
        Token AS78=null;
        GraphQueryParser.fromType_return fr =null;


        Object as_tree=null;
        Object S_FROM76_tree=null;
        Object TYPE77_tree=null;
        Object AS78_tree=null;
        RewriteRuleTokenStream stream_S_FROM=new RewriteRuleTokenStream(adaptor,"token S_FROM");
        RewriteRuleTokenStream stream_AS=new RewriteRuleTokenStream(adaptor,"token AS");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleTokenStream stream_TYPE=new RewriteRuleTokenStream(adaptor,"token TYPE");
        RewriteRuleSubtreeStream stream_fromType=new RewriteRuleSubtreeStream(adaptor,"rule fromType");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // GraphQueryParser.g:143:2: ( S_FROM TYPE fr= fromType AS as= NAME -> ^( CLAUSE ^( C_FROM ^( CLAUSE_ARGUMENTS $fr $as) ) ) )
            // GraphQueryParser.g:143:4: S_FROM TYPE fr= fromType AS as= NAME
            {
            S_FROM76=(Token)match(input,S_FROM,FOLLOW_S_FROM_in_clauseFrom919); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_FROM.add(S_FROM76);


            TYPE77=(Token)match(input,TYPE,FOLLOW_TYPE_in_clauseFrom921); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TYPE.add(TYPE77);


            pushFollow(FOLLOW_fromType_in_clauseFrom925);
            fr=fromType();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_fromType.add(fr.getTree());

            AS78=(Token)match(input,AS,FOLLOW_AS_in_clauseFrom927); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_AS.add(AS78);


            as=(Token)match(input,NAME,FOLLOW_NAME_in_clauseFrom931); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(as);


            // AST REWRITE
            // elements: as, fr
            // token labels: as
            // rule labels: retval, fr
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleTokenStream stream_as=new RewriteRuleTokenStream(adaptor,"token as",as);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_fr=new RewriteRuleSubtreeStream(adaptor,"rule fr",fr!=null?fr.tree:null);

            root_0 = (Object)adaptor.nil();
            // 144:3: -> ^( CLAUSE ^( C_FROM ^( CLAUSE_ARGUMENTS $fr $as) ) )
            {
                // GraphQueryParser.g:144:6: ^( CLAUSE ^( C_FROM ^( CLAUSE_ARGUMENTS $fr $as) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:144:15: ^( C_FROM ^( CLAUSE_ARGUMENTS $fr $as) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_FROM, "C_FROM")
                , root_2);

                // GraphQueryParser.g:144:24: ^( CLAUSE_ARGUMENTS $fr $as)
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_fr.nextTree());

                adaptor.addChild(root_3, stream_as.nextNode());

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, clauseFrom_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseFrom"


    public static class fromType_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fromType"
    // GraphQueryParser.g:147:1: fromType : ( FROM_TYPE_GRAPH -> P_FROM_TYPE_GRAPH | FROM_TYPE_ELEMENT -> P_FROM_TYPE_ELEMENT | FROM_TYPE_NODE -> P_FROM_TYPE_NODE | FROM_TYPE_EDGE -> P_FROM_TYPE_EDGE | FROM_TYPE_ATTRIBUTE -> P_FROM_TYPE_ATTRIBUTE );
    public final GraphQueryParser.fromType_return fromType() throws RecognitionException {
        GraphQueryParser.fromType_return retval = new GraphQueryParser.fromType_return();
        retval.start = input.LT(1);

        int fromType_StartIndex = input.index();

        Object root_0 = null;

        Token FROM_TYPE_GRAPH79=null;
        Token FROM_TYPE_ELEMENT80=null;
        Token FROM_TYPE_NODE81=null;
        Token FROM_TYPE_EDGE82=null;
        Token FROM_TYPE_ATTRIBUTE83=null;

        Object FROM_TYPE_GRAPH79_tree=null;
        Object FROM_TYPE_ELEMENT80_tree=null;
        Object FROM_TYPE_NODE81_tree=null;
        Object FROM_TYPE_EDGE82_tree=null;
        Object FROM_TYPE_ATTRIBUTE83_tree=null;
        RewriteRuleTokenStream stream_FROM_TYPE_GRAPH=new RewriteRuleTokenStream(adaptor,"token FROM_TYPE_GRAPH");
        RewriteRuleTokenStream stream_FROM_TYPE_ELEMENT=new RewriteRuleTokenStream(adaptor,"token FROM_TYPE_ELEMENT");
        RewriteRuleTokenStream stream_FROM_TYPE_ATTRIBUTE=new RewriteRuleTokenStream(adaptor,"token FROM_TYPE_ATTRIBUTE");
        RewriteRuleTokenStream stream_FROM_TYPE_EDGE=new RewriteRuleTokenStream(adaptor,"token FROM_TYPE_EDGE");
        RewriteRuleTokenStream stream_FROM_TYPE_NODE=new RewriteRuleTokenStream(adaptor,"token FROM_TYPE_NODE");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // GraphQueryParser.g:148:2: ( FROM_TYPE_GRAPH -> P_FROM_TYPE_GRAPH | FROM_TYPE_ELEMENT -> P_FROM_TYPE_ELEMENT | FROM_TYPE_NODE -> P_FROM_TYPE_NODE | FROM_TYPE_EDGE -> P_FROM_TYPE_EDGE | FROM_TYPE_ATTRIBUTE -> P_FROM_TYPE_ATTRIBUTE )
            int alt24=5;
            switch ( input.LA(1) ) {
            case FROM_TYPE_GRAPH:
                {
                alt24=1;
                }
                break;
            case FROM_TYPE_ELEMENT:
                {
                alt24=2;
                }
                break;
            case FROM_TYPE_NODE:
                {
                alt24=3;
                }
                break;
            case FROM_TYPE_EDGE:
                {
                alt24=4;
                }
                break;
            case FROM_TYPE_ATTRIBUTE:
                {
                alt24=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }

            switch (alt24) {
                case 1 :
                    // GraphQueryParser.g:148:4: FROM_TYPE_GRAPH
                    {
                    FROM_TYPE_GRAPH79=(Token)match(input,FROM_TYPE_GRAPH,FOLLOW_FROM_TYPE_GRAPH_in_fromType964); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FROM_TYPE_GRAPH.add(FROM_TYPE_GRAPH79);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 148:20: -> P_FROM_TYPE_GRAPH
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(P_FROM_TYPE_GRAPH, "P_FROM_TYPE_GRAPH")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:149:5: FROM_TYPE_ELEMENT
                    {
                    FROM_TYPE_ELEMENT80=(Token)match(input,FROM_TYPE_ELEMENT,FOLLOW_FROM_TYPE_ELEMENT_in_fromType974); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FROM_TYPE_ELEMENT.add(FROM_TYPE_ELEMENT80);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 149:23: -> P_FROM_TYPE_ELEMENT
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(P_FROM_TYPE_ELEMENT, "P_FROM_TYPE_ELEMENT")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // GraphQueryParser.g:150:5: FROM_TYPE_NODE
                    {
                    FROM_TYPE_NODE81=(Token)match(input,FROM_TYPE_NODE,FOLLOW_FROM_TYPE_NODE_in_fromType984); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FROM_TYPE_NODE.add(FROM_TYPE_NODE81);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 150:20: -> P_FROM_TYPE_NODE
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(P_FROM_TYPE_NODE, "P_FROM_TYPE_NODE")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // GraphQueryParser.g:151:5: FROM_TYPE_EDGE
                    {
                    FROM_TYPE_EDGE82=(Token)match(input,FROM_TYPE_EDGE,FOLLOW_FROM_TYPE_EDGE_in_fromType994); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FROM_TYPE_EDGE.add(FROM_TYPE_EDGE82);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 151:20: -> P_FROM_TYPE_EDGE
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(P_FROM_TYPE_EDGE, "P_FROM_TYPE_EDGE")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // GraphQueryParser.g:152:5: FROM_TYPE_ATTRIBUTE
                    {
                    FROM_TYPE_ATTRIBUTE83=(Token)match(input,FROM_TYPE_ATTRIBUTE,FOLLOW_FROM_TYPE_ATTRIBUTE_in_fromType1004); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_FROM_TYPE_ATTRIBUTE.add(FROM_TYPE_ATTRIBUTE83);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 152:25: -> P_FROM_TYPE_ATTRIBUTE
                    {
                        adaptor.addChild(root_0, 
                        (Object)adaptor.create(P_FROM_TYPE_ATTRIBUTE, "P_FROM_TYPE_ATTRIBUTE")
                        );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, fromType_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "fromType"


    public static class clauseTabReader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "clauseTabReader"
    // GraphQueryParser.g:155:1: clauseTabReader : S_TAB_READER nf= STRING_LITERAL ef= STRING_LITERAL -> ^( CLAUSE ^( C_TAB_READER ^( CLAUSE_ARGUMENTS $nf $ef) ) ) ;
    public final GraphQueryParser.clauseTabReader_return clauseTabReader() throws RecognitionException {
        GraphQueryParser.clauseTabReader_return retval = new GraphQueryParser.clauseTabReader_return();
        retval.start = input.LT(1);

        int clauseTabReader_StartIndex = input.index();

        Object root_0 = null;

        Token nf=null;
        Token ef=null;
        Token S_TAB_READER84=null;

        Object nf_tree=null;
        Object ef_tree=null;
        Object S_TAB_READER84_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_S_TAB_READER=new RewriteRuleTokenStream(adaptor,"token S_TAB_READER");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // GraphQueryParser.g:156:2: ( S_TAB_READER nf= STRING_LITERAL ef= STRING_LITERAL -> ^( CLAUSE ^( C_TAB_READER ^( CLAUSE_ARGUMENTS $nf $ef) ) ) )
            // GraphQueryParser.g:156:4: S_TAB_READER nf= STRING_LITERAL ef= STRING_LITERAL
            {
            S_TAB_READER84=(Token)match(input,S_TAB_READER,FOLLOW_S_TAB_READER_in_clauseTabReader1020); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_S_TAB_READER.add(S_TAB_READER84);


            nf=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_clauseTabReader1024); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(nf);


            ef=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_clauseTabReader1028); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(ef);


            // AST REWRITE
            // elements: ef, nf
            // token labels: ef, nf
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleTokenStream stream_ef=new RewriteRuleTokenStream(adaptor,"token ef",ef);
            RewriteRuleTokenStream stream_nf=new RewriteRuleTokenStream(adaptor,"token nf",nf);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 157:3: -> ^( CLAUSE ^( C_TAB_READER ^( CLAUSE_ARGUMENTS $nf $ef) ) )
            {
                // GraphQueryParser.g:157:6: ^( CLAUSE ^( C_TAB_READER ^( CLAUSE_ARGUMENTS $nf $ef) ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE, "CLAUSE")
                , root_1);

                // GraphQueryParser.g:157:15: ^( C_TAB_READER ^( CLAUSE_ARGUMENTS $nf $ef) )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(C_TAB_READER, "C_TAB_READER")
                , root_2);

                // GraphQueryParser.g:157:30: ^( CLAUSE_ARGUMENTS $nf $ef)
                {
                Object root_3 = (Object)adaptor.nil();
                root_3 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(CLAUSE_ARGUMENTS, "CLAUSE_ARGUMENTS")
                , root_3);

                adaptor.addChild(root_3, stream_nf.nextNode());

                adaptor.addChild(root_3, stream_ef.nextNode());

                adaptor.addChild(root_2, root_3);
                }

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, clauseTabReader_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "clauseTabReader"


    public static class function_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "function"
    // GraphQueryParser.g:161:1: function : fn= NAME LEFT_PAREN argument_list RIGHT_PAREN -> ^( FUNCTION $fn argument_list ) ;
    public final GraphQueryParser.function_return function() throws RecognitionException {
        GraphQueryParser.function_return retval = new GraphQueryParser.function_return();
        retval.start = input.LT(1);

        int function_StartIndex = input.index();

        Object root_0 = null;

        Token fn=null;
        Token LEFT_PAREN85=null;
        Token RIGHT_PAREN87=null;
        GraphQueryParser.argument_list_return argument_list86 =null;


        Object fn_tree=null;
        Object LEFT_PAREN85_tree=null;
        Object RIGHT_PAREN87_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_NAME=new RewriteRuleTokenStream(adaptor,"token NAME");
        RewriteRuleSubtreeStream stream_argument_list=new RewriteRuleSubtreeStream(adaptor,"rule argument_list");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // GraphQueryParser.g:162:2: (fn= NAME LEFT_PAREN argument_list RIGHT_PAREN -> ^( FUNCTION $fn argument_list ) )
            // GraphQueryParser.g:162:4: fn= NAME LEFT_PAREN argument_list RIGHT_PAREN
            {
            fn=(Token)match(input,NAME,FOLLOW_NAME_in_function1064); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_NAME.add(fn);


            LEFT_PAREN85=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_function1066); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN85);


            pushFollow(FOLLOW_argument_list_in_function1068);
            argument_list86=argument_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_argument_list.add(argument_list86.getTree());

            RIGHT_PAREN87=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_function1070); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN87);


            // AST REWRITE
            // elements: argument_list, fn
            // token labels: fn
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleTokenStream stream_fn=new RewriteRuleTokenStream(adaptor,"token fn",fn);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 163:3: -> ^( FUNCTION $fn argument_list )
            {
                // GraphQueryParser.g:163:6: ^( FUNCTION $fn argument_list )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(
                (Object)adaptor.create(FUNCTION, "FUNCTION")
                , root_1);

                adaptor.addChild(root_1, stream_fn.nextNode());

                adaptor.addChild(root_1, stream_argument_list.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, function_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "function"


    public static class argument_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "argument_list"
    // GraphQueryParser.g:166:1: argument_list : ( argument ( COMMA argument )* -> ^( ARGUMENT_LIST argument ( argument )* ) | -> ^( ARGUMENT_LIST ) ) ;
    public final GraphQueryParser.argument_list_return argument_list() throws RecognitionException {
        GraphQueryParser.argument_list_return retval = new GraphQueryParser.argument_list_return();
        retval.start = input.LT(1);

        int argument_list_StartIndex = input.index();

        Object root_0 = null;

        Token COMMA89=null;
        GraphQueryParser.argument_return argument88 =null;

        GraphQueryParser.argument_return argument90 =null;


        Object COMMA89_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_argument=new RewriteRuleSubtreeStream(adaptor,"rule argument");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

            // GraphQueryParser.g:167:2: ( ( argument ( COMMA argument )* -> ^( ARGUMENT_LIST argument ( argument )* ) | -> ^( ARGUMENT_LIST ) ) )
            // GraphQueryParser.g:167:4: ( argument ( COMMA argument )* -> ^( ARGUMENT_LIST argument ( argument )* ) | -> ^( ARGUMENT_LIST ) )
            {
            // GraphQueryParser.g:167:4: ( argument ( COMMA argument )* -> ^( ARGUMENT_LIST argument ( argument )* ) | -> ^( ARGUMENT_LIST ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==BOOLEAN_LITERAL||LA26_0==FLOAT||LA26_0==INTEGER||LA26_0==NAME||LA26_0==NULL_LITERAL||LA26_0==STRING_LITERAL) ) {
                alt26=1;
            }
            else if ( (LA26_0==RIGHT_PAREN) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // GraphQueryParser.g:168:4: argument ( COMMA argument )*
                    {
                    pushFollow(FOLLOW_argument_in_argument_list1100);
                    argument88=argument();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_argument.add(argument88.getTree());

                    // GraphQueryParser.g:168:13: ( COMMA argument )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==COMMA) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // GraphQueryParser.g:168:14: COMMA argument
                    	    {
                    	    COMMA89=(Token)match(input,COMMA,FOLLOW_COMMA_in_argument_list1103); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA89);


                    	    pushFollow(FOLLOW_argument_in_argument_list1105);
                    	    argument90=argument();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_argument.add(argument90.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: argument, argument
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 168:31: -> ^( ARGUMENT_LIST argument ( argument )* )
                    {
                        // GraphQueryParser.g:168:34: ^( ARGUMENT_LIST argument ( argument )* )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_1);

                        adaptor.addChild(root_1, stream_argument.nextTree());

                        // GraphQueryParser.g:168:59: ( argument )*
                        while ( stream_argument.hasNext() ) {
                            adaptor.addChild(root_1, stream_argument.nextTree());

                        }
                        stream_argument.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:169:7: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 169:7: -> ^( ARGUMENT_LIST )
                    {
                        // GraphQueryParser.g:169:10: ^( ARGUMENT_LIST )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(
                        (Object)adaptor.create(ARGUMENT_LIST, "ARGUMENT_LIST")
                        , root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, argument_list_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "argument_list"


    public static class argument_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "argument"
    // GraphQueryParser.g:173:1: argument : ( STRING_LITERAL | BOOLEAN_LITERAL | NULL_LITERAL | FLOAT | INTEGER | function ) ;
    public final GraphQueryParser.argument_return argument() throws RecognitionException {
        GraphQueryParser.argument_return retval = new GraphQueryParser.argument_return();
        retval.start = input.LT(1);

        int argument_StartIndex = input.index();

        Object root_0 = null;

        Token STRING_LITERAL91=null;
        Token BOOLEAN_LITERAL92=null;
        Token NULL_LITERAL93=null;
        Token FLOAT94=null;
        Token INTEGER95=null;
        GraphQueryParser.function_return function96 =null;


        Object STRING_LITERAL91_tree=null;
        Object BOOLEAN_LITERAL92_tree=null;
        Object NULL_LITERAL93_tree=null;
        Object FLOAT94_tree=null;
        Object INTEGER95_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

            // GraphQueryParser.g:174:2: ( ( STRING_LITERAL | BOOLEAN_LITERAL | NULL_LITERAL | FLOAT | INTEGER | function ) )
            // GraphQueryParser.g:174:4: ( STRING_LITERAL | BOOLEAN_LITERAL | NULL_LITERAL | FLOAT | INTEGER | function )
            {
            root_0 = (Object)adaptor.nil();


            // GraphQueryParser.g:174:4: ( STRING_LITERAL | BOOLEAN_LITERAL | NULL_LITERAL | FLOAT | INTEGER | function )
            int alt27=6;
            switch ( input.LA(1) ) {
            case STRING_LITERAL:
                {
                alt27=1;
                }
                break;
            case BOOLEAN_LITERAL:
                {
                alt27=2;
                }
                break;
            case NULL_LITERAL:
                {
                alt27=3;
                }
                break;
            case FLOAT:
                {
                alt27=4;
                }
                break;
            case INTEGER:
                {
                alt27=5;
                }
                break;
            case NAME:
                {
                alt27=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;

            }

            switch (alt27) {
                case 1 :
                    // GraphQueryParser.g:174:6: STRING_LITERAL
                    {
                    STRING_LITERAL91=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_argument1151); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL91_tree = 
                    (Object)adaptor.create(STRING_LITERAL91)
                    ;
                    adaptor.addChild(root_0, STRING_LITERAL91_tree);
                    }

                    }
                    break;
                case 2 :
                    // GraphQueryParser.g:175:6: BOOLEAN_LITERAL
                    {
                    BOOLEAN_LITERAL92=(Token)match(input,BOOLEAN_LITERAL,FOLLOW_BOOLEAN_LITERAL_in_argument1158); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOLEAN_LITERAL92_tree = 
                    (Object)adaptor.create(BOOLEAN_LITERAL92)
                    ;
                    adaptor.addChild(root_0, BOOLEAN_LITERAL92_tree);
                    }

                    }
                    break;
                case 3 :
                    // GraphQueryParser.g:176:6: NULL_LITERAL
                    {
                    NULL_LITERAL93=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_argument1165); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL_LITERAL93_tree = 
                    (Object)adaptor.create(NULL_LITERAL93)
                    ;
                    adaptor.addChild(root_0, NULL_LITERAL93_tree);
                    }

                    }
                    break;
                case 4 :
                    // GraphQueryParser.g:177:6: FLOAT
                    {
                    FLOAT94=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_argument1172); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT94_tree = 
                    (Object)adaptor.create(FLOAT94)
                    ;
                    adaptor.addChild(root_0, FLOAT94_tree);
                    }

                    }
                    break;
                case 5 :
                    // GraphQueryParser.g:178:6: INTEGER
                    {
                    INTEGER95=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_argument1179); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INTEGER95_tree = 
                    (Object)adaptor.create(INTEGER95)
                    ;
                    adaptor.addChild(root_0, INTEGER95_tree);
                    }

                    }
                    break;
                case 6 :
                    // GraphQueryParser.g:179:6: function
                    {
                    pushFollow(FOLLOW_function_in_argument1186);
                    function96=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function96.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 24, argument_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "argument"

    // $ANTLR start synpred2_GraphQueryParser
    public final void synpred2_GraphQueryParser_fragment() throws RecognitionException {
        // GraphQueryParser.g:56:4: ( ( contextParams )? baseSelectStatement STATEMENT_TERMINATOR )
        // GraphQueryParser.g:56:4: ( contextParams )? baseSelectStatement STATEMENT_TERMINATOR
        {
        // GraphQueryParser.g:56:4: ( contextParams )?
        int alt28=2;
        int LA28_0 = input.LA(1);

        if ( (LA28_0==LEFT_BRACE) ) {
            alt28=1;
        }
        switch (alt28) {
            case 1 :
                // GraphQueryParser.g:56:4: contextParams
                {
                pushFollow(FOLLOW_contextParams_in_synpred2_GraphQueryParser214);
                contextParams();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        pushFollow(FOLLOW_baseSelectStatement_in_synpred2_GraphQueryParser217);
        baseSelectStatement();

        state._fsp--;
        if (state.failed) return ;

        match(input,STATEMENT_TERMINATOR,FOLLOW_STATEMENT_TERMINATOR_in_synpred2_GraphQueryParser219); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_GraphQueryParser

    // $ANTLR start synpred5_GraphQueryParser
    public final void synpred5_GraphQueryParser_fragment() throws RecognitionException {
        // GraphQueryParser.g:72:19: ( ( clauseFrom clauseTabReader ) )
        // GraphQueryParser.g:72:19: ( clauseFrom clauseTabReader )
        {
        // GraphQueryParser.g:72:19: ( clauseFrom clauseTabReader )
        // GraphQueryParser.g:72:20: clauseFrom clauseTabReader
        {
        pushFollow(FOLLOW_clauseFrom_in_synpred5_GraphQueryParser313);
        clauseFrom();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_clauseTabReader_in_synpred5_GraphQueryParser315);
        clauseTabReader();

        state._fsp--;
        if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred5_GraphQueryParser

    // $ANTLR start synpred6_GraphQueryParser
    public final void synpred6_GraphQueryParser_fragment() throws RecognitionException {
        // GraphQueryParser.g:72:50: ( ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN ) )
        // GraphQueryParser.g:72:50: ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN )
        {
        // GraphQueryParser.g:72:50: ( S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN )
        // GraphQueryParser.g:72:51: S_FROM LEFT_PAREN baseSelectStatement RIGHT_PAREN
        {
        match(input,S_FROM,FOLLOW_S_FROM_in_synpred6_GraphQueryParser321); if (state.failed) return ;

        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred6_GraphQueryParser323); if (state.failed) return ;

        pushFollow(FOLLOW_baseSelectStatement_in_synpred6_GraphQueryParser325);
        baseSelectStatement();

        state._fsp--;
        if (state.failed) return ;

        match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_synpred6_GraphQueryParser327); if (state.failed) return ;

        }


        }

    }
    // $ANTLR end synpred6_GraphQueryParser

    // Delegated rules

    public final boolean synpred2_GraphQueryParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_GraphQueryParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_GraphQueryParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_GraphQueryParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_GraphQueryParser() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_GraphQueryParser_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_selectStatement_in_query201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_query203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextParams_in_selectStatement214 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_baseSelectStatement_in_selectStatement217 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_STATEMENT_TERMINATOR_in_selectStatement219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextParams_in_selectStatement225 = new BitSet(new long[]{0x0000080000400000L});
    public static final BitSet FOLLOW_joinStatement_in_selectStatement228 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_STATEMENT_TERMINATOR_in_selectStatement230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_BRACE_in_contextParams242 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_contextProperty_in_contextParams244 = new BitSet(new long[]{0x0000000020000100L});
    public static final BitSet FOLLOW_COMMA_in_contextParams247 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_contextProperty_in_contextParams249 = new BitSet(new long[]{0x0000000020000100L});
    public static final BitSet FOLLOW_RIGHT_BRACE_in_contextParams253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_contextProperty276 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ASSIGN_in_contextProperty278 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_contextProperty282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clauseSelect_in_baseSelectStatement308 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_clauseFrom_in_baseSelectStatement313 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_clauseTabReader_in_baseSelectStatement315 = new BitSet(new long[]{0x000056C000000002L});
    public static final BitSet FOLLOW_S_FROM_in_baseSelectStatement321 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_baseSelectStatement323 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_baseSelectStatement_in_baseSelectStatement325 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_baseSelectStatement327 = new BitSet(new long[]{0x000056C000000002L});
    public static final BitSet FOLLOW_S_FROM_in_baseSelectStatement333 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_baseSelectStatement335 = new BitSet(new long[]{0x0000080000400000L});
    public static final BitSet FOLLOW_joinStatement_in_baseSelectStatement337 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_baseSelectStatement339 = new BitSet(new long[]{0x000056C000000002L});
    public static final BitSet FOLLOW_clauseWhere_in_baseSelectStatement344 = new BitSet(new long[]{0x000016C000000002L});
    public static final BitSet FOLLOW_clauseMergeBy_in_baseSelectStatement348 = new BitSet(new long[]{0x0000048000000002L});
    public static final BitSet FOLLOW_clauseSplitBy_in_baseSelectStatement352 = new BitSet(new long[]{0x0000048000000002L});
    public static final BitSet FOLLOW_clauseGroupBy_in_baseSelectStatement356 = new BitSet(new long[]{0x0000048000000002L});
    public static final BitSet FOLLOW_clauseHaving_in_baseSelectStatement360 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_clauseOrderBy_in_baseSelectStatement363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_baseSelectStatement_in_joinStatement423 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_joinStatement428 = new BitSet(new long[]{0x0000080000400000L});
    public static final BitSet FOLLOW_joinStatement_in_joinStatement434 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_joinStatement436 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_S_JOIN_in_joinStatement440 = new BitSet(new long[]{0x0000080000400000L});
    public static final BitSet FOLLOW_baseSelectStatement_in_joinStatement445 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_joinStatement450 = new BitSet(new long[]{0x0000080000400000L});
    public static final BitSet FOLLOW_joinStatement_in_joinStatement454 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_joinStatement456 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ON_in_joinStatement461 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_function_in_joinStatement465 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_IS_EQUAL_in_joinStatement467 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_function_in_joinStatement471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_SELECT_in_clauseSelect515 = new BitSet(new long[]{0x0000000202000000L});
    public static final BitSet FOLLOW_STAR_in_clauseSelect518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectExpr_in_clauseSelect523 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_clauseSelect526 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_selectExpr_in_clauseSelect528 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_NAME_in_selectExpr566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_selectExpr573 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_selectExpr575 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_NAME_in_selectExpr578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_ORDER_BY_in_clauseOrderBy591 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_orderByExpr_in_clauseOrderBy593 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_clauseOrderBy596 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_orderByExpr_in_clauseOrderBy598 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_NAME_in_orderByExpr634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_orderByExpr640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_HAVING_in_clauseHaving651 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_function_in_clauseHaving655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_MERGE_BY_in_clauseMergeBy685 = new BitSet(new long[]{0x0000000000000480L});
    public static final BitSet FOLLOW_mergeByDistinct_in_clauseMergeBy687 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_BY_in_clauseMergeBy689 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_mergeByExpr_in_clauseMergeBy692 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_clauseMergeBy695 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_mergeByExpr_in_clauseMergeBy697 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_DISTINCT_in_mergeByDistinct736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_mergeByExpr760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_mergeByExpr767 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_mergeByExpr769 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_NAME_in_mergeByExpr772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_SPLIT_BY_in_clauseSplitBy784 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_NAME_in_clauseSplitBy788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_GROUP_BY_in_clauseGroupBy818 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_groupByExpr_in_clauseGroupBy820 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_clauseGroupBy823 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_groupByExpr_in_clauseGroupBy825 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_NAME_in_groupByExpr861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_groupByExpr868 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_groupByExpr870 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_NAME_in_groupByExpr873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_WHERE_in_clauseWhere885 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_function_in_clauseWhere889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_FROM_in_clauseFrom919 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_TYPE_in_clauseFrom921 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_fromType_in_clauseFrom925 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_AS_in_clauseFrom927 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_NAME_in_clauseFrom931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_TYPE_GRAPH_in_fromType964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_TYPE_ELEMENT_in_fromType974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_TYPE_NODE_in_fromType984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_TYPE_EDGE_in_fromType994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_TYPE_ATTRIBUTE_in_fromType1004 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_TAB_READER_in_clauseTabReader1020 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_clauseTabReader1024 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_clauseTabReader1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_function1064 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_function1066 = new BitSet(new long[]{0x000000084A082040L});
    public static final BitSet FOLLOW_argument_list_in_function1068 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_function1070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_argument_in_argument_list1100 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_argument_list1103 = new BitSet(new long[]{0x000000080A082040L});
    public static final BitSet FOLLOW_argument_in_argument_list1105 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_argument1151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_LITERAL_in_argument1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_LITERAL_in_argument1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_argument1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_argument1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_argument1186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextParams_in_synpred2_GraphQueryParser214 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_baseSelectStatement_in_synpred2_GraphQueryParser217 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_STATEMENT_TERMINATOR_in_synpred2_GraphQueryParser219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_clauseFrom_in_synpred5_GraphQueryParser313 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_clauseTabReader_in_synpred5_GraphQueryParser315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_S_FROM_in_synpred6_GraphQueryParser321 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred6_GraphQueryParser323 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_baseSelectStatement_in_synpred6_GraphQueryParser325 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_synpred6_GraphQueryParser327 = new BitSet(new long[]{0x0000000000000002L});

}