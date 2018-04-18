package invenio.op.parser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.AbstractParser;
import qng.core.parser.QueryParser;

public class TextToLogicalExpressionParser extends AbstractParser<String, LogicalExpression> implements
		QueryParser<String, LogicalExpression> {

	@Override
	public LogicalExpression parse(String query) throws QueryException {
		GraphQueryLexer lex = new GraphQueryLexer(new ANTLRStringStream(query));
       	CommonTokenStream tokens = new CommonTokenStream(lex);
        GraphQueryParser parser = new GraphQueryParser(tokens);

        try {
        	GraphQueryParser.query_return parserResult =
            	parser.query(); // start rule method
            CommonTree tree = (CommonTree) parserResult.getTree();
            
            GraphQueryTree finalTree = new GraphQueryTree(new CommonTreeNodeStream(tree));
            LogicalExpression exp = finalTree.query();
    		return exp;

        } catch (RecognitionException e)  {
            throw new QueryException("Error parsing query [" + query + "]: " + e.getMessage(), e);
        }
	}
}
