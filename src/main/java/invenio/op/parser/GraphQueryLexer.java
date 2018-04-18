// $ANTLR 3.4 GraphQueryLexer.g 2011-10-23 13:09:08
 package invenio.op.parser; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GraphQueryLexer extends Lexer {
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

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public GraphQueryLexer() {} 
    public GraphQueryLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GraphQueryLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "GraphQueryLexer.g"; }

    // $ANTLR start "S_JOIN"
    public final void mS_JOIN() throws RecognitionException {
        try {
            int _type = S_JOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:6:7: ( 'JOIN' | 'join' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='J') ) {
                alt1=1;
            }
            else if ( (LA1_0=='j') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // GraphQueryLexer.g:6:9: 'JOIN'
                    {
                    match("JOIN"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:6:18: 'join'
                    {
                    match("join"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_JOIN"

    // $ANTLR start "S_SELECT"
    public final void mS_SELECT() throws RecognitionException {
        try {
            int _type = S_SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:7:9: ( 'SELECT' | 'select' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='S') ) {
                alt2=1;
            }
            else if ( (LA2_0=='s') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // GraphQueryLexer.g:7:11: 'SELECT'
                    {
                    match("SELECT"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:7:22: 'select'
                    {
                    match("select"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_SELECT"

    // $ANTLR start "S_FROM"
    public final void mS_FROM() throws RecognitionException {
        try {
            int _type = S_FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:8:7: ( 'FROM' | 'from' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='F') ) {
                alt3=1;
            }
            else if ( (LA3_0=='f') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // GraphQueryLexer.g:8:9: 'FROM'
                    {
                    match("FROM"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:8:18: 'from'
                    {
                    match("from"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_FROM"

    // $ANTLR start "S_TAB_READER"
    public final void mS_TAB_READER() throws RecognitionException {
        try {
            int _type = S_TAB_READER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:9:13: ( 'TAB READER' | 'tab reader' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='T') ) {
                alt4=1;
            }
            else if ( (LA4_0=='t') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // GraphQueryLexer.g:9:15: 'TAB READER'
                    {
                    match("TAB READER"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:9:30: 'tab reader'
                    {
                    match("tab reader"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_TAB_READER"

    // $ANTLR start "S_WHERE"
    public final void mS_WHERE() throws RecognitionException {
        try {
            int _type = S_WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:10:8: ( 'WHERE' | 'where' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='W') ) {
                alt5=1;
            }
            else if ( (LA5_0=='w') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // GraphQueryLexer.g:10:10: 'WHERE'
                    {
                    match("WHERE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:10:20: 'where'
                    {
                    match("where"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_WHERE"

    // $ANTLR start "S_GROUP_BY"
    public final void mS_GROUP_BY() throws RecognitionException {
        try {
            int _type = S_GROUP_BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:11:11: ( 'GROUP BY' | 'group by' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='G') ) {
                alt6=1;
            }
            else if ( (LA6_0=='g') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // GraphQueryLexer.g:11:13: 'GROUP BY'
                    {
                    match("GROUP BY"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:11:26: 'group by'
                    {
                    match("group by"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_GROUP_BY"

    // $ANTLR start "S_HAVING"
    public final void mS_HAVING() throws RecognitionException {
        try {
            int _type = S_HAVING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:12:9: ( 'HAVING' | 'having' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='H') ) {
                alt7=1;
            }
            else if ( (LA7_0=='h') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // GraphQueryLexer.g:12:11: 'HAVING'
                    {
                    match("HAVING"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:12:22: 'having'
                    {
                    match("having"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_HAVING"

    // $ANTLR start "S_ORDER_BY"
    public final void mS_ORDER_BY() throws RecognitionException {
        try {
            int _type = S_ORDER_BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:13:11: ( 'ORDER BY' | 'order by' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='O') ) {
                alt8=1;
            }
            else if ( (LA8_0=='o') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // GraphQueryLexer.g:13:13: 'ORDER BY'
                    {
                    match("ORDER BY"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:13:26: 'order by'
                    {
                    match("order by"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_ORDER_BY"

    // $ANTLR start "S_MERGE_BY"
    public final void mS_MERGE_BY() throws RecognitionException {
        try {
            int _type = S_MERGE_BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:14:11: ( 'MERGE' | 'merge' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='M') ) {
                alt9=1;
            }
            else if ( (LA9_0=='m') ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }
            switch (alt9) {
                case 1 :
                    // GraphQueryLexer.g:14:13: 'MERGE'
                    {
                    match("MERGE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:14:23: 'merge'
                    {
                    match("merge"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_MERGE_BY"

    // $ANTLR start "S_SPLIT_BY"
    public final void mS_SPLIT_BY() throws RecognitionException {
        try {
            int _type = S_SPLIT_BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:15:11: ( 'SPLIT BY' | 'split by' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='S') ) {
                alt10=1;
            }
            else if ( (LA10_0=='s') ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // GraphQueryLexer.g:15:13: 'SPLIT BY'
                    {
                    match("SPLIT BY"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:15:26: 'split by'
                    {
                    match("split by"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_SPLIT_BY"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:17:3: ( 'AS' | 'as' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='A') ) {
                alt11=1;
            }
            else if ( (LA11_0=='a') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // GraphQueryLexer.g:17:5: 'AS'
                    {
                    match("AS"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:17:12: 'as'
                    {
                    match("as"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:18:5: ( 'TYPE' | 'type' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='T') ) {
                alt12=1;
            }
            else if ( (LA12_0=='t') ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // GraphQueryLexer.g:18:7: 'TYPE'
                    {
                    match("TYPE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:18:16: 'type'
                    {
                    match("type"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:19:5: ( '*' )
            // GraphQueryLexer.g:19:7: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "DISTINCT"
    public final void mDISTINCT() throws RecognitionException {
        try {
            int _type = DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:20:9: ( 'DISTINCT' | 'distinct' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='D') ) {
                alt13=1;
            }
            else if ( (LA13_0=='d') ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // GraphQueryLexer.g:20:11: 'DISTINCT'
                    {
                    match("DISTINCT"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:20:24: 'distinct'
                    {
                    match("distinct"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DISTINCT"

    // $ANTLR start "BY"
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:21:3: ( 'BY' | 'by' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='B') ) {
                alt14=1;
            }
            else if ( (LA14_0=='b') ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // GraphQueryLexer.g:21:5: 'BY'
                    {
                    match("BY"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:21:12: 'by'
                    {
                    match("by"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BY"

    // $ANTLR start "ON"
    public final void mON() throws RecognitionException {
        try {
            int _type = ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:22:3: ( 'ON' | 'on' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='O') ) {
                alt15=1;
            }
            else if ( (LA15_0=='o') ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // GraphQueryLexer.g:22:5: 'ON'
                    {
                    match("ON"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:22:12: 'on'
                    {
                    match("on"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ON"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:24:7: ( '=' )
            // GraphQueryLexer.g:24:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "IS_EQUAL"
    public final void mIS_EQUAL() throws RecognitionException {
        try {
            int _type = IS_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:25:9: ( '==' )
            // GraphQueryLexer.g:25:11: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IS_EQUAL"

    // $ANTLR start "SIGN"
    public final void mSIGN() throws RecognitionException {
        try {
            int _type = SIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:26:5: ( '+' | '-' )
            // GraphQueryLexer.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIGN"

    // $ANTLR start "LEFT_PAREN"
    public final void mLEFT_PAREN() throws RecognitionException {
        try {
            int _type = LEFT_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:27:11: ( '(' )
            // GraphQueryLexer.g:27:13: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEFT_PAREN"

    // $ANTLR start "RIGHT_PAREN"
    public final void mRIGHT_PAREN() throws RecognitionException {
        try {
            int _type = RIGHT_PAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:28:12: ( ')' )
            // GraphQueryLexer.g:28:14: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RIGHT_PAREN"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:29:7: ( ',' )
            // GraphQueryLexer.g:29:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "LEFT_BRACE"
    public final void mLEFT_BRACE() throws RecognitionException {
        try {
            int _type = LEFT_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:30:11: ( '{' )
            // GraphQueryLexer.g:30:13: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LEFT_BRACE"

    // $ANTLR start "RIGHT_BRACE"
    public final void mRIGHT_BRACE() throws RecognitionException {
        try {
            int _type = RIGHT_BRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:31:12: ( '}' )
            // GraphQueryLexer.g:31:14: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RIGHT_BRACE"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:32:4: ( '.' )
            // GraphQueryLexer.g:32:6: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "STATEMENT_TERMINATOR"
    public final void mSTATEMENT_TERMINATOR() throws RecognitionException {
        try {
            int _type = STATEMENT_TERMINATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:33:22: ( ';' )
            // GraphQueryLexer.g:33:24: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STATEMENT_TERMINATOR"

    // $ANTLR start "FROM_TYPE_GRAPH"
    public final void mFROM_TYPE_GRAPH() throws RecognitionException {
        try {
            int _type = FROM_TYPE_GRAPH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:37:16: ( 'GRAPH' | 'graph' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='G') ) {
                alt16=1;
            }
            else if ( (LA16_0=='g') ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // GraphQueryLexer.g:37:18: 'GRAPH'
                    {
                    match("GRAPH"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:37:28: 'graph'
                    {
                    match("graph"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM_TYPE_GRAPH"

    // $ANTLR start "FROM_TYPE_ELEMENT"
    public final void mFROM_TYPE_ELEMENT() throws RecognitionException {
        try {
            int _type = FROM_TYPE_ELEMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:38:18: ( 'ELEMENT' | 'element' )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='E') ) {
                alt17=1;
            }
            else if ( (LA17_0=='e') ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // GraphQueryLexer.g:38:20: 'ELEMENT'
                    {
                    match("ELEMENT"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:38:32: 'element'
                    {
                    match("element"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM_TYPE_ELEMENT"

    // $ANTLR start "FROM_TYPE_NODE"
    public final void mFROM_TYPE_NODE() throws RecognitionException {
        try {
            int _type = FROM_TYPE_NODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:39:15: ( 'NODE' | 'node' )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='N') ) {
                alt18=1;
            }
            else if ( (LA18_0=='n') ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }
            switch (alt18) {
                case 1 :
                    // GraphQueryLexer.g:39:17: 'NODE'
                    {
                    match("NODE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:39:26: 'node'
                    {
                    match("node"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM_TYPE_NODE"

    // $ANTLR start "FROM_TYPE_EDGE"
    public final void mFROM_TYPE_EDGE() throws RecognitionException {
        try {
            int _type = FROM_TYPE_EDGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:40:15: ( 'EDGE' | 'edge' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='E') ) {
                alt19=1;
            }
            else if ( (LA19_0=='e') ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }
            switch (alt19) {
                case 1 :
                    // GraphQueryLexer.g:40:17: 'EDGE'
                    {
                    match("EDGE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:40:26: 'edge'
                    {
                    match("edge"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM_TYPE_EDGE"

    // $ANTLR start "FROM_TYPE_ATTRIBUTE"
    public final void mFROM_TYPE_ATTRIBUTE() throws RecognitionException {
        try {
            int _type = FROM_TYPE_ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:41:20: ( 'ATTRIBUTE' | 'attribute' )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='A') ) {
                alt20=1;
            }
            else if ( (LA20_0=='a') ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // GraphQueryLexer.g:41:22: 'ATTRIBUTE'
                    {
                    match("ATTRIBUTE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:41:36: 'attribute'
                    {
                    match("attribute"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FROM_TYPE_ATTRIBUTE"

    // $ANTLR start "NULL_LITERAL"
    public final void mNULL_LITERAL() throws RecognitionException {
        try {
            int _type = NULL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:43:13: ( 'NULL' | 'null' )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='N') ) {
                alt21=1;
            }
            else if ( (LA21_0=='n') ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // GraphQueryLexer.g:43:15: 'NULL'
                    {
                    match("NULL"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:43:24: 'null'
                    {
                    match("null"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NULL_LITERAL"

    // $ANTLR start "BOOLEAN_LITERAL"
    public final void mBOOLEAN_LITERAL() throws RecognitionException {
        try {
            int _type = BOOLEAN_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:44:16: ( TRUE | FALSE )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='T'||LA22_0=='t') ) {
                alt22=1;
            }
            else if ( (LA22_0=='F'||LA22_0=='f') ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // GraphQueryLexer.g:44:18: TRUE
                    {
                    mTRUE(); 


                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:44:25: FALSE
                    {
                    mFALSE(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN_LITERAL"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            // GraphQueryLexer.g:45:14: ( 'TRUE' | 'true' )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='T') ) {
                alt23=1;
            }
            else if ( (LA23_0=='t') ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // GraphQueryLexer.g:45:16: 'TRUE'
                    {
                    match("TRUE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:45:25: 'true'
                    {
                    match("true"); 



                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            // GraphQueryLexer.g:46:15: ( 'FALSE' | 'false' )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='F') ) {
                alt24=1;
            }
            else if ( (LA24_0=='f') ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // GraphQueryLexer.g:46:17: 'FALSE'
                    {
                    match("FALSE"); 



                    }
                    break;
                case 2 :
                    // GraphQueryLexer.g:46:27: 'false'
                    {
                    match("false"); 



                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:51:6: ( ( SIGN )? ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
            // GraphQueryLexer.g:51:8: ( SIGN )? ( '0' .. '9' )+ '.' ( '0' .. '9' )+
            {
            // GraphQueryLexer.g:51:8: ( SIGN )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='+'||LA25_0=='-') ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // GraphQueryLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // GraphQueryLexer.g:51:14: ( '0' .. '9' )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0 >= '0' && LA26_0 <= '9')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // GraphQueryLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);


            match('.'); 

            // GraphQueryLexer.g:51:28: ( '0' .. '9' )+
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0 >= '0' && LA27_0 <= '9')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // GraphQueryLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:52:8: ( ( SIGN )? ( '0' .. '9' )+ )
            // GraphQueryLexer.g:52:10: ( SIGN )? ( '0' .. '9' )+
            {
            // GraphQueryLexer.g:52:10: ( SIGN )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0=='+'||LA28_0=='-') ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // GraphQueryLexer.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // GraphQueryLexer.g:52:16: ( '0' .. '9' )+
            int cnt29=0;
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0 >= '0' && LA29_0 <= '9')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // GraphQueryLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt29 >= 1 ) break loop29;
                        EarlyExitException eee =
                            new EarlyExitException(29, input);
                        throw eee;
                }
                cnt29++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:55:5: ( LETTER ( LETTER | DIGIT | '_' )* )
            // GraphQueryLexer.g:55:7: LETTER ( LETTER | DIGIT | '_' )*
            {
            mLETTER(); 


            // GraphQueryLexer.g:55:14: ( LETTER | DIGIT | '_' )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0 >= '0' && LA30_0 <= '9')||(LA30_0 >= 'A' && LA30_0 <= 'Z')||LA30_0=='_'||(LA30_0 >= 'a' && LA30_0 <= 'z')) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // GraphQueryLexer.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:58:15: ( '\"' (~ ( '\"' ) )* '\"' )
            // GraphQueryLexer.g:58:17: '\"' (~ ( '\"' ) )* '\"'
            {
            match('\"'); 

            // GraphQueryLexer.g:58:21: (~ ( '\"' ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0 >= '\u0000' && LA31_0 <= '!')||(LA31_0 >= '#' && LA31_0 <= '\uFFFF')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // GraphQueryLexer.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // GraphQueryLexer.g:62:16: ( LOWER | UPPER )
            // GraphQueryLexer.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            // GraphQueryLexer.g:63:15: ( 'a' .. 'z' )
            // GraphQueryLexer.g:
            {
            if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LOWER"

    // $ANTLR start "UPPER"
    public final void mUPPER() throws RecognitionException {
        try {
            // GraphQueryLexer.g:64:15: ( 'A' .. 'Z' )
            // GraphQueryLexer.g:
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UPPER"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // GraphQueryLexer.g:65:15: ( '0' .. '9' )
            // GraphQueryLexer.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            // GraphQueryLexer.g:66:15: ( ' ' | '\\t' )
            // GraphQueryLexer.g:
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "SYMBOL"
    public final void mSYMBOL() throws RecognitionException {
        try {
            // GraphQueryLexer.g:69:16: ( '!' | '#' .. '/' | ':' .. '@' | '[' .. '`' | '{' .. '~' )
            // GraphQueryLexer.g:
            {
            if ( input.LA(1)=='!'||(input.LA(1) >= '#' && input.LA(1) <= '/')||(input.LA(1) >= ':' && input.LA(1) <= '@')||(input.LA(1) >= '[' && input.LA(1) <= '`')||(input.LA(1) >= '{' && input.LA(1) <= '~') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SYMBOL"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:72:8: ( ( ( '\\r' )? '\\n' )+ )
            // GraphQueryLexer.g:72:10: ( ( '\\r' )? '\\n' )+
            {
            // GraphQueryLexer.g:72:10: ( ( '\\r' )? '\\n' )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0=='\n'||LA33_0=='\r') ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // GraphQueryLexer.g:72:11: ( '\\r' )? '\\n'
            	    {
            	    // GraphQueryLexer.g:72:11: ( '\\r' )?
            	    int alt32=2;
            	    int LA32_0 = input.LA(1);

            	    if ( (LA32_0=='\r') ) {
            	        alt32=1;
            	    }
            	    switch (alt32) {
            	        case 1 :
            	            // GraphQueryLexer.g:72:11: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }


            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
            } while (true);


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // GraphQueryLexer.g:73:11: ( ( SPACE )+ )
            // GraphQueryLexer.g:73:13: ( SPACE )+
            {
            // GraphQueryLexer.g:73:13: ( SPACE )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0=='\t'||LA34_0==' ') ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // GraphQueryLexer.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
            } while (true);


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // GraphQueryLexer.g:1:8: ( S_JOIN | S_SELECT | S_FROM | S_TAB_READER | S_WHERE | S_GROUP_BY | S_HAVING | S_ORDER_BY | S_MERGE_BY | S_SPLIT_BY | AS | TYPE | STAR | DISTINCT | BY | ON | ASSIGN | IS_EQUAL | SIGN | LEFT_PAREN | RIGHT_PAREN | COMMA | LEFT_BRACE | RIGHT_BRACE | DOT | STATEMENT_TERMINATOR | FROM_TYPE_GRAPH | FROM_TYPE_ELEMENT | FROM_TYPE_NODE | FROM_TYPE_EDGE | FROM_TYPE_ATTRIBUTE | NULL_LITERAL | BOOLEAN_LITERAL | FLOAT | INTEGER | NAME | STRING_LITERAL | NEWLINE | WHITESPACE )
        int alt35=39;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // GraphQueryLexer.g:1:10: S_JOIN
                {
                mS_JOIN(); 


                }
                break;
            case 2 :
                // GraphQueryLexer.g:1:17: S_SELECT
                {
                mS_SELECT(); 


                }
                break;
            case 3 :
                // GraphQueryLexer.g:1:26: S_FROM
                {
                mS_FROM(); 


                }
                break;
            case 4 :
                // GraphQueryLexer.g:1:33: S_TAB_READER
                {
                mS_TAB_READER(); 


                }
                break;
            case 5 :
                // GraphQueryLexer.g:1:46: S_WHERE
                {
                mS_WHERE(); 


                }
                break;
            case 6 :
                // GraphQueryLexer.g:1:54: S_GROUP_BY
                {
                mS_GROUP_BY(); 


                }
                break;
            case 7 :
                // GraphQueryLexer.g:1:65: S_HAVING
                {
                mS_HAVING(); 


                }
                break;
            case 8 :
                // GraphQueryLexer.g:1:74: S_ORDER_BY
                {
                mS_ORDER_BY(); 


                }
                break;
            case 9 :
                // GraphQueryLexer.g:1:85: S_MERGE_BY
                {
                mS_MERGE_BY(); 


                }
                break;
            case 10 :
                // GraphQueryLexer.g:1:96: S_SPLIT_BY
                {
                mS_SPLIT_BY(); 


                }
                break;
            case 11 :
                // GraphQueryLexer.g:1:107: AS
                {
                mAS(); 


                }
                break;
            case 12 :
                // GraphQueryLexer.g:1:110: TYPE
                {
                mTYPE(); 


                }
                break;
            case 13 :
                // GraphQueryLexer.g:1:115: STAR
                {
                mSTAR(); 


                }
                break;
            case 14 :
                // GraphQueryLexer.g:1:120: DISTINCT
                {
                mDISTINCT(); 


                }
                break;
            case 15 :
                // GraphQueryLexer.g:1:129: BY
                {
                mBY(); 


                }
                break;
            case 16 :
                // GraphQueryLexer.g:1:132: ON
                {
                mON(); 


                }
                break;
            case 17 :
                // GraphQueryLexer.g:1:135: ASSIGN
                {
                mASSIGN(); 


                }
                break;
            case 18 :
                // GraphQueryLexer.g:1:142: IS_EQUAL
                {
                mIS_EQUAL(); 


                }
                break;
            case 19 :
                // GraphQueryLexer.g:1:151: SIGN
                {
                mSIGN(); 


                }
                break;
            case 20 :
                // GraphQueryLexer.g:1:156: LEFT_PAREN
                {
                mLEFT_PAREN(); 


                }
                break;
            case 21 :
                // GraphQueryLexer.g:1:167: RIGHT_PAREN
                {
                mRIGHT_PAREN(); 


                }
                break;
            case 22 :
                // GraphQueryLexer.g:1:179: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 23 :
                // GraphQueryLexer.g:1:185: LEFT_BRACE
                {
                mLEFT_BRACE(); 


                }
                break;
            case 24 :
                // GraphQueryLexer.g:1:196: RIGHT_BRACE
                {
                mRIGHT_BRACE(); 


                }
                break;
            case 25 :
                // GraphQueryLexer.g:1:208: DOT
                {
                mDOT(); 


                }
                break;
            case 26 :
                // GraphQueryLexer.g:1:212: STATEMENT_TERMINATOR
                {
                mSTATEMENT_TERMINATOR(); 


                }
                break;
            case 27 :
                // GraphQueryLexer.g:1:233: FROM_TYPE_GRAPH
                {
                mFROM_TYPE_GRAPH(); 


                }
                break;
            case 28 :
                // GraphQueryLexer.g:1:249: FROM_TYPE_ELEMENT
                {
                mFROM_TYPE_ELEMENT(); 


                }
                break;
            case 29 :
                // GraphQueryLexer.g:1:267: FROM_TYPE_NODE
                {
                mFROM_TYPE_NODE(); 


                }
                break;
            case 30 :
                // GraphQueryLexer.g:1:282: FROM_TYPE_EDGE
                {
                mFROM_TYPE_EDGE(); 


                }
                break;
            case 31 :
                // GraphQueryLexer.g:1:297: FROM_TYPE_ATTRIBUTE
                {
                mFROM_TYPE_ATTRIBUTE(); 


                }
                break;
            case 32 :
                // GraphQueryLexer.g:1:317: NULL_LITERAL
                {
                mNULL_LITERAL(); 


                }
                break;
            case 33 :
                // GraphQueryLexer.g:1:330: BOOLEAN_LITERAL
                {
                mBOOLEAN_LITERAL(); 


                }
                break;
            case 34 :
                // GraphQueryLexer.g:1:346: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 35 :
                // GraphQueryLexer.g:1:352: INTEGER
                {
                mINTEGER(); 


                }
                break;
            case 36 :
                // GraphQueryLexer.g:1:360: NAME
                {
                mNAME(); 


                }
                break;
            case 37 :
                // GraphQueryLexer.g:1:365: STRING_LITERAL
                {
                mSTRING_LITERAL(); 


                }
                break;
            case 38 :
                // GraphQueryLexer.g:1:380: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 39 :
                // GraphQueryLexer.g:1:388: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;

        }

    }


    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA35_eotS =
        "\1\uffff\24\50\1\uffff\4\50\1\121\1\122\7\uffff\4\50\1\134\4\uffff"+
        "\27\50\1\166\1\50\1\166\2\50\1\172\1\50\1\172\3\50\2\177\3\uffff"+
        "\10\50\2\uffff\31\50\1\uffff\3\50\1\uffff\4\50\1\uffff\10\50\2\u00af"+
        "\4\50\1\u00b4\1\50\1\u00b4\1\50\1\uffff\1\u00b7\1\u00b8\1\u00b7"+
        "\1\u00b8\21\50\1\u00ca\1\50\1\u00ca\1\u00cc\1\u00cd\1\u00cc\1\u00cd"+
        "\1\uffff\4\50\1\uffff\2\u00b8\2\uffff\2\u00d1\1\50\1\u00d3\1\50"+
        "\1\u00d3\4\50\2\u00d7\5\50\1\uffff\1\50\2\uffff\1\u00de\1\uffff"+
        "\1\u00de\3\uffff\2\u00df\2\uffff\6\50\2\uffff\4\50\2\u00ea\2\50"+
        "\2\u00ed\1\uffff\2\u00ee\2\uffff";
    static final String DFA35_eofS =
        "\u00ef\uffff";
    static final String DFA35_minS =
        "\1\11\1\117\1\157\1\105\1\145\1\101\1\141\1\101\1\141\1\110\1\150"+
        "\1\122\1\162\1\101\1\141\1\116\1\156\1\105\1\145\1\123\1\163\1\uffff"+
        "\1\111\1\151\1\131\1\171\1\75\1\60\7\uffff\1\104\1\144\1\117\1\157"+
        "\1\56\4\uffff\1\111\1\151\2\114\2\154\1\117\1\114\1\157\1\154\1"+
        "\102\1\120\1\125\1\142\1\160\1\165\1\105\1\145\1\101\1\141\1\126"+
        "\1\166\1\104\1\60\1\144\1\60\1\122\1\162\1\60\1\124\1\60\1\164\1"+
        "\123\1\163\2\60\3\uffff\1\105\1\107\1\145\1\147\1\104\1\114\1\144"+
        "\1\154\2\uffff\1\116\1\156\1\105\1\111\1\145\1\151\1\115\1\123\1"+
        "\155\1\163\1\40\2\105\1\40\2\145\1\122\1\162\1\125\1\120\1\165\1"+
        "\160\1\111\1\151\1\105\1\uffff\1\145\1\107\1\147\1\uffff\1\122\1"+
        "\162\1\124\1\164\1\uffff\1\115\1\105\1\155\1\145\1\105\1\114\1\145"+
        "\1\154\2\60\1\103\1\124\1\143\1\164\1\60\1\105\1\60\1\145\1\uffff"+
        "\4\60\1\105\1\145\1\120\1\110\1\160\1\150\1\116\1\156\1\122\1\162"+
        "\1\105\1\145\1\111\1\151\1\111\1\151\1\105\1\60\1\145\5\60\1\uffff"+
        "\1\124\1\40\1\164\1\40\1\uffff\2\60\2\uffff\2\60\1\40\1\60\1\40"+
        "\1\60\1\107\1\147\2\40\2\60\1\102\1\142\1\116\1\156\1\116\1\uffff"+
        "\1\156\2\uffff\1\60\1\uffff\1\60\3\uffff\2\60\2\uffff\1\125\1\165"+
        "\1\103\1\143\1\124\1\164\2\uffff\1\124\1\164\1\124\1\164\2\60\1"+
        "\105\1\145\2\60\1\uffff\2\60\2\uffff";
    static final String DFA35_maxS =
        "\1\175\1\117\1\157\1\120\1\160\1\122\1\162\1\131\1\171\1\110\1\150"+
        "\1\122\1\162\1\101\1\141\1\122\1\162\1\105\1\145\1\124\1\164\1\uffff"+
        "\1\111\1\151\1\131\1\171\1\75\1\71\7\uffff\1\114\1\154\1\125\1\165"+
        "\1\71\4\uffff\1\111\1\151\2\114\2\154\1\117\1\114\1\157\1\154\1"+
        "\102\1\120\1\125\1\142\1\160\1\165\1\105\1\145\1\117\1\157\1\126"+
        "\1\166\1\104\1\172\1\144\1\172\1\122\1\162\1\172\1\124\1\172\1\164"+
        "\1\123\1\163\2\172\3\uffff\1\105\1\107\1\145\1\147\1\104\1\114\1"+
        "\144\1\154\2\uffff\1\116\1\156\1\105\1\111\1\145\1\151\1\115\1\123"+
        "\1\155\1\163\1\40\2\105\1\40\2\145\1\122\1\162\1\125\1\120\1\165"+
        "\1\160\1\111\1\151\1\105\1\uffff\1\145\1\107\1\147\1\uffff\1\122"+
        "\1\162\1\124\1\164\1\uffff\1\115\1\105\1\155\1\145\1\105\1\114\1"+
        "\145\1\154\2\172\1\103\1\124\1\143\1\164\1\172\1\105\1\172\1\145"+
        "\1\uffff\4\172\1\105\1\145\1\120\1\110\1\160\1\150\1\116\1\156\1"+
        "\122\1\162\1\105\1\145\1\111\1\151\1\111\1\151\1\105\1\172\1\145"+
        "\5\172\1\uffff\1\124\1\40\1\164\1\40\1\uffff\2\172\2\uffff\2\172"+
        "\1\40\1\172\1\40\1\172\1\107\1\147\2\40\2\172\1\102\1\142\1\116"+
        "\1\156\1\116\1\uffff\1\156\2\uffff\1\172\1\uffff\1\172\3\uffff\2"+
        "\172\2\uffff\1\125\1\165\1\103\1\143\1\124\1\164\2\uffff\1\124\1"+
        "\164\1\124\1\164\2\172\1\105\1\145\2\172\1\uffff\2\172\2\uffff";
    static final String DFA35_acceptS =
        "\25\uffff\1\15\6\uffff\1\24\1\25\1\26\1\27\1\30\1\31\1\32\5\uffff"+
        "\1\44\1\45\1\46\1\47\44\uffff\1\22\1\21\1\23\10\uffff\1\42\1\43"+
        "\31\uffff\1\20\3\uffff\1\13\4\uffff\1\17\22\uffff\1\4\34\uffff\1"+
        "\1\4\uffff\1\3\2\uffff\1\14\1\41\21\uffff\1\36\1\uffff\1\35\1\40"+
        "\1\uffff\1\12\1\uffff\1\5\1\6\1\33\2\uffff\1\10\1\11\6\uffff\1\2"+
        "\1\7\12\uffff\1\34\2\uffff\1\16\1\37";
    static final String DFA35_specialS =
        "\u00ef\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\53\1\52\2\uffff\1\52\22\uffff\1\53\1\uffff\1\51\5\uffff\1"+
            "\34\1\35\1\25\1\33\1\36\1\33\1\41\1\uffff\12\47\1\uffff\1\42"+
            "\1\uffff\1\32\3\uffff\1\23\1\30\1\50\1\26\1\43\1\5\1\13\1\15"+
            "\1\50\1\1\2\50\1\21\1\45\1\17\3\50\1\3\1\7\2\50\1\11\3\50\6"+
            "\uffff\1\24\1\31\1\50\1\27\1\44\1\6\1\14\1\16\1\50\1\2\2\50"+
            "\1\22\1\46\1\20\3\50\1\4\1\10\2\50\1\12\3\50\1\37\1\uffff\1"+
            "\40",
            "\1\54",
            "\1\55",
            "\1\56\12\uffff\1\57",
            "\1\60\12\uffff\1\61",
            "\1\63\20\uffff\1\62",
            "\1\65\20\uffff\1\64",
            "\1\66\20\uffff\1\70\6\uffff\1\67",
            "\1\71\20\uffff\1\73\6\uffff\1\72",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\103\3\uffff\1\102",
            "\1\105\3\uffff\1\104",
            "\1\106",
            "\1\107",
            "\1\110\1\111",
            "\1\112\1\113",
            "",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\12\47",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\124\7\uffff\1\123",
            "\1\126\7\uffff\1\125",
            "\1\127\5\uffff\1\130",
            "\1\131\5\uffff\1\132",
            "\1\133\1\uffff\12\47",
            "",
            "",
            "",
            "",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\160\15\uffff\1\157",
            "\1\162\15\uffff\1\161",
            "\1\163",
            "\1\164",
            "\1\165",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\167",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\170",
            "\1\171",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\173",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\174",
            "\1\175",
            "\1\176",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "",
            "",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0092",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00b5",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00b6",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00cb",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00cf",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d2",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d2",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d6",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00d8",
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "",
            "\1\u00dd",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "",
            "",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\1\u00eb",
            "\1\u00ec",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "\12\50\7\uffff\32\50\4\uffff\1\50\1\uffff\32\50",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( S_JOIN | S_SELECT | S_FROM | S_TAB_READER | S_WHERE | S_GROUP_BY | S_HAVING | S_ORDER_BY | S_MERGE_BY | S_SPLIT_BY | AS | TYPE | STAR | DISTINCT | BY | ON | ASSIGN | IS_EQUAL | SIGN | LEFT_PAREN | RIGHT_PAREN | COMMA | LEFT_BRACE | RIGHT_BRACE | DOT | STATEMENT_TERMINATOR | FROM_TYPE_GRAPH | FROM_TYPE_ELEMENT | FROM_TYPE_NODE | FROM_TYPE_EDGE | FROM_TYPE_ATTRIBUTE | NULL_LITERAL | BOOLEAN_LITERAL | FLOAT | INTEGER | NAME | STRING_LITERAL | NEWLINE | WHITESPACE );";
        }
    }
 

}